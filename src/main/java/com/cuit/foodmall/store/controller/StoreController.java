package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.StoreService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/9 11:00
 * @description: 店铺
 */
@RestController
@RequestMapping("store")
public class StoreController {

	@Autowired
	private StoreService storeService;
	/**
	 * @description: 根据商家获取店铺信息
	 * @return: java.lang.Object
	 */
	@GetMapping("getStoreByBusinessId")
	public Object getStoreByBusinessId(HttpSession session){
		User user = (User) session.getAttribute("business");
		if (null == user){
			return Result.error("未登录");
		}
		LambdaQueryWrapper<Store> wrapper = new QueryWrapper<Store>().lambda();
		wrapper.eq(Store::getBusinessId,user.getId());
		Store store = storeService.getOne(wrapper);
		if (null == store){
			return Result.error("没有店铺信息");
		}
		session.setAttribute("store",store);
		return Result.ok(store);
	}

	/**
	 * @description: 修改店铺信息
	 * @param: store
	 * @return: java.lang.Object
	 */
	@PostMapping("update")
	public Object update(@RequestBody Store store){
		if (storeService.updateById(store)){
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}
}
