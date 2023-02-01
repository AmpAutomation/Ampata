
drop function if exists Usr_Gen_Doc_Ver_Fn_get_Id2_Calc;

create function Usr_Gen_Doc_Ver_Fn_get_Id2_Calc(
	 type1 text
	,name1 text
	)
	returns text
language plpgsql
AS
$body$
declare
-- variable declaration
s_ret text;

begin
	select 
		case
			when type1 is null or type1 = '' then ''
			when type1 = '/GenDoc' THEN '/GenDoc'
			else ''
		end

	into s_ret;

	return s_ret;
end
$body$;
