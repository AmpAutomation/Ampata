
drop procedure if exists UsrNodeFinStmt_Pr_Upd_AllCalcVals_ForAllRows;

create procedure UsrNodeFinStmt_Pr_Upd_AllCalcVals_ForAllRows()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;
begin
-- Stored procedures are atomic and are executed as a transaction


--ts1.., ts2..
raise notice 'Updating ts1_el_dt.., ts2_el_dt..';
update ampata_usr_node t
set 
	ts1_el_dt = t.ts1_el_ts::date
	,ts1_el_dt_yr = date_part('year',t.ts1_el_ts)
	,ts1_el_dt_qtr = date_part('quarter',t.ts1_el_ts)
	,ts1_el_dt_mon =  date_part('Mon',t.ts1_el_ts)
	,ts1_el_dt_mon2 =  to_char(t.ts1_el_ts,'Mon')
	,ts1_el_dt_day = date_part('day',t.ts1_el_ts)
	,ts1_el_tm  = t.ts1_el_ts::time
	,ts1_el_tm_hr  = date_part('hour',t.ts1_el_ts)
	,ts1_el_tm_min  = date_part('minute',t.ts1_el_ts)

	,ts2_el_dt = t.ts2_el_ts::date
	,ts2_el_dt_yr = date_part('year',t.ts2_el_dt)
	,ts2_el_dt_qtr = date_part('quarter',t.ts2_el_ts)
	,ts2_el_dt_mon =  date_part('Mon',t.ts2_el_dt)
	,ts2_el_dt_mon2 =  to_char(t.ts2_el_dt,'Mon')
	,ts2_el_dt_day = date_part('day',t.ts2_el_dt)
	,ts2_el_tm  = t.ts2_el_ts::time
	,ts2_el_tm_hr  = date_part('hour',t.ts2_el_ts)
	,ts2_el_tm_min  = date_part('minute',t.ts2_el_ts)
where t.dtype = 'enty_UsrNodeFinStmt'
;
	

--id2_calc
raise notice 'Updating id2_calc';
update ampata_usr_node t
set id2_calc = UsrNodeFinStmt_Fn_getId2Calc(
		 t1.id2
		,t.ts2_el_ts
		)
from ampata_usr_node t1
where t.fin_acct1__id = t1.id
and	t.dtype = 'enty_UsrNodeFinStmt'
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case when id2 = id2_calc then false 
				else true
				end
where t.dtype = 'enty_UsrNodeFinStmt'
;


--id2_dup
raise notice 'Updating id2_dup';
num_rows_updated = 0;
for rec_tenant in
	select 
	 id
	,tenant_id
	,name name1
	from mten_tenant t
	order by name
	--limit 100
loop
	raise notice '--> Iteration';
	raise notice '--- id:% tenant_id:% name1:%', rec_tenant.id, rec_tenant.tenant_id, rec_tenant.name1;

-- Get count from UPDATE
	with rows as (
		update ampata_usr_node t
		set	id2_dup = t1.id2_dup
		from (
			select t.id2, count(*) id2_dup
			from ampata_usr_node t
			where t.dtype = 'enty_UsrNodeFinStmt'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.dtype = 'enty_UsrNodeFinStmt'
	    returning 1
	)
	select count(*) from rows into num_rows_updated_in_iter
	;
	num_rows_updated = num_rows_updated + num_rows_updated_in_iter;

	raise notice '--- num_rows_updated_in_iter:%', num_rows_updated_in_iter;
	raise notice '<-- Iteration';

end loop;
raise notice 'num_rows_updated:%', num_rows_updated;



--amt_net
raise notice 'Updating amt_net';
with cte1 as(
	select t1.id
	  , t2.id2 as type1__id2
	  , t2.bal_inc_on_debt
	  , t2.bal_inc_on_cred
	from ampata_usr_node t1
	inner join ampata_usr_node_type t2
		on t1.type1__id  = t2.id
	where t1.dtype = 'enty_UsrNodeFinAcct'
	and t1.deleted_by is null
	and t2.dtype = 'enty_UsrNodeFinAcctType'
)

,cte2 as (
select 
 t.id
,t.id2
,t.fin_acct1__id2
,t.amt_debt
,t.amt_cred
,case when coalesce(ct.bal_inc_on_debt,false) then 
    coalesce(t.amt_debt,0) - coalesce(t.amt_cred,0)
 else 
    case when coalesce(ct.bal_inc_on_cred,false) then 
        coalesce(t.amt_cred,0) - coalesce(t.amt_debt,0)
    else
        0
    end
 end as amt_net
from ampata_usr_node t
inner join cte1 ct
on t.fin_acct1__id = ct.id 
where	t.dtype = 'enty_UsrNodeFinStmt'
and t.deleted_by is null
)

--select * from cte2 

update ampata_usr_node t
set  amt_net = ct.amt_net
from cte2 ct
where t.id = ct.id
and	t.dtype = 'enty_UsrNodeFinStmt'
and t.deleted_by is null
;



--amt_end_bal_calc 
raise notice 'Updating amt_end_bal_calc';

--Using a window function will not provide teh desired functinality because the window function sum(amt_net) 
--is an aggregate function of the amt_net column and is not able to set a particular row to a given value amt_beg_bal
--in essence it can only give the running total of ONE particular column
--note: you could create a query with a calcuated column that for the first row is the sum of amt_net and amt_beg_bal
--otherwise it is amt_net
--this would ignore amt_beg_bal for non-first rows but it would work
--try a cursor loop instead of a window funciton

with cte1 as(
	select
		row_number() over(
			partition by fin_acct1__id
			order by ts2_el_ts
			) as nr
		,id
		,id2
		,desc1
		,fin_acct1__id
		,fin_acct1__id2
		,ts2_el_ts
		,amt_beg_bal
		,amt_net
		,case when
			row_number() over(
				partition by fin_acct1__id
				order by ts2_el_ts
			) = 1 then coalesce(amt_beg_bal,0) + amt_net else amt_net end
			as amt_net_merg
	from ampata_usr_node t
	where	t.dtype = 'enty_UsrNodeFinStmt'
	and deleted_by is null
--	and fin_acct1__id2 = '/Mark/A/RBC/Chk'
	order by fin_acct1__id, ts2_el_ts
)

,cte2 as (
	select
		 nr
		,id
		,id2
		,desc1
		,fin_acct1__id2
		,ts2_el_ts
		,amt_beg_bal
		,amt_net
		,amt_net_merg
		,sum(amt_net_merg) over(      
			partition by fin_acct1__id
			order by ts2_el_ts
			) AS amt_end_bal_calc
	from cte1
	order by fin_acct1__id, ts2_el_ts

)

--select * from cte2 

update ampata_usr_node t
set  amt_end_bal_calc = ct.amt_end_bal_calc
from cte2 ct
where t.id = ct.id
and	t.dtype = 'enty_UsrNodeFinStmt'
and t.deleted_by is null
;


--amt_beg_bal_calc
raise notice 'Updating amt_beg_bal_calc';
with cte1 as(
	select
		row_number() over(
			partition by fin_acct1__id
			order by ts2_el_ts
			) as nr
		,id
		,id2
		,desc1
		,fin_acct1__id
		,fin_acct1__id2
		,ts2_el_ts
		,amt_beg_bal
		,lag(amt_end_bal_calc,1) over(      
			partition by fin_acct1__id
			order by ts2_el_ts)
		as amt_beg_bal_calc
		,amt_net
		,amt_end_bal_calc
	from ampata_usr_node t
	where	t.dtype = 'enty_UsrNodeFinStmt'
	and deleted_by is null
--	and fin_acct1__id2 = '/Mark/A/RBC/Chk'
	order by fin_acct1__id, ts2_el_ts
)

--select * from cte1 

update ampata_usr_node t
set  amt_beg_bal_calc = ct.amt_beg_bal_calc
from cte1 ct
where t.id = ct.id
and	t.dtype = 'enty_UsrNodeFinStmt'
and t.deleted_by is null
;


--fin_acct1__id2
raise notice 'Updating fin_acct1__id2';
update ampata_usr_node t
set  fin_acct1__id2 = t1.fin_acct1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.fin_acct1__id
	,t2b.id2 as fin_acct1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_node t2b
	on t2a.fin_acct1__id = t2b.id
	where t2a.dtype = 'enty_UsrNodeFinStmt'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrNodeFinAcct'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinStmt'
and t.deleted_by is null
;


/*
select count(*)
from ampata_usr_node t
where t.dtype ='enty_UsrNodeFinStmt'
*/

end
$BODY$
;

call UsrNodeFinStmt_Pr_Upd_AllCalcVals_ForAllRows();

