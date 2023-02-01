select agn.id,agn.id2
from ampata_sys_node agn 
where class_name = 'FinAcct'
and agn.id2 = '/L/Mark/RBC/Visa'
;


select 
	 t.id
	,t.id2
	,t.id2_calc
	,t.class_name
	,t.type1__id
	,t.beg1_ts1
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
	t.class_name = 'FinTxactItm'
and 
	t.id2 = '' 


order by 
	t.id2
;


and
	t.beg1_date1 >= '2021-07-02'

select *
from ampata_sys_node t
where
	t.class_name = 'FinTxactItm'	
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
	t.class_name = 'FinCurcy'

	 
	 
update ampata_sys_node
set id2 = id2_calc 
where
	class_name = 'FinTxactItm'
and 
	id2 is null

order by 
	t.id2
;

	
select * 
from ampata_sys_node
where
	class_name = 'FinAcct'
and 
	id2 is null 
