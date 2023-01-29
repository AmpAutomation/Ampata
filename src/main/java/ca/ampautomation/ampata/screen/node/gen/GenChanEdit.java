package ca.ampautomation.ampata.screen.node.gen;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_GenChan.edit")
@UiDescriptor("gen-chan-edit.xml")
@EditedEntityContainer("genNodeDc")
public class GenChanEdit extends StandardEditor<GenNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenChan");
    }
}