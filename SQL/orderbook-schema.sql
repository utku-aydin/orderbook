DROP DATABASE IF EXISTS orderbookdb;

CREATE DATABASE orderbookdb;

USE orderbookdb;

CREATE TABLE ob_order (
	id 				INT 										AUTO_INCREMENT,
	version_id 		INT 										DEFAULT 0,
    symbol 			VARCHAR(8) 									NOT NULL,
	price 			DECIMAL(10,2) 								NOT NULL,
	orderSize 		INT 										NOT NULL,
	side 			ENUM('BUY', 'SELL') 						NOT NULL,
    numberMatched 	INT 										NOT NULL,
    placedAt 		DATETIME 									NOT NULL,
	userSymbol 		VARCHAR(8) 									NOT NULL,
	status 			ENUM('ACTIVE', 'CANCELLED', 'FULFILLED') 	NOT NULL,
    CONSTRAINT PK_OB_ORDER 
    	PRIMARY KEY (id, version_id)
);

CREATE TABLE trade (
	id 				INT 		primary key 	AUTO_INCREMENT,
    buyId 			INT 		NOT NULL,
	sellId 			INT 		NOT NULL,
	tradeTime 		DATETIME 	NOT NULL,
	tradePrice 		DECIMAL 	NOT NULL,
    tradeSize 		INT 		NOT NULL,
    CONSTRAINT FK_trade_buyId
    	FOREIGN KEY (buyId)
    	REFERENCES ob_order(id),
    CONSTRAINT FK_trade_sellId
    	FOREIGN KEY (sellId)
    	REFERENCES ob_order(id)
);

DROP DATABASE IF EXISTS orderbookdbtest;

CREATE DATABASE orderbookdbtest;

USE orderbookdbtest;