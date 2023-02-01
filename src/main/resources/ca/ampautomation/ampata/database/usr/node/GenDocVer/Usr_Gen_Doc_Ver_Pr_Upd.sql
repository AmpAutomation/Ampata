


drop procedure if exists Usr_Gen_Doc_Ver_Pr_Upd;

create procedure Usr_Gen_Doc_Ver_Pr_Upd()
language 'plpgsql'
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction
	

update ampata_sys_node t
set id2_calc = Usr_Gen_Doc_Ver_Fn_get_Id2_Calc(
		 t1.id2
		,t.name1
	 )
FROM ampata_sys_node t1
where
	t.class_name = 'GenDocVer'
and	t.type1__id = t1.id
;


end
$BODY$
;

call Usr_Gen_Doc_Ver_Pr_Upd();

