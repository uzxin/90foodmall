package com.cuit.foodmall.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.aop.StoreLog;
import com.cuit.foodmall.entity.Store;
import com.cuit.foodmall.entity.StoreOperationLog;
import com.cuit.foodmall.service.StoreOperationLogService;
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
 * @date: 2020/3/31 11:41
 * @description: 店铺操作日志
 */
@RestController
@RequestMapping("store/operationLog")
public class StoreOperationLogController {

	@Autowired
	private StoreOperationLogService operationLogService;

	/**
	 * @description: 查询店铺操作日志
	 * @param: loginLog
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@StoreLog(value = "查看系统操作日志")
	@GetMapping("page")
	public Object page(StoreOperationLog storeOperationLog, String startDate, String endDate,
	                   HttpSession session,
	                   @RequestParam(required = false, defaultValue = "1") int page,
	                   @RequestParam(required = false, defaultValue = "10") int limit) {
		Page<StoreOperationLog> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<StoreOperationLog> wrapper = new QueryWrapper<StoreOperationLog>().lambda();
		wrapper.orderByDesc(StoreOperationLog::getCreateTime);
		if (StringUtils.isNotEmpty(storeOperationLog.getOperatorName())) {
			wrapper.like(StoreOperationLog::getOperatorName, storeOperationLog.getOperatorName());
		}
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)){
			wrapper.between(StoreOperationLog::getCreateTime, startDate, endDate);
		}
		Long storeId = ((Store) session.getAttribute("store")).getId();//店铺ID
		wrapper.eq(StoreOperationLog::getStoreId,storeId);
		IPage p = operationLogService.page(ipage, wrapper);
		return new Result(0, "", p.getTotal(), p.getRecords());
	}
}
