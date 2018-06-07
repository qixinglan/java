select * from emp;
create table emp1(id number(5),name varchar2(50));
select * from emp1;
drop table emp1;
insert into emp1 values(2,'Tom');
insert into emp1 values(1,"Andy");
select to_date('2014.5.30','yyyy.mm.dd') from dual;
select sysdate from dual;
update emp1 set name='Andy' where id=1;
delete from emp1 where id=2;
select userenv('language') from  dual;

select length(name) from emp1;
select id||name from emp1;
select concat(id,name) from emp1;
select trim('e' from 'elite') from dual;
select ltrim('elite' from 'e')from dual;