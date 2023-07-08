package database.dao;

import dto.WeatherDto;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface WeatherService {

    WeatherDto findTemperatureByDate(Date date);

    Weather createNewTemperatureRecord(WeatherDto weatherDto);
}
