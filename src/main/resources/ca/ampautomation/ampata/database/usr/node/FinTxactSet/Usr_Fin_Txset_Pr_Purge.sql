
drop procedure if exists Usr_Fin_Txset_Pr_Purge;


create procedure Usr_Fin_Txset_Pr_Purge()
language 'plpgsql'
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction

	
delete
from ampata_usr_node t
where t.class_name = 'FinTxset'
and t.deleted_by is not null
;



--update fin_txacts1__id_cnt_calc
with cte1 as(
select t.fin_txset1__id id
,count(t.fin_txset1__id) id_cnt
from ampata_usr_node t
where t.class_name = 'FinTxact'
and t.deleted_by is null
group by fin_txset1__id 
)

update ampata_usr_node t
set  fin_txacts1__id_cnt_calc = ct.id_cnt
from cte1 ct
where t.id = ct.id
and t.class_name = 'FinTxset'
and t.deleted_by is null
;



--delete rows that are not referenced by FinTxactItm

/*
select count(*)
from (
select 
 t.id2
,t.fin_txacts1__id_cnt_calc 
from ampata_usr_node t
where t.class_name = 'FinTxset'
and t.deleted_by is null
and (
	fin_txacts1__id_cnt_calc is null
or	fin_txacts1__id_cnt_calc = 0
	)
order by id2
) t
*/


delete
from ampata_usr_node t
where t.class_name = 'FinTxset'
and t.deleted_by is null
and (
	t.fin_txacts1__id_cnt_calc is null
or	t.fin_txacts1__id_cnt_calc = 0
	)
;

/*
select t.id2
from ampata_usr_node t
where t.class_name = 'FinTxactItm'
--and t.deleted_by is null
and t.fin_txact1__id = '57b40601-3d93-4f63-a741-1ff330e7e9e0'
*/



end
$BODY$;


call Usr_Fin_Txset_Pr_Purge();



