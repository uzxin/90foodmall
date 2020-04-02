package com.cuit.foodmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.foodmall.entity.SearchHistory;
import com.cuit.foodmall.mapper.SearchHistoryMapper;
import com.cuit.foodmall.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/2 14:27
 * @description:
 */
@Service("SearchHistoryService")
public class SearchHistoryServiceImpl extends ServiceImpl<SearchHistoryMapper, SearchHistory> implements SearchHistoryService {
	@Autowired
	private SearchHistoryMapper searchHistoryMapper;

	@Override
	public List<SearchHistory> listByUserId(Long userId) {
		return searchHistoryMapper.listByUserId(userId);
	}
}
