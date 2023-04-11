package com.oywb.weixin.activities.util;

import org.quartz.CronExpression;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CronUtils {
    /**
     * 将 Timestamp 转成 Cron 表达式
     *
     * @param timestamp Timestamp
     * @return Cron 表达式
     */
    public static String timestampToCron(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        String format = new SimpleDateFormat("ss mm HH dd MM ? yyyy").format(date);
        CronExpression cronExpression;
        try {
            cronExpression = new CronExpression(format);
        } catch (Exception e) {
            throw new RuntimeException("日期格式错误，转换失败");
        }
        return cronExpression.getCronExpression();
    }
}

