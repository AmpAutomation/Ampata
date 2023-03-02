package ca.ampautomation.ampata.entity.usr.gen;

import ca.ampautomation.ampata.entity.usr.*;
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


    @JoinTable(name = "AMPATA_USR_NODE_TYPE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "NODE_TYPE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrNodeType> nodeTypes1_Id;

    public List<UsrNodeType> getNodeTypes1_Id() { return nodeTypes1_Id; }

    public void setNodeTypes1_Id(List<UsrNodeType> nodeTypes1_Id) { this.nodeTypes1_Id = nodeTypes1_Id; }


    @JoinTable(name = "AMPATA_USR_EDGE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EDGE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrEdge> edges1_Id;

    public List<UsrEdge> getEdges1_Id() { return edges1_Id; }

    public void setEdges1_Id(List<UsrEdge> edges1_Id) { this.edges1_Id = edges1_Id; }



    @JoinTable(name = "AMPATA_USR_EDGE_TYPE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EDGE_TYPE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrEdgeType> edgeTypes1_Id;

    public List<UsrEdgeType> getEdgeTypes1_Id() { return edgeTypes1_Id; }

    public void setEdgeTypes1_Id(List<UsrEdgeType> edgeTypes1_Id) { this.edgeTypes1_Id = edgeTypes1_Id; }

}