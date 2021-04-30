package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.controller.base.BaseUploadFileController;
import cn.quitomos.blog.dto.LayuiJson;
import cn.quitomos.blog.dto.UploadFIleVO;
import cn.quitomos.blog.entity.Page;
import cn.quitomos.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 后台其他页面设置
 */
@Controller
@RequestMapping("/admin/page")
public class BackPageController extends BaseUploadFileController {

    private PageService pageService;

    public BackPageController() {
        super("resource/assets/img/page/", ".bmp.jpg.jpeg.png.gif");
    }


    /**
     * 页面首页
     *
     * @param model 需给出pageList
     * @return index.jsp
     */
    @RequestMapping("")
    public String page(Model model) {
        List<Page> pageList = pageService.list();
        model.addAttribute("pageList", pageList);
        return "Admin/Page/index";
    }

    /**
     * 添加页面提交
     *
     * @param page page
     * @return /page
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(Page page) {
        pageService.savePage(page);
        return "redirect:/admin/page";
    }

    /**
     * 删除页面
     *
     * @param id pageId
     * @return /page
     */
    @RequestMapping("/delete")
    public String deletePage(@RequestParam("pageid") Integer id) {
        pageService.deletePage(id);
        return "redirect:/admin/page";
    }

    /**
     * 修改页面
     *
     * @param id pageId
     * @param model 需要提供原page, pageList
     * @return edit.jsp
     */
    @RequestMapping("/edit")
    public String editPage(@RequestParam("pageid") Integer id, Model model) {
        Page page = pageService.getPageById(id);
        model.addAttribute("page", page);
        List<Page> pageList = pageService.list();
        model.addAttribute("pageList", pageList);
        return "Admin/Page/edit";
    }

    /**
     * 提交修改
     *
     * @param page page
     * @return /page
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(Page page) {
        pageService.savePage(page);
        return "redirect:/admin/page";
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
        String name = super.save(file);

        if (name == null) {
            return new LayuiJson(LayuiJson.Result.FAIL, "不支持的文件类型", null, 0);
        }
        UploadFIleVO uploadFIleVO = new UploadFIleVO(name, null);
        return new LayuiJson(LayuiJson.Result.SUCCESS, "上传成功", uploadFIleVO, 1);
    }

    @Autowired
    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }
}
