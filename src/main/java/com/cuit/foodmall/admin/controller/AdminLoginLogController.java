package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.LoginLog;
import com.cuit.foodmall.service.LoginLogService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YX
 * @date: 2020/3/26 17:39
 * @description: 登陆日志
 */
@RestController
@RequestMapping("admin/loginLog")
public class AdminLoginLogController {

	@Autowired
	private LoginLogService loginLogService;

	/**
	 * @description: 查询登陆日志
	 * @param: loginLog
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(LoginLog loginLog, String startDate, String endDate,
	                   @RequestParam(required = false, defaultValue = "1") int page,
	                   @RequestParam(required = false, defaultValue = "10") int limit) {
		Page<LoginLog> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<LoginLog> wrapper = new QueryWrapper<LoginLog>().lambda();
		wrapper.orderByDesc(LoginLog::getCreateTime);
		if (StringUtils.isNotEmpty(loginLog.getLoginName())) {
			wrapper.like(LoginLog::getLoginName, loginLog.getLoginName());
		}
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)){
			wrapper.between(LoginLog::getCreateTime, startDate, endDate);
		}
		IPage p = loginLogService.page(ipage, wrapper);
		return new Result(0, "", p.getTotal(), p.getRecords());
	}
}
