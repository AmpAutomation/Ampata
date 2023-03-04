drop function if exists Fin_Txact_Fn_get_Id2_Calc;

create function Fin_Txact_Fn_get_Id2_Calc(ts1 timestamp, i_x int, i_y int) returns text
AS $$

# date, time and objects are passed as string
from datetime import datetime, date

plpy.debug(f"call Fin_Txact_Itm_Fn_get_Id2_Calc( ts1:{ts1}"
        +f", i_x:{i_x}, i_y:{i_y} "
        +f")"
        )

s_ret = ""

if ts1 is None or ts1 == "":
    s_ret = ""
else:
    dt1 = datetime.strptime(ts1,"%Y-%m-%d %H:%M:%S")
    s_ret = f"/D{dt1:%Y%m%d/T%H%M}"

    if i_x is None:
        s_ret += f"/X00"
    else:
        s_ret += f"/X{i_x:02d}"

    if i_y is None:
        s_ret += f"/Y00"
    else:
        s_ret += f"/Y{i_y:02d}"


return s_ret

$$ LANGUAGE plpython3u
;






/*

Time Zones
----------

Mountain Standard Time (MST)
07:00 behind Coordinated Universal Time (UTC)
UTC Offset: UTC -7


Mountain Daylight Time (MDT)
06:00 behind Coordinated Universal Time (UTC)
UTC Offset: UTC -6


Mountain Time
The term Mountain Time (MT) is often used to denote the local time in areas observing either Mountain Standard Time (MST) or Mountain Daylight Time (MDT).
In other words, in locations observing Daylight Saving Time (DST) during part of the year, Mountain Time is not static but switches between MST and MDT.


MDT or MST?
Mountain Daylight Time (MDT) is a North American time zone 
it is in use from the second Sunday in March to the first Sunday in November during Daylight Saving Time (DST).
Mountain Standard Time (MST) is used during the remainder of the year.

*/

select 
	 Fin_Txact_Fn_get_Id2_Calc(TO_TIMESTAMP('2021-01-01 04:05:06', 'YYYY-MM-DD HH24:MI:ss')::timestamp,1,1) as id1
	,Fin_Txact_Fn_get_Id2_Calc(TO_TIMESTAMP('2021-01-01 04:05:06', 'YYYY-MM-DD HH24:MI:ss')::timestamp,1,null) as id2
	,Fin_Txact_Fn_get_Id2_Calc(TO_TIMESTAMP('2021-01-01 04:05:06', 'YYYY-MM-DD HH24:MI:ss')::timestamp,null,null) as id2
;
