DROP DATABASE if exists datax_service;

CREATE database datax_service;

USE datax_service;

DROP TABLE IF EXISTS tb_job;
CREATE TABLE tb_data_job (
    `data_job_id` INT NOT NULL AUTO_INCREMENT,
    `data_job_name` VARCHAR(100) DEFAULT '',
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

