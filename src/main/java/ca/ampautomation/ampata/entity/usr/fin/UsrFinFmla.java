package ca.ampautomation.ampata.entity.usr.fin;

import ca.ampautomation.ampata.entity.usr.base.UsrBaseItem;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@JmixEntity
@Entity(name = "enty_UsrFinFmla")
public class UsrFinFmla extends UsrBaseItem {

    @Column(name = "FMLA")
    private String fmla;

    public String getFmla() {
        return fmla;
    }

    public void setFmla(String fmla) {
        this.fmla = fmla;
    }

}