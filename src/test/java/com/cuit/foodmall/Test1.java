package com.cuit.foodmall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * @author: YX
 * @date: 2020/4/15 08:34
 * @description:
 */
@SpringBootTest
public class Test1 {

	@Test
	public void test2() throws FileNotFoundException {
		String s = ResourceUtils.getFile("classpath:").getParent();
		System.out.println(ResourceUtils.getFile("classpath:"));
		System.out.println(s);
	}
}
