
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

--Rename files to _v03

ALTER TABLE public.ampata_sys_item							RENAME TO ampata_sys_item_v03;
ALTER TABLE public.ampata_sys_item_type						RENAME TO ampata_sys_item_type_v03;

ALTER TABLE public.ampata_sys_node							RENAME TO ampata_sys_node_v03;
ALTER TABLE public.ampata_sys_node_type						RENAME TO ampata_sys_node_type_v03;

ALTER TABLE public.ampata_user								RENAME TO ampata_user_v03;

ALTER TABLE public.ampata_usr_edge							RENAME TO ampata_usr_edge_v03;
ALTER TABLE public.ampata_usr_edge__gen_tags1_link			RENAME TO ampata_usr_edge__gen_tags1_link_v03;
ALTER TABLE public.ampata_usr_edge_type						RENAME TO ampata_usr_edge_type_v03;
ALTER TABLE public.ampata_usr_edge_type__gen_tags1_link		RENAME TO ampata_usr_edge_type__gen_tags1_link_v03;

ALTER TABLE public.ampata_usr_item							RENAME TO ampata_usr_item_v03;
ALTER TABLE public.ampata_usr_item_type						RENAME TO ampata_usr_item_type_v03;

ALTER TABLE public.ampata_usr_node							RENAME TO ampata_usr_node_v03;
ALTER TABLE public.ampata_usr_node__gen_tags1_link			RENAME TO ampata_usr_node__gen_tags1_link_v03;
ALTER TABLE public.ampata_usr_node_type						RENAME TO ampata_usr_node_type_v03;
ALTER TABLE public.ampata_usr_node_type__gen_tags1_link		RENAME TO ampata_usr_node_type__gen_tags1_link_v03;


ALTER TABLE public.mten_tenant								RENAME TO mten_tenant_v03;
ALTER TABLE public.sec_resource_policy						RENAME TO sec_resource_policy_v03;
ALTER TABLE public.sec_resource_role						RENAME TO sec_resource_role_v03;
ALTER TABLE public.ui_setting								RENAME TO ui_setting_v03;


end
$BODY$
;



call Other_Backup_Pr();
