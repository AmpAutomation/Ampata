package ca.ampautomation.ampata.entity.usr.node.base;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import io.jmix.core.DataManager;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.jetbrains.annotations.NotNull;
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
@Table(name = "AMPATA_USR_NODE_TYPE", indexes = {
        @Index(name = "IDX_USRNODETYPE_PARENT1__ID", columnList = "PARENT1__ID"),
        @Index(name = "IDX_USRNODETYPE_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODETYPE_DESC1_GEN_FMLA1__ID", columnList = "DESC1_GEN_FMLA1__ID"),
})
@Entity(name = "enty_UsrNodeBaseType")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class UsrNodeBaseType {

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

    @Column(name = "CLASS_NAME")
    private String className;

    @InstanceName
    @Column(name = "ID2")
    private String id2;

    @Column(name = "ID2_CALC")
    private String id2Calc;

    @Column(name = "ID2_CMP")
    private Boolean id2Cmp;

    @Column(name = "ID2_DUP")
    private Integer id2Dup;

    @JoinColumn(name = "PARENT1__ID")
    @OneToOne(fetch = FetchType.LAZY)
    private UsrNodeBaseType parent1_Id;

    @Column(name = "PARENT1__ID2")
    private String parent1_Id2;

    @Column(name = "ANCESTORS1__ID2")
    @Lob
    private String ancestors1_Id2;

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

    @Column(name = "NAME2")
    private String name2;

    @Column(name = "ABRV", length = 16)
    private String abrv;

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

    @JoinTable(name = "AMPATA_USR_NODE_TYPE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "NODE_TYPE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrItemGenTag> genTags1_Id;

    @Column(name = "GEN_TAGS1__ID2")
    protected String genTags1_Id2;



    @Column(name = "TXT1")
    @Lob
    protected String txt1;

    @Column(name = "TXT2")
    @Lob
    protected String txt2;

    @Column(name = "TXT4")
    @Lob
    protected String txt4;

    @Column(name = "TXT3")
    @Lob
    protected String txt3;

    @Column(name = "INT1")
    protected Integer int1;

    @Column(name = "INT2")
    protected Integer int2;

    @Column(name = "INT3")
    protected Integer int3;

    @Column(name = "INT4")
    protected Integer int4;



    @Column(name = "BAL_INC_ON_DEBT")
    private Boolean balIncOnDebt;

    @Column(name = "BAL_INC_ON_CRED")
    private Boolean balIncOnCred;

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


    public String getTenantId() { return tenant; }

    public String getTenant() { return tenant; }

    public void setTenant(String tenant) { this.tenant = tenant; }


    public String getDtype() {return dtype; }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }



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


    public UsrNodeBaseType getParent1_Id() {
        return parent1_Id;
    }

    public void setParent1_Id(UsrNodeBaseType parent1_Id) {
        this.parent1_Id = parent1_Id;
    }

    public String getParent1_Id2() {
        return parent1_Id2;
    }

    public void setParent1_Id2(String parent1_Id2) {
        this.parent1_Id2 = parent1_Id2;
    }

    public String getAncestors1_Id2() {
        return ancestors1_Id2;
    }

    public void setAncestors1_Id2(String ancestors1_Id2) {
        this.ancestors1_Id2 = ancestors1_Id2;
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


    public String getAbrv() {
        return abrv;
    }

    public void setAbrv(String abrv) {
        this.abrv = abrv;
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


    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
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




    public String getTxt1() { return txt1; }

    public void setTxt1(String txt1) { this.txt1 = txt1; }

    public String getTxt2() { return txt2; }

    public void setTxt2(String txt2) { this.txt2 = txt2; }

    public String getTxt3() { return txt3; }

    public void setTxt3(String txt3) { this.txt3 = txt3; }

    public String getTxt4() { return txt4; }

    public void setTxt4(String txt4) { this.txt4 = txt4; }


    public Integer getInt1() { return int1; }

    public void setInt1(Integer int1) { this.int1 = int1; }

    public Integer getInt2() { return int2; }

    public void setInt2(Integer int2) { this.int2 = int2; }

    public Integer getInt3() { return int3; }

    public void setInt3(Integer int3) { this.int3 = int3; }

    public Integer getInt4() { return int4; }

    public void setInt4(Integer int4) { this.int4 = int4; }


    public void setBalIncOnDebt(Boolean balIncOnDebt) {
        this.balIncOnDebt = balIncOnDebt;
    }

    public Boolean getBalIncOnDebt() {
        return balIncOnDebt;
    }

    public void setBalIncOnCred(Boolean balIncOnCred) {
        this.balIncOnCred = balIncOnCred;
    }

    public Boolean getBalIncOnCred() {
        return balIncOnCred;
    }


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
