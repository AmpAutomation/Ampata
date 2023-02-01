



select distinct t1.id2
from ampata_sys_node as t
, ampata_sys_node as t1
where t.gen_agent1__id2 = t1.id2 
and t.class_name ='FinAcct'
and t1.class_name ='GenAgent'


update ampata_sys_node t
set	id2 = id2_calc 
where
	t.class_name = 'FinAcct'
;




update ampata_sys_node
set gen_agent1__id2 = 'C-M&K Sluser'
where class_name ='FinAcct'
and gen_agent1__id2 ='M&K'

select gen_agent1__id2
from ampata_sys_node
where class_name ='FinAcct'
and gen_agent1__id2 LIKE'%M&K%'

