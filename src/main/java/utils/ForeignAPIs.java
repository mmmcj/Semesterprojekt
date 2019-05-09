/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Flight;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author mwn
 */
public class ForeignAPIs {

    public static final String FLIGHT_URL = "https://magnusklitmose.com/Flights-1.0/api/flight/country/date/Denmark/Norway/";

    public static Flight[] callApi(String date) throws Exception {
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
        
        Flight[] flights = new Gson().fromJson(jsonStr, Flight[].class);
        
        return flights;
    }
    
    public static List<Flight> getFlights(String date, double startLat, double startLong, double endLat, double endLong) throws Exception {
        Flight[] flights = callApi(date);
        List<Flight> sortedFlights = new ArrayList();
        
        for (Flight flight : flights) {
            String[] startAiportCoord = flight.getCordiStart().split("/");
            String[] endAirportCoord = flight.getCordiEnd().split("/");
            
            int distUserToAirport = Calculator.calculateDistance(startLat, startLong, Double.valueOf(startAiportCoord[0]), Double.valueOf(startAiportCoord[1]));
            int distAirportToEvent = Calculator.calculateDistance(Double.valueOf(endAirportCoord[0]), Double.valueOf(endAirportCoord[1]), endLat, endLong);
            int totalDist = distUserToAirport + distAirportToEvent;
            flight.setTotalDistance(totalDist);
            sortedFlights.add(flight);

        }
        
        sortedFlights.sort(Comparator.comparing(Flight::getTotalDistance));
        
        for (Flight sortedFlight : sortedFlights) {
            System.out.println(sortedFlight.getTotalDistance());
        }
        
        
        return sortedFlights;
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
    

}
