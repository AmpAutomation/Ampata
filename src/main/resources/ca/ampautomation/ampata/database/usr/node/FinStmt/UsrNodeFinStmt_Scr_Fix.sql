


select t2.id
from ampata_sys_node t1
inner join ampata_sys_node t2
on t1.fin_acct1__id2 = t2.id2
where 
t1.class_name ='FinStmt'
and t2.class_name ='FinAcct'



-- Update id2
update ampata_sys_node t
set	id2 = id2_calc 
where
t.class_name = 'UsrNodeFinStmt'
and t.id2_calc is not null
and t.id2_calc <> ''
;


select sum(e.amt_debt)
from ampata_sys_node e 
where e.class_name = 'UsrNodeFinTxactItm'
and e.fin_stmt1__id2 = '/L/Mark/RBC/Visa/D20200203'
group by e.fin_stmt1__id2
;

select e.id, e.id2, e.fin_stmt_itm1__desc1 , e.amt_debt, e.amt_cred, e.amt_net
from ampata_sys_node e 
where e.class_name = 'UsrNodeFinTxactItm'
and e.fin_stmt1__id2 = '/L/Mark/RBC/Visa/D20200203'
;

update ampata_sys_node e 
set fin_stmt_itm1__desc1 = 'Online Banking payment'
, fin_stmt_itm1__desc2 = 'ALBERTA MAINT.'
, fin_stmt_itm1__desc3 = ''
, fin_stmt_itm1__ref_id = '8851'
where e.class_name = 'UsrNodeFinTxactItm'
and e.fin_stmt_itm1__desc1 = 'Online Banking payment - 8851 ALBERTA MAINT.'
;

select distinct e.fin_stmt_itm1__desc1, e.fin_stmt_itm1__desc2, e.fin_stmt_itm1__desc3
from ampata_sys_node e 
where e.class_name = 'UsrNodeFinTxactItm'
and e.fin_stmt_itm1__desc1 like '%MAINT%'
;


select count(t.fin_stmt_itm1__desc1)
from ampata_sys_node t
where
	t.fin_stmt_itm1__desc1 = 'MAINT'

select
	 t.id
	,t.id2
	,t.ts1_el_dt
	,t.fin_stmt_itm1__desc1
	,t.fin_stmt_itm1__desc2
	,t.fin_stmt_itm1__desc3
from ampata_sys_node t
where
	t.fin_stmt_itm1__desc2 like '%MAINT.%'

	
delete from ampata_sys_node
where id = 'b5e67285-760d-0321-e95c-8dce336677dd'


1027.15
select 1002.94  + 24.21


update ampata_sys_node t
set 
	 ts1_el_ts =	case when ts1_el_dt is not null then (ts1_el_dt + case when ts1_el_tm is not null then ts1_el_tm else '00:00'::time end)::timestamp else null end
	,ts3_el_ts =	case when ts3_el_dt is not null then (ts3_el_dt + case when ts3_el_tm is not null then ts3_el_tm else '00:00'::time end)::timestamp else null end
where t.class_name ='FinStmt'
;

select 
	 case when ts1_el_dt is not null then (ts1_el_dt + case when ts1_el_tm is not null then ts1_el_tm else '00:00'::time end)::timestamp else null end as ts1_el_ts
	,ts1_el_dt
	,ts1_el_tm
	,case when ts3_el_dt is not null then (ts3_el_dt + case when ts3_el_tm is not null then ts3_el_tm else '00:00'::time end)::timestamp else null end as ts3_el_ts
	,ts3_el_dt
	,ts3_el_tm
from ampata_sys_node t
where t.class_name ='FinStmt'


select 
ts1_el_ts
,ts2_ts1 
,ts3_ts1 
from ampata_usr_node t
where t.class_name = 'UsrNodeFinStmt'

update ampata_usr_node t
set 
 ts2_ts1 = ts3_ts1 
where t.class_name = 'UsrNodeFinStmt'

update ampata_usr_node t
set 
ts3_ts1 = null
,ts3_date1 = null
,ts3_date1_yr = null
,ts3_date1_qtr = null
,ts3_date1_mon =  null
,ts3_date1_mon2 =  null
,ts3_date1_day = null
,ts3_time1  = null
,ts3_time1_hr  = null
,ts3_time1_min  = null
where t.class_name = 'UsrNodeFinStmt'
;


update ampata_usr_node t
set 
 ts2_date1 = t.ts2_ts1::date
,ts2_date1_yr = date_part('year',t.ts2_date1)
,ts2_date1_qtr = date_part('quarter',t.ts2_ts1)
,ts2_date1_mon =  date_part('Mon',t.ts2_date1)
,ts2_date1_mon2 =  to_char(t.ts2_date1,'Mon')
,ts2_date1_day = date_part('day',t.ts2_date1)
,ts2_time1  = t.ts2_ts1::time
,ts2_time1_hr  = date_part('hour',t.ts2_ts1)
,ts2_time1_min  = date_part('minute',t.ts2_ts1)
where t.class_name = 'UsrNodeFinStmt'
;
	


