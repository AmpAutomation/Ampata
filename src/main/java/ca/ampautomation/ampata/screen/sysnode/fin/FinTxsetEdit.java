package ca.ampautomation.ampata.screen.sysnode.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.SysNode;

@UiController("ampata_FinTxset.edit")
@UiDescriptor("fin-txset-edit.xml")
@EditedEntityContainer("finTsetDc")
public class FinTxsetEdit extends StandardEditor<SysNode> {
}