package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBase;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactItm;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSet;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetGrpg;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseComn;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.core.Sort;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.Screen;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("bean_UsrNodeFinTxactSet.Service")
public class UsrNodeFinTxactSet0Service extends UsrNodeBase0Service {

    public UsrNodeFinTxactSet0Service() {
        this.typeOfNode = UsrNodeFinTxactSet.class;
    }

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
        isChanged = updateFinTxacts1_IdCntCalc(screen, thisNode, updOption)  || isChanged;

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
     *      <li><i>ts1.elTs</i> (tsEnd) required</li>
     *      <li><i>sortIdx</i> (intX) optional</li>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>sortIdx</i> (idX) = "0"</li>
     *      <li><i>desc1</i> = "D2023-01-01/X00"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>sortIdx</i> (idX) = is null</li>
     *      <li><i>desc1</i> = "D2023-01-01/X00"</li>
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

                //ts1.elTs required
                Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                if (o_ts1_ElTs.isEmpty()) {
                    logger.debug(logPrfx + " --- o_ts1_ElTs: null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                    sb.append("D");
                    sb.append(o_ts1_ElTs.get().format(FRMT_DT));
                }

                //sortIdx optional
                Optional<Integer> o_sortIdx = Optional.ofNullable(thisNode.getSortIdx());
                if (o_sortIdx.isEmpty()) {
                    logger.debug(logPrfx + " --- o_sortIdx: null");
                }else{
                    logger.debug(logPrfx + " --- o_sortIdx: " + o_sortIdx);
                }
                sb.append(SEP1);
                sb.append("X");
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
     *          <li><i>inst1</i> = "D2016-12-28/X00"</li>
     *          <li><i>name1</i> = "D2016-12-28/X00"</li>
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
     *      <li><i>type1_Id.id2</i> optional</li>
     *      <p>If <i>desc1Node1_Id.isNull()</i> then</p>
     *      <p style="margin-left: 24px;">find first UsrNodeFinTxactItm e </p>
     *      <p style="margin-left: 24px;">where e.id2 = node.id2 + "/Y00/X00" </p>
     *      <p>If <i>desc1Node1_Id.isPresent()</i> then</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>desc1Node1_Id.amtDebt</i> optional</li>
     *          <li><i>desc1Node1_Id.amtCred</i> optional</li>
     *          <li><i>desc1Node1_Id.sysNodeFinCurcy1_Id.Id2</i> optional</li>
     *          <p>If <i>type1_Id2.isPresent() && type1_Id2.contains("Exch")</i> then</p>
     *          <ul style="margin-left: 24px;">
     *              <p>If <i>desc1Node2_Id.isNull()</i> then</p>
     *              <p style="margin-left: 24px;">find first UsrNodeFinTxactItm e </p>
     *              <p style="margin-left: 24px;">where e.id2 = node.id2 + "/Y01/X00" </p>
     *              <p>If <i>desc1Node2_Id.isPresent()</i> then</p>
     *              <ul style="margin-left: 24px;">
     *                  <li><i>desc1Node2_Id.amtDebt</i> optional</li>
     *                  <li><i>desc1Node2_Id.amtCred</i> optional</li>
     *                  <li><i>desc1Node2_Id.sysNodeFinCurcy1_Id.Id2</i> optional</li>
     *              </ul>
     *          </ul>
     *      </ul>
     *      <li><i>genChan1_Id.id2</i> optional</li>
     *      <li><i>finHow1_Id.id2</i> optional</li>
     *      <li><i>finWhat1_Id.id2</i> optional</li>
     *      <li><i>whatText1</i> optional</li>
     *      <li><i>finWhy1_Id.id2</i> optional</li>
     *      <li><i>whyText1</i> optional</li>
     *      <li><i>genTags1_Id.map.id2</i> optional</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>type1_Id.id2</i> = "Exp"</li>
     *      <li><i>desc1Node1_Id.isPresent</i> = false</li>
     *      <li><i>genChan1_Id.id2</i> = "RBC Bank"</li>
     *      <li><i>finHow1_Id.id2</i> = "RBC Direct"</li>
     *      <li><i>finWhat1_Id.id2</i> = "account fee"</li>
     *      <li><i>whatText1</i> = ""</li>
     *      <li><i>finWhy1_Id.id2</i> = ""</li>
     *      <li><i>whyText1</i> = ""</li>
     *      <li><i>genTags1_Id.map.id2</i> = ""</li>
     *      <li><i>desc1</i> = "Exp 125.00 CAD on chan [RBC Bank] via [RBC Direct] for account fee"</li>
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

                //type1_Id.id2 optional
                Optional<String> o_type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(UsrNodeBaseType::getId2);
                if (o_type1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_type1_Id2: null");
                }else{
                    logger.debug(logPrfx + " --- o_type1_Id2: " + o_type1_Id2.get());
                    sb.append(o_type1_Id2.get());
                }


                PropertyCondition condDType_Eq_FTI = PropertyCondition.equal("dtype", "enty_UsrNodeFinTxactItm");

                // ts1.elTs equal this
                // required
                Optional<LocalDateTime> o_ts1 = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                if(o_ts1.isEmpty()) {
                    logger.trace(logPrfx + " --- o_ts1: is Empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }
                PropertyCondition condTs1_ElTs_Eq_This = PropertyCondition.equal("ts1.elTs", o_ts1.get());

                // int1 equal this
                // optional
                LogicalCondition condInt1_Eq_This = LogicalCondition.or();
                Integer int1 = Optional.ofNullable(thisNode.getSortIdx()).orElse(0);
                if(int1 == 0){
                    // if zero or condition with is null
                    //isSet false = is null; isSet true = is not null
                    condInt1_Eq_This.add(PropertyCondition.isSet("int1", false));
                }
                condInt1_Eq_This.add(PropertyCondition.equal("int1", int1));

                // int2 equal 0
                // optional
                LogicalCondition condInt2_Eq_0 = LogicalCondition.or();
                Integer int2 = 0;
                condInt2_Eq_0.add(PropertyCondition.isSet("int2", false));
                condInt2_Eq_0.add(PropertyCondition.equal("int2", int2));

                // int2 equal 1
                // optional
                PropertyCondition condInt2_Eq_1 = PropertyCondition.equal("int2", 1);

                // sortIdx 0
                // optional
                LogicalCondition condSortIdx_Eq_0 = LogicalCondition.or();
                Integer sortIdx = 0;
                condSortIdx_Eq_0.add(PropertyCondition.isSet("sortIdx", false));
                condSortIdx_Eq_0.add(PropertyCondition.equal("sortIdx", sortIdx));

                // id2 = thisNode.getId2() + "/Y00/Z00"
                LogicalCondition condDesc1Node1 = LogicalCondition.and();
                condDesc1Node1.add(condDType_Eq_FTI);
                condDesc1Node1.add(condTs1_ElTs_Eq_This);
                condDesc1Node1.add(condInt1_Eq_This);
                condDesc1Node1.add(condInt2_Eq_0);
                condDesc1Node1.add(condSortIdx_Eq_0);

                //desc1Node1_Id.id2 optional
                Optional<UsrNodeBase> o_desc1Node1_Id = Optional.ofNullable(thisNode.getDesc1Node1_Id())
                        .or(() -> dataManager.load(UsrNodeBase.class)
                                .condition(condDesc1Node1)
                                .sort(Sort.by("id2"))
                                .firstResult(0)
                                .optional())
                        .or(() -> Optional.empty());


                //thisAmt
                if (o_desc1Node1_Id.isPresent()) {

                    //amtDebt optional
                    Optional<BigDecimal> o_amtDebt = Optional.ofNullable(o_desc1Node1_Id.get().getAmtDebt());
                    if (o_amtDebt.isEmpty()) {
                        logger.debug(logPrfx + " --- o_amtDebt: null");
                    }else{
                        logger.debug(logPrfx + " --- o_amtDebt: " + o_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
                        sb.append(SEP0);
                        sb.append(o_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
                    }

                    //amtCred optional
                    Optional<BigDecimal> o_amtCred = Optional.ofNullable(o_desc1Node1_Id.get().getAmtCred());
                    if (o_amtCred.isEmpty()) {
                        logger.debug(logPrfx + " --- o_amtCred: null");
                    }else{
                        logger.debug(logPrfx + " --- o_amtCred: " + o_amtCred.get().setScale(2, RoundingMode.HALF_UP));
                        sb.append(SEP0);
                        sb.append(o_amtCred.get().setScale(2, RoundingMode.HALF_UP));
                    }

                    //sysNodeFinCurcy1_Id2 optional
                    Optional<String> o_sysNodeFinCurcy1_Id2 = Optional.ofNullable(o_desc1Node1_Id.get().getSysNodeFinCurcy1_Id()).map(n->n.getId2());
                    if (o_sysNodeFinCurcy1_Id2.isEmpty()) {
                        logger.debug(logPrfx + " --- o_sysNodeFinCurcy1_Id2: null");
                    }else{
                        logger.debug(logPrfx + " --- o_sysNodeFinCurcy1_Id2: " + o_sysNodeFinCurcy1_Id2.get());
                        sb.append(SEP0);
                        sb.append(o_sysNodeFinCurcy1_Id2.get());
                    }

                    if (o_type1_Id2.isPresent() && o_type1_Id2.get().contains("Exch")) {


                        // id2 = thisNode.getId2() + "/Y01/Z00"
                        LogicalCondition condDesc1Node2 = LogicalCondition.and();
                        condDesc1Node2.add(condDType_Eq_FTI);
                        condDesc1Node2.add(condTs1_ElTs_Eq_This);
                        condDesc1Node2.add(condInt1_Eq_This);
                        condDesc1Node2.add(condInt2_Eq_1);
                        condDesc1Node2.add(condSortIdx_Eq_0);

                        //desc1Node2_Id.id2 optional
                        Optional<UsrNodeBase> o_desc1Node2_Id = Optional.ofNullable(thisNode.getDesc1Node2_Id())
                                .or(() -> dataManager.load(UsrNodeBase.class)
                                        .condition(condDesc1Node2)
                                        .sort(Sort.by("id2"))
                                        .firstResult(0)
                                        .optional())
                                .or(() -> Optional.empty());

                        if (o_desc1Node2_Id.isPresent()) {
                            sb.append(SEP0);
                            sb.append("->");


                            //amtDebt optional
                            Optional<BigDecimal> o_amtDebt2 = Optional.ofNullable(o_desc1Node2_Id.get().getAmtDebt());
                            if (o_amtDebt2.isEmpty()) {
                                logger.debug(logPrfx + " --- o_amtDebt2: null");
                            }else{
                                logger.debug(logPrfx + " --- o_amtDebt2: " + o_amtDebt2.get().setScale(2, RoundingMode.HALF_UP));
                                sb.append(SEP0);
                                sb.append(o_amtDebt2.get().setScale(2, RoundingMode.HALF_UP));
                            }

                            //amtCred optional
                            Optional<BigDecimal> o_amtCred2 = Optional.ofNullable(o_desc1Node2_Id.get().getAmtCred());
                            if (o_amtCred2.isEmpty()) {
                                logger.debug(logPrfx + " --- o_amtCred2: null");
                            }else{
                                logger.debug(logPrfx + " --- o_amtCred2: " + o_amtCred2.get().setScale(2, RoundingMode.HALF_UP));
                                sb.append(SEP0);
                                sb.append(o_amtCred2.get().setScale(2, RoundingMode.HALF_UP));
                            }

                            //sysNodeFinCurcy1_Id2 optional
                            Optional<String> o_sysNodeFinCurcy2_Id2 = Optional.ofNullable(o_desc1Node2_Id.get().getSysNodeFinCurcy1_Id()).map(n->n.getId2());
                            if (o_sysNodeFinCurcy2_Id2.isEmpty()) {
                                logger.debug(logPrfx + " --- o_sysNodeFinCurcy2_Id2: null");
                            }else{
                                logger.debug(logPrfx + " --- o_sysNodeFinCurcy2_Id2: " + o_sysNodeFinCurcy2_Id2.get());
                                sb.append(SEP0);
                                sb.append(o_sysNodeFinCurcy2_Id2.get());
                            }

                        }
                    }
                }

                //genChan1_Id.id2 optional
                Optional<String> o_genChan1_Id2 = Optional.ofNullable(thisNode.getGenChan1_Id()).map(UsrNodeBase::getId2);
                if (o_genChan1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_genChan1_Id2: is empty");
                }else if(o_genChan1_Id2.get().isBlank()){
                    logger.debug(logPrfx + " --- o_finHow1_Id2: is blank");
                }else{
                    logger.debug(logPrfx + " --- o_genChan1_Id2: " + o_genChan1_Id2.get());
                    sb.append(SEP0);
                    sb.append("in chan [");
                    sb.append(o_genChan1_Id2.get());
                    sb.append("]");
                }

                //finHow1_Id.id2 optional
                Optional<String> o_finHow1_Id2 = Optional.ofNullable(thisNode.getFinHow1_Id()).map(UsrItemBase::getId2);
                String finHow1_Id2 = o_finHow1_Id2.orElse("");
                if (o_finHow1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finHow1_Id2: is empty");
                }else if(o_finHow1_Id2.get().isBlank()){
                    logger.debug(logPrfx + " --- o_finHow1_Id2: is blank");
                }else{
                    logger.debug(logPrfx + " --- o_finHow1_Id2: " + o_finHow1_Id2.get());
                    sb.append(SEP0);
                    sb.append("via [");
                    sb.append(o_finHow1_Id2.get());
                    sb.append("]");
                }


                //finWhat1_Id.id2 optional
                Optional<String> o_finWhat1_Id2 = Optional.ofNullable(thisNode.getFinWhat1_Id()).map(UsrItemBase::getId2);
                logger.debug(logPrfx + " --- o_finWhat1_Id2: " + o_finWhat1_Id2.orElse("null"));
                String finWhat1_Id2 = o_finWhat1_Id2.orElse("");

                //whatText1 optional
                Optional<String> o_whatText1 = Optional.ofNullable(thisNode.getWhatText1());
                logger.debug(logPrfx + " --- o_whatText1: " + o_whatText1.orElse("null"));
                String whatText1 = o_whatText1.orElse("");

                if (!finWhat1_Id2.isBlank() || !whatText1.isBlank()) {
                    sb.append(SEP0);
                    sb.append("for [");
                    sb.append(StringUtils.strip(o_finWhat1_Id2.orElse("")
                            + " " + o_whatText1.orElse("") ));
                    sb.append("]");
                }


                //finWhy1_Id.id2 optional
                Optional<String> o_finWhy1_Id2 = Optional.ofNullable(thisNode.getFinWhy1_Id()).map(UsrItemBase::getId2);
                logger.debug(logPrfx + " --- o_finWhy1_Id2: " + o_finWhy1_Id2.orElse("null"));
                String finWhy1_Id2 = o_finWhy1_Id2.orElse("");

                //whyText1 optional
                Optional<String> o_whyText1 = Optional.ofNullable(thisNode.getWhyText1());
                logger.debug(logPrfx + " --- o_whyText1: " + o_whyText1.orElse("null"));
                String whyText1 = o_whyText1.orElse("");

                if (!finWhy1_Id2.isBlank() || !whyText1.isBlank()) {
                    sb.append(SEP0);
                    sb.append("for [");
                    sb.append(StringUtils.strip(o_finWhy1_Id2.orElse("")
                            + " " + o_whyText1.orElse("") ));
                    sb.append("]");
                }

                //genTags1_Id.map.id2 optional
                Optional<List<UsrItemGenTag>> o_genTags1_Id = Optional.ofNullable(thisNode.getGenTags1_Id());

                if (o_genTags1_Id.isEmpty()) {
                    logger.debug(logPrfx + " --- o_genTags1_Id: null");
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


    public Boolean updateFinTxacts1_IdCntCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, UpdateOption updOption) {
        String logPrfx = "updateFinTxacts1_IdCntCalc";
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
                Integer idCntCalc_ = thisNode.getFinTxacts1_IdCntCalc();
                Integer idCntCalc = null ;
                String qry1 = "select count(e.id2)"
                        + " from enty_UsrNodeFinTxact e"
                        + " where e.parent1_Id = :parent1_Id"
                        ;
                try{
                    idCntCalc = dataManager.loadValue(qry1,Integer.class)
                            .store("main")
                            .parameter("parent1_Id",thisNode)
                            .one();
                    if (idCntCalc == null) { idCntCalc = 0;}

                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                    logger.debug(logPrfx + " --- idCntCalc: null");
                }

                logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);
                if (Objects.equals(idCntCalc_, idCntCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxacts1_IdCntCalc(idCntCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxacts1_IdCntCalc(idCntCalc)");
                    isChanged = true;
                }
            }
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    @Override
    public Integer getSortIdxMax(@NotNull Screen screen, Object grpgKey) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof UsrNodeFinTxactSetGrpg
            if (!(grpgKey instanceof UsrNodeFinTxactSetGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeFinTxactSetGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer sortIdxCntIsNull = null;
            String sortIdxCntIsNullQry = "select count(e)"
                    + " from enty_UsrNodeFinTxactItm e"
                    + " where e.parent1_Id = :parent1_Id"
                    + " and e.ts1.elTs = :ts1_ElTs"
                    + " and e.sortIdx is null"
                    ;
            try {
                sortIdxCntIsNull = dataManager.loadValue(sortIdxCntIsNullQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
                        .parameter("ts1_ElTs", l_grpgKey.ts1())
                        .one();
            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- sortIdxCntIsNullQry error: " + e.getMessage());
                notifications.create().withCaption("sortIdxCntIsNullQry error: " + e.getMessage()).show();
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