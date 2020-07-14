package com.intecsec.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类 <br>
 * SimpleDateFormat实例在ThreadLocal中
 * 
 * JFan 2014年12月10日 下午12:19:00
 */
public final class DateUtils {

	private DateUtils() {
	}

	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHmmss = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHHmmssSSS = "yyyyMMddHHmmssSSS";

	public static final String YYYY_MM_DD_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	public static final String YYYYMMDD_SLASH = "yyyy/MM/dd";

	// ## 时间格式化

	public static SimpleDateFormat getUSDateFormat(String pattern) {
		String key = "DateUtils_US_" + pattern;
		SimpleDateFormat format = ThreadLocalContext.getGlobalValue(key);
		if (null == format) {
			format = new SimpleDateFormat(pattern, Locale.US);
			// format.setTimeZone(TimeZone.getTimeZone("GMT"));
			ThreadLocalContext.setGlobalValue(key, format);
		}
		return format;
	}

	public static SimpleDateFormat getDateFormat(String pattern) {
		String key = "DateUtils_" + pattern;
		SimpleDateFormat format = ThreadLocalContext.getGlobalValue(key);
		if (null == format) {
			format = new SimpleDateFormat(pattern);
			// format = new SimpleDateFormat(pattern, Locale.US);
			// format.setTimeZone(TimeZone.getTimeZone("GMT"));
			ThreadLocalContext.setGlobalValue(key, format);
		}
		return format;
	}

	// 字符串转成时间戳(秒)
	public static Long converStringToTimestamp(String strDate) throws ParseException {
		Long timeStamp = 0L;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
		try {
			Date date = simpleDateFormat.parse(strDate);
			timeStamp = date.getTime() / 1000L;
		} catch (ParseException pe) {
			throw pe;
		}
		return timeStamp;
	}

	// 时间转时间戳
	public static Long converDateToTimestamp(Date date) {
		Long timeStamp = date.getTime() / 1000L;
		return timeStamp;
	}

	// 时间戳转字符串(秒)
	public static String converTimestampToString(Long timeStamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
		return sdf.format(new Date(timeStamp * 1000L));
	}

	// 时间戳转时间
	public static Date converTimestampToDate(Long timeStamp) {
		return new Date(timeStamp * 1000L);
	}

	// ## Calendar

	public static Calendar getCalendar() {
		return getCalendar(System.currentTimeMillis());
	}

	public static Calendar getCalendar(long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return c;
	}

	public static Calendar getCalendar(Date time) {
		Calendar c = Calendar.getInstance();
		if (null != time)
			c.setTime(time);
		return c;
	}

	// ## 获取时间的 YMD值

	/**
	 * 获取当前时间的年份
	 */
	public static int currentYear() {
		Calendar c = getCalendar();
		return c.get(Calendar.YEAR);
	}

	/**
	 * 返回指定日期的年份
	 */
	public static int getYear(Date time) {
		Calendar c = getCalendar(time);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取当前时间的月份
	 */
	public static int currentMonth() {
		Calendar c = getCalendar();
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回指定日期的月份
	 */
	public static int getMonth(Date time) {
		Calendar c = getCalendar(time);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前时间的日期值
	 */
	public static int currentDay() {
		Calendar c = getCalendar();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回指定日期的日期值
	 */
	public static int getDay(Date time) {
		Calendar c = getCalendar(time);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	// ## 时间数字化

	/**
	 * 将当前时间数字化（年月日）<br>
	 * now() -> 20150821
	 */
	public static int currentTimeDay() {
		return getTimeDay(new Date());
	}

	/**
	 * 将指定时间数字化（年月日）<br>
	 * milli -> 20150821
	 */
	public static int getTimeDay(long milli) {
		Args.notNull(milli, "'milli'");
		return getTimeDay(new Date(milli));
	}

	/**
	 * 将指定时间数字化（年月日）<br>
	 * date -> 20150821
	 */
	public static int getTimeDay(Date date) {
		Args.notNull(date, "'date'");
		Calendar c = getCalendar(date);
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		int d = c.get(Calendar.DAY_OF_MONTH);
		return (y * 10000) + (m * 100) + (d);
	}

	/**
	 * 将当前时间数字化（年月）<br>
	 * date -> 201602
	 */
	public static int currentTimeMonth() {
		return getTimeMonth(new Date());
	}

	/**
	 * 将指定时间数字化（年月）<br>
	 * date -> 201602
	 */
	public static int getTimeMonth(Date date) {
		Args.notNull(date, "'date'");
		Calendar c = getCalendar(date);
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		return (y * 100) + m;
	}

	/**
	 * 将当前时间数字化（年）<br>
	 * date -> 2016
	 */
	public static int currentTimeYear() {
		return getTimeYear(new Date());
	}

	/**
	 * 将指定时间数字化（年）<br>
	 * date -> 2016
	 */
	public static int getTimeYear(Date date) {
		Args.notNull(date, "'date'");
		Calendar c = getCalendar(date);
		int y = c.get(Calendar.YEAR);
		return y;
	}

	// ## 字符串 转换成 日期时间

	/**
	 * String 2 Data object
	 */
	public static Date parseString(String source, String pattern) throws ParseException {
		Args.notNull(source, "'source'");
		Args.notNull(pattern, "'pattern'");

		SimpleDateFormat formatter = getDateFormat(pattern);
		return formatter.parse(source);
	}

	// ## 日期时间 转换成 字符串

	@Deprecated
	public static String convertDateToString(Date date) {
		if (null == date)
			return "";
		return formatDate(date, "yyyy-MM-dd");
	}

	@Deprecated
	public static String formatDate(Date date) {
		if (null == date)
			return null;
		return formatDate(date, YYYY_MM_DD_HH_mm_ss);
	}

	/**
	 * long(milli) 2 String
	 */
	public static String formatMilli(long milli, String pattern) {
		Args.notNull(pattern, "'pattern'");

		SimpleDateFormat formatter = getDateFormat(pattern);
		return formatter.format(milli);
	}

	/**
	 * long(secons) 2 String
	 */
	public static String formatSecond(long secons, String pattern) {
		return formatMilli(secons * 1000, pattern);
	}

	/**
	 * Date 2 String
	 */
	public static String formatDate(Date date, String pattern) {
		Args.notNull(date, "'date'");
		Args.notNull(pattern, "'pattern'");

		SimpleDateFormat formatter = getDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * Date 2 String (US)
	 */
	public static String formatUSDate(Date date, String pattern) {
		Args.notNull(date, "'date'");
		Args.notNull(pattern, "'pattern'");

		SimpleDateFormat formatter = getUSDateFormat(pattern);
		return formatter.format(date);
	}

	// ####

	/**
	 * 返回时间的yyyyMMddHHmmss格式字符串
	 * 
	 * @param time 需要转换的时间（不允许为NULL）
	 */
	public static String getSeriesYMDHMS(Date time) {
		return getSeriesYMDHMS(time.getTime());
	}

	/**
	 * 返回时间的yyyyMMddHHmmss格式字符串
	 * 
	 * @param time 需要转化的时间毫秒数
	 */
	public static String getSeriesYMDHMS(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date(time));
	}

	/**
	 * 返回时间的yyyyMMddHHmmss格式字符串<br>
	 * 可操作时间变更
	 * 
	 * @param time 需要转换的时间（不允许为NULL）
	 * @param tu 基于time需要变更的时间单位
	 * @param num 需要变更的时间单位值（可正可负）
	 */
	public static String getSeriesYMDHMS(Date time, TimeUnit tu, long num) {
		long millis = time.getTime();
		long addMillis = tu.toMillis(num);
		return getSeriesYMDHMS(millis + addMillis);
	}

	/**
	 * 返回时间的yyyyMMddHHmmss格式字符串<br>
	 * 可操作时间变更
	 * 
	 * @param time 需要转化的时间毫秒数
	 * @param tu 基于time需要变更的时间单位
	 * @param num 需要变更的时间单位值（可正可负）
	 */
	public static String getSeriesYMDHMS(long time, TimeUnit tu, long num) {
		long addMillis = tu.toMillis(num);
		return getSeriesYMDHMS(time + addMillis);
	}

	// ## 日期时间操作

	/**
	 * 返回指定的日期（+-指定月数）之后的值<br>
	 * 整数就是+month月，负数就是往前推month月
	 */
	public static Date moveMonth(Date current, int month) {
		Calendar c = getCalendar(current);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	/**
	 * 返回指定的日期（+-指定天数）之后的值<br>
	 * 整数就是+day天，负数就是往前推day天
	 */
	public static Date moveDay(Date current, int day) {
		Calendar c = getCalendar(current);
		c.add(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}

	/**
	 * 返回指定的日期（+-指定小时）之后的值<br>
	 * 整数就是+hour小时，负数就是往前推hour小时
	 */
	public static Date moveHour(Date current, int hour) {
		Calendar c = getCalendar(current);
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	/**
	 * 返回指定的日期（+-指定分钟）之后的值<br>
	 * 整数就是+minute分钟，负数就是往前推minute分钟
	 */
	public static Date moveMinute(Date current, int minute) {
		Calendar c = getCalendar(current);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}

	/**
	 * 返回指定的日期（+-指定秒数）之后的值<br>
	 * 整数就是+second秒，负数就是往前推second秒
	 */
	public static Date moveSecond(Date current, int second) {
		Calendar c = getCalendar(current);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}

	// before

	/**
	 * 返回指定日期【前month月】的一个时间对象
	 * 
	 * @param month 前几个月（不区分正负）+5和-5都表示时间往前推5个月
	 */
	public static Date beforeMonth(Date current, int month) {
		return moveMonth(current, -Math.abs(month));
	}

	/**
	 * 返回指定日期【前day天】的一个时间对象
	 * 
	 * @param day 前几天（不区分正负）+5和-5都表示时间往前推5天
	 */
	public static Date beforeDay(Date current, int day) {
		return moveDay(current, -Math.abs(day));
	}

	/**
	 * 返回指定日期【前hour小时】的一个时间对象
	 * 
	 * @param hour 前几小时（不区分正负）+5和-5都表示时间往前推5小时
	 */
	public static Date beforeHour(Date current, int hour) {
		return moveHour(current, -Math.abs(hour));
	}

	/**
	 * 返回指定日期【前minute分钟】的一个时间对象
	 * 
	 * @param minute 前几分钟（不区分正负）+5和-5都表示时间往前推5分钟
	 */
	public static Date beforeMinute(Date current, int minute) {
		return moveMinute(current, -Math.abs(minute));
	}

	/**
	 * 返回指定日期【前second秒】的一个时间对象
	 * 
	 * @param second 前几秒（不区分正负）+5和-5都表示时间往前推5秒
	 */
	public static Date beforeSecond(Date current, int second) {
		return moveSecond(current, -Math.abs(second));
	}

	// after

	/**
	 * 返回指定日期【day个月后】的一个时间对象
	 * 
	 * @param month 多少个月之后（不区分正负）+5和-5都表示时间往前推5个月
	 */
	public static Date afterMonth(Date current, int month) {
		return moveMonth(current, Math.abs(month));
	}

	/**
	 * 返回指定日期【day天后】的一个时间对象
	 * 
	 * @param day 多少天之后（不区分正负）+5和-5都表示时间往前推5天
	 */
	public static Date afterDay(Date current, int day) {
		return moveDay(current, Math.abs(day));
	}

	/**
	 * 返回指定日期【hour小时后】的一个时间对象
	 * 
	 * @param hour 多少小时之后（不区分正负）+5和-5都表示时间往前推5小时
	 */
	public static Date afterHour(Date current, int hour) {
		return moveHour(current, Math.abs(hour));
	}

	/**
	 * 返回指定日期【minute分钟后】的一个时间对象
	 * 
	 * @param minute 多少分钟之后（不区分正负）+5和-5都表示时间往前推5分钟
	 */
	public static Date afterMinute(Date current, int minute) {
		return moveMinute(current, Math.abs(minute));
	}

	/**
	 * 返回指定日期【second秒后】的一个时间对象
	 * 
	 * @param second 多少秒之后（不区分正负）+5和-5都表示时间往前推5秒
	 */
	public static Date afterSecond(Date current, int second) {
		return moveSecond(current, Math.abs(second));
	}

	// ##

	/**
	 * 分钟数值转换成 HHmm 的字符串（建议范围0~1440）<br>
	 * 例如:0->0000、60->0100、90->0130、810->1330、1440->2400
	 */
	public static String minute2HHmm(int minute) {
		int mi = Math.abs(minute);
		int h = mi / 60;
		int m = mi % 60;
		return (h < 10 ? ("0" + h) : h) + ":" + (m < 10 ? ("0" + m) : m);
	}

	/**
	 * 获取相差几小时
	 * 
	 * @param current 当前时间
	 * @param hour +1：一小时后 -1：一小时前
	 * @deprecated 请使用 moveHour() 或 beforeHour() 或 afterHour()
	 */
	public static Date diffHour(Date current, int hour) {
		Calendar c = getCalendar(current);
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	/**
	 * 获取相差几秒
	 * 
	 * @param current 当前时间
	 * @param second +1：一秒后 -1：一秒前
	 * @deprecated 请使用 moveSecond() 或 beforeSecond() 或 afterSecond()
	 */
	public static Date diffSecond(Date current, int second) {
		Calendar c = getCalendar(current);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}

}
