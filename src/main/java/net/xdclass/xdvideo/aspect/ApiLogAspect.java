package net.xdclass.xdvideo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ApiLogAspect {

    @Pointcut("@annotation(net.xdclass.xdvideo.annotation.ApiLog)") // 定义切点
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("ApiLogAspect around start");
        Object result = point.proceed();
        MethodSignature signature = (MethodSignature) point.getSignature();
        System.out.println(signature.getName());
        System.out.println(point.getTarget().getClass().getName());

        System.out.println("ApiLogAspect around end");
        return result;
    }
}
