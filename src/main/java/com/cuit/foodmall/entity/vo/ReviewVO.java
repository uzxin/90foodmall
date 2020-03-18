package com.cuit.foodmall.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cuit.foodmall.entity.Review;
import lombok.Data;

/**
 * @author: YX
 * @date: 2020/3/18 10:18
 * @description:
 */
@Data
public class ReviewVO extends Review {
	/*
	订单编号
    */
	@TableField("order_number")
	private String orderNumber;
	/*
	产品图片
    */
	@TableField("src")
	private String src;
}
