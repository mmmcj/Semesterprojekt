package rest;

import DTO.EventDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Flight;
import facade.Facade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
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
    @Path("events/{search}")
//  @RolesAllowed("user")
    public String getEventsBySearch(@PathParam("search") String search) {
        return gson.toJson(facade.getEventCollection(search));
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("events/{lattitude}/{longitude}/{distance}")
    public String getEventsByLocation(@PathParam("lattitude") String lattitude, @PathParam("longitude")String longitude , @PathParam("distance") String distance){
        return gson.toJson(facade.getEventsByLocation(Double.valueOf(lattitude), Double.valueOf(longitude), Integer.valueOf(distance)));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("flights/{date}/{userLat}/{userLong}/{eventLat}/{eventLong}")
//  @RolesAllowed("user")
    public String getFlightsByDate(@PathParam("date") String date, @PathParam("userLat") String userLat, @PathParam("userLong") String userLong, @PathParam("eventLat") String eventLat, @PathParam("eventLong") String eventLong) throws Exception {
        return gson.toJson(ForeignAPIs.getFlights(date, Double.valueOf(userLat), Double.valueOf(userLong), Double.valueOf(eventLat), Double.valueOf(eventLong)));
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
