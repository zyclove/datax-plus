DROP DATABASE if exists source_db;

CREATE database source_db;

USE source_db;

DROP TABLE IF EXISTS table_for_t;
CREATE TABLE table_for_t (
    `pk_col_a` INT NOT NULL AUTO_INCREMENT,
    `col_name` VARCHAR(100) DEFAULT '',
    `col_value` VARCHAR(100) DEFAULT '',
    PRIMARY KEY(`pk_col_a`)
)DEFAULT CHARSET=utf8;

INSERT INTO table_for_t (col_name, col_value ) VALUES ( '111111','aaaaaaaa');
INSERT INTO table_for_t (col_name, col_value ) VALUES ( '8787878','bbbbb');
INSERT INTO table_for_t (col_name, col_value ) VALUES ( 'asdf','aaaacccaaaa');
INSERT INTO table_for_t (col_name, col_value ) VALUES ( 'swsdfasdf','dfdfdfdddd');


#------------------------- db 88
DROP DATABASE if exists target_db;

CREATE database target_db;

USE target_db;

DROP TABLE IF EXISTS diag_8;
CREATE TABLE diag_8 (
    `col_a` INT NOT NULL AUTO_INCREMENT,
    `col_b` VARCHAR(100) DEFAULT '',
    `col_c` VARCHAR(100) DEFAULT '',
    PRIMARY KEY(`col_a`)
)DEFAULT CHARSET=utf8;