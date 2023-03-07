package ca.ampautomation.ampata.entity.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JmixEntity
@Entity(name = "enty_UsrNodeGenAgent")
public class UsrNodeGenAgent extends UsrNodeBase {


    /**
     * Updates all calculated fields
     * @return Boolean if any field was changed
     *
     * Updated Fields
     *      desc1
     *      id2Calc
     *      id2Cmp
     *      id2Dup
     *
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
     * Updates the field id2Calc
     * @return Boolean if the field was changed
     *
     * Dependent Fields
     *      type1_Id.id2 required
     *      name1 required
     *      switch on type1_Id.id2
     *          case "C"
     *              genAgent1_Id.id2 optional
     *              genAgent2_Id.id2 optional
     *
     * Examples
     *      type1_Id.id2 = "P"
     *      name1 = "Mark Sluser"
     *      id2="P::Mark Sluser"
     *
     *      type1_Id.id2 = "C"
     *      name1 = "Sluser"
     *      genAgent1_Id.id2 = "P::Mark Sluser"
     *      genAgent2_Id.id2 = "P::Elisa Sluser"
     *      id2="C::M&E Sluser"
     *
     *      type1_Id.id2 = "O"
     *      name1 = "Gov Can"
     *      id2="O::Gov Can"
     *
     */
    @Override
    public Boolean updateId2Calc(DataManager dataManager){
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //type1_Id.id2 required
        Optional<String> l_type1_Id2 = Optional.ofNullable(this.type1_Id).map(UsrNodeBaseType::getId2);
        if(l_type1_Id2.isEmpty()){
            logger.debug(logPrfx + " --- l_type1_Id2: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- l_type1_Id2: " + l_type1_Id2.get());
            sb.append(l_type1_Id2.get());
        }

        //name1 required
        Optional<String> l_name1 = Optional.ofNullable(this.name1);
        if(l_name1.isEmpty()){
            logger.debug(logPrfx + " --- l_name1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- l_name1: " + l_name1.get());
        }

        switch (l_type1_Id2.get()){
            case "P", "O" -> {
                sb.append(SEP3);
                sb.append(l_name1.get());
            }
            case "C" -> {
                String a1fn = Optional.ofNullable(this.genAgent1_Id).map(UsrNodeGenAgent::getNameFrst).orElse("");
                String a1ln = Optional.ofNullable(this.genAgent1_Id).map(UsrNodeGenAgent::getNameLast).orElse("");
                String a2fn = Optional.ofNullable(this.genAgent2_Id).map(UsrNodeGenAgent::getNameFrst).orElse("");
                String a2ln = Optional.ofNullable(this.genAgent2_Id).map(UsrNodeGenAgent::getNameLast).orElse("");
                //name1 as couple's last name
                sb.append(SEP3);
                sb.append(a1fn.charAt(0));
                sb.append(a1ln.equals(l_name1.get()) ? "" : "(" + a1ln + ")");
                sb.append("&");
                sb.append(a2fn.charAt(0));
                sb.append(a2ln.equals(l_name1.get()) ? "" : "(" + a2ln + ")");
                sb.append(" ");
                sb.append(l_name1.get());
            }
            default -> {
                logger.debug(logPrfx + " --- l_type1_Id2: " + l_type1_Id2.get() + " is not programmed");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
        }

        String l_id2Calc_ = this.id2Calc;
        String l_id2Calc = sb.toString();
        logger.debug(logPrfx + " --- l_id2Calc: " + l_id2Calc);

        if (Objects.equals(l_id2Calc_, l_id2Calc)) {
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
     * Updates the field desc1
     * @return Boolean if the field was changed
     *
     * Dependent Fields
     *      type1_Id.id2 required
     *      name1 required
     *      switch on type1_Id.id2
     *          case "C"
     *              genAgent1_Id.id2 optional
     *              genAgent2_Id.id2 optional
     *      genTags1.map.id2 optional
     *
     * Examples
     *      type1_Id.id2 = "P"
     *      name1 = "Mark Sluser"
     *      name2 = null
     *      genTags1.map.id2 = ""
     *      desc1 = "Person::Mark Sluser"
     *
     *      type1_Id.id2 = "C"
     *      name1 = "Sluser"
     *      name2 = null
     *      genAgent1_Id.id2 = "P::Mark Sluser"
     *      genAgent2_Id.id2 = "P::Elisa Sluser"
     *      genTags1.map.id2 = ""
     *      id2 = "Couple::Mark & Elisa Sluser"
     *
     *      type1_Id.id2 = "O"
     *      name1 = "Gov Can"
     *      name2 = "Government of Canada"
     *      genTags1.map.id2 = ""
     *      desc1 ="Org::Government of Canada"
     *
     */
    @Override
    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //type1_Id required
        Optional<String> l_type1_Id2 = Optional.ofNullable(this.type1_Id).map(UsrNodeBaseType::getId2);
        if(l_type1_Id2.isEmpty()){
            logger.debug(logPrfx + " --- type1_Id: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- type1_Id: " + l_type1_Id2.get());
            sb.append(l_type1_Id2.get());
        }

        //name1 required
        Optional<String> l_name1 = Optional.ofNullable(this.name1);
        if(l_name1.isEmpty()){
            logger.debug(logPrfx + " --- name1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- name1: " + l_name1.get());
        }

        //name2 optional
        Optional<String> l_name2 = Optional.ofNullable(this.name2);
        if(l_name2.isEmpty()){
            logger.debug(logPrfx + " --- name2: null");
        } else {
            logger.debug(logPrfx + " --- name2: " + l_name2.get());
        }

        String nm = l_name2.isPresent() ? l_name2.get()
                : l_name1.isPresent() ? l_name1.get()
                : "";

        switch (l_type1_Id2.get()){
            case "P", "O" -> {
                sb.append(SEP3);
                sb.append(nm);
            }
            case "C" -> {
                String a1fn = Optional.ofNullable(this.genAgent1_Id).map(UsrNodeGenAgent::getNameFrst).orElse("");
                String a1ln = Optional.ofNullable(this.genAgent1_Id).map(UsrNodeGenAgent::getNameLast).orElse("");
                String a2fn = Optional.ofNullable(this.genAgent2_Id).map(UsrNodeGenAgent::getNameFrst).orElse("");
                String a2ln = Optional.ofNullable(this.genAgent2_Id).map(UsrNodeGenAgent::getNameLast).orElse("");

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
                logger.debug(logPrfx + " --- l_type1_Id2: " + l_type1_Id2.get() + " is not programmed");
                logger.trace(logPrfx + " <-- ");
                return isChanged;
            }
        }

        //genTags1.map.id2 optional
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