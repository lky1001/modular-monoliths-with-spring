delete from `users`.outbox_message where id is not null;

delete from `users`.user_authority where user_id > 0;
delete from `users`.user where id > 0;
