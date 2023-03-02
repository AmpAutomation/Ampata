
do $BODY$
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
		,t.fin_curcy1__id
		,t.fin_curcy1__id2
		,t.ts1_el_ts
		,t.ts3_el_ts
		,t.amt_debt
		,t.amt_cred
		,t.amt_net
		from ampata_sys_node t
		where t.class_name = 'UsrFinBal'
		and t.deleted_by is null
		and t.tenant = rec_tenant.tenant_id
		order by t.fin_acct1__id2, t.fin_dept1__id2, t.fin_curcy1__id2, t.ts3_el_ts
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
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.fin_curcy1__id2:%', i_tenant, i_fin_bal, rec_fin_bal.fin_curcy1__id2;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.ts1_el_ts:%', i_tenant, i_fin_bal, rec_fin_bal.ts1_el_ts;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.ts3_el_ts:%', i_tenant, i_fin_bal, rec_fin_bal.ts3_el_ts;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.amt_debt:%', i_tenant, i_fin_bal, rec_fin_bal.amt_debt;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.amt_cred:%', i_tenant, i_fin_bal, rec_fin_bal.amt_cred;
		raise notice '--- i_tenant:% --- i_fin_bal:% rec_fin_bal.amt_net:%', i_tenant, i_fin_bal, rec_fin_bal.amt_net;

		if rec_fin_bal.type1__id2 = '/Rng-Given-Year' then
			if rec_fin_bal.fin_dept1__id  is null then
				select t.id
				from ampata_sys_node t
				where t.class_name = 'UsrFinBal'
				and t.deleted_by is null
				and t.tenant = rec_tenant.tenant_id
				and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
				and t.fin_dept1__id = null
				and t.fin_curcy1__id = rec_fin_bal.fin_curcy1__id
				and t.ts1_el_ts = rec_fin_bal.ts1_el_ts - interval '1' year
				and t.ts3_el_ts = rec_fin_bal.ts3_el_ts - interval '1' year
				into vc_fin_bal1__id
				;

			else
				select t.id
				from ampata_sys_node t
				where t.class_name = 'UsrFinBal'
				and t.deleted_by is null
				and t.tenant = rec_tenant.tenant_id
				and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
				and t.fin_dept1__id = rec_fin_bal.fin_dept1__id
				and t.fin_curcy1__id = rec_fin_bal.fin_curcy1__id
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
			update ampata_sys_node t 
			set fin_bal1__id = vc_fin_bal1__id::uuid
			where t.class_name = 'UsrFinBal'
			and t.id = rec_fin_bal.id
			;

		end if;
		
		
		--amt_beg_bal_calc
		select t2.amt_end_bal_calc
		from ampata_sys_node t
		inner join ampata_sys_node t2
			on t2.id  = t.fin_bal1__id
		where t.class_name = 'UsrFinBal'
		and t.deleted_by is null
		and t.tenant = rec_tenant.tenant_id
		and t.id = rec_fin_bal.id
		and t2.class_name = 'UsrFinBal'
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
			from ampata_sys_node t
			where t.class_name = 'UsrFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_curcy1__id = rec_fin_bal.fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into i_fin_txact_itms1__id_cnt_calc
			;
		else
			select count(t.id)
			from ampata_sys_node t
			where t.class_name = 'UsrFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_dept1__id = rec_fin_bal.fin_dept1__id
			and t.fin_curcy1__id = rec_fin_bal.fin_curcy1__id
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
			from ampata_sys_node t
			where t.class_name = 'UsrFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_curcy1__id = rec_fin_bal.fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into num_fin_txact_itms1__amt_debt_sum_calc
			;
		else
			select sum(t.amt_debt)
			from ampata_sys_node t
			where t.class_name = 'UsrFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_dept1__id = rec_fin_bal.fin_dept1__id
			and t.fin_curcy1__id = rec_fin_bal.fin_curcy1__id
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
			from ampata_sys_node t
			where t.class_name = 'UsrFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_curcy1__id = rec_fin_bal.fin_curcy1__id
			and t.id_ts_ts1 between rec_fin_bal.ts1_el_ts and rec_fin_bal.ts3_el_ts
			into num_fin_txact_itms1__amt_cred_sum_calc
			;
		else
			select sum(t.amt_cred)
			from ampata_sys_node t
			where t.class_name = 'UsrFinTxactItm'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			and t.fin_acct1__id = rec_fin_bal.fin_acct1__id
			and t.fin_dept1__id = rec_fin_bal.fin_dept1__id
			and t.fin_curcy1__id = rec_fin_bal.fin_curcy1__id
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
		from ampata_sys_node t
		inner join ampata_sys_node_type t2
			on t.type1__id = t2.id
		where t.class_name = 'UsrFinAcct'
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

	
		update ampata_sys_node t 
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
		where t.class_name = 'UsrFinBal'
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
		update ampata_sys_node t
		set	id2_dup = t1.id2_dup
		from (
			select t.id2, count(*) id2_dup
			from ampata_sys_node t
			where t.class_name = 'UsrFinBal'
			and t.deleted_by is null
			and t.tenant = rec_tenant.tenant_id
			group by t.id2 
		) t1
		where t.id2 = t1.id2 
		and	t.class_name = 'UsrFinBal'
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

end
$BODY$
;



