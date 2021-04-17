CREATE TABLE if not exists accounts (
    ID int NOT NULL AUTO_INCREMENT,
   accountNumber varchar(255) NOT NULL UNIQUE,
   accountName varchar(255) NOT NULL,
   accountType varchar(255) NOT NULL,
    Balance DOUBLE(40,2) NOT NULL,
    PRIMARY KEY (ID)
);


CREATE TABLE if not exists accountsHistory (
    ID int NOT NULL AUTO_INCREMENT,
   accountNumber varchar(255) NOT NULL,
   transactionType varchar(255) NOT NULL,
    Amount DOUBLE(40,2) NOT NULL,
   closedBalance DOUBLE(40,2) NOT NULL,
   transactionDate DATE,
    PRIMARY KEY (ID)
);