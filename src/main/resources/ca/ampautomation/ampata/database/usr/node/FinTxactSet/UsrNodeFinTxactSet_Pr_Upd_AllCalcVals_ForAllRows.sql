
drop procedure if exists Usr_Fin_Txact_Set_Pr_Upd;

create procedure UsrNodeFinTxactSet_Pr_Upd_AllCalcVals_ForAllRows()
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
	t.dtype = 'enty_UsrNodeFinTxactSet'
;

--id2_calc
raise notice 'Updating id2_calc';
update ampata_usr_node t
set	 id2_calc = UsrNodeFinTxactSet_Fn_getId2Calc(
		t.ts1_el_ts
		,t.sort_idx
		)
where t.dtype = 'enty_UsrNodeFinTxactSet'
;

--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case when id2 = id2_calc then false 
				else true
				end
where t.dtype = 'enty_UsrNodeFinTxactSet'
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
			where t.dtype = 'enty_UsrNodeFinTxactSet'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.dtype = 'enty_UsrNodeFinTxactSet'
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
set  type1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxactSet'
and t.deleted_by is null
;
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
	where t2a.dtype = 'enty_UsrNodeFinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrNodeFinTxactSetType'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxactSet'
and t.deleted_by is null
;




--gen_chan1__id2
raise notice 'Updating type1__id2';
update ampata_usr_node t
set  gen_chan1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxactSet'
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
	where t2a.dtype = 'enty_UsrNodeFinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrNodeGenChan'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxactSet'
and t.deleted_by is null
;



--fin_how1__id2
raise notice 'Updating fin_how1__id2';
update ampata_usr_node t
set  fin_how1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxactSet'
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
	where t2a.dtype = 'enty_UsrNodeFinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrItemFinHow'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxactSet'
and t.deleted_by is null
;



--fin_what1__id2
raise notice 'Updating fin_what1__id2';
update ampata_usr_node t
set  fin_what1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxactSet'
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
	where t2a.dtype = 'enty_UsrNodeFinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrItemFinWhat'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxactSet'
and t.deleted_by is null
;



--fin_why1__id2
raise notice 'Updating fin_why1__id2';
update ampata_usr_node t
set  fin_why1__id2 = null
where t.dtype = 'enty_UsrNodeFinTxactSet'
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
	where t2a.dtype = 'enty_UsrNodeFinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrItemFinWhy'
	
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxactSet'
and t.deleted_by is null
;

--desc1
raise notice 'Updating desc1';
/*
select distinct
 ftxs_id,count(ftxs_id) cnt
from (
*/
with ct1 as (
select distinct
 ftxs_id id
,case when ftxs_desc1_node1__id is not null then ftxs_desc1_node1__id else ftxi_id_first end desc1_node1__id
from (
select 
	 ftxs.id ftxs_id
	,ftxs.id2 ftxs_id2
	,ftx.id ftx_id
	,ftx.id2 ftx_id2
	,ftxs.desc1_node1__id ftxs_desc1_node1__id
	, first_value(ftxi.id) over (partition by ftxs.id order by ftxi.ts1_el_ts, coalesce(ftxi.int1,0), coalesce(ftxi.int2,0), coalesce(ftxi.sort_idx,0)) ftxi_id_first
	,ftxi.id ftxi_id
	,ftxi.id2 ftxi_id2
	,ftxi.int1 ftxi_int1
	,ftxi.int2 ftxi_int2
	,ftxi.sort_idx  ftxi_sort_idx
from ampata_usr_node ftxi
inner join ampata_usr_node ftx
	on ftxi.fin_txact1__id = ftx.id
inner join ampata_usr_node ftxs
	on ftx.fin_txact_set1__id = ftxs.id
where ftxi.dtype = 'enty_UsrNodeFinTxactItm'
	and ftxi.deleted_by is null
	and ftx.dtype = 'enty_UsrNodeFinTxact'
	and ftxs.dtype = 'enty_UsrNodeFinTxactSet'
	and ftxi.ts1_el_ts = ftxs.ts1_el_ts
	and ftxi.int1 = ftxs.sort_idx
	and (ftxi.int2 = 0 or ftxi.int2 is null)
	and (ftxi.sort_idx = 0 or ftxi.sort_idx is null)
order by ftxi.ts1_el_ts, coalesce(ftxi.int1,0), coalesce(ftxi.int2,0), coalesce(ftxi.sort_idx,0)
) t
)
, ct2 as (
select distinct
 ftxs_id id
,case when ftxs_desc1_node2__id is not null then ftxs_desc1_node2__id else ftxi_id_first end desc1_node2__id
from (
select 
	 ftxs.id ftxs_id
	,ftxs.id2 ftxs_id2
	,ftx.id ftx_id
	,ftx.id2 ftx_id2
	,ftxs.desc1_node2__id ftxs_desc1_node2__id
	, first_value(ftxi.id) over (partition by ftxs.id order by ftxi.ts1_el_ts, coalesce(ftxi.int1,0), coalesce(ftxi.int2,0), coalesce(ftxi.sort_idx,0)) ftxi_id_first
	,ftxi.id ftxi_id
	,ftxi.id2 ftxi_id2
	,ftxi.int1 ftxi_int1
	,ftxi.int2 ftxi_int2
	,ftxi.sort_idx  ftxi_sort_idx
from ampata_usr_node ftxi
inner join ampata_usr_node ftx
	on ftxi.fin_txact1__id = ftx.id
inner join ampata_usr_node ftxs
	on ftx.fin_txact_set1__id = ftxs.id
where ftxi.dtype = 'enty_UsrNodeFinTxactItm'
	and ftxi.deleted_by is null
	and ftx.dtype = 'enty_UsrNodeFinTxact'
	and ftxs.dtype = 'enty_UsrNodeFinTxactSet'
--	and ftxi.id2 like ftxs.id2 || '/Y01/%'
	and ftxi.ts1_el_ts = ftxs.ts1_el_ts
	and ftxi.int1 = ftxs.sort_idx
	and ftxi.int2 = 1
	and (ftxi.sort_idx = 0 or ftxi.sort_idx is null)
	and ftxs.type1__id2 like '%Exch%'
order by ftxi.ts1_el_ts, coalesce(ftxi.int1,0), coalesce(ftxi.int2,0), coalesce(ftxi.sort_idx,0)
) t
)
, ct3 as (
select
	 ct1.id
	,ct1.desc1_node1__id
	,ct2.desc1_node2__id
from ct1 ct1
left join ct2 ct2
	on ct1.id = ct2.id

)

/*
) t
group by t.ftxs_id
having count(t.ftxs_id) > 1
*/


, ct4 as (
select 
 id
,id2
,trim(array_to_string(array[coalesce(desc1_type1__id2,''), desc1_amt, desc1_gen_chan1__id2, desc1_fin_what1, desc1_fin_why1, desc1_fin_how1__id2], ' ')) desc1
,type1__id2
,desc1_amt
,desc1_gen_chan1__id2
,desc1_fin_how1__id2
,desc1_fin_what1
,desc1_fin_why1
from (

	select t2a.id
	,t2a.id2
	,t2a.type1__id2
	,case when t2a.type1__id2 is not null then '' || t2a.type1__id2 || '' else null end desc1_type1__id2
	,t2b.amt_debt
	,t2b.amt_cred
	,trim(
	 case when t2b.amt_debt is null and t2b.amt_cred is null
		then null
		else   coalesce (case when t2b.amt_debt is not null then ' ' ||  t2b.amt_debt || '' else null end, '')
			|| coalesce (case when t2b.amt_cred is not null then ' ' ||  t2b.amt_cred || '' else null end, '')
			|| coalesce (case when t2b.sys_node_fin_curcy1__id2 is not null then ' ' || t2b.sys_node_fin_curcy1__id2 else null end, '') 
		end 

		|| case when t2a.type1__id2 like '%Exch%'
			then	' ->'
				||	coalesce (case when t2c.amt_debt is not null then ' ' ||  t2c.amt_debt || '' else null end, '')
				||	coalesce (case when t2c.amt_cred is not null then ' ' ||  t2c.amt_cred || '' else null end, '')
				||	coalesce (case when t2c.sys_node_fin_curcy1__id2 is not null then ' ' || t2c.sys_node_fin_curcy1__id2 else null end, '')
			else ''
			end
		)
		desc1_amt

	
	,t2a.gen_chan1__id2
	,case when t2a.gen_chan1__id2 is not null then 'in chan [' || t2a.gen_chan1__id2 || ']' else null end desc1_gen_chan1__id2
	,t2a.fin_how1__id2
	,case when t2a.fin_how1__id2 is not null then 'via [' || t2a.fin_how1__id2 || ']' else null end desc1_fin_how1__id2
	,t2a.what_text1
	,t2a.fin_what1__id2
	,case when coalesce(t2a.what_text1,'') = '' and coalesce(t2a.fin_what1__id2,'') = '' then null else 'for ' || trim(coalesce(t2a.what_text1,'') || ' ' || coalesce(t2a.fin_what1__id2,'')) || '' end desc1_fin_what1
	,t2a.why_text1
	,t2a.fin_why1__id2
	,case when coalesce(t2a.why_text1,'') = '' and coalesce(t2a.fin_why1__id2,'') = '' then null else 'for ' || trim(coalesce(t2a.why_text1,'') || ' ' || coalesce(t2a.fin_why1__id2,'')) || '' end desc1_fin_why1
	from ampata_usr_node t2a
	inner join ct3 ct3
	on t2a.id = ct3.id
	inner join ampata_usr_node t2b
	on ct3.desc1_node1__id = t2b.id
	left join ampata_usr_node t2c
	on ct3.desc1_node2__id = t2c.id
	
	where t2a.dtype = 'enty_UsrNodeFinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrNodeFinTxactItm'
) t
)

/*
select * from ct4 t
where t.type1__id2 = '/Txfer;Exch(Fee=Curcy1)'
*/

update ampata_usr_node t
set  desc1 = t1.desc1
from ct4 t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrNodeFinTxactSet'
and t.deleted_by is null
;




/*
select count(*)
from ampata_usr_node t
where t.dtype ='enty_UsrNodeFinTxactSet'
*/

end
$BODY$
;

call UsrNodeFinTxactSet_Pr_Upd_AllCalcVals_ForAllRows();

