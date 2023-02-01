


drop procedure if exists Sys_Curcy_Exch_Rate_Pr_Upd;

create procedure Sys_Curcy_Exch_Rate_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction


--id2_calc, beg1_date1..
raise notice 'Updating id2_calc, beg1_date1..';
update ampata_sys_node t
set id2_calc = Sys_Fin_Curcy_Exch_Rate_Fn_get_Id2_Calc(
		 t1.fin_curcy1__id2
		,t2.fin_curcy1__id2
		,t.beg1_date1
		)
/*
	,beg1_date1_yr 	= date_part('year',t.beg1_date1)
	,beg1_date1_qtr =  TRUNC((date_part('Mon',t.beg1_date1) - 1) / 3) + 1
	,beg1_date1_mon =  date_part('Mon',t.beg1_date1)
	,beg1_date1_mon2 =  to_char(t.beg1_date1,'Mon')
	,beg1_date1_day = date_part('day',t.beg1_date1)
*/

	,beg1_date1_yr = date_part('year',t.beg1_date1)
	,beg1_date1_qtr = date_part('quarter',t.beg1_date1)
	,beg1_date1_mon =  date_part('Mon',t.beg1_date1)
	,beg1_date1_mon2 =  to_char(t.beg1_date1,'Mon')
	,beg1_date1_day = date_part('day',t.beg1_date1)

from ampata_sys_node t1
, ampata_sys_node t2
where t.class_name = 'FinCurcyExchRate'
and t.fin_curcy1__id = t1.id 
and t.fin_curcy2__id = t2.id 

	;

--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_sys_node t
set	id2_cmp = 	case 
				when id2 = id2_calc then false 
				else true
				end
where t.class_name = 'FinCurcyExchRate'
;




--id2_dup
raise notice 'Updating id2_dup';
num_rows_updated = 0;
with rows as (
	update ampata_sys_node t
	set	id2_dup = t1.id2_dup
	from (
		select t.id2, count(*) id2_dup
		from ampata_sys_node t
		where t.deleted_by is null
--			and t.tenant = rec_tenant.tenant_id
		group by t.id2 
	) t1
	where t.id2 = t1.id2 
    returning 1
)
select count(*) from rows into num_rows_updated
;
raise notice 'num_rows_updated:%', num_rows_updated;


end
$BODY$
;

call Sys_Curcy_Exch_Rate_Pr_Upd();
