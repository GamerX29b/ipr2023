package facade;


import dto.Weather;
import javax.ejb.Remote;

@Remote
public interface WeatherFacade{

    Weather temperature ();

}
