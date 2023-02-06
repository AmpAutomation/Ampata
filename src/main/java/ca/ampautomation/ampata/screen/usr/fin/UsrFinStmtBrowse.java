package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("enty_UsrFinStmt.browse")
@UiDescriptor("usr-fin-stmt-browse.xml")
@LookupComponent("tableMain")
public class UsrFinStmtBrowse extends StandardLookup<UsrNode> {
    @Install(to = "tableMain.[idTs.ts1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

}