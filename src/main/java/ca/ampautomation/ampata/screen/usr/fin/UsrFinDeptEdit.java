package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_UsrFinDept.edit")
@UiDescriptor("usr-fin-dept-edit.xml")
@EditedEntityContainer("finDeptDc")
public class UsrFinDeptEdit extends StandardEditor<UsrNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("FinDept");
    }
}