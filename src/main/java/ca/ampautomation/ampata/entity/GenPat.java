package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;

@JmixEntity
@Entity(name = "ampata_GenPat")
public class GenPat extends GenEntity {

    @JoinColumn(name = "NAME1_PAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenPatType name1Pat1_Id;

    @Column(name = "NAME1_PAT1__ID2")
    private String name1Pat1_Id2;

    public String getName1Pat1_Id2() {
        return name1Pat1_Id2;
    }

    public void setName1Pat1_Id2(String name1Pat1_Id2) {
        this.name1Pat1_Id2 = name1Pat1_Id2;
    }

    public GenPatType getName1Pat1_Id() {
        return name1Pat1_Id;
    }

    public void setName1Pat1_Id(GenPatType name1Pat1_Id) {
        this.name1Pat1_Id = name1Pat1_Id;
    }

}