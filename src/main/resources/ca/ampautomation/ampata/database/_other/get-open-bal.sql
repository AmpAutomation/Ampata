
--drop table if exists tt0;


--create temporary table tt0 as (
with recursive
 cte0 as (
-- non-recursive term
select 
 t.id
,t.id2
,t.parent1__id
,t.parent1__id2
from ampata_sys_node t
where t.class_name = 'FinAcct'
and t.deleted_by is null
and t.id = '24d02d9e-db36-cdc0-4420-2a0cc758df1d' -- /Mark
union
-- recursive term
select
 rt.id
,rt.id2
,rt.parent1__id
,rt.parent1__id2
from ampata_sys_node rt
inner join cte0 ct on ct.id = rt.parent1__id
where rt.class_name = 'FinAcct'
and rt.deleted_by is null

)
--);

, cte1 as (
select 
 t.fin_acct1__id
,t.fin_acct1__id2 
,t.fin_dept1__id
,t.fin_dept1__id2
,sum(t.amt_debt) as amt_debt
,sum(t.amt_cred) as amt_cred
,sum(t.amt_net) as amt_net

from ampata_sys_node t 
where t.class_name = 'FinTxactItm'
and t.deleted_by is null
and t.id_ts_ts1 < '2021-01-01'::timestamp
and t.fin_acct1__id in (select t.id from cte0 t)

group by 
 t.fin_acct1__id
,t.fin_acct1__id2
,t.fin_dept1__id
,t.fin_dept1__id2
)


,cte2 as(
select ct.*, t1.sort_key as sort_key, t2.id as type1__id, t2.id2 as type1__id2
from cte1 ct
inner join ampata_sys_node t1
on ct.fin_acct1__id = t1.id
inner join ampata_sys_node_type t2
on t1.type1__id = t2.id
)


/*
, cte3 as (
select 
  '3c7d777e-78f8-c9f2-03bd-d0b4142b39a6'::uuid as fin_acct1__id
, '/Mark/Q/Open_Bal' as fin_acct1__id2 
,t.fin_dept1__id
,t.fin_dept1__id2
,sum(t.amt_debt)
,sum(t.amt_cred)
,sum(t.amt_net)
, '_00_02_02' as sort_key 
,'8fa646a4-1118-4231-bc04-b518d8d04c10'::uuid as type1__id
,'/Q' as type1__id2

from ampata_sys_node t 
where t.class_name = 'FinTxactItm'
and t.deleted_by is null
and t.id_ts_ts1 < '2017-01-01'::timestamp

group by 
 t.fin_dept1__id
,t.fin_dept1__id2
)

select t.*
from (
select *
from cte2
union
select *
from cte3
) t
*/

select t.*
from cte2 t
inner join ampata_sys_node fdep
	on t.fin_dept1__id = fdep.id
where (
t.type1__id = 'd6cb71fd-3edc-4c69-9c98-720d3fe3a108' -- /A
or t.type1__id = 'b824fe9c-ca85-4c8c-ad78-97f95a27b0b3'  -- /L
or t.type1__id = '8fa646a4-1118-4231-bc04-b518d8d04c10'  -- /Q
)
order by t.sort_key, fdep.sort_idx 

/*
select * 
from ampata_sys_node t
where t.class_name = 'FinAcct'
and id2 = '/Q/Mark/Open_Bal'
*/
