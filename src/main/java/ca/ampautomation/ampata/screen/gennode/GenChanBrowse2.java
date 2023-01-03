package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.GenNodeRepository;
import ca.ampautomation.ampata.entity.GenNodeType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import io.jmix.ui.screen.LookupComponent;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UiController("ampata_GenChan.browse2")
@UiDescriptor("gen-chan-browse2.xml")
@LookupComponent("table")
public class GenChanBrowse2 extends MasterDetailScreen<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected Filter filter;


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GenNodeRepository repo;


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
    private Table<GenNode> table;

    @Autowired
    private Form form;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> genChansDc;

    @Autowired
    private CollectionLoader<GenNode> genChansDl;

    @Autowired
    private InstanceContainer<GenNode> genChanDc;


    private CollectionContainer<GenNodeType> genChanTypesDc;

    private CollectionLoader<GenNodeType> genChanTypesDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<GenNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;


    Logger logger = LoggerFactory.getLogger(GenChanBrowse2.class);


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        genChanTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        genChanTypesDl = dataComponents.createCollectionLoader();
        genChanTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'GenChan' order by e.id2");
        FetchPlan genChanTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genChanTypesDl.setFetchPlan(genChanTypesFp);
        genChanTypesDl.setContainer(genChanTypesDc);
        genChanTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(genChanTypesDc);



        genDocVersDc = dataComponents.createCollectionContainer(GenNode.class);
        genDocVersDl = dataComponents.createCollectionLoader();
        genDocVersDl.setQuery("select e from ampata_GenNode e where e.className = 'GenDocVer' order by e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genDocVersDl.setFetchPlan(genDocVersFp);
        genDocVersDl.setContainer(genDocVersDc);
        genDocVersDl.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(genDocVersDc);


        genTagsDc = dataComponents.createCollectionContainer(GenNode.class);
        genTagsDl = dataComponents.createCollectionLoader();
        genTagsDl.setQuery("select e from ampata_GenNode e where e.className = 'GenTag' order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genTagsDl.setFetchPlan(genTagsFp);
        genTagsDl.setContainer(genTagsDc);
        genTagsDl.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(genTagsDc);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        genChansDl.load();
        table.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "genChansDc", target = Target.DATA_CONTAINER)
    public void onGenChansDcCollectionChange(CollectionContainer.CollectionChangeEvent<GenNode> event) {
        String logPrfx = "onGenChansDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "genChansDc", target = Target.DATA_CONTAINER)
    public void onGenChansDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onGenChansDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisGenChan = event.getItem();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenChan.setClassName("GenChan");
        logger.debug(logPrfx + " --- className: GenChan");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadLists")
    public void onReloadListsClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsClick";
        logger.trace(logPrfx + " --> ");

        genChanTypesDl.load();
        logger.debug(logPrfx + " --- called genChanTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }
    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Gen_Node_Pr_Upd()");
        repo.execGenNodePrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Gen_Node_Pr_Upd()");

        logger.debug(logPrfx + " --- executing Db-Proc.Gen_Chan_Pr_Upd()");
        repo.execGenChanPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Gen_Chan_Pr_Upd()");

        logger.debug(logPrfx + " --- loading genChansDl.load()");
        genChansDl.load();
        logger.debug(logPrfx + " --- finished genChansDl.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisGenChans = table.getSelected().stream().toList();
        if (thisGenChans == null || thisGenChans.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenChans.forEach(orig -> {
            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());


            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            GenNode savedCopy = dataManager.save(copy);
            genChansDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated GenChan " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisGenChans = table.getSelected().stream().toList();
        if (thisGenChans == null || thisGenChans.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisGenChans.forEach(orig -> {

            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());


            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            GenNode savedCopy = dataManager.save(copy);
            genChansDc.getMutableItems().add(savedCopy);
            logger.debug("Derived GenChan " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
            sels.add(savedCopy);

        });
        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisGenChans = table.getSelected().stream().toList();
        if (thisGenChans == null || thisGenChans.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenChans.forEach(thisGenChan -> {
            GenNode thisTrackedGenChan = dataContext.merge(thisGenChan);
            thisGenChan = thisTrackedGenChan;
            if (thisGenChan != null) {

                Boolean thisGenChanIsChanged = false;




                thisGenChanIsChanged = updateId2Calc(thisGenChan) || thisGenChanIsChanged;
                thisGenChanIsChanged = updateId2(thisGenChan) || thisGenChanIsChanged;
                thisGenChanIsChanged = updateId2Cmp(thisGenChan) || thisGenChanIsChanged;

                if (thisGenChanIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisGenChan).");
                    //dataManager.save(thisGenChan);
                }


            }
        });
        updateGenChanHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateGenChanHelper() {
        String logPrfx = "updateGenChanHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing genChansDl.load().");
            genChansDl.load();

            List<GenNode> thisGenChans = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisGenChans.forEach(thisGenChan -> {
                //GenNode thisTrackedGenChan = dataContext.merge(thisGenChan);
                if (thisGenChan != null) {
                    GenNode thisTrackedGenChan = dataContext.merge(thisGenChan);
                    thisGenChan = thisTrackedGenChan;

                    Boolean thisGenChanIsChanged = false;

                    thisGenChanIsChanged = updateId2Dup(thisGenChan) || thisGenChanIsChanged;

                    if (thisGenChanIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisGenChan).");
                        //dataManager.save(thisGenChan);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing genChansDl.load().");
                genChansDl.load();

                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisGenChans);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisGenChans = table.getSelected().stream().toList();
        if (thisGenChans == null || thisGenChans.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenChans.forEach(thisGenChan -> {
            if (thisGenChan != null) {

                thisGenChan = dataContext.merge(thisGenChan);;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisGenChan);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing genChansDl.load().");
            genChansDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisGenChans);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "genChanDc", target = Target.DATA_CONTAINER)
    public void onGenChanDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onGenChanDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisGenChan = event.getSource().getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            //todo I observed thisGenChan is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenChan.setClassName("GenChan");
        logger.debug(logPrfx + " --- className: GenChan");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisGenChan = genChanDc.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisGenChan);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisGenChan = genChanDc.getItemOrNull();
            if (thisGenChan == null) {
                logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisGenChan);
            updateId2Dup(thisGenChan);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisGenChan = genChanDc.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisGenChan);
        updateId2Cmp(thisGenChan);
        updateId2Dup(thisGenChan);

        logger.debug(logPrfx + " --- id2: " + thisGenChan.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisGenChan = genChanDc.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- genChanDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisGenChan);
        updateId2Cmp(thisGenChan);

        logger.debug(logPrfx + " --- id2Calc: " + thisGenChan.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisGenChan = genChanDc.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisGenChan);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisGenChan = genChanDc.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisGenChan);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genChanTypesDl.load();
        logger.debug(logPrfx + " --- called genChanTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenDocVer1_IdFieldListBtn")
    public void onUpdateGenDocVer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenDocVer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisGenChan = genChanDc.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisGenChan);

        logger.trace(logPrfx + " <-- ");
    }

    public Boolean updateCalcVals(GenNode thisGenChan){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateGenChanCalcVals(thisGenChan) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateGenChanCalcVals(@NotNull GenNode thisGenChan) {
        String logPrfx = "updateGenChanCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in GenChan Object
        isChanged = updateId2Calc(thisGenChan) || isChanged;
        isChanged = updateId2Cmp(thisGenChan) || isChanged;
        isChanged = updateId2Dup(thisGenChan) || isChanged;
        isChanged = updateDesc1(thisGenChan) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull GenNode thisGenChan) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull GenNode thisGenChan) {
        // Assume thisGenChan is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisGenChan.getId2();
        String id2 = thisGenChan.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisGenChan.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(GenNode thisGenChan){
        // Assume thisGenChan is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisGenChan.getId2Calc();
        String id2Calc = thisGenChan.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisGenChan.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull GenNode thisGenChan) {
        // Assume thisGenChan is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisGenChan.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisGenChan.getId2(),thisGenChan.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisGenChan.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull GenNode thisGenChan) {
        // Assume thisGenChan is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisGenChan.getId2Dup();
        if (thisGenChan.getId2() != null){
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'GenChan' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try{
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",thisGenChan.getId())
                        .parameter("id2",thisGenChan.getId2())
                        .one();
            }catch (IllegalStateException e){
                id2Dup =0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisGenChan.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisGenChan.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull GenNode thisGenChan){
        // Assume thisGenChan is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String desc1_ = thisGenChan.getDesc1();

        String name1 = Objects.toString(thisGenChan.getName1());

        String tag = "";
        String tag1 = "";
        if (thisGenChan.getGenTag1_Id() != null) {
            tag1 = Objects.toString(thisGenChan.getGenTag1_Id().getId2());}
        String tag2 = "";
        if (thisGenChan.getGenTag1_Id() != null) {
            tag2 = Objects.toString(thisGenChan.getGenTag2_Id().getId2());}
        String tag3 = "";
        if (thisGenChan.getGenTag1_Id() != null) {
            tag3 = Objects.toString(thisGenChan.getGenTag3_Id().getId2());}
        String tag4 = "";
        if (thisGenChan.getGenTag1_Id() != null) {
            tag4 = Objects.toString(thisGenChan.getGenTag4_Id().getId2());}
        if (!(tag1 + tag2 + tag3 + tag4).equals("")) {
            tag = "tag [" + String.join(",",tag1, tag2, tag3, tag4) + "]";}
        logger.debug(logPrfx + " --- tag: " + tag);

        List<String> list = Arrays.asList(
                name1
                ,tag);
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(desc1_, desc1)){
            thisGenChan.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private GenNode findGenChanById2(@NotNull String GenChan_Id2) {
        String logPrfx = "findGenChanById2";
        logger.trace(logPrfx + " --> ");

        if (GenChan_Id2 == null) {
            logger.debug(logPrfx + " --- GenChan_Id2 is null.");
            notifications.create().withCaption("GenChan_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'GenChan' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + GenChan_Id2);

        GenNode GenChan1_Id = null;
        try {
            GenChan1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", GenChan_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return GenChan1_Id;

    }


    private void addEnteredTextToComboBoxOptionsList(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "addEnteredTextToComboBoxOptionsList";
        logger.trace(logPrfx + " --> ");

        String text = enterPressEvent.getText();
        if (!Objects.equals(text, "<null>")){
            @SuppressWarnings("unchecked")
            // enterPressEvent.getSource is directly connected to a ComboBox
            ComboBox<String> cb = (ComboBox<String>) enterPressEvent.getSource();

            List<String> list;
            // this comboBox options list is created with a call to setOptionsList(List)
            // see onUpdateGenChan1_Desc1Field
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


}