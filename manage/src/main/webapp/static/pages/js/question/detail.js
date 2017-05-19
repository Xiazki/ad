var Detail = function () {

    var init = function () {
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