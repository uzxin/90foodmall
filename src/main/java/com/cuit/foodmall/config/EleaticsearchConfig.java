package com.cuit.foodmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;

/**
 * @author: YX
 * @date: 2020/4/1 15:20
 * @description:
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.cuit.foodmall.es")
public class EleaticsearchConfig {
	@PostConstruct
	void init() {
		System.setProperty("es.set.netty.runtime.available.processors", "false");
	}
}
