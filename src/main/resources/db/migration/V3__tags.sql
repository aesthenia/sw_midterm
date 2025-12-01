create table t_tag (
                       id bigserial primary key ,
                       name varchar(255)
);

create table t_tasks_tags (
                              task_id bigint not null,
                              tag_id bigint not null,
                              constraint fk_tasks_tags_task foreign key (task_id) references t_task(id),
                              constraint fk_tasks_tags_tag foreign key (tag_id) references t_tag(id)
);