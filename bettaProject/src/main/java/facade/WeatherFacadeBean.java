package facade;

import dto.WeatherDto;

import interceptor.LoggerInterceptor;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.annotation.security.DenyAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;
import javax.interceptor.Interceptors;

@Stateless
public class WeatherFacadeBean implements WeatherFacade{

    Logger log = Logger.getLogger(WeatherFacadeBean.class.getName());

//    @EJB
//    WeatherService weatherService;

    @Override
    @Interceptors(LoggerInterceptor.class)
    public WeatherDto temperature (){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new WeatherDto();
    }

    @PreDestroy
    @DenyAll
    public void calledPreDestroy(){
        log.info("I'm destroy WeatherFacade");
    }

}
