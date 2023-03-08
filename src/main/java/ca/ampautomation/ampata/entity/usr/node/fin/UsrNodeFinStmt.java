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
        isChanged = this.updateInst1(dataManager) || isChanged;
        isChanged = this.updateName1(dataManager) || isChanged;

        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>inst1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>finAcct1_Id.id2</i> required</li>
     *      <li><i>ts2.elTs</i> (tsEnd) required</li>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *     <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *     <li><i>ts2.elTs</i> (tsEnd) = "2018-12-31"</li>
     *     <li><i>desc1</i> = "A/CurY/RBC/Chk/D2018-10-31"</li>
     * </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateInst1(DataManager dataManager){
        String logPrfx = "updateInst1";
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

        String l_inst1_ = this.inst1;
        String l_inst1 = sb.toString();
        logger.debug(logPrfx + " --- l_inst1: " + l_inst1);

        if (Objects.equals(l_inst1_, l_inst1)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setInst1(l_inst1);
            logger.debug(logPrfx + " --- called setInst1(l_inst1)");
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
        String l_desc1 = sb.toString().strip();
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


    /**
     * <h1>Update the <i>ts2</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>inst1</i></li>
     *          <li><i>name1</i></li>
     *          <li><i>desc1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>     *      </ul>
     * @return Boolean true if any field was changed, otherwise false
     */
    @Override
    public Boolean updateTs2Deps(DataManager dataManager) {
        String logPrfx = "updateTs2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateInst1(dataManager) || isChanged;
        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateDesc1(dataManager) || isChanged;

        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


}