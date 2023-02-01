


-- Update id2
update ampata_sys_node t
	set id2 = t.id2_calc
where t.class_name ='FinStmtItm'
and t.id2_calc is not null
and t.id2_calc <> ''

