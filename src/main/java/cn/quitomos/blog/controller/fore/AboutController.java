package cn.quitomos.blog.controller.fore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {

    /**
     * 关于页
     *
     * @return about.ftl
     */
    @RequestMapping("")
    public String about() {
        return "about";
    }
}
