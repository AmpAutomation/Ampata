


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
where t.class_name = 'FinCurcy'
and t.deleted_by is null
;

--update id_ts_date1.., beg1_date1..
raise notice 'Updating beg1_date1..';
update ampata_sys_node t
/*
set beg1_date1 = beg1_s1::date
	,beg1_date1_yr = date_part('year',beg1_ts1)
	,beg1_date1_qtr = date_part('quarter',beg1_ts1)
	,beg1_date1_mon =  date_part('Mon',beg1_ts1)
	,beg1_date1_mon2 =  to_char(beg1_ts1,'Mon')
	,beg1_date1_day = date_part('day',beg1_ts1)
*/

set beg1_date1_yr = date_part('year',beg1_date1)
	,beg1_date1_qtr = date_part('quarter',beg1_date1)
	,beg1_date1_mon =  date_part('Mon',beg1_date1)
	,beg1_date1_mon2 =  to_char(beg1_date1,'Mon')
	,beg1_date1_day = date_part('day',beg1_date1)

where t.class_name = 'FinCurcy'
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_sys_node t
set	id2_cmp = 	case 
				when id2 = id2_calc then false 
				else true
				end
where t.class_name = 'FinCurcy'
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
		where t.class_name = 'FinCurcy'
		and t.deleted_by is null
		group by t.id2 
	) t1
	where t.id2 = t1.id2 
	and	t.class_name = 'FinCurcy'
    returning 1
)
select count(*) from rows into num_rows_updated
;
raise notice 'num_rows_updated:%', num_rows_updated;


--type1__id2
update ampata_sys_node t
set	type1__id2 = t2.id2
from ampata_sys_node_type t2
where t.class_name = 'FinCurcy'
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




