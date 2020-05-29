package com.msacademy.mx.covid.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;
@Aspect
@Component
public class Aop {
    private String logString;
    private static final Logger log = Logger.getLogger(Aop.class.getName());
    @Pointcut("execution(* com.msacademy.mx.covid.controller.*.*(..))")
    protected void allMethods() { }
    @Before("allMethods()")
    public void logBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logString = "HTTP_METHOD: " + request.getMethod()
                + ", URL: " + request.getRequestURL().toString()
                + ", Arguments: " + Arrays.toString(joinPoint.getArgs());
    }
    @AfterReturning(value = "allMethods()", returning = "result")
    public void logAfter(Object result){
        logString += ", HTTP_STATUS: "+this.getValue(result);
        log.info(logString);
    }
    private HttpStatus getValue(Object result) {
        String returnValue = null;
        ResponseEntity response = (ResponseEntity) result;
        return response.getStatusCode();
    }
}



