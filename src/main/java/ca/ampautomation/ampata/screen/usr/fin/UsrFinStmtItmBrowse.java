package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("enty_UsrFinStmtItm.browse")
@UiDescriptor("usr-fin-stmt-itm-browse.xml")
@LookupComponent("tableMain")
public class UsrFinStmtItmBrowse extends StandardLookup<UsrNode> {

    @Install(to = "tableMain.[instTs1.ts1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

}