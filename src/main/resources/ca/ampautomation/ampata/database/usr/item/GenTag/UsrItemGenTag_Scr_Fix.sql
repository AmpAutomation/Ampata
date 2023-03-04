


SELECT t.id
,id2
,dtype
,class_name
,tenant 
FROM ampata_usr_node t
where t.class_name = 'UsrItemGenTag'

update ampata_usr_node t
set dtype  = 'enty_UsrItemGenTag'
where t.class_name = 'UsrItemGenTag'
