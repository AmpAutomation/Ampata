package ca.ampautomation.ampata.screen.usr.node.gen;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrNodeGenBasic.edit")
@UiDescriptor("usr-node-gen-basic-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenBasic0Edit extends StandardEditor<UsrNodeBase> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("UsrItemGenTag");
    }

}