package com.ad.controller.question_square;

import com.ad.common.ContextHolder;
import com.ad.common.PageBean;
import com.ad.controller.BaseController;
import com.ad.ds.constant.QuestionType;
import com.ad.ds.question.QuestionService;
import com.ad.entity.project.Project;
import com.ad.entity.question.Question;
import com.ad.vo.question.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/5/8.
 */
@Controller
@RequestMapping(value = "questionSquare")
public class QuestionSquareController extends BaseController {


    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toSquare() {
        return "question_square/questions";
    }

    /**
     * 返回问题广场问题列表
     *
     * @param start
     * @param length
     * @param draw
     * @return
     */
    @RequestMapping(value = "questions")
    public String questionPage(Model model, @RequestParam(name = "start") Integer start,
                               @RequestParam(name = "length") Integer length,
                               @RequestParam(name = "draw") Integer draw,
                               @RequestParam(name = "searchInfo") String searchInfo) {
        Project project = ContextHolder.getCurrentProject();
        List<Question> questions = questionService.listByType(project.getId(), QuestionType.PUBLIC.getType(), start, length, searchInfo);
        List<QuestionVo> vos = questions.stream().map(QuestionVo::from).collect(Collectors.toList());
        PageBean<QuestionVo> pageBean = new PageBean<>();
        Integer count = questionService.countByType(project.getId(), QuestionType.PUBLIC.getType(), searchInfo);
        pageBean.setData(vos);
        pageBean.setRecordsTotal(count / length + 1);
        pageBean.setRecordsFiltered(count);
        pageBean.setData(vos);
        pageBean.setDraw(draw);
        model.addAttribute("page", pageBean);
        return "question_square/list";
    }

    @RequestMapping(value = "questions/{id}")
    public String toDetail(Model model, @PathVariable(value = "id") Long id) {
        Question question = questionService.getById(id);
        model.addAttribute("vo", question);
        return "question_square/deatail";
    }

}
