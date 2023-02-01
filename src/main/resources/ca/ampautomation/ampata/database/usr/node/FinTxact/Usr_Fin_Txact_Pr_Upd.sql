
drop procedure if exists Usr_Fin_Txact_Pr_Upd;

create procedure Usr_Fin_Txact_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction

--beg1_date1..
raise notice 'Updating beg1_date1..';
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
where
	t.class_name = 'FinTxact'
;
	
--id_dt_date1
raise notice 'Updating id_dt_date1';
update ampata_usr_node t
set id_dt_date1  = beg1_date1
where class_name = 'FinTxact'
;	

		
--id2_calc, id_dt_date1..
raise notice 'Updating id2_calc, id_dt_date1..';
update ampata_usr_node t
set  id2_calc = Usr_Fin_Txact_Fn_get_Id2_Calc(
		 t.id_dt_date1
		,t.id_x
		,t.id_y
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

where t.class_name = 'FinTxact'
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case when id2 = id2_calc then false 
				else true
				end
where t.class_name = 'FinTxact'
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
			where t.class_name = 'FinTxact'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'FinTxact'
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
	where t2a.class_name = 'FinTxact'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinTxact'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxact'
and t.deleted_by is null
;

--gen_chan1__id2
raise notice 'Updating gen_chan1__id2';
update ampata_usr_node t
set  gen_chan1__id2 = null
where t.class_name = 'FinTxact'
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
	where t2a.class_name = 'FinTxact'
	and t2a.deleted_by is null
	and t2b.class_name = 'GenChan'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxact'
and t.deleted_by is null
;


--fin_how1__id2
raise notice 'Updating fin_how1__id2';
update ampata_usr_node t
set  fin_how1__id2 = null
where t.class_name = 'FinTxact'
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
	where t2a.class_name = 'FinTxact'
	and t2a.deleted_by is null
	and t2b.dtype = 'ampata_FinHow'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxact'
and t.deleted_by is null
;



--fin_what1__id2
raise notice 'Updating fin_what1__id2';
update ampata_usr_node t
set  fin_what1__id2 = null
where t.class_name = 'FinTxact'
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
	where t2a.class_name = 'FinTxact'
	and t2a.deleted_by is null
	and t2b.dtype = 'ampata_FinWhat'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxact'
and t.deleted_by is null
;



--fin_why1__id2
raise notice 'Updating fin_why1__id2';
update ampata_usr_node t
set  fin_why1__id2 = null
where t.class_name = 'FinTxact'
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
	where t2a.class_name = 'FinTxact'
	and t2a.deleted_by is null
	and t2b.dtype = 'ampata_FinWhy'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxact'
and t.deleted_by is null
;


--fin_txset1__id2_trgt
raise notice 'Updating fin_txset1__id2_trgt';
update ampata_usr_node t
set	fin_txset1__id2_trgt = substring(id2_calc,1,16)
where t.class_name = 'FinTxact'
and deleted_by is null
;


--fin_txact_itms1__id_cnt_calc
raise notice 'Updating fin_txact_itms1__id_cnt_calc';
with cte1 as(
select t.fin_txact1__id id
,count(t.fin_txact1__id) id_cnt
from ampata_usr_node t
where t.class_name = 'FinTxactItm'
and t.deleted_by is null
group by fin_txact1__id 
)

update ampata_usr_node t
set fin_txact_itms1__id_cnt_calc = ct.id_cnt
from cte1 ct
where t.id = ct.id
and t.class_name = 'FinTxact'
and t.deleted_by is null
;


--fin_txact_itms1__amt_debt_sum_calc
--fin_txact_itms1__amt_cred_sum_calc
--fin_txact_itms1__amt_eq_calc
raise notice 'Updating fin_txact_itms1__amt_debt_sum_calc, fin_txact_itms1__amt_cred_sum_calc, fin_txact_itms1__amt_eq_calc';
with cte1 as(
select t.fin_txact1__id id
,sum(t.amt_debt) amt_debt_sum_calc
,sum(t.amt_cred) amt_cred_sum_calc
,case when sum(t.amt_debt) = sum(t.amt_cred) then true else false end amt_eq_calc
from ampata_usr_node t
where t.class_name = 'FinTxactItm'
and t.deleted_by is null
group by fin_txact1__id 
)

update ampata_usr_node t
set  fin_txact_itms1__amt_debt_sum_calc = ct.amt_debt_sum_calc
, fin_txact_itms1__amt_cred_sum_calc = ct.amt_cred_sum_calc
, fin_txact_itms1__amt_eq_calc = ct.amt_eq_calc
from cte1 ct
where t.id = ct.id
and t.class_name = 'FinTxact'
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
	where t2a.class_name = 'FinTxact'
	and t2a.deleted_by is null
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxact'
and t.deleted_by is null
;



/*
select count(*)
from ampata_usr_node t
where t.class_name ='FinTxact'
*/

end
$BODY$
;

call Usr_Fin_Txact_Pr_Upd();

