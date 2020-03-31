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
 * @date: 2020/3/31 11:12
 * @description: 店铺操作记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("store_operation_log")
public class StoreOperationLog extends BasePO{

	/*
	店铺ID
	 */
	@TableField("store_id")
	private Long storeId;
	/*
	操作人ID
	 */
	@TableField("operator_id")
	private Long operatorId;
	/*
	操作人账号
	 */
	@TableField("operator_name")
	private String operatorName;
	/*
	操作
	 */
	@TableField("operating")
	private String operating;
	/*
	ip地址
	 */
	@TableField("ip")
	private String ip;
	/*
	登陆设备
	 */
	@TableField("device")
	private String device;
	/*
	浏览器类型
	 */
	@TableField("browser_type")
	private String browserType;
	/*
	请求方法名
	 */
	@TableField("method")
	private String method;
	/*
	操作时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
}
