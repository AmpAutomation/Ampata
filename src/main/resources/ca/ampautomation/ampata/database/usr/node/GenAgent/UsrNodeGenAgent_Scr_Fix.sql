


	
-- Clean up
/*
update ampata_usr_node
set gen_agent1__id2 = 'P-Mark Sluser'
where class_name ='GenAgent'
and gen_agent1__id2 ='Mark'

select gen_agent1__id2
from ampata_usr_node
where class_name ='GenAgent'
and gen_agent1__id2 LIKE'%Mark%'

*/

/*
update ampata_usr_node
set type1__id2 = '/GenAgent/O'
where class_name ='GenAgent'
and type1__id2 ='\GenAgent\O'

update ampata_usr_node
set type1__id2 = '/GenAgent/P'
where class_name ='GenAgent'
and type1__id2 ='\GenAgent\P'

update ampata_usr_node
set type1__id2 = '/GenAgent/C'
where class_name ='GenAgent'
and type1__id2 ='\GenAgent\C'


select type1__id2
from ampata_usr_node
where class_name ='GenAgent'
and type1__id2 LIKE'%O%'

*/

-- Examine
/*

select distinct t1.id2
from ampata_usr_node as t
, ampata_usr_node as t1
where t.gen_agent1__id2 = t1.id2 
and t.class_name ='FinAcct'
and t1.class_name ='GenAgent'
*/


-- Examine
/*
select set_config('client_min_messages','debug5',false)
select set_config('client_min_messages','info',false)

select count(*)
from ampata_usr_node as t
where 
    t.class_name ='GenAgent'
*/


-- Update gen_agent1__id
update ampata_usr_node as t
set gen_agent1__id = t1.id
from ampata_usr_node as t1
where t.gen_agent1__id2 = t1.id2 
and t.class_name ='GenAgent'
and t1.class_name ='GenAgent'
;

-- Update gen_agent2__id
update ampata_usr_node as t
set gen_agent2__id = t1.id
from ampata_usr_node as t1
where t.gen_agent2__id2 = t1.id2 
and t.class_name ='GenAgent'
and t1.class_name ='GenAgent'
;

-- Update type1__id
update ampata_usr_node as t
set type1__id = t1.id
from ampata_usr_node_type as t1
where t.type1__id2 = t1.id2 
and t.class_name ='GenAgent'
and t1.class_name ='GenAgent'
;


update ampata_usr_node t
set dtype = 'enty_' || t.class_name
where t.class_name = 'UsrNodeGenAgent'

SELECT t.id
,t.id2
,t.dtype
,t.class_name
from ampata_usr_node t
where t.class_name = 'UsrNodeGenAgent'

update ampata_usr_node_type t
set dtype = 'enty_UsrNodeBaseType'

update ampata_usr_node t
set dtype = 'enty_UsrNodeBase'

update ampata_usr_node t
set dtype = 'enty_' || t.class_name 
where t.class_name in( 'UsrNodeGenAgent', 'UsrItemGenTag', 'UsrNodeGenFile')



update ampata_usr_node_type t
set dtype = 'enty_' || t.class_name || 'Type'
where t.class_name = 'UsrNodeGenAgent'


SELECT t.id
,t.id2
,t.dtype
,t.class_name
from ampata_usr_node_type t
where t.class_name = 'UsrNodeGenAgent'

SELECT t.id
,t.id2
,t.dtype
,t.class_name
from ampata_usr_node t
where t.dtype = 'UsrNodeBase'




SELECT t.id
,t.tenant
,t.id2
,t.dtype
,t.class_name
,t.name1
from ampata_usr_node t
where t.class_name like 'Node'
order by t.id2 

SELECT t.dtype, count(t.dtype)
from ampata_usr_node t
group by t.dtype 


SELECT t.dtype, count(t.dtype)
from ampata_usr_item t
group by t.dtype 





