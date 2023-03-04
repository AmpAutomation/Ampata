
--ampata_usr_item
--dtype

update ampata_usr_item t set dtype = 'enty_UsrItemBase' where dtype = 'enty_UsrItem';

update ampata_usr_item t set dtype = 'enty_UsrItemFinHow' where dtype = 'enty_UsrFinHow';
update ampata_usr_item t set dtype = 'enty_UsrItemFinWhat' where dtype = 'enty_UsrFinWhat';
update ampata_usr_item t set dtype = 'enty_UsrItemFinWhy' where dtype = 'enty_UsrFinWhy';
update ampata_usr_item t set dtype = 'enty_UsrItemGenFmla' where dtype = 'enty_UsrFinFmla';

update ampata_usr_item t set dtype = 'enty_UsrItemGenFmla' where dtype = 'enty_UsrGenFmla';
update ampata_usr_item t set dtype = 'enty_UsrItemGenTag' where dtype = 'enty_UsrGenTag';


--ampata_usr_node
--dtype

update ampata_usr_node t set dtype = 'enty_UsrNodeBase' where dtype = 'enty_UsrNode';


update ampata_usr_node t set dtype = 'enty_UsrItemFinAcct' where dtype = 'enty_UsrFinAcct';
update ampata_usr_node t set dtype = 'enty_UsrItemFinDept' where dtype = 'enty_UsrFinDept';

update ampata_usr_node t set dtype = 'enty_UsrItemFinBal' where dtype = 'enty_UsrFinBal';
update ampata_usr_node t set dtype = 'enty_UsrItemFinBalSet' where dtype = 'enty_UsrFinBalSet';

update ampata_usr_node t set dtype = 'enty_UsrItemFinStmt' where dtype = 'enty_UsrFinStmt';
update ampata_usr_node t set dtype = 'enty_UsrItemFinStmtItm' where dtype = 'enty_UsrFinStmtItm';

update ampata_usr_node t set dtype = 'enty_UsrItemFinTaxLne' where dtype = 'enty_UsrFinTaxLne';

update ampata_usr_node t set dtype = 'enty_UsrItemFinTxact' where dtype = 'enty_UsrFinTxact';
update ampata_usr_node t set dtype = 'enty_UsrItemFinTxactItm' where dtype = 'enty_UsrFinTxactItm';
update ampata_usr_node t set dtype = 'enty_UsrItemFinTxactSet' where dtype = 'enty_UsrFinTxactSet';


update ampata_usr_node t set dtype = 'enty_UsrItemGenAgent' where dtype = 'enty_UsrGenAgent';
update ampata_usr_node t set dtype = 'enty_UsrItemGenBasic' where dtype = 'enty_UsrGenBasc';
update ampata_usr_node t set dtype = 'enty_UsrItemGenChan' where dtype = 'enty_UsrGenChan';
update ampata_usr_node t set dtype = 'enty_UsrItemGenDocFrg' where dtype = 'enty_UsrGenDocFrg';
update ampata_usr_node t set dtype = 'enty_UsrItemGenDocVer' where dtype = 'enty_UsrGenDocVer';
update ampata_usr_node t set dtype = 'enty_UsrItemGenFile' where dtype = 'enty_UsrGenFile';

update ampata_usr_node t set dtype = 'enty_UsrItemTngyEquip' where dtype = 'enty_UsrTngyEquip';

--dtype
update ampata_usr_node t set class_name = substring(t.dtype from 6);



select 
t.id
,t.id2
,fmla
,desc1
from ampata_usr_item t
where t.dtype = 'enty_UsrItemGenFmla'

