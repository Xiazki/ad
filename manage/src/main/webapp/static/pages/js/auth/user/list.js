var User = function () {

    var init = function () {

        initUserList();

    };

    var initUserList = function () {
        var table = $("#user-list").DataTable({
            ordering: false,
            searching: false,
            "scrollX": false,
            "stateSave": true,
            "processing": true,
            'serverSide': true,
            "ajax": {
                url: "/user/list"
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
                    but += '<button type="button" class="btn yellow btn-outline btn-xs permission-config" id="permission_' + id + '">配置权限</button>'
                    but += '<button type="button" class="btn dark btn-outline blue btn-xs edit-config" id="edit_' + id + '">修改</button>';
                    but += '<button type="button" class="btn red btn-outline blue btn-xs delete-config" id="delete_' + id + '">删除</button>';
                    return but;
                },
                "targets": 4
            }],
            "drawCallback": function (settings) {

                $(".permission-config").unbind("click");
                $(".permission-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.location.href = "/user/toAddPermission/" + id;
                });

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
    User.init();
});