package com.noarsark.seckill.service;

import com.noarsark.seckill.dao.GoodsDao;
import com.noarsark.seckill.domain.SecKillGoods;
import com.noarsark.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 商品Service
 * @author: noarsark
 * @email: noarsark817@163.com
 * @date: 2019/5/6 10:57 AM
 * @project: seckill
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 获取所有秒杀商品详情
     * @return
     */
    public List<GoodsVO> getGoodsVOs() {
        return goodsDao.getGoodsVOs();
    }

    /**
     * 获取某款商品详细信息
     * @param goodsId
     * @return
     */
    public GoodsVO getGoodsDetailById(Long goodsId) {
        return goodsDao.getGoodsDetailById(goodsId);
    }

    /**
     * 减小库存，这里不需要更新库存，库存的正确更改依靠SQL实现
     * @param goods 对goods进行的库存进行更新
     */
    public int reduceStock(GoodsVO goods) {
        SecKillGoods g = new SecKillGoods();
        g.setGoodsId(goods.getId());
        return goodsDao.updateStock(g);
    }
}
