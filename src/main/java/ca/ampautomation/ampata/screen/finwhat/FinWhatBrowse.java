package ca.ampautomation.ampata.screen.finwhat;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinWhat;

@UiController("ampata_FinWhat.browse")
@UiDescriptor("fin-what-browse.xml")
@LookupComponent("finWhatsTable")
public class FinWhatBrowse extends StandardLookup<FinWhat> {
}