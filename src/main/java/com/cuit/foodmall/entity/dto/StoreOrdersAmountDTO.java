package com.cuit.foodmall.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: YX
 * @date: 2020/4/3 14:41
 * @description:
 */
@Data
public class StoreOrdersAmountDTO {
    /*
	店铺名字
	 */
    @TableField("storeName")
    private String storeName;
    /*
    订单数
     */
    @TableField("orders")
    private int orders;
    /*
    金额
     */
    @TableField("amount")
    private BigDecimal amount;
}
