package com.noarsark.seckil.service;

import com.noarsark.seckil.domain.MiaoshaUser;
import com.noarsark.seckil.domain.OrderInfo;
import com.noarsark.seckil.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author noarsark
 * @date 2022/5/30
 * @apiNote
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        // 减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        // order_info miaosha_order
        return orderService.createOrder(user, goods);
    }
}
