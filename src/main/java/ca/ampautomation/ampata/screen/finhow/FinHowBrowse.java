package ca.ampautomation.ampata.screen.finhow;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinHow;

@UiController("ampata_FinHow.browse")
@UiDescriptor("fin-how-browse.xml")
@LookupComponent("finHowsTable")
public class FinHowBrowse extends StandardLookup<FinHow> {
}