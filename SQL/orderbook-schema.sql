DROP DATABASE IF EXISTS orderbookdb;

CREATE DATABASE orderbookdb;

USE orderbookdb;

CREATE TABLE ob_order (
	id 				INT 													AUTO_INCREMENT,
	version 		INT 													DEFAULT 0,
    symbol 			VARCHAR(8) 												NOT NULL,
	price 			DECIMAL(10,2) 											NOT NULL,
	orderSize 		INT 													NOT NULL,
	side 			ENUM('BUY', 'SELL') 									NOT NULL,
    numberMatched 	INT 													NOT NULL,
    placedAt 		DATETIME 												NOT NULL,
	userSymbol 		VARCHAR(8) 												NOT NULL,
	status 			ENUM('PENDING', 'ACTIVE', 'CANCELLED', 'FULFILLED') 	NOT NULL,
    CONSTRAINT PK_ob_order 
    	PRIMARY KEY (id, version)
);

CREATE TABLE trade (
	id 				INT 		primary key 	AUTO_INCREMENT,
    buyId 			INT 		NOT NULL,
	buyVersion		INT			NOT NULL,
	sellId 			INT 		NOT NULL,
	sellVersion		INT 		NOT NULL,
	tradeTime 		DATETIME 	NOT NULL,
	tradePrice 		DECIMAL 	NOT NULL,
    tradeSize 		INT 		NOT NULL,
    CONSTRAINT FK_trade_buy
    	FOREIGN KEY (buyId, buyVersion)
    	REFERENCES ob_order(id, version),
    CONSTRAINT FK_trade_sell
    	FOREIGN KEY (sellId, sellVersion)
    	REFERENCES ob_order(id, version)
);

DROP DATABASE IF EXISTS orderbookdbtest;

CREATE DATABASE orderbookdbtest;

USE orderbookdbtest;