DROP DATABASE if exists datax_service;

CREATE database datax_service;

USE datax_service;

DROP TABLE IF EXISTS tb_job_logs;
CREATE TABLE tb_job_logs (
    `accountId` INT NOT NULL AUTO_INCREMENT,
    `userName` VARCHAR(50) NOT NULL,
    `realName` VARCHAR(50) NOT NULL DEFAULT '',
    `password` VARCHAR(50) NOT NULL,
    `phoneNumber` VARCHAR(50) NULL DEFAULT '',
    `address` VARCHAR(200) NULL DEFAULT '',
    `major` VARCHAR(200) NULL DEFAULT '',
    `gender` int not null default 1,
    `age` int not null default 1,
    `userType` int not null default 1,
    `createTime` datetime NOT NULL DEFAULT '1970-01-01',
    PRIMARY KEY(`accountId`)
)DEFAULT CHARSET=utf8;