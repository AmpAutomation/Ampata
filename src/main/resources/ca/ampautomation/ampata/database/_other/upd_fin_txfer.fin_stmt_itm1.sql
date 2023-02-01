-- loop through finTxfer and search for only 1 FinStmtItm with the same amt_debt or amt_cred
-- if found then updatate FinTxactItm.fin_stmt_itm1_id to the found 

do $$
declare
	node record;
	node2 ampata_sys_node%ROWTYPE;
	_id2 varchar(50);
	_id uuid;
	amt numeric(19,2);
	num_rows int;
	num_rows_updated int;

begin
	num_rows_updated = 0;
	for node in
		select 
		 id
		,id2
		,class_name
		,fin_acct1__id
		,id_ts_ts1 
		,amt_debt
		,amt_cred
		,amt_net
		,fin_stmt_itm1__id
		from ampata_sys_node t
		where t.class_name = 'FinTxactItm'
		and t.fin_stmt_itm1__id is null
--		and t.fin_acct1__id = 'addff8fa-a1a3-47ef-9309-62c1adab4998' -- /Mark/L/RBC/Visa
		and t.fin_acct1__id = 'cd75edac-f633-4dea-b657-0c0d472e0bdb' -- /Mark/L/RBC/Visa_USD

		
		order by id2
		--limit 100
	loop
	    
		RAISE NOTICE 'id:% id2:% class_name:% fin_acct1__id:% id_ts_ts1:% amt_debt:% amt_cred:% fin_stmt_itm1__id:%', node.id, node.id2, node.class_name, node.fin_acct1__id, node.id_ts_ts1, node.amt_debt, node.amt_cred, node.fin_stmt_itm1__id;

		if node.amt_debt is not null then
	    	amt = node.amt_debt;
	    elseif node.amt_cred is not null then
	    	amt = node.amt_cred;
	    else 
	    	continue;
	    end if;
		RAISE NOTICE 'searching for fin_acct1__id2:% id_ts_ts1:% amt:% ', '/Mark/L/RBC/Visa', node.id_ts_ts1, amt;
	   
		select count(*) into num_rows
		FROM ampata_sys_node t
		inner join ampata_sys_node t2
		on t.fin_stmt1__id = t2.id
		where t.class_name = 'FinStmtItm'
		and t.deleted_by is null
		and t2.class_name = 'FinStmt'
		and t2.fin_acct1__id = node.fin_acct1__id
		and t.id_ts_ts1 between node.id_ts_ts1 - interval '3 day' and node.id_ts_ts1 + interval '3 day'
		and (t.amt_debt = amt or t.amt_cred = amt)
		;

		RAISE NOTICE 'num_rows:%', num_rows;

		if num_rows = 1 then
		
			SELECT * INTO node2 
			FROM ampata_sys_node t
			inner join ampata_sys_node t2
			on t.fin_stmt1__id = t2.id
			where t.class_name = 'FinStmtItm'
			and t.deleted_by is null
			and t2.class_name = 'FinStmt'
			and t2.fin_acct1__id = node.fin_acct1__id
			and t.id_ts_ts1 between node.id_ts_ts1 - interval '3 day' and node.id_ts_ts1 + interval '3 day'
			and (t.amt_debt = amt or t.amt_cred = amt)
			;
		
			RAISE NOTICE 'FOUND - id:% id2:% class_name:% fin_stmt1__id:% id_ts_ts1:% amt_debt:% amt_cred:%', node2.id, node2.id2, node2.class_name, node2.fin_stmt1__id, node2.id_ts_ts1, node2.amt_debt, node2.amt_cred;

			update ampata_sys_node 
			set fin_stmt_itm1__id = node2.id 
			WHERE id = node.id;

			num_rows_updated = num_rows_updated + 1;

		else
			raise notice 'NOT FOUND - rows:%', num_rows;
		end if;
		raise notice '';
		
	end loop;

	raise notice 'num_rows_updated:%', num_rows_updated;
end;
$$;
