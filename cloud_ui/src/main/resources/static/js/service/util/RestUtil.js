var com;
(function (com) {
    var cloudo;
    (function (cloudo) {
        var util;
        (function (util) {
            /**
             * rest工具类，想后台发送请求
             * TODO 后期可完善接口加密功能
             */
            var RestUtil = /** @class */ (function () {
                function RestUtil() {
                }
                /**
                 * 获取后台服务地址
                 * @returns {string}
                 */
                RestUtil.getBackgroundURL = function () {
                    return localStorage.getItem("backgroundURL");
                };
                /**
                 * 获取token
                 * @returns {string}
                 */
                RestUtil.getToken = function () {
                    return localStorage.getItem("token");
                };
                /**
                 * 发送ajax请求，异步
                 * @param {string} url 请求URL
                 * @param {Object} parameterSet 请求参数
                 * @param {Function} success 请求成功回调函数
                 * @param {Function} error 请求失败回调函数
                 * @param successCallbackParameters 请求成功参数（可变参数）
                 */
                RestUtil.postAjax = function (url, parameterSet, success, error) {
                    var successCallbackParameters = [];
                    for (var _i = 4; _i < arguments.length; _i++) {
                        successCallbackParameters[_i - 4] = arguments[_i];
                    }
                    RestUtil.postAjaxBase.apply(RestUtil, [true, url, parameterSet, success, error].concat(successCallbackParameters));
                };
                /**
                 * 发送ajax请求，同步
                 * @param {string} url 请求URL
                 * @param {Object} parameterSet 请求参数
                 * @param {Function} success 请求成功回调函数
                 * @param {Function} error 请求失败回调函数
                 * @param successCallbackParameters 请求成功参数（可变参数）
                 */
                RestUtil.postAjaxAsync = function (url, parameterSet, success, error) {
                    var successCallbackParameters = [];
                    for (var _i = 4; _i < arguments.length; _i++) {
                        successCallbackParameters[_i - 4] = arguments[_i];
                    }
                    RestUtil.postAjaxBase.apply(RestUtil, [false, url, parameterSet, success, error].concat(successCallbackParameters));
                };
                /**
                 * 发送ajax请求，异步
                 * @param async ture同步  false异步
                 * @param {string} url 请求URL
                 * @param {Object} parameterSet 请求参数
                 * @param {Function} success 请求成功回调函数
                 * @param {Function} error 请求失败回调函数
                 * @param successCallbackParameters 请求成功参数（可变参数）
                 */
                RestUtil.postAjaxBase = function (async, url, parameterSet, success, error) {
                    var successCallbackParameters = [];
                    for (var _i = 5; _i < arguments.length; _i++) {
                        successCallbackParameters[_i - 5] = arguments[_i];
                    }
                    $.ajax({
                        type: "post",
                        url: RestUtil.getBackgroundURL() + url,
                        dataType: "json",
                        async: async,
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
                            request.setRequestHeader("Authorization", RestUtil.getToken());
                        }
                    });
                };
                //默认的成功回调函数
                RestUtil.defalutSuccessFunction = function (data, success, error) {
                    var successCallbackParameters = [];
                    for (var _i = 3; _i < arguments.length; _i++) {
                        successCallbackParameters[_i - 3] = arguments[_i];
                    }
                    if (data.code == 200) {
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
                //默认的失败回调函数
                RestUtil.defalutErrorFunction = function (data, error) {
                    if (error != null) {
                        error(data);
                    }
                    else {
                        console.log(data);
                        layer.alert('数据获取异常！', { icon: 0 });
                    }
                };
                return RestUtil;
            }());
            util.RestUtil = RestUtil;
        })(util = cloudo.util || (cloudo.util = {}));
    })(cloudo = com.cloudo || (com.cloudo = {}));
})(com || (com = {}));
