
drop procedure if exists Usr_Fin_Bal_Pr_Upd;

create procedure Usr_Fin_Bal_Pr_Upd()
language 'plpgsql'
as $BODY$
declare
	rec_tenant record;
	rec_fin_bal record;
	i_rows_updated_in_tenant int;
	i_rows_updated_in_fin_bal int;
	i_rows_updated int;
	i_tenant int;
	i_fin_bal int;
	vc_fin_bal1__id varchar(255);
	num_amt_beg_bal_calc numeric(19,2);
	i_fin_txact_itms1__id_cnt_calc int;
	num_fin_txact_itms1__amt_debt_sum_calc numeric(19,2);
	num_fin_txact_itms1__amt_cred_sum_calc numeric(19,2);
	num_fin_txact_itms1__amt_net_sum_calc numeric(19,2);
	b_fin_txact_itms1__amt_eq_calc boolean;
	num_fin_txact_itms1__amt_debt_sum_diff numeric(19,2);
	num_fin_txact_itms1__amt_cred_sum_diff numeric(19,2);
	num_fin_txact_itms1__amt_net_sum_diff numeric(19,2);
	num_amt_net numeric(19,2);
	num_amt_end_bal_calc numeric(19,2);
	b_bal_inc_on_debt boolean;
	b_bal_inc_on_cred boolean;

begin
-- Stored procedures are atomic and are executed as a transaction

	
--default values

--amt_debt
raise notice 'Updating amt_debt';
update ampata_usr_node t
set amt_debt = 0
where t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.amt_debt is null
;	

--amt_cred
raise notice 'Updating amt_cred';
update ampata_usr_node t
set amt_cred = 0
where t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.amt_cred is null
;	


--amt_net
raise notice 'Updating amt_net';
update ampata_usr_node t
set amt_net = 0
where t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.amt_net is null
;	


--amt_beg_bal
raise notice 'Updating amt_beg_bal';
update ampata_usr_node t
set amt_beg_bal = 0
where t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.amt_beg_bal is null
;	



--amt_end_bal
raise notice 'Updating amt_end_bal';
update ampata_usr_node t
set amt_end_bal = 0
where t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.amt_end_bal is null
;	


--type1_id2
raise notice 'Updating type1_id2';
update ampata_usr_node t
set 
	 type1__id2 = t2.id2

from ampata_usr_node_type t2
where t.type1__id = t2.id
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t2.class_name = 'UsrNodeFinBal'
;	


--fin_bal_set1__id2
--ts1_el_ts
--ts3_el_ts
--fin_dept1__id
raise notice 'Updating fin_bal_set1__id2, ts1_el_ts, ts3_el_ts, fin_dept1__id';
update ampata_usr_node t
set 
	 fin_bal_set1__id2 = t1.id2
	,ts1_el_ts = t1.ts1_el_ts
	,ts3_el_ts = t1.ts3_el_ts
	,fin_dept1__id = t1.fin_dept1__id

FROM ampata_usr_node t1
WHERE
	t.fin_bal_set1__id = t1.id
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
;	

	
--ts1.., ts3..
raise notice 'Updating ts1_el_dt.., ts3_el_dt..';
update ampata_usr_node t
set 
	ts1_el_dt = t.ts1_el_ts::date
	,ts1_el_dt_yr = date_part('year',t.ts1_el_ts)
	,ts1_el_dt_qtr = date_part('quarter',t.ts1_el_ts)
	,ts1_el_dt_mon =  date_part('Mon',t.ts1_el_ts)
	,ts1_el_dt_mon2 =  to_char(t.ts1_el_ts,'Mon')
	,ts1_el_dt_day = date_part('day',t.ts1_el_ts)
	,ts1_el_tm  = t.ts1_el_ts::time
	,ts1_el_tm_hr  = date_part('hour',t.ts1_el_ts)
	,ts1_el_tm_min  = date_part('minute',t.ts1_el_ts)

	,ts3_el_dt = t.ts3_el_ts::date
	,ts3_el_dt_yr = date_part('year',t.ts3_el_dt)
	,ts3_el_dt_qtr = date_part('quarter',t.ts3_el_ts)
	,ts3_el_dt_mon =  date_part('Mon',t.ts3_el_dt)
	,ts3_el_dt_mon2 =  to_char(t.ts3_el_dt,'Mon')
	,ts3_el_dt_day = date_part('day',t.ts3_el_dt)
	,ts3_el_tm  = t.ts3_el_ts::time
	,ts3_el_tm_hr  = date_part('hour',t.ts3_el_ts)
	,ts3_el_tm_min  = date_part('minute',t.ts3_el_ts)
where t.class_name = 'UsrNodeFinBal'
;
	
--id_dt_date1
raise notice 'Updating id_dt_date1';
update ampata_usr_node t
set id_dt_date1 = ts3_el_dt
where class_name = 'UsrNodeFinBal'
;	


--fin_acct1__id2
raise notice 'Updating fin_acct1__id2';
update ampata_usr_node t
set fin_acct1__id2 = t1.id2
from ampata_usr_node t1
where t.fin_acct1__id = t1.id
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
;	


--fin_dept1__id2
raise notice 'Updating fin_dept1__id2';
update ampata_usr_node t
set fin_dept1__id2 = t1.id2
from ampata_usr_node t1
where t.fin_dept1__id = t1.id
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
;	

--sys_fin_curcy1__id2
raise notice 'Updating fin_curcy1__id2';
update ampata_usr_node t
set sys_fin_curcy1__id2 = t1.id2
from ampata_sys_node t1
where t.sys_fin_curcy1__id = t1.id
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
;	


--id2_calc part 1
raise notice 'Updating id2_calc part 1';
update ampata_usr_node t
set id2_calc = Usr_Fin_Bal_Fn_get_Id2_Calc(
		null
		,t.fin_acct1__id2
		,t.fin_dept1__id2
		,t.ts1_el_dt
		,t.ts3_el_dt
		)

where	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.fin_bal_set1__id is null
;

--id2_calc part 2
raise notice 'Updating id2_calc part 2';
update ampata_usr_node t
set id2_calc = Usr_Fin_Bal_Fn_get_Id2_Calc(
		t.fin_bal_set1__id2
		,t.fin_acct1__id2
		,t.fin_dept1__id2
		,null
		,null
		)

where	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.fin_bal_set1__id is not null
;



--update sort_key part 1
raise notice 'Updating sort_key';
update ampata_usr_node t
set sort_key = '_' || right('00' || t.sort_idx,2)
where	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.fin_bal_set1__id is null
;

--update sort_key part 1
raise notice 'Updating sort_key';
update ampata_usr_node t
set sort_key = '_AA' || t2.sort_key || t3.sort_key
from ampata_usr_node t2
, ampata_usr_node t3
where	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.fin_bal_set1__id = t2.id
and t.fin_acct1__id = t3.id
;


--id2_cmp
raise notice 'Updating id2_cmp';
update ampata_usr_node t
set	id2_cmp = 	case when id2 = id2_calc then false 
				else true
				end
where t.class_name = 'UsrNodeFinBal'
;



--id2_dup
raise notice 'Updating id2_dup';
i_rows_updated = 0;
for rec_tenant in
	select 
	 id
	,tenant_id
	,name name1
	from mten_tenant t
	order by name
	--limit 100
loop
	raise notice '--> Iteration';
	raise notice '--- id:% tenant_id:% name1:%', rec_tenant.id, rec_tenant.tenant_id, rec_tenant.name1;

-- Get count from UPDATE
	with rows as (
		update ampata_usr_node t
		set	id2_dup = t1.id2_dup
		from (
			select t.id2, count(*) id2_dup
			from ampata_usr_node t
			where t.class_name = 'UsrNodeFinBal'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'UsrNodeFinBal'
	    returning 1
	)
	select count(*) from rows into i_rows_updated_in_fin_bal
	;
	i_rows_updated = i_rows_updated + i_rows_updated_in_fin_bal;

	raise notice '--- i_rows_updated_in_fin_bal:%', i_rows_updated_in_fin_bal;
	raise notice '<-- Iteration';

end loop;
raise notice 'i_rows_updated:%', i_rows_updated;



--amt_beg_bal_calc
--fin_txact_itms1__id_cnt_calc
--fin_txact_itms1__amt_debt_sum_calc
--fin_txact_itms1__amt_cred_sum_calc
--fin_txact_itms1__amt_net_sum_calc
--fin_txact_itms1__amt_eq_calc
--fin_txact_itms1__amt_debt_sum_diff
--fin_txact_itms1__amt_cred_sum_diff
--fin_txact_itms1__amt_net_sum_diff
--amt_net
--amt_end_bal_calc
raise notice 'Updating amt_net';
i_tenant = 0;
i_rows_updated_in_tenant = 0;
i_rows_updated = 0;

for rec_tenant in
	select 
	 id
	,tenant_id
	,name name1
	from mten_tenant t
	order by name
	--limit 100
loop
	raise notice '--> i_tenant:%', i_tenant;
	raise notice '--- i_tenant:% id:% tenant_id:% name1:%', i_tenant, rec_tenant.id, rec_tenant.tenant_id, rec_tenant.name1;

	i_fin_bal = 0;
	i_rows_updated_in_fin_bal = 0;

	for rec_fin_bal in
		select 
		 t.id
		,t.id2
		,t.type1__id
		,t.type1__id2
		,t.fin_acct1__id
		,t.fin_acct1__id2
		,t.fin_dept1__id
		,t.fin_dept1__id2
		,t.sys_fin_curcy1__id
		,t.sys_fin_curcy1__id2
		,t.ts1_el_ts
		,t.ts3_el_ts
		,t.amt_debt
		,t.amt_cred
		,t.amt_net
		from ampata_usr_node t
		where t.class_name = 'UsrNodeFinBal'
		and t.deleted_by is null
		and t.tenant = rec_tenant.tenant_id
		order by t.fin_acct1__id2, t.fin_dept1__id2, t.sys_fin_curcy1__id2, t.ts3_el_ts
		--limit 100
	loop

--		if rec_fin_bal.id2 <> 'R=Y2016/_;S=All;D=;D=;A=/Mark/A/RBC/Chk' then
--			continue;
--		end if;
		
		raise notice '--- i_tenant:% --> i_fin_bal:%', i_tenant, i_fin_bal;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.id2:%', i_tenant, i_fin_bal, rec_fin_bal.id2;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.type1__id2:%', i_tenant, i_fin_bal, rec_fin_bal.type1__id2;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.fin_acct1__id2:%', i_tenant, i_fin_bal, rec_fin_bal.fin_acct1__id2;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.fin_dept1__id2:%', i_tenant, i_fin_bal, rec_fin_bal.fin_dept1__id2;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.sys_fin_curcy1__id2:%', i_tenant, i_fin_bal, rec_fin_bal.sys_fin_curcy1__id2;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.ts1_el_ts:%', i_tenant, i_fin_bal, rec_fin_bal.ts1_el_ts;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.ts3_el_ts:%', i_tenant, i_fin_bal, rec_fin_bal.ts3_el_ts;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.amt_debt:%', i_tenant, i_fin_bal, rec_fin_bal.amt_debt;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.amt_cred:%', i_tenant, i_fin_bal, rec_fin_bal.amt_cred;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.amt_net:%', i_tenant, i_fin_bal, rec_fin_bal.amt_net;

		if rec_fin_bal.type1__id2 = '/Rng-Given-Year' then
			if rec_fin_bal.fin_dept1__id  is null then
				select t.id
				from ampata_usr_node t
				where t.class_name = 'UsrNodeFinBal'
				and t.deleted_by is null
				and t.tenant = rec_tenant.tenant_id
				and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
				and t.fin_dept1__id is null
				and t.sys_fin_curcy1__id = rec_fin_bal.sys_fin_curcy1__id
				and t.ts1_el_ts = rec_fin_bal.ts1_el_ts - interval '1' year
				and t.ts3_el_ts = rec_fin_bal.ts3_el_ts - interval '1' year
				into vc_fin_bal1__id
				;

			else
				select t.id
				from ampata_usr_node t
				where t.class_name = 'UsrNodeFinBal'
				and t.deleted_by is null
				and t.tenant = rec_tenant.tenant_id
				and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
				and t.fin_dept1__id = rec_fin_bal.fin_dept1__id
				and t.sys_fin_curcy1__id = rec_fin_bal.sys_fin_curcy1__id
				and t.ts1_el_ts = rec_fin_bal.ts1_el_ts - interval '1' year
				and t.ts3_el_ts = rec_fin_bal.ts3_el_ts - interval '1' year
				into vc_fin_bal1__id
				;
			end if;

--		elsif rec_fin_bal.type1__id2 == '/Rng-Given-Month' then
--		elsif rec_fin_bal.type1__id2 == '/Rng-Given-Week' then
--		elsif rec_fin_bal.type1__id2 == '/Rng-Given-Day' then
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% vc_fin_bal1__id:%', i_tenant, i_fin_bal, vc_fin_bal1__id;

		if vc_fin_bal1__id is not null then
			update ampata_usr_node t 
			set fin_bal1__id = vc_fin_bal1__id::uuid
			where t.class_name = 'UsrNodeFinBal'
			and t.id = rec_fin_bal.id
			;

		end if;
		
		
		--amt_beg_bal_calc
		select t2.amt_end_bal_calc
		from ampata_usr_node t
		inner join ampata_usr_node t2
			on t2.id  = t.fin_bal1__id
		where t.class_name = 'UsrNodeFinBal'
		and t.deleted_by is null
		and t.tenant = rec_tenant.tenant_id
		and t.id = rec_fin_bal.id
		and t2.class_name = 'UsrNodeFinBal'
		into num_amt_beg_bal_calc
		;

		if num_amt_beg_bal_calc is null then
			num_amt_beg_bal_calc = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_amt_beg_bal_calc:%', i_tenant, i_fin_bal, num_amt_beg_bal_calc;

	
		--amt_net
		if rec_fin_bal.amt_debt is null then
			rec_fin_bal.amt_debt = 0;
		end if;

		if rec_fin_bal.amt_cred is null then
			rec_fin_bal.amt_cred = 0;
		end if;

		if b_bal_inc_on_debt then
			num_amt_net = rec_fin_bal.amt_debt - rec_fin_bal.amt_cred;
		else
			num_amt_net = rec_fin_bal.amt_cred - rec_fin_bal.amt_debt;
		end if;

		if num_amt_net is null then
			num_amt_net = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_amt_net:%', i_tenant, i_fin_bal, num_amt_net;

	
		--fin_txact_itms1__id_cnt_calc
		if rec_fin_bal.fin_dept1__id  is null then
			select count(t.id)
			from ampata_usr_node t
			where t.class_name = 'UsrNodeFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.sys_fin_curcy1__id = rec_fin_bal.sys_fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into i_fin_txact_itms1__id_cnt_calc
			;
		else
			select count(t.id)
			from ampata_usr_node t
			where t.class_name = 'UsrNodeFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_dept1__id = rec_fin_bal.fin_dept1__id
			and t.sys_fin_curcy1__id = rec_fin_bal.sys_fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into i_fin_txact_itms1__id_cnt_calc
			;
		end if;

		if i_fin_txact_itms1__id_cnt_calc is null then
			i_fin_txact_itms1__id_cnt_calc = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% i_fin_txact_itms1__id_cnt_calc:%', i_tenant, i_fin_bal, i_fin_txact_itms1__id_cnt_calc;


	
		--fin_txact_itms1__amt_debt_sum_calc
		if rec_fin_bal.fin_dept1__id  is null then
			select sum(t.amt_debt)
			from ampata_usr_node t
			where t.class_name = 'UsrNodeFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.sys_fin_curcy1__id = rec_fin_bal.sys_fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into num_fin_txact_itms1__amt_debt_sum_calc
			;
		else
			select sum(t.amt_debt)
			from ampata_usr_node t
			where t.class_name = 'UsrNodeFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_dept1__id = rec_fin_bal.fin_dept1__id
			and t.sys_fin_curcy1__id = rec_fin_bal.sys_fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into num_fin_txact_itms1__amt_debt_sum_calc
			;
		end if;

		if num_fin_txact_itms1__amt_debt_sum_calc is null then
			num_fin_txact_itms1__amt_debt_sum_calc = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_fin_txact_itms1__amt_debt_sum_calc:%', i_tenant, i_fin_bal, num_fin_txact_itms1__amt_debt_sum_calc;


		--fin_txact_itms1__amt_cred_sum_calc
		if rec_fin_bal.fin_dept1__id  is null then
			select sum(t.amt_cred)
			from ampata_usr_node t
			where t.class_name = 'UsrNodeFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.sys_fin_curcy1__id = rec_fin_bal.sys_fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into num_fin_txact_itms1__amt_cred_sum_calc
			;
		else
			select sum(t.amt_cred)
			from ampata_usr_node t
			where t.class_name = 'UsrNodeFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_dept1__id = rec_fin_bal.fin_dept1__id
			and t.sys_fin_curcy1__id = rec_fin_bal.sys_fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into num_fin_txact_itms1__amt_cred_sum_calc
			;
		end if;

		if num_fin_txact_itms1__amt_cred_sum_calc is null then
			num_fin_txact_itms1__amt_cred_sum_calc = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_fin_txact_itms1__amt_cred_sum_calc:%', i_tenant, i_fin_bal, num_fin_txact_itms1__amt_cred_sum_calc;


		--fin_txact_itms1__amt_net_sum_calc
		select t2.bal_inc_on_debt, t2.bal_inc_on_cred 
		from ampata_usr_node t
		inner join ampata_usr_node_type t2
			on t.type1__id = t2.id
		where t.class_name = 'UsrNodeFinAcct'
		and t.deleted_by is null
		and t.id = rec_fin_bal.fin_acct1__id
		into b_bal_inc_on_debt, b_bal_inc_on_cred
		;
	
		if b_bal_inc_on_debt then
			num_fin_txact_itms1__amt_net_sum_calc = num_fin_txact_itms1__amt_debt_sum_calc - num_fin_txact_itms1__amt_cred_sum_calc;
		else
			num_fin_txact_itms1__amt_net_sum_calc = num_fin_txact_itms1__amt_cred_sum_calc - num_fin_txact_itms1__amt_debt_sum_calc;
		end if;

		if num_fin_txact_itms1__amt_net_sum_calc is null then
			num_fin_txact_itms1__amt_net_sum_calc = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_fin_txact_itms1__amt_net_sum_calc:%', i_tenant, i_fin_bal, num_fin_txact_itms1__amt_net_sum_calc;

	
		--fin_txact_itms1__amt_debt_sum_diff
		num_fin_txact_itms1__amt_debt_sum_diff = rec_fin_bal.amt_debt - num_fin_txact_itms1__amt_debt_sum_calc;

		if num_fin_txact_itms1__amt_debt_sum_diff is null then
			num_fin_txact_itms1__amt_debt_sum_diff = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_fin_txact_itms1__amt_debt_sum_diff:%', i_tenant, i_fin_bal, num_fin_txact_itms1__amt_debt_sum_diff;

	
		--fin_txact_itms1__amt_cred_sum_diff
		num_fin_txact_itms1__amt_cred_sum_diff = rec_fin_bal.amt_cred - num_fin_txact_itms1__amt_cred_sum_calc;

		if num_fin_txact_itms1__amt_cred_sum_diff is null then
			num_fin_txact_itms1__amt_cred_sum_diff = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_fin_txact_itms1__amt_cred_sum_diff:%', i_tenant, i_fin_bal, num_fin_txact_itms1__amt_cred_sum_diff;


		--fin_txact_itms1__amt_net_sum_diff
		num_fin_txact_itms1__amt_net_sum_diff = num_amt_net - num_fin_txact_itms1__amt_net_sum_calc;

		if num_fin_txact_itms1__amt_net_sum_diff is null then
			num_fin_txact_itms1__amt_net_sum_diff = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_fin_txact_itms1__amt_net_sum_diff:%', i_tenant, i_fin_bal, num_fin_txact_itms1__amt_net_sum_diff;


		--fin_txact_itms1__amt_eq_calc
		if num_fin_txact_itms1__amt_net_sum_diff = 0 then
			b_fin_txact_itms1__amt_eq_calc = true;
		else
			b_fin_txact_itms1__amt_eq_calc = false;
		end if;
	
		if b_fin_txact_itms1__amt_eq_calc is null then
			b_fin_txact_itms1__amt_eq_calc = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% b_fin_txact_itms1__amt_eq_calc:%', i_tenant, i_fin_bal, b_fin_txact_itms1__amt_eq_calc;



		--amt_end_bal_calc
		num_amt_end_bal_calc = num_amt_beg_bal_calc + num_fin_txact_itms1__amt_net_sum_calc;

		if num_amt_end_bal_calc is null then
			num_amt_end_bal_calc = 0;
		end if;
		raise notice '--- i_tenant:% --- i_fin_bal:% num_amt_end_bal_calc:%', i_tenant, i_fin_bal, num_amt_end_bal_calc;

	
		update ampata_usr_node t 
		set  amt_beg_bal_calc = num_amt_beg_bal_calc
			,amt_net = num_amt_net
			,fin_txact_itms1__id_cnt_calc = i_fin_txact_itms1__id_cnt_calc
			,fin_txact_itms1__amt_debt_sum_calc = num_fin_txact_itms1__amt_debt_sum_calc
			,fin_txact_itms1__amt_cred_sum_calc = num_fin_txact_itms1__amt_cred_sum_calc
			,fin_txact_itms1__amt_net_sum_calc = num_fin_txact_itms1__amt_net_sum_calc
			,fin_txact_itms1__amt_debt_sum_diff = num_fin_txact_itms1__amt_debt_sum_diff
			,fin_txact_itms1__amt_cred_sum_diff = num_fin_txact_itms1__amt_cred_sum_diff
			,fin_txact_itms1__amt_net_sum_diff = num_fin_txact_itms1__amt_net_sum_diff
			,fin_txact_itms1__amt_eq_calc = b_fin_txact_itms1__amt_eq_calc
			,amt_end_bal_calc = num_amt_end_bal_calc
		where t.class_name = 'UsrNodeFinBal'
		and t.id = rec_fin_bal.id
		;
	
		i_rows_updated_in_fin_bal = i_rows_updated_in_fin_bal + 1;
	
		raise notice '--- i_tenant:% --- i_fin_bal:% i_rows_updated_in_fin_bal:%', i_tenant , i_fin_bal, i_rows_updated_in_fin_bal;
		raise notice '--- i_tenant:% --- i_fin_bal:%', i_tenant, i_fin_bal;
		
		raise notice '--- i_tenant:% <-- i_fin_bal:%', i_tenant, i_fin_bal;
		i_fin_bal = i_fin_bal +1;

	end loop;




-- Get count from UPDATE
/*
	with rows as (
		update ampata_usr_node t
		set	id2_dup = t1.id2_dup
		from (
			select t.id2, count(*) id2_dup
			from ampata_usr_node t
			where t.class_name = 'UsrNodeFinBal'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'UsrNodeFinBal'
	    returning 1
	)
	select count(*) from rows into i_rows_updated_in_tenant
	;
*/
	i_rows_updated_in_tenant = i_rows_updated_in_fin_bal;

	i_rows_updated = i_rows_updated + i_rows_updated_in_tenant;

	raise notice '--- i_rows_updated_in_tenant:%', i_rows_updated_in_tenant;
	raise notice '<-- i_tenant:%', i_tenant;
	i_tenant = i_tenant +1;
end loop;
raise notice 'i_rows_updated:%', i_rows_updated;




--amt_net
raise notice 'Updating amt_net';
with cte1 as(
	select t1.id
	  , t2.id2 as type1__id2
	  , t2.bal_inc_on_debt
	  , t2.bal_inc_on_cred
	from ampata_usr_node t1
	inner join ampata_usr_node_type t2
		on t1.type1__id  = t2.id
	where t1.class_name = 'UsrNodeFinAcct'
	and t1.deleted_by is null
	and t2.class_name = 'UsrNodeFinAcct'
)

,cte2 as (
select 
 t.id
,t.id2
,t.fin_acct1__id2
,t.amt_debt
,t.amt_cred
,case when coalesce(ct.bal_inc_on_debt,false) then 
    coalesce(t.amt_debt,0) - coalesce(t.amt_cred,0)
 else 
    case when coalesce(ct.bal_inc_on_cred,false) then 
        coalesce(t.amt_cred,0) - coalesce(t.amt_debt,0)
    else
        0
    end
 end as amt_net
from ampata_usr_node t
inner join cte1 ct
on t.fin_acct1__id = ct.id 
where	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
)

--select * from cte2 

update ampata_usr_node t
set  amt_net = ct.amt_net
from cte2 ct
where t.id = ct.id
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
;



/*
--amt_end_bal_calc 
raise notice 'Updating amt_end_bal_calc';

--Using a window function will not provide teh desired functinality because the window function sum(amt_net) 
--is an aggregate function of the amt_net column and is not able to set a particular row to a given value amt_beg_bal
--in essence it can only give the running total of ONE particular column
--note: you could create a query with a calcuated column that for the first row is the sum of amt_net and amt_beg_bal
--otherwise it is amt_net
--this would ignore amt_beg_bal for non-first rows but it would work
--try a cursor loop instead of a window funciton

with cte1 as(
	select
		row_number() over(
			partition by tenant, fin_acct1__id, fin_dept1__id
			order by id_dt_date1
			) as nr
		,id
		,id2
		,tenant
		,desc1
		,fin_acct1__id
		,fin_acct1__id2
		,sys_fin_curcy1__id
		,sys_fin_curcy1__id2
		,id_dt_date1
		,amt_beg_bal
		,amt_net
		,case when
			row_number() over(
				partition by tenant, fin_acct1__id, fin_acct1__id2
				order by id_dt_date1
			) = 1 then coalesce(amt_beg_bal,0) + amt_net else amt_net end
			as amt_net_merg
	from ampata_usr_node t
	where	t.class_name = 'UsrNodeFinBal'
	and deleted_by is null
--	and fin_acct1__id2 = '/Mark/A/RBC/Chk'
	order by tenant, fin_acct1__id, fin_acct1__id2, id_dt_date1
)

,cte2 as (
	select
		 nr
		,id
		,id2
		,tenant
		,desc1
		,fin_acct1__id2
		,sys_fin_curcy1__id2
		,id_dt_date1
		,amt_beg_bal
		,amt_net
		,amt_net_merg
		,sum(amt_net_merg) over(      
			partition by tenant, fin_acct1__id, fin_acct1__id2
			order by id_dt_date1
			) AS amt_end_bal_calc
	from cte1
	order by tenant, fin_acct1__id, fin_acct1__id2, id_dt_date1

)

--select * from cte2 

update ampata_usr_node t
set  amt_end_bal_calc = ct.amt_end_bal_calc
from cte2 ct
where t.id = ct.id
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
;


--amt_beg_bal_calc
with cte1 as(
	select
		row_number() over(
			partition by tenant, fin_acct1__id, sys_fin_curcy1__id
			order by id_dt_date1
			) as nr
		,id
		,id2
		,tenant
		,desc1
		,fin_acct1__id
		,fin_acct1__id2
		,sys_fin_curcy1__id
		,sys_fin_curcy1__id2
		,id_dt_date1
		,amt_beg_bal
		,lag(amt_end_bal_calc,1) over(      
			partition by tenant, fin_acct1__id, sys_fin_curcy1__id
			order by id_dt_date1)
		as amt_beg_bal_calc
		,amt_net
		,amt_end_bal_calc
	from ampata_usr_node t
	where	t.class_name = 'UsrNodeFinBal'
	and deleted_by is null
--	and fin_acct1__id2 = '/Mark/A/RBC/Chk'
	order by tenant, fin_acct1__id, sys_fin_curcy1__id, id_dt_date1
)

--select * from cte1 

update ampata_usr_node t
set  amt_beg_bal_calc = ct.amt_beg_bal_calc
from cte1 ct
where t.id = ct.id
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
;
*/


--fin_acct1__id2
raise notice 'Updating fin_acct1__id2';
update ampata_usr_node t
set  fin_acct1__id2 = t1.fin_acct1__id2
from (
	select t2a.id
	,t2a.id2
	,t2a.fin_acct1__id
	,t2b.id2 as fin_acct1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_node t2b
	on t2a.fin_acct1__id = t2b.id
	where t2a.class_name = 'UsrNodeFinBal'
	and t2a.deleted_by is null
	and t2b.class_name = 'UsrNodeFinAcct'
	
) t1
where t.id = t1.id 
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
;



--sys_fin_curcy1__id, sys_fin_curcy1__id2
raise notice 'Updating sys_fin_curcy1__id, sys_fin_curcy1__id2';
update ampata_usr_node t
set  sys_fin_curcy1__id = t1.sys_fin_curcy1__id
	,sys_fin_curcy1__id2 = t1.sys_fin_curcy1__id2
from (
	select t2a.id
		,t2a.id2
		,t2a.fin_acct1__id
		,t2b.id2 as fin_acct1__id2
		,t2b.sys_fin_curcy1__id
		,t2c.id2 as sys_fin_curcy1__id2
	from ampata_usr_node t2a
	inner join ampata_usr_node t2b
	on t2a.fin_acct1__id = t2b.id
	inner join ampata_sys_node t2c
	on t2b.sys_fin_curcy1__id = t2c.id
	where t2a.class_name = 'UsrNodeFinBal'
	and t2a.deleted_by is null
	and t2b.class_name = 'UsrNodeFinAcct'
	and t2c.class_name = 'UsrFinCurcy'
) t1
where t.id = t1.id 
and	t.class_name = 'UsrNodeFinBal'
and t.deleted_by is null
and t.sys_fin_curcy1__id is null
;




/*
select count(*)
from ampata_usr_node t
where t.class_name ='FinBal'
*/

end
$BODY$
;

call Usr_Fin_Bal_Pr_Upd();

