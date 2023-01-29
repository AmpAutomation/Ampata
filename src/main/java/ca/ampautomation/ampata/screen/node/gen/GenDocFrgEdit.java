package ca.ampautomation.ampata.screen.node.gen;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_TngNDocFrg.edit")
@UiDescriptor("gen-doc-frg-edit.xml")
@EditedEntityContainer("genNodeDc")
public class GenDocFrgEdit extends StandardEditor<GenNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenDocFrg");
    }


}