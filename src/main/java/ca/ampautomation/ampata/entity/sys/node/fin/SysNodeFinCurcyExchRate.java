package ca.ampautomation.ampata.entity.sys.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@JmixEntity
@Entity(name = "enty_SysNodeFinCurcyExchRate")
public class SysNodeFinCurcyExchRate extends SysNodeBase {

    @Override
    public Boolean updateId2Calc(DataManager dataManager){
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        StringBuilder sb = new StringBuilder();

        //require ts1.elTs
        if (this.ts1 == null) {
            logger.debug(logPrfx + " --- ts1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }
        if (this.ts1.getElTs() == null) {
            logger.debug(logPrfx + " --- ts1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- ts1: " + this.ts1.getElTs().format(frmtTs));
        }

        //require finCurcy1_Id
        if (this.finCurcy1_Id == null) {
            logger.debug(logPrfx + " --- finCurcy1_Id: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- finCurcy1_Id: " + finCurcy1_Id.getId());
        }

        //require finCurcy2_Id
        if (this.finCurcy2_Id == null) {
            logger.debug(logPrfx + " --- finCurcy2_Id: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- finCurcy2_Id: " + finCurcy2_Id.getId());
        }

        //create id2
        //finCurcy1_Id
        sb.append(finCurcy1_Id.getId2());
        //finCurcy2_Id
        sb.append( "->").append(finCurcy2_Id.getId2());
        //ts1.elTs
        sb.append(this.SEP1 + "D").append(ts1.getElTs().format(frmtDt)
        );


        String l_id2Calc_ = this.id2Calc;
        String l_id2Calc = sb.toString();
        logger.debug(logPrfx + " --- l_id2Calc: " + l_id2Calc);

        if (!Objects.equals(l_id2Calc_, l_id2Calc)){
            this.setId2Calc(l_id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + this.id2Calc);
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
        StringBuilder sb = new StringBuilder();

        // finCurcy1
        // "->"
        // finCurcy2
        // ts1ElTs
        // amt1 (rate)
        // genTags1


        sb.append("Exchange ");

        //finCurcy1
        Optional<SysNodeFinCurcy> l_finCurcy1_Id = Optional.ofNullable(this.finCurcy1_Id);
        if(l_finCurcy1_Id.isEmpty()){
            logger.debug(logPrfx + " --- l_finCurcy1_Id: null");
            sb.append("???");
        }else{
            logger.debug(logPrfx + " --- l_finCurcy1_Id.getId2(): " + l_finCurcy1_Id.get().getId2());
            sb.append(l_finCurcy1_Id.map(SysNodeFinCurcy::getId2).get());
        }

        //finCurcy2
        Optional<SysNodeFinCurcy> l_finCurcy2_Id = Optional.ofNullable(this.finCurcy2_Id);
        if(l_finCurcy2_Id.isEmpty()){
            sb.append(" -> ");
            sb.append("???");
            logger.debug(logPrfx + " --- l_finCurcy2_Id: null");
        }else{
            sb.append(" -> ");
            logger.debug(logPrfx + " --- l_finCurcy2_Id.getId2(): " + l_finCurcy2_Id.get().getId2());
            sb.append(l_finCurcy2_Id.map(SysNodeFinCurcy::getId2).get());
        }

        //ts1.elTs
        Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(this.ts1).map(HasTmst::getElTs);
        if (l_ts1ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- ts1.elTs: null");
            sb.append(SEP0);
            sb.append("????-??-??");
        }else{
            logger.debug(logPrfx + " --- ts1.elTs: " + l_ts1ElTs.get().format(frmtDt));
            sb.append(SEP0);
            sb.append("on date ").append(l_ts1ElTs.get().format(frmtDt));
        }

        //amt1
        Optional<BigDecimal> l_amt1 = Optional.ofNullable(this.amt1);
        if (l_amt1.isEmpty()) {
            logger.debug(logPrfx + " --- amt1: null");
        }else{
            logger.debug(logPrfx + " --- amt1: " + l_amt1.get().setScale(9, RoundingMode.HALF_UP));
            sb.append(SEP0);
            sb.append("at rate ");
            sb.append(l_amt1.get().setScale(9, RoundingMode.HALF_UP));
        }

/*
        String l_desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
*/

        String l_desc1_ = this.desc1;
        String l_desc1 = sb.toString();
        logger.debug(logPrfx + " --- l_desc1: " + l_desc1);

        if (!Objects.equals(l_desc1_, l_desc1)){
            this.setDesc1(l_desc1);
            logger.debug(logPrfx + " --- called setDesc1(l_desc1)");
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

        //require amt1
        Optional<BigDecimal> l_amt1 = Optional.ofNullable(this.amt1);
        if (l_amt1.isEmpty()) {
            logger.debug(logPrfx + " --- amt1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- amt1: " + l_amt1.get().setScale(9, RoundingMode.HALF_UP));
        }

        BigDecimal l_amt2_ = this.amt2;
        BigDecimal l_amt2 = null;
        if (l_amt1.get().equals(BigDecimal.ZERO)){
            l_amt2 = BigDecimal.ZERO;
        } else{
            l_amt2 = new BigDecimal(1 / l_amt1.get().doubleValue()).setScale(9, RoundingMode.HALF_UP);;

        }
        logger.debug(logPrfx + " --- amt2: " + l_amt2.setScale(9, RoundingMode.HALF_UP));

        if (!Objects.equals(l_amt2_, l_amt2)){
            this.setAmt2(l_amt2);
            logger.debug(logPrfx + " --- called setId2Calc(l_id2Calc)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}