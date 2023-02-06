package ca.ampautomation.ampata.entity.usr;

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

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@JmixEntity
@Table(name = "AMPATA_USR_ITEM", indexes = {
        @Index(name = "IDX_USRITEM_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_USRITEM_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRITEM_DESC1_GEN_FMLA1__ID", columnList = "DESC1_GEN_FMLA1__ID")
})
@Entity(name = "enty_UsrItem")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class UsrItem implements AcceptsTenant {

    //Common
    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @TenantId
    @Column(name = "TENANT")
    private String tenant;

    @Column(name="DTYPE", insertable = false, updatable = false)
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

    @JoinColumn(name = "TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsrItemType type1_Id;

    @Column(name = "TYPE1__ID2")
    private String type1_Id2;

    @Column(name = "SORT_IDX")
    private Integer sortIdx;

    @Column(name = "SORT_KEY")
    private String sortKey;

    @Column(name = "INST1")
    private String inst1;

    @Column(name = "NAME1")
    private String name1;

    @JoinColumn(name = "NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsrItem name1GenFmla1_Id;

    @Column(name = "NAME1_GEN_FMLA1__ID2")
    private String name1GenFmla1_Id2;

    @Column(name = "DESC1")
    private String desc1;

    @JoinColumn(name = "DESC1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsrItem desc1GenFmla1_Id;

    @Column(name = "DESC1_GEN_FMLA1__ID2")
    private String desc1GenFmla1_Id2;

    @Column(name = "NOTE")
    @Lob
    private String note;

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
    public String getTenantId() {
        return tenant;
    }

    public String getTenant() { return tenant; }

    public void setTenant(String tenant) { this.tenant = tenant; }

    public String getDtype() { return dtype; }

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

    public UsrItemType getType1_Id() {
        return type1_Id;
    }

    public void setType1_Id(UsrItemType type1_Id) {
        this.type1_Id = type1_Id;
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

    public UsrItem getName1GenFmla1_Id() { return name1GenFmla1_Id; }

    public void setName1GenFmla1_Id(UsrItem name1GenFmla1_Id) { this.name1GenFmla1_Id = name1GenFmla1_Id; }

    public String getName1GenFmla1_Id2() { return name1GenFmla1_Id2; }

    public void setName1GenFmla1_Id2(String name1GenFmla1_Id2) { this.name1GenFmla1_Id2 = name1GenFmla1_Id2; }

    public String getInst1() {
        return inst1;
    }

    public void setInst1(String inst1) {
        this.inst1 = inst1;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public UsrItem getDesc1GenFmla1_Id() { return desc1GenFmla1_Id; }

    public void setDesc1GenFmla1_Id(UsrItem desc1GenFmla1_Id) { this.desc1GenFmla1_Id = desc1GenFmla1_Id; }

    public String getDesc1GenFmla1_Id2() { return desc1GenFmla1_Id2; }

    public void setDesc1GenFmla1_Id2(String desc1GenFmla1_Id2) { this.desc1GenFmla1_Id2 = desc1GenFmla1_Id2; }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

        isChanged = this.updateId2Calc() || isChanged;
        isChanged = this.updateId2Cmp() || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateId2() {
        // Assume this is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = this.getId2();
        String id2 = this.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            this.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateId2Calc(){
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = this.getId2Calc();
        String id2Calc = this.getName1();
        if(!Objects.equals(id2Calc_, id2Calc)){
            this.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2Cmp() {
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = this.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(this.getId2(),this.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            this.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2Dup(DataManager dataManager) {
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = this.getId2Dup();
        if (this.getId2() != null){
            String id2Qry = "select count(e) from enty_" + this.getClass().getSimpleName() + " e"
                    + " where e.id2 = :id2"
                    + " and e.id <> :id";
            Integer id2Dup;
            try{
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",this.getId())
                        .parameter("id2",this.getId2())
                        .one();
            }catch (IllegalStateException e){
                id2Dup =0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                this.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- this.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateDesc1(){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String desc1_ = this.getDesc1();
        String desc1 = this.getInst1() != null ? this.getInst1()
                : this.getName1() != null ? this.getName1()
                : "";
        if (!Objects.equals(desc1_, desc1)){
            this.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


}