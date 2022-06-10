package net.xdclass.xdvideo.annotation;

import net.xdclass.xdvideo.model.enums.OperateTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLog {
    String value() default "日志记录";

    OperateTypeEnum operateType() default OperateTypeEnum.OTHER;

    boolean resultFlag() default false;
}
