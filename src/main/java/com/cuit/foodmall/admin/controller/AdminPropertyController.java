package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Property;
import com.cuit.foodmall.entity.ShipMethod;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.PropertyService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/25 12:19
 * @description: 商品属性
 */
@RestController
@RequestMapping("admin/property")
public class AdminPropertyController {

	@Autowired
	private PropertyService propertyService;

	/**
	 * @description: 查询属性
	 * @param: bank
	 * @param: page
	 * @param: property
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Property property, @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<Property> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<Property> wrapper = new QueryWrapper<Property>().lambda();
		if (StringUtils.isNotEmpty(property.getName())){
			wrapper.like(Property::getName, property.getName());
		}
		IPage<Property> p = propertyService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 更新或添加
	 * @param: mallHeadLines
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(Property property, HttpSession session){
		if (null == property.getId()){
			User user = (User) session.getAttribute("admin");
			property.setCreateUserId(user.getId());
			property.setCreateUserName(user.getUsername());
		}
		if (propertyService.saveOrUpdate(property)){
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
		if (propertyService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}
}
