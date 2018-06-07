create table t_emp(
id number(4) primary key,
name varchar2(20),
salary number(7,2),
age number(3)
);
select * from t_emp;
create sequence emp_id_seq increment by 1 start with 1
insert into t_emp values (emp_id_seq.nextval,'уехЩ',3000,20)
delete from t_emp where id=1;