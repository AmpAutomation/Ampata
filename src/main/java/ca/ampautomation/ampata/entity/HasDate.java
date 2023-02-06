package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

@JmixEntity(name = "enty_HasDate")
@Embeddable
public class HasDate {

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

    public void setDate1(LocalDate date1) {
        if (!Objects.equals(this.date1, date1)) {
            this.date1 = date1;
            updateAllFields();
        }
    }

    public LocalDate getDate1() {
        return date1;
    }


    public Integer getDate1Day() {
        return date1Day;
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

    public void updateAllFields(){
        if (date1 == null) {
            date1Yr = null;
            date1Qtr = null;
            date1Mon = null;
            date1Mon2 = null;
            date1Day =  null;
        }else{
            date1Yr = date1.getYear();
            date1Qtr = ((date1.getMonthValue() - 1) / 4) + 1;
            date1Mon = date1.getMonthValue();
            date1Mon2 = date1.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            date1Day = date1.getDayOfMonth();
        }
    }

}