select
 id
,id2

/* 
,id2_calc
,id2_cmp
*/

,id2_dup

,parent1__id
,parent1__id2
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
,note

,version
,created_by
,created_date
,last_modified_by
,last_modified_date
,deleted_by
,deleted_date


from ampata_sys_node

where dtype = 'enty_SysNodeFinCurcy'
and deleted_by is null
order by id2
