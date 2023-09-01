package com.intecsec.java.basic.datetime;

import com.intecsec.java.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateTest {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println(calendar.get(Calendar.MINUTE));
	}

	public static void getNextMonth() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		for (int i = 0; i < 3; i++) {
			int newMonth = month + i;
			newMonth = newMonth > 12 ? newMonth - 12 : newMonth;
			int newYear = newMonth > 12 ? year + 1 : year;
			String monthStr = String.valueOf(newMonth);
			if(month < 10) {
				monthStr = "0" + monthStr;
			}

			System.out.print(newYear);
			System.out.print(monthStr);
			System.out.println("");
		}
	}

	public static void genDate() {
		try {
			Map<String, String> allMap = new HashMap<>();
			allMap.put("txnTime", "20190917165450");

			String notifyTime = StringUtils.isNotEmpty(allMap.get("txnTime")) ?
					DateUtils.formatDate(DateUtils.parseString(allMap.get("txnTime"), DateUtils.YYYYMMDDHHmmss), DateUtils.YYYY_MM_DD_HH_mm_ss)
					: DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD_HH_mm_ss);

			System.out.println(notifyTime);


			Date notifyTime1 = StringUtils.isNotEmpty(notifyTime) ?
					DateUtils.parseString(notifyTime, DateUtils.YYYY_MM_DD_HH_mm_ss) : new Date();

			System.out.println(notifyTime1);

		} catch (Exception e) {

		}

	}

	public static void nowBefore() {
		long timeStamp = System.currentTimeMillis() / 1000L;
		Date now = DateUtils.converTimestampToDate(timeStamp);
		Date nowAfterTowHour = DateUtils.converTimestampToDate(timeStamp + 7200);
		System.out.println(now);
		System.out.println(nowAfterTowHour);
	}

	public static void testModDate() {
		Date date = new Date();
		System.out.println("Be:" + date);
		System.out.println("Be:" + date.getTime());
		modDate(date);
		System.out.println("Af:" + date);
		System.out.println("Af:" + date.getTime());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("2017-06-14 15:37:46");
	}

	private static void modDate(Date date) {
		/*for(long i = 0; i < 9999999999L ; i++) {
			
		}*/
		date = new Date();
		System.out.println("In:" + date);
		System.out.println("In:" + date.getTime());
	}

	public static void getYYYYMMDD() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		System.out.println(simpleDateFormat.format(new Date()));
	}

	private static void getCal() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int dow = calendar.get(Calendar.DAY_OF_WEEK);
		int dom = calendar.get(Calendar.DAY_OF_MONTH);
		int doy = calendar.get(Calendar.DAY_OF_YEAR);
		System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":"  + second);
		System.out.println("当前周的第：" + dow + "天");
		System.out.println("当前月的第：" + dom + "天");
		System.out.println("当前年的第：" + doy + "天");
	}

	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		System.out.println(result);
		return result;
	}

	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		System.out.println(result);
		return result;
	}
}
