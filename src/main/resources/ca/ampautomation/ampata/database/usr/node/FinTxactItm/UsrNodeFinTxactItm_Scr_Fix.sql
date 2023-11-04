



update ampata_usr_node_type t
	set dtype ='enty_UsrNodeFinTxactItm'
where t.dtype = 'enty_UsrNodeFinTxfer'




-- Update id2
update ampata_usr_node t
	set id2 = t.id2_calc
where t.dtype ='enty_UsrNodeFinTxactItm'
and t.id2_calc is not null
and t.id2_calc <> ''



-- Update type1__id
update ampata_usr_node t
set type1__id = 'dcc34cd9-ddc8-4622-996b-a8dd86db652a'
where
	t.dtype = 'enty_UsrNodeFinTxactItm'
;



-- Update fin_txact1__id2
update ampata_usr_node as t
set fin_txact1__id2 = t1.id2
from ampata_usr_node as t1
where t.fin_txact1__id = t1.id 
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeFinTxact'
;


-- Update fin_fmla1__id
update ampata_usr_node as t
set fin_fmla1__id = t1.id
from ampata_sys_entity as t1
where t.fin_fmla1__id2 = t1.id2 
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrItemGenFmla'
;

/*
select 
	 t.id2
	,t.fin_fmla1__id 
	,t.fin_fmla1__id2 
from ampata_usr_node as t
	,ampata_sys_entity as t1
where t.fin_fmla1__id2 = t1.id2 
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrItemGenFmla'
;*/

-- Update fin_txact_itm1__id
update ampata_usr_node t
set fin_txact_itm1__id2 = t1.id2
from ampata_usr_node t1
where t.fin_txact_itm1__id = t1.id
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeFinTxactItm'
;

update ampata_usr_node t
set fin_txact_itm1__id2 = t1.id2
from ampata_usr_node t1
where t.fin_txact_itm1__id = t1.id
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeFinTxactItm'
;



select 
	 t.id2
	,t.fin_txact_itm1__id 
	,t.fin_txact_itm1__id2 
from ampata_usr_node t
	,ampata_usr_node t1
where t.fin_txact_itm1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeFinTxactItm'

select
	 t.id2
	,t.fin_txact_itm1__id 
	,t.fin_txact_itm1__id2 

from ampata_usr_node t
where t.dtype ='enty_UsrNodeFinTxactItm'
and t.fin_txact_itm1__id2 <> ''
and t.fin_txact_itm1__id is null


--D20170413-T0000-X01-Y02-Z01
select 
	 t.id2
	,t.fin_txact_itm1__id 
	,t.fin_txact_itm1__id2 

from ampata_usr_node t
where t.dtype ='enty_UsrNodeFinTxactItm'
and t.id2_calc like 'D20170413-T0000-X01%'
	



-- Update fin_curcy1__id
update ampata_usr_node t
set fin_curcy1__id = t1.id
from ampata_usr_node t1
where 
	t.fin_curcy1__id2 = t1.id2
and t.dtype = 'enty_UsrNodeFinTxactItm'
and t1.dtype = 'enty_SysNodeFinCurcy'
;

-- Problems
select 
	 t1.id2 
	,t1.fin_curcy1__id 
	,t1.fin_curcy1__id2 
from ampata_usr_node t1
where t1.dtype = 'enty_UsrNodeFinTxactItm'
and (
		t1.fin_curcy1__id is null
	or t1.fin_curcy1__id2 = ''
)
order by t1.id2 
;


-- Update fin_acct1__id
update ampata_usr_node t
set fin_acct1__id = t1.id
from ampata_usr_node t1
where t.fin_acct1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeFinAcct'
;

update ampata_usr_node t
set fin_acct1__id2 = replace(t.fin_acct1__id2,'|','/')
where t.dtype ='enty_UsrNodeFinTxactItm'
and not t.fin_acct1__id2 is null


select replace(t.fin_acct1__id2,'|','/'), count(*)
from ampata_usr_node t
group by t.fin_acct1__id2 



-- Update fin_stmt1__id
update ampata_usr_node t
set fin_stmt1__id = t1.id
from ampata_usr_node t1
where t.fin_stmt1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeFinStmt'
;

update ampata_usr_node t
set fin_stmt1__id2 = replace(t.fin_stmt1__id2,'|','/')
where t.dtype ='enty_UsrNodeFinTxactItm'
and not t.fin_stmt1__id2 is null


select replace(t.fin_stmt1__id2,'|','/') id2, count(*)
from ampata_usr_node t
where t.fin_stmt1__id2 is not null 
and t.fin_stmt1__id2 <> ''
group by t.fin_stmt1__id2 
order by t.fin_stmt1__id2 



-- Update fin_dept1__id
update ampata_usr_node t
set fin_dept1__id = t1.id
from ampata_usr_node t1
where t.fin_dept1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeFinDept'
;

update ampata_usr_node t
set fin_dept1__id2 = 'G'
where fin_dept1__id = '74befcf2-0d1b-4dc5-abf3-30405ca9cab5'

select replace(t.fin_dept1__id2,'|','/') id2, count(*)
from ampata_usr_node t
where t.fin_dept1__id2 is not null 

group by t.fin_dept1__id2 
order by t.fin_dept1__id2 




-- Update fin_tax_lne1__id
update ampata_usr_node t
set fin_tax_lne1__id = t1.id
from ampata_usr_node t1
where t.fin_tax_lne1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeGenDocFrg'
;

update ampata_usr_node t
set fin_tax_lne1__id2 = t1.id2
from ampata_usr_node t1
where t.fin_tax_lne1__id = t1.id
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeGenDocFrg'
;



select t.fin_tax_lne1__id2 id2
--	, count(*)
from ampata_usr_node t
left join ampata_usr_node t1
on  t.fin_tax_lne1__id2 = t1.id2
where t1.id=null
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrNodeGenDocFrg'

group by t.fin_tax_lne1__id2 
order by t.fin_tax_lne1__id2 


where t.fin_tax_lne1__id2 is not null 
and t.fin_tax_lne1__id2 <> ''
group by t.fin_tax_lne1__id2 
order by t.fin_tax_lne1__id2 


select t.fin_tax_lne1__id2, count(*)
from ampata_usr_node t
where t.fin_tax_lne1__id2 is not null 
and t.fin_tax_lne1__id2 <> ''
group by t.fin_tax_lne1__id2 
order by t.fin_tax_lne1__id2 




-- Update fin_rate1__id
update ampata_usr_node t
set fin_rate1__id = t1.id
from ampata_sys_entity t1
where t.fin_rate1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='ampata_FinRate'



-- To do
-- get exchange rate using 
-- this currency fin_curcy1__id
-- other currency fin_txact_itm__id.fin_curcy1__id, 
select 
	 t.id
	,t.fin_rate1__id
	,t.fin_rate1__id2
from ampata_usr_node t
	, ampata_sys_entity t1
where t.fin_rate1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='ampata_FinRate'
and t2.dtype ='enty_UsrNodeFinCurcy'
and t3.dtype ='enty_UsrNodeFinCurcy'
;



-- Update fin_how1__id
update ampata_usr_node t
set fin_how1__id = t1.id
from ampata_sys_entity t1
where t.fin_how1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrItemFinHow'
;

-- Update fin_what1__id
update ampata_usr_node t
set fin_what1__id = t1.id
from ampata_sys_entity t1
where t.fin_what1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrItemFinWhat'
;

-- Update fin_txact1__what1__id
update ampata_usr_node t
set fin_txact1__what1__id = t1.id
from ampata_sys_entity t1
where t.fin_txact1__what1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrItemFinWhat'
;




-- Update fin_why1__id
update ampata_usr_node t
set fin_why1__id = t1.id
from ampata_sys_entity t1
where t.fin_why1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrItemFinWhy'
;

-- Update fin_txact1__why1__id
update ampata_usr_node t
set fin_txact1__why1__id = t1.id
from ampata_sys_entity t1
where t.fin_txact1__why1__id2 = t1.id2
and t.dtype ='enty_UsrNodeFinTxactItm'
and t1.dtype ='enty_UsrItemFinWhy'
;



update ampata_usr_node t
set 
	 ts2_el_dt = fin_txact1__ts1_el_dt
	,ts2_el_tm = fin_txact1__ts1_el_tm
where t.dtype ='enty_UsrNodeFinTxactItm'
;


update ampata_usr_node t
set 
	 ts1_el_ts =	case when ts1_el_dt is not null then (ts1_el_dt + case when ts1_el_tm is not null then ts1_el_tm else '00:00'::time end)::timestamp else null end
	,ts2_el_ts =	case when ts2_el_dt is not null then (ts2_el_dt + case when ts2_el_tm is not null then ts2_el_tm else '00:00'::time end)::timestamp else null end
where t.dtype ='enty_UsrNodeFinTxactItm'
;

select 
	 case when id_ts_date1 is not null then (id_ts_date1 + case when id_ts_time1 is not null then id_ts_time1 else '00:00'::time end)::timestamp else null end as id_ts_ts1
	,id_ts_date1
	,id_ts_time1
	,case when ts1_el_dt is not null then (ts1_el_dt + case when ts1_el_tm is not null then ts1_el_tm else '00:00'::time end)::timestamp else null end as ts1_el_ts
	,ts1_el_dt
	,ts1_el_tm
	,case when ts2_el_dt is not null then (ts2_el_dt + case when ts2_el_tm is not null then ts2_el_tm else '00:00'::time end)::timestamp else null end as ts2_el_ts
	,ts2_el_dt
	,ts2_el_tm
from ampata_usr_node t
where t.dtype ='enty_UsrNodeFinTxactItm'



select *
from ampata_usr_node t
where t.id2 = '/D20200330/T0000/X00/Y00/Z00'


delete 
from ampata_usr_node 
where id = 'afcbb4f4-d819-4b89-a9fa-967b78c9338c'

select * 
from ampata_usr_node t
where dtype = 'enty_UsrNodeFinTxactItm'
and gen_chan1__id = '7f0c4d3f-925e-4d82-8557-e68935f1d3d5'

select * 
from ampata_usr_node 
where id = '7f0c4d3f-925e-4d82-8557-e68935f1d3d5'

select * 
from ampata_usr_node 
where id = '3977f4d5-672d-4685-9566-9a8e00756cb3'


select * 
from ampata_usr_node 
where id = 'edbaf1b0-f717-4c6a-8e0b-c53df9b23ea3'


SELECT
t.id
,t.id2
,t.dtype
,t.fin_txact1__ts1_el_dt
from ampata_usr_node t
WHERE t.fin_txact1__ts1_el_dt is not null




SELECT
t.ts3_el_ts
,count(t.ts3_el_ts)
from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxactItm'
group by t.ts3_el_ts 

SELECT
 t.id2
,t.dtype
,t.ts1_el_ts
,t.ts2_el_ts
,t.ts3_el_ts
,t.nm1s1_inst1_ts1_el_ts 
,t.nm1s1_inst1_dt1_el_dt 

FROM ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxactItm'
and t.ts1_el_ts = t.ts2_el_ts
and t.ts3_el_ts is null



UPDATE ampata_usr_node t 
set ts2_el_ts = null
where t.dtype = 'enty_UsrNodeFinTxactItm'
and t.ts1_el_ts = t.ts2_el_ts
and t.ts3_el_ts is null

UPDATE ampata_usr_node t 
set ts1_el_ts = t.nm1s1_inst1_ts1_el_ts
where t.dtype = 'enty_UsrNodeFinTxactItm'


UPDATE ampata_usr_node t 
set ts2_el_ts = t.ts3_el_ts
where t.dtype = 'enty_UsrNodeFinTxactItm'


UPDATE ampata_usr_node t 
set ts3_el_ts = null
where t.dtype = 'enty_UsrNodeFinTxactItm'

UPDATE ampata_usr_node t 
set
	ts3_el_dt = t.ts3_el_ts::date
	,ts3_el_dt_yr = date_part('year',t.ts3_el_dt)
	,ts3_el_dt_qtr = date_part('quarter',t.ts3_el_ts)
	,ts3_el_dt_mon =  date_part('Mon',t.ts3_el_dt)
	,ts3_el_dt_mon2 =  to_char(t.ts3_el_dt,'Mon')
	,ts3_el_dt_day = date_part('day',t.ts3_el_dt)
	,ts3_el_tm  = t.ts3_el_ts::time
	,ts3_el_tm_hr  = date_part('hour',t.ts3_el_ts)
	,ts3_el_tm_min  = date_part('minute',t.ts3_el_ts)
where t.dtype = 'enty_UsrNodeFinTxactItm'





UPDATE ampata_usr_node t 
set parent1__id = t.fin_txact1__id 
where t.dtype = 'enty_UsrNodeFinTxactItm'


UPDATE ampata_usr_node t 
set parent1__id = t.fin_txact_set1__id 
where t.dtype = 'enty_UsrNodeFinTxact'







--ts1
select count(*)
from (

select 
case when ftx.ts1_el_ts <> ftxi.ts1_el_ts or ftxs.ts1_el_ts <> ftxi.ts1_el_ts 
	then 'ts1_el_ts' 
	else 
		case when ftx.int1 <> ftxi.int1 or ftxs.sort_idx <> ftxi.int1
			then 'int1' 
			else 
				case when ftx.sort_idx <> ftxi.int2
					then 'int2' 
					else 'good'
					end
			end
	end

,ftxs.id2
,ftxs.ts1_el_ts
,ftxs.sort_idx

,ftx.id2
,ftx.ts1_el_ts 
,ftx.int1
,ftx.sort_idx

,ftxi.parent1__id 
,ftxi.id 
,ftxi.id2
,ftxi.ts1_el_ts
,ftxi.ts2_el_ts
,ftxi.ts3_el_ts
,ftxi.int1
,ftxi.int2
,ftxi.sort_idx
,ftxi.desc1
from ampata_usr_node ftxi
inner join ampata_usr_node ftx
on ftx.id = ftxi.parent1__id 
inner join ampata_usr_node ftxs
on ftxs.id = ftx.parent1__id 
where ftxi.dtype = 'enty_UsrNodeFinTxactItm'
and (
    ftx.ts1_el_ts <> ftxi.ts1_el_ts 
or ftxs.ts1_el_ts <> ftxi.ts1_el_ts 
or ftx.int1 <> ftxi.int1
or ftxs.sort_idx <> ftxi.int1
or ftx.sort_idx <> ftxi.int2

)
order by ftxi.id2

)t2

select
t.id
,t.id2
,t.ts1_el_ts 
,t.int1 
,t.sort_idx

from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinTxact'
and t.ts1_el_ts = '2019-06-24 00:00:00.000'


select
t.tenant
,t.id
,t.id2
,t.ts1_el_ts 
,t.int1 
,t.sort_idx
,t.amt_debt
,t.amt_cred
,t.amt_net

from ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinStmtItm'
and t.ts1_el_ts >= '2019-06-23 00:00:00.000'
and t.ts1_el_ts <= '2019-06-25 00:00:00.000'


select 
ftxi.id2
,ftxi.desc1
,ftxi.int2
,ftxi.int1
from ampata_usr_node ftxi
inner join ampata_usr_node ftx
on ftx.id = ftxi.parent1__id 
inner join ampata_usr_node ftxs
on ftxs.id = ftx.parent1__id 
where ftxi.dtype = 'enty_UsrNodeFinTxactItm'
and ftxi.ts1_el_ts <> ftx.ts1_el_ts 
order by ftxi.id2

select 
ftxi.id2
from ampata_usr_node ftxi
where ftxi.dtype ='enty_UsrNodeFinTxactItm'
and ftxi.parent1__id = '89d095e6-c5ad-e21e-6b91-f944d04f4f7f'


select 
ftxi.id
,ftxi.id2
,fsti.id
,fsti.id2
from ampata_usr_node ftxi
inner join ampata_usr_node fsti
on ftxi.fin_stmt_itm1__id = fsti.id
where ftxi.dtype ='enty_UsrNodeFinTxactItm'
and fsti.deleted_by is not null


select 
fsti.id
,fsti.id2
--,fsti.deleted_by 
--,fsti.deleted_date 
,fsti.txt1
,fsti.txt2
,fsti.txt3
,fsti.txt4
from ampata_usr_node fsti
where dtype = 'enty_UsrNodeFinStmtItm'


