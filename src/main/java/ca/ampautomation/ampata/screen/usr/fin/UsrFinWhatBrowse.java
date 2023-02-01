package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrFinWhat;

@UiController("ampata_FinWhat.browse")
@UiDescriptor("usr-fin-what-browse.xml")
@LookupComponent("finWhatsTable")
public class UsrFinWhatBrowse extends StandardLookup<UsrFinWhat> {
}