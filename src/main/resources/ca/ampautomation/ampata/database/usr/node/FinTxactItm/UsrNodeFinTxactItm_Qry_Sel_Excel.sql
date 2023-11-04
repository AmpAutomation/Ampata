select
 t.id
,t.id2

/* 
,t.tenant
,t.dtype
*/

/* 
,t.id2_calc
,t.id2_cmp
*/

,t.id2_dup

/*
,t.parent1__id
,t.parent1__id2
,t.sort_idx
*/

,t.type1__id
,t.type1__id2
/*
,t.inst
,t.name1
,t.name1_gen_fmla1__id
,t.name1_gen_fmla1__id2
,t.name2
,t.abrv
,t.code
*/
,t.desc1

/*
,t.desc1_fmla1__id
,t.desc1_fmla1__id2
,t.desc1_node1__id
,t.desc1_node1__id2
,t.status
,t.status_calc
*/
,t.note

,t.ts1_el_ts
,t.ts1_el_dt
,t.ts1_el_dt_yr
,t.ts1_el_dt_qtr
,t.ts1_el_dt_mon
,t.ts1_el_dt_mon2
,t.ts1_el_dt_day
,t.ts1_el_tm
,t.ts1_el_tm_hr
,t.ts1_el_tm_min

/*
,t.dt1_el_dt
,t.dt1_el_dt_yr
,t.dt1_el_dt_qtr
,t.dt1_el_dt_mon
,t.dt1_el_dt_mon2
,t.dt1_el_dt_day
,t.dt1_el_tm
,t.dt1_el_tm_hr
,t.dt1_el_tm_min

,t.tm1_el_tm
,t.tm1_el_tm_hr
,t.tm1_el_tm_min

,t.ts2_el_ts
,t.ts2_el_dt
,t.ts2_el_dt_yr
,t.ts2_el_dt_qtr
,t.ts2_el_dt_mon
,t.ts2_el_dt_mon2
,t.ts2_el_dt_day
,t.ts2_el_tm
,t.ts2_el_tm_hr
,t.ts2_el_tm_min


*/

,t.int1
,t.int2
,t.sort_idx
,t.sort_key

/*
,t.gen_chan1__id
,t.gen_chan1__id2
,t.fin_how1__id
,t.fin_how1__id2
,t.what_text1
,t.fin_what1__id
,t.fin_what1__id2
,t.why_text1
,t.fin_why1__id
,t.fin_why1__id2
*/


/*
,t.gen_doc_vers1__id2
,t.gen_doc_ver1__id
,t.gen_doc_ver1__id2
,t.gen_doc_ver1__code
,t.gen_doc_ver1__ver
,t.gen_doc_ver1__name1
,t.gen_file1__id
,t.gen_file1__id2
,t.gen_file1__uri
,t.gen_tags1__id2
,t.gen_tag1__id
,t.gen_tag1__id2
,t.gen_tag2__id
,t.gen_tag2__id2
,t.gen_tag3__id
,t.gen_tag3__id2
,t.gen_tag4__id
,t.gen_tag4__id2
*/


,t.fin_txact_set1__id
,t.fin_txact_set1__id2
,t.fin_txact_set1__type1__id2
,t.fin_txact_set1__desc1
,t.fin_txact_set1__ei1__role
,t.fin_txact_set1__gen_chan1__id2
,t.fin_txact_set1__how1__id2
,t.fin_txact_set1__what_text1
,t.fin_txact_set1__what1__id2
,t.fin_txact_set1__why_text1
,t.fin_txact_set1__why1__id2

,t.fin_txact1__fin_txact_itms1__id2
,t.parent1__id
,t.parent1__id2
,t.parent1__ei1__role

,t.parent1__type1__id2

/*
,t.fin_txact1__gen_chan1__id2
,t.fin_txact1__beg1_date1
,t.fin_txact1__beg1_time1
,t.fin_txact1__how1__id2
,t.fin_txact1__what_text1
,t.fin_txact1__what1__id2
,t.fin_txact1__why_text1
,t.fin_txact1__why1__id2

*/

,t.fin_stmt1__id
,t.fin_stmt1__id2
,t.fin_stmt_itm1__txt1
,t.fin_stmt_itm1__txt2
,t.fin_stmt_itm1__txt3
,t.fin_stmt_itm1__txt4
,t.fin_stmt_itm1__txt_curcy_exch
,t.fin_stmt_itm1__txt_ref_id

,t.fin_acct1__id
,t.fin_acct1__id2
,t.fin_acct1__type1__id2
,t.fin_dept1__id
,t.fin_dept1__id2

/*
,t.fin_tax_lne1__id
,t.fin_tax_lne1__id2
,t.fin_tax_lne1__code
,t.fin_tax_lne1__type1__id2
*/

,t.amt_debt
,t.amt_cred
,t.amt_net
,t.sys_node_fin_curcy1__id
,t.sys_node_fin_curcy1__id2
,t.amt_beg_bal
,t.amt_beg_bal_calc
,t.amt_end_bal
,t.amt_end_bal_calc

,t.amt_calc_gen_fmla1__id
,t.amt_calc_gen_fmla1__id2
,t.amt_calc_fin_txact_itm1__id
,t.amt_calc_fin_txact_itm1__id2
,t.amt_calc_fin_txact_itm1__ei1__rate
,t.amt_calc_fin_txact_itm1__fin_acct1__id2
,t.fin_acct2__id2
,t.amt_calc

/*
,t.fin_txact_set1__gen_doc_vers1__id2
,t.fin_txact_set1__gen_tags1__id2
,t.fin_txact1__gen_doc_vers1__id2
,t.fin_txact1__gen_tags1__id2

,t.fin_txact_set1__fin_accts__id2

,t.fin_txact_itms1__id2
,t.fin_txact_itms1__fin_accdt1__id2
,t.fin_txact_itms1__id_cnt
,t.fin_txact_itms1__debt_sum
,t.fin_txact_itms1__cred_sum
,t.fin_txact_itms1__debt_eq_cred
,t.fin_txacdt1__id2
,t.fin_txacdt1__fin_accdt1__id2
,t.fin_txacdt1__id_cnt

*/
--,t.version
,t.created_by
,t.created_date
,t.last_modified_by
,t.last_modified_date
,t.deleted_by
,t.deleted_date


from ampata_usr_node t

where tenant = 'Mark'
and dtype ='enty_UsrNodeFinTxactItm' 
and deleted_by is null 
and ts1_el_dt_yr = '2017'

order by id2
