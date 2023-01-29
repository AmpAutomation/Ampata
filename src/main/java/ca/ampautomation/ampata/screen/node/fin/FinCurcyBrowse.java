package ca.ampautomation.ampata.screen.node.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinCurcy.browse")
@UiDescriptor("fin-curcy-browse.xml")
@LookupComponent("finCurcysTable")
public class FinCurcyBrowse extends StandardLookup<GenNode> {
}