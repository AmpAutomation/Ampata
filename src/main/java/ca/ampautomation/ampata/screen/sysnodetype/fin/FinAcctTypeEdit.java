package ca.ampautomation.ampata.screen.sysnodetype.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.SysNodeType;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinAcctType.edit")
@UiDescriptor("fin-acct-type-edit.xml")
@EditedEntityContainer("sysNodeTypeDc")
public class FinAcctTypeEdit extends StandardEditor<SysNodeType> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinAcct");
    }


}