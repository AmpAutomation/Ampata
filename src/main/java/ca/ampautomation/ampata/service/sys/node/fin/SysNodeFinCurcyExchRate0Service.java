package ca.ampautomation.ampata.service.sys.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyExchRate;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.service.sys.node.base.SysNodeBase0Service;
import io.jmix.ui.screen.Screen;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component("bean_SysNodeFinCurcyExchRate.Service")
public class SysNodeFinCurcyExchRate0Service extends SysNodeBase0Service {

    public SysNodeFinCurcyExchRate0Service() {
        this.typeOfNode = SysNodeFinCurcyExchRate.class;
    }


    /**
     * <h1>Update the <i>id2Calc</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "Gen"</li>
     *          <li><i>id2Calc</i> = "Gen"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "Biz"</li>
     *          <li><i>id2Calc</i> = "Biz"</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateId2Calc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");
        boolean isChanged = false;

        StringBuilder sb = new StringBuilder();

        //require ts1.elTs
        if (thisNode.getTs1() == null) {
            logger.debug(logPrfx + " --- ts1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }
        if (thisNode.getTs1().getElTs() == null) {
            logger.debug(logPrfx + " --- ts1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- ts1: " + thisNode.getTs1().getElTs().format(FRMT_TS));
        }

        //require finCurcy1_Id
        if (thisNode.getFinCurcy1_Id() == null) {
            logger.debug(logPrfx + " --- finCurcy1_Id: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- finCurcy1_Id: " + thisNode.getFinCurcy1_Id().getId());
        }

        //require finCurcy2_Id
        if (thisNode.getFinCurcy2_Id() == null) {
            logger.debug(logPrfx + " --- finCurcy2_Id: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- finCurcy2_Id: " + thisNode.getFinCurcy2_Id().getId());
        }

        //create id2
        //finCurcy1_Id
        sb.append(thisNode.getFinCurcy1_Id().getId2());
        //finCurcy2_Id
        sb.append( "->").append(thisNode.getFinCurcy2_Id().getId2());
        //ts1.elTs
        sb.append(SEP1 + "D").append(thisNode.getTs1().getElTs().format(FRMT_DT)
        );


        String l_id2Calc_ = thisNode.getId2Calc();
        String l_id2Calc = sb.toString();
        logger.debug(logPrfx + " --- l_id2Calc: " + l_id2Calc);

        if (!Objects.equals(l_id2Calc_, l_id2Calc)){
            thisNode.setId2Calc(l_id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + thisNode.getId2Calc());
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>desc1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>status</i> optional</li>
     *      <li><i>finAcct1_Id.id2</i> optional</li>
     *      <li><i>finDept1_Id.id2</i> optional</li>
     *      <li><i>ts2.elTs</i> (tsEnd) optional</li>
     *      <li><i>amtEndBalCalc</i> optional</li>
     *      <li><i>genTags1_Id.map.id2</i> optional</li>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *     <li><i>status()</i> = "Reconciled"</li>
     *     <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *     <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *     <li><i>ts1.elTs</i> (tsBeg) = "2018-01-01"</li>
     *     <li><i>ts2.elTs</i> (tsEnd) = "2018-12-31"</li>
     *     <li><i>amtEndBalCalc</i> = 125.00</li>
     *     <li><i>genTags1_Id.map.id2</i> = ()</li>
     *     <li><i>desc1</i> = "Reconciled acct [A/CurY/Cash_Eq/RBC/Chk] begDate 2018-01-01 endDate 2018-12-31 amtEndBalCalc 125.00"</li>
     * </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateDesc1(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        // finCurcy1
        // "->"
        // finCurcy2
        // ts1_ElTs
        // amt1 (rate)
        // genTags1


        sb.append("Exchange ");

        //finCurcy1
        Optional<SysNodeFinCurcy> o_finCurcy1_Id = Optional.ofNullable(thisNode.getFinCurcy1_Id());
        if(o_finCurcy1_Id.isEmpty()){
            logger.debug(logPrfx + " --- o_finCurcy1_Id: empty");
            sb.append("???");
        }else{
            logger.debug(logPrfx + " --- o_finCurcy1_Id.getId2(): " + o_finCurcy1_Id.get().getId2());
            sb.append(o_finCurcy1_Id.map(SysNodeFinCurcy::getId2).get());
        }

        //finCurcy2
        Optional<SysNodeFinCurcy> o_finCurcy2_Id = Optional.ofNullable(thisNode.getFinCurcy2_Id());
        if(o_finCurcy2_Id.isEmpty()){
            sb.append(" -> ");
            sb.append("???");
            logger.debug(logPrfx + " --- o_finCurcy2_Id: empty");
        }else{
            sb.append(" -> ");
            logger.debug(logPrfx + " --- o_finCurcy2_Id.getId2(): " + o_finCurcy2_Id.get().getId2());
            sb.append(o_finCurcy2_Id.map(SysNodeFinCurcy::getId2).get());
        }

        //ts1.elTs
        Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
        if (o_ts1_ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- o_ts1_ElTs: empty");
            sb.append(SEP0);
            sb.append("????-??-??");
        }else{
            logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
            sb.append(SEP0);
            sb.append("on date ").append(o_ts1_ElTs.get().format(FRMT_DT));
        }

        //amt1
        Optional<BigDecimal> o_amt1 = Optional.ofNullable(thisNode.getAmt1());
        if (o_amt1.isEmpty()) {
            logger.debug(logPrfx + " --- o_amt1: empty");
        }else{
            logger.debug(logPrfx + " --- o_amt1: " + o_amt1.get().setScale(9, RoundingMode.HALF_UP));
            sb.append(SEP0);
            sb.append("at rate ");
            sb.append(o_amt1.get().setScale(9, RoundingMode.HALF_UP));
        }

/*
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
*/

        String desc1_ = thisNode.getDesc1();
        String desc1 = sb.toString().strip();
        logger.debug(logPrfx + " --- desc1: " + desc1);

        if (!Objects.equals(desc1_, desc1)){
            thisNode.setDesc1(desc1);
            logger.debug(logPrfx + " --- called setDesc1(desc1)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>amt2</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>status</i> optional</li>
     *      <li><i>finAcct1_Id.id2</i> optional</li>
     *      <li><i>finDept1_Id.id2</i> optional</li>
     *      <li><i>ts2.elTs</i> (tsEnd) optional</li>
     *      <li><i>amtEndBalCalc</i> optional</li>
     *      <li><i>genTags1_Id.map.id2</i> optional</li>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *     <li><i>status()</i> = "Reconciled"</li>
     *     <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *     <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *     <li><i>ts1.elTs</i> (tsBeg) = "2018-01-01"</li>
     *     <li><i>ts2.elTs</i> (tsEnd) = "2018-12-31"</li>
     *     <li><i>amtEndBalCalc</i> = 125.00</li>
     *     <li><i>genTags1_Id.map.id2</i> = ()</li>
     *     <li><i>desc1</i> = "Reconciled acct [A/CurY/Cash_Eq/RBC/Chk] begDate 2018-01-01 endDate 2018-12-31 amtEndBalCalc 125.00"</li>
     * </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateAmt2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmt2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //require amt1
        Optional<BigDecimal> o_amt1 = Optional.ofNullable(thisNode.getAmt1());
        if (o_amt1.isEmpty()) {
            logger.debug(logPrfx + " --- o_amt1: empty");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- o_amt1: " + o_amt1.get().setScale(9, RoundingMode.HALF_UP));
        }

        BigDecimal amt2_ = thisNode.getAmt2();
        BigDecimal amt2 = null;
        if (o_amt1.get().equals(BigDecimal.ZERO)){
            amt2 = BigDecimal.ZERO;
        } else{
            amt2 = new BigDecimal(1 / o_amt1.get().doubleValue()).setScale(9, RoundingMode.HALF_UP);;

        }
        logger.debug(logPrfx + " --- amt2: " + amt2.setScale(9, RoundingMode.HALF_UP));

        if (!Objects.equals(amt2_, amt2)){
            thisNode.setAmt2(amt2);
            logger.debug(logPrfx + " --- called setAmt2(amt2)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



}