package dto;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;

public class CityInfo {
    @FormParam("name")
    private String name;
    @FormParam("value")
    private String value;
    //Типы headerParam можно подсмотреть тут: jdk.internal.net.http.hpack.SimpleHeaderTable.HeaderField
    @HeaderParam("location") //должен доставать из браузера инфу о текущем городе
    private String locationFromBrowser;
    @CookieParam("cityDate")
    private String cityDateFromCooke;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLocationFromBrowser() {
        return locationFromBrowser;
    }

    public void setLocationFromBrowser(String locationFromBrowser) {
        this.locationFromBrowser = locationFromBrowser;
    }

    public String getCityDateFromCooke() {
        return cityDateFromCooke;
    }

    public void setCityDateFromCooke(String cityDateFromCooke) {
        this.cityDateFromCooke = cityDateFromCooke;
    }
}
