package com.noarsark.seckil.service;

import com.alibaba.druid.util.StringUtils;
import com.noarsark.seckil.Result.CodeMsg;
import com.noarsark.seckil.controller.exception.GlobalException;
import com.noarsark.seckil.dao.MiaoshaUserDao;
import com.noarsark.seckil.domain.MiaoshaUser;
import com.noarsark.seckil.redis.MiaoshaUserKey;
import com.noarsark.seckil.redis.RedisService;
import com.noarsark.seckil.util.MD5Util;
import com.noarsark.seckil.util.UUIDUtil;
import com.noarsark.seckil.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(long id) {
         return miaoshaUserDao.findById(id);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
            System.out.println(user.getNickname());
        }

        return user;
    }

    public Boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass= loginVo.getPassword();
        // 判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 验证密码
        String dbPass = user.getPassword();
        String saltDb = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDb);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        // 生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        // MiaoshaUserKey:tk+uuid
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
