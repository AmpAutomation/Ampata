package ca.ampautomation.ampata.screen.usr;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

@UiController("ampata_UsrNode.edit")
@UiDescriptor("usr-node-edit.xml")
@EditedEntityContainer("usrNodeDc")
public class UsrNodeEdit extends StandardEditor<UsrNode> {
}