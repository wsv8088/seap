/**
 * Created by dbwangshuang on 2015/1/14.
 */
// 定义与grid相关的对象
var seap = {};
// grid定义与封装
seap.grid = {
    // 加载grid控件到指定的容器
    load: function (op) {
        this.options = $.extend({
            target: "grid",
            mtype: "post",
            datatype: "json",
            height: 300,
            viewrecords: true,
            pager: "pager",
            rowNum: 10,
            toolbar: true,
            rowList: [10, 20, 30, 40, 50],
            altRows: true,
            multiselect: false,
            multikey: "ctrlKey",
            multiboxonly: true,
            autowidth: true,
            rownumbers: true,
            nav: false
            // 自定义传递到后台的参数名
        }, op);
    },

    queryData: function (obj) {
        var target = obj.target ? obj.target : this.defaultTarget;
        var options = $.extend({
            target: target,
            url: null,
            data: null
        }, obj);


        if (options.url != null) {
            var op = this.getOptions(target);
            op.url = options.url;
        }

        if (options.data != null) {
            var queryParams = this.getQueryParams(target);
            var data = options.data;
            for (var o in data) {
                queryParams[o] = data[o];
            }
        }
        this.loadData(target);
    },
    getQueryParams: function (target) {
        if (target == undefined) {
            target = this.defaultTarget;
        }
        var queryParams = $('#' + target).datagrid('options').queryParams;
        return queryParams;
    },
    getOptions: function (target) {
        if (target == undefined) {
            target = this.defaultTarget;
        }
        return $('#' + target).jqGrid('getGridParam');
    },
    refresh: function (target) {
        if (target == undefined) {
            target = this.defaultTarget;
        }
        $('#' + target).datagrid('reload');
    },
    getSelections: function (target) {
        if (target == undefined) {
            target = this.defaultTarget;
        }
        var selections = $('#' + target).datagrid('getSelections');
        return selections;
    },
    getRows: function (target) {
        if (target == undefined) {
            target = this.defaultTarget;
        }
        var rows = $('#' + target).jqGrid('getRowData');
        return rows;
    }
}
