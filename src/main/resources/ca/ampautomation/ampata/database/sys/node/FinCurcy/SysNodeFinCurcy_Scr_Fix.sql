

SELECT id2
,fin_curcy1__id 
,fin_curcy1__id2 
,fin_curcy2__id 
,fin_curcy2__id2 
FROM ampata_sys_node t
where t.dtype = 'enty_SysNodeFinCurcyExchRate'

SELECT id2
FROM ampata_sys_node t
where t.dtype = 'enty_SysNodeFinCurcy'

update ampata_sys_node t
set fin_curcy2__id = t2.id
from ampata_sys_node t2
where t.dtype = 'enty_SysNodeFinCurcyExchRate'
and t.fin_curcy2__id2 = t2.id2
and t2.dtype = 'enty_SysNodeFinCurcy'
