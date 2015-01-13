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

