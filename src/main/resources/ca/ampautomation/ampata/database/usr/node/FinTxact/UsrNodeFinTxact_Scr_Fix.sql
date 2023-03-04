




-- Update id2
update ampata_sys_node t
	set id2 = t.id2_calc
where t.class_name ='FinTxact'
and t.id2_calc is not null
and t.id2_calc <> ''


-- Update fin_txset1_id2
update ampata_sys_node t
	set fin_txset1__id = t1.id 
from ampata_sys_node t1
where t.fin_txset1__id2 = t1.id2
and t.class_name ='FinTxact'
and t1.class_name ='FinTxactSet'


-- Update type1__id
update ampata_sys_node t
set type1__id = t1.id
from ampata_sys_node_type t1
where t.class_name = 'UsrNodeFinTxact'
and	t.deleted_by is null
and t.type1__id is null
and t1.class_name ='FinTxact'
and t1.id2 ='/'
;


--check for txact desc that are not '/''
select 
t.id 
,t.id2
,t.type1__id2
from ampata_sys_node t
where t.class_name = 'UsrNodeFinTxact'
and t.deleted_by is null
and t.type1__id2 <> '/'
order by t.id2 


-- Update type1__id
update ampata_sys_node t
set type1__id2 = '/'
where t.class_name = 'UsrFinTxact'
and t.type1__id2 = ''


/*
select t.type1__id2, count(t.type1__id2)
from ampata_sys_node t
WHERE t.class_name = 'UsrFinTxact'
group by t.type1__id2

*/


-- Update fin_txset1_id2
update ampata_sys_node t
	set fin_how1__id  = t1.id 
from ampata_sys_entity  t1
where t.fin_how1__id2 = t1.id2
and t1.dtype = 'enty_UsrItemFinHow'
and t.class_name ='FinTxact'


update ampata_sys_node t
	set fin_what1__id  = t1.id 
from ampata_sys_entity t1
where t.fin_what1__id2 = t1.id2
and t1.dtype = 'enty_UsrItemFinWhat'
and t.class_name ='FinTxact'

update ampata_sys_node t
	set fin_why1__id  = t1.id 
from ampata_sys_entity t1
where t.fin_why1__id2 = t1.id2
and t.class_name ='FinTxact'
and t1.dtype = 'enty_UsrItemFinWhy'

/*
select t.fin_why1__id, t1.id 
from ampata_sys_node t
, ampata_sys_entity t1
where t.fin_why1__id2 = t1.id2
and t1.dtype = 'enty_UsrItemFinWhy'
and t.class_name ='FinTxact'
*/


--ts1_el_ts
select 
 id2
,TO_DATE(SUBSTRING(id2,3,8),'YYYYMMDD')::date
from ampata_sys_node t
where t.class_name ='FinTxact'
and deleted_by is null
and ts1_el_ts is null

update ampata_sys_node t
set ts1_el_ts = TO_DATE(SUBSTRING(id2,3,8),'YYYYMMDD')::date
where t.class_name ='FinTxact'
and deleted_by is null
and ts1_el_ts is null





