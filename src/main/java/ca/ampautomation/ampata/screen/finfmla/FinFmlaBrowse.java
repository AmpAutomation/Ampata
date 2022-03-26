package ca.ampautomation.ampata.screen.finfmla;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinFmla;

@UiController("ampata_FinFmla.browse")
@UiDescriptor("fin-fmla-browse.xml")
@LookupComponent("finFmlasTable")
public class FinFmlaBrowse extends StandardLookup<FinFmla> {
}