package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxact;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSet;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseComn;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("bean_UsrNodeFinTxact.Service")
public class UsrNodeFinTxact0Service extends UsrNodeBase0Service {

    public UsrNodeFinTxact0Service() {
        this.typeOfNode = UsrNodeFinTxact.class;
    }

    @Autowired
    protected UsrNodeFinTxactSet0Service serviceParent1;

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

        isChanged = updateParent1_Id(screen, thisNode, updOption) || isChanged;

        isChanged = updateInt1(screen, thisNode, updOption) || isChanged;
        isChanged = updateTs1(screen, thisNode, updOption) || isChanged;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;
        isChanged = updateSortKey(screen, thisNode, updOption) || isChanged;

        isChanged = updateFinTxactItms1_AmtDebtSumCalc(screen, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtCredSumCalc(screen, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtNet2SumCalc(screen, thisNode, updOption)  || isChanged;

        isChanged = updateFinTxactItms1_IdCntCalc(screen, thisNode, updOption)  || isChanged;
        isChanged = updateFinTxactItms1_AmtEqCalc(screen, thisNode, updOption)  || isChanged;

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
     *          <li><i>parent1_Id.ts1.elTs</i> (tsEnd) required</li>
     *          <li><i>parent1_Id.sortIdx</i> (intX) optional default 0</li>
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>ts1.elTs</i> (tsEnd) required</li>
     *          <li><i>int1</i> (intX) optional default 0</li>
     *      </ul>
     *      <ul>
     *      <li><i>sortIdx</i> (intY) optional default 0</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = true</li>
     *      <li><i>parent1_Id.ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>parent1_Id.sortIdx</i> (idX) = "0"</li>
     *      <li><i>sortIdx</i> (idY) = "0"</li>
     *      <li><i>desc1</i> = "D2023-01-01/X00/Y00"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = false</li>
     *      <li><i>ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>int1</i> (idX) = "0"</li>
     *      <li><i>sortKey</i> (idY) = "0"</li>
     *      <li><i>desc1</i> = "D2023-01-01/X00/Y00"</li>
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
                if(o_parent1_Id.isPresent()) {
                    logger.trace(logPrfx + " --- o_parent1_Id: " + o_parent1_Id.get().getId2());

                    //ts1.elTs required
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(o_parent1_Id.get().getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: is null");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                        sb.append("D");
                        sb.append(o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    //int1 optional
                    Optional<Integer> o_int1 = Optional.ofNullable(o_parent1_Id.get().getSortIdx());
                    if (o_int1.isEmpty()) {
                        logger.debug(logPrfx + " --- o_int1: is null");
                    }else{
                        logger.debug(logPrfx + " --- o_int1: " + o_int1);
                    }
                    sb.append(SEP1);
                    sb.append("X");
                    //the length of the string will be 2 characters with leading zeros
                    sb.append(String.format("%02d",o_int1.orElse(0)));

                }else{
                    logger.trace(logPrfx + " --- o_parent1_Id: is empty");

                    //ts1.elTs required
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: is null");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }else{
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                        sb.append("D");
                        sb.append(o_ts1_ElTs.get().format(FRMT_DT));
                    }

                    //int1 optional
                    Optional<Integer> o_int1 = Optional.ofNullable(thisNode.getInt1());
                    if (o_int1.isEmpty()) {
                        logger.debug(logPrfx + " --- o_int1: is null");
                    }else{
                        logger.debug(logPrfx + " --- o_int1: " + o_int1);
                    }
                    sb.append(SEP1);
                    sb.append("X");
                    //the length of the string will be 2 characters with leading zeros
                    sb.append(String.format("%02d",o_int1.orElse(0)));

                }

                //sortIdx optional
                Optional<Integer> o_sortIdx = Optional.ofNullable(thisNode.getSortIdx());
                if (o_sortIdx.isEmpty()) {
                    logger.debug(logPrfx + " --- o_sortIdx: is null");
                }else{
                    logger.debug(logPrfx + " --- o_sortIdx: " + o_sortIdx);
                }
                sb.append(SEP1);
                sb.append("Y");
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
     *          <li><i>inst1</i> = "D2016-12-28/X00/Y00"</li>
     *          <li><i>name1</i> = "D2016-12-28/X00/Y00"</li>
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
     *      <li><i>genTags1_Id.map.id2</i> optional</li>
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
                    logger.debug(logPrfx + " --- o_type1_Id2: is null");
                }else{
                    logger.debug(logPrfx + " --- o_type1_Id2: " + o_type1_Id2.get());
                    sb.append(o_type1_Id2.get());
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
     * <h1>Update the <i>ts1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *      <p>If <i>parent1_Id.isPresent()</i> then</p>
     *      <ul style="margin-left: 36px;">
     *          <li><i>parent1_Id.ts1.elTs</i> (tsEnd) required</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = true</li>
     *      <li><i>parent1_Id.ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>ts1</i> = "2022-01-01"</li>
     *      <li><i>int1</i> (Result) = "2023-01-01"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = false</li>
     *      <li><i>ts1</i> = "2022-01-01"</li>
     *      <li><i>int1</i> (Result) = "2022-01-01"</li>
     * </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateTs1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateTs1";
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
                HasTmst ts1_ = thisNode.getTs1();
                HasTmst ts1 = thisNode.getTs1();
                if (ts1 == null){
                    ts1 = dataManager.create(HasTmst.class);
                }

                //parent1_Id optional
                Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if(o_parent1_Id.isPresent()) {
                    logger.trace(logPrfx + " --- o_parent1_Id: " + o_parent1_Id.get().getId2());

                    //ts1.elTs required
                    Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(o_parent1_Id.get().getTs1()).map(HasTmst::getElTs);
                    if (o_ts1_ElTs.isEmpty()) {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: empty");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    } else {
                        logger.debug(logPrfx + " --- o_ts1_ElTs: " + o_ts1_ElTs.get().format(FRMT_DT));
                        ts1.setElTs(o_ts1_ElTs.get());
                    }

                    logger.debug(logPrfx + " --- ts1_ElTs: " + ts1.getElTs().format(FRMT_DT));

                    if (Objects.equals(ts1_, ts1)) {
                        logger.debug(logPrfx + " --- no change detected");
                    } else {
                        thisNode.setTs1(ts1);
                        logger.debug(logPrfx + " --- called thisNode.setTs1(ts1)");
                        isChanged = true;
                    }
                }
            }
        }
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
     *          <li><i>desc1</i></li>
     *          <li><i>inst1</i></li>
     *          <li><i>name1</i></li>

     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>finTxactSet1_Id2Trgt</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    @Override
    public Boolean updateTs1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>int1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *      <p>If <i>parent1_Id.isPresent()</i> then</p>
     *      <ul style="margin-left: 36px;">
     *          <li><i>parent1_Id.ts1.elTs</i> (tsEnd) required</li>
     *          <li><i>parent1_Id.sortIdx</i> (intX) required</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = true</li>
     *      <li><i>parent1_Id.ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>parent1_Id.sortIdx</i> (idX) = 12</li>
     *      <li><i>int1</i> (idX) = 10</li>
     *      <li><i>int1</i> (Result) = 12</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = false</li>
     *      <li><i>int1</i> (idX) = 10</li>
     *      <li><i>int1</i> = 10</li>
     * </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateInt1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateInt1";
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
                Integer int1_ = thisNode.getInt1();
                Integer int1 = thisNode.getInt1();

                //parent1_Id optional
                Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if(o_parent1_Id.isPresent()) {
                    logger.trace(logPrfx + " --- o_parent1_Id: " + o_parent1_Id.get().getId2());

                    //int1 required
                    Optional<Integer> o_parent1_sortIdx = Optional.ofNullable(o_parent1_Id.get().getSortIdx());
                    if (o_parent1_sortIdx.isEmpty()) {
                        logger.debug(logPrfx + " --- o_parent1_sortIdx: is null");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    } else {
                        logger.debug(logPrfx + " --- o_parent1_sortIdx: " + o_parent1_sortIdx);
                        int1 = o_parent1_sortIdx.get();
                    }

                    logger.debug(logPrfx + " --- int1: " + int1);

                    if (Objects.equals(int1_, int1)) {
                        logger.debug(logPrfx + " --- no change detected");
                    } else {
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


    /**
     * <h1>Update the <i>int1</i> field dependent fields</h1>
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
     *          <li><i>desc1</i></li>
     *          <li><i>inst1</i></li>
     *          <li><i>name1</i></li>

     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>finTxactSet1_Id2Trgt</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateInst1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public List<UsrNodeBase> findParentNodes(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findParentNodes";
        logger.trace(logPrfx + " --> ");

        LogicalCondition cond = LogicalCondition.and();
        List<UsrNodeBase> qryRsltNodes = new ArrayList<UsrNodeBase>();
        cond.add(PropertyCondition.equal("dtype", "enty_UsrNodeFinTxactSet"));

        //parent1.ts1.elTs required
        //thisNode.ts1.elTs -> parent1.ts1.elTs
        Optional<LocalDateTime> o_ts1 = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
        if(o_ts1.isEmpty()){
            logger.trace(logPrfx + " --- o_ts1: is Empty");
            logger.trace(logPrfx + " <-- ");
            return qryRsltNodes;
        }else{
            cond.add(PropertyCondition.equal("ts1.elTs", o_ts1.get()));

        }

        //parent1.int1 optional
        //thisNode.int1 -> parent1.sortIdx
        LogicalCondition condSortIdx = LogicalCondition.or();
        Integer parent1_Int1 = Optional.ofNullable(thisNode.getInt1()).orElse(0);
        if(parent1_Int1 == 0){
            // if zero or condition with is null
            //isSet false = is null; isSet true = is not null
            condSortIdx.add(PropertyCondition.isSet("sortIdx", false));
        }
        condSortIdx.add(PropertyCondition.equal("sortIdx", parent1_Int1));
        cond.add(condSortIdx);

        qryRsltNodes = dataManager.load(UsrNodeBase.class).condition(cond).list();

        logger.trace(logPrfx + " <-- ");
        return qryRsltNodes;
    }

    @Override
    public UsrNodeBase createParentNode(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createParentNode";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactSet mergedFinTxactSet = null;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            DataContext dataContext = UiControllerUtils.getScreenData(screen).getDataContext();

            UsrNodeFinTxactSet parent1_Id = dataManager.create(UsrNodeFinTxactSet.class);

            //thisNode.ts1.elTs <-> parent1.ts1.elTs
            HasTmst ts1 = dataManager.create(HasTmst.class);
            ts1.setElTs(thisNode.getTs1().getElTs());
            parent1_Id.setTs1(ts1);

            //thisNode.int1 <-> parent1.sortKey
            parent1_Id.setSortIdx(thisNode.getInt1());

            serviceParent1.updateInst1(screen, parent1_Id, UpdateOption.LOCAL);
            serviceParent1.updateName1(screen, parent1_Id, UpdateOption.LOCAL);

            serviceParent1.updateId2Calc(screen, parent1_Id, UpdateOption.LOCAL);
            serviceParent1.updateId2(screen, parent1_Id, UpdateOption.LOCAL);
            serviceParent1.updateId2Cmp(screen, parent1_Id, UpdateOption.LOCAL);
            serviceParent1.updateId2Dup(screen, parent1_Id, UpdateOption.LOCAL);

            UsrNodeFinTxactSet savedFinTxactSet = dataManager.save(parent1_Id);

            mergedFinTxactSet = dataContext.merge(savedFinTxactSet);
            logger.debug(logPrfx + " --- created FinTxactSet id: " + mergedFinTxactSet.getId());
            notifications.create().withCaption("Created FinTxactSet with id2:" + mergedFinTxactSet.getId2()).show();
        }

        logger.trace(logPrfx + " <-- ");
        return mergedFinTxactSet;
    }


    public Boolean updateFinTxactItms1_AmtDebtSumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
                        + " where e.parent1_Id = :parent1_Id"
                        + " group by e.parent1_Id"
                        ;
                try{
                    amtDebtSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("parent1_Id",thisNode)
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

    public Boolean updateFinTxactItms1_AmtCredSumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
                        + " where e.parent1_Id = :parent1_Id"
                        + " group by e.parent1_Id"
                        ;
                try{
                    amtCredSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                            .store("main")
                            .parameter("parent1_Id",thisNode)
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

    public Boolean updateFinTxactItms1_IdCntCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
                        + " where e.parent1_Id = :parent1_Id"
                        ;
                try{
                    idCntCalc = dataManager.loadValue(qry1,Integer.class)
                            .store("main")
                            .parameter("parent1_Id",thisNode)
                            .one();
                    if (idCntCalc == null) {
                        idCntCalc = 0;}
                    logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);

                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                    logger.debug(logPrfx + " --- idCntCalc: is null");
                }

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

    public Boolean updateFinTxactItms1_SysNodeFinCurcyEqCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinTxactItms1_SysNodeFinCurcyEqCalc";
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
                Boolean finCurcyEqCalc_ = thisNode.getFinTxactItms1_SysNodeFinCurcyEqCalc();
                Boolean finCurcyEqCalc = false;

                List<UsrNodeBase> finCurcyList = null;
                String qry1 = "select e.finCurcy1_Id"
                        + " from enty_UsrNodeFinTxactItm e"
                        + " where e.parent1_Id = :parent1_Id"
                        ;
                try{
                    finCurcyList = dataManager.loadValue(qry1, UsrNodeBase.class)
                            .store("main")
                            .parameter("parent1_Id",thisNode)
                            .list();
                    if (finCurcyList == null || finCurcyList.isEmpty()) {
                        logger.debug(logPrfx + " --- finCurcyList.size(): 0");
                    }else{
                        logger.debug(logPrfx + " --- finCurcyList.size(): " + finCurcyList.size());

                        //finCurcyEqCalc = finCurcyList.size() == 1;

                        String finCurcyFirst_Id2 = finCurcyList.get(0).getId2();
                        finCurcyEqCalc = true;
                        for (UsrNodeBase finCurcy: finCurcyList){
                            if (Objects.equals(finCurcyFirst_Id2,finCurcy.getId2())){
                                finCurcyEqCalc = false;
                            }
                        }

                    }
                } catch (IllegalStateException e){
                    logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                    logger.debug(logPrfx + " --- amtCredSumCalc: is null");
                }

                logger.debug(logPrfx + " --- finCurcyEqCalc: " + finCurcyEqCalc);

                if (Objects.equals(finCurcyEqCalc_, finCurcyEqCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_SysNodeFinCurcyEqCalc(finCurcyEqCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_SysNodeFinCurcyEqCalc(finCurcyEqCalc)");
                    isChanged = true;
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    @Override
    public Boolean updateIdPartsFrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateIdPartsFrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateTs1FrId2(screen, thisNode, updOption) || isChanged;
        isChanged = updateInt1FrId2(screen, thisNode, updOption)  || isChanged;
        isChanged = updateSortIdxFrId2(screen, thisNode, updOption)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



}