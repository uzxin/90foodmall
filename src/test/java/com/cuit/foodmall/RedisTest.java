package com.cuit.foodmall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: YX
 * @date: 2020/3/3 11:32
 * @description:
 */
@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	@Test
	public void test(){
		redisTemplate.opsForValue().set("test:name","uzxin");
	}

	@Test
	public void test1(){
		System.out.println(redisTemplate.hasKey("test:name"));
	}
}
