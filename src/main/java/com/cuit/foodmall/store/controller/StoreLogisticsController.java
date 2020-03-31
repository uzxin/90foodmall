package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.aop.StoreLog;
import com.cuit.foodmall.entity.Logistics;
import com.cuit.foodmall.entity.Refund;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.service.LogisticsService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/13 10:30
 * @description: 商家物流管理
 */
@RestController
@RequestMapping("store/logistics")
public class StoreLogisticsController {

	@Autowired
	private LogisticsService logisticsService;

	/**
	 * @description: 查询店铺的物流信息
	 * @param: logistics
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "查看物流")
	@GetMapping("page")
	public Object page(Logistics logistics, HttpSession session,String begin, String end,
						@RequestParam(required = false, defaultValue = "1") int page,
						@RequestParam(required = false,defaultValue = "10") int limit){
		Page<Logistics> ipage = new Page<>(page, limit);
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		LambdaQueryWrapper<Logistics> wrapper = new QueryWrapper<Logistics>().lambda();
		if (StringUtils.isNotEmpty(logistics.getExpressNumber())){
			wrapper.eq(Logistics::getExpressNumber, logistics.getExpressNumber());
		}
		if (StringUtils.isNotEmpty(begin) && StringUtils.isNotEmpty(end)){
			wrapper.between(Logistics::getCreateTime,begin,end);
		}
		wrapper.eq(Logistics::getStoreId, storeId);
		IPage<Logistics> p = logisticsService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

}
