
UPDATE ampata_sys_node t 
set id2 = t.id2_calc 
where t.dtype = 'enty_UsrNodeFinBal'
and t.deleted_by is null



--set manual values
UPDATE ampata_sys_node t 
set amt_beg_bal = t.amt_beg_bal_calc 
	,amt_debt = t.fin_txact_itms1__amt_debt_sum_calc
	,amt_cred = t.fin_txact_itms1__amt_cred_sum_calc
	,amt_net = case when t3.bal_inc_on_debt 
		then coalesce(t.fin_txact_itms1__amt_debt_sum_calc,0) - coalesce(t.fin_txact_itms1__amt_cred_sum_calc,0) 
		else coalesce(t.fin_txact_itms1__amt_cred_sum_calc,0) - coalesce(t.fin_txact_itms1__amt_debt_sum_calc,0)  
		end
	,amt_end_bal = t.amt_end_bal_calc
from ampata_sys_node t2
, ampata_sys_node_type t3
where t.dtype = 'enty_UsrNodeFinBal'
and t.deleted_by is null
and t.fin_acct1__id = t2.id
and t2.type1__id = t3.id




SELECT
 t.id2
,t.dtype
,t.ts1_el_ts
,t.ts2_el_ts
,t.ts3_el_ts

FROM ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinBal'


UPDATE ampata_usr_node t 
set ts2_el_ts = t.ts3_el_ts
where t.dtype = 'enty_UsrNodeFinBal'


UPDATE ampata_usr_node t 
set ts3_el_ts = null
where t.dtype = 'enty_UsrNodeFinBal'

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
where t.dtype = 'enty_UsrNodeFinBal'


