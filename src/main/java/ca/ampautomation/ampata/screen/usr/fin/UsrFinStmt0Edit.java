package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrFinStmt.edit")
@UiDescriptor("usr-fin-stmt-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrFinStmt0Edit extends StandardEditor<UsrBaseNode> {

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