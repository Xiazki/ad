var AddQuestion = function () {

    var thisEditor;

    var init = function () {

        $("#submit").bind('click', submit);
        var select = $("select");
        select.select2();
        select.select2({
            minimumResultsForSearch: -1
        });
        initSearchUser();
        initQuestionEditor();
    };

    var initQuestionEditor = function () {
        var editor = new wangEditor('question_editor');
        editor.config.uploadImgUrl = "/file/upload";
        editor.config.uploadImgFileName = 'file';
        editor.config.uploadImgFns.onload = function (resultText, xhr) {
            console.info(resultText);
            editor.command(null, 'insertHtml', '<img src=' + resultText + ' style="max-width:100%;"/>');
        };
        editor.create();
        thisEditor = editor;
    };

    var initSearchUser = function () {
        var $selectUser = $("#select_users");
        $selectUser.select2({
            ajax: {
                url: "/search/user",
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        q: params.term, // search term
                        page: params.page
                    }
                        ;
                },
                processResults: function (data) {
                    return {
                        results: data
                    };
                },
                cache: true
            },
            escapeMarkup: function (markup) {
                return markup;
            }, // let our custom formatter work
            minimumInputLength: 1,
            templateResult: function (repo) {
                if (repo.loading) return repo.text;
                return repo.text;
            },
            templateSelection: function (d) {
                return d.text;
            }
        });
    };

    var submit = function () {
        $.ajax({
                url: '/question/add',
                type: 'post',
                dataType: 'JSON',
                data: {
                    title: $("#title").val(),
                    principalId: $("#select_users").val(),
                    priority: $("#priority").val(),
                    questionDesc: thisEditor.$txt.html()
                },
                success: function (d) {
                    if (d['success']) {
                        window.location.href = '/question'
                    } else {
                        $.notify(d['message'], {
                            type: 'danger', animate: {
                                enter: 'animated fadeInDown',
                                exit: 'animated fadeOutUp'
                            }
                        });
                    }
                }
            }
        );
    };

    return {
        init: function () {
            init();
        }
    }
}();
jQuery(document).ready(function () {
    AddQuestion.init();
});