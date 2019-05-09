/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.HashMap;

/**
 *
 * @author mwn
 */
public class Flight {

    private int id;
    private String startCountry, endCountry, startDestination, endDestination, departureTime, duration;
    private String departureDate;
    private int numberOfSeats;
    private String airline;
    private double price;
    private String cordiStart, cordiEnd;
    private int totalDistance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartCountry() {
        return startCountry;
    }

    public void setStartCountry(String startCountry) {
        this.startCountry = startCountry;
    }

    public String getEndCountry() {
        return endCountry;
    }

    public void setEndCountry(String endCountry) {
        this.endCountry = endCountry;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCordiStart() {
        return cordiStart;
    }

    public void setCordiStart(String cordiStart) {
        this.cordiStart = cordiStart;
    }

    public String getCordiEnd() {
        return cordiEnd;
    }

    public void setCordiEnd(String cordiEnd) {
        this.cordiEnd = cordiEnd;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }
    
    

    

}
