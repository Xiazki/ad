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
            "columnDefs": [],
            "drawCallback": function (settings) {

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