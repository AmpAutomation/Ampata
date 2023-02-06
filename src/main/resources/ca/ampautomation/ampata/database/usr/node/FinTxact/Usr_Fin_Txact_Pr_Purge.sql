
drop procedure if exists Fin_Txact_Pr_Purge;


create procedure Fin_Txact_Pr_Purge()
language 'plpgsql'
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction

	

-- Find Txfers linking to deleted FinTxact
/*
select *
from ampata_sys_node t
inner join ampata_sys_node t2
on t2.fin_txact1__id = t.id
where t.class_name = 'UsrFinTxact'
and t.deleted_by is not null
and t2.class_name = 'UsrFinTxactItm'
*/
	
	
delete
from ampata_sys_node t
where t.class_name = 'UsrFinTxact'
and t.deleted_by is not null
;



--update fin_txact_itms1__id_cnt_calc
with cte1 as(
select t.fin_txact1__id id
,count(t.fin_txact1__id) id_cnt
from ampata_sys_node t
where t.class_name = 'UsrFinTxactItm'
and t.deleted_by is null
group by fin_txact1__id 
)

update ampata_sys_node t
set  fin_txact_itms1__id_cnt_calc = ct.id_cnt
from cte1 ct
where t.id = ct.id
and t.class_name = 'UsrFinTxact'
and t.deleted_by is null
;



--delete rows that are not referenced by FinTxactItm

/*
select count(*)
from (
select 
 t.id2
,t.fin_txact_itms1__id_cnt_calc 
from ampata_sys_node t
where t.class_name = 'UsrFinTxact'
and t.deleted_by is null
and (
	fin_txact_itms1__id_cnt_calc is null
or	fin_txact_itms1__id_cnt_calc = 0
	)
order by id2
) t
*/


delete
from ampata_sys_node t
where t.class_name = 'UsrFinTxact'
and t.deleted_by is null
and (
	t.fin_txact_itms1__id_cnt_calc is null
or	t.fin_txact_itms1__id_cnt_calc = 0
	)
;

/*
select t.id2
from ampata_sys_node t
where t.class_name = 'UsrFinTxactItm'
--and t.deleted_by is null
and t.fin_txact1__id = '57b40601-3d93-4f63-a741-1ff330e7e9e0'
*/



end
$BODY$;


call Fin_Txact_Pr_Purge();



