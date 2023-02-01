


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
,beg1_date1
,beg1_date1_yr
,beg1_date1_qtr
,beg1_date1_mon
,beg1_date1_mon2
,beg1_date1_day
FROM ampata_sys_entity t
where t.dtype = 'ampata_FinRate'

delete
FROM ampata_sys_entity t
where t.dtype = 'ampata_FinRate'


update ampata_sys_node t
set class_name = 'FinCurcy'
where t.class_name = 'SysFinCurcy'

