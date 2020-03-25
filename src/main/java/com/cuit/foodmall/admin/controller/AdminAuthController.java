package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Auth;
import com.cuit.foodmall.service.AuthService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/23 12:21
 * @description: 权限
 */
@RestController
@RequestMapping("admin/auth")
public class AdminAuthController {

	@Autowired
	private AuthService authService;

	/**
	 * @description: 查询所有权限
	 * @return: java.lang.Object
	 */
	@GetMapping("list")
	public Object list(Auth auth){
		LambdaQueryWrapper<Auth> wrapper = new QueryWrapper<Auth>().lambda();
		if (StringUtils.isNotEmpty(auth.getName())){
			wrapper.like(Auth::getName, auth.getName());
		}
		List<Auth> list = authService.list(wrapper);
		return new Result(0,"",list.size(),list);
	}

	/**
	 * @description: 更新或添加
	 * @param: mallHeadLines
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(Auth auth){
		if (authService.saveOrUpdate(auth)){
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * @description: 删除
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("del")
	public Object del(Long id){
		if (authService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

}
