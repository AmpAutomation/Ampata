


drop procedure if exists Usr_Gen_Agent_Pr_Upd;

create procedure Usr_Gen_Agent_Pr_Upd()
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
update ampata_usr_node t
set  type1__id2 = t1.type1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.type1__id
	,t2b.id2 as type1__id2
	from ampata_usr_node t2a
	left join ampata_usr_node_type as t2b
		on t2a.type1__id = t2b.id
	where t2a.class_name = 'UsrGenAgent'
	and t2a.deleted_by is null
) t1
where t.id = t1.id 
;

--id2
raise notice 'Updating id2';
update ampata_usr_node t
set  id2 = t1.id2
from (
	select t2.id
	,t2.id2 id2_old
	, 	case when t2.type1__id2 = '/P' 
		then
			'P-' || coalesce(t2.name1,'')
		else
			case when type1__id2 = '/O' 
			then  
				'O-' || coalesce(t2.name1,'')
			else
				case when type1__id2 = '/C' 
				then 
					   'C-' 
					|| left(coalesce(t2.gen_agent1__name_frst,''),1)
					|| case when coalesce(t2.gen_agent1__name_last,'') = coalesce(t2.name1,'') then '' else '(' || coalesce(t2.gen_agent1__name_last,'') || ')' end
					|| '&'
					|| left(coalesce(t2.gen_agent2__name_frst,''),1)
					|| case when coalesce(t2.gen_agent2__name_last,'') = coalesce(t2.name1,'') then '' else '(' || coalesce(t2.gen_agent2__name_last,'') || ')' end
					|| ' ' || coalesce(name1,'')
				else t2.id2
				end 
			end 
		end 
		id2
	from(
		select t3a.id
		,t3a.id2
		,t3a.type1__id2
		,t3a.name1
		,t3b.name_frst as gen_agent1__name_frst
		,t3b.name_last as gen_agent1__name_last
		,t3c.name_frst as gen_agent2__name_frst
		,t3c.name_last as gen_agent2__name_last
		from ampata_usr_node as t3a
		left join ampata_usr_node as t3b
			on t3a.gen_agent1__id = t3b.id
		left join ampata_usr_node as t3c
			on t3a.gen_agent2__id = t3c.id
		where
		    t3a.class_name ='GenAgent'
--		and t3a.type1__id2 = '/C'
	) t2
--	where t2.type1__id2 = '/C'
) t1
where t.id = t1.id 
;
	
--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case 
				when id2 = id2_calc then false 
				else true
				end
where t.class_name = 'UsrGenAgent'
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
			where t.class_name = 'UsrGenAgent'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'UsrGenAgent'
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
update ampata_usr_node t
set  desc1 = t1.desc1
from (
	select t2a.id
	,t2a.id2
	,t2a.name1
	,t2a.name2
	,t2a.type1__id
	,coalesce (name2 ,coalesce (name1,'')) as desc1
	from ampata_usr_node t2a
	where t2a.class_name = 'UsrGenAgent'
	and t2a.deleted_by is null
) t1
where t.id = t1.id 
and	t.class_name = 'UsrGenAgent'
and t.deleted_by is null
;


end
$BODY$
;

call Usr_Gen_Agent_Pr_Upd();

