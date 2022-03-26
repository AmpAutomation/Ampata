package ca.ampautomation.ampata.screen.finhow;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinHow;

@UiController("ampata_FinHow.edit")
@UiDescriptor("fin-how-edit.xml")
@EditedEntityContainer("finHowDc")
public class FinHowEdit extends StandardEditor<FinHow> {
}