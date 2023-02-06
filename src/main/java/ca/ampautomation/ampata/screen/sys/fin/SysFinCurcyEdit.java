package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_SysFinCurcy.edit")
@UiDescriptor("sys-fin-curcy-edit.xml")
@EditedEntityContainer("SysFinCurcyDc")
public class SysFinCurcyEdit extends StandardEditor<UsrNode> {
    @Autowired
    private TextField<String> classNameField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event)
    {
        classNameField.setValue("UsrFinCurcy");
    }


}