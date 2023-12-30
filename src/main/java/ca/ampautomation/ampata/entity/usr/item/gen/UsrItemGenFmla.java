package ca.ampautomation.ampata.entity.usr.item.gen;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBase;
import io.jmix.core.metamodel.annotation.JmixEntity;

import jakarta.persistence.*;

@JmixEntity
@Entity(name = "enty_UsrItemGenFmla")
public class UsrItemGenFmla extends UsrItemBase {

    @Column(name = "SRC_CODE")
    private String srcCode;

    public String getSrcCode() {
        return srcCode;
    }

    public void setSrcCode(String srcCode) {
        this.srcCode = srcCode;
    }

}