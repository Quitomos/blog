package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.controller.base.BaseUploadFileController;
import cn.quitomos.blog.dto.LayuiJson;
import cn.quitomos.blog.dto.UploadFIleVO;
import cn.quitomos.blog.entity.Notice;
import cn.quitomos.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 后台公告相关
 */
@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController extends BaseUploadFileController {

    private NoticeService noticeService;

    public BackNoticeController() {
        super("resource/assets/img/notice/", ".bmp.jpg.jpeg.png.gif");
    }

    /**
     * 添加公告页
     *
     * @return insert.jsp
     */
    @RequestMapping("/insert")
    public String insert() {
        return ("Admin/Notice/insert");
    }

    /**
     * 公告内容图片上传
     *
     * @param file 上传的文件
     * @param request request
     * @return json:["code": 0/1(成功/失败), "msg": 提示信息, "data.src": 文件访问uri]
     */
    @RequestMapping("/contentimg")
    @ResponseBody
    public LayuiJson uploadContentImg(MultipartFile file, HttpServletRequest request) {
        String name = super.save(file);
        String uri = request.getContextPath() + "/img/notice/" + name;

        if (name == null) {
            return new LayuiJson(LayuiJson.Result.FAIL, "上传失败", null, 0);
        }
        UploadFIleVO uploadFIleVO = new UploadFIleVO(name, uri);
        return new LayuiJson<>(LayuiJson.Result.SUCCESS, "上传成功", uploadFIleVO, 1);
    }

    /**
     * 添加公告提交
     *
     * @param notice notice
     * @return /list
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmint(Notice notice) {
        noticeService.saveNotice(notice);
        return "redirect:/admin/notice/list";
    }

    /**
     * 公告列表
     *
     * @param model 需放入属性noticeList
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<Notice> noticeList = noticeService.listNotice();
        model.addAttribute("noticeList", noticeList);
        return "Admin/Notice/index";
    }

    /**
     * 删除公告
     *
     * @param id 公告id
     */
    @RequestMapping("/delete")
    public void delete(@RequestParam("noticeid") Integer id) {
        noticeService.deleteNotice(id);
    }

    /**
     * 修改公告页
     *
     * @param id 公告id
     * @param model 需要给出notice
     * @return edit.jsp
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam("noticeid") Integer id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "Admin/Notice/edit";
    }

    /**
     * 公告修改提交
     *
     * @param notice notice
     * @return /list
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(Notice notice) {
        noticeService.saveNotice(notice);
        return "redirect:/admin/notice/list";
    }

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
}
