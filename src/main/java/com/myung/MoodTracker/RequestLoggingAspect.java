package com.myung.MoodTracker;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class RequestLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before("execution(* com.myung.MoodTracker..*Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) throws JsonProcessingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String remoteIP = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String parameters = getParametersAsString(joinPoint.getArgs());
        String headers = getHeadersAsString(request);

        logger.info("Request: Remote IP: {}, Headers: {}, Method: {}, URI: {}, Parameters: {}", remoteIP, headers, method, uri, parameters);
    }

    @AfterReturning(pointcut = "execution(* com.myung.MoodTracker..*Controller.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Response: {}", result.toString());
    }

    private String getParametersAsString(Object[] args) throws JsonProcessingException {
        Map<String, Object> parametersMap = new HashMap<>();

        for (Object arg : args) {
            if (arg != null) {
                String argType = arg.getClass().getSimpleName();
                if (!argType.startsWith("BindingResult")) {
                    parametersMap.put(argType, arg);
                }
            }
        }

        return objectMapper.writeValueAsString(parametersMap);
    }

    private String getHeadersAsString(HttpServletRequest request) throws JsonProcessingException {
        Map<String, String> headersMap = new HashMap<>();
        Collections.list(request.getHeaderNames())
                .forEach(headerName -> headersMap.put(headerName, request.getHeader(headerName)));

        return objectMapper.writeValueAsString(headersMap);
    }
}
