package com.cuit.foodmall.entity.vo;

import com.cuit.foodmall.entity.UserAddress;
import lombok.Data;

/**
 * @author: YX
 * @date: 2020/2/26 16:58
 * @description: 收货地址
 */
@Data
public class UserAddressVO extends UserAddress {
	/*
	省
	 */
	private String province;
	/*
	市
	 */
	private String city;
	/*
	区（县）
	 */
	private String country;
}
