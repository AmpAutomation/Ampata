package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrFinHow;

@UiController("ampata_FinHow.browse")
@UiDescriptor("usr-fin-how-browse.xml")
@LookupComponent("finHowsTable")
public class UsrFinHowBrowse extends StandardLookup<UsrFinHow> {
}