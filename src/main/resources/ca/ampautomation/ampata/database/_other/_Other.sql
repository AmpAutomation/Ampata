select version();


CREATE ROLE ampata WITH ENCRYPTED PASSWORD 'AmpYourData1!';
alter role ampata login superuser

CREATE ROLE test WITH ENCRYPTED PASSWORD 'TestYourData1!';
alter role test login superuser


select * from pg_stat_activity
where datname = 'ampata_dev'
and state = 'idle'
;

--Kill an Idle Connection:
select pg_terminate_backend(15695);

--Kill all Connections to database:
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'ampata_dev'
  AND pid <> pg_backend_pid();
  

 
drop database ampata_dev;

CREATE DATABASE ampata_dev;
GRANT ALL PRIVILEGES ON DATABASE ampata_dev TO ampata;


CREATE DATABASE ampata_issue_1;
GRANT ALL PRIVILEGES ON DATABASE ampata_issue_1 TO ampata;



-- ampata_dev2

SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'ampata_dev2'
  AND pid <> pg_backend_pid();



drop database ampata_dev2;

CREATE DATABASE ampata_dev2;
GRANT ALL PRIVILEGES ON DATABASE ampata_dev2 TO ampata;



--DROP EXTENSION "uuid-ossp";
CREATE EXTENSION "uuid-ossp";

--DROP EXTENSION plpython3u;
CREATE EXTENSION plpython3u;




alter table ampata_sys_node 
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

alter table ampata_sys_node 
ALTER COLUMN "version" SET DEFAULT 1;


alter table ampata_sys_node_type
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

alter table ampata_sys_node_type 
ALTER COLUMN "version" SET DEFAULT 1;


alter table ampata_sys_item 
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

alter table ampata_sys_item 
ALTER COLUMN "version" SET DEFAULT 1;


alter table ampata_sys_item_type
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

alter table ampata_sys_item_type 
ALTER COLUMN "version" SET DEFAULT 1;





alter table ampata_usr_node 
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

alter table ampata_usr_node 
ALTER COLUMN "version" SET DEFAULT 1;


alter table ampata_usr_node_type
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

alter table ampata_usr_node_type 
ALTER COLUMN "version" SET DEFAULT 1;


alter table ampata_usr_item 
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

alter table ampata_usr_item 
ALTER COLUMN "version" SET DEFAULT 1;


alter table ampata_usr_item_type
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

alter table ampata_usr_item_type 
ALTER COLUMN "version" SET DEFAULT 1;




drop database ampata_dev_bkup;

CREATE DATABASE ampata_dev_bkup;
GRANT ALL PRIVILEGES ON DATABASE ampata_dev_bkup TO ampata;





CREATE DATABASE test3;
GRANT ALL PRIVILEGES ON DATABASE test3 TO test;

show all 



where name = 'idle_in_transaction_session_timeout'

TABLE pg_settings


idle_session_timeout

ALTER SYSTEM reset idle_in_transaction_session_timeout


--Enable session timeout in ms
--With superuser access
alter system set idle_in_transaction_session_timeout = 5000;

--Enable session timeout in ms
--Without superuser access
SET SESSION idle_in_transaction_session_timeout = 5000;


alter system set idle_session_timeout = 0;

--Disable session timeout
alter system set idle_in_transaction_session_timeout = 0;

--Without superuser access
SET SESSION idle_in_transaction_session_timeout = 0;

alter system set idle_session_timeout = 0;





-- Change settings for all sessions
--ALTER SYSTEM SET client_min_messages = (...;)
--ALTER SYSTEM SET log_min_messages = ...;
ALTER SYSTEM SET client_min_messages = 'notice'
ALTER SYSTEM SET client_min_messages = default

DROP EXTENSION "uuid-ossp";
CREATE EXTENSION "uuid-ossp";
DROP EXTENSION plpython3u;
CREATE EXTENSION plpython3u;


-- Change settings for this sessions
/*
 * https://www.postgresql.org/docs/current/functions-admin.html
 * 
 * current_setting ( setting_name text [, missing_ok boolean ] ) → text
 * Returns the current value of the setting setting_name. If there is no such setting, current_setting throws an error unless missing_ok is supplied and is true (in which case NULL is returned). This function corresponds to the SQL command SHOW.
 * current_setting('datestyle') → ISO, MDY
 * 
 * set_config ( setting_name text, new_value text, is_local boolean ) → text
 * Sets the parameter setting_name to new_value, and returns that value. If is_local is true, the new value will only apply during the current transaction. If you want the new value to apply for the rest of the current session, use false instead. This function corresponds to the SQL command SET.
 * set_config('log_statement_stats', 'off', false) → off
 */


select current_setting('client_min_messages')
select set_config('client_min_messages','info',false)
select set_config('client_min_messages','debug5',false)
select set_config('client_min_messages','default',false)

/*
 * Message Severity Levels
 * ======================
 *  DEBUG1..5	Provides successively-more-detailed information for use by developers.									DEBUG	INFORMATION
 *  INFO		Provides information implicitly requested by the user, e.g., output from VACUUM VERBOSE.				INFO	INFORMATION
 * *NOTICE		Provides information that might be helpful to users, e.g., notice of truncation of long identifiers.	NOTICE	INFORMATION
 *  WARNING		Provides warnings of likely problems, e.g., COMMIT outside a transaction block.							NOTICE	WARNING
 *  ERROR		Reports an error that caused the current command to abort.												WARNING	ERROR
 *  LOG			Reports information of interest to administrators, e.g., checkpoint activity.							INFO	INFORMATION
 *  FATAL		Reports an error that caused the current session to abort.												ERR		ERROR
 *  PANIC		Reports an error that caused all database sessions to abort.											CRIT	ERROR
 * default is NOTICE
 * 
**/



 