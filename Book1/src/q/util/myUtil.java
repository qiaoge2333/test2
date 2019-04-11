package q.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;



public class myUtil {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static Date LocalDate2Date(LocalDate localdate) {
		if(localdate==null) {
			return null;
		}
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = localdate.atStartOfDay(zoneId);
		Date date = Date.from(zonedDateTime.toInstant());
		return date;
	}
	

	
	public static LocalDate Date2LocalDate(Date date) {
		if(date == null)return null;
		Date d = new Date(date.getTime());
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = d.toInstant();
		LocalDate localDate = instant.atZone(zoneId).toLocalDate();
		return localDate;
	}
	
	public static String Number2String(Number number) {
		if(number == null)return "0";
		String str;
		try {
			str = String.valueOf(number);
		}catch (Exception e) {
			return "0";
		}
		return str;
	}
	
	//将String数组转变成String
	public static String ToString(String [] S) {
		String string = "";
		for(String s : S) {
			string +=s;
		}
		return string;
	}
	
	//避免因为字符串为空而导致错误
	public static String getString(String s) {
		if (s == null)return "";
		else return s;
	}
	
	//获取当前日期
	public static Date getDate() {
		Date date = new Date();
		return date;
	}
	
	
	//类型转换double转String
	public static String Double2String(Double d) {
		if (d == null) return "0";
		String str = "";
		try {
			str = String.valueOf(d);
		}catch (Exception e) {
			return "0";
		}
		return str;
		
	}
	//类型转换String转Double
	public static double String2Double(String str) {
		if (str == null) return 0;
		double number = 0;
		try {
			number = Double.parseDouble(str);
		}catch (Exception e) {
			return 0;
		}
		
		return number;
	}
	//类型转换String转Integer
	public static int String2Integer(String str) {
		if(str == null)return 0;
		int number = 0;
		try {
			number = Integer.parseInt(str);
		}catch (Exception e) {
			return 0;
		}
		return number;
	}
	
	public static long String2Long(String str) {
		if(str == null) return 0;
		long number = 0;
		try {
			number = Long.parseLong(str);
		}catch (Exception e) {
			return 0;
		}
		
		return number;
	}
	
	public static String Date2String(Date date) {
		String result = "";
		try {
			result = sdf.format(date);
		} catch (Exception e) {
			return "";
		}
		return result;
	}
	
	public static Date String2Date(String s) {
		Date d = null;
		try {
			d = sdf.parse(s);
		} catch (Exception e) {
			return null;
		}
		return d;
	}
	


}
