var Role = function () {

    var init = function () {

        initRoleList();

    };

    var initRoleList = function () {
        var table = $("#role-list").DataTable({
            ordering: false,
            searching: false,
            "scrollX": false,
            "stateSave": true,
            "processing": true,
            'serverSide': true,
            "ajax": {
                url: "/role/list"
            },
            'pageLength': 20,
            lengthChange: false,
            "columns": [
                {"data": "roleName"},
                {"data": "roleDesc"},
                {"data": "id"}
            ],
            "columnDefs": [{
                "render": function (data, type, row) {
                    var but = "";
                    var id = row['id'];
                    but += '<button type="button" class="btn yellow btn-outline btn-xs permission-config" id="permission_' + id + '">配置权限</button>';
                    but += '<button type="button" class="btn dark btn-outline btn-xs edit-config" id="edit_' + id + '">修改</button>';
                    but += '<button type="button" class="btn red btn-outline btn-xs delete-config" id="delete_' + id + '">删除</button>';
                    return but;
                },
                "targets": 2
            }],
            "drawCallback": function (settings) {

                $(".permission-config").unbind("click");
                $(".permission-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.location.href = "/role/toAddPermission/" + id;
                });

                $(".edit-config").unbind("click");
                $(".edit-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.location.href = "/role/edit/" + id;
                });
                $(".delete-config").unbind("click");
                $(".delete-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.location.href = "/role/delete/" + id;
                    $.ajax({
                        url: "/role/delete/" + id,
                        dataType: 'JSON',
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
                            }
                        }
                    });
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
    Role.init();
});