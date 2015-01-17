/**
 * Created by dbwangshuang on 2015/1/14.
 */
var seap = {};
seap.goto = function(options) {
    var op = $.extend({
        url: '',
        type: 'ajax',// ajax,iframe
        success: null
    }, options);

}

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
