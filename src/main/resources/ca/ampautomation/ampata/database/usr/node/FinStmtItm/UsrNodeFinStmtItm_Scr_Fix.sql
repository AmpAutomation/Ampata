


-- Update id2
update ampata_usr_node t
	set id2 = t.id2_calc
where t.dtype ='enty_UsrNodeFinStmtItm'
and t.id2_calc is not null
and t.id2_calc <> ''



select 
t.*
/*
,t.desc1 
,t.txt1
,t.txt2
,t.txt3
,t.txt4
*/
from ampata_usr_node t
where t.dtype ='enty_UsrNodeFinStmtItm'
and (t.fin_txact_itms1__id_cnt_calc = 0 or t.fin_txact_itms1__id_cnt_calc is null)

