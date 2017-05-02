var Project = function(){

    var init = function(){
        initPlugin();
        formValid();
    };

    var initPlugin = function(){

    };

    var submit = function(){

    };

    var formValid = function(){
        $('#project-save').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                projectName: {
                    required: true
                },
                projectDesc: {
                    required: true
                },
                serverIp: {
                    required: true
                },
                path :{
                    required:true
                }
            },

            messages: {
                projectName: {
                    required: "项目名称是必填项"
                },
                projectDesc: {
                    required: "项目描述是必填项"
                },
                serverIp: {
                    required: "服务器IP是必填项"
                },
                path: {
                    required: "发布路径是必填项"
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
    Project.init();
});