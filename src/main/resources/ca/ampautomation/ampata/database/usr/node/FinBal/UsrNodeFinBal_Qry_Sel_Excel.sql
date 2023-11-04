select
 id
,id2

/* 
,id2_calc
,id2_cmp
*/

,id2_dup

/*
,class_name
,parent1__id
,parent1__id2
*/
,sort_idx
,sort_key

,type1__id
,type1__id2
,inst1
,name1

/*
,name1_gen_pat1__id
,name1_gen_pat1__id2
*/

,name2
,abrv
,code
,desc1

/*
,desc1_gen_pat1__id
,desc1_gen_pat1__id2
,desc1_fin_txact_itm1__id
,desc1_fin_txact_itm1__id2
*/

,status
,status_calc
,note

,ts1_el_ts
,ts1_el_dt
,ts1_el_dt_yr
,ts1_el_dt_qtr
,ts1_el_dt_mon
,ts1_el_dt_mon2
,ts1_el_dt_day
,ts1_el_tm
,ts1_el_tm_hr
,ts1_el_tm_min

,ts2_el_ts
,ts2_el_dt
,ts2_el_dt_yr
,ts2_el_dt_qtr
,ts2_el_dt_mon
,ts2_el_dt_mon2
,ts2_el_dt_day
,ts2_el_tm
,ts2_el_tm_hr
,ts2_el_tm_min


,fin_bal1__id
,fin_bal1__id2

,fin_bal_set1__id
,fin_bal_set1__id2

,fin_acct1__id
,fin_acct1__id2
/*
,fin_acct1__type1__id2
*/
,fin_dept1__id
,fin_dept1__id2

/*
,fin_tax_lne1__id
,fin_tax_lne1__id2
,fin_tax_lne1__code
,fin_tax_lne1__type1__id2
*/

,sys_node_fin_curcy1__id
,sys_node_fin_curcy1__id2
,amt_beg_bal
,amt_beg_bal_calc
,amt_debt
,amt_cred
,amt_net
,amt_end_bal
,amt_end_bal_calc


,version
,created_by
,created_date
,last_modified_by
,last_modified_date
,deleted_by
,deleted_date


from ampata_usr_node

where tenant ='Mark' 
and dtype = 'enty_UsrNodeFinBal'
and deleted_by is null
and fin_bal_set1__id2 like '%2020%'

order by sort_key
