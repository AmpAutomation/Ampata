package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_UsrGenDocFrg.edit")
@UiDescriptor("usr-gen-doc-frg-edit.xml")
@EditedEntityContainer("usrNodeDc")
public class UsrGenDocFrgEdit extends StandardEditor<UsrNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenDocFrg");
    }


}