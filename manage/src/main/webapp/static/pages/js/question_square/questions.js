var Ques = function () {

    var init = function () {
        initList(0, 10, "");
    };

    var initList = function (start, length, searchInfo) {
        $.ajax({
            url: '/questionSquare/questions',
            data: {
                start: start,
                length: length,
                draw: 1,
                searchInfo: searchInfo
            },
            success: function (d) {
                $("#list").html(d);
            }
        })
    };

    return {
        //main function to initiate the module
        init: function () {
            init();
        }

    };

}();

jQuery(document).ready(function () {
    Ques.init();
})