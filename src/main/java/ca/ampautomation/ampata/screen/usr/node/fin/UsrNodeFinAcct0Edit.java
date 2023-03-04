package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrNodeFinAcct.edit")
@UiDescriptor("usr-node-fin-acct-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinAcct0Edit extends StandardEditor<UsrNodeBase> {


    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onInit(InitEvent event) {
    }

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {

    }


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("UsrNodeFinAcct");
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {


    }


}