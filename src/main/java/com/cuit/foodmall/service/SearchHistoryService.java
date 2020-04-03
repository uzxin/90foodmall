package com.cuit.foodmall.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.foodmall.entity.SearchHistory;
import com.cuit.foodmall.entity.dto.SearchKeyWordDTO;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/2 14:26
 * @description:
 */
public interface SearchHistoryService extends IService<SearchHistory> {
	/**
	 * @description: 查询用户搜索记录
	 * @param: userId
	 * @return: java.util.List<com.cuit.foodmall.entity.SearchHistory>
	 */
	List<SearchHistory> listByUserId(Long userId);

	/**
	 * @description: 查询关键词搜索次数
	 * @return: java.util.List<com.cuit.foodmall.entity.SearchKeyWordDTO>
	 */
	List<SearchKeyWordDTO> listSearchNum();
}
