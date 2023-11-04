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
*/
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

,note


,version
,created_by
,created_date
,last_modified_by
,last_modified_date
,deleted_by
,deleted_date


from ampata_usr_node

where tenant ='Mark' 
and dtype ='enty_UsrNodeFinAcct'
and deleted_by is null 
order by sort_key