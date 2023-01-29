package ca.ampautomation.ampata.screen.node.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("ampata_FinStmt.browse")
@UiDescriptor("fin-stmt-browse.xml")
@LookupComponent("table")
public class FinStmtBrowse extends StandardLookup<GenNode> {
    @Install(to = "table.[idTs.ts1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

}