# Overview

## Solution - How to run

Precondition: Database (with structures). See below for setup in a lower environment.

Start system

```
docker compose -f wallet-be/docker/docker-compose.yml -p transaction-ppfbanka up
```

Send REST requests to http://localhost:8080/ , e.g.

```
GET /accounts/2002222222/transactions
```

## Assignment

Vytvořte backendovou mikroslužbu, která nad definovaným modelem (ta_database.sql) bude vracet seznam transakcí
transparentního účtu pro front end.

Rozhraní služby je REST
Pohyby na účtu
GET /accounts/2002222222/transactions
odpověď viz. ta-2002222222-transactions-response.json

Hodnota 2002222222 v cestě je číslo účtu. V modelu je uvedené jako ownAccountNumber.

Zabezpečení, stránkování a výkonové optimalizace pro tuto úlohu neřešíme.

### Given data model

```
-- MS SQL server
CREATE TABLE [transaction](
	[trxId]                    [bigint] IDENTITY(1000,1) NOT NULL,
	[amount]                   [numeric](19, 2)    NOT NULL,
	[currency]                 [nvarchar](3)       NOT NULL,
	[id]                       [nvarchar](20)      NOT NULL,
	[bankref]                  [nvarchar](20)      NOT NULL,
	[transactionId]            [nvarchar](20)      NOT NULL,        
	[bookingDate]              [date]              NULL,
	[postingDate]              [date]              NOT NULL,
	[creditDebitIndicator]     [varchar](4)        NULL,
	[ownAccountNumber]         [nvarchar](20)      NULL,
	[counterPartyAccount]      [bigint]            NOT NULL,
	[detail1]                  [nvarchar](50)      NULL,
	[detail2]                  [nvarchar](50)      NULL,
	[detail3]                  [nvarchar](50)      NULL,
	[detail4]                  [nvarchar](50)      NULL,
	[productBankRef]           [nvarchar](50)      NULL,
	[transactionType]          [bigint]            NOT NULL,
	[statement]                [bigint]            NOT NULL,   
	[constantSymbol]           [varchar](10)       NULL,
	[specificSymbol]           [varchar](10)       NULL,    
	[variableSymbol]           [varchar](10)       NULL
)
GO

CREATE TABLE [transactionType](
	[trxTypeId]                [bigint] IDENTITY(1000,1) NOT NULL,
	[type]                     [nvarchar](20)            NOT NULL,
	[code]                     [int]                     NOT NULL
)
GO

CREATE TABLE [statement](
	[statementId]              [bigint] IDENTITY(1000,1) NOT NULL,
	[number]                   [nvarchar](20)            NOT NULL,
	[period]                   [nvarchar](20)            NOT NULL,
	[description]              [nvarchar](1000)          NULL
)
GO

CREATE TABLE [account](
	[accountId]              [bigint] IDENTITY(1000,1) NOT NULL,
	[name]                   [nvarchar](50)            NOT NULL,
	[number]                 [nvarchar](20)            NOT NULL,
	[code]                   [nvarchar](4)             NOT NULL
)
GO


ALTER TABLE [transaction]
    ADD CONSTRAINT PK_transaction_trxId PRIMARY KEY (trxId)
GO

ALTER TABLE [transactionType]
    ADD CONSTRAINT PK_transactionType_trxTypeId PRIMARY KEY (trxTypeId)
GO

ALTER TABLE [statement]
    ADD CONSTRAINT PK_statement_statementId PRIMARY KEY (statementId)
GO

ALTER TABLE [account]
    ADD CONSTRAINT PK_account_accountId PRIMARY KEY (accountId)
GO

ALTER TABLE [transaction] 
    ADD CONSTRAINT FK_transaction_counterPartyAccount FOREIGN KEY (counterPartyAccount) REFERENCES account(accountId)
GO    

ALTER TABLE [transaction] 
    ADD CONSTRAINT FK_transaction_transactionType FOREIGN KEY (transactionType) REFERENCES transactionType(trxTypeId)
GO  

ALTER TABLE [transaction] 
    ADD CONSTRAINT FK_transaction_statement FOREIGN KEY (statement) REFERENCES statement(statementId)
GO   
```

### Sample response

```
[ 
{
    "amount": {
        "currency": "CZK",
        "value": 1500
    },
    "bankref": "PS221019SO314822",
    "bookingDate": "2022-10-19",
    "counterPartyAccount": {
        "accountName": "PPF BANKA A.S.",
        "accountNumber": "0000009504010019",
        "bankCode": "6000"
    },
    "creditDebitIndicator": "CRDT",
    "details": {
        "detail1": "Posílám peníze"
    },
    "id": "20221019:0000000219",
    "ownAccountNumber": "2002222222",
    "postingDate": "2022-10-19",
    "productBankRef": "PS221019SO314822",
    "specificSymbol": "12",
    "statementNumber": "196",
    "statementPeriod": "2022",
    "transactionId": "4831716",
    "transactionType": "DPO",
    "transactionTypeCode": 1012209,
    "variableSymbol": "12"
},
{
    "amount": {
        "currency": "CZK",
        "value": 1999
    },
    "bankref": "PS221019SO314822",
    "bookingDate": "2022-10-19",
    "counterPartyAccount": {
        "accountName": "PPF BANKA A.S.",
        "accountNumber": "0000009505020008",
        "bankCode": "6000"
    },
    "creditDebitIndicator": "CRDT",
    "details": {
        "detail1": "Trvalý příkaz 8"
    },
    "id": "20221019:0000000220",
    "ownAccountNumber": "2002222222",    
    "postingDate": "2022-10-19",
    "productBankRef": "PS221019SO314822",
    "specificSymbol": "12",
    "statementNumber": "196",
    "statementPeriod": "2022",
    "transactionId": "4831701",
    "transactionType": "DPO",
    "transactionTypeCode": 0,
    "variableSymbol": "12"
},
{
    "amount": {
        "currency": "CZK",
        "value": 2000
    },
    "bankref": "PS221019SO314823",
    "bookingDate": "2022-10-19",
    "counterPartyAccount": {
        "accountName": "PPF BANKA A.S.",
        "accountNumber": "0000009503010009",
        "bankCode": "6000"
    },
    "creditDebitIndicator": "CRDT",
    "details": {
        "detail1": "Na dárky"
    },
    "id": "20221019:0000000221",
    "ownAccountNumber": "2002222222",
    "postingDate": "2022-10-19",
    "productBankRef": "PS221019SO314823",
    "specificSymbol": "61",
    "statementNumber": "196",
    "statementPeriod": "2022",
    "transactionId": "4831700",
    "transactionType": "DPO",
    "transactionTypeCode": 1012209,
    "variableSymbol": "61"
},
{
    "amount": {
        "currency": "CZK",
        "value": 100
    },    
    "bankref": "PS221018SO314645",
    "bookingDate": "2022-10-18",
    "counterPartyAccount": {
        "accountName": "PPF BANKA A.S.",
        "accountNumber": "0000009504010019",
        "bankCode": "6000"
    },
    "creditDebitIndicator": "CRDT",
    "details": {
        "detail1": "Příspěvek"
    },
    "id": "20221018:0000003607",
    "ownAccountNumber": "2002222222",
    "postingDate": "2022-10-18",
    "productBankRef": "PS221018SO314645",
    "specificSymbol": "12",
    "statementNumber": "195",
    "statementPeriod": "2022",
    "transactionId": "4831425",
    "transactionType": "DPO",
    "transactionTypeCode": 1012209,
    "variableSymbol": "12"
},
{
    "amount": {
        "currency": "CZK",
        "value": 1594
    },
    "bankref": "PS221018SO314645",
    "bookingDate": "2022-10-18",
    "counterPartyAccount": {
        "accountName": "PPF BANKA A.S.",
        "accountNumber": "0000009505020008",
        "bankCode": "6000"
    },
    "creditDebitIndicator": "DBIT",
    "details": {
        "detail1": "Platba elektřiny"
    },
    "id": "20221018:0000003608",
    "ownAccountNumber": "2002222222",
    "postingDate": "2022-10-18",
    "productBankRef": "PS221018SO314645",
    "specificSymbol": "12",
    "statementNumber": "195",
    "statementPeriod": "2022",
    "transactionId": "4831381",
    "transactionType": "DPO",
    "transactionTypeCode": 0,
    "variableSymbol": "12"
}
]
```

# Technical notes

### Database

Start database in docker.

```
docker compose -f docker/docker-compose.yml -p transaction-ppfbanka up -d db
```

In the dev profile (which is active), database structure is updated automatically based on the model.

The following may be useful for deploying to production (prod profile).

```
apt-get install mysql-client
```

Create structures:

```
mysql -h 127.0.0.1 -P 3306 -u user -D db -p
source src/main/resources/schema.sql
\q
```

Client (for testing):

```
mysql -h 127.0.0.1 -P 3306 -u user -D db -p
```

## Testing

Run TransactionControllerTest.
