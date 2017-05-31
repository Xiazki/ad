var Detail = function () {

    var init = function () {

        var select = $("select");
        select.select2();
        select.select2({
            minimumResultsForSearch: -1
        });

        initSearchUser();
        initPlugin();
    };

    var initPlugin = function () {
        var $submit = $('#submit');
        $submit.bind('click', updateStatus);
        //初始化select2
        $('.deal').bind('click', function () {
            var status = $(this).attr('id').split("_")[1];
            $("#status").val(status);
            $('#basic').modal('show');
        });

        var $publicSubmit = $('#public_submit');
        $publicSubmit.bind('click', setQuestionPublic);
        $('#set_public').bind('click', function () {
            $('#public').modal('show');
        });

        //var transfer = $('#transfer');
        //$publicSubmit.bind('click', setQuestionPublic);

        var $transfer = $("#tranform_submit");
        $transfer.bind('click',transfer);

        $('#transfer').bind('click', function () {
            $('#tranform').modal('show');
        });

    };

    var transfer = function () {
        $.ajax({
            url: '/question/transfer',
            data: {
                id: $("#id").val(),
                userId: $("#select_users").val()
            },
            dataType: 'json',
            success: function (d) {
                if (d['success']) {
                    $('#tranform').modal('hide');
                    window.location.href = '/question';
                } else {
                    $('#public').modal('hide');
                    $.notify(d['message'], {
                        type: 'danger', animate: {
                            enter: 'animated fadeInDown',
                            exit: 'animated fadeOutUp'
                        }
                    });
                }
            }
        });
    };

    var setQuestionPublic = function () {
        $.ajax({
            url: '/question/toPublic/' + $('#id').val(),
            data: {
                title: $("#public_title").val(),
                content: $("#public_content").val()
            },
            dataType: 'json',
            success: function (d) {
                if (d['success']) {
                    $('#public').modal('hide');
                    $.notify("发布成功", {
                        type: 'success', animate: {
                            enter: 'animated fadeInDown',
                            exit: 'animated fadeOutUp'
                        }
                    });
                } else {
                    $('#public').modal('hide');
                    $.notify(d['message'], {
                        type: 'danger', animate: {
                            enter: 'animated fadeInDown',
                            exit: 'animated fadeOutUp'
                        }
                    });
                }
            }
        });
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

    var updateStatus = function () {
        $.ajax({
            url: '/question/deal/' + $('#id').val(),
            data: {
                status: $("#status").val(),
                event: $("#content").val()
            },
            dataType: 'json',
            success: function (d) {
                if (d['success']) {
                    window.location.href = "/question"
                } else {
                    $('#basic').modal('hide');
                    $.notify(d['message'], {
                        type: 'danger', animate: {
                            enter: 'animated fadeInDown',
                            exit: 'animated fadeOutUp'
                        }
                    });
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
    Detail.init();
});