package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Role;
import com.cuit.foodmall.service.RoleService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	/**
	 * @description: 查询角色
	 * @param: role
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Role role, @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<Role> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<Role> wrapper = new QueryWrapper<Role>().lambda();
		if (StringUtils.isNotEmpty(role.getName())){
			wrapper.like(Role::getName, role.getName());
		}
		IPage<Role> p = roleService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 更新或添加角色
	 * @param: mallHeadLines
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(Role role){
		if (roleService.saveOrUpdate(role)){
			return Result.ok();
		}
		return Result.error();
	}


	/**
	 * @description: 删除角色
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("del")
	public Object del(Long id){
		if (roleService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

	/**
	 * @description: 查询所有角色
	 * @return: java.lang.Object
	 */
	@GetMapping("list")
	public Object list(){
		return Result.ok(roleService.list());
	}
}
