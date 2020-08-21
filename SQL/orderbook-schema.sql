DROP DATABASE IF EXISTS orderbookdb;

CREATE DATABASE orderbookdb;

USE orderbookdb;

CREATE TABLE ob_order (
	id 				INT 													AUTO_INCREMENT,
	version 		INT 													DEFAULT 0,
    stock_id			VARCHAR(8) 												NOT NULL,
	price 			DECIMAL(10,2) 											NOT NULL,
	order_size 		INT 													NOT NULL,
	side 			ENUM('BUY', 'SELL') 									NOT NULL,
    number_matched 	INT 													NOT NULL,
    placed_at 		DATETIME 												NOT NULL,
	user_symbol 		VARCHAR(8) 												NOT NULL,
	status 			ENUM('PENDING', 'ACTIVE', 'CANCELLED', 'FULFILLED') 	NOT NULL,
	
    CONSTRAINT PK_ob_order 
    	PRIMARY KEY (id, version),
	CONSTRAINT FK_ob_order_stock
		FOREIGN KEY (stockId)
		REFERENCES 
);

CREATE TABLE ob_trade (
	id 				INT 		primary key 	AUTO_INCREMENT,
    buy_id 			INT 		NOT NULL,
	buy_version		INT			NOT NULL,
	sell_id 		INT 		NOT NULL,
	sell_version	INT 		NOT NULL,
	trade_time 		DATETIME 	NOT NULL,
	trade_price 	DECIMAL 	NOT NULL,
    trade_size 		INT 		NOT NULL,
	
    CONSTRAINT FK_trade_buy
    	FOREIGN KEY (buy_id, buy_version)
    	REFERENCES ob_order(id, version),
    CONSTRAINT FK_trade_sell
    	FOREIGN KEY (sell_id, sell_version)
    	REFERENCES ob_order(id, version)
);

CREATE TABLE ob_stock (
	id 				INT 			primary key 	AUTO_INCREMENT,
	tick_size		DECIMAL(2,2)	NOT NULL,
	stock_symbol	VARCHAR(8)		NOT NULL
);

CREATE TABLE ob_user (
	id 				INT 			primary key 	AUTO_INCREMENT,
	company_id		INT				NOT NULL,
	symbol			VARCHAR(8)		NOT NULL,
	
	CONSTRAINT FK_ob_user
		FOREIGN KEY (company_id)
		REFERENCES company(id)
);

CREATE TABLE ob_company (
	id 				INT 			primary key 	AUTO_INCREMENT,
	company_symbol	VARCHAR(8)		NOT NULL
);

CREATE TABLE ob_trade_audit (
	id 				INT 			primary key 	AUTO_INCREMENT,
	trade_log		TEXT			NOT NULL
);

CREATE TABLE ob_order_audit (
	id 				INT 			primary key 	AUTO_INCREMENT,
	order_log		TEXT			NOT NULL
);

DROP DATABASE IF EXISTS orderbookdbtest;

CREATE DATABASE orderbookdbtest;

USE orderbookdbtest;