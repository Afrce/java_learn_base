package net.xdclass.xdvideo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonData {
    /**
     * 状态吗 成功 success, 异常或者失败 error
     */
    private String status;

    /**
     * 具体的状态码 例如500， 422，401等
     */
    private Integer code;
    /**
     * 描述
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * 需要返回的数据信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    /**
     * 异常信息的数据 422 时需要返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errors;

    public <T> T getData(TypeReference<T> typeReference){
        return JSON.parseObject(JSON.toJSONString(data),typeReference);
    }

    /**
     * 成功，不传入数据
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData("success", 200, null, null, null);
    }

    /**
     * 成功带上消息
     * @param message
     * @return
     */
    public static JsonData buildSuccessWithMessage(String message) {
        return new JsonData("success", 200, message, null,null);
    }

    /**
     * 成功带上数据
     * @param data
     * @return
     */
    public static JsonData buildSuccessWithData(Object data) {
        return new JsonData("success", 200, null, data, null);
    }
    /**
     * 成功带上消息及数据返回
     * @param message
     * @param data
     * @return
     */
    public static JsonData buildSuccessWithMessageAndData(String message, Object data) {
        return new JsonData("success", 200, message, data, null);
    }

    /**
     * 默认的失败
     * @return
     */
    public static JsonData buildFailed() {
        return new JsonData("errors", 400, null, null, null);
    }

    /**
     * 失败带上指定code
     * @param code
     * @return
     */
    public static JsonData buildFailedWithCode(Integer code) {
        return new JsonData("errors", code, null, null, null);
    }

    /**
     * 失败带上指定code和消息
     * @param code
     * @param message
     * @return
     */
    public static JsonData buildFailedWithCodeAndMsg(Integer code, String message) {
        return new JsonData("errors", code, message,null,  null);
    }

    /**
     * 失败 带上code，消息及errors
     * @param code
     * @param message
     * @param errors
     * @return
     */
    public static JsonData buildFailedWithCodeAndMsgAndErrors(Integer code, String message, Object errors) {
        return new JsonData("errors", code, message, null, errors);
    }
}
