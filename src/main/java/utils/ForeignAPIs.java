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

    public static final String FLIGHT_URL = "https://magnusklitmose.com/Flights-1.0/api/flight/date/all/";

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
        List<Flight> returnFlights = new ArrayList();
        
        for (Flight flight : flights) {
            String[] startAiportCoord = flight.getCordiStart().split("/");
            String[] endAirportCoord = flight.getCordiEnd().split("/");

            flight.setStartLat(Double.valueOf(startAiportCoord[0]));
            flight.setStartLong(Double.valueOf(startAiportCoord[1]));
            flight.setEndLat(Double.valueOf(endAirportCoord[0]));
            flight.setEndLong(Double.valueOf(endAirportCoord[1]));
            
            int distUserToAirport = Calculator.calculateDistance(startLat, startLong, flight.getStartLat(), flight.getStartLong());
            int distAirportToEvent = Calculator.calculateDistance(flight.getEndLat(), flight.getEndLong(), endLat, endLong);
            int totalDist = distUserToAirport + distAirportToEvent;
            flight.setTotalDistance(totalDist);
            sortedFlights.add(flight);

        }
        
        sortedFlights.sort(Comparator.comparing(Flight::getTotalDistance));
        
        for (int i = 0; i < 5; i++) {
            returnFlights.add(sortedFlights.get(i));
        }
        
        
        return returnFlights;
    }
    

}
