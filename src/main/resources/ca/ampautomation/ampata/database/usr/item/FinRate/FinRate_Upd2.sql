


update ampata_sys_entity t
set fin_curcy1__id = t1.id
from ampata_sys_node t1
where
	t.dtype = 'enty_SysNodeFinCurcyExchRate'
and t1.dtype = 'enty_SysNodeFinCurcy'
and t.fin_curcy1__id2 = t1.id2

/*
select t.id
	,t.id2
	,t.fin_curcy1__id
	,t.fin_curcy1__id2

from ampata_sys_entity t
	, ampata_sys_node t1
where
	t.dtype = 'enty_SysNodeFinCurcyExchRate'
and t1.dtype = 'enty_SysNodeFinCurcy'
and t.fin_curcy1__id2 = t1.id2
*/


update ampata_sys_entity t
set fin_curcy1__id = t1.id
from ampata_sys_node t1
where
	t.dtype = 'enty_SysNodeFinCurcyExchRate'
and t1.dtype = 'enty_SysNodeFinCurcy'
and t.fin_curcy2__id2 = t1.id2


update ampata_sys_entity t
set id2 = id2_calc
where
	t.dtype = 'enty_SysNodeFinCurcyExchRate'

select count(*)
from ampata_sys_entity t
where
	t.dtype = 'enty_SysNodeFinCurcyExchRate'

	
update ampata_sys_entity t
set fin_curcy1__id = t.fin_curcy2__id
	,fin_curcy1__id2 = t.fin_curcy2__id2
where t.dtype = 'enty_SysNodeFinCurcyExchRate'

update ampata_sys_entity t
set fin_curcy2__id = '79f81a41-8846-4502-bba7-f771e74c3f91'
	,fin_curcy2__id2 = 'CAD'
where t.dtype = 'enty_SysNodeFinCurcyExchRate'


