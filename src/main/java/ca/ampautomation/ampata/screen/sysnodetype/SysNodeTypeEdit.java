package ca.ampautomation.ampata.screen.sysnodetype;

import ca.ampautomation.ampata.entity.SysNodeType;
import io.jmix.ui.screen.*;

@UiController("ampata_SysNodeType.edit")
@UiDescriptor("sys-node-type-edit.xml")
@EditedEntityContainer("sysNodeTypeDc")
public class SysNodeTypeEdit extends StandardEditor<SysNodeType> {

}