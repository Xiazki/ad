var Permission = function () {

    var init = function () {

        var $submit = $("#submit");
        $submit.bind('click', submit);
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
            "ajax": function (data, callback, settings) {
                $.ajax({
                    url: "/user/permission/list",
                    dataType: 'JSON',
                    data: {
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
                {"data": "permissionId"},
                {"data": "permissionName"},
                {"data": "permissionDesc"}
            ],
            "columnDefs": [{
                "render": function (data, type, row) {
                    var but = "";
                    var id = row['permissionId'];
                    var hasChooseed = row['hasChoosed'];
                    var disable = row['disable'];
                    if (hasChooseed) {
                        if (disable) {
                            but += '<input type="checkbox" name="permissions" value="' + id + '" checked disabled>'
                        } else {
                            but += '<input type="checkbox" class="user-permission" name="permissions" value="' + id + '" checked>'
                        }
                    } else {
                        but += '<input type="checkbox" class="user-permission" name="permissions" value="' + id + '">'
                    }
                    return but;
                },
                "targets": 0
            }],
            "drawCallback": function (settings) {
            }
        }).on('error.dt', function (e, settings, techNote, message) {
            console.log('An error has been reported by DataTables: ', message);
            window.location.href = "/login";
        });
    };

    var submit = function () {
        var id = $("#id").val();
        var pers = [];
        $(".user-permission:checked").each(function () {
            pers.push($(this).val());
        });
        if (pers.length <= 0) {
            $.notify('情选择权限', {
                type: 'danger', animate: {
                    enter: 'animated fadeInDown',
                    exit: 'animated fadeOutUp'
                }
            });
            return;
        }
        $.ajax({
            url: '/user/permission/add',
            type: 'POST',
            data: {
                'id': id,
                'permissions[]': pers
            },
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