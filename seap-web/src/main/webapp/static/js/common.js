// 加载后绑定部分常用控件的事件
$(function () {
    // 绑定树菜单的选中事件,改变各自节点的外观样式
    $("ul[class='submenu'] li a").bind("click", function (e) {
        // 获取触发事件的控件<a>
        var obj = e.target;
        $("ul[class='nav nav-list'] li").removeClass();
        $(obj).parents("li").attr("class", "active open");
    });
    // 其他
});


function showWaiting() {
}

function hideWaiting() {
}


var seap = {};
/**
 *
 * @param options
 * {url(请求链接):xx,params(参数):{a:xx, b:xx},type:ajax or iframe,callback(回调方法名)}
 */
seap.loadpage = function (options) {
    var op = $.extend({
        target: 'page-content',
        url: '',
        params: null,
        type: 'ajax',// ajax,iframe
        callback: null
    }, options);

    if (op.type == 'iframe') {
        var str_iframe = "<iframe id='mainframe' name='mainframe' src='" + op.url
            + "' frameborder=0 scrolling='auto' width='100%'></iframe>";
        $("#page-content").empty().append(str_iframe);
    } else if (op.type == 'ajax') {
        $("#" + op.target).load(op.url, op.params || null, function(data) {
            if (typeof(op.callback) == "function") {
                op.callback();
            } else if (typeof(op.callback) == "string") {
                eval(op.callback + "()");
            }
        }) ;

    } else {
        alert("页面跳转类型错误");
        return;
    }
};

seap.grid = {
    // 默认grid的容器id(div/table/...)
    defaultTarget: 'dg',
    // 加载grid控件到指定的容器
    load: function (op) {
        var options = $.extend({
            target: "grid",
            mtype: "POST",
            datatype: "json",
            height: 300,
            viewrecords: true,
            pager: "#pager",
            rowNum: 10,
            toolbar: true,
            rowList: [10, 20, 30, 40, 50],
            altRows: true,
            multiselect: true,
            multikey: "ctrlKey",
            multiboxonly: true,
            autowidth: true,
            rownumbers: true,
            nav: false
            // 自定义传递到后台的参数名
        }, op);
        var target = options.target ? options.target : this.defaultTarget;
        $(target).jqGrid(options);
    }


}