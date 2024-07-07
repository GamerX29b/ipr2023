package webService;

import com.google.gson.Gson;
import dto.City;
import dto.CityInfo;
import facade.WeatherFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
//import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//Это класс ресурса. Классы ресурсов — это POJO, у которых есть хотя бы один метод,
// аннотированный @Path или указатель метода запроса.
@RequestScoped
@ConstrainedTo(RuntimeType.CLIENT)
@Path("webdata")
public class WebData {

    @EJB(lookup = "java:global/bettaProject-1.0/WeatherFacadeBean!facade.WeatherFacade")
    WeatherFacade weatherFacade;

    Logger log = Logger.getLogger(WebData.class.getName());

    @GET
    @Path("/result/{data}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response result(@PathParam(value = "data") @DefaultValue("ничего") String data, @Context Request req) {
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
                              @PathParam(value = "two") String two) {
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
                              @PathParam(value = "two") String two) {
        String returnedData = "Пришёл текст " + one + " и текст " + two;
        log.info("req");
        return Response.ok(returnedData).build();
    }

    @GET
    @Path("/result2")
    @Produces(MediaType.APPLICATION_JSON + "; qs=1") //qs=1 - это приоритет, если бы выбор был из двух, то можно задать
    public Response result2(@QueryParam(value = "data") String data) {
        return Response.ok(data).build();
    }

    @GET
    @Path("/temperature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response weather(@Context Request req, @CookieParam("cityDate") String cityDate) {
        if (cityDate != null) {
            log.info("Last city user is " + cityDate);
        }
        Gson gson = new Gson();
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(86400);
        EntityTag et = new EntityTag("111");
        Response.ResponseBuilder rb = req.evaluatePreconditions(et);
        if (rb != null) {
            return rb.cacheControl(cacheControl).tag(et).build();
        }
        rb = Response.ok(gson.toJson(weatherFacade.temperature())).cacheControl(cacheControl).tag(et);

        NewCookie cityInfoCookie = new NewCookie("cityDate", "Omsk");
        Response response = rb.cookie(cityInfoCookie).build();
        return response;
    }

    @GET
    @Path("/citys")
    @Produces(MediaType.APPLICATION_JSON)
    public Response citys() {
        Gson gson = new Gson();
        FileInputStream fi = null;
        List<City> cityList = new ArrayList<>();
        try {
            fi = new FileInputStream(new File("citys.bin"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            cityList = (List<City>) oi.readObject();
            oi.close();
            fi.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.ok(gson.toJson(cityList)).build();
    }

    @PUT
    @Path("/cityInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cityInfo(@BeanParam CityInfo cityInfo) {
        log.info("Из куки получен город " + cityInfo.getCityDateFromCooke());
        log.info("Из данных браузера получен город " + cityInfo.getLocationFromBrowser());
        List<City> cityList = cityListFromFile();
        City city = new City();
        city.setValue(cityInfo.getValue());
        city.setName(cityInfo.getName());
        cityList.add(city);
        rewriteList(cityList);
        return Response.ok().build();
    }

//    @POST
//    @Path("/imageWeather")
//    @Consumes("image/jpeg")
//    public Response imageWeather(@QueryParam("stream") MultipartFormDataInput stream){
//
//
//        return Response.ok().build();
//    }

    //http://localhost:8082/rest/webdata/deleteCity/Новосибирск
    @DELETE //просто удаление одного из городов из списка
    @Path("/deleteCity/{cityName}")
    @LogResp //собственно, созданная аннотация-интерцептор. Именно таким способом я хотел сделать логгирование для реста в РК
    public Response deleteCity(@PathParam("cityName") String cityName) {
        List<City> cityList = cityListFromFile();
        cityList.removeIf(city -> city.getName().equals(cityName));
        rewriteList(cityList);
        return Response.accepted().build();
    }

    @GET
    @Path("/addCity") //http://localhost:8082/rest/webdata/addCity;cityName=Москва;cityValue=Moscow
    @LogResp
    public Response addCity(@MatrixParam("cityName") String cityName,
                            @MatrixParam("cityValue") String cityValue){
        List<City> cityList = cityListFromFile();
        City city = new City();
        city.setValue(cityValue);
        city.setName(cityName);
        cityList.add(city);
        rewriteList(cityList);
        return Response.accepted().build();
    }

    private List<City> cityListFromFile() {
        try {
            List<City> cityList = new ArrayList<>();
            FileInputStream fileInput = null;
            fileInput = new FileInputStream(new File("citys.bin"));
            ObjectInputStream oi = new ObjectInputStream(fileInput);
            cityList = (List<City>) oi.readObject();
            oi.close();
            fileInput.close();
            return cityList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void rewriteList(List<City> cityList){
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File("citys.bin"));
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(cityList);
            outputStream.close();
            fileOutput.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //@HttpMethod() //- метааннотация которой помечены все стандартные аннотации типа Http можно создать свою
    //Как применять - не понятно.
}
