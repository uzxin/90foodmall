package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.PayMethod;
import com.cuit.foodmall.entity.ShipMethod;
import com.cuit.foodmall.service.ShipMethodService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: YX
 * @date: 2020/3/25 12:19
 * @description: 物流方式
 */
@RestController
@RequestMapping("admin/shipMethod")
public class AdminShipMethodController {

	@Autowired
	private ShipMethodService shipMethodService;

	/**
	 * @description: 查询物流方式
	 * @param: shipMethod
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(ShipMethod shipMethod, @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<ShipMethod> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<ShipMethod> wrapper = new QueryWrapper<ShipMethod>().lambda();
		if (StringUtils.isNotEmpty(shipMethod.getName())){
			wrapper.like(ShipMethod::getName, shipMethod.getName());
		}
		IPage<ShipMethod> p = shipMethodService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 更新或添加
	 * @param: mallHeadLines
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(ShipMethod shipMethod){
		if (shipMethodService.saveOrUpdate(shipMethod)){
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
		if (shipMethodService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}
}
