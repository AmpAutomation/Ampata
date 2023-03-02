package ca.ampautomation.ampata.screen.usr.gen;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrGenBasc.edit")
@UiDescriptor("usr-gen-basc-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrGenBasc0Edit extends StandardEditor<UsrBaseNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("UsrGenTag");
    }

}