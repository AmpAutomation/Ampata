package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("ampata_UsrFinStmtItm.browse")
@UiDescriptor("usr-fin-stmt-itm-browse.xml")
@LookupComponent("table")
public class UsrFinStmtItmBrowse extends StandardLookup<UsrNode> {

    @Install(to = "table.[idTs.ts1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

}