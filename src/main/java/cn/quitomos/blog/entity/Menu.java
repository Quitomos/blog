package cn.quitomos.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 6187390085913578636L;

    private Integer menuId;

    @ToString.Exclude
    private Menu parentMenu;

    private String menuName;

    private String menuUrl;

    private String menuIcon;

    private Integer menuOrder;

    @ToString.Exclude
    private List<Menu> childList;
}
