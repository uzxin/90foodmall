package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.service.StoreService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	 * @param: sort 排序方式
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Store store,String begin,String end,String sort,
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
		wrapper.in(Store::getStatus,"1","2");//只查询开启或者关闭的店铺
		if (StringUtils.isNotEmpty(sort)){
			wrapper.last("ORDER BY "+sort);
		}
		IPage<Store> p = storeService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 开启或关闭店铺
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("openOrClose")
	public Object openOrClose(Long id){
		Store store = storeService.getById(id);
		if (store.getStatus().equals("1")){
			store.setStatus("2");//关闭店铺
		}else if (store.getStatus().equals("2")){
			store.setStatus("1");//开启店铺
		}
		storeService.updateById(store);
		return Result.ok("修改成功");
	}

	@PostMapping("update")
	public Object update(Store store){
		Store s = storeService.getById(store.getId());
		store.setLevel(s.getLevel());
		store.setScoreDeliverySpeed(s.getScoreDeliverySpeed());
		store.setScoreDescription(s.getScoreDescription());
		store.setScoreServiceAttitude(s.getScoreServiceAttitude());
		if (storeService.updateById(store)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}
	/**
	 * @description: 删除店铺
	 * @param: id
	 * @return: java.lang.Object
	 */
	@GetMapping("del")
	public Object del(Long id){
		if (storeService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

	/**
	 * @description: 评分
	 * @param: store
	 * @return: java.lang.Object
	 */
	@PostMapping("score")
	public Object score(Store store){
		if (storeService.updateById(store)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}

}
