package com.oywb.weixin.activities;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

public class main {

    public static void main(String[] args) {
        // 示例 Cron 表达式
        String cronExpression = "00 00 01 10 * ?"; // 每月的第一个星期二中午12点执行

        try {
            CronExpression expression = new CronExpression(cronExpression);
            Date now = new Date(System.currentTimeMillis());

            Date next = expression.getNextValidTimeAfter(now);

            //String frequency = getCronFrequency(expression);
            System.out.println("Cron Expression Frequency: " + next);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static String getCronFrequency(CronExpression expression) {
        String[] parts = expression.getCronExpression().split(" ");
        if (parts.length < 6) {
            return "Invalid Cron Expression";
        }

        String seconds = parts[0];
        String minutes = parts[1];
        String hours = parts[2];
        String dayOfMonth = parts[3];
        String month = parts[4];
        String dayOfWeek = parts[5];
        String year = parts.length > 6 ? parts[6] : "*"; // year is optional

        if ("*".equals(dayOfMonth) && "*".equals(month) && "?".equals(dayOfWeek)) {
            return "Daily";
        }

        if ("?".equals(dayOfMonth) && "*".equals(month) && ! "?".equals(dayOfWeek)) {
            return "Weekly";
        }

        if (!"*".equals(dayOfMonth) && "*".equals(month) && "?".equals(dayOfWeek)) {
            return "Monthly";
        }

        return "Custom";
    }
}
