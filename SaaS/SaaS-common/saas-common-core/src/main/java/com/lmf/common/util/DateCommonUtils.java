/**
 *
 */
package com.saas.common.util;

import org.apache.commons.lang3.time.DateUtils;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author quanzhi
 */
public class DateCommonUtils {
    public static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy_MM_dd");
    public static SimpleDateFormat yyyyMMdd_dash = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat MMdd_dash = new SimpleDateFormat("MM-dd");
    public static SimpleDateFormat MMdd = new SimpleDateFormat("MM.dd");
    public static SimpleDateFormat MM = new SimpleDateFormat("MM");
    public static SimpleDateFormat MM_CN = new SimpleDateFormat("MM月");
    public static SimpleDateFormat MMdd_CN = new SimpleDateFormat("MM月dd日");
    public static SimpleDateFormat yyyyMMddHHmm_dash = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat yyyyMMddHHmmss_dash = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat yyMMddHHmmss = new SimpleDateFormat("yyMMddHHmmss");
    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat yyMMddHHmm = new SimpleDateFormat("yyMMddHHmm");
    public static SimpleDateFormat yyyyMMddHH_dash = new SimpleDateFormat("yyyy-MM-dd HH");
    public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
    public static SimpleDateFormat yyyy_MM_ddHHmmss = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
    public static SimpleDateFormat MMddyyyy_dash = new SimpleDateFormat("MM/dd/yyyy");
    public static SimpleDateFormat yyyyMM_dash = new SimpleDateFormat("yyyy-MM");
    private static Calendar calendar= Calendar.getInstance();


    public static Date getToDay() {
        Date now = new Date();
        String nowStr = yyyy_MM_dd.format(now);
        try {
            Date today = yyyy_MM_dd.parse(nowStr);
            return today;
        } catch (Exception e) {
            e.printStackTrace();
            return now;
        }

    }

    public static String getToDayStr() {
        return yyyyMMdd_dash.format(getToDay());
    }

    public static Date getYesterday() {
        return addDays(getToDay(), -1);
    }

    public static String getYesterdayStr() {
        return yyyyMMdd_dash.format(getYesterday());
    }

    /**
     * 获取本月第一天
     *
     * @return
     */
    public static Date getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getToDay());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取本月第一天
     *
     * @return
     */
    public static Date getFirstDayOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getToDay());
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static String getHttpDateMonthParam(String dateStr, String formatStr, int plusNum, String toFormat) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        SimpleDateFormat to = new SimpleDateFormat(toFormat);
        try {
            Date date = format.parse(dateStr);
            return URLEncoder.encode(to.format(DateUtils.addMonths(date, plusNum)), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String format(Date date, SimpleDateFormat format) {
        try {
            if (date == null) {
                return null;
            }
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parse(String timeStr, SimpleDateFormat format) {
        try {
            return format.parse(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期加天数
     *
     * @param startTime
     * @param days
     * @return
     */
    public static Date addDays(Date startTime, Integer days) {
        if (startTime == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(startTime);
            c.add(Calendar.DATE, days);
            return c.getTime();
        }
    }

    /**
     * 生成当前时间字符串
     *
     * @return
     */
    public static String getTimeStr() {
        String time = format(new Date(), DateCommonUtils.yyyyMMddHHmmss_dash);
        return time;
    }

    /**
     * 获取下个月最后一天
     *
     * @param dateStr -本月第一天
     * @return
     * @throws Exception
     */
    public static Date getNextFirstDayByMonth(String dateStr) {
        Date date = getFirstDayByMonth(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        date = calendar.getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, -1);
        date = c.getTime();
        return date;
    }

    public static Date getFirstDayByMonth(String dateStr) {
        dateStr = dateStr + "-01";
        return parse(dateStr, new SimpleDateFormat("yyyy-MM-dd"));
    }

    /*
  输入日期字符串比如201703，返回当月第一天的Date
  */
    public static Date getMinDateMonth(String month){
        try {
            Date nowDate=yyyyMM_dash.parse(month);
            calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    输入日期字符串，返回当月最后一天的Date
    */
    public static Date getMaxDateMonth(String month){
        try {
            Date nowDate=yyyyMM_dash.parse(month);
            calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
