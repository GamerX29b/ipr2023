package database.dao;

import dto.WeatherDto;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateful
//@TransactionManagement(TransactionManagementType.CONTAINER) //управление контейнером
@TransactionManagement(TransactionManagementType.BEAN) //управление бином
public class WeatherServiceBean implements WeatherService{

    Logger log = Logger.getLogger(WeatherServiceBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction ut;

    @Override
    @Lock(LockType.READ)
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
    public Weather createNewTemperatureRecord(WeatherDto weatherDto) {

       try {
           ut.begin();
           Weather weatherModel = new Weather();
           weatherModel.setTemperature(weatherDto.getTemperature());
           weatherModel.setHumidity(weatherDto.getHumidity());
           weatherModel.setData(weatherDto.getTimestamp());
           log.info("ADD TO BASE " + weatherModel.getTemperature() + " " + weatherModel.getHumidity() + " " + weatherModel.getData() + " " + weatherModel.getId());
           entityManager.persist(weatherModel);
           ut.commit();
           return weatherModel;
       } catch (Exception e){
           try {
               ut.rollback();
           } catch (SystemException ex) {
               throw new RuntimeException(ex);
           }
       }
        return null;
    }

    @PreDestroy
    public void calledPreDestroy(){
        log.info("I'm destroy WeatherService");
    }
}
