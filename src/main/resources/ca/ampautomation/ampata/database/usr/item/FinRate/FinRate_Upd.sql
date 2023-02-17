


drop procedure if exists Fin_Rate_Pr_Upd;

create procedure Fin_Rate_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction


--id2_calc, beg1_date1..
raise notice 'Updating id2_calc, beg1_date1..';
update ampata_sys_entity t
set id2_calc = Fin_Rate_Fn_get_Id2_Calc(
		 t1.id2
		,t2.id2
		,t.beg1_date1
		)
	,beg1_date1_yr 	= date_part('year',t.beg1_date1)
	,beg1_date1_qtr =  TRUNC((date_part('Mon',t.beg1_date1) - 1) / 3) + 1
	,beg1_date1_mon =  date_part('Mon',t.beg1_date1)
	,beg1_date1_mon2 =  to_char(t.beg1_date1,'Mon')
	,beg1_date1_day = date_part('day',t.beg1_date1)
from ampata_sys_node t1
	,ampata_sys_node t2
	where
	t.dtype = 'ampata_FinRate'
and t1.class_name = 'UsrFinCurcy'
and t2.class_name = 'UsrFinCurcy'
and t.fin_curcy1__id = t1.id
and t.fin_curcy2__id = t2.id
;

--fin_curcy1__id
raise notice 'Updating fin_curcy1__id';
update ampata_sys_entity t
set fin_curcy1__id2 = t2.id2
from ampata_sys_node t2
where t.dtype = 'ampata_FinRate'
and t2.class_name = 'UsrFinCurcy'
and t.fin_curcy1__id = t2.id
;

--fin_curcy2__id
raise notice 'Updating fin_curcy2__id';
update ampata_sys_entity t
set fin_curcy2__id2 = t2.id2
from ampata_sys_node t2
where t.dtype = 'ampata_FinRate'
and t2.class_name = 'UsrFinCurcy'
and t.fin_curcy2__id = t2.id
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_sys_entity t
set	id2_cmp = 	case 
				when id2 = id2_calc then false 
				else true
				end
where t.dtype = 'ampata_FinRate'
;




--id2_dup
raise notice 'Updating id2_dup';
num_rows_updated = 0;
/*
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
*/
-- Get count from UPDATE
	with rows as (
		update ampata_sys_entity t
		set	id2_dup = t1.id2_dup
		from (
			select t.id2, count(*) id2_dup
			from ampata_sys_entity t
			where t.dtype = 'ampata_FinRate'
			and t.deleted_by is null
--			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.dtype = 'ampata_FinRate'
	    returning 1
	)
	select count(*) from rows into num_rows_updated_in_iter
	;
	num_rows_updated = num_rows_updated + num_rows_updated_in_iter;

	raise notice '--- num_rows_updated_in_iter:%', num_rows_updated_in_iter;
	raise notice '<-- Iteration';
/*
end loop;
*/
raise notice 'num_rows_updated:%', num_rows_updated;



end
$BODY$
;

call Fin_Rate_Pr_Upd();

