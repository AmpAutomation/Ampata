package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrFinDept.edit")
@UiDescriptor("usr-fin-dept-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrFinDept0Edit extends StandardEditor<UsrBaseNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("UsrFinDept");
    }
}