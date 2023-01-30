package ca.ampautomation.ampata.screen.sysnode.gen;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_TngNDocFrg.edit")
@UiDescriptor("gen-doc-frg-edit.xml")
@EditedEntityContainer("sysNodeDc")
public class GenDocFrgEdit extends StandardEditor<SysNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenDocFrg");
    }


}