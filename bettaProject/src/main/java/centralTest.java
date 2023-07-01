import com.fasterxml.jackson.databind.ObjectMapper;
import dto.WeatherApiResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


public class centralTest {

    public static void main (String[] args) throws IOException, InterruptedException {

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
        System.out.println(weatherApiResponse.getData().get(Integer.parseInt(parserFromSearch.format(date))).getTemp());

        Weather weather = new Weather();


    }

}
