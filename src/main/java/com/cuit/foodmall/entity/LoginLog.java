package com.cuit.foodmall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: YX
 * @date: 2020/3/26 17:33
 * @description: 登陆日志
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("login_log")
public class LoginLog extends BasePO{

	/*
	登陆时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@TableField("create_time")
	private Date createTime;
	/*
	登陆ip
	 */
	@TableField("login_ip")
	private String loginIp;
	/*
	登陆ID
	 */
	@TableField("login_id")
	private Long loginId;
	/*
	登陆名
	 */
	@TableField("login_name")
	private String loginName;
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
}
