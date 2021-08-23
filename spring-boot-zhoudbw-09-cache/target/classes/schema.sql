drop table if exists employee;
create table employee(
    id int primary key auto_increment,
    name varchar,
    job varchar,
    birthday varchar,
    sex varchar
);