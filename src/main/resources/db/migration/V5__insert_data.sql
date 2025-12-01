insert into t_permission (name) values ('ROLE_ADMIN');
insert into t_permission (name) values ('ROLE_USER');

insert into t_user (username, email, password)
values
    ('admin', 'admin@example.com', '$2a$10$l.EXFjdEt1ggyjWNr32e3uCOIPSpuoYNchag6wJ3EDfoWvMmeK.8O'),
    ('user', 'user@example.com', '$2a$10$l.EXFjdEt1ggyjWNr32e3uCOIPSpuoYNchag6wJ3EDfoWvMmeK.8O');


insert into t_user_permissions (user_id, permissions_id) values (1, 1);
insert into t_user_permissions (user_id, permissions_id) values (2, 2);

insert into t_tag (name) values ('Work'), ('Study'), ('Not important');

insert into t_task (title, description, done, user_id)
values ('Prepare Midterm', 'Complete Java project', false, 2);

insert into t_tasks_tags (task_id, tag_id) values (1, 2);