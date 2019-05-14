package utils;

import entity.City;
import entity.Country;
import entity.Genre;
import entity.Image;
import entity.User;
import facade.Facade;
import java.util.Date;
import javax.persistence.EntityManager;

public class SetupTestEvents {

    public static void main(String[] args) {
//        EntityManager em = PuSelector.getEntityManagerFactory("pu_production").createEntityManager();
        //testSwappiFutureCalls();
        startCorrectly();
    }


    public static void startCorrectly() {
        
//        User u = new User("somename", "pw");
//        System.out.println(u.getUserPass());
        
//        Facade f = new Facade();
//        
//        f.createEvent(
//                "Denmark",
//                "København",
//                "Sport",
//                "Typisk FCK vs Brøndby kamp",
//                250.0,
//                "hold dig indedøre",
//                "FCK mod brøndby... Dette skulle være en lang description",
//                new Image("someUrl.com"), // img object
//                "someImageUrl.com/image/2",
//                new Date(System.currentTimeMillis()),
//                new Date(System.currentTimeMillis()));
        /*
        Collection<Event> listOfEvents = f.getEvents();
        
        for (Event event : listOfEvents) {
            System.out.println(event.getCity().getCityName());
            System.out.println(event.getEndDate());
            System.out.println();
        }
        
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
        */
        
    }

}