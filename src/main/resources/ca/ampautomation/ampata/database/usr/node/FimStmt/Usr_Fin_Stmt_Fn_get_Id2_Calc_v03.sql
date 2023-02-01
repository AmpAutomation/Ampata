drop function if exists Fin_Stmt_Fn_get_Id2_Calc;

create function Fin_Stmt_Fn_get_Id2_Calc(acct text, date1 date) returns text
AS $$

# date, time and objects are passed as string
from datetime import datetime, date


s_ret = ""

if acct is None or acct == "" or date1 is None or date1 == "":
	s_ret = "?????"
else:
	dt1 = datetime.strptime(date1,"%Y-%m-%d")
	s_ret = f"{acct}/D{dt1:%Y%m%d}"


return s_ret

$$ LANGUAGE plpython3u
;




select Fin_Stmt_Fn_get_Id2_Calc('',null) as id1
UNION all
SELECT Fin_Stmt_Fn_get_Id2_Calc('', '2021-01-01'::date) as id2
UNION all
SELECT Fin_Stmt_Fn_get_Id2_Calc('/A/Mark/RBC/Chk', '2021-01-01'::date) as id2
;
