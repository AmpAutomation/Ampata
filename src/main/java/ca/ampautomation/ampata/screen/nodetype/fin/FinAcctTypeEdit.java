package ca.ampautomation.ampata.screen.nodetype.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNodeType;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinAcctType.edit")
@UiDescriptor("fin-acct-type-edit.xml")
@EditedEntityContainer("genNodeTypeDc")
public class FinAcctTypeEdit extends StandardEditor<GenNodeType> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinAcct");
    }


}