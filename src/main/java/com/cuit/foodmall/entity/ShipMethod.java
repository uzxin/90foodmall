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
 * @date: 2020/3/6 10:12
 * @description: 物流方式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("ship_method")
public class ShipMethod extends BasePO {

	/*
	名字
	 */
	@TableField("name")
	private String name;
	/*
	图片地址
	 */
	@TableField("image_src")
	private String imageSrc;
	/*
	创建人ID
	 */
	@TableField("create_user_id")
	private Long createUserId;
	/*
	创建人姓名
	 */
	@TableField("create_user_name")
	private String createUserName;
	/*
	创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
}
