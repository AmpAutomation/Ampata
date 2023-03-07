
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
,t.dtype
,t.id2
,desc1
from ampata_usr_node_type t

where t.dtype = 'enty_UsrItemGenFmla'





--ampata_usr_node_type
--dtype

update ampata_usr_item_type t set dtype = 'enty_UsrItemBaseType' where dtype = 'enty_UsrBaseItemType';


update ampata_usr_node t set dtype = 'enty_UsrNodeFinAcct' where dtype = 'enty_UsrItemFinAcct';
update ampata_usr_node t set dtype = 'enty_UsrNodeFinDept' where dtype = 'enty_UsrItemFinDept';

update ampata_usr_node t set dtype = 'enty_UsrNodeFinBal' where dtype = 'enty_UsrItemFinBal';
update ampata_usr_node t set dtype = 'enty_UsrNodeFinBalSet' where dtype = 'enty_UsrItemFinBalSet';

update ampata_usr_node t set dtype = 'enty_UsrNodeFinStmt' where dtype = 'enty_UsrItemFinStmt';
update ampata_usr_node t set dtype = 'enty_UsrNodeFinStmtItm' where dtype = 'enty_UsrItemFinStmtItm';

update ampata_usr_node t set dtype = 'enty_UsrNodeFinTaxLne' where dtype = 'enty_UsrItemFinTaxLne';

update ampata_usr_node t set dtype = 'enty_UsrNodeFinTxact' where dtype = 'enty_UsrItemFinTxact';
update ampata_usr_node t set dtype = 'enty_UsrNodeFinTxactItm' where dtype = 'enty_UsrItemFinTxactItm';
update ampata_usr_node t set dtype = 'enty_UsrNodeFinTxactSet' where dtype = 'enty_UsrItemFinTxactSet';


update ampata_usr_node t set dtype = 'enty_UsrNodeGenAgent' where dtype = 'enty_UsrItemGenAgent';
update ampata_usr_node t set dtype = 'enty_UsrNodeGenBasic' where dtype = 'enty_UsrItemGenBasic';
update ampata_usr_node t set dtype = 'enty_UsrNodeGenChan' where dtype = 'enty_UsrItemGenChan';
update ampata_usr_node t set dtype = 'enty_UsrNodeGenDocFrg' where dtype = 'enty_UsrItemGenDocFrg';
update ampata_usr_node t set dtype = 'enty_UsrNodeGenDocVer' where dtype = 'enty_UsrItemGenDocVer';
update ampata_usr_node t set dtype = 'enty_UsrNodeGenFile' where dtype = 'enty_UsrItemGenFile';

update ampata_usr_node t set dtype = 'enty_UsrNodeTngyEquip' where dtype = 'enty_UsrItemTngyEquip';



--ampata_usr_node_type
--dtype

update ampata_usr_node_type t set dtype = 'enty_UsrNodeBaseType' where dtype = 'enty_UsrNodeType';


update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinAcctType' where dtype = 'enty_UsrItemFinAcctType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinDeptType' where dtype = 'enty_UsrItemFinDeptType';

update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinBalType' where dtype = 'enty_UsrItemFinBalType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinBalSetType' where dtype = 'enty_UsrItemFinBalSetType';

update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinStmtType' where dtype = 'enty_UsrItemFinStmtType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinStmtItmType' where dtype = 'enty_UsrItemFinStmtItmType';

update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinTaxLneType' where dtype = 'enty_UsrItemFinTaxLneType';

update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinTxactType' where dtype = 'enty_UsrItemFinTxactType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinTxactItmType' where dtype = 'enty_UsrItemFinTxactItmType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeFinTxactSetType' where dtype = 'enty_UsrItemFinTxactSetType';


update ampata_usr_node_type t set dtype = 'enty_UsrNodeGenAgentType' where dtype = 'enty_UsrItemGenAgentType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeGenBasicType' where dtype = 'enty_UsrItemGenBasicType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeGenChanType' where dtype = 'enty_UsrItemGenChanType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeGenDocFrgType' where dtype = 'enty_UsrItemGenDocFrgType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeGenDocVerType' where dtype = 'enty_UsrItemGenDocVerType';
update ampata_usr_node_type t set dtype = 'enty_UsrNodeGenFileType' where dtype = 'enty_UsrItemGenFileType';

update ampata_usr_node_type t set dtype = 'enty_UsrNodeTngyEquipType' where dtype = 'enty_UsrItemTngyEquipType';

--dtype
update ampata_usr_node_type t set class_name = substring(t.dtype from 6);
