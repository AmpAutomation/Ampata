


SELECT
 t.id2
,t.dtype
,t.ts1_el_ts
,t.ts2_el_ts
,t.ts3_el_ts

FROM ampata_usr_node t
where t.dtype = 'enty_UsrNodeFinBalSet'


UPDATE ampata_usr_node t 
set ts2_el_ts = t.ts3_el_ts
where t.dtype = 'enty_UsrNodeFinBalSet'


UPDATE ampata_usr_node t 
set ts3_el_ts = null
where t.dtype = 'enty_UsrNodeFinBalSet'

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
where t.dtype = 'enty_UsrNodeFinBalSet'



