


drop procedure if exists Sys_Fin_Curcy_Pr_Upd;

create procedure Sys_Fin_Curcy_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction


--update id2_calc, sort_key
raise notice 'Updating id2_calc, sort_key';
update ampata_sys_node t
set id2_calc = t.name1
	,sort_key = '_' || right('0' || t.sort_idx,2)
where t.dtype = 'enty_SysNodeFinCurcy'
and t.deleted_by is null
;

--update id_ts_date1.., ts1_el_dt..
raise notice 'Updating ts1_el_dt..';
update ampata_sys_node t
/*
set ts1_el_dt = ts1_s1::date
	,ts1_el_dt_yr = date_part('year',ts1_el_ts)
	,ts1_el_dt_qtr = date_part('quarter',ts1_el_ts)
	,ts1_el_dt_mon =  date_part('Mon',ts1_el_ts)
	,ts1_el_dt_mon2 =  to_char(ts1_el_ts,'Mon')
	,ts1_el_dt_day = date_part('day',ts1_el_ts)
*/

set ts1_el_dt_yr = date_part('year',ts1_el_dt)
	,ts1_el_dt_qtr = date_part('quarter',ts1_el_dt)
	,ts1_el_dt_mon =  date_part('Mon',ts1_el_dt)
	,ts1_el_dt_mon2 =  to_char(ts1_el_dt,'Mon')
	,ts1_el_dt_day = date_part('day',ts1_el_dt)

where t.dtype = 'enty_SysNodeFinCurcy'
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_sys_node t
set	id2_cmp = 	case 
				when id2 = id2_calc then false 
				else true
				end
where t.dtype = 'enty_SysNodeFinCurcy'
;


--id2_dup
raise notice 'Updating id2_dup';

-- Get count from UPDATE
with rows as (
	update ampata_sys_node t
	set	id2_dup = t1.id2_dup
	from (
		select t.id2, count(*) id2_dup
		from ampata_sys_node t
		where t.dtype = 'enty_SysNodeFinCurcy'
		and t.deleted_by is null
		group by t.id2 
	) t1
	where t.id2 = t1.id2 
	and	t.dtype = 'enty_SysNodeFinCurcy'
    returning 1
)
select count(*) from rows into num_rows_updated
;
raise notice 'num_rows_updated:%', num_rows_updated;


--type1__id2
update ampata_sys_node t
set	type1__id2 = t2.id2
from ampata_sys_node_type t2
where t.dtype = 'enty_SysNodeFinCurcy'
and t.type1__id = t2.id
;


/*
select count(*)
from ampata_sys_node t
where t.class_name ='FinCurcy'
*/

end
$BODY$;


call Sys_Fin_Curcy_Pr_Upd();




