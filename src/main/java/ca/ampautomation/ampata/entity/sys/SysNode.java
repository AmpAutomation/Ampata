package ca.ampautomation.ampata.entity.sys;

import ca.ampautomation.ampata.entity.HasDate;
import ca.ampautomation.ampata.entity.HasTime;
import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcy;
import ca.ampautomation.ampata.entity.sys.gen.SysGenFmla;
import ca.ampautomation.ampata.entity.sys.SysNodeType;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import io.jmix.core.DataManager;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@JmixEntity
@Table(name = "AMPATA_SYS_NODE", indexes = {
        @Index(name = "IDX_SYSNODE_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_SYSNODE_PARENT1__ID", columnList = "PARENT1__ID"),
        @Index(name = "IDX_SYSNODE_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_SYSNODE_NM1S1_TYPE1__ID", columnList = "NM1S1_TYPE1__ID"),
        @Index(name = "IDX_SYSNODE_NM1S1_NAME1_GEN_FMLA1__ID", columnList = "NM1S1_NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_SYSNODE_NM1S1_INST1_GEN_FMLA1__ID", columnList = "NM1S1_INST1_GEN_FMLA1__ID"),
        @Index(name = "IDX_SYSNODE_NM1S1_INST1_NODE1__ID", columnList = "NM1S1_INST1_NODE1__ID"),
        @Index(name = "IDX_SYSNODE_DESC1_GEN_FMLA1__ID", columnList = "DESC1_GEN_FMLA1__ID"),
})
@Entity(name = "enty_SysNode")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
public class SysNode {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    protected UUID id;

    @Column(name="DTYPE", insertable = false, updatable = false)
    protected String dtype;

    @InstanceName
    @Column(name = "ID2")
    protected String id2;

    @Column(name = "ID2_CALC")
    protected String id2Calc;

    @Column(name = "ID2_CMP")
    protected Boolean id2Cmp;

    @Column(name = "ID2_DUP")
    protected Integer id2Dup;

    @Column(name = "CLASS_NAME")
    protected String className;

    @JoinColumn(name = "PARENT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysNode parent1_Id;

    @Column(name = "PARENT1__ID2")
    protected String parent1_Id2;

    @Column(name = "ANCESTORS1__ID2")
    @Lob
    protected String ancestors1_Id2;

    @Column(name = "SORT_IDX")
    protected Integer sortIdx;

    @Column(name = "SORT_KEY")
    protected String sortKey;

    @Column(name = "NAME1")
    protected String name1;

    @JoinColumn(name = "NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysGenFmla name1GenFmla1_Id;

    @Column(name = "NAME1_GEN_FMLA1__ID2")
    protected String name1GenFmla1_Id2;

    @JoinColumn(name = "TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysNodeType type1_Id;

    @Column(name = "TYPE1__ID2")
    protected String type1_Id2;

    @Column(name = "INST1")
    protected String inst1;

    @Column(name = "NM1S1_NAME1")
    protected String nm1s1Name1;

    @JoinColumn(name = "NM1S1_NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysGenFmla nm1s1Name1GenFmla1_Id;

    @Column(name = "NM1S1_NAME1_GEN_FMLA1__ID2")
    protected String nm1s1Name1GenFmla1_Id2;

    @JoinColumn(name = "NM1S1_TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysNodeType nm1s1Type1_Id;

    @Column(name = "NM1S1_TYPE1__ID2")
    protected String nm1s1Type1_Id2;


    @Column(name = "NM1S1_INST1")
    protected String nm1s1Inst1;

    @JoinColumn(name = "NM1S1_INST1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysGenFmla nm1s1Inst1GenFmla1_Id;

    @Column(name = "NM1S1_INST1_GEN_FMLA1__ID2")
    protected String nm1s1Inst1GenFmla1_Id2;


    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "elTs", column = @Column(name = "NM1S1_INST1_TS1_EL_TS")),
            @AttributeOverride(name = "elDt", column = @Column(name = "NM1S1_INST1_TS1_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_DAY")),
            @AttributeOverride(name = "elTm", column = @Column(name = "NM1S1_INST1_TS1_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "NM1S1_INST1_TS1_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "NM1S1_INST1_TS1_EL_TM_MIN"))
    })
    protected HasTmst nm1s1Inst1Ts1;

    @Column(name = "NM1S1_INST1_INT1")
    protected Integer nm1s1Inst1Int1;

    @Column(name = "NM1S1_INST1_INT2")
    protected Integer nm1s1Inst1Int2;

    @Column(name = "NM1S1_INST1_INT3")
    protected Integer nm1s1Inst1Int3;

    @Column(name = "NM1S1_INST1_TXT1")
    protected String nm1s1Inst1Txt1;

    @Column(name = "NM1S1_INST1_TXT2")
    protected String nm1s1Inst1Txt2;

    @JoinColumn(name = "NM1S1_INST1_NODE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode nm1s1Inst1Node1_Id;

    @Column(name = "NM1S1_INST1_NODE1__ID2")
    protected String nm1s1Inst1Node1_Id2;


    @Column(name = "NAME2")
    protected String name2;

    @Column(name = "ABRV", length = 16)
    protected String abrv;

    @Column(name = "CODE")
    protected String code;

    @Column(name = "DESC1")
    protected String desc1;

    @JoinColumn(name = "DESC1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysGenFmla desc1GenFmla1_Id;

    @Column(name = "DESC1_GEN_FMLA1__ID2")
    protected String desc1GenFmla1_Id2;

    @Column(name = "NOTE")
    @Lob
    protected String note;



    @AttributeOverrides({
            @AttributeOverride(name = "elTs", column = @Column(name = "TS1_EL_TS")),
            @AttributeOverride(name = "elDt", column = @Column(name = "TS1_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "TS1_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "TS1_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "TS1_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "TS1_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "TS1_EL_DT_DAY")),
            @AttributeOverride(name = "elTm", column = @Column(name = "TS1_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "TS1_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "TS1_EL_TM_MIN"))
    })
    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    protected HasTmst ts1;

    @Column(name = "AMT1", precision = 19, scale = 9)
    protected BigDecimal amt1;

    @Column(name = "AMT2", precision = 19, scale = 9)
    protected BigDecimal amt2;

    @JoinColumn(name = "FIN_CURCY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysFinCurcy finCurcy1_Id;

    @Column(name = "FIN_CURCY1__ID2")
    protected String finCurcy1_Id2;

    @JoinColumn(name = "FIN_CURCY2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysFinCurcy finCurcy2_Id;

    @Column(name = "FIN_CURCY2__ID2")
    protected String finCurcy2_Id2;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;


    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getDtype() {return dtype; }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public String getId2() { return id2; }

    public void setId2(String id2) { this.id2 = id2; }

    public String getId2Calc() { return id2Calc; }

    public void setId2Calc(String id2Calc) {this.id2Calc = id2Calc; }

    public Boolean getId2Cmp() { return id2Cmp; }

    public void setId2Cmp(Boolean id2Cmp) {this.id2Cmp = id2Cmp; }

    public Integer getId2Dup() { return id2Dup; }

    public void setId2Dup(Integer id2Dup) {this.id2Dup = id2Dup; }

    public SysNode getParent1_Id() {
        return parent1_Id;
    }

    public void setParent1_Id(SysNode parent1_Id) {
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

    public Integer getSortIdx() { return sortIdx; }

    public void setSortIdx(Integer sortIdx) {this.sortIdx = sortIdx; }

    public String getSortKey() { return sortKey; }

    public void setSortKey(String sortKey) {this.sortKey = sortKey; }


    public String getName1() { return name1; }

    public void setName1(String name1) {this.name1 = name1; }


    public SysGenFmla getName1GenFmla1_Id() { return name1GenFmla1_Id; }

    public void setName1GenFmla1_Id(SysGenFmla name1GenFmla1_Id) { this.name1GenFmla1_Id = name1GenFmla1_Id; }

    public String getName1GenFmla1_Id2() { return name1GenFmla1_Id2; }

    public void setName1GenFmla1_Id2(String name1GenFmla1_Id2) { this.name1GenFmla1_Id2 = name1GenFmla1_Id2; }

    
    public void setType1_Id(SysNodeType type1_Id) {
        this.type1_Id = type1_Id;
    }

    public SysNodeType getType1_Id() {
        return type1_Id;
    }

    public String getType1_Id2() {
        return type1_Id2;
    }

    public void setType1_Id2(String type1_Id2) {
        this.type1_Id2 = type1_Id2;
    }


    public String getInst1() {
        return inst1;
    }

    public void setInst1(String inst1) {
        this.inst1 = inst1;
    }


    public SysNodeType getNm1s1Type1_Id() {
        return nm1s1Type1_Id;
    }

    public void setNm1s1Type1_Id(SysNodeType nm1s1Type1_Id) {
        this.nm1s1Type1_Id = nm1s1Type1_Id;
    }


    public String getNm1s1Type1_Id2() {
        return nm1s1Type1_Id2;
    }

    public void setNm1s1Type1_Id2(String nm1s1Type1_Id2) {
        this.nm1s1Type1_Id2 = nm1s1Type1_Id2;
    }


    public String getNm1s1Name1() {
        return nm1s1Name1;
    }

    public void setNm1s1Name1(String nm1s1Name1) {
        this.nm1s1Name1 = nm1s1Name1;
    }

    public SysGenFmla getNm1s1Name1GenFmla1_Id() { return nm1s1Name1GenFmla1_Id; }

    public void setNm1s1Name1GenFmla1_Id(SysGenFmla nm1s1Name1GenFmla1_Id) { this.nm1s1Name1GenFmla1_Id = nm1s1Name1GenFmla1_Id; }


    public String getNm1s1Name1GenFmla1_Id2() {
        return nm1s1Name1GenFmla1_Id2;
    }

    public void setNm1s1Name1GenFmla1_Id2(String nm1s1Name1GenFmla1_Id2) { this.nm1s1Name1GenFmla1_Id2 = nm1s1Name1GenFmla1_Id2; }


    public String getNm1s1Inst1() { return nm1s1Inst1; }

    public void setNm1s1Inst1(String nm1s1Inst1) { this.nm1s1Inst1 = nm1s1Inst1; }

    public SysGenFmla getNm1s1Inst1GenFmla1_Id() { return nm1s1Inst1GenFmla1_Id; }

    public void setNm1s1Inst1GenFmla1_Id(SysGenFmla nm1s1Inst1GenFmla1_Id) { this.nm1s1Inst1GenFmla1_Id = nm1s1Inst1GenFmla1_Id; }


    public String getNm1s1Inst1GenFmla1_Id2() {
        return nm1s1Inst1GenFmla1_Id2;
    }

    public void setNm1s1Inst1GenFmla1_Id2(String nm1s1Inst1GenFmla1_Id2) { this.nm1s1Inst1GenFmla1_Id2 = nm1s1Inst1GenFmla1_Id2; }


    public HasTmst getNm1s1Inst1Ts1() {
        return nm1s1Inst1Ts1;
    }

    public void setNm1s1Inst1Ts1(HasTmst nm1s1Inst1Ts1) {
        this.nm1s1Inst1Ts1 = nm1s1Inst1Ts1;
    }



    public Integer getNm1s1Inst1Int1() {
        return nm1s1Inst1Int1;
    }

    public void setNm1s1Inst1Int1(Integer nm1s1Inst1Int1) {
        String logPrfx = "setNm1s1Inst1Int1";
        logger.trace(logPrfx + " --> ");
        this.nm1s1Inst1Int1 = nm1s1Inst1Int1;

        logger.trace(logPrfx + " <-- ");
    }

    public Integer getNm1s1Inst1Int2() {
        return nm1s1Inst1Int2;
    }

    public void setNm1s1Inst1Int2(Integer nm1s1Inst1Int2) {
        String logPrfx = "setNm1s1Inst1Int2";
        logger.trace(logPrfx + " --> ");
        this.nm1s1Inst1Int2 = nm1s1Inst1Int2;

        logger.trace(logPrfx + " <-- ");
    }

    public Integer getNm1s1Inst1Int3() {
        return nm1s1Inst1Int3;
    }

    public void setNm1s1Inst1Int3(Integer nm1s1Inst1Int3) {
        String logPrfx = "setNm1s1Inst1Int3";
        logger.trace(logPrfx + " --> ");

        this.nm1s1Inst1Int3 = nm1s1Inst1Int3;

        logger.trace(logPrfx + " <-- ");
    }


    public String getNm1s1Inst1Txt1() {
        return nm1s1Inst1Txt1;
    }

    public void setNm1s1Inst1Txt1(String nm1s1Inst1Txt1) { this.nm1s1Inst1Txt1 = nm1s1Inst1Txt1; }

    public String getNm1s1Inst1Txt2() { return nm1s1Inst1Txt2; }

    public void setNm1s1Inst1Txt2(String nm1s1Inst1Txt2) { this.nm1s1Inst1Txt2 = nm1s1Inst1Txt2; }


    public UsrNode getNm1s1Inst1Node1_Id() { return nm1s1Inst1Node1_Id; }

    public void setNm1s1Inst1Node1_Id(UsrNode nm1s1Inst1Node1_Id) { this.nm1s1Inst1Node1_Id = nm1s1Inst1Node1_Id; }


    public String getName2() { return name2; }

    public void setName2(String name2) {this.name2 = name2; }


    public String getAbrv() {
        return abrv;
    }

    public void setAbrv(String abrv) {
        this.abrv = abrv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.abrv = code;
    }

    
    public String getDesc1() { return desc1; }

    public void setDesc1(String desc1) {this.desc1 = desc1; }


    public SysGenFmla getDesc1GenFmla1_Id() { return desc1GenFmla1_Id; }

    public void setDesc1GenFmla1_Id(SysGenFmla desc1GenFmla1_Id) { this.desc1GenFmla1_Id = desc1GenFmla1_Id; }

    public String getDesc1GenFmla1_Id2() { return desc1GenFmla1_Id2; }

    public void setDesc1GenFmla1_Id2(String desc1GenFmla1_Id2) { this.desc1GenFmla1_Id2 = desc1GenFmla1_Id2; }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public void setTs1(HasTmst ts1) {
        String logPrfx = "setBeg";
        logger.trace(logPrfx + " --> ");
        this.ts1 = ts1;

        logger.trace(logPrfx + " <-- ");
    }

    public HasTmst getTs1() {
        return ts1;
    }


    public SysNode getFinCurcy1_Id() { return finCurcy1_Id; }

    public void setFinCurcy1_Id(SysFinCurcy finCurcy1_Id) { this.finCurcy1_Id = finCurcy1_Id; }

    public String getFinCurcy1_Id2() { return finCurcy1_Id2; }

    public void setFinCurcy1_Id2(String finCurcy1_Id2) { this.finCurcy1_Id2 = finCurcy1_Id2; }

    public SysNode getFinCurcy2_Id() { return finCurcy2_Id; }

    public void setFinCurcy2_Id(SysFinCurcy finCurcy2_Id) { this.finCurcy2_Id = finCurcy2_Id; }

    public String getFinCurcy2_Id2() { return finCurcy2_Id2; }

    public void setFinCurcy2_Id2(String finCurcy2_Id2) { this.finCurcy2_Id2 = finCurcy2_Id2; }


    public BigDecimal getAmt1() {
        return amt1;
    }

    public void setAmt1(BigDecimal amt1) {
        this.amt1 = amt1;
    }

    public BigDecimal getAmt2() {
        return amt2;
    }

    public void setAmt2(BigDecimal amt2) {
        this.amt2 = amt2;
    }


    public Integer getVersion() { return version; }

    public void setVersion(Integer version) { this.version = version; }

    public Date getCreatedDate() { return createdDate; }

    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public String getCreatedBy() { return createdBy; }

    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public Date getLastModifiedDate() { return lastModifiedDate; }

    public void setLastModifiedDate(Date lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }

    public String getLastModifiedBy() { return lastModifiedBy; }

    public void setLastModifiedBy(String lastModifiedBy) { this.lastModifiedBy = lastModifiedBy; }

    public Date getDeletedDate() { return deletedDate; }

    public void setDeletedDate(Date deletedDate) { this.deletedDate = deletedDate; }

    public String getDeletedBy() { return deletedBy; }

    public void setDeletedBy(String deletedBy) { this.deletedBy = deletedBy; }



    public Boolean updateCalcVals(DataManager dataManager){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateDesc1(dataManager) || isChanged;
        isChanged = this.updateInst1(dataManager) || isChanged;
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

    public Boolean updateId2CalcDeps(DataManager dataManager) {
        String logPrfx = "updateId2CalcDeps";
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
        String l_name1_ = this.name1;
        String l_name1 = this.inst1;
        if (!Objects.equals(l_name1_, l_name1)) {
            this.name1 = l_name1;
            logger.debug(logPrfx + " --- name1:" + l_name1);
            isChanged = true;
        }

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

    public Boolean updateType1_IdDeps(DataManager dataManager) {
        String logPrfx = "updateType1_IdDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInst1(DataManager dataManager){
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInst1Deps(DataManager dataManager) {
        String logPrfx = "updateInst1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateName1(dataManager) || isChanged;
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

    public Boolean updateFinCurcy1_IdDeps(DataManager dataManager) {
        String logPrfx = "updateFinCurcy1_IdDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateInst1(dataManager) || isChanged;
        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinCurcy2_IdDeps(DataManager dataManager) {
        String logPrfx = "updateFinCurcy2_IdDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateInst1(dataManager) || isChanged;
        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTs1Deps(DataManager dataManager) {
        String logPrfx = "updateTs1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateInst1(dataManager) || isChanged;
        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmt2(DataManager dataManager) {
        String logPrfx = "updateAmt2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}