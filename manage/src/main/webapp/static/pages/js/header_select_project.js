var SelectProject = function () {

    var init = function () {
        $(".selectProject-header").bind('click', function () {
            var id = $(this).attr("id").split("_")[1];
            $.ajax({
                url: '/project/select',
                type: 'POST',
                data: {
                    id: id
                },
                dataType: 'JSON',
                success: function (d) {
                    if (d['success']) {
                        window.location.reload();
                    } else {
                        $.notify(d['message'], {
                            type: 'danger', animate: {
                                enter: 'animated fadeInDown',
                                exit: 'animated fadeOutUp'
                            }
                        });
                    }
                }
            })
        })
    };


    return {
        init: function () {
            init();
        }
    }
}();
jQuery(document).ready(function () {
    SelectProject.init();
});