

-- Update type1__id

update ampata_sys_node t
set type1__id = t1.id
FROM ampata_sys_node_type t1
where t.type1__id2 = t1.id2 
and	t.class_name = 'UsrNodeGenDocVer'
and t1.class_name ='GenDocVer'
;

update ampata_usr_node t
set name1 = id2
where t.class_name = 'UsrNodeGenDocVer'
and t.name1 is null



