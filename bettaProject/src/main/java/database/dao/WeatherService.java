package database.dao;

import dto.Weather;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface WeatherService {

    Weather findTemperatureByDate(Date date);

    void createNewTemperatureRecord(Weather weather);
}
