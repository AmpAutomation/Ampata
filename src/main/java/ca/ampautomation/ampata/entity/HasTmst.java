package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

@JmixEntity(name = "ampata_HasTmst")
@Embeddable
public class HasTmst {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TS1")
    private Date ts1;

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


    @Temporal(TemporalType.TIME)
    @Column(name = "TIME1")
    private Date time1;

    @javax.persistence.Column
    private Integer time1Hr;

    @javax.persistence.Column
    private Integer time1Min;

    public void setTs1(Date ts1) {
        this.ts1 = ts1;
    }

    public Date getTs1() {
        return ts1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;

        if (date1 == null) {
            date1Yr = null;
            date1Qtr = null;
            date1Mon = null;
            date1Mon2 = null;
            date1Day =  null;
            return;
        }

        LocalDate localDate = Instant.ofEpochMilli(date1.getTime())
                                  .atZone(ZoneId.systemDefault())
                                  .toLocalDate();

        date1Yr = localDate.getYear();
        date1Qtr = ((localDate.getMonthValue() - 1) / 4) + 1;
        date1Mon = localDate.getMonthValue();
        date1Mon2 = localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.CANADA);
        date1Day =  localDate.getDayOfMonth();
    }

    public Date getDate1() {
        return date1;
    }

    public void setTime1(Date time1) {
        this.time1 = time1;
        ZoneId timeZone = ZoneId.systemDefault();
        LocalTime localTime = time1.toInstant().atZone(timeZone).toLocalTime();
        time1Hr = localTime.getHour();
        time1Min = localTime.getMinute();
    }

    public Date getTime1() {
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

}