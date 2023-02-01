package ca.ampautomation.ampata.entity.sys;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "AMPATA_SYS_ITEM", indexes = {
        @Index(name = "IDX_SYSITEM_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_SYSITEM_NAME1_GEN_PAT1__ID", columnList = "NAME1_GEN_PAT1__ID"),
        @Index(name = "IDX_SYSITEM_DESC1_GEN_PAT1__ID", columnList = "DESC1_GEN_PAT1__ID"),
})
@Entity(name = "ampata_SysItem")
public class SysItem {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "ID2")
    private String id2;

    @Column(name = "ID2_CALC")
    private String id2Calc;

    @Column(name = "ID2_CMP")
    private Boolean id2Cmp;

    @Column(name = "ID2_DUP")
    private Integer id2Dup;

    @JoinColumn(name = "TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysItemType type1_Id;

    @Column(name = "TYPE1__ID2")
    private String type1_Id2;

    @Column(name = "SORT_IDX")
    private Integer sortIdx;

    @Column(name = "NAME1")
    private String name1;

    @JoinColumn(name = "NAME1_GEN_PAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysItem name1GenPat1_Id;

    @Column(name = "NAME1_GEN_PAT1__ID2")
    private String name1GenPat1_Id2;

    @Column(name = "DESC1")
    private String desc1;

    @JoinColumn(name = "DESC1_GEN_PAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysItem desc1GenPat1_Id;

    @Column(name = "DESC1_GEN_PAT1__ID2")
    private String desc1GenPat1_Id2;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;


    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId2Calc() {
        return id2Calc;
    }

    public void setId2Calc(String id2Calc) {
        this.id2Calc = id2Calc;
    }

    public Boolean getId2Cmp() {
        return id2Cmp;
    }

    public void setId2Cmp(Boolean id2Cmp) {
        this.id2Cmp = id2Cmp;
    }

    public Integer getId2Dup() {
        return id2Dup;
    }

    public void setId2Dup(Integer id2Dup) {
        this.id2Dup = id2Dup;
    }

    public String getType1_Id2() {
        return type1_Id2;
    }

    public void setType1_Id2(String type1_Id2) {
        this.type1_Id2 = type1_Id2;
    }

    public SysItemType getType1_Id() { return type1_Id; }

    public void setType1_Id(SysItemType type1_Id) { this.type1_Id = type1_Id; }

    public Integer getSortIdx() { return sortIdx; }

    public void setSortIdx(Integer sortIdx) { this.sortIdx = sortIdx; }

    public String getName1() { return name1; }

    public void setName1(String name1) { this.name1 = name1; }

    public SysItem getName1GenPat1_Id() { return name1GenPat1_Id; }

    public void setName1GenPat1_Id(SysItem name1GenPat1_Id) { this.name1GenPat1_Id = name1GenPat1_Id; }

    public String getName1GenPat1_Id2() { return name1GenPat1_Id2; }

    public void setName1GenPat1_Id2(String name1GenPat1_Id2) { this.name1GenPat1_Id2 = name1GenPat1_Id2; }

    public String getDesc1() { return desc1; }

    public void setDesc1(String desc1) { this.desc1 = desc1; }

    public SysItem getDesc1GenPat1_Id() { return desc1GenPat1_Id; }

    public void setDesc1GenPat1_Id(SysItem desc1GenPat1_Id) { this.desc1GenPat1_Id = desc1GenPat1_Id; }

    
    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}