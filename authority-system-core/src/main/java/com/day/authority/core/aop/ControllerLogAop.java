package com.day.authority.core.aop;

import cn.hutool.core.util.ReflectUtil;
import com.day.authority.common.annotations.SaveApiLog;
import com.day.authority.common.dto.Result;
import com.day.authority.common.enums.ResponseCode;
import com.day.authority.common.util.JsonUtil;
import com.day.authority.core.log.ControllerLogDto;
import com.day.authority.core.log.service.ControllerLogAopPostProcess;
import com.day.authority.core.util.HttpServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:19
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAop {

    @Autowired(required = false)
    private ControllerLogAopPostProcess logAopPostProcess;


    @Pointcut("execution(public * com.day.authority.server.model.*.*.api.controller.*.*(..)) || @annotation(com.day.authority.common.annotations.SaveApiLog)")
    public void logPointCut() {
    }


    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint pjp) {
        HttpServletRequest request = HttpServletUtil.getRequest();
        try {
            if (Objects.isNull(request)) {
                return pjp.proceed();
            }

            //记录执行时间
            log.info("---- [request] url: {}, args:{}", request.getRequestURL(), obtainMethodArgs(pjp));
            long start = System.currentTimeMillis();
            Object result = pjp.proceed();
            long runTime = System.currentTimeMillis() - start;

            String resJson = JsonUtil.toJson(result);
            log.info("---- [response] url: {} userTime:{}, result:{}", request.getRequestURL(), runTime, resJson);

            //如果需要保存，则异步入库
            Method workMethod = ReflectUtil.getMethodByName(pjp.getSignature().getDeclaringType(), pjp.getSignature().getName());
            SaveApiLog annotation = workMethod.getAnnotation(SaveApiLog.class);
            if (Objects.nonNull(annotation) && Objects.nonNull(logAopPostProcess)) {
                ControllerLogDto controllerLog = ControllerLogDto
                        .builder()
                        .runTime(runTime)
                        .resJson(resJson)
                        .args(pjp.getArgs()).build();
                logAopPostProcess.postProcess(request, controllerLog);
            }

            return result;
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            return Result.fail(ResponseCode.FAIL);
        }
    }

    private static String obtainMethodArgs(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] argNames = methodSignature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();
        // 拼接参数
        Map<String, Object> args = new HashMap<>(argValues.length);
        for (int i = 0; i < argNames.length; i++) {
            String argName = argNames[i];
            Object argValue = argValues[i];
            // 被忽略时，标记为 ignore 字符串，避免和 null 混在一起
            args.put(argName, !isIgnoreArgs(argValue) ? argValue : "[参数不支持打印]");
        }
        return JsonUtil.toJson(args);
    }

    private static boolean isIgnoreArgs(Object object) {
        if (object == null) {
            return Boolean.FALSE;
        }

        Class<?> clazz = object.getClass();
        // 处理数组的情况
        if (clazz.isArray()) {
            return IntStream.range(0, Array.getLength(object))
                    .anyMatch(index -> isIgnoreArgs(Array.get(object, index)));
        }
        // 递归，处理数组、Collection、Map 的情况
        if (Collection.class.isAssignableFrom(clazz)) {
            return ((Collection<?>) object).stream()
                    .anyMatch((Predicate<Object>) ControllerLogAop::isIgnoreArgs);
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return isIgnoreArgs(((Map<?, ?>) object).values());
        }

        // obj
        return object instanceof BindingResult || object instanceof MultipartFile || object instanceof HttpServletRequest || object instanceof HttpServletResponse;
    }

}
