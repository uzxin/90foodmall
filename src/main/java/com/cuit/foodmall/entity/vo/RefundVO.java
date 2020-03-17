package com.cuit.foodmall.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cuit.foodmall.entity.Refund;
import lombok.Data;

/**
 * @author: YX
 * @date: 2020/3/17 11:30
 * @description:
 */
@Data
public class RefundVO extends Refund {

	@TableField("src")
	private String src;
}
