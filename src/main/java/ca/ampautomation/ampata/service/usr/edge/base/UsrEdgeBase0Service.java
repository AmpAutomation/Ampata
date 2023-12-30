package ca.ampautomation.ampata.service.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBase;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.other.Globals;
import ca.ampautomation.ampata.other.UpdateOption;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component("bean_UsrEdgeBase.Service")
public class UsrEdgeBase0Service implements Globals {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class typeOfEdge;

    public UsrEdgeBase0Service() {
        this.typeOfEdge = UsrEdgeBase.class;
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
    public Boolean updateCalcVals(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateName1(screen, thisEdge, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisEdge, updOption) || isChanged;
        isChanged = updateSortKey(screen, thisEdge, updOption) || isChanged;

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
    public Boolean updateId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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

                String id2_ = thisEdge.getId2();
                String id2 = thisEdge.getId2Calc();
                logger.debug(logPrfx + " --- id2:" + id2);

                if(Objects.equals(id2_, id2)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisEdge.setId2(id2);
                    logger.debug(logPrfx + " --- called thisEdge.setId2(id2)");
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
    public Boolean updateId2Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisEdge, updOption) || isChanged;

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
    public Boolean updateId2Calc(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
                Optional<String> o_name1 = Optional.ofNullable(thisEdge.getName1());
                if(o_name1.isEmpty()) {
                    logger.trace(logPrfx + " --- name1 is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.trace(logPrfx + " --- name1 :" + o_name1.get());
                    sb.append(o_name1.get());
                }

                String id2Calc_ = thisEdge.getId2Calc();
                String id2Calc = sb.toString();
                logger.debug(logPrfx + " --- id2Calc:" + id2Calc);

                if (Objects.equals(id2Calc_, id2Calc)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisEdge.setId2Calc(id2Calc);
                    logger.debug(logPrfx + " --- called thisEdge.setId2Calc(id2Calc)");
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
    public Boolean updateId2CalcDeps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2CalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisEdge, updOption) || isChanged;

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
    public Boolean updateId2Cmp(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisEdge.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisEdge.getId2(),thisEdge.getId2Calc());
        logger.debug(logPrfx + " --- id2Cmp:" + id2Cmp);

        if (Objects.equals(id2Cmp_, id2Cmp)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisEdge.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- called thisEdge.setId2Cmp(id2Cmp)");
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
    public Boolean updateId2Dup(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
                Integer id2Dup_ = thisEdge.getId2Dup();
                Integer id2Dup;
                if (thisEdge.getId2() != null){
                    String id2Qry = "select count(e)"
                            + " from enty_" + thisEdge.getClass().getSimpleName() + " e"
                            + " where e.id2 = :id2"
                            + " and e.id <> :id";
                    try{
                        id2Dup = this.dataManager.loadValue(id2Qry, Integer.class)
                                .store("main")
                                .parameter("id",thisEdge.getId())
                                .parameter("id2",thisEdge.getId2())
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
                        thisEdge.setId2Dup(id2Dup);
                        logger.debug(logPrfx + " --- called thisEdge.setId2Cmp(l_id2Cmp_)");
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
    public Boolean updateSortIdxDeps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateId2DupDeps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2DupDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisEdge, updOption) || isChanged;

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
    public Boolean updateSortKey(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
                Optional<Integer> o_sortIdx = Optional.ofNullable(thisEdge.getSortIdx());
                if(o_sortIdx.isEmpty()) {
                    logger.trace(logPrfx + " --- sortIdx is null");
                }else{
                    logger.trace(logPrfx + " --- sortIdx :" + o_sortIdx.get());
                }
                sb.append(String.format(FRMT_SORTKEY, o_sortIdx.orElse(0)));
                sb.insert(0,SEP4);

                String sortKey_ = thisEdge.getSortKey();
                String sortKey;

                sortKey = sb.toString();
                logger.debug(logPrfx + " --- sortKey:" + sortKey);

                if (Objects.equals(sortKey_, sortKey)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisEdge.setSortKey(sortKey);
                    logger.debug(logPrfx + " --- called thisEdge.setSortKey(sortKey)");
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
    public Boolean updateSortKeyDeps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateName1(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption){
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
                Optional<String> o_type1_Id2 = Optional.ofNullable(thisEdge.getType1_Id()).map(UsrEdgeBaseType::getId2);
                if (o_type1_Id2.isEmpty()) {
                    logger.debug(logPrfx + " --- type1_Id2: null");
                }else{
                    logger.debug(logPrfx + " --- type1_Id2: " + o_type1_Id2.get());
                    sb.append(o_type1_Id2.get());
                    sb.append(SEP3);
                }

                //inst1 required
                Optional<String> o_inst1 = Optional.ofNullable(thisEdge.getInst1());
                if (o_inst1.isEmpty()) {
                    logger.debug(logPrfx + " --- o_inst1: empty");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.debug(logPrfx + " --- o_inst1: " + o_inst1.get());
                    sb.append(o_inst1.get());
                }

                String name1_ = thisEdge.getName1();
                String name1 = o_inst1.get();
                logger.debug(logPrfx + " --- name1:" + name1);

                if (Objects.equals(name1_, name1)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisEdge.setName1(name1);
                    logger.debug(logPrfx + " --- thisEdge.setName1(name1)");
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
    public Boolean updateName1Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateName1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateId2Calc(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisEdge, updOption) || isChanged;

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
    public Boolean updateType1_IdDeps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateType1_IdDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateName1(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Calc(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisEdge, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>inst1</i> field</h1>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateInst1(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption){
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
    public Boolean updateInst1Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInst1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateName1(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Calc(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisEdge, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisEdge, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>desc1</i> field</h1>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateDesc1(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption){
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
    public Boolean updateDesc1Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateDesc1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateSortIdxFrId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateTxt1Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTxt1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    public Boolean updateTs1(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateTs1Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateTs2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateTs2Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt1(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt1Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt2Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt3(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt3Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt4(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
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
    public Boolean updateInt4Deps(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt4Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    public Boolean updateDt1(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateDt1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTm1(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTm1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateIdPartsFrId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt1FrId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt2FrId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt2FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt3FrId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateInt3FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTs1FrId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs1FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTs2FrId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs2FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateTs3FrId2(@NotNull Screen screen, @NotNull UsrEdgeBase thisEdge, @NotNull UpdateOption updOption) {
        String logPrfx = "updateTs3FrId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    public List<String> getStatusList(){
        String logPrfx = "getStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status "
                + " from enty_" + typeOfEdge.getSimpleName() + " e"
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

        //todo
        /*

        Notifications notifications;
        if(screen instanceof UsrEdgeBase0BaseMain
                || screen instanceof UsrEdgeBase0BaseEdit
        ) {
            notifications = ((UsrEdgeBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof UsrEdgeBaseGrpg
            if (!(grpgKey instanceof UsrEdgeBaseGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof UsrEdgeBaseGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer sortIdxCntIsNull = null;
            String sortIdxCntIsNullQry = "select count(e)"
                    + " from enty_UsrEdgeBase e"
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
                    + " from enty_"+ typeOfEdge.getSimpleName() +" e"
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
*/
        logger.trace(logPrfx + " <-- ");
        return sortIdxMax;
    }


}