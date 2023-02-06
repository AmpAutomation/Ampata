package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrFinStmt.edit")
@UiDescriptor("usr-fin-stmt-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrFinStmtEdit extends StandardEditor<UsrNode> {

    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        classNameField.setValue("UsrFinStmt");
    }

    @Subscribe("id2Button")
    public void onId2ButtonClick(Button.ClickEvent event) {
        id2Field.setValue(id2CalcField.getValue());

    }
}