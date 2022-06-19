package com.noarsark.seckill.controller.user;

import com.noarsark.seckill.result.ServerResponse;
import com.noarsark.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 用户注册Controller
 * @author: noarsark
 * @email: noarsark817@163.com
 * @date: 2019/5/3 12:32 AM
 * @project: seckill
 */
@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/batch_register/{num}")
    @ResponseBody
    public ServerResponse<Boolean> batchRegister(@PathVariable("num") Integer num) {
        userService.batchRegister(num);
        return ServerResponse.success(true);
    }
}
