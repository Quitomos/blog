package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.dto.MenuDTO;
import cn.quitomos.blog.entity.Menu;
import cn.quitomos.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 后台导航设置相关
 */
@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {
    private MenuService menuService;

    /**
     * 导航设置首页
     *
     * @param model 需给出menuList
     * @return index.jsp
     */
    @RequestMapping("")
    public String menu(Model model) {
        List<Menu> menuList = menuService.list();
        model.addAttribute("menuList", menuList);
        return "Admin/Menu/index";
    }

    /**
     * 添加导航项提交
     *
     * @param menuDTO menuDTO
     * @return /menu
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(MenuDTO menuDTO) {
        menuService.saveMenu(menuDTO);
        return "redirect:/admin/menu";
    }

    /**
     * 删除导航项
     *
     * @param id menuId
     * @return /menu
     */
    @RequestMapping("/delete")
    public String deleteMenu(@RequestParam("menuid") Integer id) {
        menuService.deleteMenu(id);
        return "redirect:/admin/menu";
    }

    /**
     * 修改导航项
     *
     * @param id menuId
     * @param model 需要提供原menu, menuList
     * @return edit.jsp
     */
    @RequestMapping("/edit")
    public String editMenu(@RequestParam("menuid") Integer id, Model model) {
        Menu menu = menuService.getMenuById(id);
        model.addAttribute("menu", menu);
        List<Menu> menuList = menuService.list();
        model.addAttribute("menuList", menuList);
        return "Admin/Menu/edit";
    }

    /**
     * 提交修改
     *
     * @param menuDTO menuDTO
     * @return /menu
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(MenuDTO menuDTO) {
        menuService.saveMenu(menuDTO);
        return "redirect:/admin/menu";
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
