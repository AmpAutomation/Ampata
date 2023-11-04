package ca.ampautomation.ampata.screen.usr.item.base;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseType;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseGrpg;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseTypeGrpg;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.item.base.UsrItemBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.item.base.UsrItemBase0Type0Service;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public  abstract class UsrItemBase0Type0BaseMain<ItemTypeT extends UsrItemBaseType, ItemTypeServiceT extends UsrItemBase0Type0Service, ItemTypeRepoT extends UsrItemBase0Type0Repo, TableT extends Table> extends MasterDetailScreen<ItemTypeT> {

    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<ItemTypeT> typeOfItemTypeT;

    @SuppressWarnings("unchecked")
    public UsrItemBase0Type0BaseMain() {
        this.typeOfItemTypeT = (Class<ItemTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<ItemTypeT> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    //Service
    protected ItemTypeServiceT service;

    protected ItemTypeServiceT getService(){
        return service;
    }

    public void setService(ItemTypeServiceT service) {
        this.service = service;
    }

    @Autowired
    protected DataComponents dataComponents;

    @Autowired
    protected FetchPlans fetchPlans;

    @Autowired
    protected DataContext dataContext;

    @Autowired
    protected DataManager dataManager;

    @Autowired
    protected Metadata metadata;

    @Autowired
    protected MetadataTools metadataTools;

    @Autowired
    protected Notifications notifications;


    //Repository
    protected ItemTypeRepoT repo;

    protected ItemTypeRepoT getRepo(){
        return repo;
    }

    public void setRepo(ItemTypeRepoT repo) {
        this.repo = repo;
    }


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected  PropertyFilter<ItemTypeT> filterConfig1A_Parent1_Id;


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;


    //Template
    @Autowired
    protected TextField<Integer> tmplt_SortIdxField;
    @Autowired
    protected RadioButtonGroup<Integer> tmplt_SortIdxFieldRdo;


    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<ItemTypeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<ItemTypeT> colCntnrMain;
    @Autowired
    protected InstanceContainer<ItemTypeT> instCntnrMain;
    @Autowired
    protected TableT tableMain;


    //Other data loaders, containers and tables



    //Field
    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected TagField<UsrItemGenTag> genTags1_IdField;

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_SortIdxFieldRdo.setOptionsMap(map1);

        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put(UpdateOption.SKIP.toString(), UpdateOption.SKIP.toInt());
        map2.put(UpdateOption.LOCAL.toString(), UpdateOption.LOCAL.toInt());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST.toString(), UpdateOption.LOCAL__REF_TO_EXIST.toInt());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST_NEW.toString(), UpdateOption.LOCAL__REF_TO_EXIST_NEW.toInt());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toString(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toInt());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toString(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toInt());
        updateColItemCalcValsOption.setOptionsMap(map2);
        updateColItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());
        updateInstItemCalcValsOption.setOptionsMap(map2);
        updateInstItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());


        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        //tableMain.sort("sortKey", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<ItemTypeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisItemType is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<ItemTypeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = event.getItem();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null");
            //todo I observed thisItemType is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (thisItemType.getClassName() == null || thisItemType.getClassName().isBlank()){
            thisItemType.setClassName(typeOfItemTypeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfItemTypeT.getSimpleName());
        }
*/

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPr_Upd_AllCalcVals_ForAllRows");
        repo.execPr_Upd_AllCalcVals_ForAllRows();
        logger.debug(logPrfx + " --- finished repo.execPr_Upd_AllCalcVals_ForAllRows");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = tableMain.getSelected().stream().toList();
        if (thisItemTypes == null || thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItemTypes.forEach(orig -> {
            ItemTypeT copy = onDuplicateBtnClickHelper(orig);

            ItemTypeT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }


    public ItemTypeT onDuplicateBtnClickHelper(ItemTypeT orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        ItemTypeT copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (orig.getId2().equals(copy.getId2())){
            copy.setId2(copy.getId2() + " Copy");
            copy.setId2Calc(copy.getId2());
        }
        logger.trace(logPrfx + " <-- ");
        return copy;
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = tableMain.getSelected().stream().toList();
        if (thisItemTypes == null || thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<ItemTypeT> chngItems = new ArrayList<>();
        List<ItemTypeT> finalChngItems = chngItems;

        thisItemTypes.forEach(thisItemType -> {
            thisItemType = dataContext.merge(thisItemType);
            if (thisItemType != null) {

                Boolean thisItemTypeIsChanged = false;

                thisItemTypeIsChanged = onSetBtnClickHelper(thisItemType);

                if (thisItemTypeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisItemType).");
                    //dataManager.save(thisItemType);
                }
            }
        });
        chngItems = finalChngItems.stream().distinct().collect(Collectors.toList());
        updateHelper(chngItems);
        logger.trace(logPrfx + " <-- ");
    }

    public Boolean onSetBtnClickHelper(ItemTypeT thisItemType){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisItemTypeIsChanged = false;

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItemTypeIsChanged = service.updateId2Calc(this, thisItemType, updOption) || thisItemTypeIsChanged;
        thisItemTypeIsChanged = service.updateId2(this, thisItemType, updOption) || thisItemTypeIsChanged;
        thisItemTypeIsChanged = service.updateId2Cmp(this, thisItemType, updOption) || thisItemTypeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisItemTypeIsChanged;
    }



    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = tableMain.getSelected().stream().toList();
        if (thisItemTypes == null || thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<ItemTypeT> chngItemTypes = new ArrayList<>();

        Map<?, List<ItemTypeT>> grpgMap = getGrpgMap(thisItemTypes);

        grpgMap.forEach((key, nodeTypes) -> {
            onRebuildSortIdxBtnHelper(key, nodeTypes, chngItemTypes);

        });

        updateHelper(chngItemTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper(Object grpgKey, List<ItemTypeT> grpgItemTypes, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onRebuildSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        // get all adj Items that could be modified
        LogicalCondition logcCond = LogicalCondition.and();

        //Add condition for this group
        Condition grpgCond = getConditionGrpg(grpgKey);
        if (grpgCond != null) {
            logcCond.add(grpgCond);
        }

        List<ItemTypeT> adjItemTypes = dataManager.load(typeOfItemTypeT).condition(logcCond).list();

        AtomicInteger sortIdx = new AtomicInteger(0);
        // sort the nodes
        adjItemTypes.sort(getComparator());

        //Update the adjacent nodes
        adjItemTypes.forEach(adjItemType -> {
            if (adjItemType != null) {
                adjItemType = dataContext.merge(adjItemType);

                Boolean adjItemTypeIsChanged = false;

                Integer sortIdx_ = adjItemType.getSortIdx();
                logger.debug(logPrfx + " --- sortIdx:" + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    adjItemType.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- called adjItemType.setSortIdx(id2)");
                    adjItemTypeIsChanged = true;
                    chngItemTypes.add(adjItemType);
                }
                sortIdx.incrementAndGet() ;
            }
        });


        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper2(Object grpgKey, ItemTypeT grpItemType, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onRebuildSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = new ArrayList<ItemTypeT>(tableMain.getSelected().stream().toList());
        if (thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItemTypes.sort(getComparatorThisItem());

        List<ItemTypeT> chngItemTypes = new ArrayList<>();

        Map<?, List<ItemTypeT>> grpgMap = getGrpgMap(thisItemTypes);

        grpgMap.forEach((grpgKey, grpgItemTypes) -> {
            onMoveFrstSortIdxBtnHelper2(grpgKey, grpgItemTypes, chngItemTypes);
        });

        updateHelper(chngItemTypes);

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveFrstSortIdxBtnHelper2(Object grpgKey, List<ItemTypeT> grpgItemTypes, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onMoveFrstSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        grpgItemTypes.forEach(thisItem -> {
            onMoveFrstSortIdxBtnHelper(grpgKey, thisItem, sortIdxMin, chngItemTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveFrstSortIdxBtnHelper(Object grpgKey, ItemTypeT thisItemType, AtomicInteger sortIdxMin, List<ItemTypeT> chngItemTypes){
        String logPrfx = "onMoveFrstSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisItemType = dataContext.merge(thisItemType);
        Boolean thisItemTypeIsChanged = false;

        Integer sortIdx_ = thisItemType.getSortIdx();
        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin.intValue()){

            // for this node, set sortIdx to minSortIdx
            Integer sortIdx = sortIdxMin.intValue();
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            if (Objects.equals(sortIdx_, sortIdx)) {
                logger.debug(logPrfx + " --- no change detected");
            }else{
                thisItemType.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisItemType.setSortIdx(sortIdx)");
                thisItemTypeIsChanged = true;
                chngItemTypes.add(thisItemType);
            }

            // for all prev nodes, inc sortIdx

            // build condition
            LogicalCondition logcCond = LogicalCondition.and();
            logcCond.add(PropertyCondition.greaterOrEqual("sortIdx",sortIdxMin.intValue()));
            logcCond.add(PropertyCondition.less("sortIdx",sortIdx_));

            //Add condition for this group
            Condition grpgCond = getConditionGrpg(grpgKey);
            if (grpgCond != null) {
                logcCond.add(grpgCond);
            }

            // database read
            logger.debug(logPrfx + " --- executing dataManager.load");
            List<ItemTypeT> prevItemTypes = dataManager.load(typeOfItemTypeT).condition(logcCond).list();

            prevItemTypes.forEach(prevItemType -> {
                if (prevItemType != null) {
                    prevItemType = dataContext.merge(prevItemType);

                    Boolean prevItemTypeIsChanged = false;

                    Integer prevItemTypeSortIdx_ = prevItemType.getSortIdx();
                    if (prevItemTypeSortIdx_ != null
                            && prevItemTypeSortIdx_ >= sortIdxMin.intValue()) {

                        // for prevItemType, inc sortIdx
                        Integer prevItemTypeSortIdx = prevItemTypeSortIdx_ + 1;
                        logger.debug(logPrfx + "prevItemTypeSortIdx: " + prevItemTypeSortIdx);

                        prevItemType.setSortIdx(prevItemTypeSortIdx);
                        logger.debug(logPrfx + " --- prevItemType.setSortIdx(prevItemTypeSortIdx)");
                        prevItemTypeIsChanged = true;
                        chngItemTypes.add(prevItemType);
                    }
                }
            });

            sortIdxMin.incrementAndGet();

            if (dataContext.hasChanges()) {
                // database write
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("movePrevSortIdxBtn")
    public void onMovePrevSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMovePrevSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = new ArrayList<ItemTypeT>(tableMain.getSelected().stream().toList());
        if (thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItemTypes.sort(getComparatorThisItem());
        List<ItemTypeT> chngItemTypes = new ArrayList<>();

        Map<?, List<ItemTypeT>> grpgMap = getGrpgMap(thisItemTypes);

        grpgMap.forEach((grpgKey, grpgItemTypes) -> {
            onMovePrevSortIdxBtnHelper2(grpgKey, grpgItemTypes, chngItemTypes);
        });

        updateHelper(chngItemTypes);

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper2(Object grpgKey, List<ItemTypeT> grpgItemTypes, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onMovePrevSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);
        if(sortIdxMax == null){
            logger.debug(logPrfx + " --- thisWindow.getSortIdxMax(grpgKey) returned null");
            notifications.create().withCaption("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        grpgItemTypes.forEach(thisItemType -> {
            onMovePrevSortIdxBtnHelper(grpgKey, thisItemType, sortIdxMin, sortIdxMax, chngItemTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper(Object grpgKey, ItemTypeT thisItemType, Integer sortIdxMin, Integer sortIdxMax, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onMovePrevSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItemType = dataContext.merge(thisItemType);
        Boolean thisItemTypeIsChanged = false;

        Integer sortIdx_ = thisItemType.getSortIdx();

        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin){

            // for this node, dec sortIdx
            Integer sortIdx = sortIdx_ - 1;
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            thisItemType.setSortIdx(sortIdx);
            logger.debug(logPrfx + " --- called thisItemType.setSortIdx(sortIdx)");
            thisItemTypeIsChanged = true;
            chngItemTypes.add(thisItemType);

            // for prev node, inc sortIdx

            // build condition
            LogicalCondition logcCond =  LogicalCondition.and();
            logcCond.add(PropertyCondition.equal("sortIdx",sortIdx_ - 1));

            //Add condition for this group
            Condition grpgCond = getConditionGrpg(grpgKey);
            if (grpgCond != null) {
                logcCond.add(grpgCond);
            }

            // database read
            logger.debug(logPrfx + " --- executing dataManager.load");
            ItemTypeT prevItemType = dataManager.load(typeOfItemTypeT).condition(logcCond).one();

            if(prevItemType != null){
                prevItemType = dataContext.merge(prevItemType);
                Boolean prevItemTypeIsChanged = false;

                Integer prevItemTypeSortIdx_ = prevItemType.getSortIdx();
                if (prevItemTypeSortIdx_ != null
                        && prevItemTypeSortIdx_ < sortIdxMax){

                    Integer prevItemSortIdx = prevItemTypeSortIdx_ + 1;
                    logger.debug(logPrfx + "prevItemSortIdx: " + prevItemSortIdx);

                    prevItemType.setSortIdx(prevItemSortIdx);
                    logger.debug(logPrfx + " --- prevItemType.setSortIdx(prevItemSortIdx)");
                    prevItemTypeIsChanged = true;
                    chngItemTypes.add(prevItemType);
                }
            }

            // database write
            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();
            }

        }

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("moveNextSortIdxBtn")
    public void onMoveNextSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveNextSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = new ArrayList<ItemTypeT>(tableMain.getSelected().stream().toList());
        if (thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItemTypes.sort(getComparatorThisItem());

        List<ItemTypeT> chngItemTypes = new ArrayList<>();

        Map<?, List<ItemTypeT>> grpgMap = getGrpgMap(thisItemTypes);

        grpgMap.forEach((grpgKey, grpgItemTypes) -> {
            onMoveNextSortIdxBtnHelper2(grpgKey, grpgItemTypes, chngItemTypes);
        });

        updateHelper(chngItemTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveNextSortIdxBtnHelper2(Object grpgKey, List<ItemTypeT> grpgItemTypes, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onMoveNextSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);

        grpgItemTypes.forEach(thisItemType -> {
            onMoveNextSortIdxBtnHelper(grpgKey, thisItemType, sortIdxMin, sortIdxMax, chngItemTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveNextSortIdxBtnHelper(Object grpgKey, ItemTypeT thisItemType, Integer sortIdxMin, Integer sortIdxMax, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onMoveNextSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItemType != null) {
            thisItemType = dataContext.merge(thisItemType);
            Boolean thisItemTypeIsChanged = false;

            Integer sortIdx_ = thisItemType.getSortIdx();

            if (sortIdxMax != null
                    && sortIdx_ < sortIdxMax){

                // for this node, inc sortIdx
                Integer sortIdx = sortIdx_ + 1;
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                thisItemType.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisItemType.setSortIdx(sortIdx)");
                thisItemTypeIsChanged = true;
                chngItemTypes.add(thisItemType);


                // for next node, dec sortIdx

                // build condition
                LogicalCondition logcCond =  LogicalCondition.and();
                logcCond.add(PropertyCondition.equal("sortIdx",sortIdx_ + 1));

                //Add condition for this group
                Condition grpgCond = getConditionGrpg(grpgKey);
                if (grpgCond != null) {
                    logcCond.add(grpgCond);
                }

                // database read
                logger.debug(logPrfx + " --- executing dataManager.load");
                ItemTypeT nextItemType = dataManager.load(typeOfItemTypeT).condition(logcCond).one();

                if(nextItemType != null){
                    nextItemType = dataContext.merge(nextItemType);
                    Boolean nextItemTypeIsChanged = false;

                    Integer nextItemTypeSortIdx_ = nextItemType.getSortIdx();
                    if (nextItemTypeSortIdx_ != null
                            && nextItemTypeSortIdx_ > sortIdxMin){

                        Integer nextItemTypeSortIdx = nextItemTypeSortIdx_ - 1;
                        logger.debug(logPrfx + " --- nextItemTypeSortIdx: " + sortIdx);

                        nextItemType.setSortIdx(nextItemTypeSortIdx);
                        logger.debug(logPrfx + " --- called nextItemType.setSortIdx(nextItemTypeSortIdx)");
                        nextItemTypeIsChanged = true;
                        chngItemTypes.add(nextItemType);
                    }

                }

                if (dataContext.hasChanges()) {
                    logger.debug(logPrfx + " --- executing dataContext.commit().");
                    dataContext.commit();
                }

            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveLastSortIdxBtn")
    public void onMoveLastSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveLastSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = new ArrayList<ItemTypeT>(tableMain.getSelected().stream().toList());
        if (thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItemTypes.sort(getComparatorThisItem());

        List<ItemTypeT> chngItemTypes = new ArrayList<>();

        Map<?, List<ItemTypeT>> grpgMap = getGrpgMap(thisItemTypes);

        grpgMap.forEach((grpgKey, grpgItemTypes) -> {
            onMoveLastSortIdxBtnHelper2(grpgKey, grpgItemTypes, chngItemTypes);
        });

        updateHelper(chngItemTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveLastSortIdxBtnHelper2(Object grpgKey, List<ItemTypeT> grpgItemTypes, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onMoveLastSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);
        if(sortIdxMax == null){
            logger.debug(logPrfx + " --- thisWindow.getSortIdxMax(grpgKey) returned null");
            notifications.create().withCaption("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        AtomicInteger sortIdxMaxAtmc = new AtomicInteger(sortIdxMax);

        grpgItemTypes.forEach(thisItemType -> {
            onMoveLastSortIdxBtnHelper(grpgKey, thisItemType, sortIdxMaxAtmc, chngItemTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveLastSortIdxBtnHelper(Object grpgKey, ItemTypeT thisItemType, AtomicInteger sortIdxMax, List<ItemTypeT> chngItemTypes) {
        String logPrfx = "onMoveLastSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItemType != null) {
            thisItemType = dataContext.merge(thisItemType);

            Boolean thisItemTypeIsChanged = false;

            Integer sortIdx_ = thisItemType.getSortIdx();
            if (sortIdx_ != null
                    && sortIdx_ < sortIdxMax.intValue()){

                // for this node, max sortIdx
                Integer sortIdx = sortIdxMax.intValue();
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisItemType.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- called thisItemType.setSortIdx(sortIdx)");
                    thisItemTypeIsChanged = true;
                    chngItemTypes.add(thisItemType);
                }


                // for all next nodes, dec sortIdx

                // build condition
                LogicalCondition logcCond = LogicalCondition.and();
                logcCond.add(PropertyCondition.greater("sortIdx",sortIdx_));
                logcCond.add(PropertyCondition.lessOrEqual("sortIdx", sortIdxMax.intValue()));

                //Add condition for this group
                Condition grpgCond = getConditionGrpg(grpgKey);
                if (grpgCond != null) {
                    logcCond.add(grpgCond);
                }

                // database read
                logger.debug(logPrfx + " --- executing dataManager.load");
                List<ItemTypeT> nextItemTypes = dataManager.load(typeOfItemTypeT).condition(logcCond).list();

                nextItemTypes.forEach(nextItemType -> {
                    if (nextItemType != null) {
                        nextItemType = dataContext.merge(nextItemType);

                        Boolean nextItemTypeIsChanged = false;

                        Integer nextSortIdx_ = nextItemType.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ <= sortIdxMax.intValue()) {

                            // for this item, dec sortIdx
                            Integer nextSortIdx = nextItemType.getSortIdx() - 1;
                            logger.debug(logPrfx + "nextSortIdx: " + nextSortIdx);

                            nextItemType.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextItemType.setSortIdx(nextSortIdx)");
                            nextItemTypeIsChanged = true;
                            chngItemTypes.add(nextItemType);
                        }
                    }
                });

                sortIdxMax.decrementAndGet();

                // database write
                if (dataContext.hasChanges()) {
                    logger.debug(logPrfx + " --- executing dataContext.commit().");
                    dataContext.commit();
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    protected Map<?, List<ItemTypeT>> getGrpgMap(List<ItemTypeT> thisItemTypes){
        String logPrfx = "getGrpMap";
        logger.trace(logPrfx + " --> ");

        Map<UsrItemBaseTypeGrpg, List<ItemTypeT>> grpMap = thisItemTypes.stream()
                .collect(groupingBy(nodeType -> new UsrItemBaseTypeGrpg()));

        logger.trace(logPrfx + " <-- ");
        return grpMap;
    }

    protected Condition getConditionGrpg(Object grpgKey){
        String logPrfx = "getConditionGrpg";
        logger.trace(logPrfx + " --> ");

        // Ensure grpgKey is instanceof UsrItemBaseGrpg
        if (!(grpgKey instanceof UsrItemBaseGrpg l_grpgKey)){
            logger.trace(logPrfx + " --- grpgKey is not instanceof UsrItemBaseGrpg");
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        //todo
        PropertyCondition cond = null;
        //Add condition for this group
        //cond = PropertyCondition.equal("parent1_Id",l_grpgKey.parent1_Id());

        logger.trace(logPrfx + " <-- ");
        return cond;
    }

    protected Comparator<ItemTypeT> getComparator(){
        String logPrfx = "getComparator";
        logger.trace(logPrfx + " --> ");

        Comparator<ItemTypeT> comparator = Comparator.comparing(
                ItemTypeT::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder()));

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }

    protected void updateHelper(List<ItemTypeT> chngItems) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");


        if(chngItems != null && !chngItems.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<ItemTypeT> thisItemTypes = tableMain.getSelected().stream().toList();

            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);

            //Loop throught the items again to update the id2Dup attribute
            chngItems.forEach(thisItemType -> {
                //ItemTypeT thisItemType = dataContext.merge(thisItemType);
                if (thisItemType != null) {
                    thisItemType = dataContext.merge(thisItemType);
                    Boolean thisItemIsChanged = false;

                    thisItemIsChanged = service.updateId2Dup(this, thisItemType, updOption) || thisItemIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisItemTypes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = tableMain.getSelected().stream().toList();
        if (thisItemTypes == null || thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItemTypes.forEach(thisItemType -> {
            if (thisItemType != null) {

                thisItemType = dataContext.merge(thisItemType);;

                boolean isChanged = false;

                isChanged = service.updateCalcVals(this, thisItemType, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisItemTypes);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemTypeT> thisItemTypes = tableMain.getSelected().stream().toList();
        if (thisItemTypes == null || thisItemTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItemTypes.forEach(thisItemType -> {
            if (thisItemType != null) {

                thisItemType = dataContext.merge(thisItemType);;

                boolean isChanged = false;

                isChanged = service.updateId2(this, thisItemType, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisItemTypes);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<ItemTypeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = event.getSource().getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            //todo I observed thisItemType is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (StringUtils.isEmpty(thisItemType.getClassName())) {
            thisItemType.setClassName(typeOfItemTypeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfItemTypeT.getSimpleName());
        }
*/

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisItemType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
            if (thisItemType == null) {
                logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateId2Deps(this, thisItemType, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2(this, thisItemType, updOption);
        service.updateId2Deps(this, thisItemType, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, thisItemType, updOption);
        service.updateId2Deps(this, thisItemType, updOption);

        logger.debug(logPrfx + " --- id2Calc: " + thisItemType.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisItemType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Dup(this, thisItemType, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            ItemTypeT thisItem = instCntnrMain.getItemOrNull();
            if (thisItem == null) {
                logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateSortIdxDeps(this, thisItem, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateSortKeyFieldBtn")
    public void onUpdateSortKeyFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateSortKeyFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisItemType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateName1(this, thisItemType, updOption);
        service.updateName1Deps(this, thisItemType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemTypeT thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        //todo
        //service.updateDesc1(thisItemType, updOption);

        logger.trace(logPrfx + " <-- ");
    }


}
