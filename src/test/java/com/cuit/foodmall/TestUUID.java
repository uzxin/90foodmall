package com.cuit.foodmall;

import com.cuit.foodmall.service.OrderService;
import com.cuit.foodmall.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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

	@Test
	public void test4(){
		for (int i = 0; i < 10; i++) {
			int hashCode = UUID.randomUUID().toString().hashCode();
			//可能为负数
			if(hashCode<0){
				hashCode = -hashCode;
			}
			System.out.println(hashCode);
		}
	}

	@Test
	public void test454548(){
		System.out.println(UUID.randomUUID().toString());
	}

	@Test
	public void test1578(){
		System.out.println(getBetweenDate("2020-03-20","2020-03-20"));
		System.out.println(getBetweenDate("2020-03-20","2020-03-19"));
		System.out.println(getBetweenDate("2020-03-20","2020-03-31"));
	}

	public static List<String> getBetweenDate(String start, String end){
		List<String> list = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);

		long distance = ChronoUnit.DAYS.between(startDate, endDate);
		if (distance < 1) {
			return list;
		}
		Stream.iterate(startDate, d -> {
			return d.plusDays(1);
		}).limit(distance + 1).forEach(f -> {
			list.add(f.toString());
		});
		return list;
	}
}
