# Oracle

## Install

https://hub.docker.com/r/oracleinanutshell/oracle-xe-11g

-- https://jingyan.baidu.com/article/a3761b2ba6a2541576f9aa8e.html

DROP TABLESPACE user1_space;

CREATE SMALLFILE TABLESPACE "user1_space" DATAFILE 'TS_NAME_DATA.DBF' SIZE 1000M AUTOEXTEND ON NEXT 1000M MAXSIZE UNLIMITED LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO;

drop user USER1;

CREATE USER USER1  IDENTIFIED BY user1 DEFAULT TABLESPACE "user1_space";

alter user USER1 identified by 12345678;

grant connect,resource,alter system,debug connect session,select any table,delete any table,drop any table,alter any table to USER1;

-- 赋予权限
GRANT CONNECT, CREATE TABLE, CREATE TRIGGER, CREATE PROCEDURE, CREATE VIEW, CREATE SEQUENCE, DEBUG CONNECT SESSION TO USER1;

-- grant dba to USER1;

-- grant select TO USER1;



CREATE TABLE table_t (
	c1 number,
    c2 VARCHAR(100),
    c3 VARCHAR(100),
    c4 VARCHAR(100)
)