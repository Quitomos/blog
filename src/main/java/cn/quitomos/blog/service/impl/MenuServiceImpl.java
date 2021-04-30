package cn.quitomos.blog.service.impl;

import cn.quitomos.blog.adapter.MenuAdapter;
import cn.quitomos.blog.dto.MenuDTO;
import cn.quitomos.blog.entity.Menu;
import cn.quitomos.blog.mapper.MenuMapper;
import cn.quitomos.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuMapper menuMapper;

    @Override
    public List<Menu> list() {
        return menuMapper.list(null);
    }

    @Override
    public List<cn.quitomos.blog.adapter.interf.Menu> listRoot() {
        Map<String, Object> params = new HashMap<>();
        params.put("menuPid", 0);
        List<Menu> menuEntityList = menuMapper.list(params);
        List<cn.quitomos.blog.adapter.interf.Menu> ret = new ArrayList<>();
        for (Menu m: menuEntityList) {
            ret.add(new MenuAdapter(m));
        }
        return ret;
    }

    @Override
    public void saveMenu(MenuDTO menuDTO) {
        Menu menu = new Menu();
        menu.setMenuIcon(menuDTO.getMenuIcon());
        menu.setMenuName(menuDTO.getMenuName());
        menu.setMenuOrder(menuDTO.getMenuOrder());
        menu.setMenuUrl(menuDTO.getMenuUrl());
        menu.setParentMenu(menuMapper.getMenuById(menuDTO.getMenuPid()));
        Integer menuId = menuDTO.getMenuId();
        if (menuId == null) {
            menuMapper.insertMenu(menu);
        } else {
            menu.setMenuId(menuId);
            menuMapper.updateMenu(menu);
        }
    }

    @Override
    public void deleteMenu(Integer menuId) {
        menuMapper.deleteMenuById(menuId);
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        return menuMapper.getMenuById(menuId);
    }

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
}
