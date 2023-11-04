




-- Update id2
update ampata_usr_node t
	set id2 = t.id2_calc
where t.dtype ='enty_UsrNodeFinTxact'
and t.id2_calc is not null
and t.id2_calc <> ''


-- Update fin_txset1_id2
update ampata_usr_node t
	set fin_txset1__id = t1.id 
from ampata_usr_node t1
where t.fin_txset1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxact'
and t1.dtype ='enty_UsrNodeFinTxactSet'


-- Update type1__id
update ampata_usr_node t
set type1__id = t1.id
from ampata_usr_node_type t1
where t.dtype = 'enty_UsrNodeFinTxact'
and	t.deleted_by is null
and t.type1__id is null
and t1.dtype ='enty_UsrNodeFinTxact'
and t1.id2 ='/'
;


--check for txact desc that are not '/''
select 
t.id 
,t.id2
,t.type1__id2
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxact'
and t.deleted_by is null
and t.type1__id2 <> '/'
order by t.id2 


-- Update type1__id
update ampata_usr_node t
set type1__id2 = '/'
where t.dtype = 'enty_UsrNodeFinTxact'
and t.type1__id2 = ''


/*
select t.type1__id2, count(t.type1__id2)
from ampata_usr_node t
WHERE t.dtype = 'enty_UsrNodeFinTxact'
group by t.type1__id2

*/


-- Update fin_txset1_id2
update ampata_usr_node t
	set fin_how1__id  = t1.id 
from ampata_sys_entity  t1
where t.fin_how1__id2 = t1.id2
and t1.dtype = 'enty_UsrItemFinHow'
and t.dtype ='enty_UsrNodeFinTxact'


update ampata_usr_node t
	set fin_what1__id  = t1.id 
from ampata_sys_entity t1
where t.fin_what1__id2 = t1.id2
and t1.dtype = 'enty_UsrItemFinWhat'
and t.dtype ='enty_UsrNodeFinTxact'

update ampata_usr_node t
	set fin_why1__id  = t1.id 
from ampata_sys_entity t1
where t.fin_why1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxact'
and t1.dtype = 'enty_UsrItemFinWhy'

/*
select t.fin_why1__id, t1.id 
from ampata_usr_node t
, ampata_sys_entity t1
where t.fin_why1__id2 = t1.id2
and t1.dtype = 'enty_UsrItemFinWhy'
and t.dtype ='enty_UsrNodeFinTxact'
*/


--ts1_el_ts
select 
 id2
,TO_DATE(SUBSTRING(id2,3,8),'YYYYMMDD')::date
from ampata_usr_node t
where t.dtype ='enty_UsrNodeFinTxact'
and deleted_by is null
and ts1_el_ts is null

update ampata_usr_node t
set ts1_el_ts = TO_DATE(SUBSTRING(id2,3,8),'YYYYMMDD')::date
where t.dtype ='enty_UsrNodeFinTxact'
and deleted_by is null
and ts1_el_ts is null





