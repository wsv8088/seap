/**
 * Created by dbwangshuang on 2015/1/16.
 */
function clickMe() {
    var params = grid.getQueryParams("#grid-table");
}


function init() {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    seap.grid.load({
        target: grid_selector,
        url: "#springUrl('')/user/user_list",
        colNames: [' ', '公司', '部门', '用户名', '真实姓名', '邮箱', '联系电话'],
        colModel: [
            {},
            {name: 'company', index: 'company', width: 150},
            {name: 'department', index: 'department', width: 80},
            {name: 'loginName', index: 'loginName', width: 80},
            {name: 'realName', index: 'realName', width: 80},
            {name: 'email', index: 'email', width: 120},
            {name: 'mobile', index: 'mobile', width: 120}

        ],
        pager: pager_selector,
        loadComplete: function () {
        }

    });
}
