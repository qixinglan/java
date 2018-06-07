
select * from produce;
select salary,age from Emp1;
create sequence xuhao start with 1 increment by 1;
create table users(id number(4) primary key,name varchar2(20),pwd varchar2(30),age number(2));
insert into users values(xuhao.nextval,'π»¡¡','110210326',23);

create table produce(id number(4) primary key,model varchar2(40),imagedes varchar2(40),producedes varchar2(200),price number(6,2));
insert into produce values(xuhao.nextval,'x200','x200.jpg','good',2000);
insert into produce values(xuhao.nextval,'x300','x300.jpg','good',3000);
insert into produce values(xuhao.nextval,'x400','x400.jpg','good',4000);
update produce set imagedes='x500.jpg' where id=3;
create table t_account(id number(4), username varchar2(40),balance number(8,2),pwd varchar2(40));
insert into t_account values(xuhao.nextval,'guliang',1000,'110210326');
