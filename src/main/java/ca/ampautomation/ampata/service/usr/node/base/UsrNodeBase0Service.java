package ca.ampautomation.ampata.service.usr.node.base;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseGrpg;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBal;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmt;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtItm;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.other.Globals;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseComn;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import io.jmix.core.DataManager;
import io.jmix.core.MetadataTools;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.Screen;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component("bean_UsrNodeBase.Service")
public class UsrNodeBase0Service implements Globals {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class typeOfNode;

    public UsrNodeBase0Service() {
        this.typeOfNode = UsrNodeBase.class;
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
     *          <li><i>sortKey</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateCalcVals(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateName1(screen, thisNode, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisNode, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisNode, updOption) || isChanged;
        isChanged = updateSortKey(screen, thisNode, updOption) || isChanged;

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
    public Boolean updateId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2";
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
            }
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
    public Boolean updateId2Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
            }
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
    public Boolean updateId2CalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateId2Cmp(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisNode.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisNode.getId2(),thisNode.getId2Calc());
        logger.debug(logPrfx + " --- id2Cmp:" + id2Cmp);

        if (Objects.equals(id2Cmp_, id2Cmp)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- called thisNode.setId2Cmp(id2Cmp)");
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
    public Boolean updateId2Dup(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Dup";
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
            }
        }

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
    public Boolean updateSortIdxDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateSortIdxDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

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
    public Boolean updateId2DupDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2DupDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <b>sortKey</b> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>sortIdx optional</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>sortIdx = 0</li>
     *          <li>sortKey = "00"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li>sortIdx = 10</li>
     *          <li>sortKey = "10"</li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateSortKey(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateSortKey";
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

                //sortIdx optional
                Optional<Integer> o_sortIdx = Optional.ofNullable(thisNode.getSortIdx());
                if(o_sortIdx.isEmpty()) {
                    logger.trace(logPrfx + " --- sortIdx is null");
                }else{
                    logger.trace(logPrfx + " --- sortIdx :" + o_sortIdx.get());
                }
                sb.append(String.format(FRMT_SORTKEY, o_sortIdx.orElse(0)));
                sb.insert(0,SEP4);

                //parent1_Id.sortKey optional
                Optional<String> o_parent1_SortKey = Optional.ofNullable(thisNode.getParent1_Id()).map(UsrNodeBase::getSortKey);
                Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if(o_parent1_SortKey.isPresent()){
                    sb.insert(0,o_parent1_SortKey.get());
                }

                String sortKey_ = thisNode.getSortKey();
                String sortKey;

                sortKey = sb.toString();
                logger.debug(logPrfx + " --- sortKey:" + sortKey);

                if (Objects.equals(sortKey_, sortKey)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setSortKey(sortKey);
                    logger.debug(logPrfx + " --- called thisNode.setSortKey(sortKey)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>sortKeyDeps</i> field dependent fields</h1>
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
    public Boolean updateSortKeyDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateSortKeyDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

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

                //type1_Id.id2 required
                Optional<String> o_type1_Id2 = Optional.ofNullable(thisNode.getType1_Id()).map(UsrNodeBaseType::getId2);
                if (o_type1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- type1_Id2: null");
                }else{
                    logger.debug(logPrfx + " --- type1_Id2: " + o_type1_Id2.get());
                    sb.append(o_type1_Id2.get());
                    sb.append(SEP3);
                }

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
    public Boolean updateType1_IdDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInst1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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
    public Boolean updateInst1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateDesc1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
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
    public Boolean updateDesc1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateDesc1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateSortIdxFrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateTxt1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTxt1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    public Boolean updateTs1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateTs1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateTs2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateTs2Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt1Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt2Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt3(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt3Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt4(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt4Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt4Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtDebt(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtDebt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtDebtDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtDebtDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtNet(screen, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtCred(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtCred";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtCredDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtCredDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtNet(screen, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNet(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNetDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtNetDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNet2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtNet2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        BigDecimal amtNet2_ = thisNode.getAmtNet2();
        BigDecimal amtNet2 = BigDecimal.ZERO ;

        //amtDebt optional
        Optional<BigDecimal> o_amtDebt = Optional.ofNullable(thisNode.getAmtDebt());
        if (o_amtDebt.isEmpty()) {
            logger.debug(logPrfx + " --- o_amtDebt: is empty");
        }else{
            logger.debug(logPrfx + " --- o_amtDebt: " + o_amtDebt.get().setScale(2, RoundingMode.HALF_UP));
            amtNet2 = amtNet2.add(o_amtDebt.get());
        }

        //amtCred optional
        Optional<BigDecimal> o_amtCred = Optional.ofNullable(thisNode.getAmtCred());
        if (o_amtCred.isEmpty()) {
            logger.debug(logPrfx + " --- o_amtCred: is empty");
        }else{
            logger.debug(logPrfx + " --- o_amtCred: " + o_amtCred.get().setScale(2, RoundingMode.HALF_UP));
            amtNet2 = amtNet2.subtract(o_amtCred.get());
        }

        logger.debug(logPrfx + " --- amtNet2: " + amtNet2.setScale(2, RoundingMode.HALF_UP));

        if (Objects.equals(amtNet2_, amtNet2)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setAmtNet2(amtNet2);
            logger.debug(logPrfx + " --- called thisNode.setAmtNet2(amtNet2)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNet2Deps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtNet2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateAmtCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    public Boolean updateAmtBegBal(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBal";
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
                BigDecimal amtBegBal_ = thisNode.getAmtBegBal();
                BigDecimal amtBegBal = thisNode.getAmtBegBalCalc();
                logger.debug(logPrfx + " --- amtBegBal:" + amtBegBal);

                if (Objects.equals(amtBegBal_, amtBegBal)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setAmtBegBal(amtBegBal);
                    logger.debug(logPrfx + " --- setAmtBegBal(amtBegBal)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtBegBalCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtBegBalCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtBegBalCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtEndBal(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtEndBal";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        BigDecimal amtEndBal_ = thisNode.getAmtEndBal();
        BigDecimal amtEndBal = thisNode.getAmtEndBalCalc();

        if(Objects.equals(amtEndBal_, amtEndBal)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setAmtEndBal(amtEndBal);
            logger.debug(logPrfx + " --- thisNode.setAmtEndBal(amtEndBal)");
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtEndBalCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateAmtEndBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    public Boolean updateFinStmtItms1_AmtDebtSumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtDebtSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinStmtItms1_AmtDebtSumCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtDebtSumCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItms1_AmtDebtSumDiff(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinStmtItms1_AmtDebtSumDiff(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtDebtSumDiff";
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
                BigDecimal amtDebt_ = Optional.ofNullable(thisNode.getAmtDebt()).orElse(BigDecimal.ZERO);
                BigDecimal amtDebtSumCalc_ = Optional.ofNullable(thisNode.getFinStmtItms1_AmtDebtSumCalc()).orElse(BigDecimal.ZERO);
                BigDecimal amtDebtSumDiff_ = thisNode.getFinStmtItms1_AmtDebtSumDiff();
                BigDecimal amtDebtSumDiff = amtDebt_.subtract(amtDebtSumCalc_);

                logger.debug(logPrfx + " --- amtDebtSumDiff: " + amtDebtSumDiff.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtDebtSumDiff_, amtDebtSumDiff)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinStmtItms1_AmtDebtSumDiff(amtDebtSumDiff);
                    logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtDebtSumDiff(amtDebtSumDiff)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinStmtItms1_AmtCredSumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }



    public Boolean updateFinStmtItms1_AmtCredSumCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtCredSumCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItms1_AmtCredSumDiff(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinStmtItms1_AmtCredSumDiff(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtCredSumDiff";
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
                BigDecimal amtCred_ = Optional.ofNullable(thisNode.getAmtCred()).orElse(BigDecimal.ZERO);
                BigDecimal amtCredSumCalc_ = Optional.ofNullable(thisNode.getFinStmtItms1_AmtCredSumCalc()).orElse(BigDecimal.ZERO);
                BigDecimal amtCredSumDiff_ = Optional.ofNullable(thisNode.getFinStmtItms1_AmtCredSumDiff()).orElse(BigDecimal.ZERO);
                BigDecimal amtCredSumDiff = amtCred_.subtract(amtCredSumCalc_) ;

                logger.debug(logPrfx + " --- amtCredSumDiff: " + amtCredSumDiff.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtCredSumDiff_, amtCredSumDiff)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinStmtItms1_AmtCredSumDiff(amtCredSumDiff);
                    logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtCredSumDiff(amtCredSumDiff)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinStmtItms1_AmtNetSumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtNetSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }


    public Boolean updateFinStmtItms1_AmtNetSumCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtNetSumCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItms1_AmtNetSumDiff(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinStmtItms1_AmtNetSumDiff(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtNetSumDiff";
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
                BigDecimal amtNet_ = Optional.ofNullable(thisNode.getAmtNet()).orElse(BigDecimal.ZERO);
                BigDecimal amtNetSumCalc_ = Optional.ofNullable(thisNode.getFinStmtItms1_AmtNetSumCalc()).orElse(BigDecimal.ZERO);
                BigDecimal amtNetSumDiff_ = thisNode.getFinStmtItms1_AmtNetSumDiff();
                BigDecimal amtNetSumDiff = amtNet_.subtract(amtNetSumCalc_) ;

                logger.debug(logPrfx + " --- amtNetSumDiff: " + amtNetSumDiff.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtNetSumDiff_, amtNetSumDiff)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinStmtItms1_AmtNetSumDiff(amtNetSumDiff);
                    logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtNetSumDiff(amtNetSumDiff)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinStmtItms1_IdCntCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }


    public Boolean updateFinStmtItms1_AmtEqCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinStmtItms1_AmtEqCalc";
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
                Boolean amtEqCalc_ = thisNode.getFinStmtItms1_AmtEqCalc();
                Boolean amtEqCalc = Objects.equals(thisNode.getFinStmtItms1_AmtDebtSumCalc()
                        , thisNode.getFinStmtItms1_AmtCredSumCalc());


                logger.debug(logPrfx + " --- amtEqCalc: " + amtEqCalc);

                if (Objects.equals(amtEqCalc_, amtEqCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinStmtItms1_AmtEqCalc(amtEqCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinStmtItms1_AmtEqCalc(amtEqCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }




    public Boolean updateFinTxactItms1_AmtDebtSumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinTxactItms1_AmtDebtSumCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactItms1_AmtDebtSumDiff(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinTxactItms1_AmtDebtSumDiff(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtDebtSumDiff";
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
                BigDecimal amtDebt_ = Optional.ofNullable(thisNode.getAmtDebt()).orElse(BigDecimal.ZERO);
                BigDecimal amtDebtSumCalc_ = Optional.ofNullable(thisNode.getFinTxactItms1_AmtDebtSumCalc()).orElse(BigDecimal.ZERO);
                BigDecimal amtDebtSumDiff_ = Optional.ofNullable(thisNode.getFinTxactItms1_AmtDebtSumDiff()).orElse(BigDecimal.ZERO);
                BigDecimal amtDebtSumDiff = amtDebt_.subtract(amtDebtSumCalc_);

                logger.debug(logPrfx + " --- amtDebtSumDiff: " + amtDebtSumDiff.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtDebtSumDiff_, amtDebtSumDiff)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtDebtSumDiff(amtDebtSumDiff);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtDebtSumDiff(amtDebtSumDiff)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinTxactItms1_AmtCredSumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }



    public Boolean updateFinTxactItms1_AmtCredSumCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactItms1_AmtCredSumDiff(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinTxactItms1_AmtCredSumDiff(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtCredSumDiff";
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
                BigDecimal amtCred_ = Optional.ofNullable(thisNode.getAmtCred()).orElse(BigDecimal.ZERO);
                BigDecimal amtCredSumCalc_ = Optional.ofNullable(thisNode.getFinTxactItms1_AmtCredSumCalc()).orElse(BigDecimal.ZERO);
                BigDecimal amtCredSumDiff_ = thisNode.getFinTxactItms1_AmtCredSumDiff();
                BigDecimal amtCredSumDiff = amtCred_.subtract(amtCredSumCalc_) ;

                logger.debug(logPrfx + " --- amtCredSumDiff: " + amtCredSumDiff.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtCredSumDiff_, amtCredSumDiff)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtCredSumDiff(amtCredSumDiff);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtCredSumDiff(amtCredSumDiff)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinTxactItms1_AmtNetSumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtNetSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }


    public Boolean updateFinTxactItms1_AmtNetSumCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtNetSumCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactItms1_AmtNetSumDiff(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinTxactItms1_AmtNet2SumCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtNet2SumCalc";
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
                BigDecimal amtNet2SumCalc_ = thisNode.getFinTxactItms1_AmtNet2SumCalc();
                BigDecimal amtNet2SumCalc = BigDecimal.ZERO ;

                //finTxactItms1_AmtDebtSumCalc optional
                Optional<BigDecimal> o_finTxactItms1_AmtDebtSumCalc = Optional.ofNullable(thisNode.getFinTxactItms1_AmtDebtSumCalc());
                if (o_finTxactItms1_AmtDebtSumCalc.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finTxactItms1_AmtDebtSumCalc: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_finTxactItms1_AmtDebtSumCalc: " + o_finTxactItms1_AmtDebtSumCalc.get().setScale(2, RoundingMode.HALF_UP));
                    amtNet2SumCalc = amtNet2SumCalc.add(o_finTxactItms1_AmtDebtSumCalc.get());
                }

                //finTxactItms1_AmtCredSumCalc optional
                Optional<BigDecimal> o_finTxactItms1_AmtCredSumCalc = Optional.ofNullable(thisNode.getFinTxactItms1_AmtCredSumCalc());
                if (o_finTxactItms1_AmtCredSumCalc.isEmpty()) {
                    logger.debug(logPrfx + " --- o_finTxactItms1_AmtCredSumCalc: is empty");
                }else{
                    logger.debug(logPrfx + " --- o_finTxactItms1_AmtCredSumCalc: " + o_finTxactItms1_AmtCredSumCalc.get().setScale(2, RoundingMode.HALF_UP));
                    amtNet2SumCalc = amtNet2SumCalc.subtract(o_finTxactItms1_AmtCredSumCalc.get());
                }

                logger.debug(logPrfx + " --- amtNet2SumCalc: " + amtNet2SumCalc.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtNet2SumCalc_, amtNet2SumCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtNet2SumCalc(amtNet2SumCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtNetSumCalc(amtNet2SumCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }


    public Boolean updateFinTxactItms1_AmtNet2SumCalcDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtNet2SumCalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactItms1_AmtNet2SumDiff(screen, thisNode, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinTxactItms1_AmtNetSumDiff(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtNetSumDiff";
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
                BigDecimal amtNet_ = Optional.ofNullable(thisNode.getAmtNet()).orElse(BigDecimal.ZERO);
                BigDecimal amtNetSumCalc_ = Optional.ofNullable(thisNode.getFinTxactItms1_AmtNetSumCalc()).orElse(BigDecimal.ZERO);
                BigDecimal amtNetSumDiff_ = Optional.ofNullable(thisNode.getFinTxactItms1_AmtNetSumDiff()).orElse(BigDecimal.ZERO);
                BigDecimal amtNetSumDiff = amtNet_.subtract(amtNetSumCalc_) ;

                logger.debug(logPrfx + " --- amtNetSumDiff: " + amtNetSumDiff.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtNetSumDiff_, amtNetSumDiff)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtNetSumDiff(amtNetSumDiff);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtNetSumDiff(amtNetSumDiff)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinTxactItms1_AmtNet2SumDiff(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtNet2SumDiff";
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
                BigDecimal amtNet2_ = Optional.ofNullable(thisNode.getAmtNet2()).orElse(BigDecimal.ZERO);
                BigDecimal amtNet2SumCalc_ = Optional.ofNullable(thisNode.getFinTxactItms1_AmtNet2SumCalc()).orElse(BigDecimal.ZERO);
                BigDecimal amtNet2SumDiff_ = Optional.ofNullable(thisNode.getFinTxactItms1_AmtNet2SumDiff()).orElse(BigDecimal.ZERO);
                BigDecimal amtNet2SumDiff = amtNet2_.subtract(amtNet2SumCalc_) ;

                logger.debug(logPrfx + " --- amtNet2SumDiff: " + amtNet2SumDiff.setScale(2, RoundingMode.HALF_UP));

                if (Objects.equals(amtNet2SumDiff_, amtNet2SumDiff)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtNet2SumDiff(amtNet2SumDiff);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtNet2SumDiff(amtNet2SumDiff)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinTxactItms1_IdCntCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }


    public Boolean updateFinTxactItms1_AmtEqCalc(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateFinTxactItms1_AmtEqCalc";
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
                Boolean amtEqCalc_ = thisNode.getFinTxactItms1_AmtEqCalc();
                Boolean amtEqCalc = Objects.equals(thisNode.getFinTxactItms1_AmtDebtSumCalc()
                        , thisNode.getFinTxactItms1_AmtCredSumCalc());


                logger.debug(logPrfx + " --- amtEqCalc: " + amtEqCalc);

                if (Objects.equals(amtEqCalc_, amtEqCalc)){
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setFinTxactItms1_AmtEqCalc(amtEqCalc);
                    logger.debug(logPrfx + " --- called thisNode.setFinTxactItms1_AmtEqCalc(amtEqCalc)");
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateDt1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateDt1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTm1(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTm1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateIdPartsFrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt1FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt2FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt2FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt3FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt3FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTs1FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTs2FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs2FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateTs3FrId2(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs3FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateParent1_Id(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateParent1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            logger.debug(logPrfx + " --- updOption: " + updOption.toString());
            switch (updOption) {
                case    SKIP,
                        LOCAL -> {
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                case     LOCAL__REF_TO_EXIST
                        ,LOCAL__REF_TO_EXIST_NEW
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                        -> {

                    UsrNodeBase parent1_Id_ = thisNode.getParent1_Id();
                    UsrNodeBase parent1_Id = null;

                    // find parent nodes
                    List<UsrNodeBase> qryRsltNodes = findParentNodes(screen, thisNode, updOption);

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


    public Boolean updateParent1_IdDeps(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
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


    public UsrNodeBase createParentNode(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createParentNode";
        logger.trace(logPrfx + " --> ");
        UsrNodeBase parent1_Id = null;

        logger.trace(logPrfx + " <-- ");
        return parent1_Id;
    }


    public List<UsrNodeBase> findParentNodes(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findParentNodes";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> rsltNodes = new ArrayList<UsrNodeBase>();

        logger.trace(logPrfx + " <-- ");
        return rsltNodes;
    }


    public Boolean updateFinBal1_Id(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinBal1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            logger.debug(logPrfx + " --- updOption: " + updOption.toString());
            switch (updOption) {
                case    SKIP,
                        LOCAL -> {
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                case     LOCAL__REF_TO_EXIST
                        ,LOCAL__REF_TO_EXIST_NEW
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                        -> {
                    UsrNodeFinBal finBal1_Id_ = thisNode.getFinBal1_Id();
                    UsrNodeFinBal finBal1_Id = null;

                    // find FinBal nodes
                    List<UsrNodeFinBal> qryRsltNodes = findFinBals(screen, thisNode, updOption);

                    if (qryRsltNodes.size() == 0){
                        logger.debug(logPrfx + " --- qryRsltNodes.size(): 0.");
                        switch(updOption){
                            case    LOCAL__REF_TO_EXIST_NEW
                                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                                    ->{
                                // Create a new node
                                logger.trace(logPrfx + " --- Creating new node");
                                finBal1_Id = createFinBal(screen, thisNode, updOption);
                            }
                        }
                    }else if(qryRsltNodes.size() > 1) {
                        String msg = MessageFormat.format("Can't update FinBal1 because {0} candidates found.",qryRsltNodes.size());
                        logger.trace(logPrfx + " --- " + msg);
                        notifications.create().withCaption(msg);
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;

                    }else{
                        // Use the first candidate
                        finBal1_Id = qryRsltNodes.get(0);

                    }
                    if (finBal1_Id == null) {
                        logger.debug(logPrfx + " --- finBal1_Id.id2: null");
                    }else {
                        logger.debug(logPrfx + " --- finBal1_Id.id2: " + finBal1_Id.getId2());
                    }

                    if (Objects.equals(finBal1_Id_, finBal1_Id)){
                        logger.debug(logPrfx + " --- no change detected");
                    } else {
                        thisNode.setFinBal1_Id(finBal1_Id);
                        logger.debug(logPrfx + " --- called thisNode.setFinBal1_Id(finBal1_Id)");
                        isChanged = true;
                    }
                }
            }
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public List<UsrNodeFinBal> findFinBals(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findFinBals";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinBal> rsltNodes = null;

        logger.trace(logPrfx + " <-- ");
        return rsltNodes;
    }

    public UsrNodeFinBal createFinBal(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createFinBal";
        logger.trace(logPrfx + " --> ");
        UsrNodeFinBal newNode = null;

        logger.trace(logPrfx + " <-- ");
        return newNode;
    }



    public Boolean updateFinStmt1_Id(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinStmt1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            logger.debug(logPrfx + " --- updOption: " + updOption.toString());
            switch (updOption) {
                case    SKIP,
                        LOCAL -> {
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                case     LOCAL__REF_TO_EXIST
                        ,LOCAL__REF_TO_EXIST_NEW
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                        -> {
                    UsrNodeFinStmt finStmt1_Id_ = thisNode.getFinStmt1_Id();
                    UsrNodeFinStmt finStmt1_Id = null;

                    // find FinStmt nodes
                    List<UsrNodeFinStmt> qryRsltNodes = findFinStmts(screen, thisNode, updOption);

                    if (qryRsltNodes.size() == 0){
                        logger.debug(logPrfx + " --- qryRsltNodes.size(): 0.");
                        switch(updOption){
                            case    LOCAL__REF_TO_EXIST_NEW
                                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                                    ->{
                                // Create a new node
                                logger.trace(logPrfx + " --- Creating new node");
                                finStmt1_Id = createFinStmt(screen, thisNode, updOption);
                            }
                        }
                    }else if(qryRsltNodes.size() > 1) {
                        String msg = MessageFormat.format("Can't update FinStmt1 because {0} candidates found.",qryRsltNodes.size());
                        logger.trace(logPrfx + " --- " + msg);
                        notifications.create().withCaption(msg);
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;

                    }else{
                        // Use the first candidate
                        finStmt1_Id = qryRsltNodes.get(0);

                    }
                    if (finStmt1_Id == null) {
                        logger.debug(logPrfx + " --- finStmt1_Id.id2: null");
                    }else {
                        logger.debug(logPrfx + " --- finStmt1_Id.id2: " + finStmt1_Id.getId2());
                    }

                    if (Objects.equals(finStmt1_Id_, finStmt1_Id)){
                        logger.debug(logPrfx + " --- no change detected");
                    } else {
                        thisNode.setFinStmt1_Id(finStmt1_Id);
                        logger.debug(logPrfx + " --- called thisNode.setFinStmt1_Id(finStmt1_Id)");
                        isChanged = true;
                    }
                }
            }
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public List<UsrNodeFinStmt> findFinStmts(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findFinStmts";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinStmt> rsltNodes = null;

        logger.trace(logPrfx + " <-- ");
        return rsltNodes;
    }

    public UsrNodeFinStmt createFinStmt(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createFinStmt";
        logger.trace(logPrfx + " --> ");
        UsrNodeFinStmt newNode = null;

        logger.trace(logPrfx + " <-- ");
        return newNode;
    }


    public Boolean updateFinStmtItm1_Id(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "updateFinStmtItm1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Notifications notifications;
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            logger.debug(logPrfx + " --- updOption: " + updOption.toString());
            switch (updOption) {
                case    SKIP,
                        LOCAL -> {
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }

                case     LOCAL__REF_TO_EXIST
                        ,LOCAL__REF_TO_EXIST_NEW
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST
                        ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                        -> {
                    UsrNodeFinStmtItm finStmtItm1_Id_ = thisNode.getFinStmtItm1_Id();
                    UsrNodeFinStmtItm finStmtItm1_Id = null;

                    // find FinStmtItm nodes
                    List<UsrNodeFinStmtItm> qryRsltNodes = findFinStmtItms(screen, thisNode, updOption);

                    if (qryRsltNodes.size() == 0){
                        logger.debug(logPrfx + " --- qryRsltNodes.size(): 0.");
                        switch(updOption){
                            case    LOCAL__REF_TO_EXIST_NEW
                                    ,LOCAL__REF_IF_EMPTY_TO_EXIST_NEW
                                    ->{
                                // Create a new parent
                                logger.trace(logPrfx + " --- Creating new parent");
                                finStmtItm1_Id = createFinStmtItm(screen, thisNode, updOption);
                            }
                        }
                    }else if(qryRsltNodes.size() > 1) {
                        String msg = MessageFormat.format("Can't update FinStmtItm1 because {0} candidates for parent1.",qryRsltNodes.size());
                        logger.trace(logPrfx + " --- " + msg);
                        notifications.create().withCaption(msg);
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;

                    }else{
                        // Use the first candidate
                        finStmtItm1_Id = qryRsltNodes.get(0);

                    }
                    if (finStmtItm1_Id == null) {
                        logger.debug(logPrfx + " --- finStmtItm1_Id.id2: null");
                    }else {
                        logger.debug(logPrfx + " --- finStmtItm1_Id.id2: " + finStmtItm1_Id.getId2());
                    }

                    if (Objects.equals(finStmtItm1_Id_, finStmtItm1_Id)){
                        logger.debug(logPrfx + " --- no change detected");
                    } else {
                        thisNode.setFinStmtItm1_Id(finStmtItm1_Id);
                        logger.debug(logPrfx + " --- called thisNode.setFinStmtItm1_Id(finStmtItm1_Id)");
                        isChanged = true;
                    }
                }
            }
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public List<UsrNodeFinStmtItm> findFinStmtItms(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "findFinStmtItms";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinStmtItm> rsltNodes = null;

        logger.trace(logPrfx + " <-- ");
        return rsltNodes;
    }

    public UsrNodeFinStmtItm createFinStmtItm(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption) {
        String logPrfx = "createFinStmtItm";
        logger.trace(logPrfx + " --> ");
        UsrNodeFinStmtItm newNode = null;

        logger.trace(logPrfx + " <-- ");
        return newNode;
    }

    public Boolean updateGenAgent1_Id(@NotNull Screen screen, @NotNull UsrNodeBase thisNode, @NotNull UpdateOption updOption){
        String logPrfx = "updateGenAgent1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        UsrNodeGenAgent genAgent1_ = thisNode.getGenAgent1_Id();
        UsrNodeGenAgent genAgent1 = null;


        switch (thisNode.getDtype().substring(5)) {

            default:
                break;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public List<String> getStatusList(){
        String logPrfx = "getStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status "
                + " from enty_" + typeOfNode.getSimpleName() + " e"
                + " where e.status is not null"
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
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof UsrNodeBaseGrpg
            if (!(grpgKey instanceof UsrNodeBaseGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeBaseGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer sortIdxCntIsNull = null;
            String sortIdxCntIsNullQry = "select count(e)"
                    + " from enty_UsrNodeBase e"
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
        if(screen instanceof UsrNodeBase0BaseMain
                || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof UsrNodeBaseGrpg
            if (!(grpgKey instanceof UsrNodeBaseGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeBaseGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer sortIdxCntIsNull = null;
            String sortIdxCntIsNullQry = "select count(e)"
                    + " from enty_UsrNodeBase e"
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
        if(screen instanceof UsrNodeBase0BaseMain
        || screen instanceof UsrNodeBase0BaseEdit) {
            notifications = ((UsrNodeBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof UsrNodeBaseGrpg
            if (!(grpgKey instanceof UsrNodeBaseGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeBaseGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer int2CntIsNull = null;
            String int2CntIsNullQry = "select count(e)"
                    + " from enty_UsrNodeBase e"
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

    public static <NodeT extends UsrNodeBase> NodeT getNodeById2(Class<NodeT> type, DataManager dataManager, @NotNull String crtieria_Id2) {
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
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


    public static <NodeT extends UsrNodeBase> NodeT getNodesBySortIdx(Class<NodeT> type, DataManager dataManager, @NotNull Integer sortIdx, UsrNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
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


    public static <NodeT extends UsrNodeBase> List<NodeT> getNodesBtwnSortIdx(Class<NodeT> type, DataManager dataManager, @NotNull Integer sortIdxA , @NotNull Integer sortIdxB, UsrNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
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

    public static <NodeT extends UsrNodeBase> List<NodeT> getNodesByParent1(Class<NodeT> type, DataManager dataManager, UsrNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
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