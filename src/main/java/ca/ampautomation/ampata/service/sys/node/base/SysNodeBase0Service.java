package ca.ampautomation.ampata.service.sys.node.base;

import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBaseGrpg;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBaseType;
import ca.ampautomation.ampata.other.Globals;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.view.sys.node.base.SysNodeBase0BaseEdit;
import ca.ampautomation.ampata.view.sys.node.base.SysNodeBase0BaseMain;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseComn;
import io.jmix.core.DataManager;
import io.jmix.core.MetadataTools;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.Screen;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Transient;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class SysNodeBase0Service implements Globals {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class typeOfNode;

    public SysNodeBase0Service() {
        this.typeOfNode = SysNodeBase.class;
    }

    @Autowired
    protected DataManager dataManager;

    @Autowired
    protected MetadataTools metadataTools;


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
    public Boolean updateCalcVals(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = this.updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = this.updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = this.updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <b>id2</b> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>id2Calc</i> required</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateId2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisNode.getId2();
        String id2 = thisNode.getId2Calc();
        logger.debug(logPrfx + " --- id2:" + id2);

        if(Objects.equals(id2_, id2)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setId2(id2);
            logger.debug(logPrfx + " --- called thisNode.setId2(id2)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>id2</i> field dependent fields</h1>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>id2Cmp</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateId2Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>id2Calc</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "Gen"</li>
     *          <li><i>id2Calc</i> = "Gen"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "Biz"</li>
     *          <li><i>id2Calc</i> = "Biz"</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateId2Calc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //name1 required
        Optional<String> o_name1 = Optional.ofNullable(thisNode.getName1());
        if(o_name1.isEmpty()) {
            logger.trace(logPrfx + " --- name1 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.trace(logPrfx + " --- name1 :" + o_name1.get());
            sb.append(o_name1.get());
        }

        String id2Calc_ = thisNode.getId2Calc();
        String id2Calc = sb.toString();
        logger.debug(logPrfx + " --- id2Calc:" + id2Calc);

        if (Objects.equals(id2Calc_, id2Calc)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- called thisNode.setId2Calc(id2Calc)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>id2Calc</i> field dependent fields</h1>
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
    public Boolean updateId2CalcDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2CalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the id2Cmp field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 required</li>
     *          <li>id2Calc required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 = "Gen"</li>
     *          <li>id2Calc = "Gen"</li>
     *          <li>id2Cmp = true</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 = "Gen"</li>
     *          <li>id2Calc = "Biz"</li>
     *          <li>id2Cmp = false</li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateId2Cmp(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisNode.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisNode.getId2(),thisNode.getId2Calc());
        logger.debug(logPrfx + " --- id2Cmp_:" + id2Cmp_);

        if (Objects.equals(id2Cmp_, id2Cmp_)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setId2Cmp(id2Cmp_);
            logger.debug(logPrfx + " --- called thisNode.setId2Cmp(id2Cmp_)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <b>id2Dup</b> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 = "Gen"</li>
     *          <li>id2Cmp = 1</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 = "Prf"</li>
     *          <li>id2Cmp = 3<br>
     *              Assuming there are 3 objects of this type with id2 = "Prf"
     *          </li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateId2Dup(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisNode.getId2Dup();
        Integer id2Dup;
        if (thisNode.getId2() != null){
            String id2Qry = "select count(e)"
                    + " from enty_" + thisNode.getClass().getSimpleName() + " e"
                    + " where e.id2 = :id2"
                    + " and e.id <> :id";
            try{
                id2Dup = this.dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",thisNode.getId())
                        .parameter("id2",thisNode.getId2())
                        .one();
            }catch (IllegalStateException e){
                id2Dup =0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            logger.debug(logPrfx + " --- id2Dup:" + id2Dup);

            if (Objects.equals(id2Dup_, id2Dup)) {
                logger.debug(logPrfx + " --- no change detected");
            }else{
                thisNode.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- called thisNode.setId2Cmp(l_id2Cmp_)");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>id2Dup</i> field dependent fields</h1>
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
     *          <li><i>id2Cmp</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateId2DupDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2DupDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>name1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.id2</i> optional</li>
     *          <li><i>inst1</i> required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.id2</i> = null</li>
     *          <li><i>inst1</i> = "Gen"</li>
     *          <li><i>name1</i> = "Gen"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.id2</i> = "/"</li>
     *          <li><i>inst1</i> = "Biz"</li>
     *          <li><i>name1</i> = "/::Biz"</li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateName1(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateName1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //type1_Id.id2 required
        Optional<String> o_type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(SysNodeBaseType::getId2);
        if (o_type1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- type1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- type1_Id2: " + o_type1_Id2.get());
            sb.append(o_type1_Id2.get());
            sb.append(SEP3);
        }

        //o_inst1 required
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
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateName1Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
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
     *          <li><i>name1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateType1_IdDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateType1_IdDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateName1(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>inst1</i> field</h1>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateInst1(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>inst1</i> field dependent fields</h1>
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
     *          <li><i>name1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInst1Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInst1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateName1(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>desc1</i> field</h1>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateDesc1(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>desc1</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateDesc1Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateDesc1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>sortIdx</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateSortIdxDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateSortIdxDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateSortIdxFrId2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateSortIdxFrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

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
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateTxt1Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTxt1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    public Boolean updateTs1(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        // Assume ts1, ts2, ts3 is not null
        String logPrfx = "updateTs1";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;


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
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateTs1Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>ts2</i> field</h1>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateTs2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

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
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateTs2Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>int1</i> field</h1>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt1(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

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
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt1Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>int2</i> field</h1>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>int2</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt2Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>int3</i> field</h1>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt3(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt3";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>int3</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt3Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt3Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>int4</i> field</h1>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt4(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt4";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>int4</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInt4Deps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt4Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtDebt(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtDebt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtDebtDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtDebtDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtNet(screen, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtCred(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtCred";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtCredDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtCredDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtNet(screen, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNet(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNetDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtNetDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtCalc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    public Boolean updateAmtBegBal(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBal";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtBegBalCalc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtBegBalCalcDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBalCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmtEndBal(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtEndBal";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

/*
        BigDecimal amtEndBal_ = thisNode.getAmtEndBal();
        BigDecimal amtEndBal = thisNode.getAmtEndBalCalc();

        if(Objects.equals(amtEndBal_, amtEndBal)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setAmtEndBal(amtEndBal);
            logger.debug(logPrfx + " --- thisNode.setAmtEndBal(amtEndBal)");
        }
*/

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtEndBalCalc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtEndBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinTxactItms1_AmtDebtSumCalc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinTxactItms1_AmtCredSumCalc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }

    public Boolean updateFinTxactItms1_AmtNetSumCalc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtNetSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }

    public Boolean updateFinTxactItms1_IdCntCalc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }

    public Boolean updateFinTxactItms1_AmtEqCalc(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }

    public Boolean updateDt1(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateDt1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTm1(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTm1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateIdPartsFrId2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt1FrId2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt2FrId2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt2FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt3FrId2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt3FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTs1FrId2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateTs2FrId2(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs2FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateParent1_Id(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateParent1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Notifications notifications;
        if(screen instanceof SysNodeBase0BaseMain
                || screen instanceof SysNodeBase0BaseEdit
            ){
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            SysNodeBase parent1_Id_ = thisNode.getParent1_Id();
            SysNodeBase parent1_Id = null;
            switch (updOption) {
                case    SKIP,
                        LOCAL -> {
                    logger.debug(logPrfx + " --- updOption: " + updOption.toString());
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                case     LOCAL__REF_TO_EXIST
                        ,LOCAL__REF_TO_EXIST_NEW
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                        -> {

                    // find parent nodes
                    List<SysNodeBase> qryRsltNodes = findParentNodes(screen, thisNode, updOption);

                    if (qryRsltNodes.size() == 0){
                        logger.debug(logPrfx + " --- qryRsltNodes.size(): 0.");
                        switch(updOption){
                            case    LOCAL__REF_TO_EXIST_NEW
                                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                                    ->{
                                // Create a new parent
                                logger.trace(logPrfx + " --- Creating new parent");
                                parent1_Id = createParentNode(screen, thisNode, updOption);
                            }
                        }
                    }else if(qryRsltNodes.size() > 1) {
                        String msg = MessageFormat.format("Can't update Parent1 because {0} candidates for parent1.",qryRsltNodes.size());
                        logger.trace(logPrfx + " --- " + msg);
                        notifications.create().withCaption(msg);
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;

                    }else{
                        // Use the first candidate
                        parent1_Id = qryRsltNodes.get(0);
                    }
                    if (parent1_Id == null) {
                        logger.debug(logPrfx + " --- parent1_Id.id2: null");
                    }else {
                        logger.debug(logPrfx + " --- parent1_Id.id2: " + parent1_Id.getId2());
                    }

                    if (Objects.equals(parent1_Id_, parent1_Id)){
                        logger.debug(logPrfx + " --- no change detected");
                    } else {
                        thisNode.setParent1_Id(parent1_Id);
                        logger.debug(logPrfx + " --- called thisNode.setParent1_Id(parent1_Id)");
                        isChanged = true;
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateParent1_IdDeps(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateParent1_IdDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateDesc1(screen, thisNode, updOption) || isChanged;
        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public SysNodeBase createParentNode(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createParentNode";
        logger.trace(logPrfx + " --> ");
        SysNodeBase parent1_Id = null;

        logger.trace(logPrfx + " <-- ");
        return parent1_Id;
    }


    public List<SysNodeBase> findParentNodes(@NotNull Screen screen, @NotNull SysNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findParentNodes";
        logger.trace(logPrfx + " --> ");

        List<SysNodeBase> rsltNodes = null;

        logger.trace(logPrfx + " <-- ");
        return rsltNodes;
    }


    public List<String> getStatusList(){
        String logPrfx = "getStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status "
                + " from enty_" + typeOfNode.getSimpleName() + " e"
                + " and e.status is not null"
                + " order by e.status"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> statuss = null;
        try {
            statuss = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + statuss.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return statuss;
        }

        logger.trace(logPrfx + " <-- ");
        return statuss;
    }

    public Integer getSortIdxMax(@NotNull Screen screen, Object grpgKey) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;

        Notifications notifications;
        if(screen instanceof SysNodeBase0BaseMain
                || screen instanceof SysNodeBase0BaseEdit
        ) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof SysNodeBaseGrpg
            if (!(grpgKey instanceof SysNodeBaseGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof SysNodeBaseGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer sortIdxCntIsNull = null;
            String sortIdxCntIsNullQry = "select count(e)"
                    + " from enty_SysNodeBase e"
                    + " where e.parent1_Id = :parent1_Id"
                    + " and e.sortIdx is null"
                    ;
            try {
                sortIdxCntIsNull = dataManager.loadValue(sortIdxCntIsNullQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
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
                    ;
            try {
                sortIdxMax = dataManager.loadValue(sortIdxMaxQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
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


    public Integer getInt1Max(@NotNull Screen screen, Object grpgKey) {
        String logPrfx = "getInt1Max";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;

        Notifications notifications;
        if(screen instanceof SysNodeBase0BaseMain
                || screen instanceof SysNodeBase0BaseEdit
        ) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof SysNodeBaseGrpg
            if (!(grpgKey instanceof SysNodeBaseGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof SysNodeBaseGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer sortIdxCntIsNull = null;
            String sortIdxCntIsNullQry = "select count(e)"
                    + " from enty_SysNodeBase e"
                    + " where e.parent1_Id = :parent1_Id"
                    + " and e.sortIdx is null"
                    ;
            try {
                sortIdxCntIsNull = dataManager.loadValue(sortIdxCntIsNullQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
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
                    ;
            try {
                sortIdxMax = dataManager.loadValue(sortIdxMaxQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
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

    public Integer getInt2Max(@NotNull Screen screen, Object grpgKey) {
        String logPrfx = "getInt2Max";
        logger.trace(logPrfx + " --> ");

        Integer int2Max = null;

        Notifications notifications;
        if(screen instanceof SysNodeBase0BaseMain
                || screen instanceof SysNodeBase0BaseEdit
        ) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof SysNodeBaseGrpg
            if (!(grpgKey instanceof SysNodeBaseGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof SysNodeBaseGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer int2CntIsNull = null;
            String int2CntIsNullQry = "select count(e)"
                    + " from enty_SysNodeBase e"
                    + " where e.parent1_Id = :parent1_Id"
                    + " and e.sortIdx is null"
                    ;
            try {
                int2CntIsNull = dataManager.loadValue(int2CntIsNullQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
                        .one();
            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- int2CntIsNullQry error: " + e.getMessage());
                notifications.create().withCaption("int2CntIsNullQry error: " + e.getMessage()).show();
                logger.trace(logPrfx + " <-- ");
                return null;
            }
            logger.debug(logPrfx + " --- int2CntIsNullQry result: " + int2CntIsNull + "");

            // If we found counted some null values, return null
            if (int2CntIsNull != 0){
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            String int2MaxQry = "select max(e.sortIdx)"
                    + " from enty_"+ typeOfNode.getSimpleName() +" e"
                    + " where e.parent1_Id = :parent1_Id"
                    ;
            try {
                int2Max = dataManager.loadValue(int2MaxQry, Integer.class)
                        .store("main")
                        .parameter("parent1_Id", l_grpgKey.parent1_Id())
                        .one();
                // max returns null if no rows or if all rows have a null value
            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- int2MaxQry error: " + e.getMessage());
            }
            logger.debug(logPrfx + " --- int2MaxQry result: " + int2Max + "");
        }


        logger.trace(logPrfx + " <-- ");
        return int2Max;

    }

    public static <NodeT extends SysNodeBase> NodeT getNodeById2(Class<NodeT> type, DataManager dataManager, @NotNull String crtieria_Id2) {
        final Logger logger = LoggerFactory.getLogger(SysNodeBase.class.getClass());
        String logPrfx = "getNodeById2";
        logger.trace(logPrfx + " --> ");

        if (crtieria_Id2 == null) {
            logger.debug(logPrfx + " --- crtieria_Id2 is null.");
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e"
                + " from enty_" + type.getSimpleName() + " e"
                + " where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + crtieria_Id2);

        NodeT node = null;
        try {
            node = dataManager.load(type)
                    .query(qry)
                    .parameter("id2", crtieria_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return node;

    }


    public static <NodeT extends SysNodeBase> NodeT getNodesBySortIdx(Class<NodeT> type, DataManager dataManager, @NotNull Integer sortIdx, SysNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(SysNodeBase.class.getClass());
        String logPrfx = "getNodesBySortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e"
                + " from enty_" + type.getSimpleName() + " e"
                + " where e.parent1_Id = :parent1_Id"
                + " and e.sortIdx = :sortIdx"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdx: " + sortIdx);

        NodeT usrNode = null;
        try {
            usrNode = dataManager.load(type)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdx", sortIdx)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return usrNode;
    }


    public static <NodeT extends SysNodeBase> List<NodeT> getNodesBtwnSortIdx(Class<NodeT> type, DataManager dataManager, @NotNull Integer sortIdxA , @NotNull Integer sortIdxB, SysNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(SysNodeBase.class.getClass());
        String logPrfx = "getNodesBtwnSortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e"
                + " from enty_" + type.getSimpleName() + " e"
                + " where e.parent1_Id = :parent1_Id"
                + " and e.sortIdx > :sortIdxA"
                + " and e.sortIdx < :sortIdxB"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdxA: " + sortIdxA);
        logger.debug(logPrfx + " --- qry:sortIdxB: " + sortIdxB);

        List<NodeT> nodes = null;
        try {
            nodes = dataManager.load(type)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdxA", sortIdxA)
                    .parameter("sortIdxB", sortIdxB)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ nodes.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return nodes;
    }

    public static <NodeT extends SysNodeBase> List<NodeT> getNodesByParent1(Class<NodeT> type, DataManager dataManager, SysNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(SysNodeBase.class.getClass());
        String logPrfx = "getNodeListByParent1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e"
                + " from enty_" + type.getSimpleName() + " e"
                + " where e.parent1_Id = :parent1_Id"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());

        List<NodeT> nodes = null;
        try {
            nodes = dataManager.load(type)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ nodes.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return nodes;
    }


}
