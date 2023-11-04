drop function if exists UsrNodeFinBal_Fn_getId2Calc;

create function UsrNodeFinBal_Fn_getId2Calc(name1 text, dept text) returns text
AS $$

plpy.debug(f"call UsrNodeFinBal_Fn_getId2Calc("
	+f"name1:{name1}"
	+f", dept:{dept}"
	+f")"
	)


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




select UsrNodeFinBal_Fn_getId2Calc(null,null) as id2
UNION all
SELECT UsrNodeFinBal_Fn_getId2Calc('R=Y2021/_;S=All','Gen') as id2
UNION all
SELECT UsrNodeFinBal_Fn_getId2Calc('R=Y2021/_;S=All',null) as id2
;
