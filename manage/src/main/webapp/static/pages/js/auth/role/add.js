var RoleAdd = function () {

    var init = function () {
        handleRoleAdd();
    };

    var submit = function () {
        var $submit = $("#submit");
        $submit.addClass("disabled");
        $submit.val("提交中");
        $.ajax({
            url: "/role/add",
            type: "POST",
            data: {
                id: $("#id").val() == "" ? 0 : $("#id").val(),
                roleName: $("#roleName").val(),
                roleDesc: $("#roleDesc").val()
            },
            dataType: "JSON",
            success: function (d) {
                if (d['success']) {
                    window.location.href = '/role';
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
            },
            error: function (d) {
                $submit.removeClass("disabled");
                $submit.val("提交");
            }
        });
    };

    var handleRoleAdd = function () {
        $('#permission-add').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                roleName: {
                    required: true
                },
                roleDesc: {
                    required: true
                }
            },

            messages: {
                permissionName: {
                    required: "名称是必填项"
                },
                permissionDesc: {
                    required: "描述是必填项"
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
    RoleAdd.init();
});