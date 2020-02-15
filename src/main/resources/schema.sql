create table employee
(
    id           long auto_increment
        primary key,
    firstname         varchar(45) null,
    lastname      varchar(45) null,
    email   varchar(45) null,
    title varchar(45) null
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(45)  null,
    password varchar(255) null
);
