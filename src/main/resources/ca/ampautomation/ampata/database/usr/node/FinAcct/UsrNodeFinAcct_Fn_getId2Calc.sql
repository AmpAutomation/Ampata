drop function if exists UsrNodeFinAcct_Fn_getId2Calc;

create function UsrNodeFinAcct_Fn_getId2Calc(name1 text, parent_id2 text) returns text
AS $$

plpy.debug(f"call UsrNodeFinAcct_Fn_getId2Calc(name1:{name1}"
        +f", parent_id2:{parent_id2}"
        +f")"
        )

s_ret = ""

if parent_id2 is None or parent_id2 == "":
    s_ret = name1
else:
    s_ret = parent_id2 + "/" + name1

return s_ret


$$ LANGUAGE plpython3u
;



select 
	 UsrNodeFinAcct_Fn_getId2Calc('name1', 'parent') as id1
	,UsrNodeFinAcct_Fn_getId2Calc('name1',NULL) as id2
;
