select agn.id,agn.id2
from ampata_sys_node agn 
where dtype = 'enty_UsrNodeFinAcct'
and agn.id2 = '/L/Mark/RBC/Visa'
;


select 
	 t.id
	,t.id2
	,t.id2_calc
	,t.dtype
	,t.type1__id
	,t.ts1_el_ts
	,t.fin_acct1__id
	,t.fin_stmt1__id
	,t.fin_stmt_itm1__desc1
	,t.fin_stmt_itm1__desc2
	,t.fin_stmt_itm1__desc3
	,t.fin_stmt_itm1__desc_amt
	,t.fin_stmt_itm1__ref_id
	,t.amt_debt
	,t.amt_cred
	,t.fin_curcy1__id
	
from ampata_sys_node t
where
	t.dtype = 'enty_UsrNodeFinTxactItm'
and 
	t.id2 = '' 


order by 
	t.id2
;


and
	t.ts1_el_dt >= '2021-07-02'

select *
from ampata_sys_node t
where
	t.dtype = 'enty_UsrNodeFinTxactItm'
and
	t.fin_stmt1__id  = '91d7a0af-f997-4b74-817b-855245c98735'


and
	t.amt_cred = 14

select 
	 t.id
	,t.id2
from
	ampata_sys_node t
where
	t.dtype = 'enty_UsrNodeFinCurcy'

	 
	 
update ampata_sys_node
set id2 = id2_calc 
where
	dtype = 'enty_UsrNodeFinTxactItm'
and 
	id2 is null

order by 
	t.id2
;

	
select * 
from ampata_sys_node
where
	dtype = 'enty_UsrNodeFinAcct'
and 
	id2 is null 
