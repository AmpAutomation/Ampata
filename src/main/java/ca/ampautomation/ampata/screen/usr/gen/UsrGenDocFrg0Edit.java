package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrGenDocFrg.edit")
@UiDescriptor("usr-gen-doc-frg-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrGenDocFrg0Edit extends StandardEditor<UsrNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("UsrGenDocFrg");
    }


}