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

    Logger log = Logger.getLogger(WebData.class.getName());
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(WebData.class);
        log.info("Webdata started");
        return classes;
    }
}
