var UserAdd = function () {

    var init = function () {
        initPlugin();
        handleUserAdd();
    };

    var initPlugin = function () {
        //初始化select2
        var select = $("select");
        select.select2();
        select.select2({
            minimumResultsForSearch: -1
        });
    }

    var submit = function () {
        var $submit = $("#submit");
        $submit.addClass("disabled");
        $submit.val("提交中");
        $.ajax({
            url: "/user/add",
            type: "post",
            data: {
                id: $("#id").val() == "" ? 0 : $("#id").val(),
                username: $("#username").val(),
                email: $("#email").val(),
                password: $("#password").val(),
                phone: $("#phone").val(),
                department: $("#department").val(),
                job: $("#job").val()
            },
            dataType: "JSON",
            success: function (d) {
                if (d['success']) {
                    window.location.href = '/user';
                } else {
                    $.notify(d['message'], {
                        type: 'danger', animate: {
                            enter: 'animated fadeInDown',
                            exit: 'animated fadeOutUp'
                        }
                    });
                    $submit.removeClass("disabled");
                    $submit.val("提交");
                }
            }
        });
    };

    var handleUserAdd = function () {
        $('#user-add').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                email: {
                    required: true
                }
            },

            messages: {
                username: {
                    required: "用户名是必填项"
                },
                password: {
                    required: "密码是必填项"
                },
                email: {
                    required: "邮箱是必填项"
                }
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function (form) {
                submit(); // form validation success, call ajax form submit
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
    UserAdd.init();
});