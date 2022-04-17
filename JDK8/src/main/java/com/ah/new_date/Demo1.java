package com.ah.new_date;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.math.RoundingMode.HALF_UP;

/**
 * <p>
 *  新的日期API
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/15 23:17
 */
public class Demo1 {
    /**
     * // LocalDate 标示日期，有年月日
     */
    @Test
    public void testLocalDate() {
        // 获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);
        // 获取指定日期
        LocalDate localDate = LocalDate.of(2018, 8, 8);
        System.out.println("localDate = " + localDate);
        // 得到年月日
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
    }

    /**
     * LocalTime表示时间有时分秒
     */
    @Test
    public void testLocalTime() {
        // 获取当前时间
        LocalTime now = LocalTime.now();
        System.out.println("now = " + now);
        // 获取指定时间
        LocalTime localTime = LocalTime.of(14, 26, 39);
        System.out.println("localTime = " + localTime);
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println(now.getNano());
    }

    /**
     * LocalDateTime表示日期时间，有年月日时分秒
     */
    @Test
    public void testLocalDateTime() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        // 获取指定时间
        LocalDateTime localTime = LocalDateTime.of(2018, 2, 3,14, 26, 39);
        System.out.println("localTime = " + localTime);

        System.out.println("now.getYear() = " + now.getYear());
        System.out.println("now.getMonthValue() = " + now.getMonthValue());
        System.out.println("now.getDayOfMonth() = " + now.getDayOfMonth());

        System.out.println("now.getHour() = " + now.getHour());
        System.out.println("now.getMinute() = " + now.getMinute());
        System.out.println("now.getSecond() = " + now.getSecond());
        System.out.println("now.getNano() = " + now.getNano());
    }

    /**
     * 修改时间
     * 修改后的时间是一个新的对象，不会改变原本的对象
     */
    @Test
    public void testUpdateDateTime() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        // 修改后
        LocalDateTime updateDateTime = now.withYear(2018);
        System.out.println("updateDateTime = " + updateDateTime);
        // 增加时间
        System.out.println("now.plusDays(2) = " + now.plusYears(2));
        // 减少时间
        System.out.println("now.minusDays(10) = " + now.minusYears(10));
    }

    /**
     * 比较时间
     */
    @Test
    public void testEqualsLocalDate() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取指定时间
        LocalDateTime localDateTime = LocalDateTime.of(2018, 2, 3,14, 26, 39);
        // 前面的时间是不是在后面的时间的后面
        System.out.println("now.isAfter(localTime) = " + now.isAfter(localDateTime));
        // 前面的时间是不是在后面的时间的前面
        System.out.println("now.isBefore(localTime) = " + now.isBefore(localDateTime));
        // 判断两个时间是否相等
        System.out.println("now.isEqual(localTime) = " + now.isEqual(localDateTime));
    }

    /**
     * 日期格式化
     */
    @Test
    public void testFormat() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now格式化前 = " + now);
        // 格式化类
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        String format = now.format(dtf);
        System.out.println("now格式化后 = " + format);
        // 自定义格式化
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd HH时mm分ss秒");
        String format1 = now.format(dtf1);
        System.out.println("now 自定义格式化之后 = " + format1);
        // 解析
        LocalDateTime parse = LocalDateTime.parse(format1, dtf1);
        System.out.println("解析之后的时间 = " + parse);
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                LocalDateTime parses = LocalDateTime.parse(format1, dtf1);
                System.out.println("多线程时间 = " + parse);
            }).start();
        }
    }

    /**
     * 时间戳
     */
    @Test
    public void testInstant() {
        // instant内部保存了秒和纳秒，一般不是给用户用的，而是方便我们程序做一些统计的。
        Instant now = Instant.now();
        System.out.println("now = " + now);
        // 加减
        System.out.println("now.plusSeconds(20) = " + now.plusSeconds(20));
        System.out.println("now.minusSeconds(20) = " + now.minusSeconds(20));
        // 得到秒毫秒纳秒
        System.out.println("now.getEpochSecond() = " + now.getEpochSecond());
        System.out.println("now.getNano() = " + now.getNano());
    }

    /**
     * 计算日期时间差
     * 后面的时间减去前面的时间
     */
    @Test
    public void testDurationAndPeriod() {
        // 获取当前时间
        LocalTime now = LocalTime.now();
        // 获取指定时间
        LocalTime localTime = LocalTime.of(14, 26, 39);
        Duration duration = Duration.between(localTime, now);
        System.out.println("between.toDays() 相差天数= " + duration.toDays());
        System.out.println("between.toHours() 相差小时= " + duration.toHours());
        System.out.println("between.toMinutes() 相差分钟= " + duration.toMinutes());
        System.out.println("between.toNanos() = 相差纳秒=" + duration.toNanos());
        System.out.println("between.toMillis() 相差毫秒= " + duration.toMillis());

        // 获取当前日期
        LocalDate now1 = LocalDate.now();
        // 获取指定日期
        LocalDate localDate = LocalDate.of(2018, 8, 8);
        Period period = Period.between(localDate, now1);
        System.out.println("period.getYears() 相差年= " + period.getYears());
        System.out.println("period.getMonths() 相差月= " + period.getMonths());
        System.out.println("period.getDays() 相差天= " + period.getDays());
    }

    /**
     * TemporalAdjuster类：自定义调整时间
     */
    @Test
    public void testTemporalAdjuster() {

    }
}
