package com.ad.common;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by xiang on 2017/4/6.
 */
public enum ResponseErrorEnum {
    ILLEGAL_PARAMS("ILLEGAL_PARAMS", "请求参数不合法!"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "接口内部异常!"),
    SESSION_ERROR("SESSION_ERROR", "session过期,请重新登录");

    private ResponseErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @JsonValue
    private Map<String, Object> serialize() {
        Map<String, Object> valueMap = Maps.newHashMap();
        valueMap.put("code", this.code);
        valueMap.put("message", this.message);
        return valueMap;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
