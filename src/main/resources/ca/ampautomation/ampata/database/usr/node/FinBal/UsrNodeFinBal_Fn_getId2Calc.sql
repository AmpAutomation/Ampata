drop function if exists UsrNodeFinBal_Fn_getId2Calc;

create function UsrNodeFinBal_Fn_getId2Calc(balset text, acct text, dept text, tst1 timestamp, tst2 timestamp) returns text
AS $$

# datetime, date, time and objects are passed as string
from datetime import datetime, timezone

plpy.debug(f"call UsrNodeFinBal_Fn_getId2Calc("
	+f"balset:{balset}"
	+f", acct:{acct}"
	+f", dept:{dept}"
	+f", tst1:{tst1}"
	+f", tst2:{tst2}"
	+f")"
	)

s_ret = ""

if balset is None or balset == "":
	if tst1 is None or tst1 == "" or tst2 is None or tst2 == "" or acct is None or acct == "":
		s_ret = "?????"
	else:
		ts1 = datetime.strptime(tst1,"%Y-%m-%d %H:%M:%S")
		ts2 = datetime.strptime(tst2,"%Y-%m-%d %H:%M:%S")
		if dept is None:
			s_ret = f"B={ts1:%Y-%m-%d};E={ts2:%Y-%m-%d};D=;A={acct}"
		else:
			s_ret = f"B={ts1:%Y-%m-%d};E={ts2:%Y-%m-%d};D={dept};A={acct}"
else:
	if acct is None or acct == "":
		s_ret = "?????"
	else:
		s_ret = f"{balset};A={acct}"


return s_ret

$$ LANGUAGE plpython3u
;




select UsrNodeFinBal_Fn_getId2Calc(null,null,null,null,null) as id2
UNION all
SELECT UsrNodeFinBal_Fn_getId2Calc('','A/Mark/RBC/Chk','Gen', '2021-01-01'::date, '2021-12-31'::date) as id2
UNION all
SELECT UsrNodeFinBal_Fn_getId2Calc('','A/Mark/RBC/Chk','', '2021-01-01'::date, '2021-12-31'::date) as id2
UNION all
SELECT UsrNodeFinBal_Fn_getId2Calc('R=Y2016/_;S=All;D=','A/Mark/RBC/Chk','Gen', null, null) as id2
UNION all
SELECT UsrNodeFinBal_Fn_getId2Calc('R=Y2016/_;S=All;D=Gen','A/Mark/RBC/Chk',null, null, null) as id2
;
