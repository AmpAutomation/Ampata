package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.*;
import ca.ampautomation.ampata.entity.sys.SysNode;
import ca.ampautomation.ampata.entity.sys.SysNodeRepository;
import ca.ampautomation.ampata.entity.sys.SysNodeType;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.UsrNodeRepository;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.propertyfilter.SingleFilterSupport;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;
import java.util.stream.Collectors;

@UiController("ampata_SysFinCurcy.browse2")
@UiDescriptor("sys-fin-curcy-browse2.xml")
@LookupComponent("table")
public class SysFinCurcyBrowse2 extends MasterDetailScreen<SysNode> {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SysNodeRepository repo;

    @Autowired
    private DataComponents dataComponents;

    @Autowired
    private FetchPlans fetchPlans;

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private Notifications notifications;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<SysNodeType> filterConfig1A_Type1_Id;

    //Toolbar

    //Template
    @Autowired
    protected EntityComboBox<SysNodeType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;

    //Main data loader
    @Autowired
    private DataLoader finCurcysDl;

    //Main data loaders and containers
    @Autowired
    private CollectionContainer<SysNode> finCurcysDc;
    @Autowired
    private InstanceContainer<SysNode> finCurcyDc;

    //Main table
    @Autowired
    private Table<SysNode> table;


    //Other data loaders and containers
    private CollectionLoader<SysNodeType> finCurcyTypesDl;
    private CollectionContainer<SysNodeType> finCurcyTypesDc;



    //Field
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<SysNodeType> type1_IdField;


    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
*/
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");



        finCurcyTypesDc = dataComponents.createCollectionContainer(SysNodeType.class);
        finCurcyTypesDl = dataComponents.createCollectionLoader();
        finCurcyTypesDl.setQuery("select e from ampata_SysNodeType e where e.className = 'SysFinCurcy' order by e.id2");
        FetchPlan finCurcyTypesFp = fetchPlans.builder(SysNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finCurcyTypesDl.setFetchPlan(finCurcyTypesFp);
        finCurcyTypesDl.setContainer(finCurcyTypesDc);
        finCurcyTypesDl.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(finCurcyTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(finCurcyTypesDc);


        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- Changed entity: " + event.getEntity());

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
    InitEntityEvent is sent in screens inherited from StandardEditor and MasterDetailScreen
    before the new entity instance is set to edited entity container.
    Use this event listener to initialize default values in the new entity instance
    */
    @Subscribe
    public void onInitEntity(InitEntityEvent<UsrNode> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = event.getEntity();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinAcct.setClassName("FinCurcy");
        logger.debug(logPrfx + " --- className: FinCurcy");

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

        table.sort("sortKey", Table.SortDirection.ASCENDING);

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

    @Subscribe(id = "finCurcysDc", target = Target.DATA_CONTAINER)
    public void onFinCurcysDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onFinCurcysDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinCurcy = event.getItem();
        if (thisFinCurcy == null) {
            logger.debug(logPrfx + " --- thisFinCurcy is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinCurcy.setClassName("FinCurcy");
        logger.debug(logPrfx + " --- className: FinCurcy");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        finCurcyTypesDl.load();
        logger.debug(logPrfx + " --- called finCurcyTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execNodePrUpdNative()");
        repo.execNodePrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execNodePrUpdNative()");

        logger.debug(logPrfx + " --- executing repo.execFinCurcyPrUpdNative()");
        repo.execFinCurcyPrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execFinCurcyPrUpdNative()");

        logger.debug(logPrfx + " --- executing finCurcysDl.load()");
        finCurcysDl.load();
        logger.debug(logPrfx + " --- finished finCurcysDl.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinCurcys = table.getSelected().stream().toList();
        if (thisFinCurcys == null || thisFinCurcys.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcy is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNode> sels = new ArrayList<>();

        thisFinCurcys.forEach(orig -> {
            SysNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            if (copy.getBeg1() == null) {
                copy.setBeg1(dataManager.create(HasDate.class));}

            updateCalcVals(copy);

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setName1(copy.getName1() == null ? "Copy" : copy.getName1() + " Copy");
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }


            SysNode savedCopy = dataManager.save(copy);
            finCurcysDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"

            );
            sels.add(savedCopy);

        });
        table.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinCurcys = table.getSelected().stream().toList();
        if (thisFinCurcys == null || thisFinCurcys.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcy is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<SysNode> chngFinCurcys = new ArrayList<>();
        List<SysNode> finalChngFinCurcys = chngFinCurcys;

        thisFinCurcys.forEach(thisFinCurcy -> {
            thisFinCurcy = dataContext.merge(thisFinCurcy);
            if (thisFinCurcy != null) {

                boolean thisFinCurcyIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinCurcy.setType1_Id(tmplt_Type1_IdField.getValue());
                    thisFinCurcyIsChanged = true;
                    finalChngFinCurcys.add(thisFinCurcy);
                }

            }
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngFinCurcys = finalChngFinCurcys.stream().distinct().collect(Collectors.toList());
        updateFinCurcyHelper(chngFinCurcys);

        logger.trace(logPrfx + " <-- ");
    }
    
    private void updateFinCurcyHelper(List<SysNode> chngFinCurcys) {
        String logPrfx = "updateFinCurcyHelper";
        logger.trace(logPrfx + " --> ");

        if(chngFinCurcys != null && !chngFinCurcys.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing finCurcysDl.load().");
            finCurcysDl.load();

            List<SysNode> thisFinCurcys = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngFinCurcys.forEach(thisFinCurcy -> {
                //SysNode thisTrackedFinCurcy = dataContext.merge(thisFinCurcy);
                if (thisFinCurcy != null) {
                    thisFinCurcy = dataContext.merge(thisFinCurcy);
                    Boolean thisFinCurcyIsChanged = false;

                    thisFinCurcyIsChanged = updateId2Dup(thisFinCurcy) || thisFinCurcyIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing finCurcysDl.load().");
                finCurcysDl.load();

                table.setSelected(thisFinCurcys);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinCurcys = table.getSelected().stream().toList();
        if (thisFinCurcys == null || thisFinCurcys.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcy is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinCurcys.forEach(thisFinCurcy -> {
            if (thisFinCurcy != null) {

                thisFinCurcy = dataContext.merge(thisFinCurcy);

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinCurcy);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finCurcysDl.load().");
            finCurcysDl.load();

            table.setSelected(thisFinCurcys);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcy = finCurcyDc.getItemOrNull();
        if (thisFinCurcy == null) {
            logger.debug(logPrfx + " --- thisFinCurcy is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateCalcVals(thisFinCurcy);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinCurcy = finCurcyDc.getItemOrNull();
            if (thisFinCurcy == null) {
                logger.debug(logPrfx + " --- finCurcyDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinCurcy);
            updateId2Dup(thisFinCurcy);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcy = finCurcyDc.getItemOrNull();
        if (thisFinCurcy == null) {
            logger.debug(logPrfx + " --- finCurcyDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinCurcy);
        updateId2Cmp(thisFinCurcy);
        updateId2Dup(thisFinCurcy);

        logger.debug(logPrfx + " --- id2: " + thisFinCurcy.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcy = finCurcyDc.getItemOrNull();
        if (thisFinCurcy == null) {
            logger.debug(logPrfx + " --- finCurcyDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinCurcy);
        updateId2Cmp(thisFinCurcy);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinCurcy.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcy = finCurcyDc.getItemOrNull();
        if (thisFinCurcy == null) {
            logger.debug(logPrfx + " --- finCurcyDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinCurcy);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcy = finCurcyDc.getItemOrNull();
        if (thisFinCurcy == null) {
            logger.debug(logPrfx + " --- finCurcyDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinCurcy);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finCurcyTypesDl.load();
        logger.debug(logPrfx + " --- called finCurcyTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("name1Field")
    public void onName1FieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onName1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinCurcy = finCurcyDc.getItemOrNull();
            if (thisFinCurcy == null) {
                logger.debug(logPrfx + " --- finCurcyDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- executing updateId2Calc(thisFinCurcy)");
            updateId2Calc(thisFinCurcy);
        }
        logger.trace(logPrfx + " <-- ");
    }


    private Boolean updateCalcVals(@NotNull SysNode thisFinCurcy) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateId2Calc(thisFinCurcy) || isChanged;
        isChanged = updateId2(thisFinCurcy) || isChanged;
        isChanged = updateId2Cmp(thisFinCurcy) || isChanged;
        isChanged = updateId2Dup(thisFinCurcy) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull SysNode thisFinCurcy) {
        // Assume thisFinCurcy is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinCurcy.getId2();
        String id2 = thisFinCurcy.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinCurcy.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull SysNode thisFinCurcy) {
        // Assume thisFinCurcy is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinCurcy.getId2Calc();
        String id2Calc = thisFinCurcy.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinCurcy.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull SysNode thisFinCurcy) {
        // Assume thisFinCurcy is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinCurcy.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinCurcy.getId2(),thisFinCurcy.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinCurcy.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull SysNode thisFinCurcy) {
        // Assume thisFinCurcy is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinCurcy.getId2Dup();
        if (thisFinCurcy.getId2() != null) {
            String id2Qry = "select count(e) from ampata_SysNode e"
                        + " where e.className = 'SysFinCurcy'"
                        + " and e.id2 = :id2"
                        + " and e.id <> :id"
                    ;
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinCurcy.getId())
                        .parameter("id2", thisFinCurcy.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinCurcy.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinCurcy.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


}