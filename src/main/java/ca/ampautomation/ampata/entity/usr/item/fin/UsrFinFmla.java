package ca.ampautomation.ampata.entity.usr.item.fin;

import ca.ampautomation.ampata.entity.usr.base.UsrItemBase;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@JmixEntity
@Entity(name = "enty_UsrFinFmla")
public class UsrFinFmla extends UsrItemBase {

    @Column(name = "FMLA")
    private String fmla;

    public String getFmla() {
        return fmla;
    }

    public void setFmla(String fmla) {
        this.fmla = fmla;
    }

}