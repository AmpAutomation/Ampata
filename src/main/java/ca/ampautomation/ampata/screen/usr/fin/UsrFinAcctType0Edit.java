package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseNodeType;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrFinAcctType.edit")
@UiDescriptor("usr-fin-acct-type-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrFinAcctType0Edit extends StandardEditor<UsrBaseNodeType> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("UsrFinAcct");
    }


}