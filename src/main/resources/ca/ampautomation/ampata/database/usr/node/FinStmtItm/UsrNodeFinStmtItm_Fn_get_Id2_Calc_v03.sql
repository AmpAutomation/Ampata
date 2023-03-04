drop function if exists Fin_Stmt_Itm_Fn_get_Id2_Calc;

create function Fin_Stmt_Itm_Fn_get_Id2_Calc(t_acct text, date1 date, f_amt float, i_x int) returns text
AS $$

# date, time and objects are passed as string
from datetime import datetime, date

s_ret = ""

if t_acct is None or t_acct == "" or date1 is None or date1 == "" or f_amt is None or f_amt == "":
    s_ret = "?????"
else:
    dt1 = datetime.strptime(date1,"%Y-%m-%d")
    s_ret = f"{t_acct}/D{dt1:%Y%m%d}"

    if i_x is None:
        s_ret += f"/X00"
    else:
        s_ret += f"/X{i_x:02d}"

    if f_amt >= 0:
        s_ret += f"/A+{f_amt:.2f}"
    else:
        s_ret += f"/A{f_amt:.2f}"

return s_ret


$$ LANGUAGE plpython3u
;




select Fin_Stmt_Itm_Fn_get_Id2_Calc('/Mark/A//RBC/Chk', '2021-01-01'::date,25.2,1) as id1
UNION all
select Fin_Stmt_Itm_Fn_get_Id2_Calc('/Mark/A//RBC/Chk', '2021-01-01'::date,-25.0,1) as id2
UNION all
select Fin_Stmt_Itm_Fn_get_Id2_Calc('/Mark/A//RBC/Chk', '2021-01-01'::date,25,null) as id3
UNION all
SELECT Fin_Stmt_Itm_Fn_get_Id2_Calc('', '2021-01-01'::date,25,0) as id4
UNION all
SELECT Fin_Stmt_Itm_Fn_get_Id2_Calc('/A/Mark/RBC/Chk', '2021-01-01'::date,25,0) as id5
UNION all
select Fin_Stmt_Itm_Fn_get_Id2_Calc(null,null,null,null) as id6
;
