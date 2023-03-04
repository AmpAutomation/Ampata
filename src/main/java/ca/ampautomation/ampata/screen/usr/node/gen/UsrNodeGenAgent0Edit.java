package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrNodeGenAgent.edit")
@UiDescriptor("usr-node-gen-agent-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenAgent0Edit extends StandardEditor<UsrNodeBase> {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("UsrNodeGenAgent");
    }
}