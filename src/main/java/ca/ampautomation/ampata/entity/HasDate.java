package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

@JmixEntity(name = "enty_HasDate")
@Embeddable
public class HasDate {

    @Column(name = "EL_DT")
    private LocalDate elDt;

    @jakarta.persistence.Column
    private Integer elDtYr;

    @jakarta.persistence.Column
    private Integer elDtQtr;

    @jakarta.persistence.Column
    private Integer elDtMon;

    @jakarta.persistence.Column
    private String elDtMon2;

    @jakarta.persistence.Column
    private Integer elDtDay;

    public void setElDt(LocalDate elDt) {
        if (!Objects.equals(this.elDt, elDt)) {
            this.elDt = elDt;
            updateAllFields();
        }
    }

    public LocalDate getElDt() {
        return elDt;
    }


    public Integer getElDtDay() {
        return elDtDay;
    }

    public String getElDtMon2() {
        return elDtMon2;
    }

    public Integer getElDtMon() {
        return elDtMon;
    }

    public Integer getElDtQtr() {
        return elDtQtr;
    }

    public Integer getElDtYr() {
        return elDtYr;
    }

    public void updateAllFields(){
        if (elDt == null) {
            elDtYr = null;
            elDtQtr = null;
            elDtMon = null;
            elDtMon2 = null;
            elDtDay =  null;
        }else{
            elDtYr = elDt.getYear();
            elDtQtr = ((elDt.getMonthValue() - 1) / 4) + 1;
            elDtMon = elDt.getMonthValue();
            elDtMon2 = elDt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            elDtDay = elDt.getDayOfMonth();
        }
    }

}