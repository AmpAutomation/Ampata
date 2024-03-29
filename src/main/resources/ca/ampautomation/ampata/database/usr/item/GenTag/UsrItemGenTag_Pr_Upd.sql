


drop procedure if exists UsrItemGenTag_Pr_Upd;

create procedure UsrItemGenTag_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	num_rows_updated_in_iter int;
	num_rows_updated int;

begin
-- Stored procedures are atomic and are executed as a transaction
	

--type1__id2
--use left join to set unmatched type1__id2 to null
raise notice 'Updating type1__id2';
update ampata_usr_item t
set  type1__id2 = t1.type1__id2
from (
	select t2a.id
	,t2a.id2
	,t2b.id2 as type1__id2
	from ampata_usr_item t2a
	left join ampata_usr_item_type as t2b
		on t2a.type1__id = t2b.id
	where t2a.dtype = 'enty_UsrItemGenTagType'
	and t2a.deleted_by is null
	
) t1
where t.id = t1.id 
;

--id2_calc
update ampata_usr_item t
set id2_calc = case when coalesce(t.type1__id2) = '' then '' else '' || t.type1__id2 || '/' end  || t.name1
FROM ampata_usr_item t1
where
	t.dtype = 'enty_UsrItemGenTag'
and	t.type1__id = t1.id
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_item t
set	id2_cmp = 	case 
				when id2 = id2_calc then false 
				else true
				end
where t.dtype = 'enty_UsrItemGenTag'
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
		update ampata_usr_item t
		set	id2_dup = t1.id2_dup
		from (
			select t.id2, count(*) id2_dup
			from ampata_usr_item t
			where t.dtype = 'enty_UsrItemGenTag'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.dtype = 'enty_UsrItemGenTag'
	    returning 1
	)
	select count(*) from rows into num_rows_updated_in_iter
	;
	num_rows_updated = num_rows_updated + num_rows_updated_in_iter;

	raise notice '--- num_rows_updated_in_iter:%', num_rows_updated_in_iter;
	raise notice '<-- Iteration';

end loop;
raise notice 'num_rows_updated:%', num_rows_updated;





--desc1
raise notice 'Updating desc1';
update ampata_usr_item t
set  desc1 = t1.desc1
from (
	select t2a.id
	,t2a.id2
	,t2a.name1
	,t2a.name2
	,t2a.type1__id
	,coalesce (name2 ,coalesce (name1,'')) as desc1
	from ampata_usr_item t2a
	where t2a.dtype = 'enty_UsrItemGenTag'
	and t2a.deleted_by is null
) t1
where t.id = t1.id 
and	t.dtype = 'enty_UsrItemGenTag'
and t.deleted_by is null
;



end
$BODY$
;

call UsrItemGenTag_Pr_Upd();

