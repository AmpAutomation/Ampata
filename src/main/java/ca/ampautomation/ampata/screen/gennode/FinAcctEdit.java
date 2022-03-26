package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.component.TextField;
import io.jmix.ui.component.TextInputField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinAcct.edit")
@UiDescriptor("fin-acct-edit.xml")
@EditedEntityContainer("genNodeDc")
public class FinAcctEdit extends StandardEditor<GenNode> {


    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onInit(InitEvent event) {
    }

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {

    }


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinAcct");
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {


    }


}