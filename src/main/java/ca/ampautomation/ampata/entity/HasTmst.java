package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

@JmixEntity(name = "enty_HasTmst")
@Embeddable
public class HasTmst {

    @InstanceName
    @Column(name = "EL_TS")
    private LocalDateTime elTs;

    @Column(name = "EL_DT")
    private LocalDate elDt;

    @javax.persistence.Column
    private Integer elDtYr;

    @javax.persistence.Column
    private Integer elDtQtr;

    @javax.persistence.Column
    private Integer elDtMon;

    @javax.persistence.Column
    private String elDtMon2;

    @javax.persistence.Column
    private Integer elDtDay;


    @Column(name = "EL_TM")
    private LocalTime elTm;

    @javax.persistence.Column
    private Integer elTmHr;

    @javax.persistence.Column
    private Integer elTmMin;

    public void setElTs(LocalDateTime elTs) {
        if (!Objects.equals(this.elTs, elTs)){
            this.elTs = elTs;
            updateAllFields();
        }
    }

    public LocalDateTime getElTs() {
        return elTs;
    }

    /*
    public void setDate1(LocalDate date1) {
        if (!Objects.equals(this.date1, date1)){
            this.date1 = date1;
            updateDate1();
        }
        if (!Objects.equals(this.ts1,date1.atTime(this.time1))){
            setTs1(date1.atTime(this.time1));}
    }
    */

    public LocalDate getElDt() {
        return elDt;
    }

    /*
    public void setTime1(LocalTime time1) {
        if (!Objects.equals(this.time1, time1)){
            this.time1 = time1;
            updateTime1();
        }
        if (!Objects.equals(this.ts1,time1.atDate(this.date1))){
            setTs1(time1.atDate(this.date1));}

    }
    */

    public LocalTime getElTm() {
        return elTm;
    }

    public Integer getElDtDay() {
        return elDtDay;
    }

    public Integer getElTmMin() {
        return elTmMin;
    }

    public Integer getElTmHr() {
        return elTmHr;
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

    public void updateDate1(){
    }
    public void updateAllFields(){
        if (elTs == null) {
            elDt = null;
            elDtYr = null;
            elDtQtr = null;
            elDtMon = null;
            elDtMon2 = null;
            elDtDay =  null;

            elTm = null;
            elTmHr = null;
            elTmMin = null;
        }else{
            elDt = elTs.toLocalDate();
            elDtYr = elDt.getYear();
            elDtQtr = ((elDt.getMonthValue() - 1) / 4) + 1;
            elDtMon = elDt.getMonthValue();
            elDtMon2 = elDt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            elDtDay = elDt.getDayOfMonth();

            elTm = elTs.toLocalTime();
            elTmHr = elTm.getHour();
            elTmMin = elTm.getMinute();
        }
    }

}