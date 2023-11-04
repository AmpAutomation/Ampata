package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseComn;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.UiControllerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component("bean_UsrNodeFinTxactItm.Service")
public class UsrNodeFinTxactItm0Service extends UsrNodeBase0Service {

    public UsrNodeFinTxactItm0Service() {
        this.typeOfNode = UsrNodeFinTxactItm.class;
    }

    @Autowired
    protected UsrNodeFinTxact0Service serviceParent1;

    @Autowired
    protected UsrNodeFinStmtItm0Service serviceFinStmtItm1;


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
    public Boolean updateCalcVals(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItm1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateParent1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateAmtNet(screen, thisNode, updOption) || isChanged;
        isChanged = updateAmtCalcFinTxactItm1_EI1_Rate(screen, thisNode, updOption) || isChanged;
        isChanged = updateAmtCalc(screen, thisNode, updOption) || isChanged;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;
        isChanged = updateSortKey(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>inst1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *      <p>If <i>parent1_Id.isPresent()</i> then</p>
     *      <ul style="margin-left: 36px;">
     *          <p>If <i>parent1_Id.isPresent()</i> then</p>
     *          <ul style="margin-left: 36px;">
     *              <li><i>parent1_Id.parent1_Id.ts1.elTs</i> (tsEnd) required</li>
     *              <li><i>parent1_Id.parent1_Id.sortIdx</i> (intX) optional default 0</li>
     *          </ul>
     *          <p><i>else</p>
     *          <ul style="margin-left: 24px;">
     *              <li><i>parent1_Id.ts1.elTs</i> (tsEnd) required</li>
     *              <li><i>parent1_Id.int1</i> (intX) optional default 0</li>
     *          </ul>
     *          <ul>
     *              <li><i>parent1_Id.sortIdx</i> (intY) optional default 0</li>
     *          </ul>
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>ts1.elTs</i> (tsEnd) required</li>
     *          <li><i>int1</i> (intX) optional default 0</li>
     *          <li><i>int2</i> (intY) optional default 0</li>
     *      </ul>
     *      <ul>
     *      <li><i>sortIdx</i> (intZ) optional default 0</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = true</li>
     *      <li><i>parent1_Id.parent1_Id.isPresent()</i> = true</li>
     *      <li><i>parent1_Id.parent1_Id.ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>parent1_Id.parent1_Id.sortIdx</i> (idX) = "0"</li>
     *      <li><i>parent1_Id.sortIdx</i> (idY) = "0"</li>
     *      <li><i>sortKey</i> (idZ) = "0"</li>
     *      <li><i>desc1</i> = "D2023-01-01/X00/Y00/Z00"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = true</li>
     *      <li><i>parent1_Id.parent1_Id.isPresent()</i> = false</li>
     *      <li><i>parent1_Id.ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>parent1_Id.sortIdx</i> (idX) = "0"</li>
     *      <li><i>parent1_Id.int1</i> (idY) = "0"</li>
     *      <li><i>sortKey</i> (idZ) = "0"</li>
     *      <li><i>desc1</i> = "D2023-01-01/X00/Y00/Z00"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = false</li>
     *      <li><i>parent1_Id.parent1_Id.isPresent()</i> = false</li>
     *      <li><i>ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>int1</i> (idX) = "0"</li>
     *      <li><i>int2</i> (idY) = "0"</li>
     *      <li><i>sortKey</i> (idZ) = "0"</li>
     *      <li><i>desc1</i> = "D2023-01-01/X00/Y00/Z00"</li>
     * </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateInst1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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

                //parent1_Id optional
                Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if(o_parent1_Id.isEmpty()) {
                    logger.trace(logPrfx + " --- o_parent1_Id: is empty");
                }else{
                    logger.trace(logPrfx + " --- o_parent1_Id: " + o_parent1_Id.get().getId2());
                }

                logger.debug(logPrfx + " --- o_parent1_Id.isPresent(): " + o_parent1_Id.isPresent());
                if(o_parent1_Id.isPresent()) {

                    //parent1_Idx2 optional
                    Optional<UsrNodeBase> o_parent1_Idx2 = Optional.ofNullable(o_parent1_Id.get().getParent1_Id());
                    if(o_parent1_Idx2.isEmpty()) {
                        logger.trace(logPrfx + " --- o_parent1_Idx2: null");
                    }else{
                        logger.trace(logPrfx + " --- o_parent1_Idx2 :" + o_parent1_Idx2.get().getId2());
                    }

                    logger.debug(logPrfx + " --- o_parent1_Idx2.isEmpty(): " + o_parent1_Idx2.isEmpty());
                    if(o_parent1_Idx2.isEmpty()) {

                        //ts1.elTs required
                        Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(o_parent1_Idx2.get().getTs1()).map(HasTmst::getElTs);
                        if (o_ts1_ElTs.isEmpty()) {
                            logger.debug(logPrfx + " --- o_ts1_ElTs: is empty");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }else{
                            logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                            sb.append("D");
                            sb.append(o_ts1_ElTs.get().format(FRMT_DT));
                        }

                        //sortIdx optional default 0
                        Optional<Integer> o_sortIdx = Optional.ofNullable(o_parent1_Idx2.get().getSortIdx());
                        if (o_sortIdx.isEmpty()) {
                            logger.debug(logPrfx + " --- o_sortIdx: is empty");
                        }else{
                            logger.debug(logPrfx + " --- o_sortIdx: " + o_sortIdx);
                        }
                        sb.append(SEP1);
                        sb.append("X");
                        //the length of the string will be 2 characters with leading zeros
                        sb.append(String.format("%02d",o_sortIdx.orElse(0)));

                    }else {
                        // o_parent1_Idx2.isPresent() is false

                        //ts1.elTs required
                        Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(o_parent1_Id.get().getTs1()).map(HasTmst::getElTs);
                        if (o_ts1_ElTs.isEmpty()) {
                            logger.debug(logPrfx + " --- o_ts1_ElTs: is empty");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }else{
                            logger.debug(logPrfx + " --- o_ts1_ElTs: " + FRMT_DT.format(o_ts1_ElTs.get()));
                            sb.append("D");
                            sb.append(o_ts1_ElTs.get().format(FRMT_DT));
                        }

                        //int1 optional default 0
                        Optional<Integer> o_int1 = Optional.ofNullable(o_parent1_Id.get().getInt1());
                        if (o_int1.isEmpty()) {
                            logger.debug(logPrfx + " --- o_int1: is empty");
                        }else{
                            logger.debug(logPrfx + " --- o_int1: " + o_int1);
                        }
                        sb.append(SEP1);
                        sb.append("X");
                        //the length of the string will be 2 characters with leading zeros
                        sb.append(String.format("%02d",o_int1.orElse(0)));
                    }

                    //sortIdx optional default 0
                    Optional<Integer> o_sortIdx = Optional.ofNullable(o_parent1_Id.get().getSortIdx());
                    if (o_sortIdx.isEmpty()) {
                        logger.debug(logPrfx + " --- o_sortIdx: null");
                    }else{
                        logger.debug(logPrfx + " --- o_sortIdx: " + o_sortIdx);
                    }
                    sb.append(SEP1);
                    sb.append("Y");
                    //the length of the string will be 2 characters with leading zeros
                    sb.append(String.format("%02d",o_sortIdx.orElse(0)));

                }else{
                    //o_parent1_Id.isPresent() is false

                    //ts1.elTs required
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: null");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                        sb.append(SEP1);
                        sb.append("D");
                        sb.append(o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    //int1 optional default 0
                    Optional<Integer> o_int1 = Optional.ofNullable(thisNode.getInt1());
                    if (o_int1.isEmpty()) {
                        logger.debug(logPrfx + " --- o_int1: null");
                    }else{
                        logger.debug(logPrfx + " --- o_int1: " + o_int1);
                    }
                    sb.append(SEP1);
                    sb.append("X");
                    //the length of the string will be 2 characters with leading zeros
                    sb.append(String.format("%02d",o_int1.orElse(0)));

                    //int2 optional default 0
                    Optional<Integer> o_int2 = Optional.ofNullable(thisNode.getInt2());
                    if (o_int2.isEmpty()) {
                        logger.debug(logPrfx + " --- o_int2: null");
                    }else{
                        logger.debug(logPrfx + " --- o_int2: " + o_int2);
                    }
                    sb.append(SEP1);
                    sb.append("Y");
                    //the length of the string will be 2 characters with leading zeros
                    sb.append(String.format("%02d",o_int2.orElse(0)));

                }

                //sortIdx optional default 0
                Optional<Integer> o_sortIdx = Optional.ofNullable(thisNode.getSortIdx());
                if (o_sortIdx.isEmpty()) {
                    logger.debug(logPrfx + " --- o_sortIdx: null");
                }else{
                    logger.debug(logPrfx + " --- o_sortIdx: " + o_sortIdx);
                }
                sb.append(SEP1);
                sb.append("Z");
                //the length of the string will be 2 characters with leading zeros
                sb.append(String.format("%02d",o_sortIdx.orElse(0)));

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
     * <h1>Update the <i>name1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>inst1</i> required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>inst1</i> = "D2016-12-28/X00/Y00/Z00"</li>
     *          <li><i>name1</i> = "D2016-12-28/X00/Y00/Z00"</li>
     *      </ul>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
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
                    logger.debug(logPrfx + " --- o_inst1: empty");
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
                    logger.debug(logPrfx + " --- thisNode.setName1(name1)");
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
     *      <li><i>debtAmt</i> required</li>
     *      <li><i>credAmt</i> required</li>
     *      <p>If <i>finStmtItm1_Id.isEmpty()</i> then</p>
     *      <ul style="margin-left: 24px;">
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 36px;">
     *          <li><i>finStmtItm1_Id.txt1</i> optional</li>
     *          <li><i>finStmtItm1_Id.txt2</i> optional</li>
     *      </ul>
     *      <ul>
     *      <li><i>finAcct1_Id.id2</i> optional</li>
     *      <li><i>finDept1_Id.id2</i> optional</li>
     *      <li><i>genTags1_Id.map.id2</i> optional</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>debtAmt</i> = 125.00</li>
     *      <li><i>credAmt</i> = 0</li>
     *      <li><i>finStmtItm1_Id.isEmpty()</i> = false</li>
     *      <li><i>finStmtItm1_Id.txt1</i> = "Credit Card Payment"</li>
     *      <li><i>finStmtItm1_Id.txt2</i> = ""</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>genTags1_Id.map.id2</i> = ""</li>
     *     <li><i>desc1</i> = "Debt 125.00 for Credit Card Payment on Acct[A/CurY/RBC/Chk]"</li>
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


                //amtDebt optional
                Optional<BigDecimal> o_amtDebt = Optional.ofNullable(thisNode.getAmtDebt());
                if (o_amtDebt.isEmpty()) {
                    logger.debug(logPrfx + " --- o_amtDebt: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_amtDebt: " + o_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
                    sb.append(SEP0);
                    sb.append(" Debt ");
                    sb.append(o_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
                }

                //amtCred optional
                Optional<BigDecimal> o_amtCred = Optional.ofNullable(thisNode.getAmtCred());
                if (o_amtCred.isEmpty()) {
                    logger.debug(logPrfx + " --- o_amtCred: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_amtCred: " + o_amtCred.get().setScale(2, RoundingMode.HALF_UP));
                    sb.append(SEP0);
                    sb.append(" Cred ");
                    sb.append(o_amtCred.get().setScale(2, RoundingMode.HALF_UP));
                }

                //sysNodeFinCurcy1_Id2 optional
                Optional<String> o_sysNodeFinCurcy1_Id2 = Optional.ofNullable(thisNode.getSysNodeFinCurcy1_Id()).map(n->n.getId2());
                if (o_sysNodeFinCurcy1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_sysNodeFinCurcy1_Id2: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_sysNodeFinCurcy1_Id2: " + o_sysNodeFinCurcy1_Id2.get());
                    sb.append(SEP0);
                    sb.append(o_sysNodeFinCurcy1_Id2.get());
                }

                //finAcct1_Id2 optional
                Optional<String> o_finAcct1_Id2 = Optional.ofNullable(thisNode.getFinAcct1_Id()).map(n->n.getId2());
                if (o_finAcct1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finAcct1_Id2: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_finAcct1_Id2: " + o_finAcct1_Id2.get());
                    sb.append(SEP0);
                    sb.append("to acct [");
                    sb.append(o_finAcct1_Id2.get());
                    sb.append("]");
                }

                //finDept1_Id2 optional
                Optional<String> o_finDept1_Id2 = Optional.ofNullable(thisNode.getFinDept1_Id()).map(n->n.getId2());
                if (o_finDept1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finDept1_Id2: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_finDept1_Id2: " + o_finDept1_Id2.get());
                    sb.append(SEP0);
                    sb.append("to dept [");
                    sb.append(o_finDept1_Id2.get());
                    sb.append("]");
                }

                //finStmtItm1_Id optional
                Optional<UsrNodeFinStmtItm> o_finStmtItm1_Id = Optional.ofNullable(thisNode.getFinStmtItm1_Id());

                if (o_finStmtItm1_Id.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finStmtItm1_Id: is empty");
                }else {
                    String thisStmtItm1_Txt1 = Objects.toString(o_finStmtItm1_Id.get().getTxt1(), "");
                    String thisStmtItm1_Txt2 = Objects.toString(o_finStmtItm1_Id.get().getTxt2(), "");
                    List<String> l_finStmtItm1_Id_Txts_AsList = Arrays.asList(
                            thisStmtItm1_Txt1
                            , thisStmtItm1_Txt2
                    );
                    String finStmtItm1_Id_Txts_AsStr = Optional.of(l_finStmtItm1_Id_Txts_AsList
                            .stream()
                            .filter(StringUtils::isNotBlank)
                            .collect(Collectors.joining(" "))
                    ).orElse("");
                    if (finStmtItm1_Id_Txts_AsStr.equals("")) {
                        logger.debug(logPrfx + " --- finStmtItm1_Id_Txts_AsStr is blank");
                    }else{
                        logger.debug(logPrfx + " --- finStmtItm1_Id_Txts_AsStr: " + finStmtItm1_Id_Txts_AsStr);
                        sb.append(SEP0);
                        sb.append("for ");
                        sb.append(finStmtItm1_Id_Txts_AsStr);
                    }

                }

                //genTags1_Id.map.id2 optional
                List<UsrItemGenTag> genTags1_Id = thisNode.getGenTags1_Id();
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
    public Boolean updateTs1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItm1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateParent1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateInt1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItm1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateParent1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateInt2Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItm1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateParent1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateSortIdxDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateSortIdxDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItm1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateParent1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateAmtNet(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtNet";
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
                            notifications.create().withCaption(msg).show();
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
    public Boolean updateAmtCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtCalc";
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
                BigDecimal amtCalc_ = thisNode.getAmtCalc();
                BigDecimal amtCalc = BigDecimal.valueOf(0);
                boolean amtCalcGood = false;

                UsrItemGenFmla amtCalcGenFmla1 = thisNode.getAmtCalcGenFmla1_Id();
                if (amtCalcGenFmla1 == null) {
                    logger.debug(logPrfx + " --- amtCalcGenFmla1: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- amtCalcGenFmla1: " + amtCalcGenFmla1.getId());
                }

                UsrNodeFinTxactItm prev1FinTxactItm;
                UsrNodeFinTxactItm prev2FinTxactItm;

                // Switch statement over above string
                switch (amtCalcGenFmla1.getId2()) {

                    // Ref1 Mult Exch_Rate
                    case "Ref1 Mult Exch_Rate" ->{

                        UsrNodeFinTxactItm amtFinTxactItm1 = thisNode.getAmtCalcFinTxactItm1_Id();
                        if (amtFinTxactItm1 == null) {
                            logger.debug(logPrfx + " --- amtFinTxactItm1: is null");
                            //notifications.create().withCaption("Unable to get amtFinTxactItm1. Please ensure amtFinTxactItm1 has a finTxactItm selected.").show();
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        } else {
                            logger.debug(logPrfx + " --- amtFinTxactItm1: " + amtFinTxactItm1.getId());
                        }

                        BigDecimal val = amtFinTxactItm1.getAmtNet();
                        if (val == null) {
                            logger.debug(logPrfx + " --- amtFinTxactItm1.getAmtNet(): is null");
                            //notifications.create().withCaption("Unable to get amtFinTxactItm1.amtNet(). Please ensure  amtFinTxactItm1.amtNet() has a value.").show();
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        } else {
                            logger.debug(logPrfx + " --- amtFinTxactItm1.getAmtNet(): " + val);
                        }

                        BigDecimal rate = thisNode.getAmtCalcFinTxactItm1_EI1_Rate();
                        if (rate == null){
                            logger.debug(logPrfx + " --- thisNode.getAmtFinTxactItm1_EI1_Rate(): is null");
                            //notifications.create().withCaption("Unable to get thisNode.getAmtFinTxactItm1_EI1_Rate(). Please ensure thisNode.getFinTxactItm1_EI1_Rate() has a value.").show();
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }
                        amtCalc = val.multiply(rate).setScale(2, RoundingMode.HALF_UP).negate();
                        amtCalcGood = true;
                    }

                    case "Prev2.Cr Sub Prev1.Dr" -> {

                        prev1FinTxactItm = getPrevFinTxactItm(thisNode, 1);
                        if (prev1FinTxactItm != null) {
                            BigDecimal prev1Dr = prev1FinTxactItm.getAmtDebt() != null ? prev1FinTxactItm.getAmtDebt() : BigDecimal.valueOf(0);

                            prev2FinTxactItm = getPrevFinTxactItm(thisNode, 2);
                            if (prev2FinTxactItm != null) {
                                BigDecimal prev2Cr = prev2FinTxactItm.getAmtCred() != null ? prev2FinTxactItm.getAmtCred() : BigDecimal.valueOf(0);

                                amtCalc = prev2Cr.subtract(prev1Dr);
                                amtCalcGood = true;
                            }
                        }
                    }

                    case "Prev2.Dr Sub Prev1.Cr" ->{

                        prev1FinTxactItm = getPrevFinTxactItm(thisNode, 1);
                        if (prev1FinTxactItm != null) {
                            BigDecimal prev1Cr = prev1FinTxactItm.getAmtCred() != null ? prev1FinTxactItm.getAmtCred() : BigDecimal.valueOf(0);

                            prev2FinTxactItm = getPrevFinTxactItm(thisNode, 2);
                            if (prev2FinTxactItm != null) {
                                BigDecimal prev2Dr = prev2FinTxactItm.getAmtDebt() != null ? prev2FinTxactItm.getAmtDebt() : BigDecimal.valueOf(0);

                                amtCalc = prev2Dr.subtract(prev1Cr);
                                amtCalcGood = true;
                            }
                        }
                    }


                    default ->{

                        logger.debug(logPrfx + " --- formula not implemented: " + amtCalcGenFmla1.getId2());
                        // notifications.create().withCaption("Formula not implemented: " + amtCalcGenFmla1.getId2()).show();
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }
                }

                logger.debug(logPrfx + " --- amtCalc: " + amtCalc.setScale(2, RoundingMode.HALF_UP));

                if (!amtCalcGood) {
                    logger.debug(logPrfx + " --- amtCalcGood: false");
                } else if (Objects.equals(amtCalc_, amtCalc)) {
                    logger.debug(logPrfx + " --- no change detected");
                } else {
                    thisNode.setAmtCalc(amtCalc);
                    logger.debug(logPrfx + " --- called thisNode.setAmtCalc(amtCalc)");
                    logger.debug(logPrfx + " --- amtCalc: " + amtCalc);
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public UsrNodeFinTxactItm getPrevFinTxactItm(@NotNull UsrNodeBase thisNode, @NotNull Integer offset) {
        String logPrfx = "getPrevFinTxactItm";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm prevFinTxactItm = null;

        if (thisNode.getSortIdx() != null || thisNode.getSortIdx() >= offset) {
            LogicalCondition logCond = LogicalCondition.and();
            logCond.add(PropertyCondition.equal("parent1_Id",thisNode.getParent1_Id()));
            logCond.add(PropertyCondition.equal("sortIdx",thisNode.getSortIdx() - offset));

            try {
                prevFinTxactItm = dataManager.load(UsrNodeFinTxactItm.class)
                        .condition(logCond)
                        .one()
                ;

            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- dataManager.load(...) java.lang.IllegalStateException: " + e.getMessage());
            }

            if (prevFinTxactItm != null) {
                logger.debug(logPrfx + " --- dataManager.load(...) result is Id: " + prevFinTxactItm.getId());
            }
        }
        logger.trace(logPrfx + " <-- ");
        return prevFinTxactItm;
    }

    //Ex D2023-01-01/X00/Y00/Z00
    //   01234567890123456789012
    @Transient
    @JmixProperty
    final String id2RegExPat = ""
            +"(D[0-9]{4}-[0-9]{2}-[0-9]{2})"   //ts1.elTs1
            +"(/X[0-9]{2})"                     //int1
            +"(/Y[0-9]{2})"                     //int2
            +"(/Z[0-9]{2})"                     //sortIdx
            ;

    @Override
    public Boolean updateIdPartsFrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateTs1FrId2(screen, thisNode, updOption) || isChanged;
        isChanged = updateInt1FrId2(screen, thisNode, updOption)  || isChanged;
        isChanged = updateInt2FrId2(screen, thisNode, updOption)  || isChanged;
        isChanged = updateSortIdxFrId2(screen, thisNode, updOption)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateTs1FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //for method substring(begIndex, endIndex)
        //  begIndex zero based, result includes value at this position
        //  endIndex zero based, result excludes value at this position
        //  length = beginIndex - endIndex
        //Ts1 begIndex = 1
        //Ts1 endIndex = 11
        //Ex D2023-01-01/X00/Y00/Z00
        //   01234567890123456789012

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
                    logger.debug(logPrfx + " --- o_id2: is empty");
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
                    id2_part = matcher.group(0);

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
    public Boolean updateTs2FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs2FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //for method substring(begIndex, endIndex)
        //  begIndex zero based, result includes value at this position
        //  endIndex zero based, result excludes value at this position
        //  length = beginIndex - endIndex
        //Ts1 begIndex = 1
        //Ts1 endIndex = 11
        //Ex D2023-01-01/X00/Y00/Z00
        //   01234567890123456789012

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
                    logger.debug(logPrfx + " --- o_id2: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_id2: " + o_id2.get());
                }

                LocalDateTime ts2_el_ts_ = thisNode.getTs2() == null ? null : thisNode.getTs2().getElTs();
                LocalDateTime ts2_el_ts = null;

                Pattern pattern = Pattern.compile(this.id2RegExPat);
                Matcher matcher = pattern.matcher(thisNode.getId2());

                if (matcher.matches()){

                    String id2_part;
                    id2_part = matcher.group(0);

                    try{
                        ts2_el_ts = LocalDateTime.parse(id2_part, FRMT_DT);

                    } catch (DateTimeParseException e){

                        logger.trace(logPrfx + " ---- DateTimeParseException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }


                    logger.debug(logPrfx + " --- ts2_el_ts: " + ts2_el_ts.format(FRMT_TS));

                    if (Objects.equals(ts2_el_ts_, ts2_el_ts)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.getTs2().setElTs(ts2_el_ts);
                        logger.debug(logPrfx + " --- called node.getTs2().setElTs(ts2_el_ts)");
                        isChanged = true;
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateTs3FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs3FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //for method substring(begIndex, endIndex)
        //  begIndex zero based, result includes value at this position
        //  endIndex zero based, result excludes value at this position
        //  length = beginIndex - endIndex
        //Ts1 begIndex = 1
        //Ts1 endIndex = 11
        //Ex D2023-01-01/X00/Y00/Z00
        //   01234567890123456789012

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
                    logger.debug(logPrfx + " --- o_id2: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_id2: " + o_id2.get());
                }

                LocalDateTime ts3_el_ts_ = thisNode.getTs3() == null ? null : thisNode.getTs3().getElTs();
                LocalDateTime ts3_el_ts = null;

                Pattern pattern = Pattern.compile(this.id2RegExPat);
                Matcher matcher = pattern.matcher(thisNode.getId2());

                if (matcher.matches()){

                    String id2_part;
                    id2_part = matcher.group(0);

                    try{
                        ts3_el_ts = LocalDateTime.parse(id2_part, FRMT_DT);

                    } catch (DateTimeParseException e){

                        logger.trace(logPrfx + " ---- DateTimeParseException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    logger.debug(logPrfx + " --- ts3_el_ts: " + ts3_el_ts.format(FRMT_TS));

                    if (Objects.equals(ts3_el_ts_, ts3_el_ts)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.getTs3().setElTs(ts3_el_ts);
                        logger.debug(logPrfx + " --- called node.getTs3().setElTs(ts3_el_ts)");
                        isChanged = true;
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateInt1FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //for method substring(begIndex, endIndex)
        //  begIndex zero based, result includes value at this position
        //  endIndex zero based, result excludes value at this position
        //  length = beginIndex - endIndex 
        //int1 begIndex = 13
        //int1 endIndex = 15
        //Ex D2023-01-01/X00/Y00/Z00
        //   01234567890123456789012

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
                    logger.trace(logPrfx + " --- id2: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                Integer int1_ = thisNode.getInt1();
                Integer int1 = null;

                Pattern pattern = Pattern.compile(this.id2RegExPat);
                Matcher matcher = pattern.matcher(thisNode.getId2());

                if (matcher.matches()){

                    String id2_part;
                    id2_part = matcher.group(1);

                    try{
                        int1 = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    logger.debug(logPrfx + " --- int1: " + int1);

                    if (Objects.equals(int1_, int1)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setInt1(int1);
                        logger.debug(logPrfx + " --- called thisNode.setInt1(int1)");
                        isChanged = true;
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Boolean updateInt2FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt2FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //for method substring(begIndex, endIndex)
        //  begIndex zero based, result includes value at this position
        //  endIndex zero based, result excludes value at this position
        //  length = beginIndex - endIndex
        //int2 begIndex = 17
        //int2 endIndex = 19
        //Ex
        //D2023-01-01/X00/Y00/Z00
        //01234567890123456789012

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
                    logger.trace(logPrfx + " --- id2: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                Integer int2_ = thisNode.getInt2();
                Integer int2 = null;

                Pattern pattern = Pattern.compile(this.id2RegExPat);
                Matcher matcher = pattern.matcher(thisNode.getId2());

                if (matcher.matches()){

                    String id2_part;
                    id2_part = matcher.group(2);

                    try{
                        int2 = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    logger.debug(logPrfx + " --- int2: " + int2);

                    if (Objects.equals(int2_, int2)){
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setInt2(int2);
                        logger.debug(logPrfx + " --- called thisNode.setInt2(int2)");
                        isChanged = true;
                    }
                }
            }
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateSortIdxFrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateSortIdxFrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        //for method substring(begIndex, endIndex)
        //  begIndex zero based, result includes value at this position
        //  endIndex zero based, result excludes value at this position
        //  length = beginIndex - endIndex 
        //sortIdx begIndex = 21
        //sortIdx endIndex = 23
        //Ex D2023-01-01/X00/Y00/Z00
        //   01234567890123456789012

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
                    logger.trace(logPrfx + " --- id2: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                Integer sortIdx_ = thisNode.getSortIdx();
                Integer sortIdx = null;

                Pattern pattern = Pattern.compile(this.id2RegExPat);
                Matcher matcher = pattern.matcher(thisNode.getId2());

                if (matcher.matches()){

                    String id2_part;
                    id2_part = matcher.group(3);

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
    public List<UsrNodeBase> findParentNodes(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findParentNodes";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> qryRsltNodes = new ArrayList<UsrNodeBase>();
        LogicalCondition cond = LogicalCondition.and();
        cond.add(PropertyCondition.equal("dtype", "enty_UsrNodeFinTxact"));

        //thisNode.ts1.elTs required
        //thisNode.ts1.elTs -> parent1.ts1.elTs
        Optional<LocalDateTime> o_ts1 = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
        if(o_ts1.isEmpty()){
            logger.trace(logPrfx + " --- o_ts1: is Empty");
            logger.trace(logPrfx + " <-- ");
            return qryRsltNodes;
        }else{
            cond.add(PropertyCondition.equal("ts1.elTs", o_ts1.get()));

        }

        //thisNode.int1 optional
        //thisNode.int1 <-> parent1.int1
        LogicalCondition condInt1 = LogicalCondition.or();
        Integer parent1_Int1 = Optional.ofNullable(thisNode.getInt1()).orElse(0);
        if(parent1_Int1 == 0){
            // if zero or condition with is null
            //isSet false = is null; isSet true = is not null
            condInt1.add(PropertyCondition.isSet("int1", false));
        }
        condInt1.add(PropertyCondition.equal("int1", parent1_Int1));
        cond.add(condInt1);

        //thisNode.int2 optional
        //thisNode.int2 <-> parent1.sortIdx
        LogicalCondition condSortIdx = LogicalCondition.or();
        Integer parent1_SortIdx = Optional.ofNullable(thisNode.getInt2()).orElse(0);
        if(parent1_SortIdx == 0){
            // if zero or condition with is null
            //isSet false = is null; isSet true = is not null
            condSortIdx.add(PropertyCondition.isSet("sortIdx", false));
        }
        condSortIdx.add(PropertyCondition.equal("sortIdx", parent1_SortIdx));

        cond.add(condSortIdx);

        qryRsltNodes = dataManager.load(UsrNodeBase.class).condition(cond).list();

        logger.trace(logPrfx + " <-- ");
        return qryRsltNodes;
    }

    @Override
    public UsrNodeBase createParentNode(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createParentNode";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact mergedFinTxact = null;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            DataContext dataContext = UiControllerUtils.getScreenData(screen).getDataContext();

            UsrNodeFinTxact parent1_Id = dataManager.create(UsrNodeFinTxact.class);

            //thisNode.ts1.elTs -> parent1.ts1.elTs
            HasTmst ts1 = dataManager.create(HasTmst.class);
            ts1.setElTs(thisNode.getTs1().getElTs());
            parent1_Id.setTs1(ts1);

            //thisNode.int1 -> parent1.int1
            parent1_Id.setInt1(thisNode.getInt1());
            //thisNode.int2 -> parent1.sortKey
            parent1_Id.setSortIdx(thisNode.getInt2());

            serviceParent1.updateInst1(screen, parent1_Id, UpdateOption.LOCAL);
            serviceParent1.updateName1(screen, parent1_Id, UpdateOption.LOCAL);

            serviceParent1.updateId2Calc(screen, parent1_Id, UpdateOption.LOCAL);
            serviceParent1.updateId2(screen, parent1_Id, UpdateOption.LOCAL);
            serviceParent1.updateId2Cmp(screen, parent1_Id, UpdateOption.LOCAL);
            serviceParent1.updateId2Dup(screen, parent1_Id, UpdateOption.LOCAL);

            UsrNodeFinTxact savedFinTxact = dataManager.save(parent1_Id);

            mergedFinTxact = dataContext.merge(savedFinTxact);
            logger.debug(logPrfx + " --- created FinTxact id: " + mergedFinTxact.getId());
            notifications.create().withCaption("Created FinTxact with id2:" + mergedFinTxact.getId2()).show();
        }


        logger.trace(logPrfx + " <-- ");
        return mergedFinTxact;
    }


    @Override
    public List<UsrNodeFinStmtItm> findFinStmtItms(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findFinStmtItms";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinStmtItm> qryRsltNodes = new ArrayList<UsrNodeFinStmtItm>();
        LogicalCondition cond = LogicalCondition.and();
        cond.add(PropertyCondition.equal("dtype", "enty_UsrNodeFinStmtItm"));

        //thisNode.finAcct1.Id required
        //thisNode.finAcct1.Id <-> finStmtItm1.finAcct1.Id
        Optional<UsrNodeFinAcct> o_finAcct1 = Optional.ofNullable(thisNode.getFinAcct1_Id());
        if(o_finAcct1.isEmpty()){
            logger.trace(logPrfx + " --- o_finAcct1: is Empty");
            logger.trace(logPrfx + " <-- ");
            return qryRsltNodes;
        }else{
            cond.add(PropertyCondition.equal("finAcct1_Id", o_finAcct1.get()));
        }

        //thisNode.ts1.elTs required
        //thisNode.ts1.elTs <-> finStmtItm1.ts1.elTs
        Optional<LocalDateTime> o_ts1 = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
        if(o_ts1.isEmpty()){
            logger.trace(logPrfx + " --- o_ts1: is Empty");
            logger.trace(logPrfx + " <-- ");
            return qryRsltNodes;
        }else{
            cond.add(PropertyCondition.equal("ts1.elTs", o_ts1.get()));
        }

        //thisNode.amtNet required
        //thisNode.amtNet <-> finStmtItm1.amtNet
        Optional<BigDecimal> o_amtNet = Optional.ofNullable(thisNode.getAmtNet());
        if(o_amtNet.isEmpty()){
            logger.trace(logPrfx + " --- o_amtNet: is Empty");
            logger.trace(logPrfx + " <-- ");
            return qryRsltNodes;
        }else{
            cond.add(PropertyCondition.equal("amtNet", o_amtNet.get()));
        }

        qryRsltNodes = dataManager.load(UsrNodeFinStmtItm.class).condition(cond).list();

        logger.trace(logPrfx + " <-- ");
        return qryRsltNodes;
    }

    @Override
    public UsrNodeFinStmtItm createFinStmtItm(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createFinStmtItm";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinStmtItm mergedFinStmtItm = null;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            DataContext dataContext = UiControllerUtils.getScreenData(screen).getDataContext();

            UsrNodeFinStmtItm finStmtItm1_Id = dataManager.create(UsrNodeFinStmtItm.class);

            //thisNode.finAcct1.id <-> finStmtItm1.finAcct1.id
            finStmtItm1_Id.setFinAcct1_Id(thisNode.getFinAcct1_Id());

            //thisNode.ts1.ElTs <-> finStmtItm1.ts1.ElTs
            HasTmst ts1 = dataManager.create(HasTmst.class);
            ts1.setElTs(thisNode.getTs1().getElTs());
            finStmtItm1_Id.setTs1(ts1);

            //thisNode.amtNet <-> finStmtItm1.amtNet
            finStmtItm1_Id.setAmtDebt(thisNode.getAmtDebt());
            finStmtItm1_Id.setAmtCred(thisNode.getAmtCred());
            finStmtItm1_Id.setAmtNet(thisNode.getAmtNet());

            serviceFinStmtItm1.updateInst1(screen, finStmtItm1_Id, UpdateOption.LOCAL);
            serviceFinStmtItm1.updateName1(screen, finStmtItm1_Id, UpdateOption.LOCAL);

            serviceFinStmtItm1.updateId2Calc(screen, finStmtItm1_Id, UpdateOption.LOCAL);
            serviceFinStmtItm1.updateId2(screen, finStmtItm1_Id, UpdateOption.LOCAL);
            serviceFinStmtItm1.updateId2Cmp(screen, finStmtItm1_Id, UpdateOption.LOCAL);
            serviceFinStmtItm1.updateId2Dup(screen, finStmtItm1_Id, UpdateOption.LOCAL);

            UsrNodeFinStmtItm savedFinStmtItm = dataManager.save(finStmtItm1_Id);

            mergedFinStmtItm = dataContext.merge(savedFinStmtItm);
            logger.debug(logPrfx + " --- created FinStmtItm id: " + mergedFinStmtItm.getId());
            notifications.create().withCaption("Created FinStmtItm with id2:" + mergedFinStmtItm.getId2()).show();
        }

        logger.trace(logPrfx + " <-- ");
        return mergedFinStmtItm;
    }


    public Boolean updateAmtCalcFinTxactItm1_EI1_Rate(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtCalcFinTxactItm1_EI1_Rate";
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

                    BigDecimal rate_ = thisNode.getAmtCalcFinTxactItm1_EI1_Rate();

                    UsrItemGenFmla amtCalcGenFmla1 = thisNode.getAmtCalcGenFmla1_Id();
                    if (amtCalcGenFmla1 == null) {
                        logger.debug(logPrfx + " --- amtCalcGenFmla1: null");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    } else if (!Objects.equals(amtCalcGenFmla1.getId2(), "Ref1 Mult Exch_Rate")) {
                        logger.debug(logPrfx + " --- amtCalcGenFmla1: " + amtCalcGenFmla1.getId2());
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    } else {
                        logger.debug(logPrfx + " --- amtCalcGenFmla1.id2: " + amtCalcGenFmla1.getId2());
                    }

                    UsrNodeFinTxactItm amtFinTxactItm1 = thisNode.getAmtCalcFinTxactItm1_Id();
                    if (amtFinTxactItm1 == null) {
                        logger.debug(logPrfx + " --- amtFinTxactItm1: null");
                        notifications.create().withCaption("Unable to get reference to the other FinTxactItm. Please ensure amtFinTxactItm1_Id is selected and it has a currency is selected.").show();
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    BigDecimal rate = BigDecimal.valueOf(0);
                    boolean qryRsltGood = false;

                    SysNodeBase curcyFr = thisNode.getAmtCalcFinTxactItm1_Id().getSysNodeFinCurcy1_Id();
                    if (curcyFr == null) {
                        logger.debug(logPrfx + " --- curcyFr: null");
                        notifications.create().withCaption("Unable to get the currency from the other FinTxactItm. Please ensure a FinTxactItm1_Id is selected and it has a currency is selected.").show();
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    } else {
                        logger.debug(logPrfx + " --- curcyFr.Id2: " + curcyFr.getId2());
                    }

                    SysNodeBase curcyTo = thisNode.getSysNodeFinCurcy1_Id();
                    if (curcyTo == null) {
                        logger.debug(logPrfx + " --- curcyTo: null");
                        notifications.create().withCaption("Unable to get the currency from this FinTxactItm. Please ensure a currency is selected.").show();
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    } else {
                        logger.debug(logPrfx + " --- curcyTo.Id2: " + curcyTo.getId2());

                    }

                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(n -> n.getElTs());

                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: is empty");
                        notifications.create().withCaption("Unable to get the date from this FinTxactItm. Please ensure a date is selected.").show();
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    } else {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    SysNodeBase curcyExchRate;
                    try {
                        curcyExchRate = dataManager.load(SysNodeBase.class)
                                .query("select e from enty_SysNodeBase e"
                                        + " where e.finCurcy1_Id.id = :curcyFr"
                                        + " and   e.finCurcy2_Id.id = :curcyTo"
                                        + " and   e.ts1.elTs = :ts1_ElTs"
                                )
                                .parameter("curcyFr", curcyFr.getId())
                                .parameter("curcyTo", curcyTo.getId())
                                .parameter("ts1_ElTs", o_ts1_ElTs.get())
                                .one()
                        ;

                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- qry1 java.lang.IllegalStateException: " + e.getMessage());
                        curcyExchRate = null;
                    }


                    if (curcyExchRate != null) {
                        rate = curcyExchRate.getAmt1();
                        qryRsltGood = true;
                        logger.debug(logPrfx + " --- qry1 result is Id: " + curcyExchRate.getId());

                    } else {
                        logger.debug(logPrfx + " --- qry1 result: is empty");
                        try {

                            curcyExchRate = dataManager.load(SysNodeBase.class)
                                    .query("select e from enty_SysNodeBase e"
                                            + " where e.finCurcy1_Id.id = :curcyFr"
                                            + " and   e.finCurcy2_Id.id = :curcyTo"
                                            + " and   e.ts1.elTs = :ts1_ElTs"
                                    )
                                    .parameter("curcyFr", curcyTo.getId())
                                    .parameter("curcyTo", curcyFr.getId())
                                    .parameter("ts1_ElTs", o_ts1_ElTs.get())
                                    .one()
                            ;
                        } catch (IllegalStateException e) {
                            logger.debug(logPrfx + " --- qry2 java.lang.IllegalStateException: " + e.getMessage());
                            curcyExchRate = null;
                        }

                        if (curcyExchRate != null) {
                            rate = curcyExchRate.getAmt2();
                            qryRsltGood = true;
                            logger.debug(logPrfx + " --- qry2 result is Id: " + curcyExchRate.getId());
                        } else {
                            logger.debug(logPrfx + " --- qry2 result: is empty");
                        }
                    }

                    logger.debug(logPrfx + " --- rate: " + rate);

                    if (!qryRsltGood) {
                        logger.debug(logPrfx + " --- qryRsltGood: false");
                    } else if (Objects.equals(rate_, rate)) {
                        logger.debug(logPrfx + " --- no change detected");
                    }else{
                        thisNode.setAmtCalcFinTxactItm1_EI1_Rate(rate);
                        logger.debug(logPrfx + " --- thisNode.setAmtFinTxactItm1_EI1_Rate(rate)");
                        isChanged = true;
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }


    public Boolean updateFinTaxLne1_Id(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinTaxLne1_Id";
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

                //finAcct1_Id.finTaxLne1_Id required
                Optional<UsrNodeFinTaxLne> o_finAcct1_Id_FinTaxLne1_Id = Optional.ofNullable(thisNode.getFinAcct1_Id())
                        .map(n -> n.getFinTaxLne1_Id());
                if (o_finAcct1_Id_FinTaxLne1_Id.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finAcct1_Id_FinTaxLne1_Id: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_finAcct1_Id_FinTaxLne1_Id: " + o_finAcct1_Id_FinTaxLne1_Id.get().getId2());
                }

                UsrNodeFinTaxLne finTaxLne_ = thisNode.getFinTaxLne1_Id();
                UsrNodeFinTaxLne finTaxLne = o_finAcct1_Id_FinTaxLne1_Id.get();
                logger.debug(logPrfx + " --- finTaxLne: " + finTaxLne);

                if (Objects.equals(finTaxLne_, finTaxLne)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTaxLne1_Id(finTaxLne);
                    logger.debug(logPrfx + " --- thisNode.setFinTaxLne1_Id(finTaxLne)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


}