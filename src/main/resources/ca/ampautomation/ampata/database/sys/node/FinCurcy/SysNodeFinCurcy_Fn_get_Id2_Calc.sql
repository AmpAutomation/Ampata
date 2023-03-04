drop function if exists Sys_Fin_Curcy_Fn_get_Id2_Calc;

create function Sys_Fin_Curcy_Fn_get_Id2_Calc(name1 text) returns text
AS $$

plpy.debug(f"call Sys_Fin_Curcy_Fn_get_Id2_Calc(name1:{name1}"
        +f")"
        )

s_ret = ""
if name1 is None or name1 == "":
	s_ret = "?????"
else:
	s_ret = "/" + name1

return s_ret


$$ LANGUAGE plpython3u
;



select Sys_Fin_Curcy_Fn_get_Id2_Calc('name1') as id2
UNION all
SELECT Sys_Fin_Curcy_Fn_get_Id2_Calc(NULL) as id2

	;
