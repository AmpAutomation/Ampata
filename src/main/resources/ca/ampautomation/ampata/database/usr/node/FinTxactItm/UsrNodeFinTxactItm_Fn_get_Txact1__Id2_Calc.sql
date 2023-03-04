drop function if exists Usr_Fin_Txact_Itm_Fn_get_Txact1__Id2_Calc;
create function Usr_Fin_Txact_Itm_Fn_get_Txact1__Id2_Calc(date1 date, time1 time, txact_date1 date, txact_time1 time, i_x int, i_y int) returns text
AS $$

# date, time and objects are passed as string
from datetime import datetime, date
#from pytz import timezone

plpy.debug(f"txact_date1:{txact_date1}, date1: {date1}, txact_time1: {txact_time1}, time1: {time1}, i_x: {i_x}, i_y: {i_y}")

s_ret = ""

if txact_date1 is None or txact_date1 == "" :
	date2 = date1
else:
	date2 = txact_date1

if date2 is None or date2 == "" :
	plpy.debug(f"date2 is None or ''")
	s_ret = ""
else:
	if txact_time1 is None or txact_time1 == "" :
		time2 = time1
	else:
		time2 = txact_time1

	if time2 is None or time2 == "" :
		sdt1 = date2 + "T" + "00:00:00"
	else:
		sdt1 = date2 + "T" + time2
	plpy.debug(f"sdt1:{sdt1}")

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

return s_ret

$$ LANGUAGE plpython3u;


--select Fin_Txact_Itm_Fn_get_TxactId('2021-01-01'::date,'2021-01-01'::date,'04:05:06-07:00'::time,'04:05:06-07:00'::time,1,1);
--select set_config('client_min_messages','debug2',false);

select 
	 Usr_Fin_Txact_Itm_Fn_get_Txact1__Id2_Calc('2021-01-01'::date,'04:05:06-07:00'::time,'2021-01-01'::date,'04:05:06-07:00'::time,1,1) as id1
	,Usr_Fin_Txact_Itm_Fn_get_Txact1__Id2_Calc('2021-01-01'::date,'04:05:06-07:00'::time,NULL::date,NULL::time,1,1) as id2
	,Usr_Fin_Txact_Itm_Fn_get_Txact1__Id2_Calc('2021-01-01'::date,NULL::time,NULL::date,NULL::time,1,1) as id3
;


