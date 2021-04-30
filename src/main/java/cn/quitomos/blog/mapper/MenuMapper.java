package cn.quitomos.blog.mapper;

import cn.quitomos.blog.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuMapper {

    /**
     * 导航项列表
     *
     * @param params 参数
     * @return menuList
     */
    List<Menu> list(Map<String, Object> params);

    /**
     * 子导航列表
     *
     * @param id 父导航id
     * @return childList
     */
    List<Menu> listChild(int id);

    /**
     * 通过menuId获得导航项
     *
     * @param id menuId
     * @return menu
     */
    Menu getMenuById(int id);

    /**
     * 添加导航项
     *
     * @param menu 添加的导航项
     */
    void insertMenu(Menu menu);

    /**
     * 根据menuId修改导航项
     *
     * @param menu 修改的导航项
     */
    void updateMenu(Menu menu);

    /**
     * 根据menuId删除导航项
     *
     * @param id menuId;
     */
    void deleteMenuById(@Param("menu_id") int id);
}
