package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.Facade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

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
    public String allUsers() {
        return gson.toJson(facade.getEvents());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("events/{search}")
//  @RolesAllowed("user")
    public String getFromUser(@PathParam("search") String search) {
        return gson.toJson(facade.getEventCollection(search));
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
