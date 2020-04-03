package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/4/3 10:24
 * @description: 商品推荐
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("product_featured")
public class ProductFeatured extends BasePO{

	/*
	商品ID
	 */
	@TableField("product_id")
	private Long productId;
	/*
	开始日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("start_date")
	private String startDate;
	/*
	结束日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("end_date")
	private String endDate;
	/*
	状态,1推荐中，2过期
	 */
	@TableField("status")
	private String status;
	/*
	推荐人ID
	 */
	@TableField("referrer_id")
	private Long referrerId;
	/*
	推荐人名字
	 */
	@TableField("referrer_name")
	private String referrerName;
	/*
	商品名
	 */
	@TableField("productName")
	private String productName;
	/*
	图片
	 */
	@TableField("src")
	private String src;
	/*
	价格
	 */
	@TableField("priceSale")
	private String priceSale;
	/*
	分类名
	 */
	@TableField("categoryName")
	private String categoryName;
	/*
	店铺名
	 */
	@TableField("storeName")
	private String storeName;
}
