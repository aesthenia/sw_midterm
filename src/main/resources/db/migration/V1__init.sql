create table t_user (
        id bigserial primary key ,
        email varchar(255) unique ,
        password varchar(255) not null ,
        username varchar(255) unique not null
);