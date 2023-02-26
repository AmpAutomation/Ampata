package ca.ampautomation.ampata.entity.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;

@JmixEntity
@Entity(name = "enty_UsrGenTag")
@DiscriminatorValue(value="enty_UsrGenTag")
public class UsrGenTag extends UsrNode {


    @JoinTable(name = "AMPATA_USR_NODE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "NODE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrNode> nodes1_Id;

    public List<UsrNode> getNodes1_Id() {
        return nodes1_Id;
    }

    public void setNodes1_Id(List<UsrNode> nodes1_Id) {
        this.nodes1_Id = nodes1_Id;
    }


    public Boolean updateCalcVals(DataManager dataManager){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}
