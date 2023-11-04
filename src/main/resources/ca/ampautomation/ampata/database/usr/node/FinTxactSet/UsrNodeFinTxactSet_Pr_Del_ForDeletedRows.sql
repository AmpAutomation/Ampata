drop procedure if exists UsrNodeFinTxactSet_Pr_Del_ForDeletedRows;

create procedure UsrNodeFinTxactSet_Pr_Del_ForDeletedRows()
language 'plpgsql'
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction

	
delete
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxactSet'
and t.deleted_by is not null
;

end
$BODY$;


call UsrNodeFinTxactSet_Pr_Del_ForDeletedRows();
