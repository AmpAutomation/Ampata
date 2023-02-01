package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

@UiController("ampata_FinTxset.edit")
@UiDescriptor("fin-txset-edit.xml")
@EditedEntityContainer("finTsetDc")
public class FinTxsetEdit extends StandardEditor<UsrNode> {
}