package ca.ampautomation.ampata.screen.finwhat;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinWhat;

@UiController("ampata_FinWhat.edit")
@UiDescriptor("fin-what-edit.xml")
@EditedEntityContainer("finWhatDc")
public class FinWhatEdit extends StandardEditor<FinWhat> {
}