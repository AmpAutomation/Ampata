
drop procedure if exists Usr_Fin_Txact_Set_Pr_Upd;

create procedure Usr_Fin_Txact_Set_Pr_Upd()
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
where t.class_name = 'FinTxactSet'
;

--id_dt_date1
raise notice 'Updating id_dt_date1';
update ampata_usr_node t
set id_dt_date1  = beg1_date1
where class_name = 'FinTxactSet'
;	

--id2_calc, id_dt_date1..
raise notice 'Updating id2_calc, id_dt_date1..';
update ampata_usr_node t
set id2_calc = Usr_Fin_Txset_Fn_get_Id2_Calc(
		 t.id_dt_date1 
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

	where
	t.class_name = 'FinTxactSet'
;

--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case 
				when id2 = id2_calc then false 
				else true
				end
where t.class_name = 'FinTxactSet'
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
			where t.class_name = 'FinTxactSet'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'FinTxactSet'
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
where t.class_name = 'FinTxactSet'
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
	where t2a.class_name = 'FinTxactSet'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinTxactSet'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactSet'
and t.deleted_by is null
;




--gen_chan1__id2
raise notice 'Updating type1__id2';
update ampata_usr_node t
set  gen_chan1__id2 = null
where t.class_name = 'FinTxactSet'
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
	where t2a.class_name = 'FinTxactSet'
	and t2a.deleted_by is null
	and t2b.class_name = 'GenChan'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactSet'
and t.deleted_by is null
;



--fin_how1__id2
raise notice 'Updating fin_how1__id2';
update ampata_usr_node t
set  fin_how1__id2 = null
where t.class_name = 'FinTxactSet'
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
	where t2a.class_name = 'FinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrFinHow'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactSet'
and t.deleted_by is null
;



--fin_what1__id2
raise notice 'Updating fin_what1__id2';
update ampata_usr_node t
set  fin_what1__id2 = null
where t.class_name = 'FinTxactSet'
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
	where t2a.class_name = 'FinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrFinWhat'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactSet'
and t.deleted_by is null
;



--fin_why1__id2
raise notice 'Updating fin_why1__id2';
update ampata_usr_node t
set  fin_why1__id2 = null
where t.class_name = 'FinTxactSet'
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
	where t2a.class_name = 'FinTxactSet'
	and t2a.deleted_by is null
	and t2b.dtype = 'enty_UsrFinWhy'
	
) t1
where t.id = t1.id 
and	t.class_name = 'FinTxactSet'
and t.deleted_by is null
;

--desc1
raise notice 'Updating desc1';
/*
select distinct
 txg_id,count(txg_id) cnt
from (
*/
with ct1 as (
select distinct
 txg_id id
,case when txg_desc1_fin_txact_itm1__id is not null then txg_desc1_fin_txact_itm1__id else txi_id_first end desc1_fin_txact_itm1__id
from (
select 
	 txg.id txg_id
	,txg.id2 txg_id2
	,tx.id tx_id
	,tx.id2 tx_id2
	,txg.desc1_fin_txact_itm1__id txg_desc1_fin_txact_itm1__id
	, first_value(txi.id) over (partition by txg.id order by txi.id_dt_date1, coalesce(txi.id_x,0), coalesce(txi.id_y,0), coalesce(txi.id_z,0)) txi_id_first
	,txi.id txi_id
	,txi.id2 txi_id2
	,txi.id_x txi_id_x
	,txi.id_y txi_id_y
	,txi.id_z txi_id_z
from ampata_usr_node txi
inner join ampata_usr_node tx
	on txi.fin_txact1__id = tx.id
inner join ampata_usr_node txg
	on tx.fin_txact_set1__id = txg.id
where txi.class_name = 'FinTxactItm'
	and txi.deleted_by is null
	and tx.class_name = 'FinTxact'
	and txg.class_name = 'FinTxactSet'
order by txi.id_dt_date1, coalesce(txi.id_x,0), coalesce(txi.id_y,0), coalesce(txi.id_z,0)
) t
)
, ct2 as (
select distinct
 txg_id id
,case when txg_desc1_fin_txact_itm2__id is not null then txg_desc1_fin_txact_itm2__id else txi_id_first end desc1_fin_txact_itm2__id
from (
select 
	 txg.id txg_id
	,txg.id2 txg_id2
	,tx.id tx_id
	,tx.id2 tx_id2
	,txg.desc1_fin_txact_itm2__id txg_desc1_fin_txact_itm2__id
	, first_value(txi.id) over (partition by txg.id order by txi.id_dt_date1, coalesce(txi.id_x,0), coalesce(txi.id_y,0), coalesce(txi.id_z,0)) txi_id_first
	,txi.id txi_id
	,txi.id2 txi_id2
	,txi.id_x txi_id_x
	,txi.id_y txi_id_y
	,txi.id_z txi_id_z
from ampata_usr_node txi
inner join ampata_usr_node tx
	on txi.fin_txact1__id = tx.id
inner join ampata_usr_node txg
	on tx.fin_txact_set1__id = txg.id
where txi.class_name = 'FinTxactItm'
	and txi.deleted_by is null
	and tx.class_name = 'FinTxact'
	and txg.class_name = 'FinTxactSet'
	and txi.id2 like txg.id2 || '/Y01/%'
	and txg.type1__id2 like '%Exch%'
order by txi.id_dt_date1, coalesce(txi.id_x,0), coalesce(txi.id_y,0), coalesce(txi.id_z,0)
) t
)
, ct3 as (
select
	 ct1.id
	,ct1.desc1_fin_txact_itm1__id
	,ct2.desc1_fin_txact_itm2__id
from ct1 ct1
left join ct2 ct2
	on ct1.id = ct2.id

)

/*
) t
group by t.txg_id
having count(t.txg_id) > 1
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
			|| coalesce (case when t2b.sys_fin_curcy1__id2 is not null then ' ' || t2b.sys_fin_curcy1__id2 else null end, '') 
		end 

		|| case when t2a.type1__id2 like '%Exch%'
			then	' ->'
				||	coalesce (case when t2c.amt_debt is not null then ' ' ||  t2c.amt_debt || '' else null end, '')
				||	coalesce (case when t2c.amt_cred is not null then ' ' ||  t2c.amt_cred || '' else null end, '')
				||	coalesce (case when t2c.sys_fin_curcy1__id2 is not null then ' ' || t2c.sys_fin_curcy1__id2 else null end, '')
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
	on ct3.desc1_fin_txact_itm1__id = t2b.id
	left join ampata_usr_node t2c
	on ct3.desc1_fin_txact_itm2__id = t2c.id
	
	where t2a.class_name = 'FinTxactSet'
	and t2a.deleted_by is null
	and t2b.class_name = 'FinTxactItm'
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
and	t.class_name = 'FinTxactSet'
and t.deleted_by is null
;




/*
select count(*)
from ampata_usr_node t
where t.class_name ='FinTxactSet'
*/

end
$BODY$
;

call Usr_Fin_Txact_Set_Pr_Upd();

