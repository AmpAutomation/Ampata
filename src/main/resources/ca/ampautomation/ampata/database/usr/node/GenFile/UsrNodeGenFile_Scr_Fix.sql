
SELECT t.id
,id2
,dtype
,class_name
,tenant 
FROM ampata_usr_node t
where t.dtype = 'enty_UsrNodeGenFile'

update ampata_usr_node t
set dtype  = 'enty_UsrNodeGenFile'
where t.dtype = 'enty_UsrNodeGenFile'

