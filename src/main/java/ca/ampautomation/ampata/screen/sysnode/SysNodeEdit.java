package ca.ampautomation.ampata.screen.sysnode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.SysNode;

@UiController("ampata_SysNode.edit")
@UiDescriptor("sys-node-edit.xml")
@EditedEntityContainer("sysNodeDc")
public class SysNodeEdit extends StandardEditor<SysNode> {
}