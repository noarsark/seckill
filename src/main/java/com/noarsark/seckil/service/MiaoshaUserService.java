package com.noarsark.seckil.service;

import com.noarsark.seckil.Result.CodeMsg;
import com.noarsark.seckil.dao.MiaoshaUserDao;
import com.noarsark.seckil.domain.MiaoshaUser;
import com.noarsark.seckil.util.MD5Util;
import com.noarsark.seckil.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id) {
         return miaoshaUserDao.findById(id);

    }

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass= loginVo.getPassword();
        // 判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        // 验证密码
        String dbPass = user.getPassword();
        String saltDb = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDb);
        if (!calcPass.equals(dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }

        return CodeMsg.SUCCESS;


    }

}
