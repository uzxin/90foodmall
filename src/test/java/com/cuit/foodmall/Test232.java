package com.cuit.foodmall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
