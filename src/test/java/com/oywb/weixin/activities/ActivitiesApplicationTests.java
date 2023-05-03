/*
package com.oywb.weixin.activities;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.quartz.CronExpression;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ActivitiesApplicationTests {

	@Test
	void contextLoads() throws Exception {
		String cronExpression = "0 0 4 ? * 3"; // 每周一执行一次
		boolean isValid = CronExpression.isValidExpression(cronExpression);

		if (isValid) {

			CronExpression cron = new CronExpression("0 0 12 ? * 5");
			Date now = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
			Date next = cron.getNextValidTimeAfter(now);

			Instant instant = next.toInstant();
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());



			localDateTime.getMonthValue();localDateTime.getYear();localDateTime.getDayOfMonth();

			LocalDate nowld = LocalDate.now(ZoneId.systemDefault());
			LocalDateTime startOfDay = nowld.atStartOfDay();

			if (startOfDay.getMonthValue() == localDateTime.getMonthValue() && startOfDay.getYear() == localDateTime.getYear()
					&& startOfDay.getDayOfMonth() == localDateTime.getDayOfMonth()) {
				System.out.println("same day");
			}


*/
/*			CronExpression cron = new CronExpression(cronExpression);
			Date lastDay = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
			Date nextValidTime = new Date(cron.getNextValidTimeAfter(lastDay).getTime() + 24 * 60 * 60 * 1000);


			LocalDate now = LocalDate.now(ZoneId.systemDefault());
			LocalDateTime startOfDay = now.atStartOfDay();
			Instant instant = nextValidTime.toInstant();
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			boolean isToday = localDateTime.isAfter(startOfDay);
			System.out.println("Cron expression will " + (isToday ? "" : "not ") + "trigger today");*//*

			System.out.println(next);
		} else {
			System.out.println("Invalid cron expression");
		}
	}

}
*/
