drop function if exists Usr_Fin_Bal_Set_Fn_get_Id2_Calc;

create function Usr_Fin_Bal_Set_Fn_get_Id2_Calc(name1 text, dept text) returns text
AS $$

# date, time and objects are passed as string
from datetime import datetime, date


s_ret = ""

if name1 is None or name1 == "":
	s_ret = "?????"
else:
	if dept is None:
		s_ret = f"{name1};D="
	else:
		s_ret = f"{name1};D={dept}"


return s_ret

$$ LANGUAGE plpython3u
;




select Usr_Fin_Bal_Set_Fn_get_Id2_Calc(null,null) as id2
UNION all
SELECT Usr_Fin_Bal_Set_Fn_get_Id2_Calc('R=Y2021/_;S=All','Gen') as id2
UNION all
SELECT Usr_Fin_Bal_Set_Fn_get_Id2_Calc('R=Y2021/_;S=All',null) as id2
;
