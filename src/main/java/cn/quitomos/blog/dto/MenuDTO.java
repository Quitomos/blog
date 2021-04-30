package cn.quitomos.blog.dto;

import cn.quitomos.blog.entity.Menu;
import lombok.Data;
import lombok.ToString;

/**
 * 前端导航项传入模型
 */
@Data
public class MenuDTO {
    private Integer menuId;

    private Integer menuPid;

    private String menuName;

    private String menuUrl;

    private String menuIcon;

    private Integer menuOrder;
}
