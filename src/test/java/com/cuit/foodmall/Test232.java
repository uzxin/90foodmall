package com.cuit.foodmall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: YX
 * @date: 2020/3/24 08:46
 * @description:
 */
@SpringBootTest
public class Test232 {

	@Test
	public void test1(){
		String date = "2018-01-01 - 2050-15-25";
		String[] split = date.split("-");
		for (String s : split) {
			System.out.println(s);
		}
	}

	@Test
	public void test2222(){
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println(LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println(LocalDateTime.now().minusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println(LocalDateTime.now().minusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println(LocalDateTime.now().minusDays(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println(LocalDateTime.now().minusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println(LocalDateTime.now().minusDays(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}
}
