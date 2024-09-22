package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenBasic;
import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.*;
import io.jmix.flowui.view.*;

@Route(value = "usrNodeGenBasics2", layout = MainView.class)
@ViewController("enty_UsrNodeGenBasic.list")
@ViewDescriptor("usr-node-gen-basic-0-list-view.xml")
@LookupComponent("usrNodeGenBasicsDataGrid")
@DialogMode(width = "64em")
public class UsrNodeGenBasic0ListView extends StandardListView<UsrNodeGenBasic> {

    @ViewComponent
    private DataContext dataContext;

    @ViewComponent
    private CollectionContainer<UsrNodeGenBasic> usrNodeGenBasicsDc;

    @ViewComponent
    private InstanceContainer<UsrNodeGenBasic> usrNodeGenBasicDc;

    @ViewComponent
    private InstanceLoader<UsrNodeGenBasic> usrNodeGenBasicDl;

    @ViewComponent
    private VerticalLayout listLayout;

    @ViewComponent
    private FormLayout form;

    @ViewComponent
    private HorizontalLayout detailActions;

    @Subscribe
    public void onInit(final InitEvent event) {
        updateControls(false);
    }

    @Subscribe("usrNodeGenBasicsDataGrid.create")
    public void onUsrNodeGenBasicsDataGridCreate(final ActionPerformedEvent event) {
        dataContext.clear();
        UsrNodeGenBasic entity = dataContext.create(UsrNodeGenBasic.class);
        usrNodeGenBasicDc.setItem(entity);
        updateControls(true);
    }

    @Subscribe("usrNodeGenBasicsDataGrid.edit")
    public void onUsrNodeGenBasicsDataGridEdit(final ActionPerformedEvent event) {
        updateControls(true);
    }

    @Subscribe("saveBtn")
    public void onSaveButtonClick(final ClickEvent<JmixButton> event) {
        dataContext.save();
        usrNodeGenBasicsDc.replaceItem(usrNodeGenBasicDc.getItem());
        updateControls(false);
    }

    @Subscribe("cancelBtn")
    public void onCancelButtonClick(final ClickEvent<JmixButton> event) {
        dataContext.clear();
        usrNodeGenBasicDl.load();
        updateControls(false);
    }

    @Subscribe(id = "usrNodeGenBasicsDc", target = Target.DATA_CONTAINER)
    public void onUsrNodeGenBasicsDcItemChange(final InstanceContainer.ItemChangeEvent<UsrNodeGenBasic> event) {
        UsrNodeGenBasic entity = event.getItem();
        dataContext.clear();
        if (entity != null) {
            usrNodeGenBasicDl.setEntityId(entity.getId());
            usrNodeGenBasicDl.load();
        } else {
            usrNodeGenBasicDl.setEntityId(null);
            usrNodeGenBasicDc.setItem(null);
        }
    }

    private void updateControls(boolean editing) {
        form.getChildren().forEach(component -> {
            if (component instanceof HasValueAndElement<?, ?> field) {
                field.setReadOnly(!editing);
            }
        });

        detailActions.setVisible(editing);
        listLayout.setEnabled(!editing);
    }
}