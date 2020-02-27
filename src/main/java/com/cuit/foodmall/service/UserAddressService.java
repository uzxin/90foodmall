package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.UserAddress;

/**
 * @author: YX
 * @date: 2020/2/26 15:43
 * @description:
 */
public interface UserAddressService extends IService<UserAddress> {
	/**
	 * @description: 设置默认地址
	 * @param: id
	 * @return: void
	 */
	public void setDefault(Long id);
}
