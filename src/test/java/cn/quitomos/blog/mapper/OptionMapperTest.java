package cn.quitomos.blog.mapper;


import cn.quitomos.blog.BaseTest;
import cn.quitomos.blog.entity.Option;
import cn.quitomos.blog.service.OptionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OptionMapperTest extends BaseTest {

    @Autowired
    private OptionMapper optionMapper;

    @Autowired
    private OptionService optionService;

    @Test
    public void getOption() {
//        Option option = optionMapper.getOption();
//        Option option = optionService.getOption();
//        System.out.println(option);
    }
}
