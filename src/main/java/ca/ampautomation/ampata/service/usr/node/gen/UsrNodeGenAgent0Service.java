package ca.ampautomation.ampata.service.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.flowui.view.View;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("bean_UsrNodeGenAgent.Service")
public class UsrNodeGenAgent0Service extends UsrNodeBase0Service {

    public UsrNodeGenAgent0Service() {
        this.typeOfNode = UsrNodeGenAgent.class;
    }



    /**
     * <h1>Update all calculated fields</h1>
     * <p></p>
     * <h2>Updated Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>desc1</i></li>
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

        isChanged = updateDesc1(view, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(view, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(view, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(view, thisNode, updOption) || isChanged;
        isChanged = updateSortKey(view, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    /**
     * <h1>Update the <i>id2Calc</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     * <ul style="margin-left: 24px;">
     *      <li><i>name1</i> required</li>
     *      <li><i>type1_Id.id2</i> required</li>
     *      <p>switch <i>type1_Id.id2</i></p>
     *      <p>case "C"</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>genAgent1_Id.id2</i> optional</li>
     *          <li><i>genAgent2_Id.id2</i> optional</li>
     *      </ul>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.ids</i> = "P"</li>
     *          <li><i>name1</i> = "Mark Sluser"</li>
     *          <li><i>id2Calc</i> = "P::Mark Sluser"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.ids</i> = "C"</li>
     *          <li><i>name1</i> = "Sluser"</li>
     *          <li><i>genAgent1_Id.id2</i> = "P::Mark Sluser"</li>
     *          <li><i>genAgent1_Id.id2</i> = "P::Elisa Sluser"</li>
     *          <li><i>id2Calc</i> = "C::M&E Sluser"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.ids</i> = "O"</li>
     *          <li><i>name1</i> = "Gov Can"</li>
     *          <li><i>id2Calc</i> = "O::Gov Can"</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateId2Calc(@NotNull View view, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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

                //type1_Id.id2 required
                Optional<String> o_type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(UsrNodeBaseType::getId2);
                if(o_type1_Id2.isEmpty()){
                    logger.debug(logPrfx + " --- o_type1_Id2: empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- o_type1_Id2: " + o_type1_Id2.get());
                    sb.append(o_type1_Id2.get());
                }

                //name1 required
                Optional<String> o_name1 = Optional.ofNullable(thisNode.getName1());
                if(o_name1.isEmpty()){
                    logger.debug(logPrfx + " --- o_name1: null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- o_name1: " + o_name1.get());
                }

                switch (o_type1_Id2.get()){
                    case "P", "O" -> {
                        sb.append(SEP3);
                        sb.append(o_name1.get());
                    }
                    case "C" -> {
                        String a1fn = Optional.ofNullable(thisNode.getGenAgent1_Id()).map(UsrNodeGenAgent::getNameFrst).orElse("");
                        String a1ln = Optional.ofNullable(thisNode.getGenAgent1_Id()).map(UsrNodeGenAgent::getNameLast).orElse("");
                        String a2fn = Optional.ofNullable(thisNode.getGenAgent2_Id()).map(UsrNodeGenAgent::getNameFrst).orElse("");
                        String a2ln = Optional.ofNullable(thisNode.getGenAgent2_Id()).map(UsrNodeGenAgent::getNameLast).orElse("");
                        //name1 as couple's last name
                        sb.append(SEP3);
                        sb.append(a1fn.charAt(0));
                        sb.append(a1ln.equals(o_name1.get()) ? "" : "(" + a1ln + ")");
                        sb.append("&");
                        sb.append(a2fn.charAt(0));
                        sb.append(a2ln.equals(o_name1.get()) ? "" : "(" + a2ln + ")");
                        sb.append(" ");
                        sb.append(o_name1.get());
                    }
                    default -> {
                        logger.debug(logPrfx + " --- o_type1_Id2: " + o_type1_Id2.get() + " is not programmed");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }
                }

                String id2Calc_ = thisNode.getId2Calc();
                String id2Calc = sb.toString();
                logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

                if (Objects.equals(id2Calc_, id2Calc)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setId2Calc(id2Calc);
                    logger.debug(logPrfx + " --- called setId2Calc(id2Calc)");
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
     *      <li><i>name1</i> optional</li>
     *      <li><i>name2</i> optional</li>
     *      <li><i>type1_Id.id2</i> required</li>
     *      <p>switch <i>type1_Id.id2</i></p>
     *      <p>case "C"</p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>genAgent1_Id.id2</i> optional</li>
     *          <li><i>genAgent2_Id.id2</i> optional</li>
     *      </ul>
     *      <li><i>genTags1.map.id2</i> optional</li>
     * </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.ids</i> = "P"</li>
     *          <li><i>name1</i> = "Mark Sluser"</li>
     *          <li><i>name2</i> = null</li>
     *          <li><i>genTags1.map.id2</i> = null</li>
     *          <li><i>desc1</i> = "Person::Mark Sluser"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.ids</i> = "C"</li>
     *          <li><i>name1</i> = "Sluser"</li>
     *          <li><i>name2</i> = null</li>
     *          <li><i>genAgent1_Id.id2</i> = "P::Mark Sluser"</li>
     *          <li><i>genAgent1_Id.id2</i> = "P::Elisa Sluser"</li>
     *          <li><i>genTags1.map.id2</i> = null</li>
     *          <li><i>desc1</i> = "Couple::Mark & Elisa Sluser"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.ids</i> = "O"</li>
     *          <li><i>name1</i> = "Gov Can"</li>
     *          <li><i>name2</i> = "Government of Canada"</li>
     *          <li><i>genTags1.map.id2</i> = null</li>
     *          <li><i>desc1</i> = "Org::Government of Canada"</li>
     *      </ul>
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

                //type1_Id required
                Optional<String> o_type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(UsrNodeBaseType::getId2);
                if(o_type1_Id2.isEmpty()){
                    logger.debug(logPrfx + " --- o_type1_Id: empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- o_type1_Id: " + o_type1_Id2.get());
                    sb.append(o_type1_Id2.get());
                }

                //name1 required
                Optional<String> o_name1 = Optional.ofNullable(thisNode.getName1());
                if(o_name1.isEmpty()){
                    logger.debug(logPrfx + " --- o_name1: empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- o_name1: " + o_name1.get());
                }

                //name2 optional
                Optional<String> o_name2 = Optional.ofNullable(thisNode.getName2());
                if(o_name2.isEmpty()){
                    logger.debug(logPrfx + " --- o_name2: empty");
                } else {
                    logger.debug(logPrfx + " --- o_name2: " + o_name2.get());
                }

                String nm = o_name2.isPresent() ? o_name2.get()
                        : o_name1.isPresent() ? o_name1.get()
                        : "";

                switch (o_type1_Id2.get()){
                    case "P", "O" -> {
                        sb.append(SEP3);
                        sb.append(nm);
                    }
                    case "C" -> {
                        String a1fn = Optional.ofNullable(thisNode.getGenAgent1_Id()).map(UsrNodeGenAgent::getNameFrst).orElse("");
                        String a1ln = Optional.ofNullable(thisNode.getGenAgent1_Id()).map(UsrNodeGenAgent::getNameLast).orElse("");
                        String a2fn = Optional.ofNullable(thisNode.getGenAgent2_Id()).map(UsrNodeGenAgent::getNameFrst).orElse("");
                        String a2ln = Optional.ofNullable(thisNode.getGenAgent2_Id()).map(UsrNodeGenAgent::getNameLast).orElse("");

                        //nm as couple's last name
                        sb.append(SEP3);
                        sb.append(a1fn);
                        sb.append(a1ln.equals(nm) ? "" : " (" + a1ln + ")");
                        sb.append(" & ");
                        sb.append(a2fn);
                        sb.append(a2ln.equals(nm) ? "" : " (" + a2ln + ")");
                        sb.append(" ");
                        sb.append(nm);
                    }
                    default -> {
                        logger.debug(logPrfx + " --- o_type1_Id2: " + o_type1_Id2.get() + " is not programmed");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }
                }

                //genTags1.map.id2 optional
                Optional<List<UsrItemGenTag>> o_genTags1_Id = Optional.ofNullable(thisNode.getGenTags1_Id());

                if (o_genTags1_Id.isEmpty()) {
                    logger.debug(logPrfx + " --- o_genTags1_Id: empty");
                }else{

                    List<UsrItemGenTag> o_genTags1_Id_AsList =  o_genTags1_Id.orElse(new ArrayList<UsrItemGenTag>());

                    String genTags1_Id_AsStr = Optional.of(
                            o_genTags1_Id_AsList
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
                    logger.debug(logPrfx + " --- called setDesc1(desc1)");
                    isChanged = true;
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}