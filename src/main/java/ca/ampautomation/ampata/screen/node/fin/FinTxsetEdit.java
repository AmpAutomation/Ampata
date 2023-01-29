package ca.ampautomation.ampata.screen.node.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinTxset.edit")
@UiDescriptor("fin-txset-edit.xml")
@EditedEntityContainer("finTsetDc")
public class FinTxsetEdit extends StandardEditor<GenNode> {
}