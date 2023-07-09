package facade;


import dto.WeatherDto;
import javax.ejb.Remote;

@Remote
public interface WeatherFacade{

    WeatherDto temperature();

}
