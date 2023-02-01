package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_UsrGenChan.edit")
@UiDescriptor("usr-gen-chan-edit.xml")
@EditedEntityContainer("genChanDc")
public class UsrGenChanEdit extends StandardEditor<UsrNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("GenChan");
    }
}