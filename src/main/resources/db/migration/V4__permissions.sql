create table t_permission (
                              id bigserial primary key ,
                              name varchar(255)
);

create table t_user_permissions (
                                    user_id bigint not null,
                                    permissions_id bigint not null ,
                                    constraint fk_user_permissions_user foreign key (user_id) references t_user(id),
                                    constraint fk_user_permissions_permission foreign key (permissions_id) references t_permission(id)
);