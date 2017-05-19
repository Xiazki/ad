package com.ad.controller.massage;

import com.ad.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiang on 2017/5/18.
 */
@Controller
@RequestMapping(value = "message")
public class MassageController extends BaseController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toMessagePage() {
        return "messages/lists";
    }
}
