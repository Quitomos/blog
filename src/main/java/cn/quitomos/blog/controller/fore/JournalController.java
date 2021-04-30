package cn.quitomos.blog.controller.fore;

import cn.quitomos.blog.adapter.JournalsAdapter;
import cn.quitomos.blog.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/journal")
public class JournalController {

    private JournalService journalService;

    /**
     * 日志页
     *
     * @param pageNum 页数
     * @param request 可以取到uri
     * @return journal.ftl, 需要给出journals
     */
    @RequestMapping(value = "/p/{page:\\d+}", method = RequestMethod.GET)
    public ModelAndView journal(@PathVariable("page") Integer pageNum, HttpServletRequest request)  {
        ModelAndView modelAndView = new ModelAndView("journals");
        String uri = request.getRequestURI();
        JournalsAdapter journals = journalService.getJournals(uri, pageNum);
        modelAndView.addObject("journals", journals);
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String journalFirstPage() {
        return "redirect:/journal/p/1";
    }

    @Autowired
    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }
}
