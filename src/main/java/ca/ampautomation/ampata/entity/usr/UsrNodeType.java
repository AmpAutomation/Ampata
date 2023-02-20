package ca.ampautomation.ampata.entity.usr;

import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
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
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@JmixEntity
@Table(name = "AMPATA_USR_NODE_TYPE", indexes = {
        @Index(name = "IDX_USRNODETYPE_PARENT1__ID", columnList = "PARENT1__ID"),
        @Index(name = "IDX_USRNODETYPE_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODETYPE_DESC1_GEN_FMLA1__ID", columnList = "DESC1_GEN_FMLA1__ID"),
})
@Entity(name = "enty_UsrNodeType")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class UsrNodeType implements AcceptsTenant {

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
    private UsrNodeType parent1_Id;

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
    private UsrGenFmla name1GenFmla1_Id;

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
    private UsrGenFmla desc1GenFmla1_Id;

    @Column(name = "DESC1_GEN_FMLA1__ID2")
    private String desc1GenFmla1_Id2;

    @Column(name = "NOTE")
    @Lob
    private String note;


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


    @Override
    public String getTenantId() { return tenant;}

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


    public UsrNodeType getParent1_Id() {
        return parent1_Id;
    }

    public void setParent1_Id(UsrNodeType parent1_Id) {
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


    public UsrGenFmla getName1GenFmla1_Id() { return name1GenFmla1_Id; }

    public void setName1GenFmla1_Id(UsrGenFmla name1GenFmla1_Id) {
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


    public UsrGenFmla getDesc1GenFmla1_Id() { return desc1GenFmla1_Id; }

    public void setDesc1GenFmla1_Id(UsrGenFmla desc1GenFmla1_Id) {
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



    public Boolean updateCalcVals(DataManager dataManager){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateDesc1(dataManager) || isChanged;
        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2(DataManager dataManager) {
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String l_id2_ = this.id2;
        String l_id2 = this.id2Calc;
        if(!Objects.equals(l_id2_, l_id2)){
            this.id2 = l_id2;
            logger.debug(logPrfx + " --- id2: " + l_id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2Deps(DataManager dataManager) {
        String logPrfx = "updateId2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2Calc(DataManager dataManager) {
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        final String delim = "/";
        String l_id2Calc_ = this.name1;
        String l_id2Calc = this.parent1_Id != null
                ? this.parent1_Id2 + delim
                + (this.name1 != null ? this.name1 : "<null>")
                : this.name1 ;
        if (!Objects.equals(l_id2Calc_, l_id2Calc)) {
            this.id2Calc = l_id2Calc;
            logger.debug(logPrfx + " --- id2Calc:" + l_id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2CalDeps(DataManager dataManager) {
        String logPrfx = "updateId2CalDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2Cmp(DataManager dataManager) {
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean l_id2Cmp_ = this.id2Cmp;
        Boolean l_id2Cmp = !Objects.equals(this.id2,this.id2Calc);
        if (!Objects.equals(l_id2Cmp_, l_id2Cmp)){
            this.id2Cmp = l_id2Cmp;
            logger.debug(logPrfx + " --- id2Cmp: " + l_id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2Dup(DataManager dataManager) {
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer l_id2Dup_ = this.id2Dup;
        if (this.id2 != null){
            String id2Qry = "select count(e) from enty_" + this.getClass().getSimpleName() + " e"
                    + " where e.id2 = :id2"
                    + " and e.id <> :id";
            Integer l_id2Dup;
            try{
                l_id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",this.id)
                        .parameter("id2",this.id2)
                        .one();
            }catch (IllegalStateException e){
                l_id2Dup =0;

            }
            l_id2Dup = l_id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + l_id2Dup + " rows");
            if (!Objects.equals(l_id2Dup_, l_id2Dup)){
                this.id2Dup = l_id2Dup;
                logger.debug(logPrfx + " --- id2Dup: " + l_id2Dup);
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateName1(DataManager dataManager){
        String logPrfx = "updateName1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateName1Deps(DataManager dataManager) {
        String logPrfx = "updateName1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


}
