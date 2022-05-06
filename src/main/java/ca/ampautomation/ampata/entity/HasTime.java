package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

@JmixEntity(name = "ampata_HasTime")
@Embeddable
public class HasTime {


    @Column(name = "TIME1")
    private LocalTime time1;

    @javax.persistence.Column
    private Integer time1Hr;

    @javax.persistence.Column
    private Integer time1Min;

    public void setTime1(LocalTime time1) {
        if (!Objects.equals(this.time1, time1)) {
            this.time1 = time1;
            updateAllFields();
        }
    }

    public LocalTime getTime1() {
        return time1;
    }

    public Integer getTime1Min() {
        return time1Min;
    }

    public Integer getTime1Hr() {
        return time1Hr;
    }

    public void updateAllFields(){
        time1Hr = time1.getHour();
        time1Min = time1.getMinute();
    }

}