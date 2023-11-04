SELECT pg_terminate_backend(pid) from pg_stat_activity where datname = 'ampata_dev';

SELECT pg_terminate_backend(54)


 
select pid, usename, datname, state, backend_start, query_start, client_addr, client_hostname from pg_stat_activity;
