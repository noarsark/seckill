package com.noarsark.seckil.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author noarsark
 * @date 2022/5/28
 * @apiNote
 */

@Getter
@Setter
public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;

}
