var AddToDo = function () {

    var submit = function () {
        $.ajax({
            url: "/todo/add",
            type: 'post',
            data: {
                title: $("#title").val(),
                content: $("#content").val()
            },
            success: function (d) {
                $("#close").click();
                window.location.href = "/todo/list";
            }
        });
    };
    var initPlug = function () {
        var $submit = $("#submit");
        $submit.bind('click', submit);

        $(".todo-complete").bind('click', function () {
            var id = $(this).attr('id').split('_')[1];
            $.ajax({
                url: '/todo/complete',
                type: 'get',
                data: {
                    id: id
                },
                dataType:'JSON',
                success: function (d) {
                    if (d['success']) {
                        window.location.href = '/todo/list';
                    } else {
                        $.notify(d['message'], {
                            type: 'danger', animate: {
                                enter: 'animated fadeInDown',
                                exit: 'animated fadeOutUp'
                            }
                        });
                    }
                }
            })
        });

        $(".todo-delete").bind('click', function () {
            var id = $(this).attr('id').split('_')[1];
            $.ajax({
                url: '/todo/delete',
                type: 'get',
                data: {
                    id: id
                },
                dataType:'JSON',
                success: function (d) {
                    if (d['success']) {
                        window.location.href = '/todo/list';
                    } else {
                        $.notify(d['message'], {
                            type: 'danger', animate: {
                                enter: 'animated fadeInDown',
                                exit: 'animated fadeOutUp'
                            }
                        });
                    }
                }
            });
        });
    };

    return {
        //main function to initiate the module
        init: function () {
            initPlug();
        }

    };

}();

jQuery(document).ready(function () {
    AddToDo.init();
});