package cn.quitomos.blog.interceptor;

import cn.quitomos.blog.adapter.OptionsAdapter;
import cn.quitomos.blog.adapter.SettingsAdapter;
import cn.quitomos.blog.adapter.interf.*;
import cn.quitomos.blog.entity.Option;
import cn.quitomos.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 前台拦截器
 */
@Component("foreInterceptor")
public class ForeInterceptor implements HandlerInterceptor {

    private OptionService optionService;
    private UserService userService;
    private MenuService menuService;
    private TagService tagService;
    private CategoryService categoryService;
    private AccountService accountService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        Option option = optionService.getOption();
        session.setAttribute("option", option);
        // 放行后台
        String servletPath = request.getServletPath();
        if (servletPath.startsWith("/admin"))
            return;

        // 全局属性配置
        String themeBase = (String) session.getAttribute("theme_base");
        String i18n = (String) session.getAttribute("i18n");
        String title = (String) session.getAttribute("title");
        String blogTitle = (String) session.getAttribute("blog_title");
        String blogUrl = (String) session.getAttribute("blog_url");
        String blogLogo = (String) session.getAttribute("blog_logo");
        String metaDescription = (String) session.getAttribute("meta_description");
        Settings settings = (Settings) session.getAttribute("settings");
        Options options = (Options) session.getAttribute("options");
        List<Menu> menus = (List<Menu>) session.getAttribute("menus");
        List<Account> accounts = (List<Account>) session.getAttribute("accounts");

        String contextPath = request.getContextPath();

        if (themeBase == null) {
            themeBase = contextPath;
            session.setAttribute("theme_base", themeBase);
        }
        if (i18n == null) {
            i18n = "zh";
            session.setAttribute("i18n", i18n);
        }
        if (title == null) {
            title = option.getOptionSiteTitle();
            session.setAttribute("title", title);
        }
        if (blogTitle == null) {
            blogTitle = option.getOptionSiteTitle();
            session.setAttribute("blog_title", blogTitle);
        }
        if (blogUrl == null || blogUrl.equals("")) {
            blogUrl = option.getOptionSiteUrl();
            session.setAttribute("blog_url", blogUrl);
        }
        if (blogLogo == null) {
            blogLogo = option.getOptionSiteIcon();
            session.setAttribute("blog_logo", blogLogo);
        }
        if (metaDescription == null) {
            metaDescription =option.getOptionMetaDescription();
            session.setAttribute("meta_description", metaDescription);
        }
        if (settings == null) {
            settings = new SettingsAdapter(option, contextPath);
            session.setAttribute("settings", settings);
        }
        if (options == null) {
            options = new OptionsAdapter(option, contextPath);
            session.setAttribute("options", options);
        }
        if (menus == null) {
            menus = menuService.listRoot();
            session.setAttribute("menus", menus);
        }
        if (accounts == null || accounts.isEmpty()) {
            accounts = accountService.listFore(contextPath);
            session.setAttribute("accounts", accounts);
        }

        // 模型属性添加
        if (modelAndView == null) return;
        Map<String, Object> model = modelAndView.getModel();
        User user = (User) model.get("user");
        List<Tag> tags = (List<Tag>) model.get("tags");
        List<Category> categories = (List<Category>) model.get("categories");

        if (user == null) {
            user = userService.getForeUserById(option.getOptionHostId(), contextPath);
            modelAndView.addObject("user", user);
        }
        if (tags == null) {
            tags = tagService.listFore(contextPath);
            modelAndView.addObject("tags", tags);
        }
        if (categories == null) {
            categories = categoryService.listRoot(contextPath);
            modelAndView.addObject("categories", categories);
        }
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
