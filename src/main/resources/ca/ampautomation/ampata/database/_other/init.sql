


CREATE ROLE ampata WITH ENCRYPTED PASSWORD '-----';
alter role ampata login superuser

CREATE ROLE test WITH ENCRYPTED PASSWORD '-----';
alter role test login superuser


ALTER USER ampata WITH PASSWORD '*******';

 
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

