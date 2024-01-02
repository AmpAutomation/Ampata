package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcct;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtItm;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtItmGrpg;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseComn;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.View;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component("bean_UsrNodeFinStmtItm.Service")
public class UsrNodeFinStmtItm0Service extends UsrNodeBase0Service {

    public UsrNodeFinStmtItm0Service() {
        this.typeOfNode = UsrNodeFinStmtItm.class;
    }


    /**
     * <h1>Update all calculated fields</h1>
     * <p></p>
     * <h2>Updated Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>amtNet</i></li>

     *          <li><i>desc1</i></li>
     *          <li><i>inst1</i></li>
     *          <li><i>name1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *          <li><i>sortKey</i></li>
     *          <li><i></i></li>
     *          <li><i>finTxactItms1_IdCnt</i></li>
     *          <li><i>finTxactItms1_AmtDebtSum</i></li>
     *          <li><i>finTxactItms1_AmtCredSum</i></li>
     *          <li><i>finTxactItms1_AmtNetSum</i></li>
     *          <li><i>finTxactItms1_AmtEq</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateCalcVals(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = updateAmtNet(view, thisNode, updOption) || isChanged;

        isChanged = updateDesc1(view, thisNode, updOption) || isChanged;
        isChanged = updateInst1(view, thisNode, updOption) || isChanged;
        isChanged = updateName1(view, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(view, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(view, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(view, thisNode, updOption) || isChanged;
        isChanged = updateSortKey(view, thisNode, updOption) || isChanged;

        isChanged = updateFinTxactItms1_AmtDebtSumCalc(view, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtCredSumCalc(view, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtNetSumCalc(view, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_IdCntCalc(view, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtEqCalc(view, thisNode, updOption)  || isChanged;

        isChanged = updateFinTxactItms1_AmtDebtSumDiff(view, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtCredSumDiff(view, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtNetSumDiff(view, thisNode, updOption)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>inst1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *      <p>If <i>parent1_Id.isEmpty()</i> then</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>finAcct1_Id.id2</i> required</li>
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 36px;">
     *          <li><i>finStmt1_Id.finAcct1_Id.id2</i> required</li>
     *      </ul>
     *      <ul>
     *      <li><i>ts1.elTs</i> (tsEnd) required</li>
     *      <li><i>int1</i> required</li>
     *      <li><i>amtNet</i> required</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = true</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>ts1.elTs</i> (tsEnd) = "2018-12-31"</li>
     *      <li><i>int1</i> = "0"</li>
     *      <li><i>amtNet</i> = "125.00"</li>
     *     <li><i>desc1</i> = "A/CurY/RBC/Chk/D2018-10-31/X00/A+125.00"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = false</li>
     *     <li><i>finStmt1_Id.finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *     <li><i>ts1.elTs</i> (tsEnd) = "2018-12-31"</li>
     *      <li><i>int1</i> = "0"</li>
     *      <li><i>amtNet</i> = "125.00"</li>
     *     <li><i>desc1</i> = "A/CurY/RBC/Chk/D2018-10-31/X00/A+125.00"</li>
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

                //parent1_Id.id2 optional
                Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if(o_parent1_Id.isEmpty()) {
                    logger.trace(logPrfx + " --- o_parent1_Id: is empty");
                }else{
                    logger.trace(logPrfx + " --- o_parent1_Id: " + o_parent1_Id.get().getId2());
                }

                if(o_parent1_Id.isEmpty()) {
                    //finAcct1_Id.id2 required
                    Optional<String> o_finAcct1_Id2 = Optional.ofNullable(thisNode.getFinAcct1_Id()).map(UsrNodeFinAcct::getId2);
                    if (o_finAcct1_Id2.isEmpty()) {
                        logger.debug(logPrfx + " --- o_finAcct1_Id2: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                        sb.append(o_finAcct1_Id2.get());
                    }
                }else{
                    //o_parent1_Id.isPresent()

                    //finAcct1_Id.id2 required
                    Optional<String> o_finAcct1_Id2 = Optional.ofNullable(o_parent1_Id.get().getFinAcct1_Id()).map(UsrNodeFinAcct::getId2);
                    if (o_finAcct1_Id2.isEmpty()) {
                        logger.debug(logPrfx + " --- o_finAcct1_Id2: is empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                        sb.append(o_finAcct1_Id2.get());
                    }

                }

                //ts1.elTs required
                Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                if (o_ts1_ElTs.isEmpty()) {
                    logger.debug(logPrfx + " --- o_ts1_ElTs: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                    sb.append(SEP1);
                    sb.append("D");
                    sb.append(o_ts1_ElTs.get().format(FRMT_DT));
                }

                //int1 required
                Optional<Integer> o_int1 = Optional.ofNullable(thisNode.getInt1());
                if (o_int1.isEmpty()) {
                    logger.debug(logPrfx + " --- o_int1: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_int1: " + o_int1.get());
                    sb.append(SEP1);
                    sb.append("X");
                    //the length of the string will be 2 characters with leading zeros
                    sb.append(String.format("%02d", o_int1.get()));
                }

                //amtNet required
                Optional<BigDecimal> o_amtNet = Optional.ofNullable(thisNode.getAmtNet());
                if (o_amtNet.isEmpty()) {
                    logger.debug(logPrfx + " --- o_amtNet: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_amtNet: " + o_amtNet.get().setScale(2, RoundingMode.HALF_UP));
                    sb.append(SEP1);
                    sb.append("A");
                    //Add the pos or neg sign
                    sb.append(o_amtNet.get().signum() >= 0 ? "+" : "-");
                    sb.append(o_amtNet.get().setScale(2, RoundingMode.HALF_UP));
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
     *      <p>If <i>parent1_Id.isEmpty()</i> then</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>finAcct1_Id.id2</i> required</li>
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 36px;">
     *          <li><i>finStmt1_Id.finAcct1_Id.id2</i> required</li>
     *      </ul>
     *      <ul>
     *      <li><i>debtAmt</i> required</li>
     *      <li><i>credAmt</i> required</li>
     *      <li><i>txt1</i> optional</li>
     *      <li><i>txt2</i> optional</li>
     *      <li><i>genTags1_Id.map.id2</i> optional</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = true</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>debtAmt</i> = 125.00</li>
     *      <li><i>credAmt</i> = 0</li>
     *      <li><i>txt1</i> = "INTERAC"</li>
     *      <li><i>txt2</i> = "COSTCO"</li>
     *     <li><i>desc1</i> = "Debt 125.00 on Acct[A/CurY/RBC/Chk] for INTERAC COSTCO"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = false</li>
     *      <li><i>finStmt1_Id.finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>debtAmt</i> = 125.00</li>
     *      <li><i>credAmt</i> = 0</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>txt1</i> = "INTERAC"</li>
     *      <li><i>txt2</i> = "COSTCO"</li>
     *      <li><i>desc1</i> = "Debt 125.00 on Acct[A/CurY/RBC/Chk] for INTERAC COSTCO"</li>
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

                //amtDebt optional
                Optional<BigDecimal> o_amtDebt = Optional.ofNullable(thisNode.getAmtDebt());
                if (o_amtDebt.isEmpty()) {
                    logger.debug(logPrfx + " --- o_amtDebt: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_amtDebt: " + o_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
                    sb.append(SEP0);
                    sb.append("debt ");
                    sb.append(o_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
                }

                //amtCred optional
                Optional<BigDecimal> o_amtCred = Optional.ofNullable(thisNode.getAmtCred());
                if (o_amtCred.isEmpty()) {
                    logger.debug(logPrfx + " --- o_amtCred: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_amtCred: " + o_amtCred.get().setScale(2, RoundingMode.HALF_UP));
                    sb.append(SEP0);
                    sb.append("cred ");
                    sb.append(o_amtCred.get().setScale(2, RoundingMode.HALF_UP));
                }

                //parent1_Id.id2 optional
                Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if(o_parent1_Id.isEmpty()) {
                    logger.trace(logPrfx + " --- o_parent1_Id: is empty");
                }else{
                    logger.trace(logPrfx + " --- o_parent1_Id: " + o_parent1_Id.get().getId2());
                }

                if(o_parent1_Id.isEmpty()) {

                    //finAcct1_Id.id2 optional
                    Optional<String> o_finAcct1_Id2 = Optional.ofNullable(thisNode.getFinAcct1_Id()).map(UsrNodeBase::getId2);
                    if (o_finAcct1_Id2.isEmpty()) {
                        logger.debug(logPrfx + " --- o_finAcct1_Id2: is empty");
                    }else{
                        logger.debug(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                        sb.append("acct [");
                        sb.append(o_finAcct1_Id2.get());
                        sb.append("]");
                    }
                }else{
                    //o_parent1_Id.isPresent()

                    //finAcct1_Id.id2 optional
                    Optional<String> o_finAcct1_Id2 = Optional.ofNullable(o_parent1_Id.get().getFinAcct1_Id()).map(UsrNodeBase::getId2);
                    if (o_finAcct1_Id2.isEmpty()) {
                        logger.debug(logPrfx + " --- o_finAcct1_Id2: is empty");
                    }else{
                        logger.debug(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                        sb.append("acct [");
                        sb.append(o_finAcct1_Id2.get());
                        sb.append("]");
                    }

                }

                //finStmtItm1_Id optional
                List<String> txts_AsList = Arrays.asList(
                        Optional.ofNullable(thisNode.getTxt1()).orElse("")
                        , Optional.ofNullable(thisNode.getTxt2()).orElse("")
                );
                String txts_AsStr = Optional.of(txts_AsList
                        .stream()
                        .filter(StringUtils::isNotBlank)
                        .collect(Collectors.joining(" "))
                ).orElse("");
                if (txts_AsStr.equals("")) {
                    logger.debug(logPrfx + " --- txts_AsStr is blank");
                }else{
                    logger.debug(logPrfx + " --- txts_AsStr: " + txts_AsStr);
                    sb.append(SEP0);
                    sb.append("for ");
                    sb.append(txts_AsStr);
                }

                //genTags1_Id.map.id2 optional
                Optional<List<UsrItemGenTag>> o_genTags1_Id = Optional.ofNullable(thisNode.getGenTags1_Id());

                if (o_genTags1_Id.isEmpty()) {
                    logger.debug(logPrfx + " --- o_genTags1_Id: is empty");
                }else{

                    List<UsrItemGenTag> genTags1_Id_AsList =  o_genTags1_Id.orElse(new ArrayList<UsrItemGenTag>());

                    String genTags1_Id_AsStr = Optional.of(
                            genTags1_Id_AsList
                                    .stream()
                                    .map(UsrItemGenTag::getId2)
                                    .collect(Collectors.joining(","))
                    ).orElse("");
                    if (genTags1_Id_AsStr.equals("")) {
                        logger.debug(logPrfx + " --- genTags1_Id_AsStr is blank");
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


    @Override
    public Boolean updateAmtNet(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
                        + " where e.finStmtItm1_Id = :finStmtItm1_Id";
                try{
                    amtDebtSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("finStmtItm1_Id", thisNode)
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
                        + " where e.finStmtItm1_Id = :finStmtItm1_Id";
                try{
                    amtCredSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("finStmtItm1_Id", thisNode)
                            .one();
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                }

                if (amtCredSumCalc == null) {
                    logger.debug(logPrfx + " --- amtCredSumCalc: null");
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
                BigDecimal amtNetSumCalc = BigDecimal.ZERO;

                //finAcct1_Id_Type1_Id required
                Optional<UsrNodeBaseType> o_finAcct1_Id_Type1_Id = Optional.ofNullable(thisNode.getFinStmt1_Id())
                        .map(n->n.getFinAcct1_Id())
                        .map(n->n.getType1_Id())
                        ;

                if (o_finAcct1_Id_Type1_Id.isEmpty()){
                    logger.debug(logPrfx + " --- o_finAcct1_Id_Type1_Id: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;

                }else{

                    Boolean balIncOnDebt = o_finAcct1_Id_Type1_Id.get().getBalIncOnDebt();
                    Boolean balIncOnCred = o_finAcct1_Id_Type1_Id.get().getBalIncOnCred();

                    //finTxactItms1_AmtDebtSumCalc optional
                    Optional<BigDecimal> o_finTxactItms1_AmtDebtSumCalc = Optional.ofNullable(thisNode.getFinTxactItms1_AmtDebtSumCalc());
                    if (o_finTxactItms1_AmtDebtSumCalc.isEmpty()) {
                        logger.debug(logPrfx + " --- o_finTxactItms1_AmtDebtSumCalc: is empty");
                    }else{
                        logger.debug(logPrfx + " --- o_finTxactItms1_AmtDebtSumCalc: " + o_finTxactItms1_AmtDebtSumCalc.get().setScale(2, RoundingMode.HALF_UP));

                        if (balIncOnDebt == null || !balIncOnDebt) {
                            amtNetSumCalc = amtNetSumCalc.subtract(o_finTxactItms1_AmtDebtSumCalc.get());
                        } else {
                            amtNetSumCalc = amtNetSumCalc.add(o_finTxactItms1_AmtDebtSumCalc.get());
                        }
                    }

                    //finTxactItms1_AmtCredSumCalc optional
                    Optional<BigDecimal> o_finTxactItms1_AmtCredSumCalc = Optional.ofNullable(thisNode.getFinTxactItms1_AmtCredSumCalc());
                    if (o_finTxactItms1_AmtCredSumCalc.isEmpty()) {
                        logger.debug(logPrfx + " --- o_finTxactItms1_AmtCredSumCalc: is empty");
                    }else{
                        logger.debug(logPrfx + " --- o_finTxactItms1_AmtCredSumCalc: " + o_finTxactItms1_AmtCredSumCalc.get().setScale(2, RoundingMode.HALF_UP));

                        if (balIncOnCred == null || !balIncOnCred) {
                            amtNetSumCalc = amtNetSumCalc.subtract(o_finTxactItms1_AmtCredSumCalc.get());
                        } else {
                            amtNetSumCalc = amtNetSumCalc.add(o_finTxactItms1_AmtCredSumCalc.get());
                        }
                    }

                }

                logger.debug(logPrfx + " --- amtNetSumCalc: " + amtNetSumCalc.setScale(2, RoundingMode.HALF_UP));

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
                        + " where e.finStmtItm1_Id = :finStmtItm1_Id"
                        ;
                try{
                    idCntCalc = dataManager.loadValue(qry1,Integer.class)
                            .store("main")
                            .parameter("finStmtItm1_Id", thisNode)
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

    //Ex A/CurY/RBC/Chk/D2018-10-31/X01/A+125.00
    //   01234567890012345678900123456789001234567890
    @Transient
    @JmixProperty
    final String id2RegExPat =
             "(.*)"                             //finAcct_Id2
            +"(/D[0-9]{4}-[0-9]{2}-[0-9]{2})"   //ts1.elTs1
            +"(/X[0-9]{2})"                     //sortIdx
            +"(/A[\\+\\-][0-9]+\\.[0-9]{2})"    //amtNet
            ;
    @Override
    public Boolean updateIdPartsFrId2(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateTs1FrId2(view, thisNode, updOption) || isChanged;
        isChanged = updateSortIdxFrId2(view, thisNode, updOption)  || isChanged;

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
        //Ex A/CurY/RBC/Chk/D2018-10-31/X01/A+125.00
        //   01234567890012345678900123456789001234567890

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
                        ts1_ElTs = LocalDateTime.parse(id2_part, FRMT_DT);

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
    public Boolean updateSortIdxFrId2(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateSortIdxFrId2";
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

                // require id2
                if(thisNode.getId2() == null) {
                    logger.trace(logPrfx + " --- node.getId2(): is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                Integer sortIdx_ = thisNode.getSortIdx();
                Integer sortIdx = null;

                //todo verify id2 pattern
                if (thisNode.getId2().length() >= 28){

                    String id2_part = thisNode.getId2().substring(26,28);
                    try{
                        sortIdx = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                    if (Objects.equals(sortIdx_, sortIdx)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- called thisNode.setSortIdx(sortIdx)");
                        isChanged = true;
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Integer getSortIdxMax(@NotNull View view, Object grpgKey) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;

        Notifications notifications;
        if(view instanceof UsrNodeBase0BaseMain
                || view instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) view).getNotifications();


            // Ensure grpgKey is instanceof UsrNodeFinStmtItmGrpg
            if (!(grpgKey instanceof UsrNodeFinStmtItmGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeFinStmtItmGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer sortIdxCntIsNull = null;
            String sortIdxCntIsNullQry = "select count(e)"
                    + " from enty_UsrNodeFinTxactItm e"
                    + " where e.parent1_Id = :parent1_Id"
                    + " and e.ts1.elTs = :ts1_ElTs"
                    + " and e.sortIdx: is null"
                    ;
            try {
                sortIdxCntIsNull = dataManager.loadValue(sortIdxCntIsNullQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
                        .parameter("ts1_ElTs", l_grpgKey.ts1())
                        .one();
            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- sortIdxCntIsNullQry error: " + e.getMessage());
                notifications.create("sortIdxCntIsNullQry error: " + e.getMessage()).show();
                logger.trace(logPrfx + " <-- ");
                return null;
            }
            logger.debug(logPrfx + " --- sortIdxCntIsNullQry result: " + sortIdxCntIsNull + "");

            // If we found counted some null values, return null
            if (sortIdxCntIsNull != 0){
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            String sortIdxMaxQry = "select max(e.sortIdx)"
                    + " from enty_"+ typeOfNode.getSimpleName() +" e"
                    + " where e.parent1_Id = :parent1_Id"
                    + " and e.ts1.elTs = :ts1_ElTs"
                    ;
            try {
                sortIdxMax = dataManager.loadValue(sortIdxMaxQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
                        .parameter("ts1_ElTs", l_grpgKey.ts1())
                        .one();
                // max returns null if no rows or if all rows have a null value
            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- sortIdxMaxQry error: " + e.getMessage());
            }
            logger.debug(logPrfx + " --- sortIdxMaxQry result: " + sortIdxMax + "");
        }

        logger.trace(logPrfx + " <-- ");
        return sortIdxMax;
    }


}