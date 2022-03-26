package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_GenNode.edit")
@UiDescriptor("gen-node-edit.xml")
@EditedEntityContainer("genNodeDc")
public class GenNodeEdit extends StandardEditor<GenNode> {
}