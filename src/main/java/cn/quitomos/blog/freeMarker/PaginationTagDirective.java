package cn.quitomos.blog.freeMarker;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * freeMarker自定义宏
 *
 * 分页功能
 *
 * https://github.com/halo-dev/halo
 */
@Component("paginationTagDirective")
public class PaginationTagDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        final BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_31).build();

        if (map.containsKey("method")) {
            String method = map.get("method").toString();
            switch (method) {
                case "categoryPosts":
                case "tagPosts":
                case "search":
                    environment.setVariable("pagination", environment.getVariable("posts"));
                    break;
                case "journals":
                    environment.setVariable("pagination", environment.getVariable("journals"));
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }
}
