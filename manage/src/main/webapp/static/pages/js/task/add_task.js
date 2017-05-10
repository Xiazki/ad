var AddTask = function () {

    var thisEditor;
    var init = function () {
        $("#submit").bind('click', save);
        initTasksDetailDiv();
    };

    var initTasksDetailDiv = function () {
        var editor = new wangEditor('detail_editor');
        editor.config.uploadImgUrl = "/file/upload";
        editor.config.uploadImgFileName = 'file';
        editor.config.uploadImgFns.onload = function (resultText, xhr) {
            console.info(resultText);
            editor.command(null, 'insertHtml', '<img src=' + resultText + ' style="max-width:100%;"/>');
        };
        editor.create();
        thisEditor = editor;
    };

    var save = function () {
        $.ajax({
            url: '/task/add',
            type: 'post',
            data: {
                title: $('#title').val(),
                detail: thisEditor.$txt.html()
            },
            dataType: 'json',
            success: function (d) {
                if (d['success']) {
                    window.location.href = '/task';
                }
            }
        });
    };

    return {
        init: function () {
            init();
        }
    }
}();
jQuery(document).ready(function () {
    AddTask.init();
});