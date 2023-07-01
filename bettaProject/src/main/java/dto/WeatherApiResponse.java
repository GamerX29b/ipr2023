package dto;

import java.util.ArrayList;

public class WeatherApiResponse {
    public ArrayList<DataWeatherApi> data;
    public Meta meta;

    public ArrayList<DataWeatherApi> getData() {
        return data;
    }

    public void setData(ArrayList<DataWeatherApi> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
