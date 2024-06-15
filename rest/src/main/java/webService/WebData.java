package webService;

import com.google.gson.Gson;
import facade.WeatherFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.logging.Logger;

//Это класс ресурса. Классы ресурсов — это POJO, у которых есть хотя бы один метод,
// аннотированный @Path или указатель метода запроса.
@RequestScoped
@Path("webdata")
public class WebData {

    @EJB(lookup = "java:global/bettaProject-1.0/WeatherFacadeBean!facade.WeatherFacade")
    WeatherFacade weatherFacade;

    Logger log = Logger.getLogger(WebData.class.getName());

    @GET
    @Path("/result/{data}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response result(@PathParam(value = "data") String data, @Context Request req){
        String returnedData = "Пришёл текст " + data;
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(86400);
        EntityTag et = new EntityTag("111");
        Response.ResponseBuilder rb = req.evaluatePreconditions(et);
        if (rb != null) {
            return rb.cacheControl(cacheControl).tag(et).build();
        }
        rb = Response.ok(returnedData).cacheControl(cacheControl).tag(et);
        return rb.build();
    }
    //отфильтровал так, чтобы были доступны только цифры во втором выражении.
    //http://localhost:8082/rest/webdata/result/10/22
    @GET
    @Path("/result/{one}/{two: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resultOne(@PathParam(value = "one") String one,
                              @PathParam(value = "two") String two){
        String returnedData = "Пришёл текст " + one + " и число " + two;
        log.info("req");
        return Response.ok(returnedData).build();
    }
    //Если закинуть два одинаковых, но во втором будут буквы, то перехватит второй согласно
    //формуле в доке https://jakarta.ee/specifications/restful-ws/3.0/jakarta-restful-ws-spec-3.0.html#template_to_regex
    //http://localhost:8082/rest/webdata/result/10/www
    @GET
    @Path("/result/{one}/{two: [A-Za-z]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resultTwo(@PathParam(value = "one") String one,
                              @PathParam(value = "two") String two){
        String returnedData = "Пришёл текст " + one + " и текст " + two;
        log.info("req");
        return Response.ok(returnedData).build();
    }

    @GET
    @Path("/result2")
    @Produces(MediaType.APPLICATION_JSON + "; qs=1") //qs=1 - это приоритет, если бы выбор был из двух, то можно задать
    public Response result2(@QueryParam(value = "data") String data){
        return Response.ok(data).build();
    }

    @GET
    @Path("/temperature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response weather(@Context Request req){
        Gson gson = new Gson();
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(86400);
        EntityTag et = new EntityTag("111");
        Response.ResponseBuilder rb = req.evaluatePreconditions(et);
        if (rb != null) {
            return rb.cacheControl(cacheControl).tag(et).build();
        }
        rb = Response.ok(gson.toJson(weatherFacade.temperature())).cacheControl(cacheControl).tag(et);
        return rb.build();
    }
}
