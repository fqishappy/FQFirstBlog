package com.fqishappy.aspect;

import com.alibaba.fastjson.JSON;
import com.fqishappy.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fqishappy
 * @date 2024/9/17 17:19
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.fqishappy.annotation.SystemLog)")
    public void pt() {}

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            log.info("=======End=======" + System.lineSeparator());
        }

        return ret;
    }


    public void handleBefore(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=============Start=============");
        // 打印请求 URL
        log.info("URL           : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("BusinessName  : {}", systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method   : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method  : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), (MethodSignature) joinPoint.getSignature());
        // 打印请求的 IP
        log.info("IP            : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args  : {}", JSON.toJSONString(joinPoint.getArgs()));
        // 结束后换行
        log.info("=============End=============" + System.lineSeparator());
    }

    private void handleAfter(Object ret) throws Throwable {
        // 打印出参
        log.info("Response      : {}", JSON.toJSONString(ret));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }

}
