package ca.ampautomation.ampata.screen.usr.gen;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrGen.edit")
@UiDescriptor("usr-gen-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrGen0Edit extends StandardEditor<UsrNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("UsrGenTag");
    }

}