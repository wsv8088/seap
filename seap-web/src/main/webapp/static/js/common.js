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


/**
 * Created by dbwangshuang on 2015/1/14.
 */
var seap = {};
seap.goto = function(options) {
    var op = $.extend({
        url:'',
        type:'ajax',// ajax,iframe
        success:null
    }, options);

    if(op.type == 'iframe') {
        $("#page-content").empty().append('<iframe id="main_iframe" name="main_iframe" src="' + op.url + '" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>');
    }
    else if(op.type == 'ajax') {
        $("#page-content").empty().append("<div style='text-align:center;margin-top:100px;font-size:12px;'>页面加载中,请稍后...</div>");
        $.ajax({
            url:op.url,
            type:'GET',
            dataType:'html',
            async:true,
            cache: false,
            success:function(result){
                var trimResult = $.trim(result);
                if(trimResult.indexOf("{") == 0) {
                    var res = eval('(' +result + ')');
                    $("#page-content").empty().append(res.message);
                    Jd.alert(res.message);
                    return;
                }

                $("#page-content").empty().append(result);
                // 回调函数
                if(!op.success) {
                    return;
                }
                op.success();
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                Jd.alert("系统繁忙请稍后再试" + errorThrown);
            }
        });
    }
    else {
        Jd.alert("页面跳转类型错误");
        return;
    }

};


// grid定义与封装
seap.grid = {
    // 默认grid的容器id(div/table/...)
    defaultTarget: '#dg',
    defaultPager: '#pager',
    // 加载grid控件到指定的容器
    load: function (op) {
        var options = $.extend({
            target: this.defaultTarget,
            mtype: "post",
            datatype: "json",
            height: 300,
            viewrecords: true,
            pager: this.defaultPager,
            rowNum: 10,
            toolbar: true,
            rowList: [10, 20, 30, 40, 50],
            altRows: true,
            multiselect: false,
            multikey: "ctrlKey",
            multiboxonly: true,
            autowidth: true,
            rownumbers: true
            // 自定义传递到后台的参数名
        }, op);
        $(options.target).jqGrid(options);
    },

    /** 获取grid数据的过滤条件 **/
    getQueryParams: function (target) {
        return this.getGridParam(target);
    },
    getGridParam: function (target, paramName) {
        if (paramName) {
            return $(target).jqGrid('getGridParam', paramName);
        }
        else {
            return $('#' + target).jqGrid('getGridParam');
        }
    },
    reload: function (target) {
        $('#' + target).jqGrid().trigger("reloadGrid");
    },
    getSelections: function (target) {
        return this.getGridParam(target, "selrow");
    },
    getRows: function (target) {
        var selectId = this.getSelections(target);
        return $('#' + target).jqGrid('getRowData', selectId);
    }
}

