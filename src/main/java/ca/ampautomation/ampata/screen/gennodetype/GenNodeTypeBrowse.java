package ca.ampautomation.ampata.screen.gennodetype;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNodeType;

@UiController("ampata_GenNodeType.browse")
@UiDescriptor("gen-node-type-browse.xml")
@LookupComponent("genNodeTypesTable")
public class GenNodeTypeBrowse extends StandardLookup<GenNodeType> {
}