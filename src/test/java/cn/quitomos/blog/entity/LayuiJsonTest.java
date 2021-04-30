package cn.quitomos.blog.entity;

import cn.quitomos.blog.BaseTest;
import cn.quitomos.blog.dto.LayuiJson;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LayuiJsonTest extends BaseTest {

    @Test
    public void newLayuiJson() {
        LayuiJson layuiJson = new LayuiJson(LayuiJson.Result.SUCCESS, "success!", "data", 3);
        System.out.println(layuiJson.toString());
        Map<String, Object> json = new HashMap<>();
        json.put("a", 1);
        json.put("b", "2");
        System.out.println(new JSONObject(json).toString());
    }
}
