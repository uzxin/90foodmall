package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.UserAddress;
import com.cuit.foodmall.mapper.UserAddressMapper;
import com.cuit.foodmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/26 15:44
 * @description:
 */
@Service("UserAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

	@Autowired
	private UserAddressMapper userAddressMapper;

	@Override
	public void setDefault(Long id) {
		userAddressMapper.setDefault(id);
	}
}
