package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;
import java.util.Optional;

@JmixEntity
@Entity(name = "enty_UsrNodeFinAcct")
public class UsrNodeFinAcct extends UsrNodeBase {


    /**
     * <h1>Update all calculated fields</h1>
     * <p></p>
     * <h2>Updated Fields</h2>
     *      <ul style="margin-left: 24px;">
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

        isChanged = this.updateName1(dataManager) || isChanged;

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
     *      <li><i>name1</i> required</li>
     *      <li><i>parent1_Id.id2</i> optional</li>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "E"</li>
     *          <li><i>parent1_Id.id2</i> = null</li>
     *          <li><i>id2Calc</i> = "E"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "Groceries"</li>
     *          <li><i>parent1_Id.id2</i> = "E"</li>
     *          <li><i>id2Calc</i> = "E/Groceries</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateId2Calc(DataManager dataManager) {
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        StringBuilder sb = new StringBuilder();

        //name1 required
        Optional<String> l_name1 = Optional.ofNullable(this.name1);
        if(l_name1.isEmpty()) {
            logger.trace(logPrfx + " --- name1 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.trace(logPrfx + " --- name1 :" + l_name1.get());
        }

        //parent1_Id.id2 optional
        Optional<String> l_parent1_Id2 = Optional.ofNullable(this.parent1_Id).map(UsrNodeBase::getId2);
        Optional<UsrNodeBase> l_parent1_Id = Optional.ofNullable(this.parent1_Id);

        // parent1    name1   id2
        // p        n       p/n
        // p        null    p/<NULL>
        // null     n       n
        // null     null    <NULL>
        if(l_parent1_Id2.isEmpty()){
            sb.append(l_name1.orElse("<NULL>"));

        }else{
            sb.append(l_parent1_Id2.get());
            sb.append(SEP1);
            sb.append(l_name1.orElse("<NULL>"));
        }

        String l_id2Calc_ = this.id2Calc;
        String l_id2Calc = sb.toString();
        logger.debug(logPrfx + " --- l_id2Calc:" + l_id2Calc);

        if (Objects.equals(l_id2Calc_, l_id2Calc)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setId2Calc(l_id2Calc);
            logger.debug(logPrfx + " --- id2Calc:" + l_id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>name1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <li><i>inst1</i> required</li>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>inst1</i> = "E"</li>
     *          <li><i>id2Calc</i> = "E"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>inst1</i> = "Groceries"</li>
     *          <li><i>id2Calc</i> = "Groceries</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    @Override
    public Boolean updateName1(DataManager dataManager) {
        String logPrfx = "updateName1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //inst1 required
        Optional<String> l_inst1 = Optional.ofNullable(this.inst1);
        if (l_inst1.isEmpty()) {
            logger.debug(logPrfx + " --- inst1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- inst1: " + l_inst1.get());
            sb.append(l_inst1.get());
        }

        String l_name1_ = this.name1;
        String l_name1 = l_inst1.get();
        logger.debug(logPrfx + " --- l_name1:" + l_name1);

        if (Objects.equals(l_name1_, l_name1)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setName1(l_name1);
            logger.debug(logPrfx + " --- setName1(l_name1)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>type1_Id</i> field dependent fields</h1>
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
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    @Override
    public Boolean updateType1_IdDeps(DataManager dataManager) {
        String logPrfx = "updateType1_IdDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}