create table t_task (
                        id bigserial primary key ,
                        description varchar(255),
                        done boolean not null default false,
                        title varchar(255),
                        user_id bigint,
                        constraint fk_task_user foreign key (user_id) references t_user(id)
);