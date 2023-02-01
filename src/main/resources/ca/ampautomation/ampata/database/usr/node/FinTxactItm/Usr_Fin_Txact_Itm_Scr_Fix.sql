



update ampata_sys_node_type t
	set class_name ='FinTxactItm'
where t.class_name = 'FinTxfer'




-- Update id2
update ampata_sys_node t
	set id2 = t.id2_calc
where t.class_name ='FinTxactItm'
and t.id2_calc is not null
and t.id2_calc <> ''



-- Update type1__id
update ampata_sys_node t
set type1__id = 'dcc34cd9-ddc8-4622-996b-a8dd86db652a'
where
	t.class_name = 'FinTxactItm'
;



-- Update fin_txact1__id2
update ampata_sys_node as t
set fin_txact1__id2 = t1.id2
from ampata_sys_node as t1
where t.fin_txact1__id = t1.id 
and t.class_name ='FinTxactItm'
and t1.class_name ='FinTxact'
;


-- Update fin_fmla1__id
update ampata_sys_node as t
set fin_fmla1__id = t1.id
from ampata_sys_entity as t1
where t.fin_fmla1__id2 = t1.id2 
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinFmla'
;

/*
select 
	 t.id2
	,t.fin_fmla1__id 
	,t.fin_fmla1__id2 
from ampata_sys_node as t
	,ampata_sys_entity as t1
where t.fin_fmla1__id2 = t1.id2 
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinFmla'
;*/

-- Update fin_txact_itm1__id
update ampata_sys_node t
set fin_txact_itm1__id2 = t1.id2
from ampata_sys_node t1
where t.fin_txact_itm1__id = t1.id
and t.class_name ='FinTxactItm'
and t1.class_name ='FinTxactItm'
;

update ampata_sys_node t
set fin_txact_itm1__id2 = t1.id2
from ampata_sys_node t1
where t.fin_txact_itm1__id = t1.id
and t.class_name ='FinTxactItm'
and t1.class_name ='FinTxactItm'
;



select 
	 t.id2
	,t.fin_txact_itm1__id 
	,t.fin_txact_itm1__id2 
from ampata_sys_node t
	,ampata_sys_node t1
where t.fin_txact_itm1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.class_name ='FinTxactItm'

select
	 t.id2
	,t.fin_txact_itm1__id 
	,t.fin_txact_itm1__id2 

from ampata_sys_node t
where t.class_name ='FinTxactItm'
and t.fin_txact_itm1__id2 <> ''
and t.fin_txact_itm1__id is null


--D20170413-T0000-X01-Y02-Z01
select 
	 t.id2
	,t.fin_txact_itm1__id 
	,t.fin_txact_itm1__id2 

from ampata_sys_node t
where t.class_name ='FinTxactItm'
and t.id2_calc like 'D20170413-T0000-X01%'
	



-- Update fin_curcy1__id
update ampata_sys_node t
set fin_curcy1__id = t1.id
from ampata_sys_node t1
where 
	t.fin_curcy1__id2 = t1.id2
and t.class_name = 'FinTxactItm'
and t1.class_name = 'FinCurcy'
;

-- Problems
select 
	 t1.id2 
	,t1.fin_curcy1__id 
	,t1.fin_curcy1__id2 
from ampata_sys_node t1
where t1.class_name = 'FinTxactItm'
and (
		t1.fin_curcy1__id is null
	or t1.fin_curcy1__id2 = ''
)
order by t1.id2 
;


-- Update fin_acct1__id
update ampata_sys_node t
set fin_acct1__id = t1.id
from ampata_sys_node t1
where t.fin_acct1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.class_name ='FinAcct'
;

update ampata_sys_node t
set fin_acct1__id2 = replace(t.fin_acct1__id2,'|','/')
where t.class_name ='FinTxactItm'
and not t.fin_acct1__id2 is null


select replace(t.fin_acct1__id2,'|','/'), count(*)
from ampata_sys_node t
group by t.fin_acct1__id2 



-- Update fin_stmt1__id
update ampata_sys_node t
set fin_stmt1__id = t1.id
from ampata_sys_node t1
where t.fin_stmt1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.class_name ='FinStmt'
;

update ampata_sys_node t
set fin_stmt1__id2 = replace(t.fin_stmt1__id2,'|','/')
where t.class_name ='FinTxactItm'
and not t.fin_stmt1__id2 is null


select replace(t.fin_stmt1__id2,'|','/') id2, count(*)
from ampata_sys_node t
where t.fin_stmt1__id2 is not null 
and t.fin_stmt1__id2 <> ''
group by t.fin_stmt1__id2 
order by t.fin_stmt1__id2 



-- Update fin_dept1__id
update ampata_sys_node t
set fin_dept1__id = t1.id
from ampata_sys_node t1
where t.fin_dept1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.class_name ='FinDept'
;

update ampata_sys_node t
set fin_dept1__id2 = 'G'
where fin_dept1__id = '74befcf2-0d1b-4dc5-abf3-30405ca9cab5'

select replace(t.fin_dept1__id2,'|','/') id2, count(*)
from ampata_sys_node t
where t.fin_dept1__id2 is not null 

group by t.fin_dept1__id2 
order by t.fin_dept1__id2 




-- Update fin_tax_lne1__id
update ampata_sys_node t
set fin_tax_lne1__id = t1.id
from ampata_sys_node t1
where t.fin_tax_lne1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.class_name ='GenDocFrg'
;

update ampata_sys_node t
set fin_tax_lne1__id2 = t1.id2
from ampata_sys_node t1
where t.fin_tax_lne1__id = t1.id
and t.class_name ='FinTxactItm'
and t1.class_name ='GenDocFrg'
;



select t.fin_tax_lne1__id2 id2
--	, count(*)
from ampata_sys_node t
left join ampata_sys_node t1
on  t.fin_tax_lne1__id2 = t1.id2
where t1.id=null
and t.class_name ='FinTxactItm'
and t1.class_name ='GenDocFrg'

group by t.fin_tax_lne1__id2 
order by t.fin_tax_lne1__id2 


where t.fin_tax_lne1__id2 is not null 
and t.fin_tax_lne1__id2 <> ''
group by t.fin_tax_lne1__id2 
order by t.fin_tax_lne1__id2 


select t.fin_tax_lne1__id2, count(*)
from ampata_sys_node t
where t.fin_tax_lne1__id2 is not null 
and t.fin_tax_lne1__id2 <> ''
group by t.fin_tax_lne1__id2 
order by t.fin_tax_lne1__id2 




-- Update fin_rate1__id
update ampata_sys_node t
set fin_rate1__id = t1.id
from ampata_sys_entity t1
where t.fin_rate1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinRate'



-- To do
-- get exchange rate using 
-- this currency fin_curcy1__id
-- other currency fin_txact_itm__id.fin_curcy1__id, 
select 
	 t.id
	,t.fin_rate1__id
	,t.fin_rate1__id2
from ampata_sys_node t
	, ampata_sys_entity t1
where t.fin_rate1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinRate'
and t2.class_name ='FinCurcy'
and t3.class_name ='FinCurcy'
;



-- Update fin_how1__id
update ampata_sys_node t
set fin_how1__id = t1.id
from ampata_sys_entity t1
where t.fin_how1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinHow'
;

-- Update fin_what1__id
update ampata_sys_node t
set fin_what1__id = t1.id
from ampata_sys_entity t1
where t.fin_what1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinWhat'
;

-- Update fin_txact1__what1__id
update ampata_sys_node t
set fin_txact1__what1__id = t1.id
from ampata_sys_entity t1
where t.fin_txact1__what1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinWhat'
;




-- Update fin_why1__id
update ampata_sys_node t
set fin_why1__id = t1.id
from ampata_sys_entity t1
where t.fin_why1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinWhy'
;

-- Update fin_txact1__why1__id
update ampata_sys_node t
set fin_txact1__why1__id = t1.id
from ampata_sys_entity t1
where t.fin_txact1__why1__id2 = t1.id2
and t.class_name ='FinTxactItm'
and t1.dtype ='ampata_FinWhy'
;



update ampata_sys_node t
set 
	 beg2_date1 = fin_txact1__beg1_date1
	,beg2_time1 = fin_txact1__beg1_time1
where t.class_name ='FinTxactItm'
;


update ampata_sys_node t
set 
	 beg1_ts1 =	case when beg1_date1 is not null then (beg1_date1 + case when beg1_time1 is not null then beg1_time1 else '00:00'::time end)::timestamp else null end
	,beg2_ts1 =	case when beg2_date1 is not null then (beg2_date1 + case when beg2_time1 is not null then beg2_time1 else '00:00'::time end)::timestamp else null end
where t.class_name ='FinTxactItm'
;

select 
	 case when id_ts_date1 is not null then (id_ts_date1 + case when id_ts_time1 is not null then id_ts_time1 else '00:00'::time end)::timestamp else null end as id_ts_ts1
	,id_ts_date1
	,id_ts_time1
	,case when beg1_date1 is not null then (beg1_date1 + case when beg1_time1 is not null then beg1_time1 else '00:00'::time end)::timestamp else null end as beg1_ts1
	,beg1_date1
	,beg1_time1
	,case when beg2_date1 is not null then (beg2_date1 + case when beg2_time1 is not null then beg2_time1 else '00:00'::time end)::timestamp else null end as beg2_ts1 
	,beg2_date1
	,beg2_time1
from ampata_sys_node t
where t.class_name ='FinTxactItm'



select *
from ampata_sys_node t
where t.id2 = '/D20200330/T0000/X00/Y00/Z00'


delete 
from ampata_sys_node 
where id = 'afcbb4f4-d819-4b89-a9fa-967b78c9338c'

select * 
from ampata_sys_node t
where class_name = 'FinTxactItm'
and gen_chan1__id = '7f0c4d3f-925e-4d82-8557-e68935f1d3d5'

select * 
from ampata_sys_node 
where id = '7f0c4d3f-925e-4d82-8557-e68935f1d3d5'

select * 
from ampata_sys_node 
where id = '3977f4d5-672d-4685-9566-9a8e00756cb3'


select * 
from ampata_sys_node 
where id = 'edbaf1b0-f717-4c6a-8e0b-c53df9b23ea3'
