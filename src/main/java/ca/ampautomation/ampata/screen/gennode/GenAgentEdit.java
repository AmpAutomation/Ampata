package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_GenAgent.edit")
@UiDescriptor("gen-agent-edit.xml")
@EditedEntityContainer("genNodeDc")
public class GenAgentEdit extends StandardEditor<GenNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenAgent");
    }
}