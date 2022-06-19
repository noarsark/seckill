package com.noarsark.seckill.resolver;

import com.noarsark.seckill.context.UserContext;
import com.noarsark.seckill.domain.User;
import com.noarsark.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @description: 用户参数解析器，用于将参数封装为User
 * @author: noarsark
 * @email: noarsark817@163.com
 * @date: 2019/5/5 5:01 PM
 * @project: seckill
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    /**
     * 判断是否支持对应的参数类型，即参数是否是User类型
     * @param methodParameter 方法参数
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        return parameterType == User.class;
    }

    /**
     * 解析参数，封装返回User对象
     * @param methodParameter 方法参数
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Nullable
    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {
        return UserContext.getUser();
    }
}
