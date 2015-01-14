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
 * 1.弹出层        z-index:3000+
 * 2.系统confirm            z-index:4000
 * 3.系统alert            z-index:9000+
 * 4.系统loading            z-index:999999
 * @author guopengliang
 * @version 1.2.2
 */

var Jd = {};
Jd.goTo = function (options) {

    var op = $.extend({
        url: '',
        type: 'ajax',// ajax,iframe
        success: null
    }, options);

    if (op.type == 'iframe') {
        $("#mainContent").empty().append('<iframe id="main_iframe" name="main_iframe" src="' + op.url + '" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>');
    }
    else if (op.type == 'ajax') {
        $("#mainContent").empty().append("<div style='text-align:center;margin-top:100px;font-size:12px;'>页面加载中,请稍后...</div>");
        $.ajax({
            url: op.url,
            type: 'GET',
            dataType: 'html',
            async: true,
            cache: false,
            success: function (result) {
                var trimResult = $.trim(result);
                if (trimResult.indexOf("{") == 0) {
                    var res = eval('(' + result + ')');
                    $("#mainContent").empty().append(res.message);
                    Jd.alert(res.message);
                    return;
                }

                $("#mainContent").empty().append(result);
                // 回调函数
                if (!op.success) {
                    return;
                }
                op.success();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                Jd.alert("系统繁忙请稍后再试" + errorThrown);
            }
        });
    }
    else {
        Jd.alert("页面跳转类型错误");
        return;
    }

};

Jd.DataGrid({toolbart: 30,})

Jd.DataGrid = function (obj) {
    this.options = $.extend({
        target: 'dg',// 自定义
        checkbox: false,// 自定义
        url: null,
        title: '',
        tooL: null,
        border: false,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        checkOnSelect: true,
        showHeader: true,
        remoteSort: true,
        columns: null,
        nowrap: true,
        striped: true,
        fitColumns: true,
        idField: null,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        onBeforeRefresh: function () {
        },
        onLoadSuccess: function (data) {
            if (data.success) {

            } else {
                if (data.message == undefined) {
                    Jd.alert('未知错误,检查返回类型');
                    return;
                }

                Jd.alert(data.message);
                return;
            }
        }
    }, obj);

    this.init = function () {
        if (this.options.checkbox == true) {
            options.frozenColumns = [[{field: 'ck', checkbox: true}]];
        }
        $('#' + this.options.target).datagrid(this.options);
    };


    this.queryData = function (obj) {
        var options = $.extend({
            url: null,
            data: null
        }, obj);


        if (options.url != null) {
            var op = this.getOptions();
            op.url = options.url;
            this.options.url = options.url;
        }

        if (options.data != null) {
            var param = this.getQueryParams();
            var data = options.data;
            for (var o in data) {
                param[o] = data[o];
            }
            this.options.queryParams = param;
        }

        this.loadData();
    };
    this.getQueryParams = function () {
        var options = this.getOptions();
        var queryParams = options.queryParams;
        return queryParams;
    };
    this.getOptions = function () {
        var op = $('#' + this.options.target).datagrid('options');
        return op;
    };
    this.loadData = function () {
        $('#' + this.options.target).datagrid('load');
    };
    this.reloadData = function () {
        $('#' + this.options.target).datagrid('reload');
    };
    this.hundlerGrid = function (option) {
        $('#' + this.options.target).datagrid(option);
    };
    this.getSelections = function () {
        var selections = $('#' + this.options.target).datagrid('getSelections');
        return selections;
    };
    this.getRows = function () {
        var rows = $('#' + this.options.target).datagrid('getRows');
        return rows;
    };
};


Jd.grid = {
    defaultTarget: 'dg',
    loadGrid: function (opt) {

        var p = $.extend({
            url: null,
            title: '',
            toolbar: null,
            border: false,
            pagination: true,
            rownumbers: true,
            singleSelect: true,
            checkOnSelect: true,
            showHeader: true,
            remoteSort: true,
            columns: null,
            nowrap: true,
            striped: true,
            fitColumns: true,
            idField: null,
            pageNumber: 1,
            pageSize: 10,
            pageList: [10, 20, 30, 40, 50],
            onBeforeRefresh: function () {
            },
            onLoadSuccess: function (data) {
                if (data.success) {

                } else {
                    if (data.message == undefined) {
                        Jd.alert('未知错误,检查返回类型');
                        return;
                    }

                    Jd.alert(data.message);
                    return;
                }
            }
        }, opt);


        if (opt.checkbox) {
            p.frozenColumns = [[{
                field: 'ck',
                checkbox: true
            }]];
        }


        var target = opt.target ? opt.target : this.defaultTarget;

        $('#' + target).datagrid(p);
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
        var options = $('#' + target).datagrid('options');
        return options;
    },
    loadData: function (target) {
        if (target == undefined) {
            target = this.defaultTarget;
        }
        $('#' + target).datagrid('load');
    },
    reloadData: function (target) {
        if (target == undefined) {
            target = this.defaultTarget;
        }
        $('#' + target).datagrid('reload');
    },
    hundlerGrid: function (target, option) {
        if (target == undefined) {
            target = this.defaultTarget;
        }
        $('#' + target).datagrid(option);
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
        var rows = $('#' + target).datagrid('getRows');
        return rows;
    }
};
Jd.alert = function (options) {
    var op = {
        title: '提示',
        content: '内容',
        type: 'attention',
        width: 'auto',
        movable: true,
        retrieveTop: null
    };

    if (typeof options == 'object') {
        op = $.extend(op, options);
    } else {
        op.content = options;
    }


    var alertOption = {
        overlay: 20,
        overlayClass: 'whiteOverlay',
        retrieveTop: op.retrieveTop,
        movable: true// 添加'movable:true'提示框能移动
    };

    // alert标志的个数
    var jqmAlertArray = $('.jqmAlertModal');

    var jqmAlertNumber = jqmAlertArray.length;
    // alert的id
    var jqmAlertId = "jqmAlert" + jqmAlertNumber;

    var jqmAlertClose = "jqmAlertClose_" + jqmAlertId;

    // z-index
    var jqmAlertZIndex = 9000 + jqmAlertNumber;
    // 宽度 自适应,min-width 200px max-width:400px不支持IE6
    var jqmAlertHtml = '<div id="' + jqmAlertId + '" class="modal jqmAlertModal">' +
        '<h1 class="tl"><div class="tr"><span class="tit">' + op.title +
        '</span><span class="modalClose ' + jqmAlertClose + '">关闭</span></div></h1>' +
        '<div class="moadalCon">' +
        '<div class="clearfix fakeMsg ' + op.type + '">' +
        '<i class="ico"></i>' +
        '<div class="conText">' +
        op.content +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="modalFooter pb20">' +
        '<a href="javascript:void(0);" class="btn btn-s ' + jqmAlertClose + '"><s><b><span>关闭</span></b></s></a>' +
        '</div>' +
        '<div class="bl">' +
        '<div class="br"></div>' +
        '</div>' +
        '</div>';
    $('body').append(jqmAlertHtml);

    $('#' + jqmAlertId).css({'top': '30%', 'position': 'fixed', 'z-index': jqmAlertZIndex});
    if (op.width == 'auto') {
        $('#' + jqmAlertId).css({'min-width': '200px', 'max-width': '400px'});
    } else {
        $('#' + jqmAlertId).css({'width': op.w + 'px'});
    }

    if (op.retrieveTop) {
        $('#' + jqmAlertId).css({'top': op.retrieveTop()});
    }
    // 关闭事件
    $('#' + jqmAlertId + ' .' + jqmAlertClose + '').click(function () {
        $('#' + jqmAlertId).jqmHide().remove();
    });
    // 显示层
    $('#' + jqmAlertId).jqm(alertOption);
    $('#' + jqmAlertId).jqmShow();

};


Jd.confirm = function (options) {
    var op = {
        title: '确认',
        content: '内容',
        type: 'attention',
        width: 370,
        movable: true,
        onConfirm: null,
        onCancel: null,
        retrieveTop: null
    };

    op = $.extend(op, options);


    var confirmOption = {
        overlay: 20,
        overlayClass: 'whiteOverlay',
        retrieveTop: op.retrieveTop,
        movable: true
    };

    // confirm标志的个数
    var jqmConfirmArray = $('.jqmConfirmModal');

    var jqmConfirmNumber = jqmConfirmArray.length;
    // confirm的id
    var jqmConfirmId = "jqmConfirm" + jqmConfirmNumber;

    var jqmConfirmClose = "jqmConfirmClose_" + jqmConfirmId;
    var jqmConfirmCancel = "jqmConfirmCancel_" + jqmConfirmId;

    // z-index
    var jqmConfirmZIndex = 4000 + jqmConfirmNumber;

    var jqmConfirmBtn = jqmConfirmId + "_btn";
    // z-index为4000,要高于自定义层的弹出
    var jqmConfirmHtml = '<div id="' + jqmConfirmId + '" class="modal jqmConfirmModal">' +
        '<h1 class="tl"><div class="tr"><span class="tit">'
        + op.title
        + '</span><span class="modalClose ' + jqmConfirmClose + '">关闭</span></div></h1>' +
        '<div class="moadalCon"><div class="clearfix fakeMsg '
        + op.type
        + '"><i class="ico"></i><div class="conText">'
        + op.content
        + '</div></div></div>'
        + '<div class="modalFooter pb20">'
        + '<a href="javascript:void(0);"id="' + jqmConfirmBtn + '" class="btn btn-m mr25"><s><b><span>确定</span></b></s></a>'
        + '<a href="javascript:void(0);" class="btn btn-s ' + jqmConfirmCancel + '"><s><b><span>取消</span></b></s></a></div>'
        + '<div class="bl"><div class="br"></div></div></div>';
    $('body').append(jqmConfirmHtml);

    $('#' + jqmConfirmId).css({'width': op.width + 'px', 'top': '30%', 'position': 'fixed', 'z-index': jqmConfirmZIndex});

    if (op.retrieveTop) {
        $('#' + jqmConfirmId).css({'top': op.retrieveTop()});
    }
    $('#' + jqmConfirmId).jqm(confirmOption);
    $('#' + jqmConfirmId).jqmShow();
    $('#' + jqmConfirmBtn).click(function () {
        if (!op.onConfirm) {
            return;
        }

        $('#' + jqmConfirmId).jqmHide().remove();
        op.onConfirm();

    });
    $('#' + jqmConfirmId + ' .' + jqmConfirmClose).click(function () {
        $('#' + jqmConfirmId).jqmHide().remove();
    });

    $('#' + jqmConfirmId + ' .' + jqmConfirmCancel).click(function () {
        $('#' + jqmConfirmId).jqmHide().remove();
        if (op.onCancel) {
            op.onCancel();
        }
    });

};

Jd.timerConfirm = function (options) {

    var op = {
        title: '确认',
        content: '内容',
        type: 'attention',
        width: 370,
        movable: true,
        maxTime: 10,
        onConfirm: null,
        onCancel: null,
        retrieveTop: null
    };

    op = $.extend(op, options);


    var confirmOption = {
        overlay: 20,
        overlayClass: 'whiteOverlay',
        retrieveTop: op.retrieveTop,
        movable: true
    };

    // confirm标志的个数
    var jqmConfirmArray = $('.jqmConfirmModal');

    var jqmConfirmNumber = jqmConfirmArray.length;
    // confirm的id
    var jqmConfirmId = "jqmConfirm" + jqmConfirmNumber;

    var jqmConfirmClose = "jqmConfirmClose_" + jqmConfirmId;
    var jqmConfirmCancel = "jqmConfirmCancel_" + jqmConfirmId;

    // z-index
    var jqmConfirmZIndex = 4000 + jqmConfirmNumber;

    var jqmConfirmBtn = jqmConfirmId + "_btn";


    // z-index为4000,要高于自定义层的弹出
    var jqmConfirmHtml = '<div id="' + jqmConfirmId + '" class="modal jqmConfirmModal">' +
        '<h1 class="tl"><div class="tr"><span class="tit">'
        + op.title
        + '</span><span class="modalClose ' + jqmConfirmClose + '">关闭</span></div></h1>' +
        '<div class="moadalCon"><div class="clearfix fakeMsg '
        + op.type
        + '"><i class="ico"></i><div class="conText">'
        + op.content
        + '</div></div></div>'
        + '<div class="modalFooter pb20">'
        + '<a href="javascript:void(0);"id="' + jqmConfirmBtn + '" class="btn btn-m mr25"><s><b><span>确定</span>' + '<span id="' + jqmConfirmBtn + '_time"></span></b></s></a>'
        + '<a href="javascript:void(0);" class="btn btn-s ' + jqmConfirmCancel + '"><s><b><span>取消</span></b></s></a></div>'
        + '<div class="bl"><div class="br"></div></div></div>';
    $('body').append(jqmConfirmHtml);

    var $jqmConfirm = $("#" + jqmConfirmId);

    $jqmConfirm.css({'width': op.width + 'px', 'top': '30%', 'position': 'fixed', 'z-index': jqmConfirmZIndex});

    if (op.retrieveTop) {
        $jqmConfirm.css({'top': op.retrieveTop()});
    }
    $jqmConfirm.jqm(confirmOption);
    $jqmConfirm.jqmShow();

    $('#' + jqmConfirmBtn).click(function () {
        if (!op.onConfirm) {
            return;
        }

        clearInterval(interval);
        $jqmConfirm.jqmHide().remove();
        op.onConfirm();

    });
    $('#' + jqmConfirmId + ' .' + jqmConfirmClose).click(function () {
        $jqmConfirm.jqmHide().remove();
    });

    $('#' + jqmConfirmId + ' .' + jqmConfirmCancel).click(function () {
        $jqmConfirm.jqmHide().remove();
        clearInterval(interval);
        if (op.onCancel) {
            op.onCancel();
        }
    });

    var t = op.maxTime;

    var interval = null;

    interval = setInterval(function () {

        $('#' + jqmConfirmBtn + "_time").html("(" + t + ")");
        if (t === 0) {
            clearInterval(interval);
            $jqmConfirm.jqmHide().remove();
            if (op.onConfirm) {
                op.onConfirm();
            }

        } else {
            t = t - 1;
        }

    }, 1000);

};

Jd.modal = {
    show: function (obj) {

        Jd.loading.show();

        var option = $.extend({
            id: 'jqmodal',
            dataType: 'local',//local,ajax
            title: 'title',
            content: '',
            width: 400,
            height: 'auto',
            position: 'fixed',
            retrieveTop: null,
            onClose: null,
            buttons: [],
            closeButton: true,
            moveable: true,
            data: {},
            success: null
        }, obj);

        // Modal的个数
        var modalArray = $('.modal');

        var modalNumber = modalArray.length;
        // Modal的唯一id
        var modalId = option.id;
        // 验证id是否允许创建
        var m = document.getElementById(modalId);
        // id不允许直接返回
        if (m != null) {
            alert("id " + modalId + " has been used!");
            return;
        }

        // Modal的z-index从3000开始
        var zIndex = 3000 + modalNumber;
        // 通过class名称绑定关闭事件的
        var jqmClose = "jqmClose_" + modalId;
        // Modal主体内容显示的id
        var jqmodalCon = "jqmodalCon_" + modalId;

        var modalFooter = "modalFooter_" + modalId;

        var modalConHeight = null;

        if (option.height != 'auto') {
            modalConHeight = option.height + "px";
        }
        // Modal的html代码
        var jqmodal = "<div id='" + modalId + "' class='modal'>" +
            "<h1 class='tl'>" +
            "<div class='tr'>" +
            "<span class='tit'>" + option.title + "</span>" +
            "<span class='modalClose " + jqmClose + "'>关闭</span>" +
            "</div>" +
            "</h1>" +
            "<div id='" + jqmodalCon + "' class='moadalCon'>" +
                // 内容
            "</div>" +

            "<div id='" + modalFooter + "' class='modalFooter pb20'>" +
                // 按钮
            '</div>' +

            "<div class='bl'>" +
            "<div class='br'></div>" +
            "</div>";
        "</div>";

        $('body').append(jqmodal);

        // 弹出层css
        $('#' + modalId).css({'width': option.width, 'z-index': zIndex, 'top': '15%', 'position': option.position});

        // 内容居中显示
        $('#' + jqmodalCon).css({'text-align': 'center'});

        // 内容的高度
        if (modalConHeight) {
            $('#' + jqmodalCon).css({'height': modalConHeight});
        }

        // 自定义按钮
        if (option.buttons && option.buttons.length > 0) {
            var btnString = this.getButtons(option.buttons);
            $('#' + modalFooter).append(btnString);
        }
        // 关闭按钮
        if (option.closeButton) {
            var closeBtnStr = '<a type="button" class="btn btn-s mr20 '
                + jqmClose + '"><s><b><span>取消</span></b></s></a>';
            $('#' + modalFooter).append(closeBtnStr);
        }

        // top自定义
        if (option.retrieveTop) {
            $('#' + modalId).css({top: option.retrieveTop()});
        }


        // 定义jqm
        $('#' + modalId).jqm({
            closeClass: jqmClose,
            overlayClass: 'whiteOverlay',
            movable: option.moveable,
            retrieveTop: option.retrieveTop
        });

        // 绑定关闭事件
        $('#' + modalId + ' .' + jqmClose).click(function () {
            $('#' + modalId).jqmHide().remove();
            if (option.onClose) {
                option.onClose();
            }
        });

        // 如果是ajax类型
        if (option.dataType == 'ajax') {
            $.ajax({
                url: obj.url,
                type: 'GET',
                async: true,
                cache: false,
                dataType: 'html',
                data: option.data,
                success: function (resultHtml) {
                    // 向主体div写html
                    $('#' + jqmodalCon).empty().append(resultHtml);

                    Jd.loading.hide();
                    $('#' + modalId).jqmShow();
                    // 回调函数
                    if (!option.success) {
                        return;
                    }
                    option.success();
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    Jd.alert("系统繁忙请稍后再试" + textStatus);
                    Jd.loading.hide();
                }

            });
        } else {
            $('#' + jqmodalCon).empty().append(option.content);
            Jd.loading.hide();
            $('#' + modalId).jqmShow();
            if (!option.success) {
                return;
            }
            option.success();
        }

    },
    close: function (modalId) {
        $('#' + modalId).jqmHide().remove();
    },
    getButtons: function (buttonArray) {
        var buttonHtml = "";
        for (var i in buttonArray) {
            var buttonName = buttonArray[i].name;
            var buttonClickFunc = buttonArray[i].functionName;
            buttonHtml = buttonHtml +
            '<a type="button" class="btn btn-s mr20" onclick="'
            + buttonClickFunc + '();"><s><b><span>' + buttonName + '</span></b></s></a>';
        }
        return buttonHtml;
    }


};


Jd.loading = {
    show: function () {
        var op = {
            content: '加载中...',
            overlay: 20,
            overlayClass: 'whiteOverlay',
            width: 200
        };

        var jqmOp = {
            overlay: op.overlay,
            overlayClass: op.overlayClass
        };
        // 不移除直接显示
        if ($('#jqmLoading').length) {
            $('#jqmLoading').jqm(jqmOp);
            $('#jqmLoading').jqmShow();
            return;
        }
        // z-index : 999999
        var jqmLoading = '<div id="jqmLoading" class="jqmLoading hide">' +
            '<span class=""></span><span style="line-height:30px;" class="ml10 font1">' + op.content + '</span>' +
            '</div>';
        $('body').append(jqmLoading);
        $('#jqmLoading').css({
            'z-index': 999999,
            'width': op.width + 'px',
            'text-align': 'center',
            'height': '30px;',
            'background': '#cdcdcd',
            'border': '1px solid #000000'
        });
        $('#jqmLoading').jqm(jqmOp).jqDrag('.jqDrag');
        $('#jqmLoading').jqmShow();
    },
    hide: function () {
        $('#jqmLoading').jqmHide();
    }
};

