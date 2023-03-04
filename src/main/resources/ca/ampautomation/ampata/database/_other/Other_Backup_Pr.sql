
drop procedure if exists Other_Backup_Pr;

create procedure Other_Backup_Pr()
language 'plpgsql'	
as $BODY$
declare
	num_rows_updated_in_iter int;
	num_rows_updated_in_loop int;
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction

--Run on backup server

--Rename files to _v02

ALTER TABLE public.ampata_sys_item			RENAME TO ampata_sys_item_v02;
ALTER TABLE public.ampata_sys_item_type		RENAME TO ampata_sys_item_type_v02;
ALTER TABLE public.ampata_sys_node			RENAME TO ampata_sys_node_v02;
ALTER TABLE public.ampata_sys_node_type		RENAME TO ampata_sys_node_type_v02;


ALTER TABLE public.ampata_usr_item			RENAME TO ampata_usr_item_v02;
ALTER TABLE public.ampata_usr_item_type		RENAME TO ampata_usr_item_type_v02;
ALTER TABLE public.ampata_usr_node			RENAME TO ampata_usr_node_v02;
ALTER TABLE public.ampata_usr_node_type		RENAME TO ampata_usr_node_type_v02;



end
$BODY$
;



call Other_Backup_Pr();
