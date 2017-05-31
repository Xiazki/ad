var Project = function () {

    var init = function () {
        initPlugin();
        formValid();
        initUserList();
    };

    var initPlugin = function () {
        var $submit = $("#submit");
        $submit.bind('click', function () {
            $('#project-save').submit();
        });
        var $subDeploy = $("#sub_deploy");
        $subDeploy.bind('click', function () {
            $('#basic').modal('show')
        });

        var $over = $("#over");
        $over.bind('click', function () {
            $('#basic').modal('hide');
            $('#log').modal('show');
            showLog();
        });

        var $showRrojectLog = $("#showProLog");
        $showRrojectLog.bind('click',function(){
            $('#proLog').modal('show');
        });

        var $log_close = $("#log_close");
        $log_close.bind('click', function () {

        });

    };

    var closeSocket = function (socket) {
        socket.close();
    };

    var showProLog = function (){

    };

    var showLog = function () {

        $('#log_content').html("");

        var id = $("#id").val();
        //temp
        var websocket = new SockJS("/log?projectId=" + id);
        websocket.onopen = function (event) {
            console.info("connected!");
        };
        websocket.onmessage = function (event) {
            $('#log_content').append(event.data);
        };
        websocket.onerror = function (event) {
            console.info("error!")
        };
        websocket.onclose = function (event) {
            console.info("closed!");
        }
    };

    var submit = function () {
        var $submit = $("#submit");
        $submit.addClass("disabled");
        $submit.val("提交中");
        $.ajax({
            url: "/project/save",
            type: "post",
            data: {
                id: $("#id").val() == null ? 0 : $("#id").val(),
                projectName: $("#projectName").val(),
                desc: $("#projectDesc").val(),
                serverIp: $("#serverIp").val(),
                path: $("#path").val()
            },
            dataType: "JSON",
            success: function (d) {
                if (d['success']) {
                    window.location.href = '/project/detail/' + $("#id").val();
                } else {
                    $.notify(d['message'], {
                        type: 'danger', animate: {
                            enter: 'animated fadeInDown',
                            exit: 'animated fadeOutUp'
                        }
                    });
                    $submit.removeClass("disabled");
                    $submit.val("保存");
                }
            }
        });
    };

    var formValid = function () {
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
                path: {
                    required: true
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

    var initUserList = function () {
        var table = $("#user-list").DataTable({
            ordering: false,
            searching: false,
            "scrollX": false,
            "stateSave": true,
            "processing": true,
            'serverSide': true,
            "ajax": function (data, callback, settings) {
                $.ajax({
                    url: "/project/attend_user/list",
                    dataType: 'JSON',
                    data: {
                        start: data.start,
                        length: data.length,
                        draw: data.draw,
                        searchInfo: data.search.value,
                        id: $("#id").val()
                    },
                    success: function (d) {
                        callback(d);
                    }
                });
            },
            'pageLength': 20,
            lengthChange: false,
            "columns": [
                {"data": "username"},
                {"data": "department"},
                {"data": "job"},
                {"data": "email"},
                {"data": "id"}
            ],
            "columnDefs": [{
                "render": function (data, type, row) {
                    var but = "";
                    var id = row['id'];
                    but += '<button type="button" class="btn dark btn-outline blue btn-xs edit-config" id="edit_' + id + '">修改</button>';
                    but += '<button type="button" class="btn red btn-outline blue btn-xs delete-config" id="delete_' + id + '">删除</button>';
                    return but;
                },
                "targets": 4
            }],
            "drawCallback": function (settings) {
                $(".edit-config").unbind("click");
                $(".edit-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.location.href = "/user/edit/" + id;
                });
            }
        }).on('error.dt', function (e, settings, techNote, message) {
            console.log('An error has been reported by DataTables: ', message);
            window.location.href = "/login";
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