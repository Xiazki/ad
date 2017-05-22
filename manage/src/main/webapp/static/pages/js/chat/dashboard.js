var Dashboard = function () {

    var uid = $("#uid").val();
    var socket = new SockJS('/chat?uid=' + uid);
    socket.onopen = function (event) {
        console.info("connected!");
    };
    socket.onmessage = function (event) {
        addchats(event.data, 1)
    };
    socket.onerror = function (event) {
        console.info("error!")
    };
    socket.onclose = function (event) {
        console.info("closed!");
    }


    var sendMsg = function (text) {
        socket.send(text);
    };

    var addchats = function (text, type) {
        var cont = $('#chats');
        var list = $('.chats', cont);
        var form = $('.chat-form', cont);
        var input = $('input', form);
        var btn = $('.btn', form);

        if (text.length == 0) {
            return;
        }

        var time = new Date();
        var time_str = (time.getHours() + ':' + time.getMinutes());
        var tpl = '';
        //临时测试，后期根据data 初始化对话框
        if (type == 1) {
            tpl += '<li class="in">';
            tpl += '<img class="avatar" alt="" src="/static/metronic/img/avatars/team1.jpg"/>';
        } else {
            tpl += '<li class="out">';
            tpl += '<img class="avatar" alt="" src="/static/metronic/img/avatars/team3.jpg"/>';
        }
        tpl += '<div class="message">';
        tpl += '<span class="arrow"></span>';
        tpl += '<a href="#" class="name">test</a>&nbsp;';
        tpl += '<span class="datetime">at ' + time_str + '</span>';
        tpl += '<span class="body">';
        tpl += text;
        tpl += '</span>';
        tpl += '</div>';
        tpl += '</li>';

        var msg = list.append(tpl);
        input.val("");

        var getLastPostPos = function () {
            var height = 0;
            cont.find("li.out, li.in").each(function () {
                height = height + $(this).outerHeight();
            });

            return height;
        };

        cont.find('.scroller').slimScroll({
            scrollTo: getLastPostPos()
        });

    };


    return {

        initChat: function () {

            var cont = $('#chats');
            var list = $('.chats', cont);
            var form = $('.chat-form', cont);
            var input = $('input', form);
            var btn = $('.btn', form);

            var handleClick = function (e) {
                e.preventDefault();

                var text = input.val();
                if (text.length == 0) {
                    return;
                }

                sendMsg(text);
                addchats(text, 0);

            };

            $('body').on('click', '.message .name', function (e) {
                e.preventDefault(); // prevent click event

                var name = $(this).text(); // get clicked user's full name
                input.val('@' + name + ':'); // set it into the input field
                App.scrollTo(input); // scroll to input if needed
            });

            btn.click(handleClick);

            input.keypress(function (e) {
                if (e.which == 13) {
                    handleClick(e);
                    return false; //<---- Add this line
                }
            });
        },

        init: function () {


            this.initChat();

        }
    };


}();

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function () {
        Dashboard.init(); // init metronic core componets
    });
}