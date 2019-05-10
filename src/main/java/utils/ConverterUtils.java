/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Date;
import java.text.SimpleDateFormat;

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
}
