package webService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Запуск. Где надо перечислить все ресурсы
 */
@ApplicationPath("/")
public class Starter extends Application {
    //Application чтобы не делать web.xml
    Logger log = Logger.getLogger(WebData.class.getName());
    @Override
    public Set<Class<?>> getClasses() { //Перечисляем необходимые классые.
        Set<Class<?>> classes = new HashSet<>();
        classes.add(WebData.class);
        log.info("Webdata started");
        classes.add(CorsFilter.class);
        log.info("CorsFilter started");
        classes.add(CorsOptionsFilter.class);
        log.info("CorsOptionsFilter started");
        classes.add(AsyncResponseController.class);
        log.info("AsyncResponse started");
        return classes;
    }
}
