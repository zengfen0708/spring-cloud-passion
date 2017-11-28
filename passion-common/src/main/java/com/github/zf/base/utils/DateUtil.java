package com.github.zf.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zengfen
 * @date 2017/11/28
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * yyyy-MM-dd
     */
    public final SimpleDateFormat CHN_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * yyyy-MM-dd HH:mm
     */
    public final SimpleDateFormat CHN_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final SimpleDateFormat CHN_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static Date stringToDate(String date, String ftm) {
        if(StringUtils.isEmpty(ftm)){
            ftm="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(ftm);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            logger.error("DateUtils:", e);
        }
        return null;
    }


    public static String forDate(Date date, String dateForMat) {
        SimpleDateFormat sdf = null;
        if (StringUtils.isEmpty(dateForMat)) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            sdf = new SimpleDateFormat(dateForMat);
        }
        String dataFor=null;
        try {
            dataFor=sdf.format(date);
        }catch (Exception e){
            logger.error("转换时间异常:",e);
        }
        return dataFor;
    }

    /**
     * 计算相差时间分钟
     * @param date
     * @param differValue
     * @return
     */
    public static Date differToDate(Date date,Integer differValue){
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(date);
        nowTime.add(Calendar.MINUTE, differValue);//30分钟前的时间
        return   nowTime.getTime();
    }

    /**
     * 相差多少天
     * @param date
     * @param day
     * @return
     */
    public static Date differToDateDay(Date date,Integer day){
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(date);
        nowTime.add(Calendar.DATE, day);//天
        return   nowTime.getTime();
    }

    public static Date  differToDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return   calendar.getTime();
    }

    public static Date  differToDayOver(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return   calendar.getTime();
    }
}
