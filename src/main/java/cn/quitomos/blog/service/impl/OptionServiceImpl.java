package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.entity.Option;
import cn.quitomos.blog.mapper.OptionMapper;
import cn.quitomos.blog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService {

    private OptionMapper optionMapper;

    @Override
    public Option getOption() {
        return optionMapper.getOption();
    }

    @Override
    public void save(Option option) {
        Option originalOption = optionMapper.getOption();
        option.setOptionId(originalOption.getOptionId());
        optionMapper.updateOption(option);
    }

    @Override
    public void addViewsByOne() {
        Option option = optionMapper.getOption();
        optionMapper.updateViews(option.getOptionId(), 1);
    }

    @Autowired
    public void setOptionMapper(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }
}
