


update ampata_sys_node t
set gen_chan1__id  = t1.id 
from ampata_sys_node t1
where t.gen_chan1__id2 = t1.id2
and t1.class_name  = 'GenChan'


update ampata_sys_node t
set  fin_txact1__beg_date2  = fin_txact1__beg_date1
	,fin_txact1__beg_time2  = fin_txact1__beg_time1


select fin_txact1__beg_date1, fin_txact1__beg_date2
from	ampata_sys_node t
where fin_txact1__beg_date2 is not null


	