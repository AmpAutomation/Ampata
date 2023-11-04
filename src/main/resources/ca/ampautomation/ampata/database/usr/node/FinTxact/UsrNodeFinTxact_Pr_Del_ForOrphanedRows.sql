
drop procedure if exists UsrNodeFinTxact_Pr_Del_ForOrphanedRows;


create procedure UsrNodeFinTxact_Pr_Del_ForOrphanedRows()
language 'plpgsql'
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction

	

-- Find Txfers linking to deleted FinTxact
/*
select *
from ampata_usr_node t
inner join ampata_usr_node t2
on t2.fin_txact1__id = t.id
where t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is not null
and t2.dtype = 'enty_UsrNodeFinTxactItm'
*/
	


--update fin_txact_itms1__id_cnt_calc
with cte1 as(
select t.parent1__id id
,count(t.parent1__id) id_cnt
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxactItm'
and t.deleted_by is null
group by parent1__id 
)

update ampata_usr_node t
set  fin_txact_itms1__id_cnt_calc = ct.id_cnt
from cte1 ct
where t.id = ct.id
and t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;



--delete rows that are not referenced by FinTxactItm

/*
select count(*)
from (
select 
 t.id2
,t.fin_txact_itms1__id_cnt_calc 
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
and (
	fin_txact_itms1__id_cnt_calc is null
or	fin_txact_itms1__id_cnt_calc = 0
	)
order by id2
) t
*/


delete
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
and (
	t.fin_txact_itms1__id_cnt_calc is null
or	t.fin_txact_itms1__id_cnt_calc = 0
	)
;

/*
select t.id2
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxactItm'
--and t.deleted_by is null
and t.fin_txact1__id = '57b40601-3d93-4f63-a741-1ff330e7e9e0'
*/



end
$BODY$;


call UsrNodeFinTxact_Pr_Del_ForOrphanedRows();



