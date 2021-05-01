package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.controller.base.BaseUploadFileController;
import cn.quitomos.blog.dto.LayuiJson;
import cn.quitomos.blog.dto.UploadFIleVO;
import cn.quitomos.blog.entity.Journal;
import cn.quitomos.blog.service.JournalService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/journal")
public class BackJournalController extends BaseUploadFileController {

    private JournalService journalService;

    public BackJournalController() {
        super("resource/assets/img/journal/", ".bmp.jpg.jpeg.png.gif");
    }

    /**
     * 日志内容图片上传
     *
     * @param file 上传的文件
     * @param request request
     * @return json:["code": 0/1(成功/失败), "msg": 提示信息, "data.src": 文件访问uri]
     */
    @RequestMapping("/contentimg")
    @ResponseBody
    public LayuiJson uploadContentImg(MultipartFile file, HttpServletRequest request) {
        String uri = super.save(file);

        if (uri == null) {
            return new LayuiJson(LayuiJson.Result.FAIL, "上传失败", null, 0);
        }
        UploadFIleVO uploadFIleVO = new UploadFIleVO(null, uri);
        return new LayuiJson<>(LayuiJson.Result.SUCCESS, "上传成功", uploadFIleVO, 1);
    }

    /**
     * 新日志提交
     *
     * @param journal 日志
     * @return /
     */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(Journal journal) {
        journalService.saveJournal(journal);
        return "redirect:/admin/journal";
    }

    /**
     * 日志列表
     *
     * @param model 需放入属性: {pageUrlPrefix: 为页码添加超链接 href="${pageUrlPrefix}=pageNum"
     *                          pageInfo: pageHelper返回值
     * @param request 可能含属性: pageNum: 页码  pageSize: 页大小
     * @return index.jsp
     */
    @RequestMapping("")
    public String list(Model model, HttpServletRequest request) {
        String pageNumStr = request.getParameter("pageNum");
        Integer pageNum = 1;
        if (pageNumStr != null)
            pageNum = Integer.parseInt(pageNumStr);
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = 10;
        if (pageSizeStr != null)
            pageSize = Integer.parseInt(pageSizeStr);
        PageInfo<Journal> pageInfo = journalService.listJournalPaged(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageUrlPrefix", request.getContextPath() + "/admin/journal?pageNum");
        return "Admin/Journal/index";
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam("journalid") Integer id) {
        journalService.deleteJournal(id);
    }

    @Autowired
    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }
}
