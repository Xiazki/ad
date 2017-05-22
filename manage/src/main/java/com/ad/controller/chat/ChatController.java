package com.ad.controller.chat;

import com.ad.bean.chat.ChatMessage;
import com.ad.controller.BaseController;
import com.ad.reids.ChatCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by xiang on 2017/5/18.
 */
@RequestMapping(value = "chat")
@Controller
public class ChatController extends BaseController {

    @Autowired
    private ChatCache chatCache;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toChatPage(Model model) {
        List<ChatMessage> chats = chatCache.getChats();
        model.addAttribute("chats", chats);
        //temp
        model.addAttribute("uid", getCurrentUserId());
        return "chat/chats";
    }

//    @MessageMapping("/send")
//    @SendTo("/chatroom/messages")
//    public String transferMessage(String value){
//        //存入缓存或数据库.
//        return value;
//    }

}
