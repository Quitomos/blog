package cn.quitomos.blog.freeMarker;

import cn.quitomos.blog.adapter.interf.Team;
import cn.quitomos.blog.service.LinksService;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("linkTagDirective")
public class LinkTagDirective implements TemplateDirectiveModel {

    private LinksService linksService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        final BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_31).build();

        if (map.containsKey("method")) {
            String method = map.get("method").toString();
            String contextPath = map.get("contextPath").toString();
            switch (method) {
                case "listTeams":
                    Team team = linksService.listFore(contextPath);
                    List<Team> teams = new ArrayList<>();
                    teams.add(team);
                    environment.setVariable("teams", beansWrapper.wrap(teams));
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }

    @Autowired
    public void setLinksService(LinksService linksService) {
        this.linksService = linksService;
    }
}
