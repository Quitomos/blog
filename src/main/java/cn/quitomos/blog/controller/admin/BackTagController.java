package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.entity.Tag;
import cn.quitomos.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 后台文章标签相关
 */
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {

    private TagService tagService;

    /**
     * 文章标签首页
     *
     * @param model 需给出tagList
     * @return index.jsp
     */
    @RequestMapping("")
    public String tag(Model model) {
        List<Tag> tagList = tagService.list();
        model.addAttribute("tagList", tagList);
        return "Admin/Tag/index";
    }

    /**
     * 添加标签提交
     *
     * @param tag tag
     * @return /tag
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(Tag tag) {
        tagService.saveTag(tag);
        return "redirect:/admin/tag";
    }

    /**
     * 删除标签
     *
     * @param id tagId
     * @return /tag
     */
    @RequestMapping("/delete")
    public String deleteTag(@RequestParam("tagid") Integer id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tag";
    }

    /**
     * 修改标签
     *
     * @param id tagId
     * @param model 需要提供原tag, tagList
     * @return edit.jsp
     */
    @RequestMapping("/edit")
    public String editTag(@RequestParam("tagid") Integer id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        List<Tag> tagList = tagService.list();
        model.addAttribute("tagList", tagList);
        return "Admin/Tag/edit";
    }

    /**
     * 提交修改
     *
     * @param tag tag
     * @return /tag
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(Tag tag) {
        tagService.saveTag(tag);
        return "redirect:/admin/tag";
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
}
