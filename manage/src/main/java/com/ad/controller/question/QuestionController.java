package com.ad.controller.question;

import com.ad.biz.QuestionBiz;
import com.ad.common.ContextHolder;
import com.ad.common.ResponseResult;
import com.ad.common.RestResultGenerator;
import com.ad.controller.BaseController;
import com.ad.ds.question.QuestionService;
import com.ad.entity.project.Project;
import com.ad.entity.question.Question;
import com.ad.vo.question.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiang on 2017/5/10.
 */
@Controller
@RequestMapping(value = "question")
public class QuestionController extends BaseController {

    @Autowired
    private QuestionBiz questionBiz;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toQuestionPage(Model model) {
        return "question/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddPage(Model model) {
        return "question/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult add(QuestionVo questionVo) {
        Project project = ContextHolder.getCurrentProject();
        questionBiz.saveQuestion(questionVo, getCurrentUserId(), project.getId());
        return RestResultGenerator.genResult("成功");
    }
}
