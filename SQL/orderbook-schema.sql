DROP DATABASE IF EXISTS orderbookdb;

CREATE DATABASE orderbookdb;

USE orderbookdb;

CREATE TABLE ob_order (
	id INT primary key AUTO_INCREMENT,
    symbol VARCHAR(8) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	orderSize INT NOT NULL,
	side ('BUY', 'SELL') NOT NULL,
    numberMatched INT NOT NULL,
    placedAt DATETIME NOT NULL,
	status ENUM('ACTIVE', 'CANCELLED', 'FULFILLED') NOT NULL
);

DROP DATABASE IF EXISTS orderbookdbtest;

CREATE DATABASE orderbookdbtest;

USE orderbookdbtest;