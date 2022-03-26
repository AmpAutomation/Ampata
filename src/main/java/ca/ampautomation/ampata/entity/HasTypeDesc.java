package ca.ampautomation.ampata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;

@JmixEntity(name = "ampata_HasTypeDesc")
@Embeddable
public class HasTypeDesc {
    @Column(name = "TYPE_DESC_SRC_COL")
    private String typeDescSrcCol;

    @Column(name = "TYPE_DESC_TEXT1")
    private String typeDescText1;

    @Column(name = "TYPE_DESC_TEXT2")
    private String typeDescText2;

    @JoinColumn(name = "GEN_DESC_PAT__1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenPat genDescPat_1_Id;

    @Column(name = "TYPE_DESC")
    private String typeDesc;

    public GenPat getGenDescPat_1_Id() {
        return genDescPat_1_Id;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public GenPat getDescPat() { return genDescPat_1_Id; }

    public void setDescPat(GenPat typeDescPat) {
        this.genDescPat_1_Id = typeDescPat;
    }

    public String getTypeDescText2() {
        return typeDescText2;
    }

    public void setTypeDescText2(String typeDescText2) {
        this.typeDescText2 = typeDescText2;
    }

    public String getTypeDescText1() {
        return typeDescText1;
    }

    public void setTypeDescText1(String typeDescText1) {
        this.typeDescText1 = typeDescText1;
    }

    public String getTypeDescSrcCol() {
        return typeDescSrcCol;
    }

    public void setTypeDescSrcCol(String typeDescSrcCol) {
        this.typeDescSrcCol = typeDescSrcCol;
    }
}