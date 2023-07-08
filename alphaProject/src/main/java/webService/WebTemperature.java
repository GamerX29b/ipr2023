package webService;

import dto.Weather;
import facade.WeatherFacade;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(endpointInterface = "webService.WebTemperatureService")
public class WebTemperature implements WebTemperatureService{

    @Resource(name = "jdbc/WeatherFacade")
    WeatherFacade weatherFacade;

    @Override
    public Weather takeTemperature(){
        return weatherFacade.temperature();
    }
}
