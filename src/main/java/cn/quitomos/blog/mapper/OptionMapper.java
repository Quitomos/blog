package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Option;
import org.apache.ibatis.annotations.Param;

public interface OptionMapper {

    /**
     * 查询全局属性配置
     *
     * @return Option
     */
    Option getOption();

    /**
     * 根据id保存全局属性配置
     *
     * @param option  option
     */
    void updateOption(Option option);

    /**
     * 增加访问数
     *
     * @param id optionId
     * @param add 增加值
     */
    void updateViews(@Param("id") int id, @Param("add") int add);
}
