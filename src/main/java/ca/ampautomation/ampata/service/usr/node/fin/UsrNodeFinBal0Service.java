package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseComn;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.UiControllerUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Component("bean_UsrNodeFinBal.Service")
public class UsrNodeFinBal0Service extends UsrNodeBase0Service {

    public UsrNodeFinBal0Service() {
        this.typeOfNode = UsrNodeFinBal.class;
    }

    @Autowired
    protected UsrNodeFinBalSet0Service serviceParent1;

    @Autowired
    protected UsrNodeFinBal0Service serviceFinBal1;

    /**
     * <h1>Update all calculated fields</h1>
     * <p></p>
     * <h2>Updated Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>desc1</i></li>
     *          <li><i>inst1</i></li>
     *          <li><i>name1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateCalcVals(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinBal1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateAmtNet(screen, thisNode, updOption) || isChanged;
        isChanged = updateAmtBegBalCalc(screen, thisNode, updOption) || isChanged;
        isChanged = updateFinTxactItms1_FieldSet(screen, thisNode, updOption) || isChanged;
        isChanged = updateAmtEndBalCalc(screen , thisNode, updOption) || isChanged;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;
        isChanged = updateSortKey(screen, thisNode, updOption) || isChanged;

        isChanged = updateFinTxactItms1_FieldSet(screen, thisNode, updOption)  || isChanged;

        isChanged = updateFinTxactItms1_IdCntCalc(screen, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtEqCalc(screen, thisNode, updOption)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>id2Calc</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <p>If <i>parent1_Id.isEmpty()</i> then</p>
     *              <ul style="margin-left: 36px;">
     *                  <li><i>ts1.elTs</i> (tsBeg) required</li>
     *                  <li><i>ts2.elTs</i> (tsEnd) required</li>
     *                  <li><i>finDept1_Id.id2</i> optional</li>
     *              </ul>
     *          <p><i>else</p>
     *              <ul style="margin-left: 36px;">
     *                  <li><i>parent1_Id.id2</i> required</li>
     *              </ul>
     *          <li><i>finAcct1_Id.id2</i> required</li>
     *     </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>parent1_Id.isEmpty()</i> = true</li>
     *          <li><i>ts1.elTs</i> = "2018-01-01"</li>
     *          <li><i>ts2.elTs</i> = "2018-12-31"</li>
     *          <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *          <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *          <li><i>id2Calc</i> = "B=2018-01-01;E=2018-12-31;D=Gen;A=A/CurY/RBC/Chk"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>parent1_Id.isEmpty()</i> = false</li>
     *          <li><i>finBalSet_Id.id2</i> = "R=Y2016/_;S=All;D="</li>
     *          <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *          <li><i>id2Calc</i> = "R=Y2016/_;S=All;D=;A=A/CurY/RBC/Chk"</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateId2Calc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {
                StringBuilder sb = new StringBuilder();

                //finAcct1_Id.id2 required
                Optional<String> o_finAcct1_Id2 = Optional.ofNullable(thisNode.getFinAcct1_Id()).map(UsrNodeFinAcct::getId2);
                if (o_finAcct1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finAcct1_Id2: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                }

                Optional<String> o_finBalSet1_Id2 = Optional.ofNullable(thisNode.getFinBalSet1_Id()).map(UsrNodeFinBalSet::getId2);
                if (o_finBalSet1_Id2.isEmpty()) {

                    //ts1.elTs (tsBeg) required
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                        sb.append("B=").append(o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    //ts2.elTs (tsEnd) required
                    Optional<LocalDateTime> o_ts2_ElTs = Optional.ofNullable(thisNode.getTs2()).map(HasTmst::getElTs);
                    if (o_ts2_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts2_ElTs: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_ts2_ElTs: " + o_ts2_ElTs.get().format(FRMT_DT));
                        sb.append(SEP2);
                        sb.append("E=").append(o_ts2_ElTs.get().format(FRMT_DT));
                    }

                    //finDept1_Id.id2 optional
                    Optional<String> o_finDept1_Id2 = Optional.ofNullable(thisNode.getFinDept1_Id()).map(UsrNodeFinDept::getId2);
                    if(o_finDept1_Id2.isEmpty()){
                        logger.debug(logPrfx + " --- o_finDept1_Id2: is empty");
                        sb.append(SEP2);
                        sb.append("D=");
                    }else{
                        logger.debug(logPrfx + " --- o_finDept1_Id2: " + o_finDept1_Id2.get());
                        sb.append(SEP2);
                        sb.append("D=").append(o_finDept1_Id2.get());
                    }

                    //finAcct1_Id.id2
                    sb.append(SEP2);
                    sb.append("A=").append(o_finAcct1_Id2.get());

                } else {
                    //finBalSet1_Id.id2
                    sb.append(o_finBalSet1_Id2.get());
                    //finAcct1
                    sb.append(SEP2);
                    sb.append("A=").append(o_finAcct1_Id2.get());
                }

                String id2Calc_ = thisNode.getId2Calc();
                String id2Calc = sb.toString();
                logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

                if (Objects.equals(id2Calc_, id2Calc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setId2Calc(id2Calc);
                    logger.debug(logPrfx + " --- called thisNode.setId2Calc(id2Calc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>name1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>inst1</i> required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>inst1</i> = "Gen"</li>
     *          <li><i>name1</i> = "Gen"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>inst1</i> = "Biz"</li>
     *          <li><i>name1</i> = "/::Biz"</li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateName1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateName1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {

                StringBuilder sb = new StringBuilder();

                //inst1 required
                Optional<String> o_inst1 = Optional.ofNullable(thisNode.getInst1());
                if (o_inst1.isEmpty()) {
                    logger.debug(logPrfx + " --- o_inst1: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_inst1: " + o_inst1.get());
                    sb.append(o_inst1.get());
                }

                String name1_ = thisNode.getName1();
                String name1 = o_inst1.get();
                logger.debug(logPrfx + " --- name1:" + name1);

                if (Objects.equals(name1_, name1)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setName1(name1);
                    logger.debug(logPrfx + " --- called thisNode.setName1(name1)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>name1</i> field dependent fields</h1>
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
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateName1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateName1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>inst1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *     <p>If <i>parent1_Id.isEmpty()</i> then</p>
     *     <ul style="margin-left: 24px;">
     *         <li><i>type1_Id.id2</i> required</li>
     *         <p>switch <i>type1_Id.id2</i></p>
     *         <p>case "Rng"</p>
     *         <ul style="margin-left: 24px;">
     *             <li><i>ts1.elTs</i> (tsBeg) required</li>
     *             <li><i>ts2.elTs</i> (tsEnd) required</li>
     *             <li><i>txt1</i> (Set) required</li>
     *             <li><i>finDept1_Id.id2</i> optional</li>
     *         </ul>
     *         <p>case "Rng-Given-Year"
     *         <br>case "Rng-Given-Month"
     *         <br>case "Rng-Given-Week"
     *         <br>case "Rng-Given-Day"</p>
     *         <ul style="margin-left: 24px;">
     *             <li><i>ts1.elTs</i> (tsInRng) required</li>
     *             <li><i>txt1</i> (txtSet) required</li>
     *             <li><i>finDept1_Id.id2</i> optional</li>
     *         </ul>
     *     </ul>
     *     <p><i>else</p>
     *     <ul style="margin-left: 36px;">
     *         <li><i>parent1_Id.type1_Id.id2</i> required</li>
     *         <p>switch <i>parent1_Id.type1_Id.id2</i></p>
     *         <p> case "Rng"</p>
     *         <ul style="margin-left: 24px;">
     *             <li><i>parent1_Id.ts1.elTs</i> (tsBeg) required</li>
     *             <li><i>parent1_Id.ts2.elTs</i> (tsEnd) required</li>
     *             <li><i>parent1_Id.txt1</i> (Set) required</li>
     *             <li><i>parent1_Id.finDept1_Id.id2</i> optional</li>
     *         </ul>
     *         <p>case "Rng-Given-Year"
     *         <br>case "Rng-Given-Month"
     *         <br>case "Rng-Given-Week"
     *         <br>case "Rng-Given-Day"</p>
     *         <ul style="margin-left: 24px;">
     *             <li><i>parent1_Id.type1_Id.id2</i> required</li>
     *             <li><i>parent1_Id.ts1.elTs</i> (tsInRng) required</li>
     *             <li><i>parent1_Id.txt1</i> (txtSet) required</li>
     *             <li><i>parent1_Id.finDept1_Id.id2</i> optional</li>
     *         </ul>
     *     </ul>
     *     <li><i>finAcct1_Id.id2</i> required</li>
     * /ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = true</li>
     *      <li><i>type1_Id.id2</i> = "Rng"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>ts2.elTs</i> = "2023-02-28"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>inst1</i> = "B=2023-01-01;E=2023-02-28;S=All;D=Gen;A=A/CurY/RBC/Chk"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = true</li>
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Year"</li>
     *      <li><i>ts1.elTs</i> = "2018-01-01"</li>
     *      <li><i>ts2.elTs</i> = "2018-12-31"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt2</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>id2Calc</i> = "B=2018-01-01;E=2018-12-31;D=Gen;A=A/CurY/RBC/Chk"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = true</li>
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Year"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>inst1</i> = "R=Y2023/_;S=All;D=Gen;A=A/CurY/RBC/Chk"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = true</li>
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Week"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>inst1</i> = "R=Y2023/W01/_;S=All;D=Gen;A=A/CurY/RBC/Chk"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = true</li>
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Month"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>inst1</i> = "R=Y2023/M01/_;S=All;D=Gen;A=A/CurY/RBC/Chk"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = true</li>
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Day"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>inst1</i> = "R=Y2023/M01/D01;S=All;D=Gen;A=A/CurY/RBC/Chk"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = false</li>
     *      <li><i>parent1_Id.type1_Id.id2</i> = "Rng"</li>
     *      <li><i>parent1_Id.ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>parent1_Id.ts2.elTs</i> = "2023-02-28"</li>
     *      <li><i>parent1_Id.txt1</i> = "All"</li>
     *      <li><i>parent1_Id.finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>inst1</i> = "B=2023-01-01;E=2023-02-28;S=All;D=Gen;A=A/CurY/RBC/Chk"</li>
     * </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateInst1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {
                StringBuilder sb = new StringBuilder();

                //parent1_Id.id2 optional
                Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if(o_parent1_Id.isEmpty()) {
                    logger.trace(logPrfx + " --- o_parent1_Id: is empty");
                }else{
                    logger.trace(logPrfx + " --- o_parent1_Id: " + o_parent1_Id.get().getId2());
                }

                if(o_parent1_Id.isEmpty()){

                    //type1_Id.id2 required
                    Optional<String> o_type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(UsrNodeBaseType::getId2);
                    if(o_type1_Id2.isEmpty()) {
                        logger.trace(logPrfx + " --- o_type1_Id2: emtpy");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.trace(logPrfx + " --- o_type1_Id2: " + o_type1_Id2.get());
                    }

                    //ts1.elTs required
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    //txt1 required
                    Optional<String> o_txt1 = Optional.ofNullable(thisNode.getTxt1());
                    if (o_txt1.isEmpty()) {
                        logger.debug(logPrfx + " --- o_txt1: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_txt1: " + o_txt1.get());
                    }

                    switch (o_type1_Id2.get()){
                        case "Rng-Given-Year"->{
                            //ex. "R=Y2023/_;S=All;D=Gen"
                            //Range
                            sb.append("R=");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP2);
                            sb.append("S=");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Week"->{
                            //ex. "R=Y2023/W01/_;S=All;D=Gen"
                            //Range
                            sb.append("R=");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            WeekFields weekFields = WeekFields.of(Locale.getDefault());
                            int weekNum = o_ts1_ElTs.get().get(weekFields.weekOfWeekBasedYear());
                            sb.append("W").append(String.format("%2d", weekNum));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP2);
                            sb.append("S=");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Month"->{
                            //ex. "R=Y2023/M01/_;S=All;D=Gen"
                            //Range
                            sb.append("R=");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            sb.append("M").append(String.format("%2d", o_ts1_ElTs.get().getMonthValue()));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP2);
                            sb.append("S=");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Day"->{
                            //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                            //Range
                            sb.append("R=");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            sb.append("M").append(String.format("%2d", o_ts1_ElTs.get().getMonthValue()));
                            sb.append(SEP1);
                            //if necessary add up to 2 leading zeros if
                            sb.append("D").append(String.format("%2d", o_ts1_ElTs.get().getDayOfMonth()));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP2);
                            sb.append("S=");
                            sb.append(o_txt1.get());

                        }
                        default->{
                            logger.trace(logPrfx + " --- case o_type1_Id2:" + o_type1_Id2 + " not programmed");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }
                    }

                    //finDept1_Id.id2 optional
                    Optional<String> o_finDept1_Id2 = Optional.ofNullable(thisNode.getFinDept1_Id()).map(UsrNodeBase::getId2);
                    if (o_finDept1_Id2.isEmpty()){
                        logger.trace(logPrfx + " --- o_finDept1_Id2: is null");
                        sb.append(SEP2);
                        sb.append("D=");
                    }else{
                        logger.trace(logPrfx + " --- o_finDept1_Id2: " + o_finDept1_Id2.get());
                        sb.append(SEP2);
                        sb.append("D=");
                        sb.append(o_finDept1_Id2);
                    }

                }else{
                    //o_parent1_Id.isPresent()

                    //type1_Id.id2 required
                    Optional<String> o_type1_Id2 = Optional.ofNullable(o_parent1_Id.get().getType1_Id()).map(UsrNodeBaseType::getId2);
                    if(o_type1_Id2.isEmpty()) {
                        logger.trace(logPrfx + " --- o_type1_Id2: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.trace(logPrfx + " --- o_type1_Id2: " + o_type1_Id2.get());
                    }

                    //ts1.elTs required
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(o_parent1_Id.get().getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    //txt1 required
                    Optional<String> o_txt1 = Optional.ofNullable(o_parent1_Id.get().getTxt1());
                    if (o_txt1.isEmpty()) {
                        logger.debug(logPrfx + " --- o_txt1: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_txt1: " + o_txt1.get());
                    }

                    switch (o_type1_Id2.get()){
                        case "Rng-Given-Year"->{
                            //ex. "R=Y2023/_;S=All;D=Gen"
                            //Range
                            sb.append("R=");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP2);
                            sb.append("S=");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Week"->{
                            //ex. "R=Y2023/W01/_;S=All;D=Gen"
                            //Range
                            sb.append("R=");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            WeekFields weekFields = WeekFields.of(Locale.getDefault());
                            int weekNum = o_ts1_ElTs.get().get(weekFields.weekOfWeekBasedYear());
                            sb.append("W").append(String.format("%2d", weekNum));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP2);
                            sb.append("S=");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Month"->{
                            //ex. "R=Y2023/M01/_;S=All;D=Gen"
                            //Range
                            sb.append("R=");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            sb.append("M").append(String.format("%2d", o_ts1_ElTs.get().getMonthValue()));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP2);
                            sb.append("S=");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Day"->{
                            //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                            //Range
                            sb.append("R=");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            sb.append("M").append(String.format("%2d", o_ts1_ElTs.get().getMonthValue()));
                            sb.append(SEP1);
                            //if necessary add up to 2 leading zeros if
                            sb.append("D").append(String.format("%2d", o_ts1_ElTs.get().getDayOfMonth()));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP2);
                            sb.append("S=");
                            sb.append(o_txt1.get());

                        }
                        default->{
                            logger.trace(logPrfx + " --- case o_type1_Id2: " + o_type1_Id2 + " not programmed");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }
                    }

                    //finDept1_Id.id2 optional
                    Optional<String> o_finDept1_Id2 = Optional.ofNullable(o_parent1_Id.get().getFinDept1_Id()).map(UsrNodeBase::getId2);
                    if (o_finDept1_Id2.isEmpty()){
                        logger.trace(logPrfx + " --- o_finDept1_Id2: is empty");
                        sb.append(SEP2);
                        sb.append("D=");
                    }else{
                        logger.trace(logPrfx + " --- o_finDept1_Id2: " + o_finDept1_Id2.get());
                        sb.append(SEP2);
                        sb.append("D=");
                        sb.append(o_finDept1_Id2);
                    }

                }

                //finAcct1_Id.id2 optional
                Optional<String> o_finAcct1_Id2 = Optional.ofNullable(thisNode.getFinAcct1_Id()).map(UsrNodeBase::getId2);
                if (o_finAcct1_Id2.isEmpty()){
                    logger.trace(logPrfx + " --- o_finAcct1_Id2: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.trace(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                    sb.append(SEP2);
                    sb.append("D=");
                    sb.append(o_finAcct1_Id2);
                }

                String inst1_ = thisNode.getInst1();
                String inst1 = sb.toString();
                logger.debug(logPrfx + " --- inst1: " + inst1);

                if (Objects.equals(inst1_, inst1)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setInst1(inst1);
                    logger.debug(logPrfx + " --- thisNode.setInst1(inst1)");
                }
            }
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
     *      <p>If <i>parent1_Id.isEmpty()</i> then</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.id2</i> required</li>
     *          <p>switch <i>type1_Id.id2</i></p>
     *          <p>case "Rng"</p>
     *          <ul style="margin-left: 24px;">
     *              <li><i>ts1.elTs</i> (tsBeg) optional</li>
     *              <li><i>ts2.elTs</i> (tsEnd) optional</li>
     *              <li><i>finDept1_Id.id2</i> optional</li>
     *          </ul>
     *          <p>case "Rng-Given-Year"
     *          <br>case "Rng-Given-Month"
     *          <br>case "Rng-Given-Week"
     *          <br>case "Rng-Given-Day"</p>
     *          <ul style="margin-left: 24px;">
     *              <li><i>ts1.elTs</i> (tsBeg) optional</li>
     *              <li><i>txt1</i> (Set) optional</li>
     *              <li><i>finDept1_Id.id2</i> optional</li>
     *          </ul>
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>parent1_Id.type1_Id.id2</i> required</li>
     *          <p>switch <i>parent1_Id.type1_Id.id2</i></p>
     *          <p>case "Rng"</p>
     *          <ul style="margin-left: 24px;">
     *              <li><i>parent1_Id.ts1.elTs</i> (tsBeg) optional</li>
     *              <li><i>parent1_Id.ts2.elTs</i> (tsEnd) optional</li>
     *              <li><i>parent1_Id.finDept1_Id.id2</i> optional</li>
     *          </ul>
     *          <p>case "Rng-Given-Year"
     *          <br>case "Rng-Given-Month"
     *          <br>case "Rng-Given-Week"
     *          <br>case "Rng-Given-Day"</p>
     *          <ul style="margin-left: 24px;">
     *              <li><i>parent1_Id.ts1.elTs</i> (tsBeg) optional</li>
     *              <li><i>parent1_Id.txt1</i> (Set) optional</li>
     *              <li><i>parent1_Id.finDept1_Id.id2</i> optional</li>
     *          </ul>
     *      </ul>
     *      <li><i>finAcct1_Id.id2</i> optional</li>
     *      <li><i>amtEndBalCalc</i> optional</li>
     *      <li><i>genTags1_Id.map.id2</i> optional</li>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *     <li><i>status()</i> = ""</li>
     *     <li><i>parent1_Id.isEmpty()</i> = true</li>
     *     <li><i>type1_Id.id2</i> = "Rng"</li>
     *     <li><i>ts1.elTs</i> = "2018-01-01"</li>
     *     <li><i>ts2.elTs</i> = "2018-12-31"</li>
     *     <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *     <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *     <li><i>amtEndBalCalc</i> = 125.00</li>
     *     <li><i>genTags1_Id.map.id2</i> = ()</li>
     *     <li><i>desc1</i> = "Rng begDate 2018-01-01 endDate 2018-12-31 Dept[Gen] Acct[A/CurY/RBC/Chk] amtEndBalCalc=125.00"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *     <li><i>status()</i> = ""</li>
     *     <li><i>parent1_Id.isEmpty()</i> = true</li>
     *     <li><i>type1_Id.id2</i> = "Rng-Given-Year"</li>
     *     <li><i>inst1</i> = "Rng=2018/_"</li>
     *     <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *     <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *     <li><i>amtEndBalCalc</i> = 125.00</li>
     *     <li><i>genTags1_Id.map.id2</i> = ()</li>
     *     <li><i>desc1</i> = "Rng-Given-Year Rng=2018/_ Dept[Gen] Acct[A/CurY/RBC/Chk] amtEndBalCalc=125.00"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *     <li><i>status()</i> = ""</li>
     *     <li><i>parent1_Id.isEmpty()</i> = false</li>
     *     <li><i>parent1_Id.type1_Id.id2</i> = "Rng-Given-Year"</li>
     *     <li><i>parent1_Id.inst1</i> = "R=Y2018_;S=All;D=Gen"</li>
     *     <li><i>parent1_Id.finDept1_Id.id2</i> = "Gen"</li>
     *     <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *     <li><i>amtEndBalCalc</i> = 125.00</li>
     *     <li><i>genTags1_Id.map.id2</i> = ()</li>
     *     <li><i>desc1</i> = "Rng-Given-Year Rng=2018/_ Dept[Gen] Acct[A/CurY/RBC/Chk] amtEndBalCalc=125.00"</li>
     * </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateDesc1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {

                StringBuilder sb = new StringBuilder();

                //parent1_Id.id2 optional
                Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if(o_parent1_Id.isEmpty()) {
                    logger.trace(logPrfx + " --- o_parent1_Id: is empty");
                }else{
                    logger.trace(logPrfx + " --- o_parent1_Id: " + o_parent1_Id.get().getId2());
                }

                if(o_parent1_Id.isEmpty()){

                    //type1_Id.id2 optional
                    Optional<String> o_type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(UsrNodeBaseType::getId2);
                    if(o_type1_Id2.isEmpty()) {
                        logger.trace(logPrfx + " --- o_type1_Id2: is null");
                    }else{
                        logger.trace(logPrfx + " --- o_type1_Id2 :" + o_type1_Id2.get());
                        sb.append(o_type1_Id2);
                    }

                    //ts1.elTs optional
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: is null");
                    }else{
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    //txt1 optional
                    Optional<String> o_txt1 = Optional.ofNullable(thisNode.getTxt1());
                    if (o_txt1.isEmpty()) {
                        logger.debug(logPrfx + " --- o_txt1: is null");
                    }else{
                        logger.debug(logPrfx + " --- o_txt1: " + o_txt1.get());
                    }

                    switch (o_type1_Id2.get()){
                        case "Rng-Given-Year"->{
                            //ex. "R=Y2023/_;S=All;D=Gen"
                            //Range
                            sb.append(SEP0);
                            sb.append("Range ");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP0);
                            sb.append("Set ");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Week"->{
                            //ex. "R=Y2023/W01/_;S=All;D=Gen"
                            //Range
                            sb.append(SEP0);
                            sb.append("Range ");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            WeekFields weekFields = WeekFields.of(Locale.getDefault());
                            int weekNum = o_ts1_ElTs.get().get(weekFields.weekOfWeekBasedYear());
                            sb.append("W").append(String.format("%2d", weekNum));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP0);
                            sb.append("Set ");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Month"->{
                            //ex. "R=Y2023/M01/_;S=All;D=Gen"
                            //Range
                            sb.append(SEP0);
                            sb.append("Range ");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            sb.append("M").append(String.format("%2d", o_ts1_ElTs.get().getMonthValue()));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP0);
                            sb.append("Set ");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Day"->{
                            //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                            //Range
                            sb.append(SEP0);
                            sb.append("Range ");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            sb.append("M").append(String.format("%2d", o_ts1_ElTs.get().getMonthValue()));
                            sb.append(SEP1);
                            //if necessary add up to 2 leading zeros if
                            sb.append("D").append(String.format("%2d", o_ts1_ElTs.get().getDayOfMonth()));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP0);
                            sb.append("Set ");
                            sb.append(o_txt1.get());

                        }
                        default->{
                            logger.trace(logPrfx + " --- case o_type1_Id2:" + o_type1_Id2 + " not programmed");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }
                    }

                    //finDept1_Id.id2 optional
                    Optional<String> o_finDept1_Id2 = Optional.ofNullable(thisNode.getFinDept1_Id()).map(UsrNodeBase::getId2);
                    if (o_finDept1_Id2.isEmpty()){
                        logger.trace(logPrfx + " --- o_finDept1_Id2: is null");
                    }else{
                        logger.trace(logPrfx + " --- o_finDept1_Id2: " + o_finDept1_Id2.get());
                        sb.append(SEP0);
                        sb.append("Dept [");
                        sb.append(o_finDept1_Id2);
                        sb.append("]");
                    }

                }else {
                    //o_parent1_Id.isPresent()

                    //type1_Id.id2 required
                    Optional<String> o_type1_Id2 = Optional.ofNullable(o_parent1_Id.get().getType1_Id()).map(UsrNodeBaseType::getId2);
                    if (o_type1_Id2.isEmpty()) {
                        logger.trace(logPrfx + " --- o_type1_Id2: is null");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    } else {
                        logger.trace(logPrfx + " --- o_type1_Id2 :" + o_type1_Id2.get());
                        sb.append(o_type1_Id2);
                    }

                    //ts1.elTs optional
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(o_parent1_Id.get().getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: is null");
                    } else {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    //txt1 optional
                    Optional<String> o_txt1 = Optional.ofNullable(o_parent1_Id.get().getTxt1());
                    if (o_txt1.isEmpty()) {
                        logger.debug(logPrfx + " --- o_txt1: is null");
                    } else {
                        logger.debug(logPrfx + " --- o_txt1: " + o_txt1.get());
                    }

                    switch (o_type1_Id2.get()) {
                        case "Rng-Given-Year" -> {
                            //ex. "R=Y2023/_;S=All;D=Gen"
                            //Range
                            sb.append(SEP0);
                            sb.append("Range ");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP0);
                            sb.append("Set ");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Week" -> {
                            //ex. "R=Y2023/W01/_;S=All;D=Gen"
                            //Range
                            sb.append(SEP0);
                            sb.append("Range ");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            WeekFields weekFields = WeekFields.of(Locale.getDefault());
                            int weekNum = o_ts1_ElTs.get().get(weekFields.weekOfWeekBasedYear());
                            sb.append("W").append(String.format("%2d", weekNum));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP0);
                            sb.append("Set ");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Month" -> {
                            //ex. "R=Y2023/M01/_;S=All;D=Gen"
                            //Range
                            sb.append(SEP0);
                            sb.append("Range ");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            sb.append("M").append(String.format("%2d", o_ts1_ElTs.get().getMonthValue()));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP0);
                            sb.append("Set ");
                            sb.append(o_txt1.get());

                        }
                        case "Rng-Given-Day" -> {
                            //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                            //Range
                            sb.append(SEP0);
                            sb.append("Range ");
                            sb.append("Y").append(o_ts1_ElTs.get().getYear());
                            sb.append(SEP1);
                            sb.append("_");
                            //if necessary add up to 2 leading zeros if
                            sb.append("M").append(String.format("%2d", o_ts1_ElTs.get().getMonthValue()));
                            sb.append(SEP1);
                            //if necessary add up to 2 leading zeros if
                            sb.append("D").append(String.format("%2d", o_ts1_ElTs.get().getDayOfMonth()));
                            sb.append(SEP1);
                            sb.append("_");
                            //Set
                            sb.append(SEP0);
                            sb.append("Set ");
                            sb.append(o_txt1.get());

                        }
                        default -> {
                            logger.trace(logPrfx + " --- case o_type1_Id2:" + o_type1_Id2 + " not programmed");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }
                    }

                    //finDept1_Id.id2 optional
                    Optional<String> o_finDept1_Id2 = Optional.ofNullable(thisNode.getFinDept1_Id()).map(UsrNodeBase::getId2);
                    if (o_finDept1_Id2.isEmpty()){
                        logger.trace(logPrfx + " --- o_finDept1_Id2: is null");
                    }else{
                        logger.trace(logPrfx + " --- o_finDept1_Id2: " + o_finDept1_Id2.get());
                        sb.append(SEP0);
                        sb.append("Dept [");
                        sb.append(o_finDept1_Id2);
                        sb.append("]");
                    }
                }

                //finAcct1_Id.id2 optional
                Optional<String> o_finAcct1_Id2 = Optional.ofNullable(thisNode.getFinAcct1_Id()).map(UsrNodeFinAcct::getId2);
                if (o_finAcct1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finAcct1_Id2: is null");
                }else{
                    logger.debug(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                    sb.append("acct [");
                    sb.append(o_finAcct1_Id2.get());
                    sb.append("]");
                }

                //amtEndBalCalc optional
                Optional<BigDecimal> o_amtEndBalCalc = Optional.ofNullable(thisNode.getAmtEndBalCalc());
                if (o_amtEndBalCalc.isEmpty()) {
                    logger.debug(logPrfx + " --- amtEndBalCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- amtEndBalCalc: " + o_amtEndBalCalc.get().setScale(2, RoundingMode.HALF_UP));
                    sb.append(SEP0);
                    sb.append("endBalCalc ");
                    sb.append(o_amtEndBalCalc.get().setScale(2, RoundingMode.HALF_UP));
                }

                //genTags1_Id.map.id2 optional
                Optional<List<UsrItemGenTag>> o_genTags1_Id = Optional.ofNullable(thisNode.getGenTags1_Id());

                if (o_genTags1_Id.isEmpty()) {
                    logger.debug(logPrfx + " --- o_genTags1_Id: is null");
                }else{

                    List<UsrItemGenTag> genTags1_Id_AsList =  o_genTags1_Id.orElse(new ArrayList<UsrItemGenTag>());

                    String genTags1_Id_AsStr = Optional.of(
                            genTags1_Id_AsList
                                    .stream()
                                    .map(UsrItemGenTag::getId2)
                                    .collect(Collectors.joining(","))
                    ).orElse("");
                    if (genTags1_Id_AsStr.equals("")) {
                        logger.debug(logPrfx + " --- genTags1_Id_AsStr: is blank");
                    }else{
                        logger.debug(logPrfx + " --- genTags1_Id_AsStr: " + genTags1_Id_AsStr);
                        sb.append(SEP0);
                        sb.append("tag [");
                        sb.append(genTags1_Id_AsStr);
                        sb.append("]");
                    }
                }

                String desc1_ = thisNode.getDesc1();
                String desc1 = sb.toString().strip();
                logger.debug(logPrfx + " --- desc1: " + desc1);

                if (Objects.equals(desc1_, desc1)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setDesc1(desc1);
                    logger.debug(logPrfx + " --- called thisNode.setDesc1(desc1)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>txt1</i> field dependent fields</h1>
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
    public Boolean updateTxt1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTxt1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;
        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>ts1</i> field dependent fields</h1>
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
    public Boolean updateTs1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;
        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

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
    public Boolean updateTs2Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;
        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateManVals(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateManVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtDebt(screen, thisNode, updOption) || isChanged;
        isChanged = updateAmtCred(screen, thisNode, updOption) || isChanged;

        isChanged = updateAmtNet(screen, thisNode, updOption) || isChanged;

        isChanged = updateAmtBegBal(screen, thisNode, updOption) || isChanged;
        isChanged = updateAmtEndBal(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtDebt(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtDebt";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {
                BigDecimal amtDebt_ = thisNode.getAmtDebt();
                BigDecimal amtDebt = thisNode.getFinTxactItms1_AmtDebtSumCalc();

                logger.debug(logPrfx + " --- amtDebt: " + amtDebt.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtDebt_, amtDebt)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setAmtDebt(amtDebt);
                    logger.debug(logPrfx + " --- called thisNode.setAmtDebt(amtDebt)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtDebtDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtDebtDeps";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;

        isChanged = updateAmtNet(screen, thisNode, updOption) || isChanged;
        isChanged = updateAmtEndBalCalc(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtCred(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtCred";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {
                BigDecimal amtCred_ = thisNode.getAmtCred();
                BigDecimal amtCred = thisNode.getFinTxactItms1_AmtCredSumCalc();

                logger.debug(logPrfx + " --- amtCred: " + amtCred.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtCred_, amtCred)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setAmtCred(amtCred);
                    logger.debug(logPrfx + " --- called thisNode.setAmtCred(amtCred)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtCredDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtCredDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtNet(screen, thisNode, updOption) || isChanged;
        isChanged = updateAmtEndBalCalc(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtNet(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {

                BigDecimal amtNet_ = thisNode.getAmtNet();
                BigDecimal amtNet = null;
                if (thisNode.getAmtDebt() != null && thisNode.getAmtCred() != null){
                    if (thisNode.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                            && thisNode.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                        amtNet = thisNode.getAmtDebt().subtract(thisNode.getAmtCred());

                    }else{
                        amtNet = thisNode.getAmtCred().subtract(thisNode.getAmtDebt());
                    }
                }
                logger.debug(logPrfx + " --- amtNet: " + amtNet.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtNet_, amtNet)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setAmtNet(amtNet);
                    logger.debug(logPrfx + " --- called thisNode.setAmtNet(amtNet)");
                    isChanged = true;
                }
            }
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtBegBal(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBal";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {
                BigDecimal amtBegBal_ = thisNode.getAmtBegBal();
                BigDecimal amtBegBal = thisNode.getAmtBegBalCalc();
                logger.debug(logPrfx + " --- amtBegBal: " + amtBegBal.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtBegBal_, amtBegBal)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setAmtBegBal(amtBegBal);
                    logger.debug(logPrfx + " --- called thisNode.setAmtBegBal(amtBegBal)");
                    isChanged = true;
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtBegBalCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {
                BigDecimal amtBegBalCalc_ = thisNode.getAmtBegBalCalc();
                BigDecimal amtBegBalCalc = null;

                if (thisNode.getFinBal1_Id() == null) {
                    amtBegBalCalc = BigDecimal.ZERO;
                }else{
                    amtBegBalCalc = thisNode.getFinBal1_Id().getAmtEndBalCalc();
                }

                logger.debug(logPrfx + " --- amtBegBalCalc: " + amtBegBalCalc.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtBegBalCalc_, amtBegBalCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setAmtBegBalCalc(amtBegBalCalc);
                    logger.debug(logPrfx + " --- called thisNode.setAmtBegBalCalc(amtBegBalCalc)");
                    isChanged = true;
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtBegBalCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBalCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtEndBalCalc(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtEndBal(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtEndBal";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {

                BigDecimal amtEndBal_ = thisNode.getAmtEndBal();
                BigDecimal amtEndBal = thisNode.getAmtBegBalCalc() == null
                        || thisNode.getAmtNet() == null
                        ? null
                        : thisNode.getAmtBegBalCalc().add(thisNode.getAmtNet());

                logger.debug(logPrfx + " --- amtEndBal: " + amtEndBal.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtEndBal_, amtEndBal)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setAmtEndBal(amtEndBal);
                    logger.debug(logPrfx + " --- called thisNode.setAmtEndBal(amtEndBal)");
                    isChanged = true;
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtEndBalCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtEndBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case     LOCAL
                    ,LOCAL__REF_TO_EXIST
                    ,LOCAL__REF_TO_EXIST_NEW
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST
                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                    -> {

                BigDecimal amtEndBalCalc_ = thisNode.getAmtEndBalCalc();
                BigDecimal amtEndBalCalc = null;
                if (thisNode.getAmtNet() != null){
                    if (thisNode.getAmtBegBalCalc() != null ){
                        amtEndBalCalc = thisNode.getAmtBegBalCalc().add(thisNode.getAmtNet());
                    }else {
                        if (thisNode.getAmtBegBal() != null) {
                            amtEndBalCalc = thisNode.getAmtBegBal().add(thisNode.getAmtNet());
                        }
                    }
                }
                logger.debug(logPrfx + " --- amtEndBalCalc: " + amtEndBalCalc.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtEndBalCalc_, amtEndBalCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setAmtEndBalCalc(amtEndBalCalc);
                    logger.debug(logPrfx + " --- called thisNode.setAmtEndBalCalc(amtEndBalCalc)");
                    isChanged = true;
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinTxactItms1_FieldSet(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinTxactItms1_FieldSet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            logger.debug(logPrfx + " --- updOption: " + updOption.toString());
            switch(updOption) {
                case SKIP -> {
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }
                case     LOCAL
                        ,LOCAL__REF_TO_EXIST
                        ,LOCAL__REF_TO_EXIST_NEW
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                        -> {

                    UsrNodeBase finAcct1 = thisNode.getFinAcct1_Id();
                    UsrNodeBase finDept1 = thisNode.getFinDept1_Id();

                    if (finAcct1 == null) {
                        logger.debug(logPrfx + " --- finAcct1: is null.");
                        notifications.create().withCaption("finAcct1: is null.").show();
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    LocalDate begDt = thisNode.getTs1() == null ? null : thisNode.getTs1().getElDt();
                    if (begDt == null) {
                        logger.debug(logPrfx + " --- begDt: is null.");
                        notifications.create().withCaption("begDt: is null.").show();
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }
                    LocalDate endDt = thisNode.getTs2() == null ? null : thisNode.getTs2().getElDt();
                    if (endDt == null) {
                        logger.debug(logPrfx + " --- endDt: is null.");
                        notifications.create().withCaption("endDt: is null.").show();
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    Integer finTxactItms1_IdCntCalc_ = thisNode.getFinTxactItms1_IdCntCalc();
                    Integer finTxactItms1_IdCntCalc = null;

                    try{
                        String finTxactItms1_QryCnt;
                        if (finDept1 == null) {
                            finTxactItms1_QryCnt = "select count(e)"
                                    + " from enty_UsrNodeFinTxactItm e"
                                    + " where e.finAcct1_Id = :finAcct1_Id"
                                    + " and e.ts1.elDt between :begDt and :endDt"
                                    + "";
                            finTxactItms1_IdCntCalc = dataManager.loadValue(finTxactItms1_QryCnt,Integer.class)
                                    .store("main")
                                    .parameter("finAcct1_Id",finAcct1)
                                    .parameter("begDt",begDt)
                                    .parameter("endDt",endDt)
                                    .one();
                        }else{
                            finTxactItms1_QryCnt = "select count(e) from enty_UsrNodeFinTxactItm e"
                                    + " where e.finAcct1_Id = :finAcct1_Id"
                                    + " and e.finDept1_Id = :finDept1_Id"
                                    + " and e.ts1.elDt between :begDt and :endDt"
                                    + "";
                            finTxactItms1_IdCntCalc = dataManager.loadValue(finTxactItms1_QryCnt,Integer.class)
                                    .store("main")
                                    .parameter("finAcct1_Id",finAcct1)
                                    .parameter("finDept1_Id",finDept1)
                                    .parameter("begDt",begDt)
                                    .parameter("endDt",endDt)
                                    .one();
                        }
                        if (finTxactItms1_IdCntCalc == null) {
                            finTxactItms1_IdCntCalc = 0;}
                        logger.debug(logPrfx + " --- finTxactItms1_IdCntCalc: " + finTxactItms1_IdCntCalc);

                    } catch (IllegalStateException e){
                        logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                        logger.debug(logPrfx + " --- finTxactItms1_IdCntCalc: is null");
                    }

                    if (Objects.equals(finTxactItms1_IdCntCalc_, finTxactItms1_IdCntCalc)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_IdCntCalc(finTxactItms1_IdCntCalc);
                        logger.debug(logPrfx + " --- thisNode.setFinTxactItms1_IdCntCalc(finTxactItms1_IdCntCalc)");
                    }

                    BigDecimal finTxactItms1_AmtDebtSumCalc_ = thisNode.getFinTxactItms1_AmtDebtSumCalc();
                    BigDecimal finTxactItms1_AmtDebtSumDiff_ = thisNode.getFinTxactItms1_AmtDebtSumDiff();

                    BigDecimal finTxactItms1_AmtDebtSumCalc = null;
                    BigDecimal finTxactItms1_AmtDebtSumDiff = null;

                    try{
                        String finTxactItms1_QryDebt;
                        if (finDept1 == null) {
                            finTxactItms1_QryDebt = "select sum(e.amtDebt) from enty_UsrNodeFinTxactItm e"
                                    + " where e.finAcct1_Id = :finAcct1_Id"
                                    + " and e.ts1.elDt between :begDt and :endDt"
                                    + "";
                            finTxactItms1_AmtDebtSumCalc = dataManager.loadValue(finTxactItms1_QryDebt,BigDecimal.class)
                                    .store("main")
                                    .parameter("finAcct1_Id",finAcct1)
                                    .parameter("begDt",begDt)
                                    .parameter("endDt",endDt)
                                    .one();
                        }else{
                            finTxactItms1_QryDebt = "select sum(e.amtDebt) from enty_UsrNodeFinTxactItm e"
                                    + " where e.finAcct1_Id = :finAcct1_Id"
                                    + " and e.finDept1_Id = :finDept1_Id"
                                    + " and e.ts1.elDt between :begDt and :endDt"
                                    + "";
                            finTxactItms1_AmtDebtSumCalc = dataManager.loadValue(finTxactItms1_QryDebt,BigDecimal.class)
                                    .store("main")
                                    .parameter("finAcct1_Id",finAcct1)
                                    .parameter("finDept1_Id",finDept1)
                                    .parameter("begDt",begDt)
                                    .parameter("endDt",endDt)
                                    .one();
                        }
                        if (finTxactItms1_AmtDebtSumCalc == null) {
                            finTxactItms1_AmtDebtSumCalc = BigDecimal.valueOf(0);}
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: " + finTxactItms1_AmtDebtSumCalc);

                        if (thisNode.getAmtDebt() == null){
                            finTxactItms1_AmtDebtSumDiff = BigDecimal.valueOf(0);
                        }else{
                            finTxactItms1_AmtDebtSumDiff = thisNode.getAmtDebt().subtract(finTxactItms1_AmtDebtSumCalc);
                        }
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: " + finTxactItms1_AmtDebtSumDiff);

                    } catch (IllegalStateException e){
                        logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: is null");
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: is null");
                    }

                    if (Objects.equals(finTxactItms1_AmtDebtSumCalc_, finTxactItms1_AmtDebtSumCalc)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtDebtSumCalc(finTxactItms1_AmtDebtSumCalc);
                        logger.debug(logPrfx + " --- thisNode.setFinTxactItms1_AmtDebtSumCalc(finTxactItms1_AmtDebtSumCalc)");
                    }

                    if (Objects.equals(finTxactItms1_AmtDebtSumDiff_, finTxactItms1_AmtDebtSumDiff)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtDebtSumDiff(finTxactItms1_AmtDebtSumDiff);
                        logger.debug(logPrfx + " --- thisNode.setFinTxactItms1_AmtDebtSumDiff(finTxactItms1_AmtDebtSumDiff)");
                    }

                    BigDecimal finTxactItms1_AmtCredSumCalc_ = thisNode.getFinTxactItms1_AmtCredSumCalc();
                    BigDecimal finTxactItms1_AmtCredSumDiff_ = thisNode.getFinTxactItms1_AmtCredSumDiff();

                    BigDecimal finTxactItms1_AmtCredSumCalc = null;
                    BigDecimal finTxactItms1_AmtCredSumDiff = null;

                    try {
                        String finTxactItms1_QryCred;
                        if (finDept1 == null) {
                            finTxactItms1_QryCred = "select sum(e.amtCred) from enty_UsrNodeFinTxactItm e"
                                    + " where e.finAcct1_Id = :finAcct1_Id"
                                    + " and e.ts1.elDt between :begDt and :endDt"
                                    + "";
                            finTxactItms1_AmtCredSumCalc = dataManager.loadValue(finTxactItms1_QryCred,BigDecimal.class)
                                    .store("main")
                                    .parameter("finAcct1_Id",finAcct1)
                                    .parameter("begDt",begDt)
                                    .parameter("endDt",endDt)
                                    .one();
                        }else{
                            finTxactItms1_QryCred = "select sum(e.amtCred) from enty_UsrNodeFinTxactItm e"
                                    + " where e.finAcct1_Id = :finAcct1_Id"
                                    + " and e.finDept1_Id = :finDept1_Id"
                                    + " and e.ts1.elDt between :begDt and :endDt"
                                    + "";
                            finTxactItms1_AmtCredSumCalc = dataManager.loadValue(finTxactItms1_QryCred,BigDecimal.class)
                                    .store("main")
                                    .parameter("finAcct1_Id",finAcct1)
                                    .parameter("finDept1_Id",finDept1)
                                    .parameter("begDt",begDt)
                                    .parameter("endDt",endDt)
                                    .one();
                        }
                        if (finTxactItms1_AmtCredSumCalc == null) {
                            finTxactItms1_AmtCredSumCalc = BigDecimal.valueOf(0);}
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: " + finTxactItms1_AmtCredSumCalc);

                        if (thisNode.getAmtDebt() == null){
                            finTxactItms1_AmtCredSumDiff = BigDecimal.valueOf(0);
                        }else{
                            finTxactItms1_AmtCredSumDiff = thisNode.getAmtCred().subtract(finTxactItms1_AmtCredSumCalc);
                        }
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: " + finTxactItms1_AmtCredSumDiff);

                    } catch (IllegalStateException e ){
                        logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: is null");
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: is null");
                    }

                    if (Objects.equals(finTxactItms1_AmtCredSumCalc_, finTxactItms1_AmtCredSumCalc)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtCredSumCalc(finTxactItms1_AmtCredSumCalc);
                        logger.debug(logPrfx + " --- thisNode.setFinTxactItms1_AmtCredSumCalc(finTxactItms1_AmtCredSumCalc)");
                    }

                    if (Objects.equals(finTxactItms1_AmtCredSumDiff_, finTxactItms1_AmtCredSumDiff)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtCredSumDiff(finTxactItms1_AmtCredSumDiff);
                        logger.debug(logPrfx + " --- thisNode.setFinTxactItms1_AmtCredSumDiff(finTxactItms1_AmtCredSumDiff)");
                    }

                    BigDecimal finTxactItms1_AmtNetSumCalc_ = thisNode.getFinTxactItms1_AmtNetSumCalc();
                    BigDecimal finTxactItms1_AmtNetSumDiff_ = thisNode.getFinTxactItms1_AmtNetSumDiff();

                    BigDecimal finTxactItms1_AmtNetSumCalc = null;
                    BigDecimal finTxactItms1_AmtNetSumDiff = null;

                    if (finTxactItms1_AmtDebtSumCalc == null || finTxactItms1_AmtCredSumCalc == null){
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: is null");
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: is null");
                    }else{

                        if (thisNode.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                                && thisNode.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                            finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtDebtSumCalc.subtract(finTxactItms1_AmtCredSumCalc);
                        }else{
                            finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtCredSumCalc.subtract(finTxactItms1_AmtDebtSumCalc);
                        }

                        if (Objects.equals(finTxactItms1_AmtNetSumCalc_, finTxactItms1_AmtNetSumCalc)){
                            logger.debug(logPrfx + " --- no change detected");
                        }else{
                            thisNode.setFinTxactItms1_AmtNetSumCalc(finTxactItms1_AmtNetSumCalc);
                            logger.debug(logPrfx + " --- thisNode.setFinTxactItms1_AmtNetSumCalc(finTxactItms1_AmtNetSumCalc)");
                        }

                        finTxactItms1_AmtNetSumDiff = finTxactItms1_AmtNetSumCalc.subtract(thisNode.getAmtNet());

                        if (Objects.equals(finTxactItms1_AmtNetSumDiff_, finTxactItms1_AmtNetSumDiff)){
                            logger.debug(logPrfx + " --- no change detected");
                        }else{
                            thisNode.setFinTxactItms1_AmtNetSumDiff(finTxactItms1_AmtNetSumDiff);
                            logger.debug(logPrfx + " --- thisNode.setFinTxactItms1_AmtNetSumDiff(finTxactItms1_AmtNetSumDiff)");
                        }
                    }

                    Boolean finTxactItms1_AmtEqCalc_ = thisNode.getFinTxactItms1_AmtEqCalc();
                    Boolean finTxactItms1_AmtEqCalc = finTxactItms1_AmtNetSumDiff == null ? null : finTxactItms1_AmtNetSumDiff.compareTo(BigDecimal.ZERO) == 0;

                    if (Objects.equals(finTxactItms1_AmtEqCalc_, finTxactItms1_AmtEqCalc)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtEqCalc(finTxactItms1_AmtEqCalc);
                        logger.debug(logPrfx + " --- thisNode.setFinTxactItms1_AmtEqCalc(finTxactItms1_AmtEqCalc)");
                    }
                }
            }
        }



        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public List<UsrNodeFinBal> findFinBals(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findFinBals";
        logger.trace(logPrfx + " --> ");


        List<UsrNodeFinBal> qryRsltNodes = new ArrayList<UsrNodeFinBal>();
        LogicalCondition cond = LogicalCondition.and();
        cond.add(PropertyCondition.equal("dtype", "enty_UsrNodeFinBal"));

        //Optional finDept1_Id
        //thisNode.finDept1.Id -> finDept1.Id
        Optional<UsrNodeFinDept> o_finDept1_Id = Optional.ofNullable(thisNode.getFinDept1_Id());
        if (o_finDept1_Id.isPresent()){
            cond.add(PropertyCondition.equal("finDept1_Id", thisNode.getFinDept1_Id()));
        } else {
            cond.add(PropertyCondition.isSet("finDept1_Id", false));
        }

        //thisNode.finAcct1.Id -> finAcct1.Id
        cond.add(PropertyCondition.equal("finAcct1_Id", thisNode.getFinAcct1_Id()));

        //thisNode.ts1.elTs - 1 day -> ts2.elTs
        cond.add(PropertyCondition.equal("ts2.elTs", thisNode.getTs1().getElTs().minusMinutes(1)));
        
        qryRsltNodes = dataManager.load(UsrNodeFinBal.class).condition(cond).list();

        logger.trace(logPrfx + " <-- ");
        return qryRsltNodes;
    }

    @Override
    public UsrNodeFinBal createFinBal(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createFinBal";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBal mergedFinBal = null;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            DataContext dataContext = UiControllerUtils.getScreenData(screen).getDataContext();

            UsrNodeFinBal finBal1_Id = dataManager.create(UsrNodeFinBal.class);

            //thisNode.finAcct1.id -> finAcct1.id
            finBal1_Id.setFinAcct1_Id(thisNode.getFinAcct1_Id());

            //thisNode.ts1.elTs - 1 day -> ts2.elTs
            HasTmst ts2 = dataManager.create(HasTmst.class);
            ts2.setElTs(thisNode.getTs1().getElTs().minusDays(1));
            finBal1_Id.setTs2(ts2);

            serviceFinBal1.updateId2Calc(screen, finBal1_Id, UpdateOption.LOCAL);
            serviceFinBal1.updateId2(screen, finBal1_Id, UpdateOption.LOCAL);
            serviceFinBal1.updateId2Cmp(screen, finBal1_Id, UpdateOption.LOCAL);
            serviceFinBal1.updateId2Dup(screen, finBal1_Id, UpdateOption.LOCAL);

            UsrNodeFinBal savedFinBal = dataManager.save(finBal1_Id);

            mergedFinBal = dataContext.merge(savedFinBal);
            logger.debug(logPrfx + " --- created FinBal id: " + mergedFinBal.getId());
            notifications.create().withCaption("Created FinBal with id2:" + mergedFinBal.getId2()).show();
        }

        logger.trace(logPrfx + " <-- ");
        return mergedFinBal;
    }



}