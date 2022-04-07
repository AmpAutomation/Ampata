package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinTxfer.edit")
@UiDescriptor("fin-txfer-edit.xml")
@EditedEntityContainer("finTxferDc")
public class FinTxferEdit extends StandardEditor<GenNode> {
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinTxfer");
    }

    @Subscribe("id2CalcButton")
    public void onId2CalcButtonClick(Button.ClickEvent event) {

    }

    @Subscribe("id2Button")
    public void onId2ButtonClick(Button.ClickEvent event) {
        id2Field.setValue(id2CalcField.getValue());

    }
}