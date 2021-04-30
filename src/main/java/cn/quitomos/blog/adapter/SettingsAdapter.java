package cn.quitomos.blog.adapter;

import cn.quitomos.blog.adapter.interf.Settings;
import cn.quitomos.blog.entity.Option;
import cn.quitomos.blog.util.MarkdownUtil;

import java.io.Serializable;

public class SettingsAdapter implements Settings, Serializable {

    private static final long serialVersionUID = 766262740290640449L;
    private final Option option;
    private final String contextPath;

    public SettingsAdapter(Option option, String contextPath) {
        this.option = option;
        this.contextPath = contextPath;
    }

    @Override
    public boolean isCdn() {
        return true;
    }

    @Override
    public String getPhotos_style() {
        return "masonry";
    }

    @Override
    public String getTop_back_mode() {
        return "lanyard";
    }

    @Override
    public String getFocus_tou() {
        return "glitch-text";
    }

    @Override
    public String getGlitch_text() {
        return option.getOptionMetaHello();
    }

    @Override
    public String getDescription() {
        return option.getOptionMetaDescription();
    }

    @Override
    public boolean isShownav() {
        return true;
    }

    @Override
    public boolean isIs_aplayer() {
        return true;
    }

    @Override
    public boolean isAplayer_float() {
        return true;
    }

    @Override
    public String getFeature_icon() {
        return "fa fa-anchor";
    }

    @Override
    public String getFeature_title() {
        return "poi~";
    }

    @Override
    public String getPost_list_style() {
        return "imageflow";
    }

    @Override
    public String getCategory_patternimg() {
        return contextPath + "/img/category_patternimg.jpg";
    }

    @Override
    public String getTag_patternimg() {
        return contextPath + "/img/tag_patternimg.jpg";
    }

    @Override
    public String getLinks_patternimg() {
        return contextPath + "/img/links_patternimg.jpg";
    }

    @Override
    public String getAbout_patternimg() {
        return contextPath + "/img/about_patternimg.jpg";
    }

    @Override
    public String getAbout_content() {
        return MarkdownUtil.renderHtml(option.getOptionAbout());
    }

    @Override
    public String getArchives_patternimg() {
        return contextPath + "/img/archives_patternimg.jpg";
    }

    @Override
    public String getJournals_patternimg() {
        return contextPath + "/img/journals_patternimg.jpg";
    }

    @Override
    public String getSearh_patternimg() {
        return contextPath + "/img/search_patternimg.jpg";
    }

    @Override
    public String getI18n() {
        return "zh";
    }

    @Override
    public boolean isMathjax() {
        return true;
    }

}
