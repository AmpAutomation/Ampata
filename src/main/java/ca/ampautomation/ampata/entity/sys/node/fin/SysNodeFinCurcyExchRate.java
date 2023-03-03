package ca.ampautomation.ampata.entity.sys.node.fin;

import ca.ampautomation.ampata.entity.sys.base.SysNodeBase;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JmixEntity
@Entity(name = "enty_SysFinCurcyExchRate")
public class SysNodeFinCurcyExchRate extends SysNodeBase {

    @Override
    public Boolean updateInst1(DataManager dataManager){
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        final String SEP = "/";
        StringBuilder sb = new StringBuilder();

        final DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();
        final DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        final DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm")
                .toFormatter();
        final DecimalFormat frmtDec = new DecimalFormat("+0.00;-0.00");

        String l_inst1_ = this.inst1;
        String l_inst1 = "";

        //require ts1.elTs
        if (this.ts1 == null) {
            logger.debug(logPrfx + " --- ts1: null");
            logger.trace(logPrfx + " <--- ");
            return isChanged;
        }
        if (this.ts1.getElTs() == null) {
            logger.debug(logPrfx + " --- ts1: null");
            logger.trace(logPrfx + " <--- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- ts1: " + this.ts1.getElTs().format(frmtTs));
        }

        //require finCurcy1_Id
        if (this.finCurcy1_Id == null) {
            logger.debug(logPrfx + " --- finCurcy1_Id: null");
            logger.trace(logPrfx + " <--- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- finCurcy1_Id: " + finCurcy1_Id.getId());
        }

        //require finCurcy2_Id
        if (this.finCurcy2_Id == null) {
            logger.debug(logPrfx + " --- finCurcy2_Id: null");
            logger.trace(logPrfx + " <--- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- finCurcy2_Id: " + finCurcy2_Id.getId());
        }

        //update type1_Id2
        if (this.type1_Id != null){
            this.type1_Id2 = type1_Id.getId2();
        }

        //create id2
        //finCurcy1_Id
        sb.append(finCurcy1_Id.getId2());
        //finCurcy2_Id
        sb.append( "->").append(finCurcy2_Id.getId2());
        //ts1.elTs
        sb.append(SEP + "D").append(ts1.getElTs().format(frmtDt)
        );

        l_inst1 = sb.toString();
        logger.debug(logPrfx + " --- l_inst1: " + l_inst1);

        if (!Objects.equals(l_inst1_, l_inst1)){
            this.setInst1(l_inst1);
            logger.debug(logPrfx + " --- inst1: " + this.inst1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        final DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        boolean isChanged = false;

        String l_desc1_ = this.getDesc1();
        String l_curcyPair = "";
        if (this.getFinCurcy1_Id() != null) {
            l_curcyPair = l_curcyPair + this.getFinCurcy1_Id().getId2();
        }else{
            l_curcyPair = l_curcyPair + "???";
        }

        if (!l_curcyPair.equals("")) {
            l_curcyPair = l_curcyPair + "->";
        }

        if (this.getFinCurcy2_Id() != null) {
            l_curcyPair = l_curcyPair + this.getFinCurcy2_Id().getId2();
        }else{
            l_curcyPair = l_curcyPair + "???";
        }

        if (!l_curcyPair.equals("")) {
            l_curcyPair = "Exchange " + l_curcyPair + "";
        }
        logger.debug(logPrfx + " --- l_curcyPair: " + l_curcyPair);


        String l_date = "";
        if (this.getTs1() != null && this.getTs1().getElDt() != null) {
            l_date = this.getTs1().getElDt().format(frmtDt);
        }else {
            l_date = "????-??-??";
        }
        if (!l_date.equals("")) {
            l_date = "on date " + l_date + "";
        }
        logger.debug(logPrfx + " --- l_date: " + l_date);


        String l_rate = "";
        if (getAmt1() != null) {
            l_rate = Objects.toString(this.getAmt1().setScale(9, RoundingMode.HALF_UP), "");
        }
        if (!l_rate.equals("")) {
            l_rate = "at rate " + l_rate + "";
        }
        logger.debug(logPrfx + " --- l_rate: " + l_rate);


        List<String> list = Arrays.asList(
                l_curcyPair
                , l_date
                , l_rate
        );

        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(l_desc1_, desc1)){
            this.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmt2(DataManager dataManager) {
        String logPrfx = "updateAmt2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal l_amt1 = this.amt1;
        BigDecimal l_amt2_ = this.amt2;
        BigDecimal l_amt2 = null;
        if (!l_amt1.equals(BigDecimal.ZERO)){
            l_amt2 = new BigDecimal(1 / l_amt1.doubleValue()).setScale(9, RoundingMode.HALF_UP);;
        }

        if (!Objects.equals(l_amt2_, l_amt2)){
            this.amt2 = l_amt2;
            logger.debug(logPrfx + " --- amt2: " + l_amt2.setScale(9, RoundingMode.HALF_UP));
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


}