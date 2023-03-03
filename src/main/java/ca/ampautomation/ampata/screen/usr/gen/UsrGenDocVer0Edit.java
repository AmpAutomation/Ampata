package ca.ampautomation.ampata.screen.usr.gen;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.base.UsrNodeBase;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrGenDocVer.edit")
@UiDescriptor("usr-gen-doc-ver-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrGenDocVer0Edit extends StandardEditor<UsrNodeBase> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("UsrGenDocVer");
    }
}