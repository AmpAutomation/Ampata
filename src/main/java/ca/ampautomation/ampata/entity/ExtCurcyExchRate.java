package ca.ampautomation.ampata.entity;

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
@Table(name = "AMPATA_EXT_CURCY_EXCH_RATE")
@Entity(name = "ampata_ExtCurcyExchRate")
public class ExtCurcyExchRate {
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

    @Column(name = "SORT_IDX")
    private Integer sortIdx;

    @Column(name = "SORT_KEY")
    private String sortKey;

    @Column(name = "NAME1")
    private String name1;

    @Column(name = "DESC1")
    private String desc1;


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

    @Column(name = "FIN_CURCY1__ID2")
    private String finCurcy1_Id2;

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
    protected Logger logger = LoggerFactory.getLogger(SysEntity.class);

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

    public String getFinCurcy2_Id2() {
        return finCurcy2_Id2;
    }

    public void setFinCurcy2_Id2(String finCurcy2_Id2) {
        this.finCurcy2_Id2 = finCurcy2_Id2;
    }

    public String getFinCurcy1_Id2() {
        return finCurcy1_Id2;
    }

    public void setFinCurcy1_Id2(String finCurcy1_Id2) {
        this.finCurcy1_Id2 = finCurcy1_Id2;
    }

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

        if (finCurcy1_Id2 == null) {
            logger.debug(logPrfx + " --- finCurcy1_Id2: null");
            logger.trace(logPrfx + " <--- ");
            return "";
        } else {
            logger.debug(logPrfx + " --- finCurcy1_Id2: " + finCurcy1_Id2);
        }

        if (finCurcy2_Id2 == null) {
            logger.debug(logPrfx + " --- finCurcy2_Id2: null");
            logger.trace(logPrfx + " <--- ");
            return "";
        } else {
            logger.debug(logPrfx + " --- finCurcy2_Id2: " + finCurcy2_Id2);
        }

        sb.append(finCurcy1_Id2 + "->" + finCurcy2_Id2
                + SEP + "D" + beg1.getDate1().format(frmtDt)
        );

        logger.debug(logPrfx + " --- sb: " + sb);
        logger.trace(logPrfx + " <--- ");
        return sb.toString();
    }

}