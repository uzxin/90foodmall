package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.PayMethod;
import com.cuit.foodmall.service.PayMethodService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: YX
 * @date: 2020/3/25 12:19
 * @description: 支付方式
 */
@RestController
@RequestMapping("admin/payMethod")
public class AdminPayMethodController {

	@Autowired
	private PayMethodService payMethodService;

	/**
	 * @description: 查询支付方式
	 * @param: payMethod
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(PayMethod payMethod, @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<PayMethod> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<PayMethod> wrapper = new QueryWrapper<PayMethod>().lambda();
		if (StringUtils.isNotEmpty(payMethod.getName())){
			wrapper.like(PayMethod::getName, payMethod.getName());
		}
		IPage<PayMethod> p = payMethodService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 更新或添加
	 * @param: mallHeadLines
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(PayMethod payMethod){
		if (payMethodService.saveOrUpdate(payMethod)){
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
		if (payMethodService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}
}
