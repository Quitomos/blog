package cn.quitomos.blog.service;

import cn.quitomos.blog.entity.Option;

public interface OptionService {

    /**
     * 获取全局选项配置
     *
     * @return Option
     */
    Option getOption();

    /**
     * 保存option的修改
     *
     * @param option option
     */
    void save(Option option);

    /**
     * 访问量+1
     */
    void addViewsByOne();
}
