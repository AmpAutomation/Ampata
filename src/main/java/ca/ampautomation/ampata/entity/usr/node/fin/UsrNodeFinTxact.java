package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBase;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.querycondition.PropertyCondition;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@JmixEntity
@Entity(name = "enty_UsrNodeFinTxact")
public class UsrNodeFinTxact extends UsrNodeBase {


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
     *      <p>If <i>parent1_Id.isPresent()</i> then</p>
     *      <ul style="margin-left: 36px;">
     *          <li><i>parent1_Id.ts1.elTs</i> (tsEnd) required</li>
     *          <li><i>parent1_Id.sortIdx</i> (intX) required</li>
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>ts1.elTs</i> (tsEnd) required</li>
     *          <li><i>int1</i> (intX) required</li>
     *      </ul>
     *      <ul>
     *      <li><i>sortIdx</i> (intY) required</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isPresent()</i> = true</li>
     *      <li><i>parent1_Id.ts1.elTs</i> (tsEnd) = "2023-01-01"</li>
     *      <li><i>parent1_Id.sortIdx</i> (idX) = "0"</li>
     *      <li><i>sortKey</i> (idY) = "0"</li>
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
    public Boolean updateInst1(DataManager dataManager){
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //parent1_Id optional
        Optional<UsrNodeBase> l_parent1_Id = Optional.ofNullable(this.parent1_Id);
        if(l_parent1_Id.isPresent()) {
            logger.trace(logPrfx + " --- l_parent1_Id :" + l_parent1_Id.get().getId2());

            //ts1.elTs required
            Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(l_parent1_Id.get().getTs1()).map(HasTmst::getElTs);
            if (l_ts1ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts1ElTs: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
                sb.append("D");
                sb.append(l_ts1ElTs.get().format(frmtDt));
            }

            //int1 required
            Optional<Integer> l_int1 = Optional.ofNullable(l_parent1_Id.get().getInt1());
            if (l_int1.isEmpty()) {
                logger.debug(logPrfx + " --- l_int1: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_int1: " + l_int1);
                sb.append(SEP1);
                sb.append("X");
                sb.append(l_int1);
            }
        }else{
            logger.trace(logPrfx + " --- l_parent1_Id is null");

            //ts1.elTs required
            Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(this.getTs1()).map(HasTmst::getElTs);
            if (l_ts1ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts1ElTs: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
                sb.append("D");
                sb.append(l_ts1ElTs.get().format(frmtDt));
            }

            //int1 required
            Optional<Integer> l_int1 = Optional.ofNullable(this.getInt1());
            if (l_int1.isEmpty()) {
                logger.debug(logPrfx + " --- l_int1: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_int1: " + l_int1);
                sb.append(SEP1);
                sb.append("X");
                sb.append(l_int1);
            }

        }

        //sortIdx required
        Optional<Integer> l_sortIdx = Optional.ofNullable(this.getSortIdx());
        if (l_sortIdx.isEmpty()) {
            logger.debug(logPrfx + " --- l_sortIdx: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- l_sortIdx: " + l_sortIdx);
            sb.append(SEP1);
            sb.append("Y");
            sb.append(l_sortIdx);
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
     *      <li><i>type1_Id.id2</i> optional</li>
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
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();



        //type1_Id.id2 optional
        Optional<String> l_type1_Id2 = Optional.ofNullable(this.getType1_Id()).map(UsrNodeBaseType::getId2);
        if (l_type1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_type1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_type1_Id2: " + l_type1_Id2.get());
            sb.append(l_type1_Id2.get());
        }

        //desc1Node1_Id.id2 optional
        Optional<UsrNodeBase> l_desc1Node1_Id = Optional.ofNullable(this.getDesc1Node1_Id())
                .or(() -> Optional.ofNullable(dataManager.load(UsrNodeFinTxactItm.class)
                        .condition(PropertyCondition.startsWith("id2",this.getId2() + "/"))
                        .one()))
                .or(() -> Optional.empty());


        //thisAmt
        if (l_desc1Node1_Id.isPresent()) {

            //amtDebt optional
            Optional<BigDecimal> l_amtDebt = Optional.ofNullable(l_desc1Node1_Id.get().getAmtDebt());
            if (l_amtDebt.isEmpty()) {
                logger.debug(logPrfx + " --- l_amtDebt: null");
            }else{
                logger.debug(logPrfx + " --- l_amtDebt: " + l_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
                sb.append(SEP0);
                sb.append(" Debt ");
                sb.append(l_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
            }

            //amtCred optional
            Optional<BigDecimal> l_amtCred = Optional.ofNullable(l_desc1Node1_Id.get().getAmtCred());
            if (l_amtCred.isEmpty()) {
                logger.debug(logPrfx + " --- l_amtCred: null");
            }else{
                logger.debug(logPrfx + " --- l_amtCred: " + l_amtCred.get().setScale(2, RoundingMode.HALF_UP));
                sb.append(SEP0);
                sb.append(" Cred ");
                sb.append(l_amtCred.get().setScale(2, RoundingMode.HALF_UP));
            }

            //sysNodeFinCurcy1_Id2 optional
            Optional<String> l_sysNodeFinCurcy1_Id2 = Optional.ofNullable(l_desc1Node1_Id.get().getSysNodeFinCurcy1_Id()).map(n->n.getId2());
            if (l_sysNodeFinCurcy1_Id2.isEmpty()) {
                logger.debug(logPrfx + " --- l_sysNodeFinCurcy1_Id2: null");
            }else{
                logger.debug(logPrfx + " --- l_sysNodeFinCurcy1_Id2: " + l_sysNodeFinCurcy1_Id2.get());
                sb.append(SEP0);
                sb.append(l_sysNodeFinCurcy1_Id2.get());
            }

            if (l_type1_Id2.isPresent() && l_type1_Id2.get().contains("Exch")) {

                //desc1Node2_Id.id2 optional
                Optional<UsrNodeBase> l_desc1Node2_Id = Optional.ofNullable(this.getDesc1Node1_Id())
                        .or(() -> Optional.ofNullable(dataManager.load(UsrNodeFinTxactItm.class)
                                .condition(PropertyCondition.startsWith("id2",this.getId2() + "/Y01/"))
                                .one()))
                        .or(() -> Optional.empty());

                if (l_desc1Node2_Id.isPresent()) {
                    sb.append(SEP0);
                    sb.append("->");


                    //amtDebt optional
                    Optional<BigDecimal> l_amtDebt2 = Optional.ofNullable(l_desc1Node2_Id.get().getAmtDebt());
                    if (l_amtDebt2.isEmpty()) {
                        logger.debug(logPrfx + " --- l_amtDebt2: null");
                    }else{
                        logger.debug(logPrfx + " --- l_amtDebt2: " + l_amtDebt2.get().setScale(2, RoundingMode.HALF_UP));
                        sb.append(SEP0);
                        sb.append(l_amtDebt2.get().setScale(2, RoundingMode.HALF_UP));
                    }

                    //amtCred optional
                    Optional<BigDecimal> l_amtCred2 = Optional.ofNullable(l_desc1Node2_Id.get().getAmtCred());
                    if (l_amtCred2.isEmpty()) {
                        logger.debug(logPrfx + " --- l_amtCred2: null");
                    }else{
                        logger.debug(logPrfx + " --- l_amtCred2: " + l_amtCred2.get().setScale(2, RoundingMode.HALF_UP));
                        sb.append(SEP0);
                        sb.append(l_amtCred2.get().setScale(2, RoundingMode.HALF_UP));
                    }

                    //sysNodeFinCurcy1_Id2 optional
                    Optional<String> l_sysNodeFinCurcy2_Id2 = Optional.ofNullable(l_desc1Node2_Id.get().getSysNodeFinCurcy1_Id()).map(n->n.getId2());
                    if (l_sysNodeFinCurcy2_Id2.isEmpty()) {
                        logger.debug(logPrfx + " --- l_sysNodeFinCurcy2_Id2: null");
                    }else{
                        logger.debug(logPrfx + " --- l_sysNodeFinCurcy2_Id2: " + l_sysNodeFinCurcy2_Id2.get());
                        sb.append(SEP0);
                        sb.append(l_sysNodeFinCurcy2_Id2.get());
                    }

                }
            }
        }

        //genChan1_Id.id2 optional
        Optional<String> l_genChan1_Id2 = Optional.ofNullable(this.getGenChan1_Id()).map(UsrNodeBase::getId2);
        if (l_genChan1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_genChan1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_genChan1_Id2: " + l_genChan1_Id2.get());
            sb.append(SEP0);
            sb.append("in chan [");
            sb.append(l_genChan1_Id2.get());
            sb.append("]");
        }

        //finHow1_Id.id2 optional
        Optional<String> l_finHow1_Id2 = Optional.ofNullable(this.getFinHow1_Id()).map(UsrItemBase::getId2);
        if (l_finHow1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_finHow1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_finHow1_Id2: " + l_finHow1_Id2.get());
            sb.append(SEP0);
            sb.append("via [");
            sb.append(l_finHow1_Id2.get());
            sb.append("]");
        }


        //finWhat1_Id.id2 optional
        Optional<String> l_finWhat1_Id2 = Optional.ofNullable(this.getFinWhat1_Id()).map(UsrItemBase::getId2);
        logger.debug(logPrfx + " --- l_finWhat1_Id2: " + l_finWhat1_Id2.orElse("null"));

        //whatText1 optional
        Optional<String> l_whatText1 = Optional.ofNullable(this.getWhatText1());
        logger.debug(logPrfx + " --- l_whatText1: " + l_whatText1.orElse("null"));

        if (l_finWhat1_Id2.isPresent() || l_whatText1.isPresent()) {
            sb.append(SEP0);
            sb.append("for [");
            sb.append(StringUtils.strip(l_finWhat1_Id2.orElse("") 
            + " " + l_whatText1.orElse("") ));
            sb.append("]");
        }


        //finWhy1_Id.id2 optional
        Optional<String> l_finWhy1_Id2 = Optional.ofNullable(this.getFinWhy1_Id()).map(UsrItemBase::getId2);
        logger.debug(logPrfx + " --- l_finWhy1_Id2: " + l_finWhy1_Id2.orElse("null"));

        //whyText1 optional
        Optional<String> l_whyText1 = Optional.ofNullable(this.getWhyText1());
        logger.debug(logPrfx + " --- l_whyText1: " + l_whyText1.orElse("null"));

        if (l_finWhy1_Id2.isPresent() || l_whyText1.isPresent()) {
            sb.append(SEP0);
            sb.append("for [");
            sb.append(StringUtils.strip(l_finWhy1_Id2.orElse("")
                    + " " + l_whyText1.orElse("") ));
            sb.append("]");
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

        String l_desc1_ = this.inst1;
        String l_desc1 = sb.toString();
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