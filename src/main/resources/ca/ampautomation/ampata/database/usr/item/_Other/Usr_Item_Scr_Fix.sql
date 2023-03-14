



SELECT t.id
,id2
,dtype
,class_name
--,'enty_' || dtype dtype2
--,'Usr' || class_name class_name2
FROM ampata_usr_node t
where t.dtype = 'enty_UsrNodeGenFile'


update ampata_sys_node  t
set  dtype = 'enty_Sys' || class_name

,dtype = 'enty_' || dtype
