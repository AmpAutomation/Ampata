package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_UsrGenAgent.edit")
@UiDescriptor("usr-gen-agent-edit.xml")
@EditedEntityContainer("usrNodeDc")
public class UsrGenAgentEdit extends StandardEditor<UsrNode> {

    //Common

    Logger logger = LoggerFactory.getLogger(UsrGenAgentEdit.class);

    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("GenAgent");
    }
}