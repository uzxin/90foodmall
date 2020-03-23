package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.vo.UserVO;
import com.cuit.foodmall.mapper.UserMapper;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: YX
 * @date: 2020/2/17 15:14
 * @description:
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public IPage<UserVO> listUser(Page<UserVO> ipage, QueryWrapper<UserVO> wrapper) {
		return userMapper.listUser(ipage, wrapper);
	}
}
