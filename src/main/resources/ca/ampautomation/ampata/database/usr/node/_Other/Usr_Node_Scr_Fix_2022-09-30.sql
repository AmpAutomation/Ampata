


drop procedure if exists Gen_Node_Pr_Upd3;
drop procedure if exists Gen_Node_Pr_Upd2;
drop procedure if exists Gen_Node_Pr_Upd;

create procedure Gen_Node_Pr_Upd()
language 'plpgsql'	
as $BODY$
declare

begin
-- Stored procedures are atomic and are executed as a transaction


update ampata_sys_node t
set type1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.type1__id = t1.id
;


update ampata_sys_node t
set parent1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.parent1__id = t1.id
;


update ampata_sys_node t
set name1_gen_pat1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.name1_gen_pat1__id  = t1.id
;


update ampata_sys_node t
set gen_doc_ver1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_doc_ver1__id  = t1.id
;


update ampata_sys_node t
set gen_file1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_file1__id  = t1.id
;

update ampata_sys_node t
set gen_tag1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_tag1__id  = t1.id
;

update ampata_sys_node t
set gen_tag2__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_tag2__id  = t1.id
;

update ampata_sys_node t
set gen_tag3__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_tag3__id  = t1.id
;

update ampata_sys_node t
set gen_tag4__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_tag4__id  = t1.id
;

update ampata_sys_node t
set gen_agent1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_agent1__id  = t1.id
;

update ampata_sys_node t
set gen_agent2__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_agent2__id  = t1.id
;

update ampata_sys_node t
set gen_chan1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.gen_chan1__id  = t1.id
;



--fin_stmt1__id
update ampata_sys_node t
set fin_stmt1__id2 = t1.id2 
from ampata_sys_node t1
where
	t.fin_stmt1__id  = t1.id
;


--fin_acct1__id
update ampata_sys_node t
set  fin_acct1__id2 = t1.id2
	,fin_acct1__type1__id2 = t1.type1__id2
from ampata_sys_node t1
where
	t.fin_acct1__id  = t1.id
;


--fin_dept1__id
update ampata_sys_node t
set  fin_dept1__id2 = t1.id2
from ampata_sys_node t1
where
	t.fin_dept1__id = t1.id
;

--fin_tax_lne1__id
update ampata_sys_node t
set  fin_tax_lne1__id2 = t1.id2
	,fin_tax_lne1__type1__id2 = t1.type1__id2
from ampata_sys_node t1
where
	t.fin_tax_lne1__id  = t1.id
;


--amt_fin_fmla1__id2
update ampata_sys_node t
set  amt_fin_fmla1__id2 = t1.id2
from ampata_sys_entity t1
where
	t.amt_fin_fmla1__id  = t1.id
;

--fin_how1__id
update ampata_sys_node t
set  fin_how1__id2 = t1.id2
from ampata_sys_entity t1
where
	t.fin_how1__id  = t1.id
;

--fin_what1__id
update ampata_sys_node t
set  fin_what1__id2 = t1.id2
from ampata_sys_entity t1
where
	t.fin_what1__id  = t1.id
;

--fin_why1__id
update ampata_sys_node t
set  fin_why1__id2 = t1.id2
from ampata_sys_entity t1
where
	t.fin_why1__id  = t1.id
;


--fin_curcy1__id
update ampata_sys_node t
set  fin_curcy1__id2 = t1.id2
from ampata_sys_node t1
where
	t.fin_curcy1__id  = t1.id
;

--fin_txact_itm1__id2
update ampata_sys_node t
set  amt_fin_txact_itm1__id2 = t1.id2
from ampata_sys_node t1
where
	t.amt_fin_txact_itm1__id  = t1.id
;



--fin_txset1__id
update ampata_sys_node t
set  fin_txset1__id2 = t1.id2 
	,fin_txset1__type1__id2 = t1.type1__id2
--	,fin_txset1__gen_chan1__id2 = t1.gen_chan_id2 
	,fin_txset1__how1__id2 = t1.fin_how1__id2
	,fin_txset1__what1__id2 = t1.fin_what1__id2
	,fin_txset1__why1__id2 = t1.fin_why1__id2
from ampata_sys_node t1
where
	t.fin_txset1__id  = t1.id
;

--fin_txact1__id
update ampata_sys_node t
set  fin_txact1__id2 = t1.id2 
	,fin_txact1__type1__id2 = t1.type1__id2
--	,fin_txact1__gen_chan1__id2 = t1.gen_chan_id2 
	,fin_txact1__how1__id2 = t1.fin_how1__id2
	,fin_txact1__what1__id2 = t1.fin_what1__id2
	,fin_txact1__why1__id2 = t1.fin_why1__id2
from ampata_sys_node t1
where
	t.fin_txact1__id  = t1.id
;



end
$BODY$
;



create procedure Gen_Node_Pr_Upd2(inParam1 IN TEXT)
language 'plpgsql'	
as $BODY$
declare
begin
-- Stored procedures are atomic and are executed as a transaction

	call Gen_Node_Pr_Upd();

end
$BODY$
;

create procedure Gen_Node_Pr_Upd3(inParam1 IN VARCHAR, outParam1 OUT VARCHAR)
language 'plpgsql'	
as $BODY$
declare
begin
-- Stored procedures are atomic and are executed as a transaction

	call Gen_Node_Pr_Upd();

end
$BODY$
;


call Gen_Node_Pr_Upd();
