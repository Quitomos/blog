package cn.quitomos.blog.controller.admin;

import cn.quitomos.blog.controller.base.BaseUploadFileController;
import cn.quitomos.blog.dto.LayuiJson;
import cn.quitomos.blog.dto.UploadFIleVO;
import cn.quitomos.blog.entity.Option;
import cn.quitomos.blog.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台站点信息相关
 */
@Controller
@RequestMapping("/admin/option")
public class BackOptionController extends BaseUploadFileController {

    private OptionService optionService;

    public BackOptionController() {
        super("resource/assets/img/option/", ".bmp.jpg.jpeg.png.gif");
    }

    /**
     * 站点信息显示
     *
     * @param model 需给出option
     * @return index.jsp
     */
    @RequestMapping("")
    public String index(Model model) {
        Option option = optionService.getOption();
        model.addAttribute("option", option);
        return "Admin/Option/index";
    }

    /**
     * 修改站点信息提交
     *
     * @param option option
     * @return /option
     */
    @RequestMapping("/editSubmit")
    public String editSubmit(Option option) {
        optionService.save(option);
        return "redirect:/admin/option";
    }

    /**
     * 站点图标上传
     *
     * @param request
     * @param file 战点图标文件
     * @return LayuiJson
     */
    @RequestMapping("/icon")
    @ResponseBody
    public LayuiJson<UploadFIleVO> uploadIcon(MultipartFile file, HttpServletRequest request) {
        String uri = super.save(file);

        if (uri == null) {
            return new LayuiJson<>(LayuiJson.Result.FAIL, "不支持的文件类型", null, 0);
        }
        UploadFIleVO uploadFIleVO = new UploadFIleVO(null, uri);
        return new LayuiJson<>(LayuiJson.Result.SUCCESS, "上传成功", uploadFIleVO, 1);
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }
}
