package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrNodeGenChan.edit")
@UiDescriptor("usr-node-gen-chan-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenChan0Edit extends StandardEditor<UsrNodeBase> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("UsrNodeGenChan");
    }
}