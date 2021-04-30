package cn.quitomos.blog.utils;

import cn.quitomos.blog.BaseTest;
import cn.quitomos.blog.service.OptionService;
import cn.quitomos.blog.util.MarkdownUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class mdTest extends BaseTest {

    private OptionService optionService;

    @Test
    public void test() {
        String aboutMe = optionService.getOption().getOptionAbout();
        System.out.println(aboutMe);
        System.out.println();
        System.out.println(MarkdownUtil.renderHtml(aboutMe));
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }
}
