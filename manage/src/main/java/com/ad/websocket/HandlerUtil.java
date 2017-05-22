package com.ad.websocket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiang on 2017/5/21.
 */
public class HandlerUtil {
    /**
     * 解析参数
     *
     * @param queryString
     * @return
     */
    public static Map<String, String> parse(String queryString) {

        Map<String, String> map = new HashMap<String, String>();

        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length > 1) {
                map.put(keyValue[0], keyValue[1]);
            } else {
                map.put(keyValue[0], null);
            }
        }

        return map;
    }
}
