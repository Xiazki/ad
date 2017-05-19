package com.ad.interceptor;

import com.ad.common.ContextHolder;
import com.ad.entity.project.Project;
import com.ad.reids.ProjectCache;
import com.ad.vo.PrincipalVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiang on 2017/5/8.
 */
public class SelectProjectInterceptor implements HandlerInterceptor {

    @Autowired
    private ProjectCache projectCache;

    private List<String> excludedUrls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUri = request.getRequestURI();
        if (requestUri.contains("/static/")) {
            return true;
        }
        for (String url : excludedUrls) {
            //过滤掉请求参数
//            String verifyUrl = requestUri.split("\\?")[0];
//            if (verifyUrl.contains(";")) {
//                verifyUrl = verifyUrl.split(";")[0];
//            }
            if (requestUri.contains(url)) {
                return true;
            }
        }

        PrincipalVo principalVo = (PrincipalVo) SecurityUtils.getSubject().getPrincipal();
        Project selectProject = projectCache.getSelectProject(principalVo.getCurrentUserId());
        if (selectProject == null) {
            response.sendRedirect("/project/selectProject");
            return false;
        }
        //不存session
        ContextHolder.setCurrentProject(selectProject);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }
}
