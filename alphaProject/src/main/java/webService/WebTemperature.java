package webService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(endpointInterface = "webService.WebTemperatureService")
public class WebTemperature implements WebTemperatureService{

    @EJB
    WeatherFacade weatherFacade;

    @Override
    public Double takeTemperature(){
        return weatherFacade.temperature();
    }
}
