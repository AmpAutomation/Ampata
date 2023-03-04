


insert into ampata_ext_curcy_exch_rate 

SELECT id
,id2
,id2_calc
,id2_cmp
,id2_dup
,sort_idx
,null as sort_key
,name1
,desc1
,amt1
,amt2
,fin_curcy1__id2
,fin_curcy2__id2
,deleted_by
,deleted_date 
,last_modified_by 
,last_modified_date 
,created_by 
,created_date 
,"version" 
,ts1_el_dt
,ts1_el_dt_yr
,ts1_el_dt_qtr
,ts1_el_dt_mon
,ts1_el_dt_mon2
,ts1_el_dt_day
FROM ampata_sys_entity t
where t.dtype = 'ampata_FinRate'

delete
FROM ampata_sys_entity t
where t.dtype = 'ampata_FinRate'


update ampata_sys_node t
set id2_calc = id2
where t.class_name = 'SysNodeFinCurcyExchRate'

select
 t1.id2
,t2.id2
,t.ts1_el_dt

from ampata_sys_node t
, ampata_sys_node t1
, ampata_sys_node t2
where t.class_name = 'SysNodeFinCurcyExchRate'
and t.fin_curcy1__id = t1.id 
and t.fin_curcy2__id = t2.id 
