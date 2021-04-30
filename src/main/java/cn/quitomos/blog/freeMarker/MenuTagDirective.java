package cn.quitomos.blog.freeMarker;

import cn.quitomos.blog.service.MenuService;
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
@Component("menuTagDirective")
public class MenuTagDirective implements TemplateDirectiveModel {

    private MenuService menuService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        final BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_31).build();

        if (map.containsKey("method")) {
            String method = map.get("method").toString();
            switch (method) {
                case "tree":
                    environment.setVariable("categoryList", beansWrapper.wrap(menuService.list()));
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
