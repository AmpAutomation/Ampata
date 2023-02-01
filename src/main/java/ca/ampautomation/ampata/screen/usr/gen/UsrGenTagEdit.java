package ca.ampautomation.ampata.screen.usr.gen;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_UsrGenTag.edit")
@UiDescriptor("usr-gen-tag-edit.xml")
@EditedEntityContainer("genTagDc")
public class UsrGenTagEdit extends StandardEditor<UsrNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("GenTag");
    }

}