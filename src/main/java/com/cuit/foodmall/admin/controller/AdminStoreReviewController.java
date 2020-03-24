package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
 * @date: 2020/3/24 12:12
 * @description: 店铺审核
 */
@RestController
@RequestMapping("admin/storeReview")
public class AdminStoreReviewController {

	@Autowired
	private StoreService storeService;

	/**
	 * @description: 查询待审核和未通过的店铺
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Store store,@RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<Store> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<Store> wrapper = new QueryWrapper<Store>().lambda();
		if (StringUtils.isNotEmpty(store.getStatus())){
			wrapper.eq(Store::getStatus,store.getStatus());
		}else {
			wrapper.in(Store::getStatus,"0","3");//0代表待审核,3代表审核未通过
		}
		IPage<Store> p = storeService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 审核通过
	 * @param: id
	 * @return: java.lang.Object
	 */
	@GetMapping("pass")
	public Object pass(Long id){
		UpdateWrapper<Store> wrapper = new UpdateWrapper<>();
		wrapper.eq("id",id);
		wrapper.set("status","1");//店铺状态从待审核置为开启
		if (storeService.update(wrapper)){
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * @description: 审核不通过
	 * @param: id
	 * @param: reviewOpinion
	 * @return: java.lang.Object
	 */
	@GetMapping("nopass")
	public Object nopass(Long id, String reviewOpinion){
		UpdateWrapper<Store> wrapper = new UpdateWrapper<>();
		wrapper.eq("id",id);
		wrapper.set("status","3");//店铺状态置为不通过
		wrapper.set("review_opinion", reviewOpinion);
		if (storeService.update(wrapper)){
			return Result.ok();
		}
		return Result.error();
	}
}
