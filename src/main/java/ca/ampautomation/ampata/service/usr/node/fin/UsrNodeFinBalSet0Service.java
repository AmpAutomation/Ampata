package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSet;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.ui.screen.Screen;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Component("bean_UsrNodeFinBalSet.Service")
public class UsrNodeFinBalSet0Service extends UsrNodeBase0Service {

    public UsrNodeFinBalSet0Service() {
        this.typeOfNode = UsrNodeFinBalSet.class;
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
     * <h1>Update the <i>id2Calc</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> required</li>
     *     </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "R=Y2021/_;S=All;D=Gen"</li>
     *          <li><i>id2Calc</i> = "R=Y2021/_;S=All;D=Gen"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "R=Y2021/_;S=All;D="</li>
     *          <li><i>id2Calc</i> = "R=Y2021/_;S=All;D="</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateId2Calc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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

                //name1 required
                Optional<String> o_name1 = Optional.ofNullable(thisNode.getName1());
                if(o_name1.isEmpty()) {
                    logger.trace(logPrfx + " --- o_name1: is empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.trace(logPrfx + " --- o_name1: " + o_name1.get());
                    sb.append(thisNode.getName1());
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
     *      <li><i>type1_Id.id2</i> required</li>
     *      <p>switch <i>type1_Id.id2</i></p>
     *      <p>case "Rng"</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>ts1.elTs</i> (tsBeg) required</li>
     *          <li><i>ts2.elTs</i> (tsEnd) required</li>
     *          <li><i>txt1</i> (txtSet) required</li>
     *          <li><i>finDept1_Id.id2</i> optional</li>
     *      </ul>
     *      <p>case "Rng-Given-Year"
     *      <br>case "Rng-Given-Month"
     *      <br>case "Rng-Given-Week"
     *      <br>case "Rng-Given-Day"</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>ts1.elTs</i> (tsInRng) required</li>
     *          <li><i>txt1</i> (txtSet) required</li>
     *          <li><i>finDept1_Id.id2</i> optional</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.id2</i> = "Rng"</li>
     *          <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *          <li><i>ts2.elTs</i> = "2023-02-28"</li>
     *          <li><i>txt1</i> = "All"</li>
     *          <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *          <li><i>inst1</i> = "B=2023-01-01;E=2023-02-28;S=All;D=Gen"</li>
     *      </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Year"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>inst1</i> = "R=Y2023/_;S=All;D=Gen"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Week"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>inst1</i> = "R=Y2023/W01/_;S=All;D=Gen"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Month"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>inst1</i> = "R=Y2023/M01/_;S=All;D=Gen"</li>
     * </ul>
     * <p></p>
     * <ul style="margin-left: 24px;">
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Day"</li>
     *      <li><i>ts1.elTs</i> = "2023-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>inst1</i> = "R=Y2023/M01/D01;S=All;D=Gen"</li>
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

                //type1_Id.id2 required
                Optional<String> l_type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(UsrNodeBaseType::getId2);
                if(l_type1_Id2.isEmpty()) {
                    logger.trace(logPrfx + " --- l_type1_Id2: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.trace(logPrfx + " --- l_type1_Id2 :" + l_type1_Id2.get());
                }

                //ts1.elTs required
                Optional<LocalDateTime> l_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
                if (l_ts1_ElTs.isEmpty()) {
                    logger.debug(logPrfx + " --- l_ts1_ElTs: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- l_ts1_ElTs: " + l_ts1_ElTs.get().format(FRMT_DT));
                }

                //txt1 required
                Optional<String> l_txt1 = Optional.ofNullable(thisNode.getTxt1());
                if (l_txt1.isEmpty()) {
                    logger.debug(logPrfx + " --- l_txt1: is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- l_txt1: " + l_txt1.get());
                }

                switch (l_type1_Id2.get()){
                    case "Rng"->{
                        //ex. "B=2023-01-01;E=2023-02-28;D=Gen"

                        //ts2.elTs required
                        Optional<LocalDateTime> l_ts2_ElTs = Optional.ofNullable(thisNode.getTs2()).map(HasTmst::getElTs);
                        if (l_ts2_ElTs.isEmpty()) {
                            logger.debug(logPrfx + " --- l_ts2_ElTs: is null");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }else{
                            logger.debug(logPrfx + " --- l_ts2_ElTs: " + l_ts2_ElTs.get().format(FRMT_DT));
                        }

                        //Ts1.elTs (Beg)
                        sb.append("B=");
                        sb.append(l_ts1_ElTs.get().format(FRMT_DT));
                        //Ts1.elTs (Beg)
                        sb.append(SEP2);
                        sb.append("E=");
                        sb.append(l_ts2_ElTs.get().format(FRMT_DT));
                        //Set
                        sb.append(SEP2);
                        sb.append("S=");
                        sb.append(l_txt1.get());

                    }
                    case "Rng-Given-Year"->{
                        //ex. "R=Y2023/_;S=All;D=Gen"
                        //Range
                        sb.append("R=");
                        sb.append("Y").append(l_ts1_ElTs.get().getYear());
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
                        sb.append("Y").append(l_ts1_ElTs.get().getYear());
                        sb.append(SEP1);
                        sb.append("_");
                        //if necessary add up to 2 leading zeros if
                        WeekFields weekFields = WeekFields.of(Locale.getDefault());
                        int weekNum = l_ts1_ElTs.get().get(weekFields.weekOfWeekBasedYear());
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
                        sb.append("Y").append(l_ts1_ElTs.get().getYear());
                        sb.append(SEP1);
                        sb.append("_");
                        //if necessary add up to 2 leading zeros if
                        sb.append("M").append(String.format("%2d", l_ts1_ElTs.get().getMonthValue()));
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
                        sb.append("Y").append(l_ts1_ElTs.get().getYear());
                        sb.append(SEP1);
                        sb.append("_");
                        //if necessary add up to 2 leading zeros if
                        sb.append("M").append(String.format("%2d", l_ts1_ElTs.get().getMonthValue()));
                        sb.append(SEP1);
                        //if necessary add up to 2 leading zeros if
                        sb.append("D").append(String.format("%2d", l_ts1_ElTs.get().getDayOfMonth()));
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
                Optional<String> finDept1_Id2 = Optional.ofNullable(thisNode.getFinDept1_Id()).map(UsrNodeBase::getId2);
                if (finDept1_Id2.isEmpty()){
                    logger.trace(logPrfx + " --- finDept1_Id2: is null");
                    sb.append(SEP2);
                    sb.append("D=");
                }else{
                    logger.trace(logPrfx + " --- finDept1_Id2: " + finDept1_Id2.get());
                    sb.append(SEP2);
                    sb.append("D=");
                    sb.append(finDept1_Id2);
                }

                String id2Calc_ = thisNode.getId2Calc();
                String id2Calc = sb.toString();
                logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

                if (Objects.equals(id2Calc_, id2Calc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else {
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
     * <h1>Update the desc1 field</h1>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <li><i>status</i> optional</li>
     *          <p style="margin-left: 36px;"> switch <i>type1_Id.id2</i></p>
     *          <p style="margin-left: 36px;"> case "Rng"</p>
     *              <ul style="margin-left: 60px;">
     *                  <li><i>type1_Id.id2</i> required</li>
     *                  <li><i>ts1.elTs</i> (tsBeg) optional</li>
     *                  <li><i>ts2.elTs</i> (tsEnd) optional</li>
     *                  <li><i>finDept1_Id.id2</i> optional</li>
     *              </ul>
     *          <p style="margin-left: 36px;">
     *              case "Rng-Given-Year"
     *          <br>case "Rng-Given-Month"
     *          <br>case "Rng-Given-Week"
     *          <br>case "Rng-Given-Day"</p>
     *              <ul style="margin-left: 60px;">
     *                  <li><i>type1_Id.id2</i> required</li>
     *                  <li><i>ts1.elTs</i> (tsInRng) optional</li>
     *                  <li><i>txt1</i> optional</li>
     *                  <li><i>finDept1_Id.id2</i> optional</li>
     *              </ul>
     *      <li>genTags1_Id.map.id2 optional</li>
     * <p></p>
     * <h2>Examples</h2>
     *      <li><i>status</i> = ""</li>
     *      <li><i>type1_Id.id2</i> = "Rng"</li>
     *      <li><i>ts1.elTs</i> (Beg) = "2018-01-01"</li>
     *      <li><i>ts2.elTs</i> (End) = "2018-12-31"</li>
     *      <li><i>finDept1_Id.id2</i> = "Gen"</li>
     *      <li><i>genTags1_Id.map.id2</i> = ""</li>
     *      <li><i>desc1</i> = "Rng-Given-Year begDate 2018-01-01 endDate 2018-12-31 on dept [Gen]"</li>
     * <p></p>
     *      <li>status = ""
     *      <li><i>type1_Id.id2</i> = "Rng-Given-Year"</li>
     *      <li>ts1.elTs (tsInRng) = "2018-01-01"</li>
     *      <li><i>txt1</i> = "All"</li>
     *      <li>finDept1_Id.id2 = null</li>
     *      <li>genTags1_Id.map.id2 = ""</li>
     *      <li>desc1 = "Rng-Given-Year begDate 2018-01-01 endDate 2018-12-31"</li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateDesc1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //status optional
        Optional<String> status = Optional.ofNullable(thisNode.getStatus());
        if (status.isEmpty()) {
            logger.debug(logPrfx + " --- status: is null");
        }else{
            logger.debug(logPrfx + " --- status: " + status.get());
            sb.append(status.get());
        }

        //type1_Id.id2 required
        Optional<String> type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(UsrNodeBaseType::getId2);
        if(type1_Id2.isEmpty()){
            logger.debug(logPrfx + " --- type1_Id2: is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- type1_Id2: " + type1_Id2.get());
            sb.append(SEP0);
            sb.append(type1_Id2.get());
        }

        //ts1.elTs optional
        Optional<LocalDateTime> ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(HasTmst::getElTs);
        if (ts1_ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- ts1_ElTs: is null");
        }else{
            logger.debug(logPrfx + " --- ts1_ElTs: " + ts1_ElTs.get().format(FRMT_DT));
        }

        //ts2.elTs optional
        Optional<LocalDateTime> ts2_ElTs = Optional.ofNullable(thisNode.getTs2()).map(HasTmst::getElTs);
        if (ts1_ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- ts2_ElTs: is null");
        }else{
            logger.debug(logPrfx + " --- ts2_ElTs: " + ts2_ElTs.get().format(FRMT_DT));
        }

        //txt1 optional
        Optional<String> txt1 = Optional.ofNullable(thisNode.getTxt1());
        if (txt1.isEmpty()) {
            logger.debug(logPrfx + " --- txt1: is null");
        }else{
            logger.debug(logPrfx + " --- txt1: " + txt1.get());
        }

        switch (type1_Id2.get()){
            case "Rng-Given-Year"->{
                //ex. "R=Y2023/_;S=All;D=Gen"
                //Range
                sb.append(SEP0);
                sb.append("Range ");
                sb.append("Y").append(ts1_ElTs.get().getYear());
                sb.append(SEP1);
                sb.append("_");
                //Set
                sb.append(SEP0);
                sb.append("Set ");
                sb.append(txt1.get());

            }
            case "Rng-Given-Week"->{
                //ex. "R=Y2023/W01/_;S=All;D=Gen"
                //Range
                sb.append(SEP0);
                sb.append("Range ");
                sb.append("Y").append(ts1_ElTs.get().getYear());
                sb.append(SEP1);
                sb.append("_");
                //if necessary add up to 2 leading zeros if
                WeekFields weekFields = WeekFields.of(Locale.getDefault());
                int weekNum = ts1_ElTs.get().get(weekFields.weekOfWeekBasedYear());
                sb.append("W").append(String.format("%2d", weekNum));
                sb.append(SEP1);
                sb.append("_");
                //Set
                sb.append(SEP0);
                sb.append("Set ");
                sb.append(txt1.get());

            }
            case "Rng-Given-Month"->{
                //ex. "R=Y2023/M01/_;S=All;D=Gen"
                //Range
                sb.append(SEP0);
                sb.append("Range ");
                sb.append("Y").append(ts1_ElTs.get().getYear());
                sb.append(SEP1);
                sb.append("_");
                //if necessary add up to 2 leading zeros if
                sb.append("M").append(String.format("%2d", ts1_ElTs.get().getMonthValue()));
                sb.append(SEP1);
                sb.append("_");
                sb.append(SEP0);
                sb.append("Set ");
                sb.append(txt1.get());

            }
            case "Rng-Given-Day"->{
                //ex. "R=Y2023/M01/D01;S=All;D=Gen"
                //Range
                sb.append(SEP0);
                sb.append("Range ");
                sb.append("Y").append(ts1_ElTs.get().getYear());
                sb.append(SEP1);
                sb.append("_");
                //if necessary add up to 2 leading zeros if
                sb.append("M").append(String.format("%2d", ts1_ElTs.get().getMonthValue()));
                sb.append(SEP1);
                //if necessary add up to 2 leading zeros if
                sb.append("D").append(String.format("%2d", ts1_ElTs.get().getDayOfMonth()));
                sb.append(SEP1);
                sb.append("_");
                //Set
                sb.append(SEP0);
                sb.append("Set ");
                sb.append(txt1.get());

            }
            default->{
                logger.trace(logPrfx + " --- case type1_Id2:" + type1_Id2 + " not programmed");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
        }

        //finDept1_Id.id2 optional
        Optional<String> finDept1_Id2 = Optional.ofNullable(thisNode.getFinDept1_Id()).map(UsrNodeBase::getId2);
        if (finDept1_Id2.isEmpty()){
            logger.trace(logPrfx + " --- type1_Id2: is null");
        }else{
            logger.trace(logPrfx + " --- type1_Id2: " + finDept1_Id2.get());
            sb.append(SEP0);
            sb.append("Dept [");
            sb.append(finDept1_Id2.get());
            sb.append("]");
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

}