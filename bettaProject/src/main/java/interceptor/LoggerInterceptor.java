package interceptor;

import database.dao.WeatherServiceBean;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggerInterceptor {

    Logger log = Logger.getLogger(LoggerInterceptor.class.getName());

    public LoggerInterceptor() {
    }

    @AroundInvoke
    public Object sayInvoked(InvocationContext invocationContext) throws Exception{
        log.info("i invoked");
        return invocationContext.proceed();
    }

}
