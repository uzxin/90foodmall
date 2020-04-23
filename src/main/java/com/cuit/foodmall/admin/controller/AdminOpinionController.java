package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.Opinion;
import com.cuit.foodmall.service.OpinionService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YX
 * @date: 2020/4/23 14:43
 * @description: 意见反馈
 */
@RestController
@RequestMapping("admin/opinion")
public class AdminOpinionController {

	@Autowired
	private OpinionService opinionService;

	/**
	 * @description: 查询
	 * @param: opinion
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(Opinion opinion,@RequestParam(required = false, defaultValue = "1") int page,
	                   @RequestParam(required = false, defaultValue = "10") int limit){
		Page<Opinion> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<Opinion> wrapper = new QueryWrapper<Opinion>().lambda();
		if (StringUtils.isNotEmpty(opinion.getOpinionType())){
			wrapper.eq(Opinion::getOpinionType, opinion.getOpinionType());
		}
		IPage<Opinion> p = opinionService.page(ipage, wrapper);
		return new Result(0, "", p.getTotal(), p.getRecords());
	}
}
