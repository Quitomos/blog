package cn.quitomos.blog.freeMarker;

import cn.quitomos.blog.service.TagService;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * freeMarker自定义宏
 *
 * https://github.com/halo-dev/halo
 */
@Component("tagTagDirective")
public class TagTagDirective implements TemplateDirectiveModel {

    private TagService tagService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        final BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_31).build();

        if (map.containsKey("method")) {
            String method = map.get("method").toString();
            switch (method) {
                case "list":
                    String contextPath = map.get("contextPath").toString();
                    environment.setVariable("tags", beansWrapper.wrap(tagService.listFore(contextPath)));
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
}
