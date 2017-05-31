var Project = function () {

    var init = function () {

        initProjectList();

    };

    var initProjectList = function () {
        var table = $("#project-list").DataTable({
            ordering: false,
            searching: true,
            "scrollX": false,
            "stateSave": true,
            "processing": true,
            'serverSide': true,
            "ajax": {
                url: "/project/list"
            },
            'pageLength': 20,
            lengthChange: false,
            "columns": [
                {"data": "projectName"},
                {"data": "type"},
                {"data": "serverIp"},
                {"data": "path"},
                {"data": "status"},
                {"data": "createTime"},
                {"data": "id"}
            ],
            "columnDefs": [{
                "render": function (data, type, row) {
                    var but = "";
                    var pt = row['type'];
                    if (pt == 1) {
                        but = 'java服务';
                    } else {
                        but = 'javaWeb项目';
                    }

                    return but;
                },
                "targets": 1
            }, {
                "render": function (data, type, row) {
                    var but = "";
                    var status = row['status'];
                    if (status == 1) {
                        but += '<span class="label label-sm label-success">正在运行</span>';
                    } else if (status == 2) {
                        but += '<span class="label label-sm label-info">正在部署</span>';
                    } else {
                        but += '<span class="label label-sm label-danger">已停止</span>';
                    }

                    return but;
                },
                "targets": 4
            }, {
                "render": function (data, type, row) {
                    var but = "";
                    var id = row['id'];
                    but += '<button type="button" class="btn yellow btn-outline btn-xs user-config" id="user_' + id + '">配置用户</button>';
                    but += '<button type="button" class="btn dark btn-outline btn-xs edit-config" id="edit_' + id + '">修改</button>';
                    return but;
                },
                "targets": 6
            }],
            "drawCallback": function (settings) {
                $(".user-config").bind('click', function () {

                });
                $(".edit-config").bind('click', function () {
                    var id = $(this).attr('id').split("_")[1];
                    window.open('/project//detail/' + id);
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