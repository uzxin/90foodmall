package com.cuit.foodmall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cuit.foodmall.entity.SearchHistory;
import com.cuit.foodmall.entity.dto.SearchKeyWordDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/2 14:24
 * @description:
 */
public interface SearchHistoryMapper extends BaseMapper<SearchHistory> {

	@Select("select keyword from search_history as t \n" +
			"WHERE not exists (select 1 from search_history where keyword=t.keyword and id>t.id)\n" +
			"AND user_id=#{userId}\n" +
			"AND del_flag=0\n" +
			"ORDER BY create_time DESC limit 12")
	List<SearchHistory> listByUserId(Long userId);

	@Select("select keyword,count(keyword) AS search_num\n" +
			"FROM search_history \n" +
			"WHERE del_flag=0\n" +
			"GROUP BY keyword\n" +
			"ORDER BY search_num DESC LIMIT 10")
	List<SearchKeyWordDTO> listSearchNum();
}
