package webService;

import com.google.gson.Gson;
import facade.WeatherFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@RequestScoped
@Path("webdata")
public class WebData {

    @EJB(lookup = "java:global/bettaProject-1.0/WeatherFacadeBean!facade.WeatherFacade")
    WeatherFacade weatherFacade;

    Logger log = Logger.getLogger(WebData.class.getName());

    @GET
    @Path("/result/{data}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response result(@PathParam(value = "data") String data){
        String returnedData = "Пришёл текст" + data;
        log.info("req");
        return Response.ok(returnedData).build();
    }

    @GET
    @Path("/result/{one}/{two}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resultOne(@PathParam(value = "one") String one,
                              @PathParam(value = "two") String two){
        String returnedData = "Пришёл текст " + one + " и " + two;
        log.info("req");
        return Response.ok(returnedData).build();
    }

    @GET
    @Path("/result2")
    @Produces(MediaType.APPLICATION_JSON)
    public Response result2(@QueryParam(value = "data") String data){
        return Response.ok(data).build();
    }

    @GET
    @Path("/temperature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response weather(){
        Gson gson = new Gson();
        String json = gson.toJson(weatherFacade.temperature());
        return  Response.accepted(json).build();
    }
}
