drop function if exists Sys_Fin_Curcy_Exch_Rate_Fn_get_Id2_Calc;

create function Sys_Fin_Curcy_Exch_Rate_Fn_get_Id2_Calc(curcy1 text, curcy2 text, date1 date) returns text
LANGUAGE plpython3u
AS 
$body$

# date, time and objects are passed as string
from datetime import datetime, date


s_ret = ""

if curcy1 is None or curcy1 == "" or curcy2 is None or curcy2 == "" or date1 is None or date1 == "":
	s_ret = "?????"
else:
	dt1 = datetime.strptime(date1,"%Y-%m-%d")
	s_ret = f"{curcy1}->{curcy2}/D{dt1:%Y-%m-%d}"


return s_ret

$body$
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

select * 
from (values
	 (Sys_Fin_Curcy_Exch_Rate_Fn_get_Id2_Calc('USD','CAD','2021-01-01'::date))
	,(Sys_Fin_Curcy_Exch_Rate_Fn_get_Id2_Calc('USD','CAD','2021-01-02'::date))

) as x(id2_calc)
UNION all
SELECT Sys_Fin_Curcy_Exch_Rate_Fn_get_Id2_Calc('USD','','2021-01-01'::date) as id2_calc
UNION ALL
SELECT Sys_Fin_Curcy_Exch_Rate_Fn_get_Id2_Calc('','CAD','2021-01-01'::date) as id2_calc
UNION ALL
SELECT Sys_Fin_Curcy_Exch_Rate_Fn_get_Id2_Calc('USD','CAD',NULL) as id2_calc
;
