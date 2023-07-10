package facade;

import database.dao.WeatherService;
import dto.WeatherDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;

@Stateless
public class WeatherFacadeBean implements WeatherFacade{

    @EJB
    WeatherService weatherService;

    @Override
    public WeatherDto temperature (){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return weatherService.findTemperatureByDate(cal.getTime());
    }

}
