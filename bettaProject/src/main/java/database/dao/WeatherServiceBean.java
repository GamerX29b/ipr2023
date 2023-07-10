package database.dao;

import dto.WeatherDto;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class WeatherServiceBean implements WeatherService{

    Logger log = Logger.getLogger(WeatherServiceBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WeatherDto findTemperatureByDate(Date date){

        Query q = entityManager.createQuery("SELECT w FROM Weather w WHERE w.data = :data");
        q.setParameter("data", date);
        List<Weather> weatherList = (List<Weather>) q.getResultList();
        Weather singleResult = weatherList.get(0);
        WeatherDto weather = new WeatherDto();

        weather.setTemperature(singleResult.getTemperature());
        weather.setHumidity(singleResult.getHumidity());
        weather.setTimestamp(singleResult.getData());

        return weather;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
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
