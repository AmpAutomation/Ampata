drop procedure if exists UsrNodeFinTxactItm_Pr_Del_ForDeletedRows;

create procedure UsrNodeFinTxactItm_Pr_Del_ForDeletedRows()
language 'plpgsql'
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction

	
delete
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxactItm'
and t.deleted_by is not null
;

end
$BODY$;


call UsrNodeFinTxactItm_Pr_Del_ForDeletedRows();
