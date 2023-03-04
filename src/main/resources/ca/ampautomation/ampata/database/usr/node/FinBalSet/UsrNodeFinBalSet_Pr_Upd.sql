
drop procedure if exists Usr_Fin_Bal_Set_Pr_Upd;

create procedure Usr_Fin_Bal_Set_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction

 --ts1_el_dt.., ts3_el_dt..
raise notice 'Updating ts1_el_dt.., ts3_el_dt..';
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

	,ts3_el_dt = t.ts3_el_ts::date
	,ts3_el_dt_yr = date_part('year',t.ts3_el_dt)
	,ts3_el_dt_qtr = date_part('quarter',t.ts3_el_ts)
	,ts3_el_dt_mon =  date_part('Mon',t.ts3_el_dt)
	,ts3_el_dt_mon2 =  to_char(t.ts3_el_dt,'Mon')
	,ts3_el_dt_day = date_part('day',t.ts3_el_dt)
	,ts3_el_tm  = t.ts3_el_ts::time
	,ts3_el_tm_hr  = date_part('hour',t.ts3_el_ts)
	,ts3_el_tm_min  = date_part('minute',t.ts3_el_ts)
where t.class_name = 'UsrNodeFinBalSet'
;

 --id_dt_date1
raise notice 'Updating id_dt_date1';
update ampata_usr_node t
set id_dt_date1 = ts3_el_dt
where class_name = 'UsrNodeFinBalSet'
;	



--fin_dept1__id2
raise notice 'Updating fin_dept1__id2';
update ampata_usr_node t
set fin_dept1__id2 = t1.id2
from ampata_usr_node t1
where t.fin_dept1__id = t1.id
and	t.class_name = 'UsrNodeFinBalSet'
and t.deleted_by is null
;	

--id2_calc
raise notice 'Updating id2_calc';
update ampata_usr_node t
set id2_calc = Usr_Fin_Bal_Set_Fn_get_Id2_Calc(
		 t.name1
		,t.fin_dept1__id2
		)
where	t.class_name = 'UsrNodeFinBalSet'
and t.deleted_by is null
;


--update sort_key
raise notice 'Updating sort_key';
update ampata_usr_node t
set sort_key = '_' || right('0' || t.sort_idx,2)
where	t.class_name = 'UsrNodeFinBalSet'
and t.deleted_by is null
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
			where t.class_name = 'UsrNodeFinBalSet'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'UsrNodeFinBalSet'
	    returning 1
	)
	select count(*) from rows into num_rows_updated_in_iter
	;
	num_rows_updated = num_rows_updated + num_rows_updated_in_iter;

	raise notice '--- num_rows_updated_in_iter:%', num_rows_updated_in_iter;
	raise notice '<-- Iteration';

end loop;
raise notice 'num_rows_updated:%', num_rows_updated;





/*
select count(*)
from ampata_usr_node t
where t.class_name ='FinBalSet'
*/

end
$BODY$
;

call Usr_Fin_Bal_Set_Pr_Upd();

