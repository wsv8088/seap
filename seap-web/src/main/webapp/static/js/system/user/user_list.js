function test() {
    $.ajax({
        type: 'POST',
        url: "http://localhost/user/user_list",
        dataType: "json"
    });
    alert("");
}
function init() {
    seap.grid.load({
        target: "#grid-table",
        url: "#springUrl('')/user/user_list",
        colNames: [' ', '公司', '部门', '用户名', '真实姓名', '邮箱', '联系电话'],
        colModel: [
            {},
            {name: 'company', index: 'id', width: 150},
            {name: 'department', index: 'sdate', width: 80},
            {name: 'loginName', index: 'name', width: 80},
            {name: 'realName', index: 'stock', width: 80},
            {name: 'email', index: 'stock', width: 120},
            {name: 'mobile', index: 'stock', width: 120}

        ],
        pager: "#grid-pager",
        loadComplete: function () {
        }

    });

    $("#grid-table").jqGrid('navGrid', "#grid-pager",
        { 	//navbar options
            edit: true,
            editicon: 'icon-pencil blue',
            add: true,
            addicon: 'icon-plus-sign purple',
            del: true,
            delicon: 'icon-trash red',
            search: true,
            searchicon: 'icon-search orange',
            refresh: true,
            refreshicon: 'icon-refresh green',
            view: true,
            viewicon: 'icon-zoom-in grey',
        },
        {
            recreateForm: true,
            beforeShowForm: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }
        },
        {
            //new record form
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: false,
            beforeShowForm: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }
        },
        {
            //delete record form
            recreateForm: true,
            beforeShowForm: function (e) {
                var form = $(e[0]);
                if (form.data('styled')) return false;

                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_delete_form(form);

                form.data('styled', true);
            },
            onClick: function (e) {
                alert(1);
            }
        },
        {
            //search form
            recreateForm: true,
            afterShowSearch: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                style_search_form(form);
            },
            afterRedraw: function () {
                style_search_filters($(this));
            }
            ,
            multipleSearch: true
            /**
             multipleGroup:true,
             showQuery: true
             */
        },
        {
            //view record form
            recreateForm: true,
            beforeShowForm: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
            }
        }
    )


}