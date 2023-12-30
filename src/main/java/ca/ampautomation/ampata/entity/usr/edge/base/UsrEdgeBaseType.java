package ca.ampautomation.ampata.entity.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import io.jmix.core.DataManager;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.multitenancy.core.AcceptsTenant;
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
@Table(name = "AMPATA_USR_EDGE_TYPE", indexes = {
        @Index(name = "IDX_USREDGETYPE_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USREDGETYPE_DESC1_GEN_FMLA1__ID", columnList = "DESC1_GEN_FMLA1__ID"),
})
@Entity(name = "enty_UsrEdgeBaseType")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class UsrEdgeBaseType implements AcceptsTenant {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name="DTYPE", insertable = false, updatable = false)
    protected String dtype;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

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
    private Integer sortIdx;

    @Column(name = "SORT_KEY")
    private String sortKey;

    @Column(name = "NAME1")
    private String name1;

    @JoinColumn(name = "NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsrItemGenFmla name1GenFmla1_Id;

    @Column(name = "NAME1_GEN_FMLA1__ID2")
    private String name1GenFmla1_Id2;

    @Column(name = "DESC1")
    @Lob
    private String desc1;

    @JoinColumn(name = "DESC1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsrItemGenFmla desc1GenFmla1_Id;

    @Column(name = "DESC1_GEN_FMLA1__ID2")
    private String desc1GenFmla1_Id2;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @JoinTable(name = "AMPATA_USR_EDGE_TYPE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "EDGE_TYPE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrItemGenTag> genTags1_Id;

    @Column(name = "GEN_TAGS1__ID2")
    protected String genTags1_Id2;


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


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    @Override
    public String getTenantId() { return tenant;}

    public String getTenant() { return tenant; }

    public void setTenant(String tenant) { this.tenant = tenant; }


    public String getDtype() {return dtype; }


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



    public Integer getSortIdx() {
        return sortIdx;
    }

    public void setSortIdx(Integer sortIdx) {
        this.sortIdx = sortIdx;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }


    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }


    public UsrItemGenFmla getName1GenFmla1_Id() { return name1GenFmla1_Id; }

    public void setName1GenFmla1_Id(UsrItemGenFmla name1GenFmla1_Id) {
        this.name1GenFmla1_Id = name1GenFmla1_Id;
    }

    public String getName1GenFmla1_Id2() {
        return name1GenFmla1_Id2;
    }

    public void setName1GenFmla1_Id2(String name1GenFmla1_Id2) {
        this.name1GenFmla1_Id2 = name1GenFmla1_Id2;
    }



    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }


    public UsrItemGenFmla getDesc1GenFmla1_Id() { return desc1GenFmla1_Id; }

    public void setDesc1GenFmla1_Id(UsrItemGenFmla desc1GenFmla1_Id) {
        this.desc1GenFmla1_Id = desc1GenFmla1_Id;
    }

    public String getDesc1GenFmla1_Id2() {
        return desc1GenFmla1_Id2;
    }

    public void setDesc1GenFmla1_Id2(String desc1GenFmla1_Id2) {
        this.desc1GenFmla1_Id2 = desc1GenFmla1_Id2;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public List<UsrItemGenTag> getGenTags1_Id() { return genTags1_Id; }

    public void setGenTags1_Id(List<UsrItemGenTag> genTags1_Id) { this.genTags1_Id = genTags1_Id; }


    public String getGenTags1_Id2() { return genTags1_Id2; }

    public void setGenTags1_Id2(String genTags1_Id2) { this.genTags1_Id2 = genTags1_Id2; }


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


}
