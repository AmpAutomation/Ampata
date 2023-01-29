package ca.ampautomation.ampata.screen.nodetype;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNodeType;

@UiController("ampata_GenNodeType.edit")
@UiDescriptor("gen-node-type-edit.xml")
@EditedEntityContainer("genNodeTypeDc")
public class GenNodeTypeEdit extends StandardEditor<GenNodeType> {

}