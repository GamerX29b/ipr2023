package facade;

import database.dao.WeatherService;
import dto.WeatherDto;

import javax.ejb.EJB;
import java.util.Date;

public class WeatherFacadeBean implements WeatherFacade{

    @EJB
    WeatherService weatherService;

    @Override
    public WeatherDto temperature (){
        return weatherService.findTemperatureByDate(new Date());
    }

}
