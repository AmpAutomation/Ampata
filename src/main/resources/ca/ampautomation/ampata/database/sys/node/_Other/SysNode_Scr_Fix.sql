
select
 id2
,dtype
,t.ts1_el_ts
,t.ts1_el_dt::timestamp

FROM ampata_sys_node t
where t.ts1_el_ts is null

update ampata_sys_node t
set ts1_el_ts = t.ts1_el_dt::timestamp
where t.ts1_el_ts is null

