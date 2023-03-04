drop procedure if exists Usr_Fin_Txact_Itm_Pr_Purge;

create procedure Usr_Fin_Txact_Itm_Pr_Purge()
language 'plpgsql'
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction

	
delete
from ampata_usr_node t
where t.class_name = 'UsrNodeFinTxactItm'
and t.deleted_by is not null
;

end
$BODY$;


call Usr_Fin_Txact_Itm_Pr_Purge();
