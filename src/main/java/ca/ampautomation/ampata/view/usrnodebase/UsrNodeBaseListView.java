package ca.ampautomation.ampata.view.usrnodebase;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
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

@Route(value = "usrNodeBases", layout = MainView.class)
@ViewController("enty_UsrNodeBase.list")
@ViewDescriptor("usr-node-base-list-view.xml")
@LookupComponent("usrNodeBasesDataGrid")
@DialogMode(width = "64em")
public class UsrNodeBaseListView extends StandardListView<UsrNodeBase> {

    @ViewComponent
    private DataContext dataContext;

    @ViewComponent
    private CollectionContainer<UsrNodeBase> usrNodeBasesDc;

    @ViewComponent
    private InstanceContainer<UsrNodeBase> usrNodeBaseDc;

    @ViewComponent
    private InstanceLoader<UsrNodeBase> usrNodeBaseDl;

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

    @Subscribe("usrNodeBasesDataGrid.create")
    public void onUsrNodeBasesDataGridCreate(final ActionPerformedEvent event) {
        dataContext.clear();
        UsrNodeBase entity = dataContext.create(UsrNodeBase.class);
        usrNodeBaseDc.setItem(entity);
        updateControls(true);
    }

    @Subscribe("usrNodeBasesDataGrid.edit")
    public void onUsrNodeBasesDataGridEdit(final ActionPerformedEvent event) {
        updateControls(true);
    }

    @Subscribe("saveBtn")
    public void onSaveButtonClick(final ClickEvent<JmixButton> event) {
        dataContext.save();
        usrNodeBasesDc.replaceItem(usrNodeBaseDc.getItem());
        updateControls(false);
    }

    @Subscribe("cancelBtn")
    public void onCancelButtonClick(final ClickEvent<JmixButton> event) {
        dataContext.clear();
        usrNodeBaseDl.load();
        updateControls(false);
    }

    @Subscribe(id = "usrNodeBasesDc", target = Target.DATA_CONTAINER)
    public void onUsrNodeBasesDcItemChange(final InstanceContainer.ItemChangeEvent<UsrNodeBase> event) {
        UsrNodeBase entity = event.getItem();
        dataContext.clear();
        if (entity != null) {
            usrNodeBaseDl.setEntityId(entity.getId());
            usrNodeBaseDl.load();
        } else {
            usrNodeBaseDl.setEntityId(null);
            usrNodeBaseDc.setItem(null);
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