drop function if exists UsrNodeFinTxact_Fn_getId2Calc;

create function UsrNodeFinTxact_Fn_getId2Calc(tst1 timestamp, i1 int, i2 int) returns text
AS $$

# date, time and objects are passed as string
from datetime import datetime, timezone

plpy.debug(f"call UsrNodeFinTxact_Fn_getId2Calc(tst1:{tst1}"
        +f", i1:{i1}, i2:{i2} "
        +f")"
        )

s_ret = ""

if tst1 is None or tst1 == "":
    s_ret = ""
else:
    ts1 = datetime.strptime(tst1,"%Y-%m-%d %H:%M:%S")
    s_ret = f"D{ts1:%Y-%m-%d}"

    if i1 is None:
        s_ret += f"/X00"
    else:
        s_ret += f"/X{i1:02d}"

    if i2 is None:
        s_ret += f"/Y00"
    else:
        s_ret += f"/Y{i2:02d}"


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
	 UsrNodeFinTxact_Fn_getId2Calc(TO_DATE('2021-01-01', 'YYYY-MM-DD')::date,1,1) as id1
	,UsrNodeFinTxact_Fn_getId2Calc(TO_DATE('2021-01-01', 'YYYY-MM-DD')::date,1,null) as id2
	,UsrNodeFinTxact_Fn_getId2Calc(TO_DATE('2021-01-01', 'YYYY-MM-DD')::date,null,null) as id2
;
