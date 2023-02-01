package ca.ampautomation.ampata.entity.usr;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@JmixEntity
@Entity(name = "ampata_FinFmla")
public class UsrFinFmla extends UsrItem {

    @Column(name = "FMLA")
    private String fmla;

    public String getFmla() {
        return fmla;
    }

    public void setFmla(String fmla) {
        this.fmla = fmla;
    }

}