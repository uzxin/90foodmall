package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Bank;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.BankService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/25 12:01
 * @description: 银行
 */
@RestController
@RequestMapping("admin/bank")
public class AdminBankController {

	@Autowired
	private BankService bankService;

	/**
	 * @description: 查询银行
	 * @param: bank
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Bank bank, @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<Bank> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<Bank> wrapper = new QueryWrapper<Bank>().lambda();
		if (StringUtils.isNotEmpty(bank.getBankName())){
			wrapper.like(Bank::getBankName, bank.getBankName());
		}
		IPage<Bank> p = bankService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 更新或添加
	 * @param: mallHeadLines
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(Bank bank, HttpSession session){
		if (null == bank.getId()){
			User user = (User) session.getAttribute("admin");
			bank.setCreateUserId(user.getId());
			bank.setCreateUserName(user.getUsername());
		}
		if (bankService.saveOrUpdate(bank)){
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
		if (bankService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}
}
