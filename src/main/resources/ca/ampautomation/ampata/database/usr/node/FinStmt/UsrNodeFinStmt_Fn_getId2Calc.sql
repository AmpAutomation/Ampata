drop function if exists UsrNodeFinStmt_Fn_getId2Calc;

create function UsrNodeFinStmt_Fn_getId2Calc(t_acct text, tst1 timestamp) returns text
AS $$


# datetime, date, time and objects are passed as string
from datetime import datetime, timezone

plpy.debug(f"call UsrNodeFinStmt_Fn_getId2Calc("
	+f"t_acct:{t_acct}"
	+f", tst1:{tst1}"
	+f")"
	)


s_ret = ""

if t_acct is None or t_acct == "" or tst1 is None or tst1 == "" :
	s_ret = "?????"
else:
	ts1 = datetime.strptime(tst1,"%Y-%m-%d %H:%M:%S")
	s_ret = f"{t_acct}/D{ts1:%Y-%m-%d}"


return s_ret

$$ LANGUAGE plpython3u
;




select UsrNodeFinStmt_Fn_getId2Calc('',null) as id1
UNION all
SELECT UsrNodeFinStmt_Fn_getId2Calc('', '2021-01-01'::date) as id2
UNION all
SELECT UsrNodeFinStmt_Fn_getId2Calc('A/Mark/RBC/Chk', '2021-01-01'::date) as id2
;
