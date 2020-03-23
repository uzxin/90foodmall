package com.cuit.foodmall.admin.controller;

import com.cuit.foodmall.service.RoleService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YX
 * @date: 2020/3/23 12:21
 * @description: 角色
 */
@RestController
@RequestMapping("admin/role")
public class AdminRoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("list")
	public Object list(){
		return Result.ok(roleService.list());
	}
}
