package rest;

import DTO.EventDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.Facade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.ConverterUtils;
import utils.ForeignAPIs;

@Path("show")
public class EventResource {

    Facade facade = new Facade();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }
     */
    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("events")
    public String allEvents() {
        return gson.toJson(facade.getEvents());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("events/random/{number}")
    public String getNumberOfRandomEvents(@PathParam("number") int number) {
        return gson.toJson(facade.getRandomEvents(number));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("events/{search}")
//  @RolesAllowed("user")
    public String getEventsBySearch(@PathParam("search") String search) {
        return gson.toJson(facade.getEventCollection(search));
    }

    @GET
    @Produces
    @Path("eventSingle/{id}")
    public String getSpecificEvent(@PathParam("id") String id) {
        int nid = Integer.valueOf(id);
        return gson.toJson(facade.getSpecificEvent(nid));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("events/{lattitude}/{longitude}/{distance}")
    public String getEventsByLocation(@PathParam("lattitude") String lattitude, @PathParam("longitude") String longitude, @PathParam("distance") String distance) {
        return gson.toJson(facade.getEventsByLocation(Double.valueOf(lattitude), Double.valueOf(longitude), Integer.valueOf(distance)));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("flights/{date}/{userLat}/{userLong}/{eventLat}/{eventLong}")
//  @RolesAllowed("user")
    public String getFlightsByDate(@PathParam("date") String date, @PathParam("userLat") String userLat, @PathParam("userLong") String userLong, @PathParam("eventLat") String eventLat, @PathParam("eventLong") String eventLong) throws Exception {
        return gson.toJson(ForeignAPIs.getFlights(date, Double.valueOf(userLat), Double.valueOf(userLong), Double.valueOf(eventLat), Double.valueOf(eventLong)));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("eventsdate/{date}")
    public String getEventsBySpecificDate(@PathParam("date") String dateStr) {
        List<EventDTO> eventCollectionBySpecificDate = facade.getEventsBySpecificDate(ConverterUtils.convertFromJsonDateToDate(dateStr));
        if (!eventCollectionBySpecificDate.isEmpty()) {
            return gson.toJson(eventCollectionBySpecificDate);
        } else {
            return gson.toJson("No Occurences on Date.");
        }

    }

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
//  @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }
   @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("apis")
    public String getswapiPeople() {
        String testSwappiFutureCalls = new Gson().toJson(testSwappiFutureCalls());
        return testSwappiFutureCalls;
    }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("apis")
  public String getFromAPIs(){
      String res = null;
      try {
          String a = getSwappiData(2);
          String b = getSwappiData(3);
          String c = getSwappiData(4);
          String d = getSwappiData(5);
          String e = getSwappiData(6);
          res = "{\"persons\":["+a+","+b+","+c+","+d+","+e+"]}";
          return res;
      } catch (IOException ex) {
          Logger.getLogger(DemoResource.class.getName()).log(Level.SEVERE, null, ex);
      }
      return res;
  }
     */
}
