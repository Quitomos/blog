package cn.quitomos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class GlobalErrorController {

    @RequestMapping("")
    public ModelAndView errorHandler(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        if (statusCode != null && statusCode.equals(404)) {
            ModelAndView modelAndView = new ModelAndView("404");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("common/error/error");
        if (statusCode == null) {
            modelAndView.addObject("status", "未知异常");
            modelAndView.addObject("error", "未知异常");
            return modelAndView;
        }
        modelAndView.addObject("status", statusCode);
        modelAndView.addObject("error", throwable);
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
