$(document).ready(function () {
    //创建对象
});
function testMd5() {
    console.log(MD5Util.MD5Encrypt("abc", "admin", 1));
}
function test() {
    RestUtil.postAjaxAuth("mobile/queryRain", { startTime: "2016-07-01", endTime: "2016-09-01" }, null, null);
}
function login() {
    Login.login();
}
var com;
(function (com) {
    var bootdo;
    (function (bootdo) {
        var util;
        (function (util) {
            var security;
            (function (security) {
                var Login = /** @class */ (function () {
                    function Login() {
                    }
                    Login.login = function () {
                        localStorage.setItem("baseUrl", Login.baseUrl);
                        RestUtil.postAjax("mobileLogin", { username: "admin", password: "admin" }, success, null);
                        function success(data) {
                            console.log(data);
                            //将获取的信息存储在localStorage中
                            localStorage.setItem(RestUtil.AUTHORITY, data[RestUtil.AUTHORITY]);
                            localStorage.setItem(RestUtil.ENCRYPT_TOKEN, data[RestUtil.ENCRYPT_TOKEN]);
                            localStorage.setItem(RestUtil.USERNAME, data[RestUtil.USERNAME]);
                        }
                    };
                    Login.baseUrl = "http://localhost:8096/";
                    return Login;
                }());
                security.Login = Login;
                var StringUtil = /** @class */ (function () {
                    function StringUtil() {
                    }
                    /**
                     * 生成指定长度的随机串
                     * @param {number} length 随机串长度默认32
                     * @returns {string} 随机串
                     */
                    StringUtil.randomString = function (length) {
                        length = length || 32;
                        var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
                        var maxPos = $chars.length;
                        var pwd = '';
                        for (var i = 0; i < length; i++) {
                            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
                        }
                        return pwd;
                    };
                    return StringUtil;
                }());
                security.StringUtil = StringUtil;
                /**
                 * MD5工具类
                 */
                var MD5Util = /** @class */ (function () {
                    function MD5Util() {
                    }
                    MD5Util.MD5Encrypt = function (value, salt, hashIterations) {
                        //第一次加密
                        // let str : string = $.md5(value + salt);
                        // //迭代
                        // for (let i=0; i<hashIterations - 1; i++) {
                        //     str = $.md5(str)
                        // }
                        // return str;
                        return CryptoJS.MD5(value).toString();
                    };
                    return MD5Util;
                }());
                security.MD5Util = MD5Util;
                var RestUtil = /** @class */ (function () {
                    function RestUtil() {
                    }
                    /**
                     * 接口加密post请求
                     * @param {string} url
                     * @param {Object} parameterSet
                     * @param {Function} success
                     * @param {Function} error
                     * @param successCallbackParameters
                     */
                    RestUtil.postAjaxAuth = function (url, parameterSet, success, error) {
                        var successCallbackParameters = [];
                        for (var _i = 4; _i < arguments.length; _i++) {
                            successCallbackParameters[_i - 4] = arguments[_i];
                        }
                        $.ajax({
                            type: "post",
                            url: localStorage.getItem("baseUrl") + url,
                            dataType: "json",
                            contentType: 'application/json; charset=UTF-8',
                            // data : JSON.stringify、(parameterSet || {}),
                            data: JSON.stringify(parameterSet || {}),
                            success: function (data) {
                                RestUtil.defalutSuccessFunction.apply(RestUtil, [data, success, error].concat(successCallbackParameters));
                            },
                            error: function (data) {
                                RestUtil.defalutErrorFunction(data, error);
                            },
                            beforeSend: function (request) {
                                request.setRequestHeader("authority", localStorage.getItem(RestUtil.AUTHORITY));
                                //获取签名信息
                                var signatureObject = RestUtil.createSignature();
                                request.setRequestHeader(RestUtil.SIGNATURE, signatureObject[RestUtil.SIGNATURE]);
                                signatureObject[RestUtil.SIGNATURE] == null;
                                request.setRequestHeader(RestUtil.SIGNATURE_PARAMS, JSON.stringify(signatureObject));
                            }
                        });
                    };
                    /**
                     * 发送ajax请求
                     * @param {string} url
                     * @param {Object} parameterSet
                     * @param {Function} success
                     * @param {Function} error
                     * @param successCallbackParameters
                     */
                    RestUtil.postAjax = function (url, parameterSet, success, error) {
                        var successCallbackParameters = [];
                        for (var _i = 4; _i < arguments.length; _i++) {
                            successCallbackParameters[_i - 4] = arguments[_i];
                        }
                        $.ajax({
                            type: "post",
                            url: localStorage.getItem("baseUrl") + url,
                            dataType: "json",
                            contentType: 'application/json; charset=UTF-8',
                            // data : JSON.stringify、(parameterSet || {}),
                            data: JSON.stringify(parameterSet || {}),
                            success: function (data) {
                                RestUtil.defalutSuccessFunction.apply(RestUtil, [data, success, error].concat(successCallbackParameters));
                            },
                            error: function (data) {
                                RestUtil.defalutErrorFunction(data, error);
                            },
                            beforeSend: function (request) {
                                request.setRequestHeader("authority", localStorage.getItem(RestUtil.AUTHORITY));
                            }
                        });
                    };
                    //生成token信息
                    RestUtil.createSignature = function () {
                        //生成随机串
                        var noncestr = StringUtil.randomString(32);
                        //生成时间戳
                        var timestamp = new Date().getTime() + "";
                        //生成签名
                        var str = "token=" + localStorage.getItem(RestUtil.AUTHORITY) + "&noncestr" + noncestr + "&timestamp" + timestamp + "&encryptToken" + localStorage.getItem(RestUtil.ENCRYPT_TOKEN);
                        //生成签名 TODO 待完善盐值迭代加密
                        // let signature = MD5Util.MD5Encrypt(str, localStorage.getItem(RestUtil.USERNAME), 2);
                        var signature = MD5Util.MD5Encrypt(str, null, RestUtil.HASH_ITERATIONS);
                        var signatureObject = {};
                        signatureObject["noncestr"] = noncestr;
                        signatureObject["timestamp"] = timestamp;
                        signatureObject[RestUtil.SIGNATURE] = signature;
                        return signatureObject;
                    };
                    RestUtil.defalutSuccessFunction = function (data, success, error) {
                        var successCallbackParameters = [];
                        for (var _i = 3; _i < arguments.length; _i++) {
                            successCallbackParameters[_i - 3] = arguments[_i];
                        }
                        if (data.code == "SUCCESS") {
                            if (success != null) {
                                //执行回调函数
                                success.apply(void 0, [data.data].concat(successCallbackParameters));
                            }
                            else {
                                //没有请求成功回调，则打印请求数据
                                console.log(data);
                            }
                        }
                        else {
                            RestUtil.defalutErrorFunction(data, error);
                        }
                    };
                    RestUtil.defalutErrorFunction = function (data, error) {
                        if (error != null) {
                            error(data);
                        }
                        else {
                            console.log(data);
                            // layer.alert('数据获取异常！', {icon: 0});
                        }
                    };
                    //存储token信息的key
                    RestUtil.AUTHORITY = "authority";
                    //存储认证token
                    RestUtil.ENCRYPT_TOKEN = "encryptToken";
                    RestUtil.SIGNATURE_PARAMS = "signatureParams";
                    RestUtil.SIGNATURE = "signature";
                    RestUtil.USERNAME = "username";
                    //迭代次数
                    RestUtil.HASH_ITERATIONS = 1;
                    return RestUtil;
                }());
                security.RestUtil = RestUtil;
            })(security = util.security || (util.security = {}));
        })(util = bootdo.util || (bootdo.util = {}));
    })(bootdo = com.bootdo || (com.bootdo = {}));
})(com || (com = {}));
var RestUtil = com.bootdo.util.security.RestUtil;
var Login = com.bootdo.util.security.Login;
var MD5Util = com.bootdo.util.security.MD5Util;
