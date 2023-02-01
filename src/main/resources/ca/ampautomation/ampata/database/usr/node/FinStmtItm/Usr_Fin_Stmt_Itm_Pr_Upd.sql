
drop procedure if exists Usr_Fin_Stmt_Itm_Pr_Upd;

create procedure Usr_Fin_Stmt_Itm_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;
begin
-- Stored procedures are atomic and are executed as a transaction

--beg1_date1.., end1_date1..
raise notice 'Updating beg1_date1.., end1_date1..';
update ampata_usr_node t
set 
	 beg1_date1 = beg1_ts1::date
	,beg1_date1_yr = date_part('year',beg1_ts1)
	,beg1_date1_qtr = date_part('quarter',beg1_ts1)
	,beg1_date1_mon =  date_part('Mon',beg1_ts1)
	,beg1_date1_mon2 =  to_char(beg1_ts1,'Mon')
	,beg1_date1_day = date_part('day',beg1_ts1)
	,beg1_time1  = beg1_ts1::time
	,beg1_time1_hr  = date_part('hour',beg1_ts1)
	,beg1_time1_min  = date_part('minute',beg1_ts1)

	,beg2_date1 = beg2_ts1::date
	,beg2_date1_yr = date_part('year',beg2_date1)
	,beg2_date1_qtr = date_part('quarter',beg2_ts1)
	,beg2_date1_mon =  date_part('Mon',beg2_date1)
	,beg2_date1_mon2 =  to_char(beg2_date1,'Mon')
	,beg2_date1_day = date_part('day',beg2_date1)
	,beg2_time1  = beg2_ts1::time
	,beg2_time1_hr  = date_part('hour',beg2_ts1)
	,beg2_time1_min  = date_part('minute',beg2_ts1)
where
	t.class_name = 'FinStmtItm'
;
	

--id_dt_date1
raise notice 'Updating id_dt_date1';
update ampata_usr_node t
set id_dt_date1  = case when beg2_date1 is not null then beg2_date1
		 		else beg1_date1
		 		end
where class_name = 'FinStmtItm'
;	


--id2_calc, id_dt_date1..
raise notice 'Updating id2_calc, id_dt_date1..';
update ampata_usr_node t
set id2_calc = Usr_Fin_Stmt_Itm_Fn_get_Id2_Calc(
		 t1.fin_acct1_id2
		,t.id_dt_date1
		,t.amt_net
		,t.id_x
		)
	,id_dt_date1_yr = date_part('year',t.id_dt_date1)
	,id_dt_date1_qtr = date_part('quarter',t.id_dt_date1)
	,id_dt_date1_mon =  date_part('Mon',t.id_dt_date1)
	,id_dt_date1_mon2 =  to_char(t.id_dt_date1,'Mon')
	,id_dt_date1_day = date_part('day',t.id_dt_date1)
		
--	,id_ts_date1 = id_ts_ts1::date
--	,id_ts_date1_yr = date_part('year',id_ts_ts1)
--	,id_ts_date1_qtr = date_part('quarter',id_ts_ts1)
--	,id_ts_date1_mon =  date_part('Mon',id_ts_ts1)
--	,id_ts_date1_mon2 =  to_char(id_ts_ts1,'Mon')
--	,id_ts_date1_day = date_part('day',id_ts_ts1)
--	,id_ts_time1  = id_ts_ts1::time
--	,id_ts_time1_hr  = date_part('hour',id_ts_ts1)
--	,id_ts_time1_min  = date_part('minute',id_ts_ts1)

FROM (

SELECT 
 t2a.id
,t2a.id2
,Usr_Fin_Stmt_Itm_Fn_get_Id2_Calc(
		 t2c.id2
		,t2a.id_ts_ts1::date
		,t2a.amt_net
		,t2a.id_x
		) as id2_calc 
,t2a.id_ts_ts1::date AS id_dt_date1
,t2a.amt_net
,t2a.id_x
,t2b.id2 AS fin_stmt1_id2
,t2c.id2 AS fin_acct1_id2
FROM ampata_usr_node t2a --FinStmtItm
INNER JOIN ampata_usr_node t2b  --FinStmt
on t2a.fin_stmt1__id = t2b.id
INNER JOIN ampata_usr_node t2c  --FinAcct
on t2b.fin_acct1__id = t2c.id
where t2a.class_name = 'FinStmtItm'
and t2a.deleted_by IS null
and t2b.class_name = 'FinStmt'
and t2c.class_name = 'FinAcct'

) t1
where t.id = t1.id
and t.class_name = 'FinStmtItm'
AND t.deleted_by IS null
;

--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case when id2 = id2_calc then false 
				else true
				end
where t.class_name = 'FinStmtItm'
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
			where t.class_name = 'FinStmtItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'FinStmtItm'
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
	where t1.class_name = 'FinAcct'
	and t1.deleted_by is null
	and t2.class_name = 'FinAcct'
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
inner join ampata_usr_node t2
on t.fin_stmt1__id = t2.id 
inner join cte1 ct
on t2.fin_acct1__id = ct.id
where	t.class_name = 'FinStmtItm'
and t.deleted_by is null
)

--select * from cte2 

update ampata_usr_node t
set  amt_net = ct.amt_net
from cte2 ct
where t.id = ct.id
and	t.class_name = 'FinStmtItm'
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
			order by id_ts_ts1::date, id_x, amt_net
			) as nr
		,id
		,id2
		,desc1
		,fin_acct1__id
		,fin_acct1__id2
		,id_ts_ts1::date
		,id_x
		,amt_beg_bal
		,amt_net
		,case when
			row_number() over(
				partition by fin_acct1__id
				order by id_ts_ts1::date, id_x, amt_net
			) = 1 then coalesce(amt_beg_bal,0) + amt_net else amt_net end
			as amt_net_merg
	from ampata_usr_node t
	where	t.class_name = 'FinStmtItm'
	and deleted_by is null
--	and fin_acct1__id2 = '/Mark/A/RBC/Chk'
	order by fin_acct1__id, id_ts_ts1::date, id_x, amt_net
)

,cte2 as (
	select
		 nr
		,id
		,id2
		,desc1
		,fin_acct1__id2
		,id_ts_ts1::date
		,id_x
		,amt_beg_bal
		,amt_net
		,amt_net_merg
		,sum(amt_net_merg) over(      
			partition by fin_acct1__id
			order by id_ts_ts1::date, id_x, amt_net
			) AS amt_end_bal_calc
	from cte1
	order by fin_acct1__id, id_ts_ts1::date, id_x, amt_net

)

--select * from cte2 

update ampata_usr_node t
set  amt_end_bal_calc = ct.amt_end_bal_calc
from cte2 ct
where t.id = ct.id
and	t.class_name = 'FinStmtItm'
and t.deleted_by is null
;


-- amt_beg_bal_calc
raise notice 'Updating amt_beg_bal_calc';
with cte1 as(
	select
		row_number() over(
			partition by fin_acct1__id
			order by id_ts_ts1::date, id_x, amt_net
			) as nr
		,id
		,id2
		,desc1
		,fin_acct1__id
		,fin_acct1__id2
		,id_ts_ts1::date
		,id_x
		,amt_beg_bal
		,lag(amt_end_bal_calc,1) over(      
			partition by fin_acct1__id
			order by id_ts_ts1::date, id_x, amt_net
			) as amt_beg_bal_calc
		,amt_net
		,amt_end_bal_calc
	from ampata_usr_node t
	where	t.class_name = 'FinStmtItm'
	and deleted_by is null
--	and fin_acct1__id2 = '/Mark/A/RBC/Chk'
	order by fin_acct1__id, id_ts_ts1::date, id_x, amt_net
)

--select * from cte1 

update ampata_usr_node t
set  amt_beg_bal_calc = ct.amt_beg_bal_calc
from cte1 ct
where t.id = ct.id
and	t.class_name = 'FinStmtItm'
and t.deleted_by is null
;




--fin_stmt1__id2
raise notice 'Updating fin_stmt1__id2';
update ampata_usr_node t
set	fin_stmt1__id2 = t1.id2
from ampata_usr_node t1
where t.fin_stmt1__id = t1.id 
and	t.class_name = 'FinStmtItm'
and	t.deleted_by is null
and	t1.class_name = 'FinStmt'
;



/*
select count(*)
from ampata_usr_node t
where t.class_name ='FinStmtItm'
*/

end
$BODY$
;

call Usr_Fin_Stmt_Itm_Pr_Upd();

