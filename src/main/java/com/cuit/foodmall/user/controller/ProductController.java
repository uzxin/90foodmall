package com.cuit.foodmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.foodmall.entity.*;
import com.cuit.foodmall.entity.dto.SearchKeyWordDTO;
import com.cuit.foodmall.entity.vo.ProductVO;
import com.cuit.foodmall.es.ProductES;
import com.cuit.foodmall.es.ProductRepository;
import com.cuit.foodmall.service.*;
import com.cuit.foodmall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/2 10:45
 * @description: 商品
 */
@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductPropertyService productPropertyService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SearchHistoryService searchHistoryService;
	@Autowired
	private ProductFeaturedService productFeaturedService;

	/**
	 * @description: 根据关键词搜索
	 * @param: keyWord
	 * @return: java.lang.Object
	 */
	@GetMapping("search")
	public Object search(String keyword, HttpSession session,
	                     @RequestParam(required = false,defaultValue = "1") int page,
	                     @RequestParam(required = false,defaultValue = "12") int limit){
		page -= 1;//搜索服务器分页从0开始
		PageRequest ipage = PageRequest.of(page, limit);
		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
		if (StringUtils.isEmpty(keyword)){
			return Result.error("关键词不能为空");
		}
		builder.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name",keyword))
				.should(QueryBuilders.matchQuery("category",keyword))
				.should(QueryBuilders.matchQuery("storeName",keyword))
		);
		builder.withPageable(ipage);
		org.springframework.data.domain.Page<ProductES> list = productRepository.search(builder.build());
		if (StringUtils.isNotEmpty(keyword)){
			User user = (User) session.getAttribute("user");
			SearchHistory history = new SearchHistory();
			history.setUserId(user.getId());
			history.setUsername(user.getUsername());
			history.setKeyword(keyword);
			searchHistoryService.save(history);
		}
		return Result.ok(list);
	}

	/**
	 * @description: 根据分类查询商品
	 * @param: categoryId
	 * @return: java.lang.Object
	 */
	@GetMapping("category/{categoryId}")
	public Object listProduct(@PathVariable("categoryId") Long categoryId,
	                          @RequestParam(required = false,defaultValue = "1") int page,
	                          @RequestParam(required = false,defaultValue = "12") int limit){
		Page<ProductVO> ipage = new Page<>(page, limit);
		IPage<ProductVO> p = productService.listProductByCid(ipage, categoryId);
		return Result.ok(p);
	}

	/**
	 * @description: 商品详情
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@GetMapping("details/{productId}")
	public Object getProduct(@PathVariable("productId") Long productId){
		return Result.ok(productService.getProductById(productId));
	}

	/**
	 * @description: 查询商品参数信息
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@GetMapping("property/{productId}")
	public Object getProperty(@PathVariable("productId") Long productId){
		LambdaQueryWrapper<ProductProperty> wrapper = new QueryWrapper<ProductProperty>().lambda();
		wrapper.eq(ProductProperty::getProductId, productId);
		return Result.ok(productPropertyService.list(wrapper));
	}

	/**
	 * @description: 查询商品图片
	 * @param: productId
	 * @return: java.lang.Object
	 */
	@GetMapping("images/{productId}")
	public Object getImages(@PathVariable("productId") Long productId){
		LambdaQueryWrapper<ProductImage> wrapper = new QueryWrapper<ProductImage>().lambda();
		wrapper.eq(ProductImage::getProductId, productId);
		return Result.ok(productImageService.list(wrapper));
	}

	/**
	 * @description: 查看搜索记录
	 * @return: java.lang.Object
	 */
	@GetMapping("getSearchHistory")
	public Object getSearchHistory(HttpSession session){
		User user = (User) session.getAttribute("user");
		return Result.ok(searchHistoryService.listByUserId(user.getId()));
	}

	/**
	 * @description: 通过搜索记录推荐商品
	 * @param: session
	 * @return: java.lang.Object
	 */
	@GetMapping("listProductBySearchHistory")
	public Object listProductBySearchHistory(HttpSession session){
		User user = (User) session.getAttribute("user");
		List<SearchHistory> searchHistories = searchHistoryService.listByUserId(user.getId());
		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
		//如果有搜索记录则根据搜索记录
		//否则根据搜索关键词排行榜去搜索
		switch (searchHistories.size()){
			case 0:List<SearchKeyWordDTO> list= searchHistoryService.listSearchNum();
				if (list.size() != 0){
					builder.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name",list.get(0).getKeyword()))
							.should(QueryBuilders.matchQuery("category",list.get(0).getKeyword()))
							.should(QueryBuilders.matchQuery("storeName",list.get(0).getKeyword())));
				}
				break;
			case 1:builder.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name",searchHistories.get(0).getKeyword()))
					.should(QueryBuilders.matchQuery("category",searchHistories.get(0).getKeyword()))
					.should(QueryBuilders.matchQuery("storeName",searchHistories.get(0).getKeyword()))
			);break;
			case 2:builder.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name",searchHistories.get(0).getKeyword()))
					.should(QueryBuilders.matchQuery("category",searchHistories.get(0).getKeyword()))
					.should(QueryBuilders.matchQuery("storeName",searchHistories.get(0).getKeyword()))
					.should(QueryBuilders.matchQuery("name",searchHistories.get(1).getKeyword()))
					.should(QueryBuilders.matchQuery("category",searchHistories.get(1).getKeyword()))
					.should(QueryBuilders.matchQuery("storeName",searchHistories.get(1).getKeyword()))
			);break;
			case 3:builder.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name",searchHistories.get(0).getKeyword()))
					.should(QueryBuilders.matchQuery("category",searchHistories.get(0).getKeyword()))
					.should(QueryBuilders.matchQuery("storeName",searchHistories.get(0).getKeyword()))
					.should(QueryBuilders.matchQuery("name",searchHistories.get(1).getKeyword()))
					.should(QueryBuilders.matchQuery("category",searchHistories.get(1).getKeyword()))
					.should(QueryBuilders.matchQuery("storeName",searchHistories.get(1).getKeyword()))
					.should(QueryBuilders.matchQuery("name",searchHistories.get(2).getKeyword()))
					.should(QueryBuilders.matchQuery("category",searchHistories.get(2).getKeyword()))
					.should(QueryBuilders.matchQuery("storeName",searchHistories.get(2).getKeyword()))
			);break;
		}
		builder.withPageable(PageRequest.of(0,12));
		org.springframework.data.domain.Page<ProductES> list = productRepository.search(builder.build());
		return Result.ok(list);
	}

	/**
	 * @description: 获取推荐商品
	 * @param: position 推荐位置
	 * @param: limit 数量
	 * @return: java.lang.Object
	 */
	@GetMapping("getProductFeatured")
	public Object getProductFeatured(String position,
	                                 @RequestParam(required = false,defaultValue = "10")int limit){
		LambdaQueryWrapper<ProductFeatured> wrapper = new QueryWrapper<ProductFeatured>().lambda();
		if (StringUtils.isNotEmpty(position)){
			wrapper.eq(ProductFeatured::getPosition, position);
		}
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		wrapper.lt(ProductFeatured::getStartDate, now);
		wrapper.gt(ProductFeatured::getEndDate, now);
		wrapper.last("AND pf.del_flag=0 AND pi.type=0 ORDER BY pf.start_date");
		return Result.ok(productFeaturedService.listAll(new Page<ProductFeatured>(1,limit),wrapper));
	}
}
