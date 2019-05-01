package utils;

import entity.City;
import entity.Country;
import entity.Event;
import entity.Genre;
import entity.Role;
import entity.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class SetupTestEvents {

    public static void main(String[] args) {
        
        //testSwappiFutureCalls();
        startCorrectly();
    }


    public static void startCorrectly() {
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        //throw new UnsupportedOperationException("REMOVE THIS LINE, WHEN YOU HAVE READ WARNING");
        em.getTransaction().begin();
        Event event = new Event(new Genre("sport"), new Country("Denmark", "DK"), new City("København"), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        //event.setId(7);
        em.persist(event);
        em.getTransaction().commit();
        
    }

}