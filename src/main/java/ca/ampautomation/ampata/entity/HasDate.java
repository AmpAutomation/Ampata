package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@JmixEntity(name = "ampata_HasDate")
@Embeddable
public class HasDate {

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE1")
    private Date date1;

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


    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate1() {
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

}