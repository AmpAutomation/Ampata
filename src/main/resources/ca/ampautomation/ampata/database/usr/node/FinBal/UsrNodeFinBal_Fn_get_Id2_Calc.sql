drop function if exists Usr_Fin_Bal_Fn_get_Id2_Calc;

create function Usr_Fin_Bal_Fn_get_Id2_Calc(balset text, acct text, dept text, date1 date, date2 date) returns text
AS $$

# date, time and objects are passed as string
from datetime import datetime, date


s_ret = ""

if balset is None or balset == "":
	if date1 is None or date1 == "" or date2 is None or date2 == "" or acct is None or acct == "":
		s_ret = "?????"
	else:
		dt1 = datetime.strptime(date1,"%Y-%m-%d")
		dt2 = datetime.strptime(date2,"%Y-%m-%d")
		if dept is None:
			s_ret = f"B={dt1:%Y-%m-%d};E={dt2:%Y-%m-%d};D=;A={acct}"
		else:
			s_ret = f"B={dt1:%Y-%m-%d};E={dt2:%Y-%m-%d};D={dept};A={acct}"
else:
	if acct is None or acct == "":
		s_ret = "?????"
	else:
		s_ret = f"{balset};A={acct}"


return s_ret

$$ LANGUAGE plpython3u
;




select Usr_Fin_Bal_Fn_get_Id2_Calc(null,null,null,null,null) as id2
UNION all
SELECT Usr_Fin_Bal_Fn_get_Id2_Calc('','/A/Mark/RBC/Chk','Gen', '2021-01-01'::date, '2021-12-31'::date) as id2
UNION all
SELECT Usr_Fin_Bal_Fn_get_Id2_Calc('','/A/Mark/RBC/Chk','', '2021-01-01'::date, '2021-12-31'::date) as id2
UNION all
SELECT Usr_Fin_Bal_Fn_get_Id2_Calc('R=Y2016/_;S=All;D=','/A/Mark/RBC/Chk','Gen', null, null) as id2
UNION all
SELECT Usr_Fin_Bal_Fn_get_Id2_Calc('R=Y2016/_;S=All;D=Gen','/A/Mark/RBC/Chk',null, null, null) as id2
;
