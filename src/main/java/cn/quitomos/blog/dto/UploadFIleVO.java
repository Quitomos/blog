package cn.quitomos.blog.dto;

import lombok.Data;

/**
 * layuiJson中上传文件data数据格式
 */
@Data
public class UploadFIleVO {

    // 文件名
    private String title;

    //访问uri
    private String src;

    public UploadFIleVO() {

    }

    /**
     * 上传文件data构造器
     *
     * @param title 文件名
     * @param src 访问uri
     */
    public UploadFIleVO(String title, String src) {
        this.src = src;
        this.title = title;
    }
}
