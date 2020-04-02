package com.cuit.foodmall.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: YX
 * @date: 2020/4/1 15:38
 * @description:
 */
public interface ProductRepository extends ElasticsearchRepository<ProductES, Long> {

}
