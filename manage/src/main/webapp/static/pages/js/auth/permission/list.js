var Permission = function () {

    var init = function () {

        initUserList();

    };

    var initUserList = function () {
        var table = $("#permission-list").DataTable({
            ordering: false,
            searching: false,
            "scrollX": false,
            "stateSave": true,
            "processing": true,
            'serverSide': true,
            "ajax": {
                url: "/permission/list"
            },
            'pageLength': 20,
            lengthChange: false,
            "columns": [
                {"data": "permissionName"},
                {"data": "permissionDesc"},
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
                "targets": 2
            }],
            "drawCallback": function (settings) {
                $(".edit-config").unbind("click");
                $(".edit-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.location.href = "/permission/edit/" + id;
                });
            }
        }).on('error.dt', function (e, settings, techNote, message) {
            console.log('An error has been reported by DataTables: ', message);
            window.location.href = "/lo";
        });
    };

    return {
        init: function () {
            init();
        }
    }
}();
jQuery(document).ready(function () {
    Permission.init();
});