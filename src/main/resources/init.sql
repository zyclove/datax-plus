DROP DATABASE if exists datax_service;

CREATE database datax_service;

USE datax_service;

DROP TABLE IF EXISTS tb_job;
CREATE TABLE tb_job (
    `job_id` INT NOT NULL AUTO_INCREMENT,
    `job_name` VARCHAR(100),
    `create_time` datetime NOT NULL DEFAULT NOW(),
    PRIMARY KEY(`job_id`)
)DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_job_log;
CREATE TABLE tb_job_log (
    `log_row_id` INT NOT NULL AUTO_INCREMENT,
    `job_id` INT,
    `log_id` INT,
    `log_body` TEXT,
    `create_time` datetime NOT NULL DEFAULT NOW(),
    PRIMARY KEY(`log_row_id`)
)DEFAULT CHARSET=utf8;

