


drop procedure if exists Usr_Gen_Agent_Pr_Upd;

create procedure Usr_Gen_Agent_Pr_Upd()
language 'plpgsql'
as $BODY$
declare

begin

	
-- Stored procedures are atomic and are executed as a transaction
	
drop table if exists tt1;
--drop table if exists tt2;

CREATE TEMPORARY TABLE tt1 ON COMMIT DROP AS 
-- drop this temp table automatically after the transaction commits
-- and store procedure is executed an atomic transaction
--CREATE TEMPORARY TABLE tt1 as

--with cte1 as (

select 
	t.*
	,t1.id2 as type1_id2
	,t2.name_frst as gen_agent1__name_frst
	,t2.name_last as gen_agent1__name_last
from ampata_usr_node as t
inner join ampata_usr_node_type as t1 
	on t.type1__id = t1.id
left join ampata_usr_node as t2 
	on t.gen_agent1__id = t2.id
where 
    t.class_name ='GenAgent'
and t2.class_name ='GenAgent'
	
union

select 
	t.*
	,t1.id2 as type1_id2
	,null as gen_agent1__name_frst
	,null as gen_agent1__name_last
from ampata_usr_node as t
inner join ampata_usr_node_type as t1 
	on t.type1__id = t1.id
where
    t.class_name ='GenAgent'
and t.gen_agent1__id is null
--)
;

--select Count(*) from tt1;

drop table if exists tt2;

CREATE TEMPORARY TABLE tt2 ON COMMIT DROP AS 
-- drop this temp table automatically after the transaction commits
-- and store procedure is executed an atomic transaction
--CREATE TEMPORARY TABLE tt2 as

--, cte2 as (

select 
	t.*
	,t2.name_frst as gen_agent2__name_frst
	,t2.name_last as gen_agent2__name_last
from tt1 as t
left join ampata_usr_node as t2 
	on t.gen_agent2__id = t2.id
	
union

select 
	t.*
	,null as gen_agent2__name_frst
	,null as gen_agent2__name_last
from tt1 as t
where t.gen_agent2__id is null 

--)
;
/*
select count(*)
from cte2
*/

/*

select
	 t1.type1_id2
	,t.name1
	,t.abrv
	,t.name_frst
	,t.name_last
	,t1.gen_agent1__name_frst
	,t1.gen_agent1__name_last
	,t1.gen_agent2__name_frst
	,t1.gen_agent2__name_last

	,Usr_Gen_Agent_Fn_get_Id2_Calc(
		 t1.type1_id2
		,t.name1
		,t.abrv
		,t.name_frst
		,t.name_last
		,t1.gen_agent1__name_frst
		,t1.gen_agent1__name_last
		,t1.gen_agent2__name_frst
		,t1.gen_agent2__name_last
	 ) as Id2_Calc
from ampata_usr_node as t
inner join cte2 as t1 
	on t.id = t1.id
where
    t.class_name ='GenAgent'
 */

update ampata_usr_node t
set id2_calc = Usr_Gen_Agent_Fn_get_Id2_Calc(
		 t1.type1_id2
		,t.name1
		,t.abrv
		,t.name_frst
		,t.name_last
		,t1.gen_agent1__name_frst
		,t1.gen_agent1__name_last
		,t1.gen_agent2__name_frst
		,t1.gen_agent2__name_last
	 )
from tt2 t1 
where
	t.id = t1.id
and t.class_name ='GenAgent'
;

/*
select count(*)
from ampata_usr_node t
where
    t.class_name ='GenAgent'
*/


--select count(*) from tt2;


--COMMIT; -- drops the temp tables


end
$BODY$
;

call Usr_Gen_Agent_Pr_Upd();

