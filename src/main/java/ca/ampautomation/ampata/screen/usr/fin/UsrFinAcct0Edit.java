package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.base.UsrNodeBase;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrFinAcct.edit")
@UiDescriptor("usr-fin-acct-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrFinAcct0Edit extends StandardEditor<UsrNodeBase> {


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