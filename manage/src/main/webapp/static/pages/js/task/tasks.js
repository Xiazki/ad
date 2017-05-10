var Task = function () {

    var init = function () {
        initTasksDetail();
    };

    var initTasksDetail = function () {
        $(".to-task-detail").bind('click', function () {
            var id = $(this).attr('id').split('_')[1];
            $.ajax({
                url: '/task/detail/' + id,
                type: 'get',
                dataType: 'json',
                success: function (d) {
                    if (d['success']) {
                        $('#task_detail').html(d['data'].detail);
                        $('#task_detail_title').text(d['data'].title);
                    }
                }
            });
        })

    };

    return {
        init: function () {
            init();
        }
    }
}();
jQuery(document).ready(function () {
    Task.init();
});