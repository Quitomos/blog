package cn.quitomos.blog.controller.fore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/links")
public class LinksController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String links() {
        return "links";
    }
}
