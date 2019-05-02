package utils;

import entity.Event;
import facade.Facade;
import java.util.Collection;

public class SetupTestEvents {

    public static void main(String[] args) {
        
        //testSwappiFutureCalls();
        startCorrectly();
    }


    public static void startCorrectly() {
        Facade f = new Facade();
        //f.createEvent("København", "Denmark", "sport", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Collection<Event> listOfEvents = f.getEvents();
        
        for (Event event : listOfEvents) {
            System.out.println(event.getCity().getCityName());
            System.out.println(event.getEndDate());
            System.out.println();
        }
        /*
        
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