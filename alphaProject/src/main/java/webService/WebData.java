package webService;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@RequestScoped
@Path("webdata")
public class WebData {

    Logger log = Logger.getLogger(WebData.class.getName());

    @GET
    @Path("/result/{data}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response result(@PathParam(value = "data") String data){
        String returnedData = "Пришёл текст" + data;
        log.info("req");
        return Response.accepted(returnedData).build();
    }

    @GET
    @Path("/result2")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response result2(@QueryParam(value = "data") String data){
        return Response.accepted(data).build();
    }
}
