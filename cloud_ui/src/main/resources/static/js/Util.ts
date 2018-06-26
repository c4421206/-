


declare let $ : any; //声明layer
declare let CryptoJS : any;

$(document).ready(function () {
    //创建对象

});

function testMd5() {
    console.log(MD5Util.MD5Encrypt("abc", "admin", 1));
}

function test() {
    RestUtil.postAjaxAuth("mobile/queryRain", {startTime:"2016-07-01", endTime:"2016-09-01"}, null, null);
}

function login() {
    Login.login();
}


namespace com.bootdo.util.security {

    export class Login {
        private static baseUrl : string = "http://localhost:8096/";

        public static login() : void {
            localStorage.setItem("baseUrl", Login.baseUrl);
            RestUtil.postAjax("mobileLogin", {username:"admin", password:"admin"}, success, null);

            function success(data) {
                console.log(data);
                //将获取的信息存储在localStorage中
                localStorage.setItem(RestUtil.AUTHORITY, data[RestUtil.AUTHORITY]);
                localStorage.setItem(RestUtil.ENCRYPT_TOKEN, data[RestUtil.ENCRYPT_TOKEN]);
                localStorage.setItem(RestUtil.USERNAME, data[RestUtil.USERNAME]);
            }
        }
    }

    export class StringUtil {
        /**
         * 生成指定长度的随机串
         * @param {number} length 随机串长度默认32
         * @returns {string} 随机串
         */
        public static randomString(length : number ) : string {
            length = length || 32;
            var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
            var maxPos = $chars.length;
            var pwd = '';
            for (let i = 0; i < length; i++) {
                pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
            }
            return pwd;
        }
    }

    /**
     * MD5工具类
     */
    export class MD5Util {

        public static MD5Encrypt(value : string, salt : string, hashIterations : number) : string {
            //第一次加密
            // let str : string = $.md5(value + salt);
            // //迭代
            // for (let i=0; i<hashIterations - 1; i++) {
            //     str = $.md5(str)
            // }
            // return str;
            return CryptoJS.MD5(value).toString();
        }
    }


    export class RestUtil {
        //存储token信息的key
        public static AUTHORITY : string = "authority";
        //存储认证token
        public static ENCRYPT_TOKEN : string = "encryptToken";

        private static SIGNATURE_PARAMS : string = "signatureParams";

        private static SIGNATURE : string = "signature";

        public static USERNAME : string = "username";
        //迭代次数
        private static HASH_ITERATIONS = 1;

        /**
         * 接口加密post请求
         * @param {string} url
         * @param {Object} parameterSet
         * @param {Function} success
         * @param {Function} error
         * @param successCallbackParameters
         */
        public static postAjaxAuth (url : string, parameterSet : Object, success : Function, error : Function, ...successCallbackParameters : any[]) {
            $.ajax({
                type: "post",
                url : localStorage.getItem("baseUrl") + url,
                dataType:"json",
                contentType : 'application/json; charset=UTF-8',
                // data : JSON.stringify、(parameterSet || {}),
                data : JSON.stringify(parameterSet || {}),
                success : function (data) {
                    RestUtil.defalutSuccessFunction(data, success, error, ...successCallbackParameters);
                },
                error : function (data) {
                    RestUtil.defalutErrorFunction(data, error);
                },
                beforeSend : function (request) {
                    request.setRequestHeader("authority", localStorage.getItem(RestUtil.AUTHORITY));
                    //获取签名信息
                    let signatureObject = RestUtil.createSignature();
                    request.setRequestHeader(RestUtil.SIGNATURE, signatureObject[RestUtil.SIGNATURE]);
                    signatureObject[RestUtil.SIGNATURE] == null;
                    request.setRequestHeader(RestUtil.SIGNATURE_PARAMS, JSON.stringify(signatureObject));
                }
            });
        }

        /**
         * 发送ajax请求
         * @param {string} url
         * @param {Object} parameterSet
         * @param {Function} success
         * @param {Function} error
         * @param successCallbackParameters
         */
        public static postAjax(url : string, parameterSet : Object, success : Function, error : Function, ...successCallbackParameters : any[]) {
            $.ajax({
                type: "post",
                url : localStorage.getItem("baseUrl") + url,
                dataType:"json",
                contentType : 'application/json; charset=UTF-8',
                // data : JSON.stringify、(parameterSet || {}),
                data : JSON.stringify(parameterSet || {}),
                success : function (data) {
                    RestUtil.defalutSuccessFunction(data, success, error, ...successCallbackParameters);
                },
                error : function (data) {
                    RestUtil.defalutErrorFunction(data, error);
                },
                beforeSend : function (request) {
                    request.setRequestHeader("authority", localStorage.getItem(RestUtil.AUTHORITY));
                }
            });
        }

        //生成token信息
        private static createSignature() : any {
            //生成随机串
            let noncestr : string = StringUtil.randomString(32);
            //生成时间戳
            let timestamp : string = new Date().getTime() + "";
            //生成签名
            let str : string = "token=" + localStorage.getItem(RestUtil.AUTHORITY) + "&noncestr" + noncestr + "&timestamp" + timestamp + "&encryptToken" + localStorage.getItem(RestUtil.ENCRYPT_TOKEN);
            //生成签名 TODO 待完善盐值迭代加密
            // let signature = MD5Util.MD5Encrypt(str, localStorage.getItem(RestUtil.USERNAME), 2);
            let signature = MD5Util.MD5Encrypt(str, null, RestUtil.HASH_ITERATIONS);
            let signatureObject = {};
            signatureObject["noncestr"] = noncestr;
            signatureObject["timestamp"] = timestamp;
            signatureObject[RestUtil.SIGNATURE] = signature;
            return signatureObject;
        }

        private static defalutSuccessFunction(data : any, success : Function, error : Function, ...successCallbackParameters) {
            if (data.code == "SUCCESS") {
                if (success != null) {
                    //执行回调函数
                    success(data.data,  ...successCallbackParameters);
                } else {
                    //没有请求成功回调，则打印请求数据
                    console.log(data);
                }
            } else {
                RestUtil.defalutErrorFunction(data, error);
            }
        }

        private static defalutErrorFunction(data : any, error : Function) {
            if (error != null) {
                error(data);
            } else {
                console.log(data);
                // layer.alert('数据获取异常！', {icon: 0});
            }
        }
    }
}
import RestUtil = com.bootdo.util.security.RestUtil;
import Login = com.bootdo.util.security.Login;
import MD5Util = com.bootdo.util.security.MD5Util;