package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@JmixEntity(name = "ampata_HasTmst")
@Embeddable
public class HasTmst {
    @Column(name = "TS1")
    private LocalDateTime ts1;

    @Column(name = "DATE1")
    private LocalDate date1;

    @javax.persistence.Column
    private Integer date1Yr;

    @javax.persistence.Column
    private Integer date1Qtr;

    @javax.persistence.Column
    private Integer date1Mon;

    @javax.persistence.Column
    private String date1Mon2;

    @javax.persistence.Column
    private Integer date1Day;


    @Column(name = "TIME1")
    private LocalTime time1;

    @javax.persistence.Column
    private Integer time1Hr;

    @javax.persistence.Column
    private Integer time1Min;

    public void setTs1(LocalDateTime ts1) {
        if (!Objects.equals(this.ts1, ts1)){
            this.ts1 = ts1;
            updateAllFields();
        }
    }

    public LocalDateTime getTs1() {
        return ts1;
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

    public LocalDate getDate1() {
        return date1;
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

    public LocalTime getTime1() {
        return time1;
    }

    public Integer getDate1Day() {
        return date1Day;
    }

    public Integer getTime1Min() {
        return time1Min;
    }

    public Integer getTime1Hr() {
        return time1Hr;
    }

    public String getDate1Mon2() {
        return date1Mon2;
    }

    public Integer getDate1Mon() {
        return date1Mon;
    }

    public Integer getDate1Qtr() {
        return date1Qtr;
    }

    public Integer getDate1Yr() {
        return date1Yr;
    }

    public void updateDate1(){
    }
    public void updateAllFields(){
        if (ts1 == null) {
            date1Yr = null;
            date1Qtr = null;
            date1Mon = null;
            date1Mon2 = null;
            date1Day =  null;
            time1Hr = null;
            time1Min = null;
        }else{
            date1 = ts1.toLocalDate();
            date1Yr = date1.getYear();
            date1Qtr = ((date1.getMonthValue() - 1) / 4) + 1;
            date1Mon = date1.getMonthValue();
            date1Mon2 = date1.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            date1Day = date1.getDayOfMonth();

            time1 = ts1.toLocalTime();
            time1Hr = time1.getHour();
            time1Min = time1.getMinute();
        }
    }

}