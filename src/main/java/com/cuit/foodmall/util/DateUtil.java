package com.cuit.foodmall.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: YX
 * @date: 2020/3/31 08:48
 * @description:
 */
public class DateUtil {

	/**
	 * @description: 获取两个日期区间中的日期
	 * @param: start
	 * @param: end
	 * @return: java.util.List<java.lang.String>
	 */
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
