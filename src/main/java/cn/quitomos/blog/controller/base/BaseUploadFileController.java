package cn.quitomos.blog.controller.base;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class BaseUploadFileController {

    /**
     * web根路径
     */
    public final String rootPath;

    /**
     * 文件存放位置相对contextPath的相对路径
     */
    public final String filePath;

    /**
     * 文件允许后缀集合
     */
    public final String allowSuffix;

    /**
     * 基类初始化
     *
     * @param filePath 文件存放位置相对于contextPath的相对路径(不以'/'开头, 以'/'结尾)
     * @param allowSuffix 允许上传文件的后缀集合
     */
    public BaseUploadFileController(String filePath, String allowSuffix) {
        String resourcePath = this.getClass().getClassLoader().getResource("").getPath();
        this.rootPath = resourcePath.substring(0, resourcePath.indexOf("WEB-INF"));
        this.filePath = filePath;
        this.allowSuffix = allowSuffix;

        // 创建目录
        File dir = new File(rootPath + filePath);
        if (!dir.exists())
            dir.mkdirs();
    }

    /**
     * 将文件存放在本地
     *
     * @param file 需要存放的文件
     * @return 成功返回新文件名,失败返回null
     */
    public String save(MultipartFile file) {

        // 1.文件后缀过滤
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        if (!this.allowSuffix.contains(suffix))
            return null;

        // 2.利用UUID生成文件名
        String newFilename = UUID.randomUUID().toString() + suffix;

        // 3.存储文件
        String path = rootPath + filePath + newFilename;
        File newFile = new File(path);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传失败, cause:{}", e);
        }
        return newFilename;
    }
}
