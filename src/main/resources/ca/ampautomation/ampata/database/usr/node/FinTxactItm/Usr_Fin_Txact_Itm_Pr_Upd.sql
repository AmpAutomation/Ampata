

drop procedure if exists Usr_Fin_Txact_Itm_Pr_Upd;
drop procedure if exists Usr_Fin_Txact_Itm_Pr_Upd2;

create procedure Usr_Fin_Txact_Itm_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction

	
	
	
--Set default values
--fin_txact1__ei1__role
raise notice 'Updating fin_txact1__ei1__role';
update ampata_usr_node t
set fin_txact1__ei1__role = 'to'
where t.class_name ='FinTxactItm'
and t.deleted_by is null
and t.amt_debt is not null
;

--fin_txact1__ei1__role
raise notice 'Updating fin_txact1__ei1__role';
update ampata_usr_node t
set fin_txact1__ei1__role = 'fr'
where t.class_name ='FinTxactItm'
and t.deleted_by is null
and t.amt_cred is not null
;

	
--beg1.., beg2..
raise notice 'Updating beg1_date1.., beg2_date1..';
update ampata_usr_node t
set  beg1_date1 = beg1_ts1::date
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
	t.class_name = 'FinTxactItm'
;
	
--id_dt_date1
raise notice 'Updating id_dt_date1';
update ampata_usr_node t
set id_dt_date1  = case when t.beg2_date1 is not null then t.beg2_date1
		 		else t.beg1_date1
		 		end
where t.class_name = 'FinTxactItm'
;	


--id2_calc
raise notice 'Updating id2_calc';
update ampata_usr_node t
set	 id2_calc = Usr_Fin_Txact_Itm_Fn_get_Id2_Calc(
		t.id_dt_date1
		,t.id_x
		,t.id_y
		,t.id_z
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

where t.class_name = 'FinTxactItm'
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case when id2 = id2_calc then false 
				else true
				end
where t.class_name = 'FinTxactItm'
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
			where t.class_name = 'FinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'FinTxactItm'
	    returning 1
	)
	select count(*) from rows into num_rows_updated_in_iter
	;
	num_rows_updated = num_rows_updated + num_rows_updated_in_iter;

	raise notice '--- num_rows_updated_in_iter:%', num_rows_updated_in_iter;
	raise notice '<-- Iteration';

end loop;
raise notice 'num_rows_updated:%', num_rows_updated;



--type1__id2
raise notice 'Updating type1__id2';
update ampata_usr_node t
set  type1__id2 = t1.type1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.type1__id
	,t2b.id2 as type1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_node_type t2b
	on t2a.type1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinTxactItm'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;



--gen_chan1__id2
raise notice 'Updating gen_chan1__id2';
update ampata_usr_node t
set  gen_chan1__id2 = t1.gen_chan1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.gen_chan1__id
	,t2b.id2 as gen_chan1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_node t2b
	on t2a.gen_chan1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.class_name = 'GenChan'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;


--fin_how1__id2
raise notice 'Updating fin_how1__id2';
update ampata_usr_node t
set  fin_how1__id2 = t1.fin_how1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.fin_how1__id
	,t2b.id2 as fin_how1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_item t2b
	on t2a.fin_how1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrFinHow'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;



--fin_what1__id2
raise notice 'Updating fin_what1__id2';
update ampata_usr_node t
set  fin_what1__id2 = t1.fin_what1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.fin_what1__id
	,t2b.id2 as fin_what1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_item t2b
	on t2a.fin_what1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrFinWhat'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;



--fin_why1__id2
raise notice 'Updating fin_why1__id2';
update ampata_usr_node t
set  fin_why1__id2 = t1.fin_why1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.fin_why1__id
	,t2b.id2 as fin_why1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_item t2b
	on t2a.fin_why1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrFinWhy'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
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
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinAcct'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;

--fin_acct2__id2
raise notice 'Updating fin_acct2__id2';
update ampata_usr_node t
set  fin_acct2__id2 = t1.fin_acct2__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.fin_acct1__id
	,t2a.fin_acct1__id2
	,t2b.id t2b_id
	,t2b.id2 t2b_id2
	,t2b.id2 fin_acct1__id2
	, case when t2b.id is not null then t2b.fin_acct1__id2 else t2a.fin_acct1__id2 end as fin_acct2__id2
	from ampata_usr_node t2a
	left join ( 
		select t3a.id
			,t3a.id2
			,t3a.fin_acct1__id2
		from ampata_usr_node t3a
		inner join ampata_usr_node t3b
			on t3a.fin_acct1__id = t3b.id
		where t3a.class_name = 'FinTxactItm'
		and t3a.deleted_by is null
		and t3b.class_name = 'FinAcct'
		) t2b
		on t2a.amt_fin_txact_itm1__id = t2b.id
	
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null

	) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;



--fin_dept1__id2
raise notice 'Updating fin_dept1__id2';
update ampata_usr_node t
set  fin_dept1__id2 = t1.fin_dept1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.fin_dept1__id
	,t2b.id2 as fin_dept1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_node t2b
	on t2a.fin_dept1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinDept'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;


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
inner join cte1 ct
on t.fin_acct1__id = ct.id 
where	t.class_name = 'FinTxactItm'
and t.deleted_by is null
)

--select * from cte2 

update ampata_usr_node t
set  amt_net = ct.amt_net
from cte2 ct
where t.id = ct.id
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;


--amt_end_bal_calc 
raise notice 'Updating amt_end_bal_calc';

--Using a window function will not provide the complete functinality because the window function sum(amt_net) 
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
			order by id_dt_date1, id_x, id_y, id_z
			) as nr
		,id
		,id2
		,desc1
		,fin_acct1__id
		,fin_acct1__id2
		,id_dt_date1
		,id_x
		,id_y
		,id_z
		,amt_beg_bal
		,amt_net
		,case when
			row_number() over(
				partition by fin_acct1__id
				order by id_dt_date1, id_x, id_y, id_z
			) = 1 then coalesce(amt_beg_bal,0) + amt_net else amt_net end
			as amt_net_merg
	from ampata_usr_node t
	where	t.class_name = 'FinTxactItm'
	and deleted_by is null
--	and fin_acct1__id2 = '/Mark/A/RBC/Chk'
	order by fin_acct1__id, id_dt_date1, id_x, id_y, id_z
)

,cte2 as (
	select
		 nr
		,id
		,id2
		,desc1
		,fin_acct1__id2
		,id_dt_date1
		,id_x
		,id_y
		,id_z
		,amt_beg_bal
		,amt_net
		,amt_net_merg
		,sum(amt_net_merg) over(      
			partition by fin_acct1__id
			order by id_dt_date1, id_x, id_y, id_z
			) AS amt_end_bal_calc
	from cte1
	order by fin_acct1__id, id_dt_date1, id_x, id_y, id_z

)

--select * from cte2 

update ampata_usr_node t
set  amt_end_bal_calc = ct.amt_end_bal_calc
from cte2 ct
where t.id = ct.id
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;


-- amt_beg_bal_calc
raise notice 'Updating amt_beg_bal_calc';
with cte1 as(
	select
		row_number() over(
			partition by fin_acct1__id
	      	order by id_dt_date1, id_x, id_y, id_z
			) as nr
		,id
		,id2
		,desc1
		,fin_acct1__id
		,fin_acct1__id2
		,id_dt_date1
		,id_x
		,id_y
		,id_z
		,amt_beg_bal
		,lag(amt_end_bal_calc,1) over(      
			partition by fin_acct1__id
			order by id_dt_date1, id_x, id_y, id_z
			) as amt_beg_bal_calc
		,amt_net
		,amt_end_bal_calc
	from ampata_usr_node t
	where	t.class_name = 'FinTxactItm'
	and deleted_by is null
--	and fin_acct1__id2 = '/Mark/A/RBC/Chk'
	order by fin_acct1__id, id_dt_date1, id_x, id_y, id_z
)

--select * from cte1 

update ampata_usr_node t
set  amt_beg_bal_calc = ct.amt_beg_bal_calc
from cte1 ct
where t.id = ct.id
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;




/*
do $$
declare
	node record;
	node2 ampata_usr_node%ROWTYPE;
	_id2 varchar(50);
	_id uuid;
	amt numeric(19,2);
	num_rows int;
	num_rows_updated int;

begin
	num_rows_updated = 0;
	for node in
		select 
		 id
		,id2
		,class_name
		,fin_acct1__id
		,id_dt_date1 
		,amt_debt
		,amt_cred
		,amt_net
		,fin_stmt_itm1__id
		from ampata_usr_node t
		where t.class_name = 'FinTxactItm'
		and t.fin_stmt_itm1__id is null
--		and t.fin_acct1__id = 'addff8fa-a1a3-47ef-9309-62c1adab4998' -- /Mark/L/RBC/Visa
		and t.fin_acct1__id = 'cd75edac-f633-4dea-b657-0c0d472e0bdb' -- /Mark/L/RBC/Visa_USD

		
		order by id2
		--limit 100
	loop
	    
		RAISE NOTICE 'id:% id2:% class_name:% fin_acct1__id:% id_dt_date1:% amt_debt:% amt_cred:% fin_stmt_itm1__id:%', node.id, node.id2, node.class_name, node.fin_acct1__id, node.id_dt_date1, node.amt_debt, node.amt_cred, node.fin_stmt_itm1__id;

		if node.amt_debt is not null then
	    	amt = node.amt_debt;
	    elseif node.amt_cred is not null then
	    	amt = node.amt_cred;
	    else 
	    	continue;
	    end if;
		RAISE NOTICE 'searching for fin_acct1__id2:% id_dt_date1:% amt:% ', '/Mark/L/RBC/Visa', node.id_dt_date1, amt;
	   
		select count(*) into num_rows
		FROM ampata_usr_node t
		inner join ampata_usr_node t2
		on t.fin_stmt1__id = t2.id
		where t.class_name = 'FinStmtItm'
		and t.deleted_by is null
		and t2.class_name = 'FinStmt'
		and t2.fin_acct1__id = node.fin_acct1__id
		and t.id_dt_date1 between node.id_dt_date1 - interval '3 day' and node.id_dt_date1 + interval '3 day'
		and (t.amt_debt = amt or t.amt_cred = amt)
		;

		RAISE NOTICE 'num_rows:%', num_rows;

		if num_rows = 1 then
		
			SELECT * INTO node2 
			FROM ampata_usr_node t
			inner join ampata_usr_node t2
			on t.fin_stmt1__id = t2.id
			where t.class_name = 'FinStmtItm'
			and t.deleted_by is null
			and t2.class_name = 'FinStmt'
			and t2.fin_acct1__id = node.fin_acct1__id
			and t.id_dt_date1 between node.id_dt_date1 - interval '3 day' and node.id_dt_date1 + interval '3 day'
			and (t.amt_debt = amt or t.amt_cred = amt)
			;
		
			RAISE NOTICE 'FOUND - id:% id2:% class_name:% fin_stmt1__id:% id_dt_date1:% amt_debt:% amt_cred:%', node2.id, node2.id2, node2.class_name, node2.fin_stmt1__id, node2.id_dt_date1, node2.amt_debt, node2.amt_cred;

			update ampata_usr_node 
			set fin_stmt_itm1__id = node2.id 
			WHERE id = node.id;

			num_rows_updated = num_rows_updated + 1;

		else
			raise notice 'NOT FOUND - rows:%', num_rows;
		end if;
		raise notice '';
		
	end loop;

	raise notice 'num_rows_updated:%', num_rows_updated;
end;
$$;



*/



--fin_curcy1__id2
raise notice 'Updating fin_curcy1__id2';
update ampata_usr_node t
set  sys_fin_curcy1__id2 = t1.sys_fin_curcy1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.sys_fin_curcy1__id
	,t2b.id2 as sys_fin_curcy1__id2
	from ampata_usr_node t2a
	inner join ampata_sys_node t2b
	on t2a.sys_fin_curcy1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinCurcy'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;


--fin_stmt_itm1__desc1
--fin_stmt_itm1__desc2
--fin_stmt_itm1__desc3
--fin_stmt_itm1__desc4
--fin_stmt_itm1__exch_desc
--fin_stmt_itm1__ref_id
raise notice 'Updating fin_stmt_itm1__desc1..4, fin_stmt_itm1__exch_desc, fin_stmt_itm1__ref_id';
update ampata_usr_node t
set 
 fin_stmt_itm1__desc1 = t1.fin_stmt_itm1__desc1
,fin_stmt_itm1__desc2 = t1.fin_stmt_itm1__desc2
,fin_stmt_itm1__desc3 = t1.fin_stmt_itm1__desc3
,fin_stmt_itm1__desc4 = t1.fin_stmt_itm1__desc4
from (
	select t2a.id
	,t2a.id2
	,t2a.fin_stmt_itm1__id
	,t2b.id2 as fin_stmt_itm1__id2
	,t2b.desc1 as fin_stmt_itm1__desc1
	,t2b.desc2 as fin_stmt_itm1__desc2
	,t2b.desc3 as fin_stmt_itm1__desc3
	,t2b.desc4 as fin_stmt_itm1__desc4
	,t2b.exch_desc as fin_stmt_itm1__exch_desc
	,t2b.ref_id as fin_stmt_itm1__ref_id
	from ampata_usr_node t2a
	inner join ampata_usr_node t2b
	on t2a.fin_stmt_itm1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinStmtItm'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;


/*

select unnest(Array['One',Null,'Three'])

select array_to_string(array['One',Null,'Three'],' ')

*/



--desc1
raise notice 'Updating desc1';
update ampata_usr_node t
set  desc1 = array_to_string(array[t1.desc1_amt, t1.desc1_curcy, t1.desc1_acct, t1.desc1_dept, t1.desc1_stmt_itm], ' ')
from (
	select t2a.id
	,t2a.id2
	,t2a.amt_debt
	,t2a.amt_cred
	,case when t2a.amt_debt is null and t2a.amt_cred is null
		then null
		else   coalesce (case when t2a.amt_debt is not null then 'Debt ' ||  t2a.amt_debt || '' else null end, '')
			|| coalesce (case when t2a.amt_cred is not null then 'Cred ' ||  t2a.amt_cred || '' else null end, '')
		end 
		desc1_amt
	,case when t2a.sys_fin_curcy1__id2 is not null then t2a.sys_fin_curcy1__id2 else null end desc1_curcy
	,case when t2a.fin_acct1__id2 is not null then 'to acct [' || t2a.fin_acct1__id2 || ']' else null end desc1_acct
	,case when t2a.fin_dept1__id2 is not null then 'to dept [' || t2a.fin_dept1__id2 || ']' else null end desc1_dept
	,t2a.fin_stmt_itm1__desc1
	,t2a.fin_stmt_itm1__desc2
	,case when coalesce(t2a.fin_stmt_itm1__desc1,'') = '' and coalesce(t2a.fin_stmt_itm1__desc2,'') = '' then null else 'for ' || array_to_string(array[t2a.fin_stmt_itm1__desc1, t2a.fin_stmt_itm1__desc2], ' ') || '' end desc1_stmt_itm
	from ampata_usr_node t2a
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;



--fin_txact1__type1__id2
raise notice 'Updating fin_txact1__type1__id2';
update ampata_usr_node t
set  fin_txact1__type1__id2  = t1.fin_txact1__type1__id2
from (
	--get a list of tx with tx groups and the tx group type1
	select ftx.id
	  , ftxt.id2  as fin_txact1__type1__id2
	from ampata_usr_node ftx
	inner join ampata_usr_node_type ftxt
		on ftx.type1__id  = ftxt.id
	where ftx.class_name = 'FinTxact'
	and ftx.deleted_by is null
	and ftxt.class_name = 'FinTxact'
) t1
where t.fin_txact1__id = t1.id 
;

--fin_txact1__id2_trgt
raise notice 'Updating fin_txact1__id2_trgt';
update ampata_usr_node t
set	fin_txact1__id2_trgt = substring(id2_calc,1,20)
where
	t.class_name = 'FinTxactItm'
and deleted_by is null
;

--fin_txact1__fin_txact_itms1__id2
raise notice 'Updating fin_txact1__fin_txact_itms1__id2';
update ampata_usr_node t
set  fin_txact1__fin_txact_itms1__id2 = t1.ids2
from (
select t2.fin_txact1__id as id, array_to_string(array_agg(t2.id2), ',') as ids2
from (
select fin_txact1__id, id2
from ampata_usr_node t
where t.class_name = 'FinTxactItm'
and t.deleted_by is null
order by fin_txact1__id, id2
) t2
group by t2.fin_txact1__id
) t1
where t.fin_txact1__id  = t1.id
;



--fin_txact1__fin_accts1__id2
raise notice 'Updating fin_txact1__fin_accts1__id2';
update ampata_usr_node t
set  fin_txact1__fin_accts1__id2 = t1.ids2
from (
select t2.fin_txact1__id as id, array_to_string(array_agg(t2.fin_acct1__id2), ',') as ids2
from (
select fin_txact1__id, fin_acct1__id2
from ampata_usr_node
where class_name = 'FinTxactItm'
and deleted_by is null
order by fin_txact1__id, id2
) t2
group by t2.fin_txact1__id
) t1
where t.fin_txact1__id  = t1.id
;





--fin_txact_set1__type1__id2
raise notice 'Updating fin_txact_set1__type1__id2';
update ampata_usr_node t
set  fin_txact_set1__type1__id2  = t1.fin_txact_set1__type1__id2
from (
	--get a list of tx with tx groups and the tx group type1
	select ftx.id
	  , ftxg.id as fin_txact_set1__id
	  , ftxt.id2  as fin_txact_set1__type1__id2
	from ampata_usr_node ftx
	inner join ampata_usr_node ftxg
		on ftx.fin_txact_set1__id  = ftxg.id
	inner join ampata_usr_node_type ftxt
		on ftxg.type1__id  = ftxt.id
	where ftx.class_name = 'FinTxact'
	and ftx.deleted_by is null
	and ftxg.class_name = 'FinTxactSet'
	and ftxt.class_name = 'FinTxactSet'
) t1
where t.fin_txact1__id = t1.id 
;


--fin_txact_set1__desc1
raise notice 'Updating fin_txact_set1__desc1';
update ampata_usr_node t
set  fin_txact_set1__desc1  = t1.fin_txact_set1__desc1
from (
	--get a list of tx with tx groups and the tx group desc1
	select ftx.id
	  , ftxg.id as fin_txact_set1__id
	  , ftxg.desc1  as fin_txact_set1__desc1
	from ampata_usr_node ftx
	inner join ampata_usr_node ftxg
		on ftx.fin_txact_set1__id  = ftxg.id
	where ftx.class_name = 'FinTxact'
	and ftx.deleted_by is null
	and ftxg.class_name = 'FinTxactSet'
) t1
where t.fin_txact1__id = t1.id 
;


--fin_txact_set1__gen_chan1__id2
raise notice 'Updating fin_txact_set1__gen_chan1__id2';
update ampata_usr_node t
set  fin_txact_set1__gen_chan1__id2 = t1.fin_txact_set1__gen_chan1__id2
from (
	--get a list of tx with tx groups and the tx group gen_chan1__id2
	select ftx.id
	  , ftxg.id as fin_txact_set1__id
	  , gch.id2  as fin_txact_set1__gen_chan1__id2
	from ampata_usr_node ftx
	inner join ampata_usr_node ftxg
		on ftx.fin_txact_set1__id  = ftxg.id
	inner join ampata_usr_node gch
		on ftxg.gen_chan1__id  = gch.id
	where ftx.class_name = 'FinTxact'
	and ftx.deleted_by is null
	and ftxg.class_name = 'FinTxactSet'
	and gch.class_name = 'GenChan'
) t1
where t.fin_txact1__id = t1.id 
;


--fin_txact_set1__what1__id2
raise notice 'Updating fin_txact_set1__gen_chan1__id2';
update ampata_usr_node t
set  fin_txact_set1__what1__id2  = t1.fin_txact_set1__what1__id2
from (
	--get a list of tx with tx groups and the tx group what1__id2
	select ftx.id
	  , ftxg.id as fin_txact_set1__id
	  , fwht.id2 as fin_txact_set1__what1__id2
	from ampata_usr_node ftx
	inner join ampata_usr_node ftxg
		on ftx.fin_txact_set1__id  = ftxg.id
	inner join ampata_usr_item fwht
		on ftxg.fin_what1__id  = fwht.id
	where ftx.class_name = 'FinTxact'
	and ftx.deleted_by is null
	and ftxg.class_name = 'FinTxactSet'
	and fwht.dtype = 'enty_UsrFinWhat'
) t1
where t.fin_txact1__id = t1.id 
;

--fin_txact_set1__what_text1
raise notice 'Updating fin_txact_set1__what_text1';
update ampata_usr_node t
set  fin_txact_set1__what_text1  = t1.fin_txact_set1__what_text1
from (
	--get a list of tx with tx groups and the tx group what_text1
	select ftx.id
	  , ftxg.id as fin_txact_set1__id
	  , ftxg.what_text1 as fin_txact_set1__what_text1
	from ampata_usr_node ftx
	inner join ampata_usr_node ftxg
		on ftx.fin_txact_set1__id  = ftxg.id
	where ftx.class_name = 'FinTxact'
	and ftx.deleted_by is null
	and ftxg.class_name = 'FinTxactSet'
) t1
where t.fin_txact1__id = t1.id 
;



--fin_txact_set1__why1__id2
raise notice 'Updating fin_txact_set1__why1__id2';
update ampata_usr_node t
set  fin_txact_set1__why1__id2  = t1.fin_txact_set1__why1__id2
from (
	--get a list of tx with tx groups and the tx group why1__id2
	select ftx.id
	  , ftxg.id as fin_txact_set1__id
	  , fwhy.id2 as fin_txact_set1__why1__id2
	from ampata_usr_node ftx
	inner join ampata_usr_node ftxg
		on ftx.fin_txact_set1__id  = ftxg.id
	inner join ampata_usr_item fwhy
		on ftxg.fin_why1__id  = fwhy.id
	where ftx.class_name = 'FinTxact'
	and ftx.deleted_by is null
	and ftxg.class_name = 'FinTxactSet'
	and fwhy.dtype = 'enty_UsrFinWhy'
) t1
where t.fin_txact1__id = t1.id 
;

--fin_txact_set1__why_text1
raise notice 'Updating fin_txact_set1__why_text1';
update ampata_usr_node t
set  fin_txact_set1__why_text1  = t1.fin_txact_set1__why_text1
from (
	--get a list of tx with tx groups and the tx group why_text1
	select ftx.id
	  , ftxg.id as fin_txact_set1__id
	  , ftxg.why_text1 as fin_txact_set1__why_text1
	from ampata_usr_node ftx
	inner join ampata_usr_node ftxg
		on ftx.fin_txact_set1__id  = ftxg.id
	where ftx.class_name = 'FinTxact'
	and ftx.deleted_by is null
	and ftxg.class_name = 'FinTxactSet'
) t1
where t.fin_txact1__id = t1.id 
;



--fin_txact_set1__fin_txacts1__id2
raise notice 'Updating fin_txact_set1__fin_txacts1__id2';
update ampata_usr_node ftxi
set  fin_txact_set1__fin_txacts1__id2 = ftxg.ids2
from (
	select t2.fin_txact_set1__id as id, array_to_string(array_agg(t2.fin_txact1__id2), ',') as ids2
	from (
		select ftxi.id
		  , ftxi.id2
		  , ftx.id as fin_txact1__id
		  , ftx.id2 as fin_txact1__id2
		  , ftxg.id as fin_txact_set1__id
		  , ftxg.id2 as fin_txact_set1__id2
		from ampata_usr_node ftxi
		inner join ampata_usr_node ftx
			on ftxi.fin_txact1__id  = ftx.id
		inner join ampata_usr_node ftxg
			on ftx.fin_txact_set1__id  = ftxg.id
		where ftxi.class_name = 'FinTxactItm'
		and ftxi.deleted_by is null
		and ftx.class_name = 'FinTxact'
		and ftxg.class_name = 'FinTxactSet'
	) t2
	group by t2.fin_txact_set1__id
) ftxg
, ampata_usr_node ftx
where ftxi.fin_txact1__id = ftx.id
and ftx.fin_txact_set1__id = ftxg.id
;


--fin_txact_set1__fin_accts1__id2
update ampata_usr_node ftxi
set  fin_txact_set1__fin_accts1__id2 = ftxg.ids2
from (
	select t2.fin_txact_set1__id as id, array_to_string(array_agg(t2.fin_acct1__id2), ',') as ids2
	from (
		select ftxi.id
		  , ftxi.id2
		  , ftxi.fin_acct1__id
		  , ftxi.fin_acct1__id2
		  , ftx.id as fin_txact1__id
		  , ftx.id2 as fin_txact1__id2
		  , ftxg.id as fin_txact_set1__id
		  , ftxg.id2 as fin_txact_set1__id2
		from ampata_usr_node ftxi
		inner join ampata_usr_node ftx
			on ftxi.fin_txact1__id  = ftx.id
		inner join ampata_usr_node ftxg
			on ftx.fin_txact_set1__id  = ftxg.id
		where ftxi.class_name = 'FinTxactItm'
		and ftxi.deleted_by is null
		and ftx.class_name = 'FinTxact'
		and ftxg.class_name = 'FinTxactSet'
	) t2
	group by t2.fin_txact_set1__id
) ftxg
, ampata_usr_node ftx
where ftxi.fin_txact1__id = ftx.id
and ftx.fin_txact_set1__id = ftxg.id

;





-- amt_fin_txact_itm1__fin_acct1__id2
--Used for references to currency exchanges, GST and depreciation
raise notice 'Updating amt_fin_txact_itm1__fin_acct1__id2';
update ampata_usr_node t
set  amt_fin_txact_itm1__fin_acct1__id2 = t1.fin_acct1__id2
from (
	select t2a.id as amt_fin_txact_itm1__id
	  , t2b.id as fin_acct1__id
	  , t2b.id2  as fin_acct1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_node t2b
		on t2a.fin_acct1__id = t2b.id
	where t2a.class_name = 'FinTxactItm'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinAcct'
) t1
where t.amt_fin_txact_itm1__id = t1.amt_fin_txact_itm1__id 
and	t.class_name = 'FinTxactItm'
and t.deleted_by is null
;





/*
select 
Fin_Txact_Itm_Fn_get_Id2_Calc(
		 t.id_dt_date1 
		,t.id_x
		,t.id_y
		,t.id_z
		) id2_calc
	,id_dt_date1::date id_ts_date1
	,date_part('year',id_dt_date1) as id_ts_date1_yr
	,date_part('quarter',id_dt_date1) as id_ts_date1_qtr
	,date_part('Mon',id_dt_date1) as id_ts_date1_mon
	,to_char(id_dt_date1,'Mon') as id_ts_date1_mon2
	,date_part('day',id_dt_date1) as id_ts_date1_day
	,id_dt_date1::time as id_ts_time1
	,date_part('hour',id_dt_date1) as id_ts_time1_hr
	,date_part('minute',id_dt_date1) as id_ts_time1_min
from ampata_usr_node t
where t.class_name ='FinTxactItm'
*/


/*
 * select t.id2, t.fin_txact1__fin_txact_itms1__id2, t1.ids2
from (
select t2.fin_txact1__id as id, array_to_string(array_agg(t2.id2), ',') as ids2
from (
select fin_txact1__id, id2, class_name 
from ampata_usr_node
order by fin_txact1__id, id2
) t2
where t2.class_name = 'FinTxactItm'
group by t2.fin_txact1__id
) t1
, ampata_usr_node t
where
	t.fin_txact1__id  = t1.id
and t.class_name = 'FinTxactItm'
;
*/

/*
select t.amt_debt, t.amt_cred
from ampata_usr_node t
inner join ampata_usr_node ta
on t.fin_acct1__id = ta.id 
where t.class_name = 'FinTxactItm'
and ta.class_name = 'FinAcct'
group by t.fin_txact1__id 
*/

/*
select t.id2, count(*) id2_dup
from ampata_usr_node t
where
	t.class_name = 'FinTxactItm'
and t.id2='/D20191025/T0000/X02/Y01/Z00'
and t.deleted_by is null
group by t.id2
*/


/*
select count(*)
from ampata_usr_node t
where t.class_name ='FinTxactItm'
*/

/*
update ampata_usr_node t
set type1__id = 'dcc34cd9-ddc8-4622-996b-a8dd86db652a'
,type1__id2 = '/'
where t.class_name ='FinTxactItm'
and t.deleted_by is null
;
*/

end
$BODY$
;


create procedure Usr_Fin_Txact_Itm_Pr_Upd2(inParam1 IN TEXT)
language 'plpgsql'	
as $BODY$
declare
begin
-- Stored procedures are atomic and are executed as a transaction

	call Usr_Fin_Txact_Itm_Pr_Upd();

end
$BODY$
;



call Usr_Fin_Txact_Itm_Pr_Upd();




