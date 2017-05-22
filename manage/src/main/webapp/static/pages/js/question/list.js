var Question = function () {

    var init = function () {

        initQuestionList(1, 'question_list');
        initQuestionList(2, 'creator_q_list');
        initNeedVerifyList();
    };

    var initQuestionList = function (tableType, tableId) {
        var table = $("#" + tableId).DataTable({
            ordering: false,
            searching: true,
            "language": {
                "search": "查询:"
            },
            "scrollX": false,
            "stateSave": true,
            "processing": true,
            'serverSide': true,
            "ajax": function (data, callback, settings) {
                $.ajax({
                    url: '/question/list',
                    dataType: 'JSON',
                    data: {
                        start: data.start,
                        length: data.length,
                        draw: data.draw,
                        type: tableType,
                        'search[value]': data.search.value
                    },
                    success: function (d) {
                        callback(d);
                    }
                })
            },
            'pageLength': 20,
            lengthChange: false,
            "columns": [
                {"data": "title"},
                {"data": "creatorId"},
                {"data": "status"},
                {"data": "priority"},
                {"data": "id"}
            ],
            "columnDefs": [{
                "render": function (data, type, row) {
                    var but = "";
                    if (tableType == 1) {
                        but = row['creatorName'];
                    } else {
                        but = row['principalName'];
                    }
                    return but;
                },
                "targets": 1
            },
                {
                    "render": function (data, type, row) {
                        var but = "";
                        var status = row['status'];
                        if (status == 0) {
                            but = '<span class="label label-sm label-danger">打开</span>'
                        } else if (status == 1) {
                            but = '<span class="label label-sm label-default">关闭</span>'
                        } else if (status == 3) {
                            but = '<span class="label label-sm label-success">已解决</span>'
                        } else if (status == 4) {
                            but = '<span class="label label-sm label-info">重新打开</span>'
                        } else {
                            but = '<span class="label label-sm label-info">验证中</span>'
                        }
                        return but;
                    },
                    "targets": 2
                }, {
                    "render": function (data, type, row) {
                        var but = "";
                        var priority = row['priority'];
                        if (priority == 1) {
                            but = '<span class="label label-sm label-danger">高</span>'
                        } else if (priority == 2) {
                            but = '<span class="label label-sm label-warning">中</span>'
                        } else {
                            but = '<span class="label label-sm label-warning">低</span>'
                        }
                        return but;
                    },
                    "targets": 3
                },
                {
                    "render": function (data, type, row) {
                        var but = "";
                        var id = row['id'];
                        but += '<button type="button" class="btn yellow btn-outline btn-xs detail-config" id="detail_' + id + '">查看详情</button>';
                        but += '<button type="button" class="btn dark btn-outline btn-xs edit-config" id="edit_' + id + '">修改</button>';
                        if (tableType == 2) {
                            but += '<button type="button" class="btn red btn-outline btn-xs delete-config" id="delete_' + id + '">删除</button>';
                        }
                        return but;
                    },
                    "targets": 4
                }
            ],
            "drawCallback": function (settings) {

                $(".detail-config").unbind("click");
                $(".detail-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    var t;
                    if (tableType = 1) {
                        t = 0;
                    } else {
                        t = 1;
                    }
                    window.open("/question/detail/" + id + "?type=" + t);
                });

                $(".edit-config").unbind("click");
                $(".edit-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.open("/question/edit/" + id);
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

    var initNeedVerifyList = function () {
        var table = $("#need_verify_list").DataTable({
            ordering: false,
            searching: true,
            "language": {
                "search": "查询:"
            },
            "scrollX": false,
            "stateSave": true,
            "processing": true,
            'serverSide': true,
            "ajax":'/question/needVerify/list',
            'pageLength': 20,
            lengthChange: false,
            "columns": [
                {"data": "title"},
                {"data": "creatorId"},
                {"data": "status"},
                {"data": "priority"},
                {"data": "id"}
            ],
            "columnDefs": [{
                "render": function (data, type, row) {
                    var but = "";
                    but = row['principalName'];
                    return but;
                },
                "targets": 1
            },
                {
                    "render": function (data, type, row) {
                        var but = "";
                        var status = row['status'];
                        if (status == 0) {
                            but = '<span class="label label-sm label-danger">打开</span>'
                        } else if (status == 1) {
                            but = '<span class="label label-sm label-default">关闭</span>'
                        } else if (status == 3) {
                            but = '<span class="label label-sm label-success">已解决</span>'
                        } else if (status == 4) {
                            but = '<span class="label label-sm label-info">重新打开</span>'
                        } else {
                            but = '<span class="label label-sm label-info">验证中</span>'
                        }
                        return but;
                    },
                    "targets": 2
                }, {
                    "render": function (data, type, row) {
                        var but = "";
                        var priority = row['priority'];
                        if (priority == 1) {
                            but = '<span class="label label-sm label-danger">高</span>'
                        } else if (priority == 2) {
                            but = '<span class="label label-sm label-warning">中</span>'
                        } else {
                            but = '<span class="label label-sm label-warning">低</span>'
                        }
                        return but;
                    },
                    "targets": 3
                },
                {
                    "render": function (data, type, row) {
                        var but = "";
                        var id = row['id'];
                        but += '<button type="button" class="btn yellow btn-outline btn-xs verify_detail-config" id="verifyDetail_' + id + '">查看详情</button>';
                        but += '<button type="button" class="btn dark btn-outline btn-xs verify_edit-config" id="verifyEdit_' + id + '">修改</button>';
                        return but;
                    },
                    "targets": 4
                }
            ],
            "drawCallback": function (settings) {

                $(".verify_detail-config").unbind("click");
                $(".verify_detail-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.open("/question/detail/" + id + "?type=1");
                });

                $(".verify_edit-config").unbind("click");
                $(".verify_edit-config").bind("click", function () {
                    var id = $(this).attr("id").split("_")[1];
                    window.open("/question/edit/" + id);
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
    Question.init();
});