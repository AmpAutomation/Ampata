package ca.ampautomation.ampata.service.usr.item.base;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseType;
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
import java.util.Objects;
import java.util.Optional;

@Component("bean_UsrItemBaseTypeService")
public class UsrItemBase0Type0Service implements Globals {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class typeOfItemType;

    public UsrItemBase0Type0Service() {
        this.typeOfItemType = UsrItemBaseType.class;
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
    public Boolean updateCalcVals(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateName1(screen, thisItemType, updOption) || isChanged;

        isChanged = updateId2Calc(screen, thisItemType, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisItemType, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisItemType, updOption) || isChanged;
        isChanged = updateSortKey(screen, thisItemType, updOption) || isChanged;

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
    public Boolean updateId2(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
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

                String id2_ = thisItemType.getId2();
                String id2 = thisItemType.getId2Calc();
                logger.debug(logPrfx + " --- id2:" + id2);

                if(Objects.equals(id2_, id2)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisItemType.setId2(id2);
                    logger.debug(logPrfx + " --- called thisItemType.setId2(id2)");
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
    public Boolean updateId2Deps(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisItemType, updOption) || isChanged;

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
    public Boolean updateId2Calc(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
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
                Optional<String> o_name1 = Optional.ofNullable(thisItemType.getName1());
                if(o_name1.isEmpty()) {
                    logger.trace(logPrfx + " --- name1 is null");
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }else{
                    logger.trace(logPrfx + " --- name1 :" + o_name1.get());
                    sb.append(o_name1.get());
                }

                String id2Calc_ = thisItemType.getId2Calc();
                String id2Calc = sb.toString();
                logger.debug(logPrfx + " --- id2Calc:" + id2Calc);

                if (Objects.equals(id2Calc_, id2Calc)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisItemType.setId2Calc(id2Calc);
                    logger.debug(logPrfx + " --- called thisItemType.setId2Calc(id2Calc)");
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
    public Boolean updateId2CalcDeps(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2CalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisItemType, updOption) || isChanged;

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
    public Boolean updateId2Cmp(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisItemType.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisItemType.getId2(),thisItemType.getId2Calc());
        logger.debug(logPrfx + " --- id2Cmp:" + id2Cmp);

        if (Objects.equals(id2Cmp_, id2Cmp)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisItemType.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- called thisItemType.setId2Cmp(id2Cmp)");
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
    public Boolean updateId2Dup(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
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
                Integer id2Dup_ = thisItemType.getId2Dup();
                Integer id2Dup;
                if (thisItemType.getId2() != null){
                    String id2Qry = "select count(e)"
                            + " from enty_" + thisItemType.getClass().getSimpleName() + " e"
                            + " where e.id2 = :id2"
                            + " and e.id <> :id";
                    try{
                        id2Dup = this.dataManager.loadValue(id2Qry, Integer.class)
                                .store("main")
                                .parameter("id",thisItemType.getId())
                                .parameter("id2",thisItemType.getId2())
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
                        thisItemType.setId2Dup(id2Dup);
                        logger.debug(logPrfx + " --- called thisItemType.setId2Cmp(l_id2Cmp_)");
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
    public Boolean updateSortIdxDeps(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
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
    public Boolean updateId2DupDeps(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
        String logPrfx = "updateId2DupDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(screen, thisItemType, updOption) || isChanged;

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
    public Boolean updateSortKey(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
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
                Optional<Integer> o_sortIdx = Optional.ofNullable(thisItemType.getSortIdx());
                if(o_sortIdx.isEmpty()) {
                    logger.trace(logPrfx + " --- sortIdx is null");
                }else{
                    logger.trace(logPrfx + " --- sortIdx :" + o_sortIdx.get());
                }
                sb.append(String.format(FRMT_SORTKEY, o_sortIdx.orElse(0)));
                sb.insert(0,SEP4);

                String sortKey_ = thisItemType.getSortKey();
                String sortKey;

                sortKey = sb.toString();
                logger.debug(logPrfx + " --- sortKey:" + sortKey);

                if (Objects.equals(sortKey_, sortKey)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisItemType.setSortKey(sortKey);
                    logger.debug(logPrfx + " --- called thisItemType.setSortKey(sortKey)");
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
    public Boolean updateSortKeyDeps(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
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
    public Boolean updateName1(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption){
        String logPrfx = "updateName1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

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
    public Boolean updateName1Deps(@NotNull Screen screen, @NotNull UsrItemBaseType thisItemType, @NotNull UpdateOption updOption) {
        String logPrfx = "updateName1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateId2Calc(screen, thisItemType, updOption) || isChanged;
        isChanged = updateId2Cmp(screen, thisItemType, updOption) || isChanged;
        isChanged = updateId2Dup(screen, thisItemType, updOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    public Integer getSortIdxMax(@NotNull Screen screen, Object grpgKey) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;

/*
        Notifications notifications;
        if(screen instanceof UsrItemBase0BaseMain
                || screen instanceof UsrItemBase0BaseEdit) {
            notifications = ((UsrItemBase0BaseComn) screen).getNotifications();

            // Ensure grpgKey is instanceof UsrItemBaseGrpg
            if (!(grpgKey instanceof UsrItemBaseGrpg l_grpgKey)){
                logger.trace(logPrfx + " --- grpgKey is not instanceof UsrItemBaseGrpg");
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            Integer sortIdxCntIsNull = null;
            String sortIdxCntIsNullQry = "select count(e)"
                    + " from enty_UsrItemBaseType e"
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
                    + " from enty_"+ typeOfItemType.getSimpleName() +" e"
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
