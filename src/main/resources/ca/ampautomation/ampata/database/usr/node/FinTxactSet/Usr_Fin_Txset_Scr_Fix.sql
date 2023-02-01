



-- Update id2
update ampata_sys_node t
	set id2 = t.id2_calc
where t.class_name ='FinTxset'
and t.id2_calc is not null
and t.id2_calc <> ''



-- Update type1__id
update ampata_sys_node t
set type1__id = t1.id
FROM ampata_sys_node_type t1
where t.type1__id2 = t1.id2 
and	t.class_name = 'FinTxset'
and t1.class_name ='FinTxset'
;


insert into ampata_sys_node (id2, id2_calc, class_name, type1__id, type1__id2)
SELECT t.id2 id2
	, t.id2 id2_calc
	, 'FinTxset' class_name 
	, '9b7b7338-4d5b-4efc-98e8-daadeb31d298'::uuid type1__id 
	, '/' type1__id2 
FROM
(
SELECT DISTINCT LEFT(t1.id2,20) id2
FROM ampata_sys_node t1
WHERE t1.class_name = 'FinTxact'
) t


SELECT 
	 t1.id
	,t1.id2
	,t1.gen_chan1__id 
	,t1.gen_chan1__id2
	,t1.fin_how1__id
	,t1.fin_how1__id2
	,t2.fin_how1__id
	,t2.fin_how1__id2
	,t1.fin_what1__id
	,t1.fin_what1__id2
	,t2.fin_what1__id
	,t2.fin_what1__id2
	,t1.fin_why1__id
	,t1.fin_why1__id2
	,t2.fin_why1__id
	,t2.fin_why1__id2	
	
FROM ampata_sys_node t1
, ampata_sys_node t2
WHERE t1.class_name = 'FinTxset'
and t1.id = t2.fin_txset1__id 
and t2.class_name = 'FinTxact'
and t1.fin_how1__id is null
and t2.fin_how1__id is not null 




update ampata_sys_node t
set type1__id  = 'c2d8e4fa-39f3-c21e-e9fd-fe5f397db836' 
-- /Exp
from ampata_sys_node t1
where t.id = t1.fin_txset1__id 
and t.class_name ='FinTxset'
and t1.class_name ='FinTxact'
and (t.type1__id is null
or t.type1__id = '9b7b7338-4d5b-4efc-98e8-daadeb31d298'
-- /
)
and t1.type1__id = 'aab58b28-11a1-4f74-9425-6e58f99cfb1f' 
-- /Exp
;

update ampata_sys_node t
set type1__id = '9acb4979-596b-48f0-9c1c-5aedc479691c'
from ampata_sys_node t1
where t.fin_txset1__id = t1.id
and t.class_name ='FinTxact'
and t1.class_name ='FinTxset'
and t.type1__id = 'aab58b28-11a1-4f74-9425-6e58f99cfb1f'
-- /Exp
and t1.type1__id = 'c2d8e4fa-39f3-c21e-e9fd-fe5f397db836' 
-- /Exp
;


update ampata_sys_node t
set gen_chan1__id = t1.gen_chan1__id 
from ampata_sys_node t1
where t.id = t1.fin_txset1__id 
and t.class_name ='FinTxset'
and t1.class_name ='FinTxact'
and t.gen_chan1__id is null
and t1.gen_chan1__id is not null 
;

update ampata_sys_node t
set gen_chan1__id = null
from ampata_sys_node t1
where t.fin_txset1__id = t1.id
and t.class_name ='FinTxact'
and t1.class_name ='FinTxset'
and t.gen_chan1__id = t1.gen_chan1__id
;


update ampata_sys_node t
set fin_how1__id = t1.fin_how1__id 
from ampata_sys_node t1
where t.id = t1.fin_txset1__id 
and t.class_name ='FinTxset'
and t1.class_name ='FinTxact'
and t.fin_how1__id is null
and t1.fin_how1__id is not null 
;

update ampata_sys_node t
set fin_how1__id = null
from ampata_sys_node t1
where t.fin_txset1__id = t1.id
and t.class_name ='FinTxact'
and t1.class_name ='FinTxset'
and t.fin_how1__id = t1.fin_how1__id
;


update ampata_sys_node t
set fin_what1__id = t1.fin_what1__id 
from ampata_sys_node t1
where t.id = t1.fin_txset1__id 
and t.class_name ='FinTxset'
and t1.class_name ='FinTxact'
and t.fin_what1__id is null
and t1.fin_what1__id is not null 
;

update ampata_sys_node t
set fin_what1__id = null
from ampata_sys_node t1
where t.fin_txset1__id = t1.id
and t.class_name ='FinTxact'
and t1.class_name ='FinTxset'
and t.fin_what1__id = t1.fin_what1__id
;

update ampata_sys_node t
set what_text1 = t1.what_text1 
from ampata_sys_node t1
where t.id = t1.fin_txset1__id 
and t.class_name ='FinTxset'
and t1.class_name ='FinTxact'
and t.what_text1 is null
and t1.what_text1 is not null 
;

update ampata_sys_node t
set what_text1 = null
from ampata_sys_node t1
where t.fin_txset1__id = t1.id
and t.class_name ='FinTxact'
and t1.class_name ='FinTxset'
and t.what_text1 = t1.what_text1
;




update ampata_sys_node t
set fin_why1__id = t1.fin_why1__id 
from ampata_sys_node t1
where t.id = t1.fin_txset1__id 
and t.class_name ='FinTxset'
and t1.class_name ='FinTxact'
and t.fin_why1__id is null
and t1.fin_why1__id is not null 
;

update ampata_sys_node t
set fin_why1__id = null
from ampata_sys_node t1
where t.fin_txset1__id = t1.id
and t.class_name ='FinTxact'
and t1.class_name ='FinTxset'
and t.fin_why1__id = t1.fin_why1__id
;

update ampata_sys_node t
set why_text1 = t1.why_text1 
from ampata_sys_node t1
where t.id = t1.fin_txset1__id 
and t.class_name ='FinTxset'
and t1.class_name ='FinTxact'
and t.why_text1 is null
and t1.why_text1 is not null 
;

update ampata_sys_node t
set why_text1 = null
from ampata_sys_node t1
where t.fin_txset1__id = t1.id
and t.class_name ='FinTxact'
and t1.class_name ='FinTxset'
and t.why_text1 = t1.why_text1
;




--beg1_ts1 
select 
 id2
,TO_DATE(SUBSTRING(id2,3,8),'YYYYMMDD')::date
from ampata_sys_node t
where t.class_name ='FinTxset'
and deleted_by is null
and beg1_ts1 is null 

update ampata_sys_node t
set beg1_ts1 = TO_DATE(SUBSTRING(id2,3,8),'YYYYMMDD')::date
where t.class_name ='FinTxset'
and deleted_by is null
and beg1_ts1 is null 


select 
 t3.id2 as txset1_id2
,t3.id_dt_date1  as txset1_id_dt
,t3.id_x as txset1_id_x
,t2.id2 as txact1_id2
,t2.id_dt_date1 as txact1_id_dt 
,t2.id_x as txact1_id_x
,t2.id_y as txact1_id_y
,t.id2
,t.id_dt_date1 
,t.id_x
,t.id_y
,t.id_z
from ampata_sys_node t
inner join ampata_sys_node t2
on t.fin_txact1__id = t2.id
inner join ampata_sys_node t3
on t2.fin_txset1__id = t3.id
where t.class_name ='FinTxactItm'
and t.deleted_by is null
and t2.class_name ='FinTxact'
and t3.class_name ='FinTxset'
and (t.id_dt_date1 <> t2.id_dt_date1 
or t2.id_dt_date1 <> t3.id_dt_date1 
or t.id_x <> t2.id_x 
or t2.id_x <> t3.id_x 
or t.id_y <> t2.id_y
)
order by t.id2


select t.id 
,t.id2 
,t.id_dt_date1 
,t.id_x
from ampata_sys_node t
where t.class_name = 'FinTxact'
and t.deleted_by is null
and (
   t.fin_txset1__id = '0ed1c324-6d57-4f84-827b-13a2d03a9861'
or t.fin_txset1__id = 'e119d6bf-355b-4fc2-93c8-1685fe6cc8ad'
)

select t.id 
,t.id2 
,t.id_dt_date1 
,t.id_x
,t.id_y
from ampata_sys_node t
where t.class_name = 'FinTxactItm'
and t.deleted_by is null
and (
   t.fin_txact1__id = '85cd9239-7831-4fed-b293-f60e17898f08'
or t.fin_txact1__id = 'c1797bd7-671d-42e4-9f84-1965b2df5f60'
)



select count(*)
from (
select 
 t2.id_dt_date1 
,t2.id_x
,t2.id_y
,t.id2
,t.id_dt_date1 
,t.id_x
,t.id_y
from ampata_sys_node t
inner join ampata_sys_node t2
on t.fin_txact1__id = t2.id
where t.class_name ='FinTxactItm'
and t.deleted_by is null
and t2.class_name ='FinTxact'
and (t.id_dt_date1 <> t2.id_dt_date1 
or t.id_x <> t2.id_x 
or t.id_y <> t2.id_y
)
order by t.id2
) t



