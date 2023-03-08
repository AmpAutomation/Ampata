package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import ca.ampautomation.ampata.entity.HasTmst;

@JmixEntity
@Entity(name = "enty_UsrNodeFinBal")
public class UsrNodeFinBal extends UsrNodeBase {

    /**
     * <h1>Update all calculated fields</h1>
     * <p></p>
     * <h2>Updated Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>desc1</i></li>
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

        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

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
    public Boolean updateId2Calc(DataManager dataManager){
        String logPrfx = "updateId2Calc";
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
        }

        Optional<String> l_finBalSet1_Id2 = Optional.ofNullable(this.finBalSet1_Id).map(UsrNodeFinBalSet::getId2);
        if (l_finBalSet1_Id2.isEmpty()) {

            //ts1.elTs (tsBeg) required
            Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(this.ts1).map(HasTmst::getElTs);
            if (l_ts1ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts1ElTs: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
                sb.append("B=").append(l_ts1ElTs.get().format(frmtDt));
            }

            //ts2.elTs (tsEnd) required
            Optional<LocalDateTime> l_ts2ElTs = Optional.ofNullable(this.ts2).map(HasTmst::getElTs);
            if (l_ts2ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts2ElTs: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_ts2ElTs: " + l_ts2ElTs.get().format(frmtDt));
                sb.append(SEP2);
                sb.append("E=").append(l_ts2ElTs.get().format(frmtDt));
            }

            //finDept1_Id.id2 optional
            Optional<String> l_finDept1_Id2 = Optional.ofNullable(this.finDept1_Id).map(UsrNodeFinDept::getId2);
            if(l_finDept1_Id2.isEmpty()){
                logger.debug(logPrfx + " --- l_finDept1_Id2: null");
                sb.append(SEP2);
                sb.append("D=");
            }else{
                logger.debug(logPrfx + " --- l_finDept1_Id2: " + l_finDept1_Id2.get());
                sb.append(SEP2);
                sb.append("D=").append(l_finDept1_Id2.get());
            }

            //finAcct1_Id.id2
            sb.append(SEP2);
            sb.append("A=").append(l_finAcct1_Id2.get());

        } else {
            //finBalSet1_Id.id2
            sb.append(l_finBalSet1_Id2.get());
            //finAcct1
            sb.append(SEP2);
            sb.append("A=").append(l_finAcct1_Id2.get());
        }

        String l_id2Calc_ = this.id2Calc;
        String l_id2Calc = sb.toString();
        logger.debug(logPrfx + " --- l_id2Calc: " + l_id2Calc);

        if (Objects.equals(l_id2Calc_, l_id2Calc)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setId2Calc(l_id2Calc);
            logger.debug(logPrfx + " --- called setId2Calc(l_id2Calc)");
            isChanged = true;
        }

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
    public Boolean updateInst1(DataManager dataManager) {
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
        
        if(l_parent1_Id.isEmpty()){

            //type1_Id.id2 required
            Optional<String> l_type1_Id2 = Optional.ofNullable(this.type1_Id).map(UsrNodeBaseType::getId2);
            if(l_type1_Id2.isEmpty()) {
                logger.trace(logPrfx + " --- l_type1_Id2 is null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.trace(logPrfx + " --- l_type1_Id2 :" + l_type1_Id2.get());
            }

            //ts1.elTs required
            Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(this.ts1).map(HasTmst::getElTs);
            if (l_ts1ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts1ElTs: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
            }

            //txt1 required
            Optional<String> l_txt1 = Optional.ofNullable(this.txt1);
            if (l_txt1.isEmpty()) {
                logger.debug(logPrfx + " --- l_txt1: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_txt1: " + l_txt1.get());
            }

            switch (l_type1_Id2.get()){
                case "Rng-Given-Year"->{
                    //ex. "R=Y2023/_;S=All;D=Gen"
                    //Range
                    sb.append("R=");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP2);
                    sb.append("S=");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Week"->{
                    //ex. "R=Y2023/W01/_;S=All;D=Gen"
                    //Range
                    sb.append("R=");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    WeekFields weekFields = WeekFields.of(Locale.getDefault());
                    int weekNum = l_ts1ElTs.get().get(weekFields.weekOfWeekBasedYear());
                    sb.append("W").append(String.format("%2d", weekNum));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP2);
                    sb.append("S=");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Month"->{
                    //ex. "R=Y2023/M01/_;S=All;D=Gen"
                    //Range
                    sb.append("R=");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    sb.append("M").append(String.format("%2d", l_ts1ElTs.get().getMonthValue()));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP2);
                    sb.append("S=");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Day"->{
                    //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                    //Range
                    sb.append("R=");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    sb.append("M").append(String.format("%2d", l_ts1ElTs.get().getMonthValue()));
                    sb.append(SEP1);
                    //if necessary add up to 2 leading zeros if
                    sb.append("D").append(String.format("%2d", l_ts1ElTs.get().getDayOfMonth()));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP2);
                    sb.append("S=");
                    sb.append(l_txt1.get());

                }
                default->{
                    logger.trace(logPrfx + " --- case l_type1_Id2:" + l_type1_Id2 + " not programmed");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }
            }

            //finDept1_Id.id2 optional
            Optional<String> l_finDept1_Id2 = Optional.ofNullable(this.finDept1_Id).map(UsrNodeBase::getId2);
            if (l_finDept1_Id2.isEmpty()){
                logger.trace(logPrfx + " --- l_finDept1_Id2 is null");
                sb.append(SEP2);
                sb.append("D=");
            }else{
                logger.trace(logPrfx + " --- l_finDept1_Id2: " + l_finDept1_Id2.get());
                sb.append(SEP2);
                sb.append("D=");
                sb.append(l_finDept1_Id2);
            }
            
        }else{
            //l_parent1_Id.isPresent()

            //type1_Id.id2 required
            Optional<String> l_type1_Id2 = Optional.ofNullable(l_parent1_Id.get().getType1_Id()).map(UsrNodeBaseType::getId2);
            if(l_type1_Id2.isEmpty()) {
                logger.trace(logPrfx + " --- l_type1_Id2 is null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.trace(logPrfx + " --- l_type1_Id2 :" + l_type1_Id2.get());
            }

            //ts1.elTs required
            Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(l_parent1_Id.get().getTs1()).map(HasTmst::getElTs);
            if (l_ts1ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts1ElTs: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
            }

            //txt1 required
            Optional<String> l_txt1 = Optional.ofNullable(l_parent1_Id.get().getTxt1());
            if (l_txt1.isEmpty()) {
                logger.debug(logPrfx + " --- l_txt1: null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }else{
                logger.debug(logPrfx + " --- l_txt1: " + l_txt1.get());
            }

            switch (l_type1_Id2.get()){
                case "Rng-Given-Year"->{
                    //ex. "R=Y2023/_;S=All;D=Gen"
                    //Range
                    sb.append("R=");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP2);
                    sb.append("S=");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Week"->{
                    //ex. "R=Y2023/W01/_;S=All;D=Gen"
                    //Range
                    sb.append("R=");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    WeekFields weekFields = WeekFields.of(Locale.getDefault());
                    int weekNum = l_ts1ElTs.get().get(weekFields.weekOfWeekBasedYear());
                    sb.append("W").append(String.format("%2d", weekNum));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP2);
                    sb.append("S=");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Month"->{
                    //ex. "R=Y2023/M01/_;S=All;D=Gen"
                    //Range
                    sb.append("R=");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    sb.append("M").append(String.format("%2d", l_ts1ElTs.get().getMonthValue()));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP2);
                    sb.append("S=");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Day"->{
                    //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                    //Range
                    sb.append("R=");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    sb.append("M").append(String.format("%2d", l_ts1ElTs.get().getMonthValue()));
                    sb.append(SEP1);
                    //if necessary add up to 2 leading zeros if
                    sb.append("D").append(String.format("%2d", l_ts1ElTs.get().getDayOfMonth()));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP2);
                    sb.append("S=");
                    sb.append(l_txt1.get());

                }
                default->{
                    logger.trace(logPrfx + " --- case l_type1_Id2:" + l_type1_Id2 + " not programmed");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }
            }

            //finDept1_Id.id2 optional
            Optional<String> l_finDept1_Id2 = Optional.ofNullable(l_parent1_Id.get().getFinDept1_Id()).map(UsrNodeBase::getId2);
            if (l_finDept1_Id2.isEmpty()){
                logger.trace(logPrfx + " --- l_finDept1_Id2 is null");
                sb.append(SEP2);
                sb.append("D=");
            }else{
                logger.trace(logPrfx + " --- l_finDept1_Id2: " + l_finDept1_Id2.get());
                sb.append(SEP2);
                sb.append("D=");
                sb.append(l_finDept1_Id2);
            }
            
        }

        //finAcct1_Id.id2 optional
        Optional<String> l_finAcct1_Id2 = Optional.ofNullable(this.finAcct1_Id).map(UsrNodeBase::getId2);
        if (l_finAcct1_Id2.isEmpty()){
            logger.trace(logPrfx + " --- l_finAcct1_Id2 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.trace(logPrfx + " --- l_finAcct1_Id2: " + l_finAcct1_Id2.get());
            sb.append(SEP2);
            sb.append("D=");
            sb.append(l_finAcct1_Id2);
        }

        String l_inst1_ = this.inst1;
        String l_inst1 = sb.toString();
        logger.debug(logPrfx + " --- l_inst1: " + l_inst1);

        if (!Objects.equals(l_inst1_, l_inst1)) {
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
    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
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

        if(l_parent1_Id.isEmpty()){

            //type1_Id.id2 optional
            Optional<String> l_type1_Id2 = Optional.ofNullable(this.type1_Id).map(UsrNodeBaseType::getId2);
            if(l_type1_Id2.isEmpty()) {
                logger.trace(logPrfx + " --- l_type1_Id2 is null");
            }else{
                logger.trace(logPrfx + " --- l_type1_Id2 :" + l_type1_Id2.get());
                sb.append(l_type1_Id2);
            }

            //ts1.elTs optional
            Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(this.ts1).map(HasTmst::getElTs);
            if (l_ts1ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts1ElTs: null");
            }else{
                logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
            }

            //txt1 optional
            Optional<String> l_txt1 = Optional.ofNullable(this.txt1);
            if (l_txt1.isEmpty()) {
                logger.debug(logPrfx + " --- l_txt1: null");
            }else{
                logger.debug(logPrfx + " --- l_txt1: " + l_txt1.get());
            }

            switch (l_type1_Id2.get()){
                case "Rng-Given-Year"->{
                    //ex. "R=Y2023/_;S=All;D=Gen"
                    //Range
                    sb.append(SEP0);
                    sb.append("Range ");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP0);
                    sb.append("Set ");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Week"->{
                    //ex. "R=Y2023/W01/_;S=All;D=Gen"
                    //Range
                    sb.append(SEP0);
                    sb.append("Range ");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    WeekFields weekFields = WeekFields.of(Locale.getDefault());
                    int weekNum = l_ts1ElTs.get().get(weekFields.weekOfWeekBasedYear());
                    sb.append("W").append(String.format("%2d", weekNum));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP0);
                    sb.append("Set ");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Month"->{
                    //ex. "R=Y2023/M01/_;S=All;D=Gen"
                    //Range
                    sb.append(SEP0);
                    sb.append("Range ");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    sb.append("M").append(String.format("%2d", l_ts1ElTs.get().getMonthValue()));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP0);
                    sb.append("Set ");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Day"->{
                    //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                    //Range
                    sb.append(SEP0);
                    sb.append("Range ");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    sb.append("M").append(String.format("%2d", l_ts1ElTs.get().getMonthValue()));
                    sb.append(SEP1);
                    //if necessary add up to 2 leading zeros if
                    sb.append("D").append(String.format("%2d", l_ts1ElTs.get().getDayOfMonth()));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP0);
                    sb.append("Set ");
                    sb.append(l_txt1.get());

                }
                default->{
                    logger.trace(logPrfx + " --- case l_type1_Id2:" + l_type1_Id2 + " not programmed");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }
            }

            //finDept1_Id.id2 optional
            Optional<String> l_finDept1_Id2 = Optional.ofNullable(this.finDept1_Id).map(UsrNodeBase::getId2);
            if (l_finDept1_Id2.isEmpty()){
                logger.trace(logPrfx + " --- l_finDept1_Id2 is null");
            }else{
                logger.trace(logPrfx + " --- l_finDept1_Id2: " + l_finDept1_Id2.get());
                sb.append(SEP0);
                sb.append("Dept [");
                sb.append(l_finDept1_Id2);
                sb.append("]");
            }

        }else {
            //l_parent1_Id.isPresent()

            //type1_Id.id2 required
            Optional<String> l_type1_Id2 = Optional.ofNullable(l_parent1_Id.get().getType1_Id()).map(UsrNodeBaseType::getId2);
            if (l_type1_Id2.isEmpty()) {
                logger.trace(logPrfx + " --- l_type1_Id2 is null");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            } else {
                logger.trace(logPrfx + " --- l_type1_Id2 :" + l_type1_Id2.get());
                sb.append(l_type1_Id2);
            }

            //ts1.elTs optional
            Optional<LocalDateTime> l_ts1ElTs = Optional.ofNullable(l_parent1_Id.get().getTs1()).map(HasTmst::getElTs);
            if (l_ts1ElTs.isEmpty()) {
                logger.debug(logPrfx + " --- l_ts1ElTs: null");
            } else {
                logger.debug(logPrfx + " --- l_ts1ElTs: " + l_ts1ElTs.get().format(frmtDt));
            }

            //txt1 optional
            Optional<String> l_txt1 = Optional.ofNullable(l_parent1_Id.get().getTxt1());
            if (l_txt1.isEmpty()) {
                logger.debug(logPrfx + " --- l_txt1: null");
            } else {
                logger.debug(logPrfx + " --- l_txt1: " + l_txt1.get());
            }

            switch (l_type1_Id2.get()) {
                case "Rng-Given-Year" -> {
                    //ex. "R=Y2023/_;S=All;D=Gen"
                    //Range
                    sb.append(SEP0);
                    sb.append("Range ");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP0);
                    sb.append("Set ");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Week" -> {
                    //ex. "R=Y2023/W01/_;S=All;D=Gen"
                    //Range
                    sb.append(SEP0);
                    sb.append("Range ");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    WeekFields weekFields = WeekFields.of(Locale.getDefault());
                    int weekNum = l_ts1ElTs.get().get(weekFields.weekOfWeekBasedYear());
                    sb.append("W").append(String.format("%2d", weekNum));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP0);
                    sb.append("Set ");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Month" -> {
                    //ex. "R=Y2023/M01/_;S=All;D=Gen"
                    //Range
                    sb.append(SEP0);
                    sb.append("Range ");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    sb.append("M").append(String.format("%2d", l_ts1ElTs.get().getMonthValue()));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP0);
                    sb.append("Set ");
                    sb.append(l_txt1.get());

                }
                case "Rng-Given-Day" -> {
                    //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                    //Range
                    sb.append(SEP0);
                    sb.append("Range ");
                    sb.append("Y").append(l_ts1ElTs.get().getYear());
                    sb.append(SEP1);
                    sb.append("_");
                    //if necessary add up to 2 leading zeros if
                    sb.append("M").append(String.format("%2d", l_ts1ElTs.get().getMonthValue()));
                    sb.append(SEP1);
                    //if necessary add up to 2 leading zeros if
                    sb.append("D").append(String.format("%2d", l_ts1ElTs.get().getDayOfMonth()));
                    sb.append(SEP1);
                    sb.append("_");
                    //Set
                    sb.append(SEP0);
                    sb.append("Set ");
                    sb.append(l_txt1.get());

                }
                default -> {
                    logger.trace(logPrfx + " --- case l_type1_Id2:" + l_type1_Id2 + " not programmed");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }
            }

            //finDept1_Id.id2 optional
            Optional<String> l_finDept1_Id2 = Optional.ofNullable(this.finDept1_Id).map(UsrNodeBase::getId2);
            if (l_finDept1_Id2.isEmpty()){
                logger.trace(logPrfx + " --- l_finDept1_Id2 is null");
            }else{
                logger.trace(logPrfx + " --- l_finDept1_Id2: " + l_finDept1_Id2.get());
                sb.append(SEP0);
                sb.append("Dept [");
                sb.append(l_finDept1_Id2);
                sb.append("]");
            }
        }

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
    public Boolean updateTs1Deps(DataManager dataManager) {
        String logPrfx = "updateTs1Deps";
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