package facade;


import dto.WeatherDto;

import javax.ejb.Remote;

public interface WeatherFacade{

    WeatherDto temperature();

}
