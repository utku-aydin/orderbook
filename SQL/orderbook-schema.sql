DROP DATABASE IF EXISTS orderbookdb;

CREATE DATABASE orderbookdb;

USE orderbookdb;

CREATE TABLE 'order' (
	id INT AUTO_INCREMENT,
    symbol VARCHAR(8) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	'size' INT NOT NULL,
	side VARCHAR(3) NOT NULL,
    numberMatched INT NOT NULL,
    placedAt DATETIME NOT NULL,
	fulfilled boolean NOT NULL,
	
	CONSTRAINT pk_order
		PRIMARY KEY (id)
);

DROP DATABASE IF EXISTS orderbookdbtest;

CREATE DATABASE orderbookdbtest;

USE orderbookdbtest;