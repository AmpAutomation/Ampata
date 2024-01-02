package ca.ampautomation.ampata.view.usr.item.gen;

import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;

@Route(value = "usrItemGenFmlas:/id", layout = MainView.class)
@ViewController("enty_UsrItemGenFmla.edit")
@ViewDescriptor("usr-item-gen-fmla-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrItemGenFmla0Edit extends StandardDetailView<UsrItemGenFmla> {
}