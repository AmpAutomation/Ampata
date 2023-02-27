package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrFinAcct.edit")
@UiDescriptor("usr-fin-acct-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrFinAcct_Edit extends StandardEditor<UsrNode> {


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
        classNameField.setValue("UsrFinAcct");
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {


    }


}