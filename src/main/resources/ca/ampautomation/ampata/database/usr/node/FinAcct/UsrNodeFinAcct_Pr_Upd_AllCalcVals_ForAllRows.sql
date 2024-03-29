


drop procedure if exists UsrNodeFinAcct_Pr_Upd_AllCalcVals_ForAllRows;

create procedure UsrNodeFinAcct_Pr_Upd_AllCalcVals_ForAllRows()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction

--update parent_id2
raise notice 'Updating parent_id2';
update ampata_usr_node t
set parent1__id2 = t2.id2
from ampata_usr_node t2
where t.dtype = 'enty_UsrNodeFinAcct'
and t.deleted_by is null
and t2.dtype = 'enty_UsrNodeFinAcct'
and t.parent1__id = t2.id
;	


--update id2_calc, sort_key
raise notice 'Updating id2_calc, sort_key';
with recursive cte1 as (
-- non-recursive term
select t.id
, t.id2
, '' || t.name1 as r_id2_calc
, t.parent1__id
, '_' || right('0' || t.sort_idx,2) as r_sort_key
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinAcct'
and t.deleted_by is null
and t.parent1__id is null
union
-- recursive term
select rt.id
, rt.id2
, ct.r_id2_calc || '/' || rt.name1 as r_id2_calc
, rt.parent1__id
,  ct.r_sort_key || '_' || right('0' || rt.sort_idx,2) as r_sort_key
from ampata_usr_node rt
inner join cte1 ct on ct.id = rt.parent1__id
where rt.dtype = 'enty_UsrNodeFinAcct'
and rt.deleted_by is null
)
update ampata_usr_node t
set id2_calc = ct.r_id2_calc
,sort_key = ct.r_sort_key
from cte1 ct
where t.id = ct.id
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case 
				when id2 = id2_calc then false 
				else true
				end
where t.dtype = 'enty_UsrNodeFinAcct'
;

update ampata_usr_node t
set	type1__id2 = t2.id2
from ampata_usr_node_type t2
where t.dtype = 'enty_UsrNodeFinAcct'
and t.type1__id = t2.id
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
			where t.dtype = 'enty_UsrNodeFinAcct'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.dtype = 'enty_UsrNodeFinAcct'
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
where t.dtype ='enty_UsrNodeFinAcct'
*/

end
$BODY$;


call UsrNodeFinAcct_Pr_Upd_AllCalcVals_ForAllRows();




