INSERT INTO `users`.oauth_client_details (id, client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('1', 'sns-web-service', 'test', '{bcrypt}$2a$10$XP0E1urfGSKEA8woxa4rguUftqfY7OqOHNvolXTDvumftpUo75N1u', 'read,write', 'authorization_code,implicit,password,client_credentials,refresh_token', 'http://localhost/callback', null, 600, 86400, null, 'false');