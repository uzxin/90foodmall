package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.MallHeadLines;
import com.cuit.foodmall.entity.User;
import com.cuit.foodmall.service.MallHeadLinesService;
import com.cuit.foodmall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: YX
 * @date: 2020/3/25 08:37
 * @description: 商城头条
 */
@RestController
@RequestMapping("admin/mallHeadLines")
public class AdminMallHeadLinesController {

	@Autowired
	private MallHeadLinesService mallHeadLinesService;

	/**
	 * @description: 查询商城头条
	 * @param: mallHeadLines
	 * @param: page
	 * @param: limit
	 * @return: java.lang.Object
	 */
	@GetMapping("page")
	public Object page(MallHeadLines mallHeadLines,
	                   @RequestParam(required = false,defaultValue = "1") int page,
	                   @RequestParam(required = false,defaultValue = "10") int limit){
		Page<MallHeadLines> ipage = new Page<>(page, limit);
		LambdaQueryWrapper<MallHeadLines> wrapper = new QueryWrapper<MallHeadLines>().lambda();
		if (StringUtils.isNotEmpty(mallHeadLines.getName())){
			wrapper.like(MallHeadLines::getName, mallHeadLines.getName());
		}
		wrapper.orderByDesc(MallHeadLines::getWeight);
		IPage<MallHeadLines> p = mallHeadLinesService.page(ipage, wrapper);
		return new Result(0,"",p.getTotal(),p.getRecords());
	}

	/**
	 * @description: 更新或添加
	 * @param: mallHeadLines
	 * @return: java.lang.Object
	 */
	@PostMapping("addOrUpdate")
	public Object addOrUpdate(MallHeadLines mallHeadLines, HttpSession session){
		if (null == mallHeadLines.getId()){
			User user = (User) session.getAttribute("admin");
			mallHeadLines.setCreateUserId(user.getId());
			mallHeadLines.setCreateUserName(user.getUsername());
		}
		if (mallHeadLinesService.saveOrUpdate(mallHeadLines)){
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
		if (mallHeadLinesService.removeById(id)){
			return Result.ok("删除成功");
		}
		return Result.error("删除失败");
	}

	/**
	 * @description: 修改权重
	 * @param: id
	 * @return: java.lang.Object
	 */
	@PostMapping("changeWeight")
	public Object changeWeight(Long id){
		MallHeadLines mallHeadLines = mallHeadLinesService.getById(id);
		if ("0".equals(mallHeadLines.getWeight())){
			mallHeadLines.setWeight("1");
		}else {
			mallHeadLines.setWeight("0");
		}
		if (mallHeadLinesService.updateById(mallHeadLines)){
			return Result.ok();
		}
		return Result.error();
	}
}
