package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinTxset.edit")
@UiDescriptor("fin-txset-edit.xml")
@EditedEntityContainer("genNodeDc")
public class FinTxsetEdit extends StandardEditor<GenNode> {
}