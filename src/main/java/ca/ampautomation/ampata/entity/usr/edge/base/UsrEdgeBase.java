package ca.ampautomation.ampata.entity.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.core.DataManager;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JmixEntity
@Table(name = "AMPATA_USR_EDGE", indexes = {
        @Index(name = "IDX_USREDGE_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_USREDGE_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USREDGE_DESC1_GEN_FMLA1__ID", columnList = "DESC1_GEN_FMLA1__ID"),
        @Index(name = "IDX_AMPATA_USR_EDGE_NODE_IN__ID", columnList = "NODE_IN__ID"),
        @Index(name = "IDX_AMPATA_USR_EDGE_NODE_OT__ID", columnList = "NODE_OT__ID"),
})
@Entity(name = "enty_UsrEdgeBase")
public class UsrEdgeBase {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @TenantId
    @Column(name = "TENANT")
    protected String tenant;

    @Column(name = "DTYPE", insertable = false, updatable = false)
    protected String dtype;

    @InstanceName
    @Column(name = "ID2")
    private String id2;

    @Column(name = "ID2_CALC")
    private String id2Calc;

    @Column(name = "ID2_CMP")
    private Boolean id2Cmp;

    @Column(name = "ID2_DUP")
    private Integer id2Dup;


    @Column(name = "SORT_IDX")
    protected Integer sortIdx;

    @Column(name = "SORT_KEY")
    protected String sortKey;

    @Column(name = "NAME1")
    protected String name1;

    @JoinColumn(name = "NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla name1GenFmla1_Id;

    @Column(name = "NAME1_GEN_FMLA1__ID2")
    protected String name1GenFmla1_Id2;


    @Column(name = "NAME2")
    protected String name2;

    @JoinColumn(name = "NAME2_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla name2GenFmla1_Id;

    @Column(name = "NAME2_GEN_FMLA1__ID2")
    protected String name2GenFmla1_Id2;


    @JoinColumn(name = "TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrEdgeBaseType type1_Id;

    @Column(name = "TYPE1__ID2")
    protected String type1_Id2;

    @Column(name = "INST1")
    protected String inst1;


    @Column(name = "DESC1")
    @Lob
    protected String desc1;

    @JoinColumn(name = "DESC1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla desc1GenFmla1_Id;

    @Column(name = "DESC1_GEN_FMLA1__ID2")
    protected String desc1GenFmla1_Id2;


    @Column(name = "NOTE")
    @Lob
    protected String note;


    @JoinTable(name = "AMPATA_USR_EDGE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "EDGE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrItemGenTag> genTags1_Id;

    @Column(name = "GEN_TAGS1__ID2")
    protected String genTags1_Id2;


    @JoinColumn(name = "NODE_IN__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsrNodeBase nodeIn_Id;

    @Column(name = "NODE_IN__ID2")
    protected String nodeIn_Id2;


    @JoinColumn(name = "NODE_OT__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsrNodeBase nodeOt_Id;

    @Column(name = "NODE_OT__ID2")
    protected String nodeOt_Id2;

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


    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }


    public String getTenant() { return tenant; }

    public void setTenant(String tenant) { this.tenant = tenant; }


    public String getDtype() {return dtype; }


    public String getId2() { return id2; }

    public void setId2(String id2) { this.id2 = id2; }

    public String getId2Calc() { return id2Calc; }

    public void setId2Calc(String id2Calc) { this.id2Calc = id2Calc; }

    public Boolean getId2Cmp() { return id2Cmp; }

    public void setId2Cmp(Boolean id2Cmp) { this.id2Cmp = id2Cmp; }

    public Integer getId2Dup() { return id2Dup; }

    public void setId2Dup(Integer id2Dup) { this.id2Dup = id2Dup; }



    public Integer getSortIdx() { return sortIdx; }

    public void setSortIdx(Integer sortIdx) { this.sortIdx = sortIdx; }

    public String getSortKey() { return sortKey; }

    public void setSortKey(String sortKey) { this.sortKey = sortKey; }

    public String getName1() { return name1; }

    public void setName1(String name1) { this.name1 = name1; }


    public UsrItemGenFmla getName1GenFmla1_Id() { return name1GenFmla1_Id; }

    public void setName1GenFmla1_Id(UsrItemGenFmla name1GenFmla1_Id) { this.name1GenFmla1_Id = name1GenFmla1_Id; }

    public String getName1GenFmla1_Id2() { return name1GenFmla1_Id2; }

    public void setName1GenFmla1_Id2(String name1GenFmla1_Id2) { this.name1GenFmla1_Id2 = name1GenFmla1_Id2; }


    public String getName2() { return name2; }

    public void setName2(String name2) { this.name2 = name2; }


    public UsrItemGenFmla getName2GenFmla1_Id() { return name2GenFmla1_Id; }

    public void setName2GenFmla1_Id(UsrItemGenFmla name2GenFmla1_Id) { this.name2GenFmla1_Id = name2GenFmla1_Id; }

    public String getName2GenFmla1_Id2() { return name2GenFmla1_Id2; }

    public void setName2GenFmla1_Id2(String name2GenFmla1_Id2) { this.name2GenFmla1_Id2 = name2GenFmla1_Id2; }


    public void setType1_Id(UsrEdgeBaseType type1_Id) { this.type1_Id = type1_Id; }

    public UsrEdgeBaseType getType1_Id() { return type1_Id; }

    public String getType1_Id2() { return type1_Id2; }

    public void setType1_Id2(String type1_Id2) { this.type1_Id2 = type1_Id2; }


    public String getInst1() { return inst1; }

    public void setInst1(String inst1) { this.inst1 = inst1; }



    public String getDesc1() { return desc1; }

    public void setDesc1(String desc1) { this.desc1 = desc1; }


    public UsrItemGenFmla getDesc1GenFmla1_Id() { return desc1GenFmla1_Id; }

    public void setDesc1GenFmla1_Id(UsrItemGenFmla desc1GenFmla1_Id) { this.desc1GenFmla1_Id = desc1GenFmla1_Id; }

    public String getDesc1GenFmla1_Id2() { return desc1GenFmla1_Id2; }

    public void setDesc1GenFmla1_Id2(String desc1GenFmla1_Id2) { this.desc1GenFmla1_Id2 = desc1GenFmla1_Id2; }



    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }


    public List<UsrItemGenTag> getGenTags1_Id() { return genTags1_Id; }

    public void setGenTags1_Id(List<UsrItemGenTag> genTags1_Id) { this.genTags1_Id = genTags1_Id; }


    public String getGenTags1_Id2() { return genTags1_Id2; }

    public void setGenTags1_Id2(String genTags1_Id2) { this.genTags1_Id2 = genTags1_Id2; }


    public UsrNodeBase getNodeIn_Id() { return nodeIn_Id; }

    public void setNodeIn_Id(UsrNodeBase nodeIn_Id) { this.nodeIn_Id = nodeIn_Id; }

    public String getNodeIn_Id2() { return nodeIn_Id2; }

    public void setNodeIn_Id2(String nodeIn_Id2) { this.nodeIn_Id2 = nodeIn_Id2; }


    public UsrNodeBase getNodeOt_Id() { return nodeOt_Id; }

    public void setNodeOt_Id(UsrNodeBase nodeOt_Id) { this.nodeOt_Id = nodeOt_Id; }

    public String getNodeOt_Id2() { return nodeOt_Id2; }

    public void setNodeOt_Id2(String nodeOt_Id2) { this.nodeOt_Id2 = nodeOt_Id2; }



    public Integer getVersion() { return version; }

    public void setVersion(Integer version) { this.version = version; }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }



}