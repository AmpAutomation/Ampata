package ca.ampautomation.ampata.screen.sysnode.gen;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_GenChan.edit")
@UiDescriptor("gen-chan-edit.xml")
@EditedEntityContainer("sysNodeDc")
public class GenChanEdit extends StandardEditor<SysNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenChan");
    }
}