package ca.ampautomation.ampata.entity;

import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@JmixEntity
@Entity(name = "ampata_FinRate")
public class FinRate extends GenEntity {

    @AttributeOverrides({
            @AttributeOverride(name = "date1", column = @Column(name = "BEG_DATE1")),
            @AttributeOverride(name = "date1Yr", column = @Column(name = "BEG_DATE1_YR")),
            @AttributeOverride(name = "date1Qtr", column = @Column(name = "BEG_DATE1_QTR")),
            @AttributeOverride(name = "date1Mon", column = @Column(name = "BEG_DATE1_MON")),
            @AttributeOverride(name = "date1Mon2", column = @Column(name = "BEG_DATE1_MON2")),
            @AttributeOverride(name = "date1Day", column = @Column(name = "BEG_DATE1_DAY"))
    })
    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    private HasDate beg;

    @Column(name = "RATE", precision = 19, scale = 9)
    private BigDecimal rate;

    @Column(name = "RATE_INV", precision = 19, scale = 9)
    private BigDecimal rateInv;

    @JoinColumn(name = "FIN_CURCY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finCurcy1_Id;

    @Column(name = "FIN_CURCY1__ID2")
    private String finCurcy1_Id2;

    @JoinColumn(name = "FIN_CURCY2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finCurcy2_Id;

    @Column(name = "FIN_CURCY2__ID2")
    private String finCurcy2_Id2;

    public HasDate getBeg() {
        return beg;
    }

    public void setBeg(HasDate beg) {
        this.beg = beg;
    }

    public BigDecimal getRateInv() {
        return rateInv;
    }

    public void setRateInv(BigDecimal rateInv) {
        this.rateInv = rateInv;
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

    public GenNode getFinCurcy2_Id() {
        return finCurcy2_Id;
    }

    public void setFinCurcy2_Id(GenNode finCurcy2_Id) {
        this.finCurcy2_Id = finCurcy2_Id;
    }

    public GenNode getFinCurcy1_Id() {
        return finCurcy1_Id;
    }

    public void setFinCurcy1_Id(GenNode finCurcy1_Id) {
        this.finCurcy1_Id = finCurcy1_Id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

}