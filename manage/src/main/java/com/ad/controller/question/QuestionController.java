package com.ad.controller.question;

import com.ad.biz.QuestionBiz;
import com.ad.common.ContextHolder;
import com.ad.common.PageBean;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "list")
    @ResponseBody
    public PageBean<QuestionVo> questionNeedSolveList(@RequestParam(name = "start") Integer start,
                                                      @RequestParam(name = "length") Integer length,
                                                      @RequestParam(name = "draw") Integer draw,
                                                      @RequestParam(name = "type") Integer type,
                                                      @RequestParam(name = "search[value]") String searchInfo) {
        Project project = ContextHolder.getCurrentProject();
        PageBean<QuestionVo> pageBean = new PageBean<>();
        //
        List<QuestionVo> vos = questionBiz.listNeedSolveQuestion(getCurrentUserId(), project.getId(), type, start, length, searchInfo);
        Integer count = questionBiz.countNeedSolveQuestion(getCurrentUserId(), project.getId(), type, start, length, searchInfo);
        pageBean.setRecordsTotal(count);
        pageBean.setRecordsFiltered(count);
        pageBean.setDraw(draw);
        pageBean.setData(vos);
        return pageBean;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddPage(Model model) {
        return "question/add";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String toEditPage(@PathVariable(value = "id") Long id, Model model) {
        QuestionVo vo = questionBiz.getById(id);
        model.addAttribute("vo", vo);
        model.addAttribute("isEdit", true);
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
