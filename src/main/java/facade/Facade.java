package facade;

import DTO.EventDTO;
import entity.City;
import entity.Country;
import entity.Event;
import entity.Genre;
import entity.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import utils.PuSelector;
import utils.Calculator;

/**
 *
 * @author Mark
 */
public class Facade {

    public Collection<EventDTO> getEvents() {
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
        try {
            Query query = em.createQuery("SELECT a FROM Event AS a");
            List<Event> events = (List<Event>) query.getResultList();
            List<EventDTO> eventdtos = new ArrayList<>();

            for (Event event : events) {
                eventdtos.add(new EventDTO(event));
            }

            return eventdtos;
        } finally {
            em.close();
        }

    }

    // der skal nok implementeres DTO'er 
    public void createEvent(String country, String city, String genre, String title, double price, String shortDesc, String longDesc, Image image, String defaultImg, Date startDate, Date endDate) {
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
        Event e = new Event(new Country(country), new City(city), new Genre(genre), title, price, shortDesc, longDesc, image, defaultImg, startDate, endDate);
        try {
            em.getTransaction().begin();
            Query cityQuery = em.createQuery("SELECT COUNT(a) FROM City AS a WHERE a.city = :city")
                    .setParameter("city", city);
            long q1 = (long) cityQuery.getSingleResult();

            Query genreQuery = em.createQuery("SELECT COUNT(a) FROM Genre AS a WHERE a.genre = :genre")
                    .setParameter("genre", genre);
            long q2 = (long) genreQuery.getSingleResult();

            Query countryQuery = em.createQuery("SELECT COUNT(a) FROM Country AS a WHERE a.country = :country")
                    .setParameter("country", country);
            long q3 = (long) countryQuery.getSingleResult();

            if (q1 == 1) {
                cityQuery = em.createQuery(
                        "SELECT a FROM City AS a WHERE a.city = :city");
                cityQuery.setParameter("city", city);

                City c = (City) cityQuery.getSingleResult();
                e.setCity(c);
            }
            if (q2 == 1) {
                genreQuery = em.createQuery(
                        "SELECT a FROM Genre AS a WHERE a.genre = :genre");
                genreQuery.setParameter("genre", genre);

                Genre g = (Genre) genreQuery.getSingleResult();
                e.setGenre(g);
            }
            if (q3 == 1) {
                countryQuery = em.createQuery(
                        "SELECT a FROM Country AS a WHERE a.country = :country");
                countryQuery.setParameter("country", country);
                Country co = (Country) countryQuery.getSingleResult();
                e.setCountry(co);
            }

            em.persist(e);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public List<Event> addtoReturnList(List<Event> evlistToreturn, List<Event> localevents) {
        for (Event ev : localevents) {
            if (!evlistToreturn.contains(ev)) {
                evlistToreturn.add(ev);
            }
        }
        return evlistToreturn;
    }

    public Collection<EventDTO> getEventCollection(String keyword) {
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
        List<Event> events = new ArrayList();
        try {
            em.getTransaction().begin();
            Query keywordQuery = em.createQuery("SELECT r FROM Event r WHERE r.title LIKE :title")
                    .setParameter("title", "%" + keyword + "%");
            try {
                List<Event> localevent = (List<Event>) keywordQuery.getResultList();
                events = addtoReturnList(events, localevent);
            } catch (Exception e) {
                System.out.println("stacktrace: " + e.getLocalizedMessage() + "\n");
            }
            keywordQuery = em.createQuery("SELECT r FROM Event r WHERE r.city.city LIKE :city")
                    .setParameter("city", "%" + keyword + "%");
            try {
                List<Event> localevent = (List<Event>) keywordQuery.getResultList();
                events = addtoReturnList(events, localevent);
            } catch (Exception e) {
                System.out.println("stacktrace: " + e.getLocalizedMessage() + "\n");
            }
            keywordQuery = em.createQuery("SELECT r FROM Event r WHERE r.country.country LIKE :country")
                    .setParameter("country", "%" + keyword + "%");
            try {
                List<Event> localevent = (List<Event>) keywordQuery.getResultList();
                events = addtoReturnList(events, localevent);
            } catch (Exception e) {
                System.out.println("stacktrace: " + e.getLocalizedMessage() + "\n");
            }
            keywordQuery = em.createQuery("SELECT r FROM Event r WHERE r.genre.genre LIKE :genre")
                    .setParameter("genre", "%" + keyword + "%");
            try {
                List<Event> localevent = (List<Event>) keywordQuery.getResultList();
                events = addtoReturnList(events, localevent);
            } catch (Exception e) {
                System.out.println("stacktrace: " + e.getLocalizedMessage() + "\n");
            }
            keywordQuery = em.createQuery("SELECT r FROM Event r WHERE r.shortDesc LIKE :shortDesc")
                    .setParameter("shortDesc", "%" + keyword + "%");
            try {
                List<Event> localevent = (List<Event>) keywordQuery.getResultList();
                events = addtoReturnList(events, localevent);
            } catch (Exception e) {
                System.out.println("stacktrace: " + e.getLocalizedMessage() + "\n");
            }

        } finally {
            em.close();
        }
        List<EventDTO> dtos = new ArrayList();

        for (Event event : events) {
            dtos.add(new EventDTO(event));
        }

        return dtos;
    }
    
    //This method finds all the events within a certain area of a location
    //The Calculator is code that we found online which helps with calculating the distance between 2 location points.
    
    public List<EventDTO> getEventsByLocation(double latittude, double longitude, int distance){
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
        List<EventDTO> dto = new ArrayList();
        List<City> citiesToFindEventsFrom = new ArrayList();
        List<Event> finalEvents = new ArrayList();
       try{
        em.getTransaction().begin();
            Query cityQuery = em.createQuery("SELECT r From City");
            List<City> citylist = (List<City>) cityQuery.getResultList();
       try{
         }
         catch (Exception e) {
                System.out.println("stacktrace: " + e.getLocalizedMessage() + "\n");
         } 
       
       citylist.forEach((city) -> {     
           int res = Calculator.calculateDistance(latittude, longitude, city.getLattitude(), city.getLongitude());
                if (res<distance) {
                    citiesToFindEventsFrom.add(city);
                }
            });
       citiesToFindEventsFrom.forEach((city) -> {
           Query cityEventsQuery = em.createQuery("SELECT r FROM Event r WHERE r.city.id LIKE :id")
                    .setParameter("id", city.getId());
            try {
                List<Event> localevent = (List<Event>) cityEventsQuery.getResultList();
                finalEvents.addAll(localevent);
            } catch (Exception e) {
                System.out.println("stacktrace: " + e.getLocalizedMessage() + "\n");
            }
       });
       }finally{
           em.close();
       }

       finalEvents.forEach((event) -> {
           dto.add(new EventDTO(event));
        });
        
        return dto;
    }

}
