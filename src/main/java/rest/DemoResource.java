package rest;

import entity.User;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.PuSelector;


/**
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class DemoResource {

  @Context
  private UriInfo context;

  @Context
  SecurityContext securityContext;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getInfoForAll() {
    return "{\"msg\":\"Hello anonymous\"}";
  }

  //Just to verify if the database is setup
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("all")
  public String allUsers() {
    EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
    try{
      List<User> users = em.createQuery("select user from User user").getResultList();
      return "["+users.size()+"]";
    } finally {
      em.close();
    }
 
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("user")
  @RolesAllowed("user")
  public String getFromUser() {
    String thisuser = securityContext.getUserPrincipal().getName();
    return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("admin")
  @RolesAllowed("admin")
  public String getFromAdmin() {
    String thisuser = securityContext.getUserPrincipal().getName();
    return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
  }
/*
   @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("apis")
    public String getswapiPeople() {
        String testSwappiFutureCalls = new Gson().toJson(testSwappiFutureCalls());
        return testSwappiFutureCalls;
    }
  */  
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
  
  public String getSwappiData(int id) throws MalformedURLException, IOException{
    URL url = new URL("https://swapi.co/api/people/"+id);
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
    return jsonStr;
  }
  

}
