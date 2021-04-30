package cn.quitomos.blog.interceptor;


import cn.quitomos.blog.entity.Option;
import cn.quitomos.blog.entity.User;
import cn.quitomos.blog.enums.UserRole;
import cn.quitomos.blog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台拦截器
 */
@Component("adminInterceptor")
public class AdminInterceptor implements HandlerInterceptor {

    private OptionService optionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 只有管理员才能访问
        User user = (User) request.getSession().getAttribute("backUser");
        if (user != null && user.getUserRole() == UserRole.ADMIN)
            return true;
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();

        // 在session中加入全局属性配置
        Option option = (Option) session.getAttribute("option");
        if (option == null) {
            option = optionService.getOption();
            session.setAttribute("option", option);
        }
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }
}
