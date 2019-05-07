/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mwn
 */
public class ForeignAPIs {

    public static final String FLIGHT_URL = "https://magnusklitmose.com/Flights-1.0/api/flight/country/date/Denmark/Norway/";

    public static String getFlights(String date) throws Exception {
        URL url = new URL(FLIGHT_URL + date);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }

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

    public static void main(String[] args) throws Exception {
    }

}
