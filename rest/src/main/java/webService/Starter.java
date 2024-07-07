package webService;

import dto.City;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        classes.add(LogWriter.class);
        generateList();
        return classes;
    }

    private void generateList(){
        List<City> cityList = new ArrayList<>();
        City city = new City();
        city.setName("Омск");
        city.setValue("Omsk");
        City city2 = new City();
        city2.setName("Новосибирск");
        city2.setValue("Novosibirsk");
        cityList.add(city);
        cityList.add(city2);

        try {
            FileOutputStream file = new FileOutputStream(new File("citys.bin"));
            ObjectOutputStream outputStream = new ObjectOutputStream(file);
            outputStream.writeObject(cityList);
            outputStream.close();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
