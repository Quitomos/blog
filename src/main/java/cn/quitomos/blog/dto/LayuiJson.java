package cn.quitomos.blog.dto;

import lombok.Data;

/**
 * layui的json数据格式
 */
@Data
public class LayuiJson <T> {

    public enum Result {
        FAIL,
        SUCCESS;
    }


    // 接口状态
    private Integer code;

    // 提示文本
    private String msg;

    // 数据
    private T data;

    // 数据个数
    private Integer count;

    /**
     * layuiJson构造器
     *
     * @param result 枚举类,成功/失败
     * @param msg 提示文本
     * @param data 数据主体
     * @param count 数据个数
     */
    public LayuiJson(LayuiJson.Result result, String msg, T data, Integer count) {
        this.code = result.equals(Result.SUCCESS)? 0: 1;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
}
