

SELECT id2
,fin_curcy1__id 
,fin_curcy1__id2 
,fin_curcy2__id 
,fin_curcy2__id2 
FROM ampata_sys_node t
where t.class_name = 'SysFinCurcyExchRate'

SELECT id2
FROM ampata_sys_node t
where t.class_name = 'SysFinCurcy'

update ampata_sys_node t
set fin_curcy2__id = t2.id
from ampata_sys_node t2
where t.class_name = 'SysFinCurcyExchRate'
and t.fin_curcy2__id2 = t2.id2
and t2.class_name = 'SysFinCurcy'
