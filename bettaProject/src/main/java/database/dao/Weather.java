package database.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="WEATHER")
public class Weather implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="DATA")
    Date data;

    @Column(name = "TEMPERATURE")
    Double temperature;

    @Column(name = "HUMIDITY")
    Double humidity;



    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
