package database.dao;

import database.model.WeatherModel;
import dto.Weather;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

@Stateless
public class WeatherServiceBean implements WeatherService{

    @PersistenceContext(unitName="weather")
    private EntityManager entityManager;

    @Override
    public Weather findTemperatureByDate(Date date){

        Query q = entityManager.createQuery("SELECT TEMPERATURE, HUMIDITY FROM WEATHER w WHERE w.DATA = :data");
        q.setParameter("DATA",date);
        WeatherModel singleResult = (WeatherModel) q.getSingleResult();

        Weather weather = new Weather();

        weather.setTemperature(singleResult.getTemperature());
        weather.setHumidity(singleResult.getHumidity());
        weather.setTimestamp(singleResult.getTimestamp());

        return weather;
    }

    @Override
    public void createNewTemperatureRecord(Weather weather){
        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setTemperature(weather.getTemperature());
        weatherModel.setHumidity(weather.getHumidity());
        weatherModel.setTimestamp(weather.getTimestamp());
        entityManager.persist(weatherModel);

    }
}
