package com.ad.controller.question_square;

import com.ad.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiang on 2017/5/8.
 */
@Controller
@RequestMapping(value = "questionSquare")
public class QuestionSquareController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toSquare() {
        return "question_square/questions";
    }
}
