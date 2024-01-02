package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

import java.time.LocalTime;
import java.util.Objects;

@JmixEntity(name = "enty_HasTime")
@Embeddable
public class HasTime {


    @Column(name = "EL_TM")
    private LocalTime elTm;

    @jakarta.persistence.Column
    private Integer elTmHr;

    @jakarta.persistence.Column
    private Integer elTmMin;

    public void setElTm(LocalTime elTm) {
        if (!Objects.equals(this.elTm, elTm)) {
            this.elTm = elTm;
            updateAllFields();
        }
    }

    public LocalTime getElTm() {
        return elTm;
    }

    public Integer getElTmMin() {
        return elTmMin;
    }

    public Integer getElTmHr() {
        return elTmHr;
    }

    public void updateAllFields(){
        elTmHr = elTm.getHour();
        elTmMin = elTm.getMinute();
    }

}