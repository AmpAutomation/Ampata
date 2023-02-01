


select 
1
-- txg.id
,txg.id2
,txg.id_dt_date1

--,tx.id
,tx.id2
,tx.id_dt_date1
,tx.id_x
,tx.id_y

--,txi.id
,txi.id2
,txi.id_dt_date1
,txi.id_x
,txi.id_y
,txi.id_z
from ampata_sys_node txi
inner join ampata_sys_node tx on txi.fin_txact1__id = tx.id
inner join ampata_sys_node txg on tx.fin_txset1__id = txg.id
where txi.class_name = 'FinTxactItm'
and txi.deleted_by is null 
and tx.class_name = 'FinTxact'
and txg.class_name = 'FinTxset'
and (
	txi.id_dt_date1  <> tx.id_dt_date1 
or	txi.id_x <> tx.id_x
or	txi.id_y <> tx.id_y

or	txg.id_dt_date1  <> tx.id_dt_date1 
or	txg.id_x <> tx.id_x

)

