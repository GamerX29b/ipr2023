package timer;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.dao.WeatherService;
import dto.DataWeatherApi;
import dto.WeatherDto;
import dto.WeatherApiResponse;

import javax.ejb.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Stateless
public class WeatherTimer {

    @EJB
    private WeatherService weatherService;

    Logger log = Logger.getLogger(WeatherTimer.class.getName());

    @Schedule(second="*", minute="1", hour="*", month="*")//Раз в минуту
    public void whyTemperature() throws IOException, ParseException, InterruptedException {
        WeatherDto weather = getActualTemperature();  //TODO сраная квота на фасад! Перед демонстрацией раскоментить.
        weatherService.createNewTemperatureRecord(weather); //You have exceeded the MONTHLY quota for Requests on your current plan, BASIC. Upgrade your plan
        log.warning("Таймер сработал" + new Date());
    }

    public WeatherDto getActualTemperature() throws IOException, InterruptedException, ParseException {
        Date date = new Date();
        SimpleDateFormat parserFromApi = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat parserFromSearch = new SimpleDateFormat("HH");
        ObjectMapper objectMapper = new ObjectMapper();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://meteostat.p.rapidapi.com/stations/hourly?station=10637&start="+parserFromApi.format(date)+"&end="+parserFromApi.format(date)
                        +"&tz=Asia%2FOmsk"))
                .header("X-RapidAPI-Key", "77aeeed127msh4f7226094c0ad44p14c306jsnadb88858a947")
                .header("X-RapidAPI-Host", "meteostat.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        WeatherApiResponse weatherApiResponse = objectMapper.readValue(response.body(), WeatherApiResponse.class);
        DataWeatherApi dwa = weatherApiResponse.getData().get(Integer.parseInt(parserFromSearch.format(date)));

        WeatherDto weather = new WeatherDto();
        weather.setTemperature(dwa.getTemp());
        weather.setHumidity(dwa.getDwpt());
        weather.setTimestamp(parserFromApi.parse(weatherApiResponse.getMeta().getGenerated()));
        return weather;
    }

}
