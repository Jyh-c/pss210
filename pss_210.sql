create database pss_210;
use pss_210;

create table t_employee
(
    eid      int auto_increment
        primary key,
    name     varchar(30) null,
    sex      varchar(4)  null,
    birthday date        null,
    phone    varchar(11) null,
    state    int(4)      null
)
    charset = utf8;

create table t_product
(
    pid   int auto_increment
        primary key,
    name  varchar(30) null,
    price float       null,
    store int         null,
    state int(4)      null
)
    charset = utf8;

create table t_sell
(
    sid      varchar(20) not null
        primary key,
    pid      int         null,
    eid      int         null,
    amount   int         null,
    selldate date        null,
    state    int(4)      null,
    constraint eid_t_employee_eid
        foreign key (eid) references t_employee (eid),
    constraint pid_t_product_pid
        foreign key (pid) references t_product (pid)
            on update cascade on delete set null
)
    collate = utf8_german2_ci;