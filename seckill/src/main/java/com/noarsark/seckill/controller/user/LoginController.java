package com.noarsark.seckill.controller.user;

import com.noarsark.seckill.result.ServerResponse;
import com.noarsark.seckill.vo.LoginInfoVO;
import com.noarsark.seckill.domain.User;
import com.noarsark.seckill.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @description: 用户登录Controller
 * @author: noarsark
 * @email: noarsark817@163.com
 * @date: 2019/5/3 12:30 AM
 * @project: seckill
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public ServerResponse<String> doLogin(HttpServletResponse response, @Valid LoginInfoVO loginInfoVO) {
        // 打印用户输入信息日志
        logger.info(loginInfoVO.toString());
        // 登录，出错会抛出全局异常，并被捕获处理
        String token = userService.login(response, loginInfoVO);
        // 直接返回true即可
        return ServerResponse.success(token);
    }

    /**
     * 根据Token获取用户信息
     * @param user
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public ServerResponse<User> userInfo(User user) {
        return ServerResponse.success(user);
    }
}
