package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.jetbrains.annotations.NotNull;

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
@Entity(name = "enty_UsrNodeFinStmtItm")
public class UsrNodeFinStmtItm extends UsrNodeBase {



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
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateCalcVals(DataManager dataManager) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = this.updateAmtNet(dataManager) || isChanged;

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
    public Boolean updateInst1(DataManager dataManager){
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //parent1_Id.id2 optional
        Optional<UsrNodeBase> l_parent1_Id = Optional.ofNullable(this.parent1_Id);
        if(l_parent1_Id.isEmpty()) {
            logger.trace(logPrfx + " --- l_parent1_Id is null");
        }else{
            logger.trace(logPrfx + " --- l_parent1_Id :" + l_parent1_Id.get().getId2());
        }

        if(l_parent1_Id.isEmpty()) {
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
        }else{
            //l_parent1_Id.isPresent()

            //finAcct1_Id.id2 required
            Optional<String> l_finAcct1_Id2 = Optional.ofNullable(l_parent1_Id.get().getFinAcct1_Id()).map(UsrNodeFinAcct::getId2);
            if (l_finAcct1_Id2.isEmpty()) {
                logger.debug(logPrfx + " --- l_finAcct1_Id2: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_finAcct1_Id2: " + l_finAcct1_Id2.get());
                sb.append(l_finAcct1_Id2.get());
            }

        }

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
            logger.debug(logPrfx + " --- l_int1: " + l_int1.get());
            sb.append(SEP1);
            sb.append("X");
            //the length of the string will be 2 characters with leading zeros
            sb.append(String.format("%02d", l_int1.get()));
        }

        //amtNet required
        Optional<BigDecimal> l_amtNet = Optional.ofNullable(l_parent1_Id.get().getAmtNet());
        if (l_amtNet.isEmpty()) {
            logger.debug(logPrfx + " --- l_amtNet: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- l_amtNet: " + l_amtNet.get().setScale(2, RoundingMode.HALF_UP));
            sb.append(SEP1);
            sb.append("A");
            //Add the pos or neg sign
            sb.append(l_amtNet.get().signum() >= 0 ? "+" : "-");
            sb.append(l_amtNet.get().setScale(2, RoundingMode.HALF_UP));
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
     *     <li><i>desc1</i> = "Debt 125.00 on Acct[A/CurY/RBC/Chk]"</li>
     * </ul>
     * <ul style="margin-left: 24px;">
     *      <li><i>parent1_Id.isEmpty()</i> = false</li>
     *      <li><i>finStmt1_Id.finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *      <li><i>debtAmt</i> = 125.00</li>
     *      <li><i>credAmt</i> = 0</li>
     *      <li><i>finAcct1_Id.id2</i> = "A/CurY/RBC/Chk"</li>
     *     <li><i>desc1</i> = "Debt 125.00 on Acct[A/CurY/RBC/Chk]"</li>
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
        Optional<BigDecimal> l_amtDebt = Optional.ofNullable(this.amtDebt);
        if (l_amtDebt.isEmpty()) {
            logger.debug(logPrfx + " --- l_amtDebt: null");
        }else{
            logger.debug(logPrfx + " --- l_amtDebt: " + l_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
            sb.append(SEP0);
            sb.append("debt ");
            sb.append(l_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
        }

        //amtCred optional
        Optional<BigDecimal> l_amtCred = Optional.ofNullable(this.amtCred);
        if (l_amtCred.isEmpty()) {
            logger.debug(logPrfx + " --- l_amtCred: null");
        }else{
            logger.debug(logPrfx + " --- l_amtCred: " + l_amtCred.get().setScale(2, RoundingMode.HALF_UP));
            sb.append(SEP0);
            sb.append("cred ");
            sb.append(l_amtCred.get().setScale(2, RoundingMode.HALF_UP));
        }

        //parent1_Id.id2 optional
        Optional<UsrNodeBase> l_parent1_Id = Optional.ofNullable(this.parent1_Id);
        if(l_parent1_Id.isEmpty()) {
            logger.trace(logPrfx + " --- l_parent1_Id is null");
        }else{
            logger.trace(logPrfx + " --- l_parent1_Id :" + l_parent1_Id.get().getId2());
        }

        if(l_parent1_Id.isEmpty()) {

            //finAcct1_Id.id2 optional
            Optional<String> l_finAcct1_Id2 = Optional.ofNullable(this.finAcct1_Id).map(UsrNodeBase::getId2);
            if (l_finAcct1_Id2.isEmpty()) {
                logger.debug(logPrfx + " --- l_finAcct1_Id2: null");
            }else{
                logger.debug(logPrfx + " --- l_finAcct1_Id2: " + l_finAcct1_Id2.get());
                sb.append("acct [");
                sb.append(l_finAcct1_Id2.get());
                sb.append("]");
            }
        }else{
            //l_parent1_Id.isPresent()


            //finAcct1_Id.id2 optional
            Optional<String> l_finAcct1_Id2 = Optional.ofNullable(l_parent1_Id.get().getFinAcct1_Id()).map(UsrNodeBase::getId2);
            if (l_finAcct1_Id2.isEmpty()) {
                logger.debug(logPrfx + " --- l_finAcct1_Id2: null");
            }else{
                logger.debug(logPrfx + " --- l_finAcct1_Id2: " + l_finAcct1_Id2.get());
                sb.append("acct [");
                sb.append(l_finAcct1_Id2.get());
                sb.append("]");
            }

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



    @Override
    public Boolean updateAmtNet(DataManager dataManager) {
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        BigDecimal l_amtNet = BigDecimal.ZERO;

        //finAcct1_Id_Type1_Id required
        Optional<UsrNodeBaseType> l_finAcct1_Id_Type1_Id = Optional.ofNullable(this.getFinAcct1_Id())
                .map(n->n.getType1_Id());

        if (l_finAcct1_Id_Type1_Id.isEmpty()){
            logger.debug(logPrfx + " --- l_finAcct1_Id_Type1_Id: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;

        }else{

            Boolean balIncOnDebt = l_finAcct1_Id_Type1_Id.get().getBalIncOnDebt();
            Boolean balIncOnCred = l_finAcct1_Id_Type1_Id.get().getBalIncOnCred();

            //amtDebt optional
            Optional<BigDecimal> l_amtDebt = Optional.ofNullable(this.amtDebt);
            if (l_amtDebt.isEmpty()) {
                logger.debug(logPrfx + " --- l_amtDebt: null");
            }else{
                logger.debug(logPrfx + " --- l_amtDebt: " + l_amtDebt.get().setScale(2, RoundingMode.HALF_UP));

                if (balIncOnDebt == null || !balIncOnDebt) {
                    l_amtNet = l_amtNet.subtract(l_amtDebt.get());
                } else {
                    l_amtNet = l_amtNet.add(l_amtDebt.get());
                }
            }

            //amtCred optional
            Optional<BigDecimal> l_amtCred = Optional.ofNullable(this.amtCred);
            if (l_amtCred.isEmpty()) {
                logger.debug(logPrfx + " --- l_amtCred: null");
            }else{
                logger.debug(logPrfx + " --- l_amtCred: " + l_amtCred.get().setScale(2, RoundingMode.HALF_UP));

                if (balIncOnCred == null || !balIncOnCred) {
                    l_amtNet = l_amtNet.subtract(l_amtCred.get());
                } else {
                    l_amtNet = l_amtNet.add(l_amtCred.get());
                }
            }

        }

        BigDecimal l_amtNet_ = this.amtNet;
        logger.debug(logPrfx + " --- l_amtNet: " + l_amtNet.setScale(2, RoundingMode.HALF_UP));

        if (Objects.equals(l_amtNet_, l_amtNet)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setAmtNet(l_amtNet);
            logger.debug(logPrfx + " --- called setAmtNet(l_amtNet)");
            isChanged = true;
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



}