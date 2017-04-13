package com.ad.common;

import org.apache.log4j.Logger;

/**
 * Created by xiang on 2017/4/7.
 */
public class RestResultGenerator {
    private static final Logger LOGGER = Logger.getLogger(RestResultGenerator.class);

    /**
     * 生成响应成功的(不带正文)的结果
     *
     * @param message 成功提示信息
     * @return ResponseResult
     */
    public static ResponseResult genResult(String message) {

        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 生成响应成功(带正文)的结果
     *
     * @param data    结果正文
     * @param message 成功提示信息
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> genResult(T data, String message) {

        ResponseResult<T> result = new ResponseResult();
        result.setSuccess(true);
        result.setData(data);
        result.setMessage(message);

        return result;
    }

    /**
     * 生成响应失败的结果
     *
     * @param message 自定义错误信息
     * @return ResponseResult
     */
    public static ResponseResult genErrorResult(String message) {

        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        result.setMessage(message);

        return result;
    }

    /**
     * 生成响应失败(带errorCode)的结果
     *
     * @param responseErrorEnum 失败信息
     * @return ResponseResult
     */
    public static ResponseResult genErrorResult(ResponseErrorEnum responseErrorEnum) {

        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        result.setErrorInfo(responseErrorEnum);

        return result;
    }
}