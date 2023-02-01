package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrFinWhat;

@UiController("ampata_FinWhat.edit")
@UiDescriptor("usr-fin-what-edit.xml")
@EditedEntityContainer("finWhatDc")
public class UsrFinWhatEdit extends StandardEditor<UsrFinWhat> {
}