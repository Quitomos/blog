package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.controller.base.BaseUploadFileController;
import cn.quitomos.blog.dto.LayuiJson;
import cn.quitomos.blog.dto.UploadFIleVO;
import cn.quitomos.blog.entity.Links;
import cn.quitomos.blog.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 后台友链相关
 */
@Controller
@RequestMapping("/admin/links")
public class BackLinksController extends BaseUploadFileController {

    private LinksService linksService;

    public BackLinksController() {
        super("resource/assets/img/links/", ".bmp.jpg.jpeg.png.gif");
    }

    /**
     * 页面首页
     *
     * @param model 需给出linksList
     * @return index.jsp
     */
    @RequestMapping("")
    public String links(Model model) {
        List<Links> linksList = linksService.list();
        model.addAttribute("linksList", linksList);
        return "Admin/Links/index";
    }

    /**
     * 添加页面提交
     *
     * @param links links
     * @return /links
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(Links links) {
        linksService.saveLinks(links);
        return "redirect:/admin/links";
    }

    /**
     * 删除页面
     *
     * @param id linksId
     * @return /links
     */
    @RequestMapping("/delete")
    public String deleteLinks(@RequestParam("linksid") Integer id) {
        linksService.deleteLinks(id);
        return "redirect:/admin/links";
    }

    /**
     * 修改页面
     *
     * @param id linksId
     * @param model 需要提供原links, linksList
     * @return edit.jsp
     */
    @RequestMapping("/edit")
    public String editLinks(@RequestParam("linksid") Integer id, Model model) {
        Links links = linksService.getLinksById(id);
        model.addAttribute("links", links);
        List<Links> linksList = linksService.list();
        model.addAttribute("linksList", linksList);
        return "Admin/Links/edit";
    }

    /**
     * 提交修改
     *
     * @param links links
     * @return /links
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(Links links) {
        linksService.saveLinks(links);
        return "redirect:/admin/links";
    }

    /**
     * 上传页面图片
     *
     * @param file 图片
     * @return json: ["code": 0/1(上传成功/失败), "data.title": 文件名, "msg": 提示信息]
     */
    @RequestMapping("/image")
    @ResponseBody
    public LayuiJson uploadTitleImg(MultipartFile file) {
        String uri = super.save(file);

        if (uri == null) {
            return new LayuiJson(LayuiJson.Result.FAIL, "不支持的文件类型", null, 0);
        }
        UploadFIleVO uploadFIleVO = new UploadFIleVO(null, uri);
        return new LayuiJson(LayuiJson.Result.SUCCESS, "上传成功", uploadFIleVO, 1);
    }

    @Autowired
    public void setLinksService(LinksService linksService) {
        this.linksService = linksService;
    }
}
