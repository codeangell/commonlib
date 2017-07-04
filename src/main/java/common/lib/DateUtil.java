package common.lib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getDate(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String formateDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static String getToday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(new Date());
    }

    public static int getTodayNumber() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE);
    }

    public static String getTime(String time, String oldFormat, String newFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
        try {
            Date date = dateFormat.parse(time);
            return new SimpleDateFormat(newFormat).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTime(long currentTimeMillis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        return dateFormat.format(currentTimeMillis);
    }

    public static String[] getYMD(long timestamp) {
        return parseDate(timestamp, "yyyy-MM-dd").split("-");
    }

    /**
     * 昨天日期
     */
    public static Date yesterdayDate(Date date) {
        return addDays(date, -1);
    }

    /**
     * 增加天数
     */
    public static Date addDays(Date date, double days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, (int) days);
        date = calendar.getTime();
        return date;
    }

    /**
     * 字符串 转日期
     */
    public static Date parseDate(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            return df.parse(date);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串 转日期
     */
    public static Date parseDate(String date, String pattern) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);

            return df.parse(date);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串 转日期
     */
    public static String parseDate(long timestamp, String pattern) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);

            return df.format(new Date(timestamp));

        } catch (Exception e) {
            return null;
        }
    }

    public static long getTimeMills(String timeStr, String pattern) {
        SimpleDateFormat df2 = new SimpleDateFormat(pattern);
        long time = 0;
        try {
            time = df2.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 字符串 转日期
     */
    public static Calendar parseCalendar(String date) {
        Calendar calendar = Calendar.getInstance();
        String[] value = date.split("-");
        if (value.length != 3) {
            return null;
        }
        calendar.set(Calendar.YEAR, Integer.valueOf(value[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(value[1]) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(value[2]));
        return calendar;
    }

    /**
     * @param calendar
     * @return 星期一到星期日 为1~7
     */
    public static int getWeekDayInteger(Calendar calendar) {
        //一周第一天是否为星期天
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        //获取周几
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        //若一周第一天为星期天，则-1
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        return weekDay;
    }

    /**
     * @param calendar
     * @return 星期一到星期日
     */
    public static String getWeekDayString(Calendar calendar) {
        String week = null;
        //获取周几
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (weekDay) {
            case Calendar.MONDAY:
                week = "周一";
                break;
            case Calendar.TUESDAY:
                week = "周二";
                break;
            case Calendar.WEDNESDAY:
                week = "周三";
                break;
            case Calendar.THURSDAY:
                week = "周四";
                break;
            case Calendar.FRIDAY:
                week = "周五";
                break;
            case Calendar.SATURDAY:
                week = "周六";
                break;
            case Calendar.SUNDAY:
                week = "周日";
                break;
        }
        return week;
    }


    /**
     * 计算年龄
     *
     * @param birthday yyyy-MM--dd
     */
    public static int getAge(String birthday) {
        int age = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date birth = df.parse(birthday);
            Date now = new Date();

            age = now.getYear() - birth.getYear();

            //如果今天 月 日小于生日月日  年龄减1
            if (birth.getMonth() > now.getMonth()) {
                age--;
            } else if (birth.getMonth() == now.getMonth() && birth.getDate() > now.getDate()) {
                age--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return age;
    }

    /**
     * 比较两个日期字符串日期大小
     *
     * @return >0 date1新于date2
     * <0 date1旧于date2
     * =0 date1等于date2
     */
    public static int compareDate(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return 0;
    }


    /**
     * 求时间差
     *
     * @param date1
     * @param date2
     * @param format
     * @return 毫秒数
     */
    public static long date1DiffDate2(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            long rt = dt1.getTime() - dt2.getTime();
            return rt;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static boolean isShowTime(long prePostionTime, long currPostionTime, int currPostion) {
        return currPostionTime != 0 && (currPostion == 0 || currPostionTime - prePostionTime > 120001);
    }

    /**
     * 判断两个时间是否处于不同分钟
     *
     * @param date1
     * @param date2
     * @param format
     * @return
     */
    public static boolean dateDiffMinuteDate(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            return Long.parseLong(showFormatTime(dt1.getTime(), "yyyyMMddHHmm")) - Long.parseLong(showFormatTime(dt2.getTime(), "yyyyMMddHHmm")) != 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    /**
     * 是否是今天
     *
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return df.format(new Date()).equals(df.format(new Date(time)));
    }

    /**
     * 按规律显示聊天时间
     *
     * @param time
     * @return
     */
    public static String showCharTime(long time) {
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        if (isToday(time)) {
            return "" + showFormatTime(time, "HH:mm");
        } else if (getToday().equals(df1.format(addDays(new Date(time), 1)))) {
            return "昨天 " + showFormatTime(time, "HH:mm");
        }
//        else if(getToday().equals(df1.format(addDays(new Date(time), 2)))) {
//            return "前天 " + showFormatTime(time, "HH:mm");
//        }
        else if (Integer.valueOf(showFormatTime(new Date().getTime(), "yyyy")) - Integer.valueOf(showFormatTime(time, "yyyy")) > 0) {
            return showFormatTime(time, "yyyy-MM-dd HH:mm");
        } else {
            return showFormatTime(time, "MM-dd HH:mm");
        }
    }

    /**
     * 按规律显示聊天时间
     *
     * @param timeStr   传入字符串时间
     * @param formatStr 所传时间的格式
     * @return
     */
    public static String showCharTime(String timeStr, String formatStr) {
        SimpleDateFormat df2 = new SimpleDateFormat(formatStr);
        long time = 0;
        try {
            time = df2.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return showCharTime(time);

    }

    /**
     * 返回格式化后时间
     *
     * @return
     */
    public static String showFormatTime(long time, String formatStr) {
        SimpleDateFormat df = new SimpleDateFormat(formatStr);
        return df.format(new Date(time));
    }


    public static final TimeZone tz = TimeZone.getTimeZone("GMT+8:00");
    public static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final long ONEDAY = 86400000;
    public static final int SHOW_TYPE_SIMPLE = 0;
    public static final int SHOW_TYPE_COMPLEX = 1;
    public static final int SHOW_TYPE_ALL = 2;
    public static final int SHOW_TYPE_CALL_LOG = 3;
    public static final int SHOW_TYPE_CALL_DETAIL = 4;

    /**
     * 获取当前当天日期的毫秒数 2012-03-21的毫秒数
     *
     * @return
     */
    public static long getCurrentDayTime() {
        Date d = new Date(System.currentTimeMillis());
        String formatDate = yearFormat.format(d);
        try {
            return (yearFormat.parse(formatDate)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getDateMills(int year, int month, int day) {
        //Date d = new Date(year, month, day);
        // 1960 4 22
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(year, month, day);
        TimeZone tz = TimeZone.getDefault();
        calendar.setTimeZone(tz);
        return calendar.getTimeInMillis();
    }

    public static String formatDate(long time) {
        int day = (int) (time / 86400000L);
        int hour = (int) ((time % 86400000L) / 3600000L);
        int minute = (int) (((time % 86400000L) % 3600000L) / 60000L);
        return day + "天" + hour + "时" + minute + "分";
    }


    public static String getWeek(int week) {
        String str = "";
        switch (week) {
            case 0:
                str = "周日";
                break;
            case 1:
                str = "周一";
                break;
            case 2:
                str = "周二";
                break;
            case 3:
                str = "周三";
                break;
            case 4:
                str = "周四";
                break;
            case 5:
                str = "周五";
                break;
            case 6:
                str = "周六";
                break;
        }
        return str;
    }

    // 获取当前时间所在年的周数
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    // 获取当前时间所在年的最大周数
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    // 获取某年的第几周的开始日期
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    // 获取某年的第几周的结束日期
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    // 获取当前时间所在周的开始日期
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    // 获取当前时间所在周的结束日期
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 获取当前年份------------------------------------------------------------------------------------
     *
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;// +1是因为返回来的值并不是代表月份，而是对应于Calendar.MAY常数的值，
        // Calendar在月份上的常数值从Calendar.JANUARY开始是0，到Calendar.DECEMBER的11
    }

    /**
     * 获取当前的时间为该月的第几天
     *
     * @return
     */
    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前的时间为该周的第几天
     *
     * @return
     */
    public static int getWeekDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取当前时间为该天的多少点
     *
     * @return
     */
    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        // Calendar calendar = Calendar.getInstance();
        // System.out.println(calendar.get(Calendar.HOUR_OF_DAY)); // 24小时制
        // System.out.println(calendar.get(Calendar.HOUR)); // 12小时制
    }

    /**
     * 获取当前的分钟时间
     *
     * @return
     */
    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 通过获得年份和月份确定该月的日期分布
     *
     * @param year
     * @param month
     * @return
     */
    public static int[][] getMonthNumFromDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);// -1是因为赋的值并不是代表月份，而是对应于Calendar.MAY常数的值，

        int days[][] = new int[6][7];// 存储该月的日期分布

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);// 获得该月的第一天位于周几（需要注意的是，一周的第一天为周日，值为1）

        int monthDaysNum = getMonthDaysNum(year, month);// 获得该月的天数
        // 获得上个月的天数
        int lastMonthDaysNum = getLastMonthDaysNum(year, month);

        // 填充本月的日期
        int dayNum = 1;
        int lastDayNum = 1;
        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < days[i].length; j++) {
                if (i == 0 && j < firstDayOfWeek - 1) {// 填充上个月的剩余部分
                    days[i][j] = lastMonthDaysNum - firstDayOfWeek + 2 + j;
                } else if (dayNum <= monthDaysNum) {// 填充本月
                    days[i][j] = dayNum++;
                } else {// 填充下个月的未来部分
                    days[i][j] = lastDayNum++;
                }
            }
        }

        return days;

    }

    /**
     * 根据年数以及月份数获得上个月的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getLastMonthDaysNum(int year, int month) {

        int lastMonthDaysNum = 0;

        if (month == 1) {
            lastMonthDaysNum = getMonthDaysNum(year - 1, 12);
        } else {
            lastMonthDaysNum = getMonthDaysNum(year, month - 1);
        }
        return lastMonthDaysNum;

    }

    /**
     * 根据年数以及月份数获得该月的天数
     *
     * @param year
     * @param month
     * @return 若返回为负一，这说明输入的年数和月数不符合规格
     */
    public static int getMonthDaysNum(int year, int month) {

        if (year < 0 || month <= 0 || month > 12) {// 对于年份与月份进行简单判断
            return -1;
        }

        int[] array = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// 一年中，每个月份的天数

        if (month != 2) {
            return array[month - 1];
        } else {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {// 闰年判断
                return 29;
            } else {
                return 28;
            }
        }

    }

    public static int getWeek(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c.get(Calendar.DAY_OF_WEEK)-1;
    }

    public static String getHMSByMin(int totalMin) {
        return (totalMin / (60 * 7)) + "天" + (totalMin % (60 * 7)) / 60 + "时" + (totalMin % (60 * 7)) % 60 + "分";
    }
}
