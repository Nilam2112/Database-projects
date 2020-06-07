CREATE TABLE BOOK (
Book_ID INT NOT NULL,
Title varchar(50) NOT NULL,
Author varchar(50) NOT NULL,
Publisher varchar(50) NOT NULL,
PRIMARY KEY(Book_ID)
);
/
DESC BOOK

CREATE TABLE CATLOG(
 ISBN varchar(50) NOT NULL,
 No_Of_Copies_Available int NOT NULL,
 No_Of_Copies_Rented int NOT NULL,
 Subject_Area varchar(40) NOT NULL,
 Description varchar(100) NOT NULL,
 PRIMARY KEY(ISBN)
);
/
DESC CATLOG


CREATE TABLE BOOK_LOGS(
Book_ID INT NOT NULL ,
ISBN varchar(50) NOT NULL, 
FOREIGN KEY(Book_ID)REFERENCES BOOK(Book_ID),
FOREIGN KEY(ISBN)REFERENCES CATLOG(ISBN)
);

DESC BOOK_LOGS 

CREATE TABLE MEMBER(
 Card_No int NOT NULL,
 Name varchar(50) NOT NULL,
 Phone int,
 SSN int NOT NULL,
 Campus_Address varchar(50),
 Home_Address varchar(50),
 Member_Type varchar(50),
 PRIMARY KEY(Card_No),
 UNIQUE(SSN)
 );

DESC MEMBER

CREATE TABLE LOANS(
 Book_ID int NOT NULL,
 Card_No int NOT NULL,
 Date_Out DATE, 
 Due_Date DATE,
 FOREIGN KEY(Book_ID)REFERENCES BOOK(Book_ID),
 FOREIGN KEY(Card_No)REFERENCES MEMBER(Card_No)
);
 
 DESC LOANS
 
 CREATE TABLE BORROWER(
 Card_No int NOT NULL,
 Name varchar(50) NOT NULL,  
 Membership_Validity DATE,
 FOREIGN KEY(Card_No)REFERENCES MEMBER(Card_No)
 );
 
 DESC BORROWER
 
 CREATE TABLE LIBRARY_STAFF(
 SSN int NOT NULL, 
 Name varchar(50) NOT NULL,
 Position varchar(50), 
 Issued_Book_ID int NOT NULL,
 PRIMARY KEY(SSN),
 FOREIGN KEY(Issued_Book_ID)REFERENCES BOOK(Book_ID)
 );

 DESC LIBRARY_STAFF
 