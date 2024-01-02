package ca.ampautomation.ampata.view.usr.node.tngy;

import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;

@Route(value = "usrNodeTngyEquips", layout = MainView.class)
@ViewController("enty_UsrNodeTngyEquip.main")
@ViewDescriptor("usr-tngy-equip-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeTngyEquip0Main extends StandardListView<UsrNodeBase> {
}