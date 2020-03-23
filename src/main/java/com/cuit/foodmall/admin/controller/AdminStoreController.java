package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.service.StoreService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YX
 * @date: 2020/3/23 17:37
 * @description:
 */
@RestController
@RequestMapping("admin/store")
public class AdminStoreController {

	@Autowired
	private StoreService storeService;

	/**
	 * @description: 查询所有店铺信息
	 * @param: store
	 * @param: begin
	 * @param: end
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Store store,String begin,String end,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<Store> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<Store> wrapper = new QueryWrapper<Store>().lambda();
		if (StringUtils.isNotEmpty(store.getName())){
			wrapper.like(Store::getName, store.getName());
		}
		if (StringUtils.isNotEmpty(begin) &&StringUtils.isNotEmpty(end)){
			wrapper.between(Store::getCreateTime, begin, end);
		}
		wrapper.ne(Store::getStatus,"0");
		IPage<Store> p = storeService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}
}
