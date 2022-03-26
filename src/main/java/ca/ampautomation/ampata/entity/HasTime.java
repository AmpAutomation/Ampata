package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@JmixEntity(name = "ampata_HasTime")
@Embeddable
public class HasTime {


    @Temporal(TemporalType.TIME)
    @Column(name = "TIME1")
    private Date time1;

    @javax.persistence.Column
    private Integer time1Hr;

    @javax.persistence.Column
    private Integer time1Min;

    public void setTime1(Date time1) {
        this.time1 = time1;
    }

    public Date getTime1() {
        return time1;
    }

    public Integer getTime1Min() {
        return time1Min;
    }

    public Integer getTime1Hr() {
        return time1Hr;
    }

}