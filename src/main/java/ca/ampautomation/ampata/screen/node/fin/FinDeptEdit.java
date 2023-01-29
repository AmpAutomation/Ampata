package ca.ampautomation.ampata.screen.node.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinDept.edit")
@UiDescriptor("fin-dept-edit.xml")
@EditedEntityContainer("genNodeDc")
public class FinDeptEdit extends StandardEditor<GenNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinDept");
    }
}