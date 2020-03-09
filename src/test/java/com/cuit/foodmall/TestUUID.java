package com.cuit.foodmall;

import com.cuit.foodmall.entity.Order;
import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author: YX
 * @date: 2020/3/6 10:24
 * @description:
 */
@SpringBootTest
public class TestUUID {

	@Autowired
	private OrderService orderService;

	@Test
	public void test1(){
		for (int i = 0; i < 100; i++) {
			System.out.println(RandomUtil.get32());
		}
	}

	@Test
	public void test2(){
		Pattern pattern = Pattern.compile("[0-9]*");
		System.out.println(pattern.matcher("12a").matches());
	}

	@Test
	public void test3(){
		orderService.list();
	}
}
