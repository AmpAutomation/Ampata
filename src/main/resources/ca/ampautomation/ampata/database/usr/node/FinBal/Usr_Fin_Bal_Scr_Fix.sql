
UPDATE ampata_sys_node t 
set id2 = t.id2_calc 
where t.class_name = 'FinBal'
and t.deleted_by is null



--set manual values
UPDATE ampata_sys_node t 
set amt_beg_bal = t.amt_beg_bal_calc 
	,amt_debt = t.fin_txact_itms1__amt_debt_sum_calc
	,amt_cred = t.fin_txact_itms1__amt_cred_sum_calc
	,amt_net = case when t3.bal_inc_on_debt 
		then coalesce(t.fin_txact_itms1__amt_debt_sum_calc,0) - coalesce(t.fin_txact_itms1__amt_cred_sum_calc,0) 
		else coalesce(t.fin_txact_itms1__amt_cred_sum_calc,0) - coalesce(t.fin_txact_itms1__amt_debt_sum_calc,0)  
		end
	,amt_end_bal = t.amt_end_bal_calc
from ampata_sys_node t2
, ampata_sys_node_type t3
where t.class_name = 'FinBal'
and t.deleted_by is null
and t.fin_acct1__id = t2.id
and t2.type1__id = t3.id

