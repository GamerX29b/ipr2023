package timer;
import javax.ejb.*;

public class WeatherTimer {

    public static String TEMPERATURE = "0";

    @Schedule(second="0", minute="1", month="*", year="*")
    public void whyTemperature() {
        String temp = "iDontKnow";


        TEMPERATURE = temp;
    }

}
