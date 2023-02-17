package ca.ampautomation.ampata.entity.sys;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.gen.SysGenFmla;
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
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "AMPATA_SYS_NODE", indexes = {
        @Index(name = "IDX_SYSNODE_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_SYSNODE_PARENT1__ID", columnList = "PARENT1__ID"),
        @Index(name = "IDX_SYSNODE_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
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
    private UUID id;

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

    @Column(name = "CLASS_NAME")
    private String className;

    @JoinColumn(name = "PARENT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysNode parent1_Id;

    @Column(name = "PARENT1__ID2")
    private String parent1_Id2;

    @Column(name = "ANCESTORS1__ID2")
    @Lob
    private String ancestors1_Id2;

    @Column(name = "SORT_IDX")
    private Integer sortIdx;

    @Column(name = "SORT_KEY")
    private String sortKey;

    @JoinColumn(name = "TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysNodeType type1_Id;

    @Column(name = "TYPE1__ID2")
    private String type1_Id2;

    @Column(name = "INST1")
    private String inst1;

    @Column(name = "NAME1")
    private String name1;

    @JoinColumn(name = "NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysGenFmla name1GenFmla1_Id;

    @Column(name = "NAME1_GEN_FMLA1__ID2")
    private String name1GenFmla1_Id2;

    @Column(name = "NAME2")
    private String name2;

    @Column(name = "ABRV", length = 16)
    private String abrv;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESC1")
    private String desc1;

    @JoinColumn(name = "DESC1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysGenFmla desc1GenFmla1_Id;

    @Column(name = "DESC1_GEN_FMLA1__ID2")
    private String desc1GenFmla1_Id2;

    @Column(name = "NOTE")
    @Lob
    private String note;



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
    private HasTmst ts1;

    @Column(name = "AMT1", precision = 19, scale = 9)
    private BigDecimal amt1;

    @Column(name = "AMT2", precision = 19, scale = 9)
    private BigDecimal amt2;

    @JoinColumn(name = "FIN_CURCY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysNode finCurcy1_Id;

    @Column(name = "FIN_CURCY1__ID2")
    private String finCurcy1_Id2;

    @JoinColumn(name = "FIN_CURCY2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysNode finCurcy2_Id;

    @Column(name = "FIN_CURCY2__ID2")
    private String finCurcy2_Id2;

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


    public String getName1() { return name1; }

    public void setName1(String name1) {this.name1 = name1; }


    public SysGenFmla getName1GenFmla1_Id() { return name1GenFmla1_Id; }

    public void setName1GenFmla1_Id(SysGenFmla name1GenFmla1_Id) { this.name1GenFmla1_Id = name1GenFmla1_Id; }

    public String getName1GenFmla1_Id2() { return name1GenFmla1_Id2; }

    public void setName1GenFmla1_Id2(String name1GenFmla1_Id2) { this.name1GenFmla1_Id2 = name1GenFmla1_Id2; }


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

    public void setFinCurcy1_Id(SysNode finCurcy1_Id) { this.finCurcy1_Id = finCurcy1_Id; }

    public String getFinCurcy1_Id2() { return finCurcy1_Id2; }

    public void setFinCurcy1_Id2(String finCurcy1_Id2) { this.finCurcy1_Id2 = finCurcy1_Id2; }

    public SysNode getFinCurcy2_Id() { return finCurcy2_Id; }

    public void setFinCurcy2_Id(SysNode finCurcy2_Id) { this.finCurcy2_Id = finCurcy2_Id; }

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


    public String getId2CalcFrFields(){
        String logPrfx = "getId2CalcFrFields";
        logger.trace(logPrfx + " --> ");

        final String SEP = "/";
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();
        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm")
                .toFormatter();
        DecimalFormat frmtDec = new DecimalFormat("+0.00;-0.00");

        //require name1
        switch (className) {
            case "FinCurcy" -> {
                if (name1 == null) {
                    logger.debug(logPrfx + " --- name1: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- name1: " + name1);
                }

            }
        }

        //require beg1.ts1
        switch (className) {
            case "FinCurcyExchRate" -> {
                if (ts1 == null) {
                    logger.debug(logPrfx + " --- ts1: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                }
                if (ts1.getElTs() == null) {
                    logger.debug(logPrfx + " --- ts1.getTs1(): null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- ts1.getTs1(): " + ts1.getElTs().format(frmtTs));
                }
            }
        }

        //require finCurcy1_Id
        switch (className) {
            case "FinCurcyExchRate" -> {
                if (finCurcy1_Id == null) {
                    logger.debug(logPrfx + " --- finCurcy1_Id: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- finCurcy1_Id: " + finCurcy1_Id.getId());
                }

            }
        }

        //require finCurcy2_Id
        switch (className) {
            case "FinCurcyExchRate" -> {
                if (finCurcy2_Id == null) {
                    logger.debug(logPrfx + " --- finCurcy2_Id: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- finCurcy2_Id: " + finCurcy2_Id.getId());
                }

            }
        }

        //update type1_Id2
        if (type1_Id != null){
            type1_Id2 = type1_Id.getId2();
        }

        //create id2
        switch (className) {
            case "FinCurcy" -> {
                sb.append(name1);
            }
            case "FinCurcyExchRate" -> {
                //finCurcy1_Id
                sb.append(finCurcy1_Id.getId2());
                //finCurcy2_Id
                sb.append( "->").append(finCurcy2_Id.getId2());
                //ts1.ts1
                sb.append(SEP + "D").append(ts1.getElTs().format(frmtDt)
                );
            }
        }


        logger.debug(logPrfx + " --- sb: " + sb);
        logger.trace(logPrfx + " <--- ");
        return sb.toString();
    }

}