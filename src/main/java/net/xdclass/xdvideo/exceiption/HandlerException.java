package net.xdclass.xdvideo.exceiption;

import net.xdclass.xdvideo.utils.JsonData;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) throws Exception {
        String msg = null;
        String field = null;
        HashMap<String, Object> errorMap = new HashMap<String, Object>();
        System.out.println(e.getClass());
       if (e instanceof BindException) {
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
           msg = "提交的数据不可用";
            FieldError fieldError = ((BindException) e).getFieldError();
            if (fieldError != null) {
                field = fieldError.getField();
                String[] msgError = {fieldError.getDefaultMessage()} ;
                errorMap.put(field, msgError);
            }
            return JsonData.buildFailedWithCodeAndMsgAndErrors(422, msg, errorMap);
        } else if (e instanceof JwtException || e instanceof AuthenticationException) {
           return JsonData.buildFailedWithCodeAndMsg(401, e.getMessage());
        } else {
           return JsonData.buildFailedWithCodeAndMsg(400, e.getMessage());
        }
    }
}
