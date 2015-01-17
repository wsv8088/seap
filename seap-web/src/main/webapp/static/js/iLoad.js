var iLoadJS = iLoadJS || {

        //初始化Jquery的Ajax请求配置信息，可以让请求过的JS缓存到客户端
        initJqueryConfig: (function () {
            $.ajaxSetup({
                cache: true
            });
        })(),

        /*--------------------------------------------
         * Desc: 更新对应区域的内容;
         * arg: contentId		->需要加载的DIV区域ID
         * 		url				->需要引入的文件的URL
         * 		_paramData		->参数对象，可以封装到URL中
         * 		_callbackFun	->载入成功后的回调方法
         * ret: 无
         * Date:   2014/02/28;
         ---------------------------------------------*/
        loadPage: (function (url, params, callbackFunction) {
            this.load("page-content", url, params, callbackFunction);
        }),
        load: (function (containerId, url, params, callbackFunction) {
            if (!document.getElementById(containerId)) {
                //alert("传递的容器ID在页面中无法找到！");
                return false;
            }
            if (!url) {
                alert("请传递需要加载的URL！");
                return false;
            }
            if (url.indexOf("?") != -1) {
                url += "&v=" + new Date().getTime();
            } else {
                url += "?v=" + new Date().getTime();
            }
            showWaiting();
            $("#" + containerId).load(url, params || null, function (data) {
                //hideWaiting();
                try {
                    if (callbackFunction && typeof(callbackFunction) == "function") {
                        callbackFunction(data);
                    }
                }
                catch (e) {
                    alert("调用回调方法异常！");
                }
            });
        })

    };


