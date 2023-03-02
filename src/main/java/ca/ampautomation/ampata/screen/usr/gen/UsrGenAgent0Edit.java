package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrGenAgent.edit")
@UiDescriptor("usr-gen-agent-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrGenAgent0Edit extends StandardEditor<UsrBaseNode> {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("UsrGenAgent");
    }
}