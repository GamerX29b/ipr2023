package database.dao;

import dto.WeatherDto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.logging.Logger;

@Stateless
public class WeatherServiceBean implements WeatherService{

    Logger log = Logger.getLogger(WeatherServiceBean.class.getName());

    @PersistenceContext(unitName="weather")
    private EntityManager entityManager;

    @Override
    public WeatherDto findTemperatureByDate(Date date){

        Query q = entityManager.createQuery("SELECT TEMPERATURE, HUMIDITY FROM WEATHER w WHERE w.DATA = :data");
        q.setParameter("data", date);
        Weather singleResult = (Weather) q.getSingleResult();

        WeatherDto weather = new WeatherDto();

        weather.setTemperature(singleResult.getTemperature());
        weather.setHumidity(singleResult.getHumidity());
        weather.setTimestamp(singleResult.getData());

        return weather;
    }

    @Override
    public Weather createNewTemperatureRecord(WeatherDto weatherDto){
        Weather weatherModel = new Weather();
        weatherModel.setTemperature(weatherDto.getTemperature());
        weatherModel.setHumidity(weatherDto.getHumidity());
        weatherModel.setData(weatherDto.getTimestamp());
        log.info("ADD TO BASE " + weatherModel.getTemperature() + " " + weatherModel.getHumidity() + " " + weatherModel.getData() + " " + weatherModel.getId());
        entityManager.persist(weatherModel);
        return weatherModel;

    }
}
