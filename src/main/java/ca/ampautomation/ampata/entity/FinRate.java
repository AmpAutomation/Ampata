package ca.ampautomation.ampata.entity;

import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@JmixEntity
@Entity(name = "ampata_FinRate")
public class FinRate extends GenEntity {

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

    public HasDate getBeg1() {
        return beg1;
    }

    public void setBeg1(HasDate beg1) {
        this.beg1 = beg1;
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

    @Override
    public String getId2CalcFrFields(){
        String logPrfx = "getId2CalcFrFields";
        logger.trace(logPrfx + " --> ");
        final String SEP = "/";
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd")
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