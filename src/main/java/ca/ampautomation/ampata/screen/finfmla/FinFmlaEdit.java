package ca.ampautomation.ampata.screen.finfmla;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinFmla;

@UiController("ampata_FinFmla.edit")
@UiDescriptor("fin-fmla-edit.xml")
@EditedEntityContainer("finFmlaDc")
public class FinFmlaEdit extends StandardEditor<FinFmla> {
}