package ca.ampautomation.ampata.screen.sysnode.fin;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinStmt.edit")
@UiDescriptor("fin-stmt-edit.xml")
@EditedEntityContainer("finStmtDc")
public class FinStmtEdit extends StandardEditor<SysNode> {

    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("FinStmt");
    }

    @Subscribe("id2Button")
    public void onId2ButtonClick(Button.ClickEvent event) {
        id2Field.setValue(id2CalcField.getValue());

    }
}