package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

@UiController("ampata_UsrFinTxset.edit")
@UiDescriptor("usr-fin-txset-edit.xml")
@EditedEntityContainer("finTxsetDc")
public class UsrFinTxsetEdit extends StandardEditor<UsrNode> {
}