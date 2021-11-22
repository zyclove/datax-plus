DROP DATABASE if exists datax_service;

CREATE database datax_service;

USE datax_service;

DROP TABLE IF EXISTS tb_job_logs;
CREATE TABLE tb_job_logs (
    `log_row_id` INT NOT NULL AUTO_INCREMENT,
    `job_id` VARCHAR(50) NOT NULL,
    `log_id` VARCHAR(50) NOT NULL DEFAULT '',
    `log_body` TEXT,
    `create_time` datetime NOT NULL DEFAULT NOW(),
    PRIMARY KEY(`accountId`)
)DEFAULT CHARSET=utf8;