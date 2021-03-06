package com.cuit.foodmall.store.controller;

import com.cuit.foodmall.aop.StoreLog;
import com.cuit.foodmall.entity.Store;
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
		Store store = (Store) session.getAttribute("store");
		if (null == store){
			return Result.error("没有店铺信息");
		}
		return Result.ok(store);
	}

	/**
	 * @description: 修改店铺信息
	 * @param: store
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "修改店铺信息")
	@PostMapping("update")
	public Object update(@RequestBody Store store, HttpSession session){
		if (storeService.updateById(store)){
			session.setAttribute("store",storeService.getById(store.getId()));//更新session数据
			return Result.ok("修改成功");
		}
		return Result.error("修改失败");
	}
}
