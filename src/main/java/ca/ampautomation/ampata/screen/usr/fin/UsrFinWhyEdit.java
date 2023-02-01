package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrFinWhy;

@UiController("ampata_FinWhy.edit")
@UiDescriptor("usr-fin-why-edit.xml")
@EditedEntityContainer("finWhyDc")
public class UsrFinWhyEdit extends StandardEditor<UsrFinWhy> {
}