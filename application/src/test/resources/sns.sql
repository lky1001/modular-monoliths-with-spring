CREATE TABLE IF NOT EXISTS `user`
(
    `user_id` BIGINT         NOT NULL,
    `name`   VARCHAR(45)    NULL,
    `created`  DATETIME     NULL,
    `updated`  DATETIME     NULL,
    PRIMARY KEY (`user_id`)
);


-- -----------------------------------------------------
-- Table `sns`.`outbox_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `outbox_message`
(
    `id`             CHAR(36)     NOT NULL,
    `occurred_on`    DATETIME     NULL,
    `type`           VARCHAR(200) NULL,
    `data`           VARCHAR(500) NULL,
    `processed_date` DATETIME     NULL,
    PRIMARY KEY (`id`)
);
