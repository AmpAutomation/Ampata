package ca.ampautomation.ampata.entity.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrEdge;
import ca.ampautomation.ampata.entity.usr.UsrItem;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@JmixEntity
@Entity(name = "enty_UsrGenTag")
public class UsrGenTag extends UsrItem {

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



    @JoinTable(name = "AMPATA_USR_EDGE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EDGE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrEdge> edges1_Id;

    public List<UsrEdge> getEdges1_Id() { return edges1_Id; }

    public void setEdges1_Id(List<UsrEdge> edges1_Id) { this.edges1_Id = edges1_Id; }

}