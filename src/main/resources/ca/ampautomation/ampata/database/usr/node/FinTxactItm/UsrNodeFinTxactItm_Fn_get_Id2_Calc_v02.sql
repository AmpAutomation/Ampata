drop function if exists Fin_Txact_Itm_Fn_get_Id2_Calc;

create function Fin_Txact_Itm_Fn_get_Id2_Calc(date1 date, time1 time, i_x int, i_y int, i_z int) returns text
AS $$

# date, time and objects are passed as string
from datetime import datetime, date


s_ret = ""

if date1 is None or date1 == "":
	s_ret = ""
else:
	if time1 is None or time1 == "":
		sdt1 = date1 + "T" + "00:00:00"
	else:
		sdt1 = date1 + "T" + time1
	
	dt1 = datetime.strptime(sdt1,"%Y-%m-%dT%H:%M:%S")
	s_ret = f"/D{dt1:%Y%m%d/T%H%M}"
	
	if i_x is None:
		s_ret += f"/X00"
	else:
		s_ret += f"/X{i_x:02d}"

	if i_y is None:
		s_ret += f"/Y00"
	else:
		s_ret += f"/Y{i_y:02d}"

	if i_z is None:
		s_ret += f"/Z00"
	else:
		s_ret += f"/Z{i_z:02d}"


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
	 Fin_Txact_Itm_Fn_get_Id2_Calc('2021-01-01'::date,'04:05:06'::time,1,1,1) as id1
	,Fin_Txact_Itm_Fn_get_Id2_Calc('2021-01-01'::date,NULL::time,1,1,1) as id2
	,Fin_Txact_Itm_Fn_get_Id2_Calc('2021-01-01'::date,NULL::time,null,null,null) as id2
;
