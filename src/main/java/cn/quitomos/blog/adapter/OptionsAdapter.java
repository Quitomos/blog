package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Options;
import cn.quitomos.blog.entity.Option;

import java.io.Serializable;

public class OptionsAdapter implements Options, Serializable {

    private static final long serialVersionUID = 3770038603869715817L;
    private final Option option;

    private final String contextPath;

    public OptionsAdapter(Option option, String contextPath) {
        this.option = option;
        this.contextPath = contextPath;
    }

    @Override
    public String getBlog_favicon() {
        return contextPath + "/img/option/" + option.getOptionSiteIcon();
    }
}
