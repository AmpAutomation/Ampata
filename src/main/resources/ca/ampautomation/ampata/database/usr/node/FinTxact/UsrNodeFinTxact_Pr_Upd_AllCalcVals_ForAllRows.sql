
drop procedure if exists UsrNodeFinTxact_Pr_Upd_AllCalcVals_ForAllRows;

create procedure UsrNodeFinTxact_Pr_Upd_AllCalcVals_ForAllRows()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction

--ts1_el_dt..
raise notice 'Updating ts1_el_dt..';
update ampata_usr_node t
set  ts1_el_dt = ts1_el_ts::date
	,ts1_el_dt_yr = date_part('year',ts1_el_ts)
	,ts1_el_dt_qtr = date_part('quarter',ts1_el_ts)
	,ts1_el_dt_mon =  date_part('Mon',ts1_el_ts)
	,ts1_el_dt_mon2 =  to_char(ts1_el_ts,'Mon')
	,ts1_el_dt_day = date_part('day',ts1_el_ts)
	,ts1_el_tm  = ts1_el_ts::time
	,ts1_el_tm_hr  = date_part('hour',ts1_el_ts)
	,ts1_el_tm_min  = date_part('minute',ts1_el_ts)
where
	t.dtype = 'enty_UsrNodeFinTxact'
;


--id2_calc
raise notice 'Updating id2_calc';
update ampata_usr_node t
set	 id2_calc = UsrNodeFinTxact_Fn_getId2Calc(
		t.ts1_el_ts
		,t.int1
		,t.sort_idx
		)
where t.dtype = 'enty_UsrNodeFinTxact'
;

--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case when id2 = id2_calc then false 
				else true
				end
where t.dtype = 'enty_UsrNodeFinTxact'
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
			where t.dtype = 'enty_UsrNodeFinTxact'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.dtype = 'enty_UsrNodeFinTxact'
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
	where t2a.dtype = 'enty_UsrNodeFinTxact'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrNodeFinTxactType'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;

--gen_chan1__id2
raise notice 'Updating gen_chan1__id2';
update ampata_usr_node t
set  gen_chan1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;
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
	where t2a.dtype = 'enty_UsrNodeFinTxact'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrNodeGenChan'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;


--fin_how1__id2
raise notice 'Updating fin_how1__id2';
update ampata_usr_node t
set  fin_how1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;
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
	where t2a.dtype = 'enty_UsrNodeFinTxact'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrItemFinHow'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;



--fin_what1__id2
raise notice 'Updating fin_what1__id2';
update ampata_usr_node t
set  fin_what1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;
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
	where t2a.dtype = 'enty_UsrNodeFinTxact'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrItemFinWhat'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;



--fin_why1__id2
raise notice 'Updating fin_why1__id2';
update ampata_usr_node t
set  fin_why1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;
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
	where t2a.dtype = 'enty_UsrNodeFinTxact'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrItemFinWhy'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;


--fin_txact_itms1__id_cnt_calc
raise notice 'Updating fin_txact_itms1__id_cnt_calc';
with cte1 as(
select t.parent1__id id
,count(t.parent1__id) id_cnt
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxactItm'
and t.deleted_by is null
group by parent1__id 
)

update ampata_usr_node t
set fin_txact_itms1__id_cnt_calc = ct.id_cnt
from cte1 ct
where t.id = ct.id
and t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;


--fin_txact_itms1__amt_debt_sum_calc
--fin_txact_itms1__amt_cred_sum_calc
--fin_txact_itms1__amt_eq_calc
raise notice 'Updating fin_txact_itms1__amt_debt_sum_calc, fin_txact_itms1__amt_cred_sum_calc, fin_txact_itms1__amt_eq_calc';
with cte1 as(
select t.parent1__id id
,sum(t.amt_debt) amt_debt_sum_calc
,sum(t.amt_cred) amt_cred_sum_calc
,case when sum(t.amt_debt) = sum(t.amt_cred) then true else false end amt_eq_calc
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxactItm'
and t.deleted_by is null
group by parent1__id 
)

update ampata_usr_node t
set  fin_txact_itms1__amt_debt_sum_calc = ct.amt_debt_sum_calc
, fin_txact_itms1__amt_cred_sum_calc = ct.amt_cred_sum_calc
, fin_txact_itms1__amt_eq_calc = ct.amt_eq_calc
from cte1 ct
where t.id = ct.id
and t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;




--desc1
raise notice 'Updating desc1';
update ampata_usr_node t
set  desc1 = array_to_string(array[t1.desc1_type1__id2, t1.desc1_gen_chan1__id2, t1.desc1_fin_how1__id2, t1.desc1_fin_what1, t1.desc1_fin_why1], ' ')
from (
	select t2a.id
	,t2a.id2
	,t2a.type1__id2
	,case when t2a.type1__id2 is not null then '' || t2a.type1__id2 || '' else null end desc1_type1__id2
	,t2a.gen_chan1__id2
	,case when t2a.gen_chan1__id2 is not null then 'in chan [' || t2a.gen_chan1__id2 || ']' else null end desc1_gen_chan1__id2
	,t2a.fin_how1__id2
	,case when t2a.fin_how1__id2 is not null then 'via [' || t2a.fin_how1__id2 || ']' else null end desc1_fin_how1__id2
	,t2a.what_text1
	,t2a.fin_what1__id2
	,case when coalesce(t2a.what_text1,'') = '' and coalesce(t2a.fin_what1__id2,'') = '' then null else 'for ' || trim(coalesce(t2a.what_text1,'') || coalesce(t2a.fin_what1__id2,'')) || ''  end desc1_fin_what1
	,t2a.why_text1
	,t2a.fin_why1__id2
	,case when coalesce(t2a.why_text1,'') = '' and coalesce(t2a.fin_why1__id2,'') = '' then null else 'for ' || trim(coalesce(t2a.why_text1,'') || coalesce(t2a.fin_why1__id2,'')) || ''  end desc1_fin_why1
	from ampata_usr_node t2a
	where t2a.dtype = 'enty_UsrNodeFinTxact'
	and t2a.deleted_by is null
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
;



/*
select count(*)
from ampata_usr_node t
where t.dtype ='enty_UsrNodeFinTxact'
*/

end
$BODY$
;

call UsrNodeFinTxact_Pr_Upd_AllCalcVals_ForAllRows();

