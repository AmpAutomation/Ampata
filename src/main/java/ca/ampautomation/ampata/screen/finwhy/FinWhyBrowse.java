package ca.ampautomation.ampata.screen.finwhy;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinWhy;

@UiController("ampata_FinWhy.browse")
@UiDescriptor("fin-why-browse.xml")
@LookupComponent("finWhiesTable")
public class FinWhyBrowse extends StandardLookup<FinWhy> {
}