package com.intecsec.java.basic.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeTest {

	public static void main(String[] args) {
		//ofsTime();
		gomsTime();
//		calendar();
		
		
	}
	
	public static void deliveryTime() {
		OrderDTO orderDTO = new OrderDTO();
		try {
			orderDTO.setDeliveryStartTime(convertStringToDate("yyyy-MM-dd HH:mm", "2017-11-16 15:00"));
			orderDTO.setDeliveryEndTime(convertStringToDate("yyyy-MM-dd HH:mm", "2017-11-16 18:00"));
			fillOrderDeliveryTimeDesc(orderDTO);
			System.out.println(orderDTO.getDeliveryTimeDesc());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void calendar() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
		String date = dateFormat.format(calendar.getTime());
		System.out.println(date);
		System.out.println(calendar.getTimeInMillis());
		System.out.println(date.split(" ")[0]);
		System.out.println(date.split(" ")[1]);
		try {
			System.out.println(dateFormat.parse(dateFormat.format(calendar.getTime())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void ofsTime() {
		long now = System.currentTimeMillis();
		int s = -10080; // -10080
		int e = -30; // -120
		System.out.println(s);
		System.out.println(e);
		String pushTimeStart = getSeriesYMDHMS(now, TimeUnit.MINUTES, s);
		String pushTimeEnd = getSeriesYMDHMS(now, TimeUnit.MINUTES, e);
		System.out.println(pushTimeStart);
		System.out.println(pushTimeEnd);
	}
	
	public static void gomsTime() {
		long currentTime = System.currentTimeMillis()/1000;
		System.out.println("currentTimeTimeStamp: " + currentTime);
		System.out.println("currentTime: " + converTimestampToString(currentTime));
		
		String start = "2017-12-02 09:33:48";
		long startTimeStamp = converStringToTimestamp(start) - 60;
		
		String end = "2017-11-19 22:35:29";
		long endTimeStamp = converStringToTimestamp(end);
		
		System.out.println("startTimeStamp: " + startTimeStamp);
		System.out.println("endTimeStamp: " + endTimeStamp);
		
		double sd = (currentTime - startTimeStamp) / 60;
		double ed = (currentTime - endTimeStamp) / 60;
		
		long s = (long) Math.ceil(sd);
		long e = (long) Math.ceil(ed);
		System.out.println(s);
		System.out.println(e);
		
		long startTime = currentTime - s * 60;
		long endTime = currentTime - e * 60; 
		
		String checkTimeStart = converTimestampToString(startTime);
		String checkTimeEnd = converTimestampToString(endTime);
		
		System.out.println(checkTimeStart);
		System.out.println(checkTimeEnd);
	}
	
	// 时间戳转字符串(秒)
    public static String converTimestampToString(Long timeStamp) {
    	String strDate = "";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	strDate = sdf.format(new Date(timeStamp * 1000L));  
    	return strDate;
    }
    
    public static String getSeriesYMDHMS(long time, TimeUnit tu, long num) {
		long addMillis = tu.toMillis(num);
		return getSeriesYMDHMS(time + addMillis);
	}
    
    public static String getSeriesYMDHMS(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date(time));
	}
    
    public static Long converStringToTimestamp(String strDate) {
    	Long timeStamp = 0L;
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
    		Date date=simpleDateFormat.parse(strDate);
    		timeStamp = date.getTime() / 1000L;
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
    	return timeStamp;
    }
    
    public static Date convertStringToDate(String pattern, String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(pattern);
        
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }
    
    public static String getWeek(Date date){
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		} 
		return weeks[week_index];
	}
    
    public static String convertDateToString(String pattern, Date aDate) {
		return getDateTime(pattern, aDate);
	}
    
    public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			// log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
    
    public static void fillOrderDeliveryTimeDesc(OrderDTO orderDTO) {
		if(orderDTO.getDeliveryStartTime() != null && orderDTO.getDeliveryEndTime() != null) {
			String currentDateTime = convertDateToString("yyyy-MM-dd HH:mm", new Date());
			String[] currentDays = currentDateTime.split(" ");
			String currentYMD = currentDays[0];
			String currentHM = currentDays[1];
			String[] currentYMDs = currentYMD.split("-");
			Integer currentM = Integer.parseInt(currentYMDs[1]);
			Integer currentD = Integer.parseInt(currentYMDs[2]);
			
			String startDateTime = convertDateToString("yyyy-MM-dd HH:mm", orderDTO.getDeliveryStartTime());
			String[] startDays = startDateTime.split(" ");
			String startYMD = startDays[0];
			String startHM = startDays[1];
			String[] startYMDs = startYMD.split("-");
			Integer startM = Integer.parseInt(startYMDs[1]);
			Integer startD = Integer.parseInt(startYMDs[2]);
			
			String endDateTime = convertDateToString("yyyy-MM-dd HH:mm", orderDTO.getDeliveryEndTime());
			String[] endDays = endDateTime.split(" ");
			String endYMD = endDays[0];
			String endHM = endDays[1];
			String[] endYMDs = endYMD.split("-");
			Integer endM = Integer.parseInt(endYMDs[1]);
			Integer endD = Integer.parseInt(endYMDs[2]);
			
			// 计算出时间
			String day = startM + "月" + startD + "日";
			String week = "今天";
			if(endM > currentM || (endM == currentM && endD > currentD)) {
				day = endM + "月" + endD + "日";
				week = getWeek(orderDTO.getDeliveryEndTime());
			}
			String timeDesc = day + "(" +week +") " + startHM + "~" + endHM;
			
			orderDTO.setDeliveryTimeDesc(timeDesc);
		}
	}
    
    
}

class OrderDTO {
	/**
	 * 闪电送配送开始时间
	 */
	private Date deliveryStartTime;
	
	/**
	 * 闪电送配送结束时间
	 */
	private Date deliveryEndTime;
	
	/**
	 * 闪电送配送时间描述
	 */
	private String deliveryTimeDesc;

	public Date getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(Date deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public Date getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(Date deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getDeliveryTimeDesc() {
		return deliveryTimeDesc;
	}

	public void setDeliveryTimeDesc(String deliveryTimeDesc) {
		this.deliveryTimeDesc = deliveryTimeDesc;
	}

	@Override
	public String toString() {
		return "OrderDTO [deliveryStartTime=" + deliveryStartTime + ", deliveryEndTime=" + deliveryEndTime
				+ ", deliveryTimeDesc=" + deliveryTimeDesc + "]";
	}
		
}
