CREATE SCHEMA IF NOT EXISTS testdb;
USE testdb;

DROP TABLE IF EXISTS `expenditure`;
DROP TABLE IF EXISTS `income`;
DROP TABLE IF EXISTS `statement`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    currency CHAR(3) NOT NULL
);

CREATE TABLE `statement` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES `user`(id),
    reported_date varchar(50) NOT NULL
);

CREATE TABLE `income` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    statement_id BIGINT NOT NULL REFERENCES `statement`(id),
    category VARCHAR(50) NOT NULL,
    amount int NOT NULL
);

CREATE TABLE `expenditure` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    statement_id BIGINT NOT NULL REFERENCES `statement`(id),
    category VARCHAR(50) NOT NULL,
    amount int NOT NULL
);

