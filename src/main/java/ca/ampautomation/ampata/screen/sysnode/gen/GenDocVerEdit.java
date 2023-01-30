package ca.ampautomation.ampata.screen.sysnode.gen;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.SysNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_GenDocVer.edit")
@UiDescriptor("gen-doc-ver-edit.xml")
@EditedEntityContainer("sysNodeDc")
public class GenDocVerEdit extends StandardEditor<SysNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenDocVer");
    }
}