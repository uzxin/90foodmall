package com.cuit.foodmall;

import com.cuit.foodmall.es.ProductES;
import com.cuit.foodmall.es.ProductRepository;
import com.cuit.foodmall.mapper.ProductESMapper;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.Iterator;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/4/1 14:53
 * @description:
 */
@SpringBootTest
public class TestElasticSearch {

	@Autowired
	private ProductRepository itemRepository;
	@Autowired
	private ElasticsearchTemplate esTemplate;
	@Autowired
	private ProductESMapper mapper;

	/*
	创建索引
	 */
	@Test
	public void cerateIndex(){
		System.out.println(esTemplate);
		// 创建索引，会根据Item类的@Document注解信息来创建
		esTemplate.createIndex(ProductES.class);
		// 配置映射，会根据Item类中的id、Field等字段来自动完成映射
		esTemplate.putMapping(ProductES.class);
	}

	/*
	添加数据
	 */
	@Test
	public void bathSave() {
		itemRepository.deleteAll();
		List<ProductES> list = mapper.list();
		itemRepository.saveAll(list);
	}


	/*
	查询全部
	 */
	@Test
	public void findAll(){
		Iterable<ProductES> item = itemRepository.findAll();
		Iterator<ProductES> it = item.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}

	/*
	分页查询
	 */
	@Test
	public void page(){
		Page<ProductES> page = itemRepository.findAll(PageRequest.of(0, 12));
		for(ProductES item:page){
			System.out.println(item);
		}
	}

	/*
	排序
	 */
	@Test
	public void sort(){
		// descending 降序
		Iterable<ProductES> iterable = itemRepository.findAll(Sort.by("priceSale").descending());
		Iterator<ProductES> it = iterable.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

	/*
	词条匹配，分词
	 */
	@Test
	public void search(){
		// 构建查询条件
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		// 添加基本分词查询
		queryBuilder.withQuery(QueryBuilders.matchQuery("storeName", "三鼠"));
		// 搜索，获取结果
		Page<ProductES> items = itemRepository.search(queryBuilder.build());
		// 总条数
		long total = items.getTotalElements();
		System.out.println("total = " + total);
		for (ProductES item : items) {
			System.out.println(item);
		}
	}

	/*
	词条匹配，不分词
	 */
	@Test
	public void testTermQuery(){
		// 查询条件生成器
		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
		builder.withQuery(QueryBuilders.termQuery("storeName","三鼠"));
		// 查询 自动分页 ,默认查找第一页的10条数据
		Page<ProductES> list = itemRepository.search(builder.build());
		for(ProductES item:list){
			System.out.println(item);
		}
	}

	/*
	模糊匹配
	 */
	@Test
	public void testFuzzyQuery(){
		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
		builder.withQuery(QueryBuilders.fuzzyQuery("storeName","三"));
		Page<ProductES> list = this.itemRepository.search(builder.build());
		for(ProductES item:list){
			System.out.println(item);
		}
	}

	/*
	布尔查询、组合查询
	 */
	@Test
	public void testBooleanQuery(){
		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
		builder.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name","三只松鼠"))
				.should(QueryBuilders.matchQuery("category","三只松鼠"))
				.should(QueryBuilders.matchQuery("storeName","三只松鼠"))
		);
		Page<ProductES> list = this.itemRepository.search(builder.build());
		for(ProductES item:list){
			System.out.println(item);
		}
	}

	/*
	范围匹配
	 *//*
	@Test
	public void testRangeQuery(){
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		queryBuilder.withQuery(QueryBuilders.rangeQuery("price").from(3000).to(4000));
		Page<Item> page = itemRepository.search(queryBuilder.build());
		for(Item i:page){
			System.out.println(i);
		}
	}*/
}
