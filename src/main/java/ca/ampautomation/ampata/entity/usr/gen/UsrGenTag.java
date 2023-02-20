package ca.ampautomation.ampata.entity.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;

@JmixEntity
@Entity(name = "enty_UsrGenTag")
@DiscriminatorValue(value="enty_UsrGenTag")
public class UsrGenTag extends UsrNode {


    @JoinTable(name = "AMPATA_USR_NODE__GEN_TAG_LINK",
            joinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "NODE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrNode> nodes;

    public List<UsrNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<UsrNode> nodes) {
        this.nodes = nodes;
    }
}
