DROP DATABASE if exists datax_plus;

CREATE database datax_plus;

USE datax_plus;


DROP TABLE IF EXISTS tb_data_source;
CREATE TABLE tb_data_source (
    `data_source_id` INT NOT NULL AUTO_INCREMENT,
    `data_source_type` INT NOT NULL,
    `data_source_name` VARCHAR(100) DEFAULT '',
    `db_host_url` VARCHAR(500) DEFAULT '',
    `db_username` VARCHAR(100) DEFAULT '',
    `db_password` VARCHAR(100) DEFAULT '',
    `status` TINYINT,
    `create_time` datetime NOT NULL DEFAULT NOW(),
    PRIMARY KEY(`data_source_id`)
)DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS tb_data_job;
CREATE TABLE tb_data_job (
    `data_job_id` INT NOT NULL AUTO_INCREMENT,
    `data_job_name` VARCHAR(100) DEFAULT '',
    `status` TINYINT,
    `create_time` datetime NOT NULL DEFAULT NOW(),
    PRIMARY KEY(`data_job_id`)
)DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_data_job_log;
CREATE TABLE tb_data_job_log (
    `log_row_id` INT NOT NULL AUTO_INCREMENT,
    `data_job_id` INT,
    `log_id` INT,
    `log_body` TEXT,
    `create_time` datetime NOT NULL DEFAULT NOW(),
    PRIMARY KEY(`log_row_id`)
)DEFAULT CHARSET=utf8;









DROP TABLE IF EXISTS tb_users_roles;
CREATE TABLE tb_users_roles (
                                `row_id` INT NOT NULL AUTO_INCREMENT,
                                `account_id` INT NOT NULL,
                                `role_id` INT NOT NULL,
                                PRIMARY KEY(`row_id`)
)DEFAULT CHARSET=utf8;

INSERT INTO tb_users_roles (`account_id`, `role_id`) VALUES (1,1);

DROP TABLE IF EXISTS tb_functions;
CREATE TABLE tb_functions (
                              `function_id` INT NOT NULL AUTO_INCREMENT,
                              `number` INT NOT NULL,
                              `order` int NOT NULL,
                              `name` VARCHAR(200) NULL,
                              `path` VARCHAR(200) NULL,
                              `type` TINYINT NOT NULL DEFAULT 1,
                              `parent_function_id` INT NOT NULL DEFAULT 0,
                              PRIMARY KEY (`function_id`)
)DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_functions_items;
CREATE TABLE tb_functions_items (
                                    `function_item_id` INT NOT NULL AUTO_INCREMENT,
                                    `function_id` INT NOT NULL DEFAULT 0,
                                    `item_number` INT NOT NULL DEFAULT 0,
                                    `item_name` VARCHAR(200) NULL,
                                    PRIMARY KEY (`function_item_id`)
)DEFAULT CHARSET=utf8;

# ---增加默认的
INSERT INTO tb_functions (`function_id`, `name`, `order`, `path`, `type`, `parent_function_id`) VALUES (10,'首页', 0, '', 0, 0);

INSERT INTO tb_functions (`function_id`, `name`, `order`, `path`, `type`, `parent_function_id`) VALUES (1000,'权限管理', 0, '', 0, 0);
INSERT INTO tb_functions (`function_id`, `name`, `order`, `path`, `type`, `parent_function_id`) VALUES (1001,'用户管理', 1, '', 0, 1000);
INSERT INTO tb_functions (`function_id`, `name`, `order`, `path`, `type`, `parent_function_id`) VALUES (1002,'角色管理', 2, '', 0, 1000);
INSERT INTO tb_functions (`function_id`, `name`, `order`, `path`, `type`, `parent_function_id`) VALUES (1003,'功能点管理',3, '', -1, 1000);

INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10011,1001, '查看用户');
INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10012,1001, '新增用户');
INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10013,1001, '编辑用户');
INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10014,1001, '删除用户');

-- INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10021,1001, '查看角色');
-- INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10022,1001, '新增角色');
-- INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10023,1001, '编辑角色');
-- INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10024,1001, '删除角色');
-- INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10025,1001, '查看角色');
-- INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10026,1001, '新增角色');
-- INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10027,1001, '编辑角色');
-- INSERT INTO tb_functions_items (`function_item_id`, `function_id`, `item_name`) VALUES (10028,1001, '删除角色');

DROP TABLE IF EXISTS tb_roles;
CREATE TABLE tb_roles (
                          `role_id` INT NOT NULL AUTO_INCREMENT,
                          `code` VARCHAR(10) NOT NULL,
                          `name` VARCHAR(200) NULL,
                          `type` TINYINT NOT NULL DEFAULT 1,
                          `status` TINYINT NOT NULL DEFAULT 0,
                          PRIMARY KEY (`role_id`)
)DEFAULT CHARSET=utf8;

INSERT INTO tb_roles (`role_id`, `code`, `name`, `type`, `status` ) VALUES (1, 'admin', 'admin', 0, 1);

DROP TABLE IF EXISTS tb_roles_functions;
CREATE TABLE tb_roles_functions (
                                    `row_id` INT NOT NULL AUTO_INCREMENT,
                                    `role_id` INT NOT NULL,
                                    `function_id` INT NOT NULL,
                                    `status` TINYINT NOT NULL DEFAULT 0,
                                    PRIMARY KEY (`row_id`)
)DEFAULT CHARSET=utf8;

INSERT INTO tb_roles_functions (`role_id`, `function_id`, `status`) VALUES (1,10,1);

INSERT INTO tb_roles_functions (`role_id`, `function_id`, `status`) VALUES (1,1000,1);
INSERT INTO tb_roles_functions (`role_id`, `function_id`, `status`) VALUES (1,1001,1);
INSERT INTO tb_roles_functions (`role_id`, `function_id`, `status`) VALUES (1,1002,1);
INSERT INTO tb_roles_functions (`role_id`, `function_id`, `status`) VALUES (1,1003,1);

DROP TABLE IF EXISTS tb_roles_items;
CREATE TABLE tb_roles_items (
                                `row_id` INT NOT NULL AUTO_INCREMENT,
                                `role_id` INT NOT NULL,
                                `item_id` INT NOT NULL,
                                `status` TINYINT NOT NULL DEFAULT 0,
                                PRIMARY KEY (`row_id`)
)DEFAULT CHARSET=utf8;
