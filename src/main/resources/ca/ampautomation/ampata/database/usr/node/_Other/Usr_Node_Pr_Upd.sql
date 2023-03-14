


drop procedure if exists Usr_Node_Pr_Upd3;
drop procedure if exists Usr_Node_Pr_Upd2;
drop procedure if exists Usr_Node_Pr_Upd;

create procedure Usr_Node_Pr_Upd()
language 'plpgsql'	
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction


--type1__id2
--use left join to set unmatched type1__id2 to null
raise notice 'Updating type1__id2';
update ampata_usr_node t
set  type1__id2 = t1.type1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.type1__id
	,t2b.id2 as type1__id2
	from ampata_usr_node t2a
	left join ampata_usr_node_type as t2b
		on t2a.type1__id = t2b.id
--	where t2a.dtype = 'enty_UsrNodeGenAgent'
--	and t2a.deleted_by is null
) t1
where t.id = t1.id 
;


--parent1__id2
raise notice 'Updating parent1__id2';
update ampata_usr_node t
set parent1__id2 = t1.id2 
from ampata_usr_node t1
where
	t.parent1__id = t1.id
;


--name1_gendesc1__id2
raise notice 'Updating name1_sys_pat1__id2';
update ampata_usr_node t
set name1_gen_pat1__id2 = t1.id2 
from ampata_usr_item t1
where
	t.name1_gen_pat1__id = t1.id
;

--desc1_gen_pat1__id2
raise notice 'Updating desc1_sys_pat1__id2';
update ampata_usr_node t
set desc1_gen_pat1__id2 = t1.id2 
from ampata_usr_item t1
where
	t.desc1_gen_pat1__id = t1.id
;


--gen_doc_ver1__id2
raise notice 'Updating gen_doc_ver1__id2';
update ampata_usr_node t
set gen_doc_ver1__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_doc_ver1__id  = t1.id
;


--gen_file1__id2
raise notice 'Updating gen_file1__id2';
update ampata_usr_node t
set gen_file1__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_file1__id  = t1.id
;

--gen_tag1__id2
raise notice 'Updating gen_tag1__id2';
update ampata_usr_node t
set gen_tag1__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_tag1__id  = t1.id
;

--gen_tag2__id2
raise notice 'Updating gen_tag2__id2';
update ampata_usr_node t
set gen_tag2__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_tag2__id  = t1.id
;

--gen_tag3__id2
raise notice 'Updating gen_tag3__id2';
update ampata_usr_node t
set gen_tag3__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_tag3__id  = t1.id
;

--gen_tag4__id2
raise notice 'Updating gen_tag4__id2';
update ampata_usr_node t
set gen_tag4__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_tag4__id  = t1.id
;

--gen_agent1__id2
raise notice 'Updating gen_agent1__id2';
update ampata_usr_node t
set gen_agent1__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_agent1__id  = t1.id
;

--gen_agent2__id2
raise notice 'Updating gen_agent2__id2';
update ampata_usr_node t
set gen_agent2__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_agent2__id  = t1.id
;

--gen_chan1__id2
raise notice 'Updating gen_chan1__id2';
update ampata_usr_node t
set gen_chan1__id2 = t1.id2 
from ampata_usr_node t1
where
	t.gen_chan1__id  = t1.id
;



--fin_stmt1__id2
raise notice 'Updating fin_stmt1__id2';
update ampata_usr_node t
set fin_stmt1__id2 = t1.id2 
from ampata_usr_node t1
where
	t.fin_stmt1__id  = t1.id
;


--fin_acct1__id, fin_acct1__type1__id2
raise notice 'Updating fin_acct1__id2, fin_acct1__type1__id2';
update ampata_usr_node t
set  fin_acct1__id2 = t1.id2
	,fin_acct1__type1__id2 = t1.type1__id2
from ampata_usr_node t1
where
	t.fin_acct1__id  = t1.id
;


--fin_dept1__id2
raise notice 'Updating fin_dept1__id2';
update ampata_usr_node t
set  fin_dept1__id2 = t1.id2
from ampata_usr_node t1
where
	t.fin_dept1__id = t1.id
;

--fin_tax_lne1__id, fin_tax_lne1__type1__id2
raise notice 'Updating fin_tax_lne1__id2, fin_tax_lne1__type1__id2';
update ampata_usr_node t
set  fin_tax_lne1__id2 = t1.id2
	,fin_tax_lne1__type1__id2 = t1.type1__id2
from ampata_usr_node t1
where
	t.fin_tax_lne1__id  = t1.id
;


--amt_fin_fmla1__id2
raise notice 'Updating amt_fin_fmla1__id2';
update ampata_usr_node t
set  amt_fin_fmla1__id2 = t1.id2
from ampata_usr_item t1
where
	t.amt_fin_fmla1__id  = t1.id
;

--fin_how1__id2
raise notice 'Updating fin_how1__id2';
update ampata_usr_node t
set  fin_how1__id2 = t1.id2
from ampata_usr_item t1
where
	t.fin_how1__id  = t1.id
;

--fin_what1__id2
raise notice 'Updating fin_what1__id2';
update ampata_usr_node t
set  fin_what1__id2 = t1.id2
from ampata_usr_item t1
where
	t.fin_what1__id  = t1.id
;

--fin_why1__id2
raise notice 'Updating fin_why1__id2';
update ampata_usr_node t
set  fin_why1__id2 = t1.id2
from ampata_usr_item t1
where
	t.fin_why1__id  = t1.id
;


--sys_fin_curcy1__id2
raise notice 'Updating sys_fin_curcy1__id2';
update ampata_usr_node t
set  sys_fin_curcy1__id2 = t1.id2
from ampata_sys_node t1
where
	t.sys_fin_curcy1__id  = t1.id
;

--amt_fin_txact_itm1__id2
raise notice 'Updating amt_fin_txact_itm1__id2';
update ampata_usr_node t
set  amt_fin_txact_itm1__id2 = t1.id2
from ampata_usr_node t1
where
	t.amt_fin_txact_itm1__id  = t1.id
;



--fin_txact_set1__id2..
raise notice 'Updating fin_txact_set1__id2..';
update ampata_usr_node t
set  fin_txact_set1__id2 = t1.id2 
	,fin_txact_set1__type1__id2 = t1.type1__id2
--	,fin_txact_set1__gen_chan1__id2 = t1.gen_chan_id2 
	,fin_txact_set1__how1__id2 = t1.fin_how1__id2
	,fin_txact_set1__what1__id2 = t1.fin_what1__id2
	,fin_txact_set1__why1__id2 = t1.fin_why1__id2
from ampata_usr_node t1
where
	t.fin_txact_set1__id  = t1.id
;

--fin_txact1__id2..
raise notice 'Updating fin_txact1__id2..';
update ampata_usr_node t
set  fin_txact1__id2 = t1.id2 
	,fin_txact1__type1__id2 = t1.type1__id2
--	,fin_txact1__gen_chan1__id2 = t1.gen_chan_id2 
	,fin_txact1__how1__id2 = t1.fin_how1__id2
	,fin_txact1__what1__id2 = t1.fin_what1__id2
	,fin_txact1__why1__id2 = t1.fin_why1__id2
from ampata_usr_node t1
where
	t.fin_txact1__id  = t1.id
;



end
$BODY$
;



create procedure Usr_Node_Pr_Upd2(inParam1 IN TEXT)
language 'plpgsql'	
as $BODY$
declare
begin
-- Stored procedures are atomic and are executed as a transaction

	call Usr_Node_Pr_Upd();

end
$BODY$
;

create procedure Usr_Node_Pr_Upd3(inParam1 IN VARCHAR, outParam1 OUT VARCHAR)
language 'plpgsql'	
as $BODY$
declare
begin
-- Stored procedures are atomic and are executed as a transaction

	call Usr_Node_Pr_Upd();

end
$BODY$
;


call Usr_Node_Pr_Upd();
