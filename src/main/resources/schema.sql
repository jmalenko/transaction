-- MySQL
CREATE TABLE transaction(
	trxId                    bigint            NOT NULL,
	amount                   numeric(19, 2)    NOT NULL,
	currency                 nvarchar(3)       NOT NULL,
	id                       nvarchar(20)      NOT NULL,
	bankref                  nvarchar(20)      NOT NULL,
	transactionId            nvarchar(20)      NOT NULL,        
	bookingDate              date              NULL,
	postingDate              date              NOT NULL,
	creditDebitIndicator     varchar(4)        NULL,
	ownAccountNumber         nvarchar(20)      NULL,
	counterPartyAccount      bigint            NOT NULL,
	detail1                  nvarchar(50)      NULL,
	detail2                  nvarchar(50)      NULL,
	detail3                  nvarchar(50)      NULL,
	detail4                  nvarchar(50)      NULL,
	productBankRef           nvarchar(50)      NULL,
	transactionType          bigint            NOT NULL,
	statement                bigint            NOT NULL,   
	constantSymbol           varchar(10)       NULL,
	specificSymbol           varchar(10)       NULL,    
	variableSymbol           varchar(10)       NULL
);

CREATE TABLE transactionType(
	trxTypeId                bigint                  NOT NULL,
	type                     nvarchar(20)            NOT NULL,
	code                     int                     NOT NULL
);

CREATE TABLE statement(
	statementId              bigint                  NOT NULL,
	number                   nvarchar(20)            NOT NULL,
	period                   nvarchar(20)            NOT NULL,
	description              nvarchar(1000)          NULL
);

CREATE TABLE account(
	accountId              bigint                  NOT NULL,
	name                   nvarchar(50)            NOT NULL,
	number                 nvarchar(20)            NOT NULL,
	code                   nvarchar(4)             NOT NULL
);


ALTER TABLE transaction
    ADD CONSTRAINT PK_transaction_trxId PRIMARY KEY (trxId)
;

ALTER TABLE transactionType
    ADD CONSTRAINT PK_transactionType_trxTypeId PRIMARY KEY (trxTypeId)
;

ALTER TABLE statement
    ADD CONSTRAINT PK_statement_statementId PRIMARY KEY (statementId)
;

ALTER TABLE account
    ADD CONSTRAINT PK_account_accountId PRIMARY KEY (accountId)
;

ALTER TABLE transaction 
    ADD CONSTRAINT FK_transaction_counterPartyAccount FOREIGN KEY (counterPartyAccount) REFERENCES account(accountId)
;

ALTER TABLE transaction 
    ADD CONSTRAINT FK_transaction_transactionType FOREIGN KEY (transactionType) REFERENCES transactionType(trxTypeId)
;

ALTER TABLE transaction 
    ADD CONSTRAINT FK_transaction_statement FOREIGN KEY (statement) REFERENCES statement(statementId)
;
