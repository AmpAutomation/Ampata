package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrFinWhy;

@UiController("ampata_FinWhy.browse")
@UiDescriptor("usr-in-why-browse.xml")
@LookupComponent("finWhiesTable")
public class UsrFinWhyBrowse extends StandardLookup<UsrFinWhy> {
}