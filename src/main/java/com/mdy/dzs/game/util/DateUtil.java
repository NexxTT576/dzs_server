package com.mdy.dzs.game.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	// 默认显示日期的格式
	public static final String DATAFORMAT_STR = "yyyy-MM-dd";

	// 默认显示日期的格式
	public static final String YYYY_MM_DATAFORMAT_STR = "yyyy-MM";

	// 默认显示日期时间的格式
	public static final String DATATIMEF_STR = "yyyy-MM-dd HH:mm:ss";

	// 默认显示简体中文日期的格式
	public static final String ZHCN_DATAFORMAT_STR = "yyyy年MM月dd日";

	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_DATATIMEF_STR = "yyyy年MM月dd日HH时mm分ss秒";

	// 默认显示简体中文日期时间的格式
	public static final String UNION_DATATIMEF_STR = "yyyyMMddHHmmss";

	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_DATATIMEF_STR_4yMMddHHmm = "yyyy年MM月dd日HH时mm分";

	public static DateFormat dateFormat = null;

	public static DateFormat dateTimeFormat = null;

	private static DateFormat zhcnDateFormat = null;

	private static DateFormat zhcnDateTimeFormat = null;
	static {
		dateFormat = new SimpleDateFormat(DATAFORMAT_STR);
		dateTimeFormat = new SimpleDateFormat(DATATIMEF_STR);
		zhcnDateFormat = new SimpleDateFormat(ZHCN_DATAFORMAT_STR);
		zhcnDateTimeFormat = new SimpleDateFormat(ZHCN_DATATIMEF_STR);
	}

	/**
	 * 获取Date中的分钟
	 * 
	 * @param d
	 * @return
	 */
	public static int getMin(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 获取Date中的小时(24小时)
	 * 
	 * @param d
	 * @return
	 */
	public static int getHour(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取Date中的秒
	 * 
	 * @param d
	 * @return
	 */
	public static int getSecond(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.SECOND);
	}

	/**
	 * 获取星期×的日
	 * 
	 * @param d
	 * @return
	 */
	public static int getWeekDay(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取xxxx-xx-xx的日
	 * 
	 * @param d
	 * @return
	 */
	public static int getDay(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取月份，1-12月
	 * 
	 * @param d
	 * @return
	 */
	public static int getMonth(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取19xx,20xx形式的年
	 * 
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.YEAR);
	}

	/**
	 * 获取日期d的days天后的一个Date
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static Date getInternalDateByDay(Date d, int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.DATE, days);
		return now.getTime();
	}

	/**
	 * 判断当前日期是否在几天范围之内
	 * 
	 * @param d
	 * @param days
	 * @return
	 * @throws ParseException
	 * @throws @throws        Exception
	 */
	public static boolean isInDays(Date d, int days) {
		boolean isin = false;
		if (d == null) {
			return false;
		}
		Date dt1;
		Date dt2;
		dt1 = d;
		dt2 = GetNowDateTime();
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(dt1);
		now.add(Calendar.DATE, days);
		Date sdate = now.getTime();
		if (dt2.after(dt1) && dt2.before(sdate)) {
			isin = true;
		}
		return isin;
	}

	/**
	 * 获取n分钟后的一个Date
	 * 
	 * @param d
	 * @param minutes
	 * @return
	 */
	public static Date getInternalDateByMintues(Date d, int minutes) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MINUTE, minutes);
		return now.getTime();
	}

	/**
	 * 获取s秒后的一个Date
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static Date getInternalDateBySecond(Date d, int seconds) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.SECOND, seconds);
		return now.getTime();
	}

	/**
	 * 获取某个时间的0:0:0
	 * 
	 * @param d
	 * @return
	 */
	public static Date getStartTimeOfDay(Date d) {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		cal.setTime(d);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		return cal.getTime();
	}

	/**
	 * 判断是否是同一天
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSameDay(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return false;
		}
		if (getYear(d1) == getYear(d2) && getMonth(d1) == getMonth(d2) && getDay(d1) == getDay(d2)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是同一月
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSameMonth(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return false;
		}
		if (getYear(d1) == getYear(d2) && getMonth(d1) == getMonth(d2)) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是昨天
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isYesterday(Date a) {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		Date today = c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		return format.format(today).equals(format.format(a));
	}

	/**
	 * 向后推一天
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 是否是今天
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isToday(Date a) {
		if (a == null)
			return false;
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		return format.format(today).equals(format.format(a));

	}

	/**
	 * 是否是今天
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isToday(String a) {
		if (a == null)
			return false;
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		return format.format(today).equals(format.format(a));

	}

	public static void main(String[] args) {
		// Date date = null;
		Date date = GetNowDateTime();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, 24);// 24小时制
		date = cal.getTime();
		Date a = null;
		try {
			a = dateTimeFormat.parse(getStringByDate(date));
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(a);
	}

	/**
	 * 获取当天00:00:00的时间
	 * 
	 * @return
	 */
	public static Date getNowDayTime() {

		String getNowDayTime = dateFormat.format(new Date());
		getNowDayTime += " 00:00:00";
		Date nowDay = null;
		try {
			nowDay = dateTimeFormat.parse(getNowDayTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nowDay;
	}

	/**
	 * 获取当天YYYY-mm-DD的时间
	 * 
	 * @return
	 */
	public static Date getNowDay() {
		String nowDay = dateFormat.format(new Date());
		try {
			return dateFormat.parse(nowDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 获取格式化后的当前时间的前？天或后？天的时间（yyyy-MM-dd 00:30:00）
	 * 
	 * @param args
	 */
	public static Date getBackOrForwardDay(Date d, int day) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.DATE, day);

		String backOrForwardDay = dateFormat.format(now.getTime());
		backOrForwardDay += " 00:30:00";
		Date bfd = null;
		try {
			bfd = dateTimeFormat.parse(backOrForwardDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bfd;
	}

	/**
	 * 获取格式化后的时间（yyyy-MM-dd hh:MM:ss）
	 * 
	 * @param args
	 */
	public static String getDateString(Date d) {
		String str = dateTimeFormat.format(d);
		return str;
	}

	/**
	 * 获取当天YYYY-mm-DD 19:00:00的时间
	 * 
	 * @return
	 */
	public static Date getNowDay7Hour() {

		String backOrForwardDay = dateFormat.format(new Date().getTime());
		backOrForwardDay += " 19:00:00";
		Date bfd = null;
		try {
			bfd = dateTimeFormat.parse(backOrForwardDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bfd;
	}

	/**
	 * 获取当天YYYY-mm-DD 20:00:00的时间
	 * 
	 * @return
	 */
	public static Date getNowDay22Hour() {

		String backOrForwardDay = dateFormat.format(new Date().getTime());
		backOrForwardDay += " 20:00:00";
		Date bfd = null;
		try {
			bfd = dateTimeFormat.parse(backOrForwardDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bfd;
	}

	/**
	 * 获取某个时间的23:59:59
	 * 
	 * @param d
	 * @return
	 */
	public static Date getEndTimeOfDay(Date d) {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		cal.setTime(d);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		return cal.getTime();
	}

	/**
	 * 获取日期与当前日期相差几天
	 */
	public static int getDateDiff(Date d) {
		Date todayEndTime = getEndTimeOfDay(new Date());
		int day = (int) ((todayEndTime.getTime() - d.getTime()) / (1000 * 24 * 60 * 60));
		return day;
	}

	/**
	 * 支持00:00格式
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isNowInHourSecond(String start, String end) {
		Date now = new Date();
		String backOrForwardDay = dateFormat.format(now.getTime());
		String startStr = backOrForwardDay + " " + start + ":00";
		String endStr = backOrForwardDay + " " + end + ":00";
		boolean res = false;
		try {
			Date startT = dateTimeFormat.parse(startStr);
			Date endT = dateTimeFormat.parse(endStr);
			if (now.getTime() >= startT.getTime() && now.getTime() < endT.getTime()) {
				res = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static long getLongTime(String date) {
		Date time = null;
		try {
			time = dateTimeFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return time.getTime();
	}

	/**
	 * 现在在日期范围内 支持yyyy-MM-dd格式
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isTodayInDateInterval(String start, String end) {
		boolean res = false;
		Date now = new Date();
		String startStr = start + " 00:00:00";
		String endStr = end + " 23:59:00";
		try {
			Date startT = dateTimeFormat.parse(startStr);
			Date endT = dateTimeFormat.parse(endStr);
			if (now.getTime() >= startT.getTime() && now.getTime() <= endT.getTime()) {
				res = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * 现在在日期时间范围内 支持yyyy-MM-dd HH:MM:SS格式
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isTodayInDateInterval_(String start, String end) {
		return isTodayInDateInterval_(start, end, 0);
	}

	public static boolean isTodayInDateInterval_(String start, String end, int interval) {
		boolean res = false;
		Date now = new Date();
		try {
			SimpleDateFormat dateTimeFormat_ = new SimpleDateFormat(DATATIMEF_STR);
			Date startT = dateTimeFormat_.parse(start);
			Date endT = dateTimeFormat_.parse(end);
			if (now.getTime() >= startT.getTime() + interval && now.getTime() <= endT.getTime() - interval) {
				res = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * 现在在两个时间点范围内YYYY-mm-DD HH:MM:SS
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isNowInTimePot(String start, String end) {
		boolean res = false;
		Date now = new Date();
		try {
			Date startT = dateTimeFormat.parse(start);
			Date endT = dateTimeFormat.parse(end);
			if (now.getTime() >= startT.getTime() && now.getTime() <= endT.getTime()) {
				res = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Date转化成String类型
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringByDate(Date date) {
		String str = dateTimeFormat.format(date);
		return str;
	}

	/**
	 * 字符串转化成Date类型 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	// dateTimeFormat
	public static Date getDateTimeByString(String date) {
		Date bfd = null;
		try {
			bfd = dateTimeFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bfd;
	}

	/**
	 * 获取格式化后的当前时间yyyy-MM-dd 00:00:00
	 * 
	 * @param args
	 */
	public static Date getDayTime(String str) {
		Date bfd = null;
		try {
			bfd = dateTimeFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bfd;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return nowDate
	 */
	public static Date GetNowDateTime() {

		return new Date();

	}

	/**
	 * 判断是否超过24小时
	 * 
	 * @param date1
	 * @param date2
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean judgmentDate(Date date1) {
		Date start = null;
		Date end = null;
		start = date1;
		end = GetNowDateTime();

		long cha = end.getTime() - start.getTime();
		if (cha < 0) {
			return false;
		}
		double result = cha * 1.0 / (1000 * 60 * 60);
		if (result <= 24) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 时间戳转换为date
	 * 
	 * @param timestampString
	 * @return
	 */
	public static Date TimeStamp2Date(int timestampString) {
		Long timestamp = (long) (timestampString * 1000);
		Date date = new java.util.Date(timestamp);
		return date;
	}

	public static boolean compare_date(String DATE1, String DATE2) {
		boolean compare = false;
		try {
			Date dt1 = dateTimeFormat.parse(DATE1);
			Date dt2 = dateTimeFormat.parse(DATE2);
			if (dt1.getTime() == dt2.getTime()) {
				compare = true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return compare;
	}

	// 比较是否相等
	public static boolean compare_dateIsEqual(Date DATE1, Date DATE2) {
		boolean compare = false;
		try {
			Date dt1 = dateTimeFormat.parse(dateTimeFormat.format(DATE1));
			Date dt2 = dateTimeFormat.parse(dateTimeFormat.format(DATE2));
			if (dt1.getTime() == dt2.getTime()) {
				compare = true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return compare;
	}

	// 比较时间大小 参数为日期
	public static boolean compare_date(Date date1, Date date2) {
		boolean compare = false;
		try {
			if (date1.getTime() > date2.getTime()) {
				compare = true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return compare;
	}

	/**
	 * 判断2个日志之间相差多久
	 * 
	 * @param starTime 开始时间
	 * @param endTime  结束时间
	 * @param flag     0天、1时、2秒、3分
	 * @return 时间差
	 */
	public static long howLongBetween2Time(String starTime, String endTime, int flag) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数

		SimpleDateFormat sd = new SimpleDateFormat(DATATIMEF_STR);
		try {
			long diff = sd.parse(endTime).getTime() - sd.parse(starTime).getTime();
			long day = diff / nd;// 计算差多少天
			long hour = diff % nd / nh;// 计算差多少小时
			long min = diff % nd % nh / nm;// 计算差多少分钟
			long sec = diff % nd % nh % nm / ns;// 计算差多少秒//输出结果
			switch (flag) {
			case 0:
				return day;
			case 1:
				return hour;
			case 2:
				return sec;
			case 3:
				return min;
			case 4:
				return diff / 1000;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static long howLongBetween2Time(Date starTime, Date endTime, int flag) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数

		SimpleDateFormat sd = new SimpleDateFormat(DATATIMEF_STR);
		long diff = endTime.getTime() - starTime.getTime();
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh;// 计算差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟
		long sec = diff % nd % nh % nm / ns;// 计算差多少秒//输出结果
		switch (flag) {
		case 0:
			return day;
		case 1:
			return hour;
		case 2:
			return sec;
		case 3:
			return min;
		case 4:
			return diff / 1000;
		}
		return 0;
	}

	/**
	 * 输入时间+上小时返回新时间
	 * 
	 * @param time    输入时间
	 * @param addtime 增加小时数
	 * @return 新时间
	 */
	public static String addTime(String time, int addtime) {
		// 引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
		// 量day格式一致
		Date date = null;
		try {
			date = dateTimeFormat.parse(time);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null) {
			return "";
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, addtime);// 24小时制
		date = cal.getTime();
		return dateTimeFormat.format(date);
	}

	/**
	 * 输入时间+上小时返回新时间
	 * 
	 * @param time    输入时间
	 * @param addtime 增加小时数
	 * @return 新时间
	 */
	public static Date addTime(Date time, int addtime) {
		// 引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
		// 量day格式一致
		Date date = null;
		date = time;
		if (date == null) {
			return time;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, addtime);// 24小时制
		date = cal.getTime();
		return date;
	}

	/**
	 * 输入时间+上小时返回新时间+分钟
	 * 
	 * @param time    输入时间
	 * @param addtime 增加小时数
	 * @return 新时间
	 */
	public static Date addTime(Date time, int addtime, int addMinutes) {
		// 引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
		// 量day格式一致
		Date date = null;
		date = time;
		if (date == null) {
			return time;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, addtime);// 24小时制
		cal.add(Calendar.MINUTE, addMinutes);
		date = cal.getTime();
		return date;
	}

	/**
	 * 获取本周日23:59:59
	 * 
	 * @return
	 */
	public static Date getCurrentSunday() {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		cal.add(Calendar.DATE, -day_of_week + 7);
		String sdate = dateFormat.format(cal.getTime()) + " 23:59:59";
		return getDayTime(dateTimeFormat.format(DateUtil.getDayTime(sdate)));
	}

	/**
	 * 获取本周一
	 * 
	 * @return
	 */
	public static Date getCurrentMonDay() {
		Date sunday = getCurrentSunday();
		Date monday = getBackOrForwardDay(sunday, -6);
		String sdate = dateFormat.format(monday.getTime()) + " 00:00:00";
		return getDayTime(dateTimeFormat.format(DateUtil.getDayTime(sdate)));
	}

	/**
	 * 获取本周日00:00:00
	 * 
	 * @return
	 */
	public static Date getCurrentSundayZero() {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		cal.add(Calendar.DATE, -day_of_week + 7);
		String sdate = dateFormat.format(cal.getTime()) + " 00:00:00";
		return getDayTime(dateTimeFormat.format(DateUtil.getDayTime(sdate)));
	}

	// unix时间转date
	public static String TimeStamp2Date(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = dateTimeFormat.format(new java.util.Date(timestamp));
		return date;
	}
}
