package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;

@JmixEntity(name = "ampata_HasServiceDesc")
@Embeddable
public class HasServiceDesc {
    @Column(name = "SERVICE_DESC_TEXT1")
    private String serviceDescText1;

    @Column(name = "SERVICE_DESC_TEXT2")
    private String serviceDescText2;

    @JoinColumn(name = "GEN_NODE__1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genNode_1_Id;

    @JoinColumn(name = "GEN_NODE__2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genNode_2_Id;

    @JoinColumn(name = "GEN_DESC_PAT__1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenPat genDescPat_1_Id;

    @Column(name = "SERVICE_DESC")
    private String serviceDesc;

    public void setGenDescPat_1_Id(GenPat genDescPat_1_Id) {
        this.genDescPat_1_Id = genDescPat_1_Id;
    }

    public GenPat getGenDescPat_1_Id() {
        return genDescPat_1_Id;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public GenNode getGenNode_2_Id() {
        return genNode_2_Id;
    }

    public void setGenNode_2_Id(GenNode genNode_2_Id) {
        this.genNode_2_Id = genNode_2_Id;
    }

    public GenNode getGenNode_1_Id() {
        return genNode_1_Id;
    }

    public void setGenNode_1_Id(GenNode genNode_1_Id) {
        this.genNode_1_Id = genNode_1_Id;
    }

    public String getServiceDescText2() {
        return serviceDescText2;
    }

    public void setServiceDescText2(String serviceDescText2) {
        this.serviceDescText2 = serviceDescText2;
    }

    public String getServiceDescText1() {
        return serviceDescText1;
    }

    public void setServiceDescText1(String serviceDescText1) {
        this.serviceDescText1 = serviceDescText1;
    }
}