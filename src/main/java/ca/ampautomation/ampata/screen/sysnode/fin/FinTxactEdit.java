package ca.ampautomation.ampata.screen.sysnode.fin;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.SysNode;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinTxact.edit")
@UiDescriptor("fin-txact-edit.xml")
@EditedEntityContainer("sysNodeDc")
public class FinTxactEdit extends StandardEditor<SysNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinTxact");
    }
}