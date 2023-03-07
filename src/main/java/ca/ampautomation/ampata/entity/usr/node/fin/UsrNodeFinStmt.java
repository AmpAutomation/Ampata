package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@JmixEntity
@Entity(name = "enty_UsrNodeFinStmt")
public class UsrNodeFinStmt extends UsrNodeBase {


    /**
     * <h1>Update all calculated fields</h1>
     * <p></p>
     * <h2>Updated Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>desc1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateCalcVals(DataManager dataManager) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateDesc1(dataManager) || isChanged;

        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * Updates the field id2Calc
     * @return Boolean if the field was changed
     *
     * Dependent Fields
     *      finAcct1_Id.id2 required
     *      ts2.elTs (tsEnd) required
     *
     * Examples
     *      finAcct1_Id.id2 = "A/CurY/RBC/Chk"
     *      ts2.elTs = "2018-10-31"
     *      id2="A/CurY/RBC/Chk/D2018-10-31"
     *
     */
    @Override
    public Boolean updateId2Calc(DataManager dataManager){
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //finAcct1_Id.id2 required
        Optional<String> l_finAcct1_Id2 = Optional.ofNullable(this.finAcct1_Id).map(UsrNodeFinAcct::getId2);
        if (l_finAcct1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_finAcct1_Id2: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- l_finAcct1_Id2: " + l_finAcct1_Id2.get());
            sb.append(l_finAcct1_Id2.get());
        }

        //ts2.elTs (tsEnd) required
        Optional<LocalDateTime> l_ts2ElTs = Optional.ofNullable(this.ts2).map(HasTmst::getElTs);
        if (l_ts2ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- l_ts2ElTs: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- l_ts2ElTs: " + l_ts2ElTs.get().format(frmtDt));
            sb.append(SEP1);
            sb.append("D");
            sb.append(l_ts2ElTs.get().format(frmtDt));
        }

        String l_id2Calc_ = this.id2Calc;
        String l_id2Calc = sb.toString();
        logger.debug(logPrfx + " --- l_id2Calc: " + l_id2Calc);

        if (Objects.equals(l_id2Calc_, l_id2Calc)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setId2Calc(l_id2Calc);
            logger.debug(logPrfx + " --- called setId2Calc(l_id2Calc)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * Updates the field desc1
     * @return Boolean if the field was changed
     *
     * Dependent Fields
     *      status optional
     *      finAcct1_Id.id2 optional
     *      finDept1_Id.id2 optional
     *      ts2.elTs (End) optional
     *      amtEndBalCalc optional
     *      genTags1_Id.map.id2 optional
     *
     * Examples
     *      status = "Reconciled"
     *      finAcct1_Id.id2 = "A/CurY/Cash_Eq/RBC/Chk"
     *      ts2.elTs (End) = "2018-12-31"
     *      amtEndBalCalc = 125.00
     *      genTags1_Id.map.id2 = ""
     *      desc1 = "reconciled acct [A/CurY/Cash_Eq/RBC/Chk] endDate 2018-12-31 amtEndBalCalc 125.00"
     */
    @Override
    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //finAcct1_Id.id2 optional
        Optional<String> l_finAcct1_Id2 = Optional.ofNullable(this.finAcct1_Id).map(UsrNodeFinAcct::getId2);
        if (l_finAcct1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_finAcct1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_finAcct1_Id2: " + l_finAcct1_Id2.get());
            sb.append("acct [");
            sb.append(l_finAcct1_Id2.get());
            sb.append("]");
        }

        //finDept1.id2 optional
        Optional<String> l_finDept1_Id2 = Optional.ofNullable(this.finDept1_Id).map(UsrNodeFinDept::getId2);
        if(l_finDept1_Id2.isEmpty()){
            logger.debug(logPrfx + " --- l_finDept1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_finDept1_Id2: " + l_finDept1_Id2.get());
            sb.append(SEP0);
            sb.append("dept [");
            sb.append(l_finDept1_Id2.get());
            sb.append("]");
        }

        //ts1.elTs (tsBeg) optional
        Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(this.ts1).map(HasTmst::getElTs);
        if (l_ts1ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- l_ts1ElTs: null");
        }else{
            logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
            sb.append(SEP0);
            sb.append("begDate ").append(l_ts1ElTs.get().format(frmtDt));
        }

        //ts2.elTs (tsEnd)
        Optional<LocalDateTime> l_ts2ElTs = Optional.ofNullable(this.ts2).map(HasTmst::getElTs);
        if (l_ts2ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- l_ts2ElTs: null");
        }else{
            logger.debug(logPrfx + " --- l_ts2ElTs: " + l_ts2ElTs.get().format(frmtDt));
            sb.append(SEP0);
            sb.append("endDate ");
            sb.append(l_ts2ElTs.get().format(frmtDt));
        }

        //amtEndBalCalc optional
        Optional<BigDecimal> l_amtEndBalCalc = Optional.ofNullable(this.amtEndBalCalc);
        if (l_amtEndBalCalc.isEmpty()) {
            logger.debug(logPrfx + " --- amtEndBalCalc: null");
        }else{
            logger.debug(logPrfx + " --- amtEndBalCalc: " + l_amtEndBalCalc.get().setScale(2, RoundingMode.HALF_UP));
            sb.append(SEP0);
            sb.append("endBalCalc ");
            sb.append(l_amtEndBalCalc.get().setScale(2, RoundingMode.HALF_UP));
        }

        //genTags1_Id.map.id2 optional
        Optional<List<UsrItemGenTag>> l_genTags1_Id = Optional.ofNullable(this.getGenTags1_Id());

        if (l_genTags1_Id.isEmpty()) {
            logger.debug(logPrfx + " --- l_genTags1_Id: null");
        }else{

            List<UsrItemGenTag> l_genTags1_Id_AsList =  l_genTags1_Id.orElse(new ArrayList<UsrItemGenTag>(null));

            String l_genTags1_Id_AsStr = Optional.of(
                    l_genTags1_Id_AsList
                            .stream()
                            .map(UsrItemGenTag::getId2)
                            .collect(Collectors.joining(","))
            ).orElse("");
            if (l_genTags1_Id_AsStr.equals("")) {
                logger.debug(logPrfx + " --- l_genTags1_Id_AsStr: is blank");
            }else{
                logger.debug(logPrfx + " --- l_genTags1_Id_AsStr: " + l_genTags1_Id_AsStr);
                sb.append(SEP0);
                sb.append("tag [");
                sb.append(l_genTags1_Id_AsStr);
                sb.append("]");
            }
        }

        String l_desc1_ = this.desc1;
        String l_desc1 = sb.toString().trim();
        logger.debug(logPrfx + " --- l_desc1: " + l_desc1);

        if (Objects.equals(l_desc1_, l_desc1)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setDesc1(l_desc1);
            logger.debug(logPrfx + " --- called setDesc1(l_desc1)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}