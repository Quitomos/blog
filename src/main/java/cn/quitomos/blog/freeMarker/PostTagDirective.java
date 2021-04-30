package cn.quitomos.blog.freeMarker;

import cn.quitomos.blog.service.ArticleService;
import cn.quitomos.blog.vo.Archive;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * freeMarker自定义宏
 *
 * https://github.com/halo-dev/halo
 */
@Component("postTagDirective")
public class PostTagDirective implements TemplateDirectiveModel {

    private ArticleService articleService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        final BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_31).build();

        if (map.containsKey("method")) {
            String method = map.get("method").toString();
            String contextPath = map.get("contextPath").toString();
            switch (method) {
                case "latest":
                    Integer top = Integer.parseInt(map.get("top").toString());
                    environment.setVariable("posts", beansWrapper.wrap(articleService.getRecentPosts(top, contextPath)));
                    break;
                case "archiveMonth":
                    environment.setVariable("archives", beansWrapper.wrap(articleService.getArchives(contextPath)));
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
