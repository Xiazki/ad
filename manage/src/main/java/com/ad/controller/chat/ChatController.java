package com.ad.controller.chat;

import com.ad.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiang on 2017/5/18.
 */
@RequestMapping(value = "chat")
@Controller
public class ChatController extends BaseController{

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String toChatPage(Model model){
        return "chat/chats";
    }
}
