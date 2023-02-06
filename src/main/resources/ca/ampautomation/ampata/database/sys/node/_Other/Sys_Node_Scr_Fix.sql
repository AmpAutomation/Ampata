
select
 id2
,class_name
,t.beg1_ts1
,t.beg1_date1::timestamp

FROM ampata_sys_node t
where t.beg1_ts1 is null 

update ampata_sys_node t
set beg1_ts1 = t.beg1_date1::timestamp
where t.beg1_ts1 is null 

