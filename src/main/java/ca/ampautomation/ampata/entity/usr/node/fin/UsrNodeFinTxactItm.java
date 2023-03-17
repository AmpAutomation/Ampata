package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.core.DataManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;

@JmixEntity
@Entity(name = "enty_UsrNodeFinTxactItm")
public class UsrNodeFinTxactItm extends UsrNodeBase {



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
     *          <p>If <i>parent1_Id.isPresent()</i> then</p>
     *          <ul style="margin-left: 36px;">
     *              <li><i>parent1_Id.parent1_Id.ts1.elTs</i> (tsEnd) required</li>
     *              <li><i>parent1_Id.parent1_Id.sortIdx</i> (intX) required</li>
     *          </ul>
     *          <p><i>else</p>
     *          <ul style="margin-left: 24px;">
     *              <li><i>parent1_Id.ts1.elTs</i> (tsEnd) required</li>
     *              <li><i>parent1_Id.int1</i> (intX) required</li>
     *          </ul>
     *          <ul>
     *              <li><i>parent1_Id.sortIdx</i> (intY) required</li>
     *          </ul>
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>ts1.elTs</i> (tsEnd) required</li>
     *          <li><i>int1</i> (intX) required</li>
     *          <li><i>int2</i> (intY) required</li>
     *      </ul>
     *      <ul>
     *      <li><i>sortIdx</i> (intZ) required</li>
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
    public Boolean updateInst1(DataManager dataManager){
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //parent1_Id optional
        Optional<UsrNodeBase> l_parent1_Id = Optional.ofNullable(this.parent1_Id);
        if(l_parent1_Id.isEmpty()) {
            logger.trace(logPrfx + " --- l_parent1_Id is null");
        }else{
            logger.trace(logPrfx + " --- l_parent1_Id :" + l_parent1_Id.get().getId2());
        }

        if(l_parent1_Id.isPresent()) {
            logger.debug(logPrfx + " --- l_parent1_Id.isPresent(): " + l_parent1_Id.isPresent());

            //parent1_Idx2 optional
            Optional<UsrNodeBase> l_parent1_Idx2 = Optional.ofNullable(l_parent1_Id.get().getParent1_Id());
            if(l_parent1_Idx2.isEmpty()) {
                logger.trace(logPrfx + " --- l_parent1_Idx2 is null");
            }else{
                logger.trace(logPrfx + " --- l_parent1_Idx2 :" + l_parent1_Idx2.get().getId2());
            }

            if(l_parent1_Idx2.isEmpty()) {
                logger.debug(logPrfx + " --- l_parent1_Idx2.isEmpty(): " + l_parent1_Idx2.isEmpty());

                //ts1.elTs required
                Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(l_parent1_Idx2.get().getTs1()).map(HasTmst::getElTs);
                if (l_ts1ElTs.isEmpty()) {
                    logger.debug(logPrfx + " --- l_ts1ElTs: null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
                    sb.append("D");
                    sb.append(l_ts1ElTs.get().format(frmtDt));
                }

                //sortIdx required
                Optional<Integer> l_sortIdx = Optional.ofNullable(l_parent1_Idx2.get().getSortIdx());
                if (l_sortIdx.isEmpty()) {
                    logger.debug(logPrfx + " --- l_sortIdx: null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- l_sortIdx: " + l_sortIdx);
                    sb.append(SEP1);
                    sb.append("X");
                    sb.append(l_sortIdx);
                }

            }else {
                logger.debug(logPrfx + " --- l_parent1_Idx2.isPresent(): " + l_parent1_Idx2.isPresent());

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
            }

            //sortIdx required
            Optional<Integer> l_sortIdx = Optional.ofNullable(l_parent1_Id.get().getSortIdx());
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

        }else{
            //l_parent1_Id.isPresent()
            logger.debug(logPrfx + " --- l_parent1_Id.isPresent(): " + l_parent1_Id.isPresent());

            //ts1.elTs required
            Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(this.ts1).map(HasTmst::getElTs);
            if (l_ts1ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts1ElTs: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
                sb.append(SEP1);
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


            //int2 required
            Optional<Integer> l_int2 = Optional.ofNullable(l_parent1_Id.get().getInt2());
            if (l_int2.isEmpty()) {
                logger.debug(logPrfx + " --- l_int2: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_int2: " + l_int2);
                sb.append(SEP1);
                sb.append("Y");
                sb.append(l_int2);
            }

            //sortIdx required
            Optional<Integer> l_sortIdx = Optional.ofNullable(this.sortIdx);
            if (l_sortIdx.isEmpty()) {
                logger.debug(logPrfx + " --- l_sortIdx: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_sortIdx: " + l_sortIdx);
                sb.append(SEP1);
                sb.append("Z");
                sb.append(l_sortIdx);
            }

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
     *      <li><i>debtAmt</i> required</li>
     *      <li><i>credAmt</i> required</li>
     *      <p>If <i>finStmtItm1_Id.isEmpty()</i> then</p>
     *      <ul style="margin-left: 24px;">
     *      </ul>
     *      <p><i>else</p>
     *      <ul style="margin-left: 36px;">
     *          <li><i>finStmtItm1_Id.desc1</i> optional</li>
     *          <li><i>finStmtItm1_Id.desc2</i> optional</li>
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
     *      <li><i>finStmtItm1_Id.desc1</i> = "Credit Card Payment"</li>
     *      <li><i>finStmtItm1_Id.desc2</i> = ""</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>genTags1_Id.map.id2</i> = ""</li>
     *     <li><i>desc1</i> = "Debt 125.00 for Credit Card Payment on Acct[A/CurY/RBC/Chk]"</li>
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


        //amtDebt optional
        Optional<BigDecimal> l_amtDebt = Optional.ofNullable(this.getAmtDebt());
        if (l_amtDebt.isEmpty()) {
            logger.debug(logPrfx + " --- l_amtDebt: null");
        }else{
            logger.debug(logPrfx + " --- l_amtDebt: " + l_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
            sb.append(SEP0);
            sb.append(" Debt ");
            sb.append(l_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
        }

        //amtCred optional
        Optional<BigDecimal> l_amtCred = Optional.ofNullable(this.getAmtCred());
        if (l_amtCred.isEmpty()) {
            logger.debug(logPrfx + " --- l_amtCred: null");
        }else{
            logger.debug(logPrfx + " --- l_amtCred: " + l_amtCred.get().setScale(2, RoundingMode.HALF_UP));
            sb.append(SEP0);
            sb.append(" Cred ");
            sb.append(l_amtCred.get().setScale(2, RoundingMode.HALF_UP));
        }

        //sysNodeFinCurcy1_Id2 optional
        Optional<String> l_sysNodeFinCurcy1_Id2 = Optional.ofNullable(this.getSysNodeFinCurcy1_Id()).map(n->n.getId2());
        if (l_sysNodeFinCurcy1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_sysNodeFinCurcy1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_sysNodeFinCurcy1_Id2: " + l_sysNodeFinCurcy1_Id2.get());
            sb.append(SEP0);
            sb.append(l_sysNodeFinCurcy1_Id2.get());
        }

        //finStmtItm1_Id optional
        Optional<UsrNodeFinStmtItm> l_finStmtItm1_Id = Optional.ofNullable(this.getFinStmtItm1_Id());

        if (l_finStmtItm1_Id.isEmpty()) {
            logger.debug(logPrfx + " --- l_finStmtItm1_Id: null");
        }else {
            String thisStmtItm1_Desc1 = Objects.toString(l_finStmtItm1_Id.get().getDesc1(), "");
            String thisStmtItm1_Desc2 = Objects.toString(l_finStmtItm1_Id.get().getDesc2(), "");
            List<String> l_finStmtItm1_Id_Descs_AsList = Arrays.asList(
                    thisStmtItm1_Desc1
                    , thisStmtItm1_Desc2
            );
            String l_finStmtItm1_Id_Descs_AsStr = Optional.of(l_finStmtItm1_Id_Descs_AsList
                    .stream()
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining(" "))
            ).orElse("");
            if (l_finStmtItm1_Id_Descs_AsStr.equals("")) {
                logger.debug(logPrfx + " --- l_finStmtItm1_Id_Descs_AsStr: is blank");
            }else{
                logger.debug(logPrfx + " --- l_finStmtItm1_Id_Descs_AsStr: " + l_finStmtItm1_Id_Descs_AsStr);
                sb.append(SEP0);
                sb.append(l_finStmtItm1_Id_Descs_AsStr);
            }

        }

        //finAcct1_Id2 optional
        Optional<String> l_finAcct1_Id2 = Optional.ofNullable(this.getFinAcct1_Id()).map(n->n.getId2());
        if (l_finAcct1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_finAcct1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_finAcct1_Id2: " + l_finAcct1_Id2.get());
            sb.append(SEP1);
            sb.append("to acct [");
            sb.append(l_finAcct1_Id2.get());
            sb.append("]");
        }

        //finDept1_Id2 optional
        Optional<String> l_finDept1_Id2 = Optional.ofNullable(this.getFinAcct1_Id()).map(n->n.getId2());
        if (l_finDept1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_finDept1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_finDept1_Id2: " + l_finDept1_Id2.get());
            sb.append(SEP1);
            sb.append("to dept [");
            sb.append(l_finDept1_Id2.get());
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
