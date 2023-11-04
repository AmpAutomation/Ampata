drop function if exists UsrNodeFinStmtItm_Fn_getId2Calc;

create function UsrNodeFinStmtItm_Fn_getId2Calc(t_acct text, tst1 timestamp, f_amt float, i1 int) returns text
AS $$


# datetime, date, time and objects are passed as string
from datetime import datetime, timezone

plpy.debug(f"call UsrNodeFinStmt_Fn_getId2Calc("
	+f"t_acct:{t_acct}"
	+f", tst1:{tst1}"
	+f", f_amt:{f_amt}"
	+f", i1:{i1}"
	+f")"
	)

s_ret = ""

if t_acct is None or t_acct == "" or tst1 is None or tst1 == "" or f_amt is None or f_amt == "":
	s_ret = "?????"
else:
	ts1 = datetime.strptime(tst1,"%Y-%m-%d %H:%M:%S")
	s_ret = f"{t_acct}/D{ts1:%Y-%m-%d}"

	if i1 is None:
		s_ret += f"/X00"
	else:
		s_ret += f"/X{i1:02d}"

	if f_amt >= 0:
		s_ret += f"/A+{f_amt:.2f}"
	else:
		s_ret += f"/A{f_amt:.2f}"

return s_ret


$$ LANGUAGE plpython3u
;




select UsrNodeFinStmtItm_Fn_getId2Calc('A/RBC/Chk', '2021-01-01'::date,25.2,1) as id1
UNION all
select UsrNodeFinStmtItm_Fn_getId2Calc('A/RBC/Chk', '2021-01-01'::date,-25.0,1) as id2
UNION all
select UsrNodeFinStmtItm_Fn_getId2Calc('A/RBC/Chk', '2021-01-01'::date,25,null) as id3
UNION all
SELECT UsrNodeFinStmtItm_Fn_getId2Calc('', '2021-01-01'::date,25,0) as id4
UNION all
SELECT UsrNodeFinStmtItm_Fn_getId2Calc('A/RBC/Chk', '2021-01-01'::date,25,0) as id5
UNION all
select UsrNodeFinStmtItm_Fn_getId2Calc(null,null,null,null) as id6
;
