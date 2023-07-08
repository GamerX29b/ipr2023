package facade;

import database.dao.WeatherService;
import dto.Weather;

import javax.ejb.EJB;
import java.util.Date;

public class WeatherFacadeBean implements WeatherFacade{

    @EJB
    WeatherService weatherService;

    @Override
    public Weather temperature (){
        return weatherService.findTemperatureByDate(new Date());
    }

}
