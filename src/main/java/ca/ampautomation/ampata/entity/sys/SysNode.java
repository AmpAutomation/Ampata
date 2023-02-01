package ca.ampautomation.ampata.entity.sys;

import ca.ampautomation.ampata.entity.HasDate;
import ca.ampautomation.ampata.entity.usr.UsrItem;
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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "AMPATA_SYS_NODE", indexes = {
        @Index(name = "IDX_SYSNODE_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_SYSNODE_PARENT1__ID", columnList = "PARENT1__ID"),
        @Index(name = "IDX_SYSNODE_NAME1_GEN_PAT1__ID", columnList = "NAME1_GEN_PAT1__ID"),
        @Index(name = "IDX_SYSNODE_DESC1_GEN_PAT1__ID", columnList = "DESC1_GEN_PAT1__ID"),
})
@Entity(name = "ampata_SysNode")
public class SysNode {
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

    @Column(name = "INST")
    private String inst;

    @Column(name = "NAME1")
    private String name1;

    @JoinColumn(name = "NAME1_GEN_PAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysGenPat name1GenPat1_Id;

    @Column(name = "NAME1_GEN_PAT1__ID2")
    private String name1GenPat1_Id2;

    @Column(name = "ABRV", length = 16)
    private String abrv;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESC1")
    private String desc1;

    @JoinColumn(name = "DESC1_GEN_PAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SysGenPat desc1GenPat1_Id;

    @Column(name = "DESC1_GEN_PAT1__ID2")
    private String desc1GenPat1_Id2;


    @AttributeOverrides({
            @AttributeOverride(name = "date1", column = @Column(name = "BEG1_DATE1")),
            @AttributeOverride(name = "date1Yr", column = @Column(name = "BEG1_DATE1_YR")),
            @AttributeOverride(name = "date1Qtr", column = @Column(name = "BEG1_DATE1_QTR")),
            @AttributeOverride(name = "date1Mon", column = @Column(name = "BEG1_DATE1_MON")),
            @AttributeOverride(name = "date1Mon2", column = @Column(name = "BEG1_DATE1_MON2")),
            @AttributeOverride(name = "date1Day", column = @Column(name = "BEG1_DATE1_DAY"))
    })
    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    private HasDate beg1;

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

    @Transient
    protected Logger logger = LoggerFactory.getLogger(UsrItem.class);

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }
    public String getId2() { return id2; }

    public void setId2(String id2) { this.id2 = id2; }

    public String getId2Calc() { return id2Calc; }

    public void setId2Calc(String id2Calc) {this.id2Calc = id2Calc; }

    public Boolean getId2Cmp() { return id2Cmp; }

    public void setId2Cmp(Boolean id2Cmp) {this.id2Cmp = id2Cmp; }

    public Integer getId2Dup() { return id2Dup; }

    public void setId2Dup(Integer id2Dup) {this.id2Dup = id2Dup; }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public SysNodeType getType1_Id() {
        return type1_Id;
    }

    public void setType1_Id(SysNodeType type1_Id) {
        this.type1_Id = type1_Id;
    }

    public Integer getSortIdx() { return sortIdx; }

    public void setSortIdx(Integer sortIdx) {this.sortIdx = sortIdx; }

    public String getSortKey() { return sortKey; }

    public void setSortKey(String sortKey) {this.sortKey = sortKey; }

    public String getName1() { return name1; }

    public void setName1(String name1) {this.name1 = name1; }

    public String getDesc1() { return desc1; }

    public void setDesc1(String desc1) {this.desc1 = desc1; }


    public HasDate getBeg1() {
        return beg1;
    }

    public void setBeg1(HasDate beg1) {
        this.beg1 = beg1;
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

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        if (beg1 == null) {
            logger.debug(logPrfx + " --- beg: null");
            logger.trace(logPrfx + " <--- ");
            return "";
        }
        if (beg1.getDate1() == null) {
            logger.debug(logPrfx + " --- beg.date1: null");
            logger.trace(logPrfx + " <--- ");
            return "";
        } else {
            logger.debug(logPrfx + " --- beg.date1: " + beg1.getDate1().format(frmtDt));
        }

        if (finCurcy1_Id == null) {
            logger.debug(logPrfx + " --- finCurcy1_Id: null");
            logger.trace(logPrfx + " <--- ");
            return "";
        } else {
            logger.debug(logPrfx + " --- finCurcy1_Id: " + finCurcy1_Id.getId());
        }

        if (finCurcy2_Id == null) {
            logger.debug(logPrfx + " --- finCurcy2_Id: null");
            logger.trace(logPrfx + " <--- ");
            return "";
        } else {
            logger.debug(logPrfx + " --- finCurcy2_Id: " + finCurcy2_Id.getId());
        }

        sb.append(finCurcy1_Id.getId2() + "->" + finCurcy2_Id.getId2()
                + SEP + "D" + beg1.getDate1().format(frmtDt)
        );

        logger.debug(logPrfx + " --- sb: " + sb);
        logger.trace(logPrfx + " <--- ");
        return sb.toString();
    }

}