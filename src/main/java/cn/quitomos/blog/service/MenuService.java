package cn.quitomos.blog.service;

import cn.quitomos.blog.dto.MenuDTO;
import cn.quitomos.blog.entity.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 导航项列表
     *
     * @return menuList
     */
    List<Menu> list();

    /**
     * 一级导航列表
     *
     * @return 适配前台后的menus
     */
    List<cn.quitomos.blog.adapter.interf.Menu> listRoot();

    /**
     * 添加/修改导航项
     *
     * @param menuDTO menuDTO
     */
    void saveMenu(MenuDTO menuDTO);

    /**
     * 删除导航项
     *
     * @param menuId 导航项Id
     */
    void deleteMenu(Integer menuId);

    /**
     * 根据导航项id获取导航项
     *
     * @param menuId menuId
     * @return menu
     */
    Menu getMenuById(Integer menuId);
}
