delete from outbox_message where id is not null;

delete from user_authority where user_id > 0;
delete from user where id > 0;
