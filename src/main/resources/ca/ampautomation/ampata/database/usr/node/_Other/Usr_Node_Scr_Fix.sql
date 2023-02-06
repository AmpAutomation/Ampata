


update ampata_usr_node t
set gen_chan1__id  = t1.id 
from ampata_usr_node t1
where t.gen_chan1__id2 = t1.id2
and t1.class_name  = 'GenChan'


update ampata_usr_node t
set  fin_txact1__beg_date2  = fin_txact1__beg_date1
	,fin_txact1__beg_time2  = fin_txact1__beg_time1


select fin_txact1__beg_date1, fin_txact1__beg_date2
from	ampata_usr_node t
where fin_txact1__beg_date2 is not null


update ampata_usr_node t
set  dtype = 'Usr' || t.class_name
WHERE t.class_name IS NOT NULL

update ampata_usr_node t
set  dtype = case when t.class_name = 'UsrGenTag' then
WHERE t.class_name IS NOT NULL

SELECT t.id
,id2
,dtype
,class_name
,tenant 
FROM ampata_usr_node t
where t.id2 = 'Test3'
WHERE t.class_name IS NULL


,fin_txact1__beg_time2  = fin_txact1__beg_time1

	