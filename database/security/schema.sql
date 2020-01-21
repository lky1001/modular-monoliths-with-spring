-- spring security oauth (https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql)
CREATE TABLE `users`.`oauth_access_token`
(
    token_id          VARCHAR(256) NULL,
    authentication_id VARCHAR(128) NOT NULL
        PRIMARY KEY,
    user_name         VARCHAR(256) NULL,
    client_id         VARCHAR(256) NULL,
    refresh_token     VARCHAR(256) NULL,
    token             BLOB         NULL,
    authentication    BLOB         NULL
)
    ENGINE = InnoDB;

CREATE TABLE `users`.`oauth_client_details`
(
    client_id               VARCHAR(256)  NULL,
    resource_ids            VARCHAR(256)  NULL,
    client_secret           VARCHAR(256)  NULL,
    scope                   VARCHAR(256)  NULL,
    authorized_grant_types  VARCHAR(256)  NULL,
    web_server_redirect_uri VARCHAR(256)  NULL,
    authorities             VARCHAR(256)  NULL,
    access_token_validity   INT           NULL,
    refresh_token_validity  INT           NULL,
    additional_information  VARCHAR(4096) NULL,
    autoapprove             VARCHAR(256)  NULL,
    id                      VARCHAR(20)   NOT NULL
        PRIMARY KEY
)
    ENGINE = InnoDB;

CREATE TABLE `users`.`oauth_refresh_token`
(
    token_id       VARCHAR(256) NULL,
    token          BLOB         NULL,
    authentication BLOB         NULL
)
    ENGINE = InnoDB;
