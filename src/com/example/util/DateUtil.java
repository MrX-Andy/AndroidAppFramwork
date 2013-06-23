package com.example.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 
 * 包含日期类型及对象处理的常用方法工具类
 * 
 * @author <a href="mailto:jiaoshi0531@163.com">焦石</a>
 * @since 1.6
 * @version 1.0.0 2011-3-30
 */
public class DateUtil {
	
	/**
     * Number of milliseconds in a standard second.
     */
    public static final long MILLIS_PER_SECOND = 1000;
    /**
     * Number of milliseconds in a standard minute.
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    /**
     * Number of milliseconds in a standard hour.
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    /**
     * Number of milliseconds in a standard day.
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
    
    /**
     * ISO8601 formatter for date-time without time zone.
     * The format used is <tt>yyyy-MM-dd'T'HH:mm:ss</tt>.
     */
    public static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    
    /**
     * ISO8601 short formatter for date-time without time zone.
     * The format used is <tt>yyyy-MM-dd HH:mm:ss</tt>.
     */
    public static final String ISO_DATETIME_FORMAT_SORT = "yyyy-MM-dd HH:mm:ss";

    /**
     * ISO8601 formatter for date-time with time zone.
     * The format used is <tt>yyyy-MM-dd'T'HH:mm:ssZZ</tt>.
     */
    public static final String ISO_DATETIME_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";

    /**
     * ISO8601 formatter for date without time zone.
     * The format used is <tt>yyyy-MM-dd</tt>.
     */
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    
    /**
     * ISO8601 formatter for date without time zone.
     * The format used is <tt>yy/MM/dd</tt>.
     */
    public static final String ISO_DATE_PAY_FORMAT = "yy/MM/dd";

    /**
     * ISO8601-like formatter for date with time zone.
     * The format used is <tt>yyyy-MM-ddZZ</tt>.
     * This pattern does not comply with the formal ISO8601 specification
     * as the standard does not allow a time zone  without a time.
     */
    public static final String ISO_DATE_TIME_ZONE_FORMAT = "yyyy-MM-ddZZ";
    
    /**
     * ISO8601 short formatter for date-time without time zone.
     * The format used is <tt>yyyy-MM-dd hh-mm-ss</tt>.
     */
    public static final String ISO_DATETIME_FORMAT_SORT2 = "yyyy-MM-dd hh-mm-ss";

    /**
     * ISO8601 formatter for time without time zone.
     * The format used is <tt>'T'HH:mm:ss</tt>.
     */
    public static final String ISO_TIME_FORMAT = "'T'HH:mm:ss";
    


    /**
     * ISO8601 formatter for time with time zone.
     * The format used is <tt>'T'HH:mm:ssZZ</tt>.
     */
    public static final String ISO_TIME_TIME_ZONE_FORMAT = "'T'HH:mm:ssZZ";

    /**
     * ISO8601-like formatter for time without time zone.
     * The format used is <tt>HH:mm:ss</tt>.
     * This pattern does not comply with the formal ISO8601 specification
     * as the standard requires the 'T' prefix for times.
     */
    public static final String ISO_TIME_NO_T_FORMAT = "HH:mm:ss";
    
    /**
     * ISO8601-like formatter for time without time zone.
     * The format used is <tt>HH:mm:ss</tt>.
     * This pattern does not comply with the formal ISO8601 specification
     * as the standard requires the 'T' prefix for times.
     */
    public static final String ISO_TIME_NO_T_AND_SS_FORMAT = "HH:mm";

    /**
     * ISO8601-like formatter for time with time zone.
     * The format used is <tt>HH:mm:ssZZ</tt>.
     * This pattern does not comply with the formal ISO8601 specification
     * as the standard requires the 'T' prefix for times.
     */
    public static final String ISO_TIME_NO_T_TIME_ZONE_FORMAT = "HH:mm:ssZZ";

    /**
     * SMTP (and probably other) date headers.
     * The format used is <tt>EEE, dd MMM yyyy HH:mm:ss Z</tt> in US locale.
     */
    public static final String SMTP_DATETIME_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";
    
    private static ConcurrentHashMap<String, SimpleDateFormat> cInstanceCache = new ConcurrentHashMap<String, SimpleDateFormat>();
    
    private DateUtil() {
        super();
    }
   
    
    /**
     * <p>Checks if two date objects are on the same day ignoring time.</p>
     *
     * <p>28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true.
     * 28 Mar 2002 13:45 and 12 Mar 2002 13:45 would return false.
     * </p>
     * 
     * @param date1  the first date, not altered, not null
     * @param date2  the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     * @since 2.1
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * <p>Checks if two calendar objects are on the same day ignoring time.</p>
     *
     * <p>28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true.
     * 28 Mar 2002 13:45 and 12 Mar 2002 13:45 would return false.
     * </p>
     * 
     * @param cal1  the first calendar, not altered, not null
     * @param cal2  the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     * @since 2.1
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
    
    /**
     * Adds a number of years to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of months to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of weeks to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of days to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of hours to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of minutes to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of seconds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a number of milliseconds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds to a date returning a new object.
     * The original date object is unchanged.
     *
     * @param date  the date, not null
     * @param calendarField  the calendar field to add to
     * @param amount  the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
    
    /**
     * <p>Formats a date/time into a specific pattern.</p>
     * 
     * @param millis  the date to format expressed in milliseconds
     * @param pattern  the pattern to use to format the date
     * @return the formatted date
     */
    public static String format(long millis, String pattern) {
        return format(new Date(millis), pattern, null);
    }

    /**
     * <p>Formats a date/time into a specific pattern.</p>
     * 
     * @param date  the date to format
     * @param pattern  the pattern to use to format the date
     * @return the formatted date
     */
    public static String format(Date date, String pattern) {
        return format(date, pattern, null);
    }
    


    /**
     * <p>Formats a date/time into a specific pattern in a locale.</p>
     * 
     * @param date  the date to format
     * @param pattern  the pattern to use to format the date
     * @param locale  the locale to use, may be <code>null</code>
     * @return the formatted date
     */
    public static String format(Date date, String pattern, Locale locale) {
    	if(date == null){
    		return "";
    	}
    	
    	SimpleDateFormat format = null;
    	
    	if(locale == null){
            format = cInstanceCache.get(pattern);
            if(format == null){
                format = new SimpleDateFormat(pattern);
                cInstanceCache.put(pattern, format);
            }
    	}else{
            format = cInstanceCache.get(pattern + locale);
            if(format == null){
                format = new SimpleDateFormat(pattern,locale);
                cInstanceCache.put(pattern + locale, format);
            }
    	}
    	
        return format.format(date);
    }
    
    /**
     * <p>Parses a string representing a date by trying a variety of different parsers.</p>
     * 
     * <p>The parse will try each parse pattern in turn.
     * A parse is only deemed sucessful if it parses the whole of the input string.
     * If no parse patterns match, a ParseException is thrown.</p>
     * 
     * @param str  the date to parse, not null
     * @param parsePatterns  the date format patterns to use, see SimpleDateFormat, not null
     * @return the parsed date
     * @throws IllegalArgumentException if the date string or pattern array is null
     * @throws ParseException if none of the date patterns were suitable
     */
    public static Date parseDate(String str, String[] parsePatterns) throws ParseException {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        
        SimpleDateFormat parser = null;
        ParsePosition pos = new ParsePosition(0);
        for (int i = 0; i < parsePatterns.length; i++) {
            if (i == 0) {
                parser = new SimpleDateFormat(parsePatterns[0]);
            } else {
                parser.applyPattern(parsePatterns[i]);
            }
            pos.setIndex(0);//重新设置文本解析起始点
            Date date = parser.parse(str, pos);
            if (date != null && pos.getIndex() == str.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }
    
}
