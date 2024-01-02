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
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.flowui.view.View;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.ViewControllerUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component("bean_UsrNodeFinStmt.Service")
public class UsrNodeFinStmt0Service extends UsrNodeBase0Service {

    public UsrNodeFinStmt0Service() { this.typeOfNode = UsrNodeFinStmt.class; }

    @Autowired
    protected UsrNodeFinStmt0Service serviceFinStmt1;

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
     *          <li><i>sortKey</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateCalcVals(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmt1_Id(view, thisNode, updOption) || isChanged;

        isChanged = updateAmtNet(view, thisNode, updOption) || isChanged;
        isChanged = updateAmtBegBalCalc(view, thisNode, updOption) || isChanged;
        isChanged = updateAmtEndBalCalc(view, thisNode, updOption) || isChanged;
        isChanged = updateFinStmtItms1_FieldSet(view, thisNode, updOption) || isChanged;
        isChanged = updateFinTxactItms1_FieldSet(view, thisNode, updOption) || isChanged;

        isChanged = updateDesc1(view, thisNode, updOption) || isChanged;
        isChanged = updateInst1(view, thisNode, updOption) || isChanged;
        isChanged = updateName1(view, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(view, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(view, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(view, thisNode, updOption) || isChanged;
        isChanged = updateSortKey(view, thisNode, updOption) || isChanged;

        isChanged = updateFinStmtItms1_FieldSet(view, thisNode, updOption)  || isChanged;

        isChanged = updateFinStmtItms1_IdCntCalc(view, thisNode, updOption)  || isChanged;
        isChanged = updateFinStmtItms1_AmtEqCalc(view, thisNode, updOption)  || isChanged;

        isChanged = updateFinTxactItms1_FieldSet(view, thisNode, updOption)  || isChanged;

        isChanged = updateFinTxactItms1_IdCntCalc(view, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtEqCalc(view, thisNode, updOption)  || isChanged;

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
    public Boolean updateInst1(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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

                //finAcct1_Id.id2 required
                Optional<String> o_finAcct1_Id2 = Optional.ofNullable(thisNode.getFinAcct1_Id()).map(UsrNodeFinAcct::getId2);
                if (o_finAcct1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finAcct1_Id2: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                    sb.append(o_finAcct1_Id2.get());
                }

                //ts2.elTs (tsEnd) required
                Optional<LocalDateTime> o_ts2_ElTs = Optional.ofNullable(thisNode.getTs2()).map(HasTmst::getElTs);
                if (o_ts2_ElTs.isEmpty()) {
                    logger.debug(logPrfx + " --- o_ts2_ElTs: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_ts2_ElTs: " + o_ts2_ElTs.get().format(FRMT_DT));
                    sb.append(SEP1);
                    sb.append("D");
                    sb.append(o_ts2_ElTs.get().format(FRMT_DT));
                }


                String inst1_ = thisNode.getInst1();
                String inst1 = sb.toString();
                logger.debug(logPrfx + " --- inst1: " + inst1);

                if (Objects.equals(inst1_, inst1)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setInst1(inst1);
                    logger.debug(logPrfx + " --- called thisNode.setInst1(inst1)");
                    isChanged = true;
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
    public Boolean updateDesc1(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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

                //finDept1.id2 optional
                Optional<String> o_finDept1_Id2 = Optional.ofNullable(thisNode.getFinDept1_Id()).map(UsrNodeFinDept::getId2);
                if(o_finDept1_Id2.isEmpty()){
                    logger.debug(logPrfx + " --- o_finDept1_Id2: is null");
                }else{
                    logger.debug(logPrfx + " --- o_finDept1_Id2: " + o_finDept1_Id2.get());
                    sb.append(SEP0);
                    sb.append("dept [");
                    sb.append(o_finDept1_Id2.get());
                    sb.append("]");
                }

                //ts1.elTs (tsBeg) optional
                Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                if (o_ts1_ElTs.isEmpty()) {
                    logger.debug(logPrfx + " --- o_ts1_ElTs: is null");
                }else{
                    logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                    sb.append(SEP0);
                    sb.append("begDate ").append(o_ts1_ElTs.get().format(FRMT_DT));
                }

                //ts2.elTs (tsEnd)
                Optional<LocalDateTime> o_ts2_ElTs = Optional.ofNullable(thisNode.getTs2()).map(HasTmst::getElTs);
                if (o_ts2_ElTs.isEmpty()) {
                    logger.debug(logPrfx + " --- o_ts2_ElTs: is null");
                }else{
                    logger.debug(logPrfx + " --- o_ts2_ElTs: " + o_ts2_ElTs.get().format(FRMT_DT));
                    sb.append(SEP0);
                    sb.append("endDate ");
                    sb.append(o_ts2_ElTs.get().format(FRMT_DT));
                }

                //amtEndBalCalc optional
                Optional<BigDecimal> o_amtEndBalCalc = Optional.ofNullable(thisNode.getAmtEndBalCalc());
                if (o_amtEndBalCalc.isEmpty()) {
                    logger.debug(logPrfx + " --- o_amtEndBalCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- o_amtEndBalCalc: " + o_amtEndBalCalc.get().setScale(2, RoundingMode.HALF_UP));
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
    public Boolean updateTs2Deps(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateInst1(view, thisNode, updOption) || isChanged;
        isChanged = this.updateName1(view, thisNode, updOption) || isChanged;
        isChanged = this.updateDesc1(view, thisNode, updOption) || isChanged;

        isChanged = this.updateId2Calc(view, thisNode, updOption) || isChanged;
        isChanged = this.updateId2Cmp(view, thisNode, updOption) || isChanged;
        isChanged = this.updateId2Dup(view, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    //Ex A/CurY/RBC/Chk/D2018-10-31
    //   01234567890123456789012345
    @Transient
    @JmixProperty
    final String id2RegExPat =
            "(.*)"                             //finAcct_Id2
                    +"(/D[0-9]{4}-[0-9]{2}-[0-9]{2})"   //ts1.elTs1
            ;

    @Override
    public Boolean updateIdPartsFrId2(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //isChanged = this.updateFinAcctFrId2(node, option) || isChanged;
        isChanged = this.updateTs1FrId2(view, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTs1FrId2(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //for method substring(begIndex, endIndex)
        //  begIndex zero based, result includes value at this position
        //  endIndex zero based, result excludes value at this position
        //  length = beginIndex - endIndex
        //Ts1 begIndex = 1
        //Ts1 endIndex = 11
        //Ex A/CurY/RBC/Chk/D2018-10-31
        //   01234567890123456789012345

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

                //id2 required
                Optional<String> o_id2 = Optional.ofNullable(thisNode.getId2());
                if (o_id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_id2: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_id2: " + o_id2.get());
                }

                LocalDateTime ts1_ElTs_ = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
                LocalDateTime ts1_ElTs = null;

                Pattern pattern = Pattern.compile(this.id2RegExPat);
                Matcher matcher = pattern.matcher(thisNode.getId2());

                if (matcher.matches()){

                    String id2_part;
                    id2_part = matcher.group(1);

                    try{
                        ts1_ElTs = LocalDateTime.parse(id2_part,this.FRMT_DT);

                    } catch (DateTimeParseException e){

                        logger.trace(logPrfx + " ---- DateTimeParseException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }


                    logger.debug(logPrfx + " --- ts1_ElTs: " + ts1_ElTs.format(FRMT_TS));

                    if (Objects.equals(ts1_ElTs_, ts1_ElTs)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.getTs1().setElTs(ts1_ElTs);
                        logger.debug(logPrfx + " --- called node.getTs1().setElTs(ts1_ElTs)");
                        isChanged = true;
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtDebt(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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
    public Boolean updateAmtDebtDeps(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtDebtDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtNet(view, thisNode, updOption) || isChanged;
        isChanged = updateAmtEndBalCalc(view, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtCred(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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
    public Boolean updateAmtCredDeps(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtCredDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtNet(view, thisNode, updOption) || isChanged;
        isChanged = updateAmtEndBalCalc(view, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtNet(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        Notifications notifications;
        if(view instanceof UsrNodeBase0BaseMain
                || view instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) view).getNotifications();

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
                    BigDecimal amtNet = BigDecimal.ZERO;

                    //finAcct1_Id required
                    Optional<UsrNodeFinAcct> o_finAcct1_Id = Optional.ofNullable(thisNode.getFinAcct1_Id());

                    //finAcct1_Type1_Id required
                    Optional<UsrNodeBaseType> o_finAcct1_Type1_Id = o_finAcct1_Id
                            .map(n->n.getType1_Id());

                    if (o_finAcct1_Type1_Id.isEmpty()){
                        logger.debug(logPrfx + " --- o_finAcct1_Type1_Id: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;

                    }else{

                        //either balIncOnDebt or balIncOnCred  must be true
                        Boolean balIncOnDebt = o_finAcct1_Type1_Id
                                .map(n -> n.getBalIncOnDebt())
                                .orElse(Boolean.FALSE);

                        Boolean balIncOnCred = o_finAcct1_Type1_Id
                                .map(n -> n.getBalIncOnCred())
                                .orElse(Boolean.FALSE);

                        //amtDebt optional
                        Optional<BigDecimal> o_amtDebt = Optional.ofNullable(thisNode.getAmtDebt());
                        if (o_amtDebt.isEmpty()) {
                            logger.debug(logPrfx + " --- o_amtDebt: is empty");
                        }else{
                            logger.debug(logPrfx + " --- o_amtDebt: " + o_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
                        }

                        //amtCred optional
                        Optional<BigDecimal> o_amtCred = Optional.ofNullable(thisNode.getAmtCred());
                        if (o_amtCred.isEmpty()) {
                            logger.debug(logPrfx + " --- o_amtCred: is empty");
                        }else{
                            logger.debug(logPrfx + " --- o_amtCred: " + o_amtCred.get().setScale(2, RoundingMode.HALF_UP));
                        }

                        if (balIncOnDebt) {
                            amtNet = amtNet.add(o_amtDebt.orElse(BigDecimal.ZERO));
                            amtNet = amtNet.subtract(o_amtCred.orElse(BigDecimal.ZERO));
                        } else if (balIncOnCred) {
                            amtNet = amtNet.subtract(o_amtDebt.orElse(BigDecimal.ZERO));
                            amtNet = amtNet.add(o_amtCred.orElse(BigDecimal.ZERO));
                        } else {
                            String msg = "UsrNodeFinAcct [" + o_finAcct1_Id.get().getId2() + "]"
                                    + " with UsrNodeFinAcctType [" + o_finAcct1_Type1_Id.get().getId2() + "]"
                                    + " has fields balIncOnDebt and balIncOnDebt not set. Please set one of them.";
                            logger.debug(logPrfx + " --- " + msg);
                            notifications.create(msg).show();
                            logger.trace(logPrfx + " <-- ");
                            return null;
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
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtBegBal(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBal";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;



        logger.debug(logPrfx + " --- updOption: " + updOption.toString());
        switch(updOption) {
            case SKIP -> {
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
            case LOCAL
                    , LOCAL__REF_TO_EXIST
                    , LOCAL__REF_TO_EXIST_NEW
                    , LOCAL__REF_IF_EMPTY_TO_EXIST
                    , LOCAL__REF_IF_EMPTY_TO_EXIST_NEW -> {

                BigDecimal amtBegBal_ = thisNode.getAmtBegBal();
                BigDecimal amtBegBal = thisNode.getAmtBegBalCalc();

                if(Objects.equals(amtBegBal_, amtBegBal)){
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
    public Boolean updateAmtBegBalCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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


                if (thisNode.getFinStmt1_Id() == null) {
                    amtBegBalCalc = BigDecimal.ZERO;
                }else{
                    amtBegBalCalc = thisNode.getFinStmt1_Id().getAmtEndBalCalc();
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
    public Boolean updateAmtBegBalCalcDeps(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtCredDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtNet(view, thisNode, updOption) || isChanged;
        isChanged = updateAmtEndBalCalc(view, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtEndBalCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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


    public Boolean updateFinStmtItms1_FieldSet(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinStmtItms1_FieldSet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Notifications notifications;
        if(view instanceof UsrNodeBase0BaseMain
                || view instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) view).getNotifications();

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
                    BigDecimal finStmtItms1_AmtDebtSumCalc_ = thisNode.getFinStmtItms1_AmtDebtSumCalc();
                    BigDecimal finStmtItms1_AmtDebtSumDiff_ = thisNode.getFinStmtItms1_AmtDebtSumDiff();

                    BigDecimal finStmtItms1_AmtDebtSumCalc = null;
                    BigDecimal finStmtItms1_AmtDebtSumDiff = null;
                    String finStmtItms1_QryDebt = "select sum(e.amtDebt) from enty_UsrNodeFinStmtItm e where e.finStmt1_Id = :finStmt1_Id";
                    try{
                        finStmtItms1_AmtDebtSumCalc = dataManager.loadValue(finStmtItms1_QryDebt,BigDecimal.class)
                                .store("main")
                                .parameter("finStmt1_Id",thisNode)
                                .one();
                        if (finStmtItms1_AmtDebtSumCalc == null) {
                            finStmtItms1_AmtDebtSumCalc = BigDecimal.valueOf(0);}
                        logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumCalc: " + finStmtItms1_AmtDebtSumCalc);

                        if (thisNode.getAmtDebt() == null){
                            finStmtItms1_AmtDebtSumDiff = BigDecimal.valueOf(0);
                        }else{
                            finStmtItms1_AmtDebtSumDiff = thisNode.getAmtDebt().subtract(finStmtItms1_AmtDebtSumCalc);
                        }
                        logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumDiff: " + finStmtItms1_AmtDebtSumDiff);

                    } catch (IllegalStateException e){
                        logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                        logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumCalc: is null");
                        logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumDiff: is null");
                    }

                    if (Objects.equals(finStmtItms1_AmtDebtSumCalc_, finStmtItms1_AmtDebtSumCalc)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinStmtItms1_AmtDebtSumCalc(finStmtItms1_AmtDebtSumCalc);
                        logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtDebtSumCalc(finStmtItms1_AmtDebtSumCalc)");
                        isChanged = true;
                    }

                    if (Objects.equals(finStmtItms1_AmtDebtSumDiff_, finStmtItms1_AmtDebtSumDiff)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinStmtItms1_AmtDebtSumDiff(finStmtItms1_AmtDebtSumDiff);
                        logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtDebtSumDiff(finStmtItms1_AmtDebtSumDiff)");
                        isChanged = true;
                    }

                    BigDecimal finStmtItms1_AmtCredSumCalc_ = thisNode.getFinStmtItms1_AmtCredSumCalc();
                    BigDecimal finStmtItms1_AmtCredSumDiff_ = thisNode.getFinStmtItms1_AmtCredSumDiff();

                    BigDecimal finStmtItms1_AmtCredSumCalc = null;
                    BigDecimal finStmtItms1_AmtCredSumDiff = null;

                    String finStmtItms1_QryCred = "select sum(e.amtCred) from enty_UsrNodeFinStmtItm e where e.finStmt1_Id = :finStmt1_Id";
                    try {
                        finStmtItms1_AmtCredSumCalc = dataManager.loadValue(finStmtItms1_QryCred, BigDecimal.class)
                                .store("main")
                                .parameter("finStmt1_Id", thisNode)
                                .one();
                        if (finStmtItms1_AmtCredSumCalc == null) {
                            finStmtItms1_AmtCredSumCalc = BigDecimal.valueOf(0);}
                        logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumCalc: " + finStmtItms1_AmtCredSumCalc);

                        if (thisNode.getAmtDebt() == null){
                            finStmtItms1_AmtCredSumDiff = BigDecimal.valueOf(0);
                        }else{
                            finStmtItms1_AmtCredSumDiff = thisNode.getAmtCred().subtract(finStmtItms1_AmtCredSumCalc);
                        }
                        logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumDiff: " + finStmtItms1_AmtCredSumDiff);

                    } catch (IllegalStateException e ){
                        logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                        logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumCalc: is null");
                        logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumDiff: is null");
                    }

                    if (Objects.equals(finStmtItms1_AmtCredSumCalc_, finStmtItms1_AmtCredSumCalc)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinStmtItms1_AmtCredSumCalc(finStmtItms1_AmtCredSumCalc);
                        logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtCredSumCalc(finStmtItms1_AmtCredSumCalc)");
                        isChanged = true;
                    }

                    if (Objects.equals(finStmtItms1_AmtCredSumDiff_, finStmtItms1_AmtCredSumDiff)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinStmtItms1_AmtCredSumDiff(finStmtItms1_AmtCredSumDiff);
                        logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtCredSumDiff(finStmtItms1_AmtCredSumDiff)");
                        isChanged = true;
                    }

                    BigDecimal finStmtItms1_AmtNetSumCalc_ = thisNode.getFinStmtItms1_AmtNetSumCalc();
                    BigDecimal finStmtItms1_AmtNetSumDiff_ = thisNode.getFinStmtItms1_AmtNetSumDiff();

                    BigDecimal finStmtItms1_AmtNetSumCalc = null;
                    BigDecimal finStmtItms1_AmtNetSumDiff = null;

                    if (finStmtItms1_AmtDebtSumCalc == null || finStmtItms1_AmtCredSumCalc == null){
                        logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumCalc: is null");
                        logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumCalc: is null");
                    }else{

                        if (thisNode.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                                && thisNode.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                            finStmtItms1_AmtNetSumCalc = finStmtItms1_AmtDebtSumCalc.subtract(finStmtItms1_AmtCredSumCalc);
                        }else{
                            finStmtItms1_AmtNetSumCalc = finStmtItms1_AmtCredSumCalc.subtract(finStmtItms1_AmtDebtSumCalc);
                        }

                        if (Objects.equals(finStmtItms1_AmtNetSumCalc_, finStmtItms1_AmtNetSumCalc)){
                            logger.debug(logPrfx + " --- no change detected");
                        }else{
                            thisNode.setFinStmtItms1_AmtNetSumCalc(finStmtItms1_AmtNetSumCalc);
                            logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtNetSumCalc(finStmtItms1_AmtNetSumCalc)");
                            isChanged = true;
                        }

                        finStmtItms1_AmtNetSumDiff = finStmtItms1_AmtNetSumCalc.subtract(thisNode.getAmtNet());

                        if (Objects.equals(finStmtItms1_AmtNetSumDiff_, finStmtItms1_AmtNetSumDiff)){
                            logger.debug(logPrfx + " --- no change detected");
                        }else{
                            thisNode.setFinStmtItms1_AmtNetSumDiff(finStmtItms1_AmtNetSumDiff);
                            logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtNetSumDiff(finStmtItms1_AmtNetSumDiff)");
                            isChanged = true;
                        }
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinTxactItms1_FieldSet(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinTxactItms1_FieldSet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Notifications notifications;
        if(view instanceof UsrNodeBase0BaseMain
                || view instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) view).getNotifications();

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
                    BigDecimal amtDebt_ = Optional.ofNullable(thisNode.getAmtDebt()).orElse(BigDecimal.ZERO);
                    BigDecimal finTxactItms1_AmtDebtSumCalc_ = thisNode.getFinTxactItms1_AmtDebtSumCalc();
                    BigDecimal finTxactItms1_AmtDebtSumDiff_ = thisNode.getFinTxactItms1_AmtDebtSumDiff();

                    BigDecimal finTxactItms1_AmtDebtSumCalc = null;
                    BigDecimal finTxactItms1_AmtDebtSumDiff = null;
                    String finTxactItms1_QryDebt = "select sum(e.amtDebt)"
                            + " from enty_UsrNodeFinTxactItm e"
                            + " join e.finStmtItm1_Id e2"
                            + " where e2.finStmt1_Id = :finStmt1_Id";
                    try{
                        finTxactItms1_AmtDebtSumCalc = dataManager.loadValue(finTxactItms1_QryDebt,BigDecimal.class)
                                .store("main")
                                .parameter("finStmt1_Id",thisNode)
                                .one();
                    } catch (IllegalStateException e){
                        logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                    }

                    if (finTxactItms1_AmtDebtSumCalc == null) {
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: is null");
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: is null");
                    }else{
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: " + finTxactItms1_AmtDebtSumCalc);
                        finTxactItms1_AmtDebtSumDiff = amtDebt_.subtract(finTxactItms1_AmtDebtSumCalc);
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: " + finTxactItms1_AmtDebtSumDiff);
                    }

                    if (Objects.equals(finTxactItms1_AmtDebtSumCalc_, finTxactItms1_AmtDebtSumCalc)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtDebtSumCalc(finTxactItms1_AmtDebtSumCalc);
                        logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtDebtSumCalc(finTxactItms1_AmtDebtSumCalc)");
                        isChanged = true;
                    }

                    if (Objects.equals(finTxactItms1_AmtDebtSumDiff_, finTxactItms1_AmtDebtSumDiff)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtDebtSumDiff(finTxactItms1_AmtDebtSumDiff);
                        logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtDebtSumDiff(finTxactItms1_AmtDebtSumDiff)");
                        isChanged = true;
                    }

                    BigDecimal amtCred_ = Optional.ofNullable(thisNode.getAmtCred()).orElse(BigDecimal.ZERO);
                    BigDecimal finTxactItms1_AmtCredSumCalc_ = thisNode.getFinTxactItms1_AmtCredSumCalc();
                    BigDecimal finTxactItms1_AmtCredSumDiff_ = thisNode.getFinTxactItms1_AmtCredSumDiff();

                    BigDecimal finTxactItms1_AmtCredSumCalc = null;
                    BigDecimal finTxactItms1_AmtCredSumDiff = null;

                    String finTxactItms1_QryCred = "select sum(e.amtCred)"
                            + " from enty_UsrNodeFinTxactItm"
                            + " e join e.finStmtItm1_Id e2"
                            + " where e2.finStmt1_Id = :finStmt1_Id";
                    try {
                        finTxactItms1_AmtCredSumCalc = dataManager.loadValue(finTxactItms1_QryCred, BigDecimal.class)
                                .store("main")
                                .parameter("finStmt1_Id", thisNode)
                                .one();
                    } catch (IllegalStateException e ){
                        logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                    }

                    if (finTxactItms1_AmtCredSumCalc == null) {
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: is null");
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: is null");
                    }else{
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: " + finTxactItms1_AmtCredSumCalc);
                        finTxactItms1_AmtCredSumDiff = amtCred_.subtract(finTxactItms1_AmtCredSumCalc);
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: " + finTxactItms1_AmtCredSumDiff);
                    }

                    if (Objects.equals(finTxactItms1_AmtCredSumCalc_, finTxactItms1_AmtCredSumCalc)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtCredSumCalc(finTxactItms1_AmtCredSumCalc);
                        logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtCredSumCalc(finTxactItms1_AmtCredSumCalc)");
                        isChanged = true;
                    }

                    if (Objects.equals(finTxactItms1_AmtCredSumDiff_, finTxactItms1_AmtCredSumDiff)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setFinTxactItms1_AmtCredSumDiff(finTxactItms1_AmtCredSumDiff);
                        logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtCredSumDiff(finTxactItms1_AmtCredSumDiff)");
                        isChanged = true;
                    }

                    BigDecimal amtNet_ = Optional.ofNullable(thisNode.getAmtNet()).orElse(BigDecimal.ZERO);
                    BigDecimal finTxactItms1_AmtNetSumCalc_ = thisNode.getFinTxactItms1_AmtNetSumCalc();
                    BigDecimal finTxactItms1_AmtNetSumDiff_ = thisNode.getFinTxactItms1_AmtNetSumDiff();

                    BigDecimal finTxactItms1_AmtNetSumCalc = null;
                    BigDecimal finTxactItms1_AmtNetSumDiff = null;

                    if (finTxactItms1_AmtDebtSumCalc == null || finTxactItms1_AmtCredSumCalc == null){
                        logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: is null");
                        logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: is null");
                    }else{

                        //finAcct1_Id_Type1_Id required
                        Optional<UsrNodeBaseType> o_finAcct1_Type1_Id = Optional.ofNullable(thisNode.getFinAcct1_Id())
                                .map(n->n.getType1_Id());

                        if (o_finAcct1_Type1_Id.isEmpty()){
                            logger.debug(logPrfx + " --- o_finAcct1_Type1_Id: is empty");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;

                        }else {

                            Boolean balIncOnDebt = o_finAcct1_Type1_Id
                                    .map(n -> n.getBalIncOnDebt())
                                    .orElse(Boolean.FALSE);

                            Boolean balIncOnCred = o_finAcct1_Type1_Id
                                    .map(n -> n.getBalIncOnCred())
                                    .orElse(Boolean.FALSE);

                            if (balIncOnDebt) {
                                finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtDebtSumCalc.subtract(finTxactItms1_AmtCredSumCalc);
                            } else if (balIncOnCred) {
                                finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtCredSumCalc.subtract(finTxactItms1_AmtDebtSumCalc);
                            }else{
                                // this is an illegal state
                            }

                            if (Objects.equals(finTxactItms1_AmtNetSumCalc_, finTxactItms1_AmtNetSumCalc)){
                                logger.debug(logPrfx + " --- no change detected");
                            }else{
                                thisNode.setFinTxactItms1_AmtNetSumCalc(finTxactItms1_AmtNetSumCalc);
                                logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtNetSumCalc(finTxactItms1_AmtNetSumCalc)");
                                isChanged = true;
                            }

                            finTxactItms1_AmtNetSumDiff = amtNet_.subtract(finTxactItms1_AmtNetSumCalc);

                        }

                        if (Objects.equals(finTxactItms1_AmtNetSumDiff_, finTxactItms1_AmtNetSumDiff)){
                            logger.debug(logPrfx + " --- no change detected");
                        }else{
                            thisNode.setFinTxactItms1_AmtNetSumDiff(finTxactItms1_AmtNetSumDiff);
                            logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtNetSumDiff(finTxactItms1_AmtNetSumDiff)");
                            isChanged = true;
                        }
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateFinStmtItms1_AmtDebtSumCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtDebtSumCalc";
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
                BigDecimal amtDebtSumCalc_ = thisNode.getFinStmtItms1_AmtDebtSumCalc();
                BigDecimal amtDebtSumCalc = null ;
                String qry1 = "select sum(e.amtDebt)"
                        + " from enty_UsrNodeFinStmtItm e"
                        + " where e.finStmt1_Id = :finStmt1_Id";
                try{
                    amtDebtSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("finStmt1_Id", thisNode)
                            .one();
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                }

                if (amtDebtSumCalc == null) {
                    logger.debug(logPrfx + " --- amtDebtSumCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- amtDebtSumCalc: " + amtDebtSumCalc.setScale(2, RoundingMode.HALF_UP));
                }


                if (Objects.equals(amtDebtSumCalc_, amtDebtSumCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinStmtItms1_AmtDebtSumCalc(amtDebtSumCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtDebtSumCalc(amtDebtSumCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateFinStmtItms1_AmtCredSumCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtCredSumCalc";
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
                BigDecimal amtCredSumCalc_ = thisNode.getFinStmtItms1_AmtCredSumCalc();
                BigDecimal amtCredSumCalc = null ;
                String qry1 = "select sum(e.amtCred)"
                        + " from enty_UsrNodeFinStmtItm e"
                        + " where e.finStmt1_Id = :finStmt1_Id";
                try{
                    amtCredSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("finStmt1_Id", thisNode)
                            .one();
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                }

                if (amtCredSumCalc == null) {
                    logger.debug(logPrfx + " --- amtCredSumCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- amtCredSumCalc: " + amtCredSumCalc.setScale(2, RoundingMode.HALF_UP));
                }

                if (Objects.equals(amtCredSumCalc_, amtCredSumCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinStmtItms1_AmtCredSumCalc(amtCredSumCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtCredSumCalc(amtCredSumCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateFinStmtItms1_AmtNetSumCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtNetSumCalc";
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
                BigDecimal amtNetSumCalc_ = thisNode.getFinStmtItms1_AmtNetSumCalc();
                BigDecimal amtNetSumCalc = null ;
                String qry1 = "select sum(e.amtNet)"
                        + " from enty_UsrNodeFinStmtItm e"
                        + " where e.finStmt1_Id = :finStmt1_Id";
                try{
                    amtNetSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("finStmt1_Id", thisNode)
                            .one();
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                }

                if (amtNetSumCalc == null) {
                    logger.debug(logPrfx + " --- amtNetSumCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- amtNetSumCalc: " + amtNetSumCalc.setScale(2, RoundingMode.HALF_UP));
                }

                if (Objects.equals(amtNetSumCalc_, amtNetSumCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinStmtItms1_AmtNetSumCalc(amtNetSumCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtNetSumCalc(amtNetSumCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateFinStmtItms1_IdCntCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinStmtItms1_IdCntCalc";
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

                Integer idCntCalc_ = thisNode.getFinStmtItms1_IdCntCalc();
                Integer idCntCalc = null ;
                String qry1 = "select count(e.amtNet)"
                        + " from enty_UsrNodeFinStmtItm e"
                        + " where e.finStmt1_Id = :finStmt1_Id"
                        ;
                try{
                    idCntCalc = dataManager.loadValue(qry1,Integer.class)
                            .store("main")
                            .parameter("finStmt1_Id", thisNode)
                            .one();
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                }

                if (idCntCalc == null) {
                    logger.debug(logPrfx + " --- idCntCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);
                }

                if (Objects.equals(idCntCalc_, idCntCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinStmtItms1_IdCntCalc(idCntCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_IdCntCalc(idCntCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    @Override
    public Boolean updateFinTxactItms1_AmtDebtSumCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalc";
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
                BigDecimal amtDebtSumCalc_ = thisNode.getFinTxactItms1_AmtDebtSumCalc();
                BigDecimal amtDebtSumCalc = null ;
                String qry1 = "select sum(e.amtDebt)"
                        + " from enty_UsrNodeFinTxactItm e"
                        + " join e.finStmtItm1_Id e2"
                        + " where e2.finStmt1_Id = :finStmt1_Id";
                try{
                    amtDebtSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("finStmt1_Id", thisNode)
                            .one();
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                }

                if (amtDebtSumCalc == null) {
                    logger.debug(logPrfx + " --- amtDebtSumCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- amtDebtSumCalc: " + amtDebtSumCalc.setScale(2, RoundingMode.HALF_UP));
                }

                if (Objects.equals(amtDebtSumCalc_, amtDebtSumCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtDebtSumCalc(amtDebtSumCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtDebtSumCalc(amtDebtSumCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateFinTxactItms1_AmtCredSumCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalc";
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
                BigDecimal amtCredSumCalc_ = thisNode.getFinTxactItms1_AmtCredSumCalc();
                BigDecimal amtCredSumCalc = null ;
                String qry1 = "select sum(e.amtCred)"
                        + " from enty_UsrNodeFinTxactItm e"
                        + " join e.finStmtItm1_Id e2"
                        + " where e2.finStmt1_Id = :finStmt1_Id";
                try{
                    amtCredSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("finStmt1_Id", thisNode)
                            .one();
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                }

                if (amtCredSumCalc == null) {
                    logger.debug(logPrfx + " --- amtCredSumCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- amtCredSumCalc: " + amtCredSumCalc.setScale(2, RoundingMode.HALF_UP));
                }

                if (Objects.equals(amtCredSumCalc_, amtCredSumCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtCredSumCalc(amtCredSumCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtCredSumCalc(amtCredSumCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateFinTxactItms1_AmtNetSumCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtNetSumCalc";
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
                BigDecimal amtNetSumCalc_ = thisNode.getFinTxactItms1_AmtNetSumCalc();
                BigDecimal amtNetSumCalc = null;
                String qry1 = "select sum(e.amtNet)"
                        + " from enty_UsrNodeFinTxactItm e"
                        + " join e.finStmtItm1_Id e2"
                        + " where e2.finStmt1_Id = :finStmt1_Id";
                try{
                    amtNetSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("finStmt1_Id", thisNode)
                            .one();
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                }

                if (amtNetSumCalc == null) {
                    logger.debug(logPrfx + " --- amtNetSumCalc: is null");
                }else{
                    logger.debug(logPrfx + " --- amtNetSumCalc: " + amtNetSumCalc.setScale(2, RoundingMode.HALF_UP));
                }

                if (Objects.equals(amtNetSumCalc_, amtNetSumCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtNetSumCalc(amtNetSumCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtNetSumCalc(amtNetSumCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateFinTxactItms1_IdCntCalc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinTxactItms1_IdCntCalc";
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

                Integer idCntCalc_ = thisNode.getFinTxactItms1_IdCntCalc();
                Integer idCntCalc = null ;
                String qry1 = "select count(e.amtNet)"
                        + " from enty_UsrNodeFinTxactItm e"
                        + " join e.finStmtItm1_Id e2"
                        + " where e2.finStmt1_Id = :finStmt1_Id"
                        ;
                try{
                    idCntCalc = dataManager.loadValue(qry1,Integer.class)
                            .store("main")
                            .parameter("finStmt1_Id", thisNode)
                            .one();
                    if (idCntCalc == null) {
                        idCntCalc = 0;}
                    logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);

                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                    logger.debug(logPrfx + " --- idCntCalc: is null");
                }

                logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);

                if (Objects.equals(idCntCalc_, idCntCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_IdCntCalc(idCntCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_IdCntCalc(idCntCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    @Override
    public List<UsrNodeFinStmt> findFinStmts(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findFinStmts";
        logger.trace(logPrfx + " --> ");


        List<UsrNodeFinStmt> qryRsltNodes = new ArrayList<UsrNodeFinStmt>();
        LogicalCondition cond = LogicalCondition.and();
        cond.add(PropertyCondition.equal("dtype", "enty_UsrNodeFinStmt"));

        //thisNode.finAcct1.Id -> finAcct1.Id
        cond.add(PropertyCondition.equal("finAcct1_Id", thisNode.getFinAcct1_Id()));

        //thisNode.ts1.elTs - 1 day -> ts2.elTs
        cond.add(PropertyCondition.equal("ts2.elTs", thisNode.getTs1().getElTs().minusDays(1)));

        qryRsltNodes = dataManager.load(UsrNodeFinStmt.class).condition(cond).list();

        logger.trace(logPrfx + " <-- ");
        return qryRsltNodes;
    }

    @Override
    public UsrNodeFinStmt createFinStmt(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createFinStmt";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinStmt mergedFinStmt = null;

        Notifications notifications;
        if(view instanceof UsrNodeBase0BaseMain
                || view instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) view).getNotifications();

            DataContext dataContext = ViewControllerUtils.getViewData(view).getDataContext();

            UsrNodeFinStmt FinStmt1_Id = dataManager.create(UsrNodeFinStmt.class);

            //thisNode.finAcct1.id -> finAcct1.id
            FinStmt1_Id.setFinAcct1_Id(thisNode.getFinAcct1_Id());

            //thisNode.ts1.elTs - 1 day -> ts2.elTs
            HasTmst ts2 = dataManager.create(HasTmst.class);
            ts2.setElTs(thisNode.getTs1().getElTs().minusDays(1));
            FinStmt1_Id.setTs2(ts2);

            serviceFinStmt1.updateId2Calc(view, FinStmt1_Id, UpdateOption.LOCAL);
            serviceFinStmt1.updateId2(view, FinStmt1_Id, UpdateOption.LOCAL);
            serviceFinStmt1.updateId2Cmp(view, FinStmt1_Id, UpdateOption.LOCAL);
            serviceFinStmt1.updateId2Dup(view, FinStmt1_Id, UpdateOption.LOCAL);

            UsrNodeFinStmt savedFinStmt = dataManager.save(FinStmt1_Id);

            mergedFinStmt = dataContext.merge(savedFinStmt);
            logger.debug(logPrfx + " --- created FinStmt id: " + mergedFinStmt.getId());
            notifications.create("Created FinStmt with id2:" + mergedFinStmt.getId2()).show();
        }

        logger.trace(logPrfx + " <-- ");
        return mergedFinStmt;
    }

    
}