package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrFinTxact.edit")
@UiDescriptor("usr-fin-txact-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrFinTxactEdit extends StandardEditor<UsrNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("UsrFinTxact");
    }
}