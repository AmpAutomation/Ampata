package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_GenDocVer.edit")
@UiDescriptor("gen-doc-ver-edit.xml")
@EditedEntityContainer("genNodeDc")
public class GenDocVerEdit extends StandardEditor<GenNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenDocVer");
    }
}