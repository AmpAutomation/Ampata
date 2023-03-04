/*
select set_config('client_min_messages','debug5',false)
*/

/*
drop function if exists Usr_Gen_Agent_Fn_get_Id2_Calc;

create function Usr_Gen_Agent_Fn_get_Id2_Calc(
	 type1 text
	,name1 text
	,abrv text
	,name_frst text
	,name_last text 
	,gen_agent1__name_frst text
	,gen_agent1__name_last text
	,gen_agent2__name_frst text
	,gen_agent2__name_last text
	)

returns text
AS $$

plpy.debug(f"call Usr_Gen_Agent_Fn_get_Id2_Calc( type1:{type1}"
		+f", name1:{name1}, abrv:{abrv}, "
		+f", name_frst:{name_frst}, name_last:{name_last}"
		+f", gen_agent1__name_frst:{gen_agent1__name_frst}, gen_agent1__name_last:{gen_agent1__name_last}"
		+f", gen_agent2__name_frst:{gen_agent2__name_frst}, gen_agent2__name_last:{gen_agent2__name_last}"
		+f")"
		)

s_ret = ""

# Empty
if type1 == "":
    plpy.debug(f"call Usr_Gen_Agent_Fn_get_Id2_Calc() --- Empty")
    s_ret = ""

# Organization
elif type1 == "/GenAgent/O":
    plpy.debug(f"call Usr_Gen_Agent_Fn_get_Id2_Calc() --- type1='/GenAgent/O'")
    if abrv is None or abrv == "" :
        s_ret = "O-" + name1
    else:
        s_ret = "O-" + abrv
    

# Person
elif type1 == "/GenAgent/P":
    plpy.debug(f"call Usr_Gen_Agent_Fn_get_Id2_Calc() --- type1='/GenAgent/P'")
    s_ret = "P-" + name_frst + " " + name_last

# Couple
elif type1 == "/GenAgent/C":
    plpy.debug(f"call Usr_Gen_Agent_Fn_get_Id2_Calc() --- type1='/GenAgent/C'")
    s_ret = "C-" + gen_agent1__name_frst[:1] + "&" + gen_agent2__name_frst[:1] + " " +gen_agent1__name_last

else:
    s_ret = ""

plpy.debug(f"call Usr_Gen_Agent_Fn_get_Id2_Calc() --- s_ret:'{s_ret}'")
return s_ret

$$ LANGUAGE plpython3u;

*/

drop function if exists Usr_Gen_Agent_Fn_get_Id2_Calc;

create function Usr_Gen_Agent_Fn_get_Id2_Calc(
	 type1 text
	,name1 text
	,abrv text
	,name_frst text
	,name_last text 
	,gen_agent1__name_frst text
	,gen_agent1__name_last text
	,gen_agent2__name_frst text
	,gen_agent2__name_last text
	)
	returns text
as
$$
declare
-- variable declaration
s_ret text;

begin
	select 
		case
			when type1 is null or type1 = '' then ''
			when type1 = '/GenAgent/O' then 
				case
					when abrv is null or abrv = '' then 'O-' || coalesce(name1,'')
					else 'O-' || abrv
				end
			when type1 = '/GenAgent/P'  then 'P-' || coalesce(name_frst,'') || ' ' || coalesce(name_last,'')
			when type1 = '/GenAgent/C'  then 'C-' || substr(coalesce(gen_agent1__name_frst,''),1,1) || '&' || substr(coalesce(gen_agent2__name_frst,''),1,1) || ' ' || coalesce(gen_agent1__name_last,'')
			else ''
		end

	into s_ret;

	return s_ret;
end
$$ language plpgsql;


/*
select 
	  Usr_Gen_Agent_Fn_get_Id2_Calc('/GenAgent/O','2','MLA','4','5','6','7','8','9') id1
	 ,Usr_Gen_Agent_Fn_get_Id2_Calc('/GenAgent/P','2','3','Mark','Sluser','6','7','8','9') id1
	 ,Usr_Gen_Agent_Fn_get_Id2_Calc('/GenAgent/C','2','3','4','5','Mark','Sluser','Elisa','Sluser') id1
;
*/

