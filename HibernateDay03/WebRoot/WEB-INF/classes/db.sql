CREATE TABLE EMP(
  ID			NUMBER(4) CONSTRAINT EMP_ID_PK PRIMARY KEY,
  NAME		VARCHAR(50)  NOT NULL,
  AGE		NUMBER(11),
  SALARY		NUMBER(7,2),
  MARRY 		CHAR(1),
  BIRTHDAY 	DATE,
  LAST_LOGIN_TIME	DATE
  );

CREATE SEQUENCE emp_seq start with 10;

insert into emp 
values(1,'张三',25,6000.00,'N',null,null);
insert into emp 
values(2,'李四',26,7000.00,'N',null,null);
insert into emp 
values(3,'王五',27,8000.00,'N',null,null);
select * from emp;




