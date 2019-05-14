/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author mwn
 */
public class ConverterUtils {

    public static Date convertToDateFromString(String startDate) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");
        java.util.Date date = simpleDateFormat.parse(startDate);
        Date sqlStartDate = new Date(date.getTime());
        return sqlStartDate;
    }

    public static String converToStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");
        return simpleDateFormat.format(date);
    }

    public static Date convertFromJsonDateToDate(String json) {
        Date dt = null;
        try {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            df.setTimeZone(tz);
            dt = df.parse(json);

        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return truncateTime(dt);
    }

    public static Date truncateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(convertFromJsonDateToDate("2019-11-23T09:33:30.000Z"));
        System.out.println(convertToDateFromString("23-11-2019"));
    }
}
