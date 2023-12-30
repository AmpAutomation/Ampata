package ca.ampautomation.ampata.view.usr.item.base;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBase;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseType;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.item.base.UsrItemBase0Repo;
import ca.ampautomation.ampata.service.usr.item.base.UsrItemBase0Service;
import io.jmix.core.*;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public abstract class UsrItemBase0BaseMain<ItemT extends UsrItemBase, ItemTypeT extends UsrItemBaseType, ItemServiceT extends UsrItemBase0Service, ItemRepoT extends UsrItemBase0Repo, TableT extends Table> extends MasterDetailScreen<ItemT> implements UsrItemBase0BaseComn {

    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<ItemT> typeOfItemT;
    protected Class<ItemTypeT> typeOfItemTypeT;

    @SuppressWarnings("unchecked")
    public UsrItemBase0BaseMain() {
        this.typeOfItemT = (Class<ItemT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfItemTypeT = (Class<ItemTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<ItemT> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    //Service
    protected ItemServiceT service;

    protected ItemServiceT getService(){
        return service;
    }

    public void setService(ItemServiceT service) {
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
    protected ItemRepoT repo;

    protected ItemRepoT getRepo(){
        return repo;
    }

    public void setRepo(ItemRepoT repo) {
        this.repo = repo;
    }


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<ItemTypeT> filterConfig1A_Type1_Id;
    
    
    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<ItemTypeT> tmplt_Type1_IdField;

    @Autowired
    protected TextField<Integer> tmplt_SortIdxField;
    @Autowired
    protected RadioButtonGroup<Integer> tmplt_SortIdxFieldRdo;


    //Main data containers, loaders and table
    @Autowired
    protected CollectionContainer<ItemT> colCntnrMain;
    @Autowired
    protected CollectionLoader<ItemT> colLoadrMain;
    @Autowired
    protected InstanceContainer<ItemT> instCntnrMain;
    @Autowired
    protected TableT tableMain;


    //Type data container and loader
    protected CollectionContainer<ItemTypeT> colCntnrType;
    protected CollectionLoader<ItemTypeT> colLoadrType;


    //Field
    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected EntityComboBox<ItemTypeT> type1_IdField;



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

        colCntnrType = dataComponents.createCollectionContainer(this.typeOfItemTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfItemTypeT.getSimpleName() + " e order by e.sortKey, e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfItemTypeT)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());
        //Field
        type1_IdField.setOptionsContainer(colCntnrType);
        //Template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //Filter
        EntityComboBox<ItemTypeT> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<ItemTypeT>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);

        logger.trace(logPrfx + " <-- ");
    }


    /*
    InitEntityEvent is sent in screens inherited from StandardEditor and MasterDetailScreen
    before the new entity instance is set to edited entity container.
    Use this event listener to initialize default values in the new entity instance
    */
    @Subscribe
    public void onInitEntity(InitEntityEvent<ItemT> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = event.getEntity();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");

    }

    /*
    AfterInitEvent is sent when the screen controller and all its declaratively defined components are created,
    dependency injection is completed, and all components have completed their internal initialization procedures.
    Nested screen fragments (if any) have sent their InitEvent and AfterInitEvent. In this event listener, you can
    create visual and data components and perform additional initialization if it depends on initialized nested
    fragments.
    */
    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        String logPrfx = "onAfterInit";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    /*
    BeforeShowEvent is sent right before the screen is shown, for example, it is not added to the application UI yet.
    Security restrictions are applied to UI components. In this event listener, you can load data,
    check permissions and modify UI components.
    */
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

        //logger.debug(logPrfx + " --- calling colLoadrMain.load() ");
        //colLoadrMain.load();
        //logger.debug(logPrfx + " --- called colLoadrMain.load() ");
        //tableMain.sort("sortKey", Table.SortDirection.ASCENDING);

        //todo
        /*
        
        String currentTenantId = tenantProvider.getCurrentUserTenantId();
        if (!currentTenantId.equals(TenantProvider.NO_TENANT)
                && Strings.isNullOrEmpty(tenantField.getValue())) {
            //tenantField.setEditable(false);
            tenantField.setValue(currentTenantId);
        }
*/
        logger.trace(logPrfx + " <-- ");

    }

    /*
    AfterShowEvent is sent right after the screen is shown, for example, when it is added to the application UI.
    In this event listener, you can show notifications, dialogs or other screens
    */
    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        String logPrfx = "onAfterShow";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- Changed entity: " + event.getEntity());

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<ItemT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisItem is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<ItemT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = event.getItem();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null");
            //todo I observed thisItem is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- called colLoadrType.load() ");
        colLoadrType.load();
        logger.debug(logPrfx + " --- finished colLoadrType.load() ");

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


    @Subscribe("deleteColDeletedColBtn")
    public void onDeleteColDeletedColBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColDeletedColBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPr_Del_ForDeletedRows");
        repo.execPr_Del_ForDeletedRows();
        logger.debug(logPrfx + " --- finished repo.execPr_Del_ForDeletedRows");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemT> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItems.forEach(orig -> {
            ItemT copy = onDuplicateBtnClickHelper(orig);

            ItemT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }

    public ItemT onDuplicateBtnClickHelper(ItemT orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        ItemT copy = metadataTools.copy(orig);
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

        List<ItemT> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<ItemT> chngItems = new ArrayList<>();

        thisItems.forEach(thisItem -> {
            thisItem = dataContext.merge(thisItem);
            if (thisItem != null) {

                Boolean thisItemIsChanged = false;

                thisItemIsChanged = onSetBtnClickHelper(thisItem);
                if(thisItemIsChanged){
                    chngItems.add(thisItem);
                }

                if (thisItemIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisItem).");
                    //dataManager.save(thisItem);
                }
            }
        });
        updateHelper(chngItems);
        logger.trace(logPrfx + " <-- ");
    }

    public Boolean onSetBtnClickHelper(ItemT thisItem){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisItemIsChanged = false;

        if (tmplt_Type1_IdFieldChk.isChecked()
        ) {
            thisItemIsChanged = true;
            thisItem.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItemIsChanged = service.updateId2Calc(this, thisItem, updOption) || thisItemIsChanged;
        thisItemIsChanged = service.updateId2(this, thisItem, updOption) || thisItemIsChanged;
        thisItemIsChanged = service.updateId2Cmp(this, thisItem, updOption) || thisItemIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisItemIsChanged;
    }


    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemT> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<ItemT> chngItems = new ArrayList<>();

        onRebuildSortIdxBtnHelper(thisItems, chngItems);

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper(List<ItemT> thisItems, List<ItemT> chngItems) {
        String logPrfx = "onRebuildSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        // get all adj Items that could be modified
        LogicalCondition logcCond = LogicalCondition.and();

        List<ItemT> adjItems = dataManager.load(typeOfItemT).condition(logcCond).list();

        AtomicInteger sortIdx = new AtomicInteger(0);
        // sort the nodes
        adjItems.sort(getComparator());

        //Update the adjacent nodes
        adjItems.forEach(adjItem -> {
            if (adjItem != null) {
                adjItem = dataContext.merge(adjItem);

                Boolean adjItemIsChanged = false;

                Integer sortIdx_ = adjItem.getSortIdx();
                logger.debug(logPrfx + " --- sortIdx:" + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    adjItem.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- called adjItem.setSortIdx(l_id2)");
                    adjItemIsChanged = true;
                    chngItems.add(adjItem);
                }
                sortIdx.incrementAndGet() ;
            }
        });


        logger.trace(logPrfx + " <-- ");
    }

    void onRebuildSortIdxBtnHelper2(Object grpgKey, ItemT grpItem, List<ItemT> chngItems) {
        String logPrfx = "onRebuildSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemT> thisItems = new ArrayList<ItemT>(tableMain.getSelected().stream().toList());
        if (thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItems.sort(getComparatorThisItem());

        List<ItemT> chngItems = new ArrayList<>();
        AtomicInteger sortIdxMin = new AtomicInteger(0);

        thisItems.forEach(thisItem -> {
            onMoveFrstSortIdxBtnHelper(thisItem, sortIdxMin, chngItems);
        });

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveFrstSortIdxBtnHelper(ItemT thisItem, AtomicInteger sortIdxMin, List<ItemT> chngItems){
        String logPrfx = "onMoveFrstSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisItem = dataContext.merge(thisItem);
        Boolean thisItemIsChanged = false;

        Integer sortIdx_ = thisItem.getSortIdx();
        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin.intValue()){

            // for this node, set sortIdx to minSortIdx
            Integer sortIdx = sortIdxMin.intValue();
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            if (Objects.equals(sortIdx_, sortIdx)) {
                logger.debug(logPrfx + " --- no change detected");
            }else{
                thisItem.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisItem.setSortIdx(sortIdx)");
                thisItemIsChanged = true;
                chngItems.add(thisItem);
            }

            // for all prev nodes, inc sortIdx

            // build condition
            LogicalCondition logcCond = LogicalCondition.and();
            logcCond.add(PropertyCondition.greaterOrEqual("sortIdx",sortIdxMin.intValue()));
            logcCond.add(PropertyCondition.less("sortIdx",sortIdx_));


            // database read
            logger.debug(logPrfx + " --- executing dataManager.load");
            List<ItemT> prevItems = dataManager.load(typeOfItemT).condition(logcCond).list();

            prevItems.forEach(prevItem -> {
                if (prevItem != null) {
                    prevItem = dataContext.merge(prevItem);

                    Boolean prevItemIsChanged = false;

                    Integer prevItemSortIdx_ = prevItem.getSortIdx();
                    if (prevItemSortIdx_ != null
                            && prevItemSortIdx_ >= sortIdxMin.intValue()) {

                        // for prevItem, inc sortIdx
                        Integer prevItemSortIdx = prevItemSortIdx_ + 1;
                        logger.debug(logPrfx + "prevItemSortIdx: " + prevItemSortIdx);

                        prevItem.setSortIdx(prevItemSortIdx);
                        logger.debug(logPrfx + " --- prevItem.setSortIdx(prevItemSortIdx)");
                        prevItemIsChanged = true;
                        chngItems.add(prevItem);
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

        List<ItemT> thisItems = new ArrayList<ItemT>(tableMain.getSelected().stream().toList());
        if (thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItems.sort(getComparatorThisItem());
        List<ItemT> chngItems = new ArrayList<>();

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this);
        if(sortIdxMax == null){
            logger.debug(logPrfx + " --- thisWindow.getSortIdxMax(grpgKey) returned null");
            notifications.create().withCaption("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisItems.forEach(thisItem -> {
            onMovePrevSortIdxBtnHelper(thisItem, sortIdxMin, sortIdxMax, chngItems);
        });

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper(ItemT thisItem, Integer sortIdxMin, Integer sortIdxMax, List<ItemT> chngItems) {
        String logPrfx = "onMovePrevSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItem = dataContext.merge(thisItem);
        Boolean thisItemIsChanged = false;

        Integer sortIdx_ = thisItem.getSortIdx();

        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin){

            // for this node, dec sortIdx
            Integer sortIdx = sortIdx_ - 1;
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            thisItem.setSortIdx(sortIdx);
            logger.debug(logPrfx + " --- called thisItem.setSortIdx(sortIdx)");
            thisItemIsChanged = true;
            chngItems.add(thisItem);

            // for prev node, inc sortIdx

            // build condition
            LogicalCondition logcCond =  LogicalCondition.and();
            logcCond.add(PropertyCondition.equal("sortIdx",sortIdx_ - 1));

            // database read
            logger.debug(logPrfx + " --- executing dataManager.load");
            ItemT prevItem = dataManager.load(typeOfItemT).condition(logcCond).one();

            if(prevItem != null){
                prevItem = dataContext.merge(prevItem);
                Boolean prevItemIsChanged = false;

                Integer prevItemSortIdx_ = prevItem.getSortIdx();
                if (prevItemSortIdx_ != null
                        && prevItemSortIdx_ < sortIdxMax){

                    Integer prevItemSortIdx = prevItemSortIdx_ + 1;
                    logger.debug(logPrfx + "prevItemSortIdx: " + prevItemSortIdx);

                    prevItem.setSortIdx(prevItemSortIdx);
                    logger.debug(logPrfx + " --- prevItem.setSortIdx(prevItemSortIdx)");
                    prevItemIsChanged = true;
                    chngItems.add(prevItem);
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

        List<ItemT> thisItems = new ArrayList<ItemT>(tableMain.getSelected().stream().toList());
        if (thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItems.sort(getComparatorThisItem());

        List<ItemT> chngItems = new ArrayList<>();

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this);

        thisItems.forEach(thisItem -> {
            onMoveNextSortIdxBtnHelper(thisItem, sortIdxMin, sortIdxMax, chngItems);
        });

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveNextSortIdxBtnHelper(ItemT thisItem, Integer sortIdxMin, Integer sortIdxMax, List<ItemT> chngItems) {
        String logPrfx = "onMoveNextSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItem != null) {
            thisItem = dataContext.merge(thisItem);
            Boolean thisItemIsChanged = false;

            Integer sortIdx_ = thisItem.getSortIdx();

            if (sortIdxMax != null
                    && sortIdx_ < sortIdxMax){

                // for this node, inc sortIdx
                Integer sortIdx = sortIdx_ + 1;
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                thisItem.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisItem.setSortIdx(sortIdx)");
                thisItemIsChanged = true;
                chngItems.add(thisItem);


                // for next node, dec sortIdx

                // build condition
                LogicalCondition logcCond =  LogicalCondition.and();
                logcCond.add(PropertyCondition.equal("sortIdx",sortIdx_ + 1));

                // database read
                logger.debug(logPrfx + " --- executing dataManager.load");
                ItemT nextItem = dataManager.load(typeOfItemT).condition(logcCond).one();

                if(nextItem != null){
                    nextItem = dataContext.merge(nextItem);
                    Boolean nextItemIsChanged = false;

                    Integer nextItemSortIdx_ = nextItem.getSortIdx();
                    if (nextItemSortIdx_ != null
                            && nextItemSortIdx_ > sortIdxMin){

                        Integer nextItemSortIdx = nextItemSortIdx_ - 1;
                        logger.debug(logPrfx + " --- nextItemSortIdx: " + sortIdx);

                        nextItem.setSortIdx(nextItemSortIdx);
                        logger.debug(logPrfx + " --- called nextItem.setSortIdx(nextItemSortIdx)");
                        nextItemIsChanged = true;
                        chngItems.add(nextItem);
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

        List<ItemT> thisItems = new ArrayList<ItemT>(tableMain.getSelected().stream().toList());
        if (thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItems.sort(getComparatorThisItem());

        List<ItemT> chngItems = new ArrayList<>();

        Integer sortIdxMax = service.getSortIdxMax(this);
        if(sortIdxMax == null){
            logger.debug(logPrfx + " --- thisWindow.getSortIdxMax(grpgKey) returned null");
            notifications.create().withCaption("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        AtomicInteger sortIdxMaxAtmc = new AtomicInteger(sortIdxMax);

        thisItems.forEach(thisItem -> {
            onMoveLastSortIdxBtnHelper(thisItem, sortIdxMaxAtmc, chngItems);
        });

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveLastSortIdxBtnHelper(ItemT thisItem, AtomicInteger sortIdxMax, List<ItemT> chngItems) {
        String logPrfx = "onMoveLastSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItem != null) {
            thisItem = dataContext.merge(thisItem);

            Boolean thisItemIsChanged = false;

            Integer sortIdx_ = thisItem.getSortIdx();
            if (sortIdx_ != null
                    && sortIdx_ < sortIdxMax.intValue()){

                // for this node, max sortIdx
                Integer sortIdx = sortIdxMax.intValue();
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisItem.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- called thisItem.setSortIdx(sortIdx)");
                    thisItemIsChanged = true;
                    chngItems.add(thisItem);
                }


                // for all next nodes, dec sortIdx

                // build condition
                LogicalCondition logcCond = LogicalCondition.and();
                logcCond.add(PropertyCondition.greater("sortIdx",sortIdx_));
                logcCond.add(PropertyCondition.lessOrEqual("sortIdx", sortIdxMax.intValue()));

                // database read
                logger.debug(logPrfx + " --- executing dataManager.load");
                List<ItemT> nextItems = dataManager.load(typeOfItemT).condition(logcCond).list();

                nextItems.forEach(nextItem -> {
                    if (nextItem != null) {
                        nextItem = dataContext.merge(nextItem);

                        Boolean nextItemIsChanged = false;

                        Integer nextSortIdx_ = nextItem.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ <= sortIdxMax.intValue()) {

                            // for this item, dec sortIdx
                            Integer nextSortIdx = nextItem.getSortIdx() - 1;
                            logger.debug(logPrfx + "nextSortIdx: " + nextSortIdx);

                            nextItem.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextItem.setSortIdx(nextSortIdx)");
                            nextItemIsChanged = true;
                            chngItems.add(nextItem);
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


    protected Comparator<ItemT> getComparator(){
        String logPrfx = "getComparator";
        logger.trace(logPrfx + " --> ");

        Comparator<ItemT> comparator = Comparator.comparing(
                ItemT::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder()));

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }


    protected void updateHelper(List<ItemT> chngItems) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(chngItems != null && !chngItems.isEmpty()) {

            List<ItemT> chngUniqItems = chngItems
                    .stream().distinct().collect(Collectors.toList());

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<ItemT> thisItems = tableMain.getSelected().stream().toList();

            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);

            //Loop throught the items again to update the id2Dup attribute
            chngUniqItems.forEach(thisItem -> {
                //ItemT thisItem = dataContext.merge(thisItem);
                if (thisItem != null) {
                    thisItem = dataContext.merge(thisItem);
                    Boolean thisItemIsChanged = false;

                    thisItemIsChanged = service.updateId2Dup(this, thisItem, updOption) || thisItemIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisItems);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemT> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItems.forEach(thisItem -> {
            if (thisItem != null) {

                thisItem = dataContext.merge(thisItem);;

                boolean isChanged = false;

                isChanged = service.updateCalcVals(this, thisItem, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisItems);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemT> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItems.forEach(thisItem -> {
            if (thisItem != null) {

                thisItem = dataContext.merge(thisItem);;

                boolean isChanged = false;

                isChanged = service.updateId2(this, thisItem, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisItems);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<ItemT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = event.getSource().getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            //todo I observed thisItem is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            ItemT thisItem = instCntnrMain.getItemOrNull();
            if (thisItem == null) {
                logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateId2Deps(this, thisItem, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2(this, thisItem, updOption);
        service.updateId2Deps(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, thisItem, updOption);
        service.updateId2Deps(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Dup(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrItemBase thisFinStmtItm = instCntnrMain.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateSortIdxDeps(this, thisFinStmtItm, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateSortKeyFieldBtn")
    public void onUpdateSortKeyFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateSortKeyFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemBase thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateName1(this, thisItem, updOption);
        service.updateName1Deps(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemBase thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateDesc1(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    protected void addEnteredTextToComboBoxOptionsList(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "addEnteredTextToComboBoxOptionsList";
        logger.trace(logPrfx + " --> ");

        String text = enterPressEvent.getText();
        if (!Objects.equals(text, "<null>")){
            @SuppressWarnings("unchecked")
            // enterPressEvent.getSource is directly connected to a ComboBox
            ComboBox<String> cb = (ComboBox<String>) enterPressEvent.getSource();

            List<String> list;
            // this comboBox options list is created with a call to setOptionsList(List)
            // see onUpdateFinStmtItm1_Desc1Field
            // therefore cb.getOptions is type ListOptions
            ListOptions<String> listOptions = (ListOptions<String>) cb.getOptions();
            if (listOptions != null && !listOptions.getItemsCollection().isEmpty()) {
                list = (List<String>) listOptions.getItemsCollection();
            } else {
                list = new ArrayList<String>();
            }

            list.add(text);
            logger.trace(logPrfx + " --- called list.add( " + text + ")");

            cb.setOptionsList(list);

            notifications.create()
                    .withCaption("Added " + text + " to list.")
                    .show();
        }

    }

    public Notifications getNotifications(){
        return notifications;
    }

}