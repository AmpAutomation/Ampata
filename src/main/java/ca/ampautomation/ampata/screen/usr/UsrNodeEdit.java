package ca.ampautomation.ampata.screen.usr;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

@UiController("enty_UsrNode.edit")
@UiDescriptor("usr-node-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeEdit extends StandardEditor<UsrNode> {
}