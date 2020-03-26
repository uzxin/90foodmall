package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.Auth;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.entity.vo.UserVO;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/2/17 15:14
 * @description:
 */
public interface UserService extends IService<User> {

	/**
	 * @description: 查询用户账号信息
	 * @param: ipage
	 * @param: wrapper
	 * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.cuit.foodmall.entity.UserVO>
	 */
	IPage<UserVO> listUser(Page<UserVO> ipage, QueryWrapper<UserVO> wrapper);

	/**
	 * @description: 查询用户权限
	 * @param: userId
	 * @return: java.util.List<com.cuit.foodmall.entity.Auth>
	 */
	List<Auth> getAuths(Long userId);
}
