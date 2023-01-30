package ca.ampautomation.ampata.screen.sysnode.fin;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinCurcy.edit")
@UiDescriptor("fin-curcy-edit.xml")
@EditedEntityContainer("sysNodeDc")
public class FinCurcyEdit extends StandardEditor<SysNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinCurcy");
    }


}