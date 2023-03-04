package ca.ampautomation.ampata.screen.usr.node.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrNodeFinAcctType.edit")
@UiDescriptor("usr-node-fin-acct-type-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinAcctType0Edit extends StandardEditor<UsrNodeBaseType> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("UsrNodeFinAcct");
    }


}