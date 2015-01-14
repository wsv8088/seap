/**
 * Created by dbwangshuang on 2015/1/14.
 */
// 定义与grid相关的对象
var grid = {};
grid.init = function(options) {
    var op = $.extend({
        mtype: "post",
        datatype: "json",
        height: 300,
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,
        multiselect: false,
        multikey: "ctrlKey",
        multiboxonly: true,
        autowidth: true,
        rownumbers: true,
    }, options);

}