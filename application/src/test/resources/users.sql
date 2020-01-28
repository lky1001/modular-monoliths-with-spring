-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user`
(
    `id`       BIGINT       NOT NULL AUTO_INCREMENT,
    `email`    VARCHAR(100) NOT NULL,
    `password` VARCHAR(500) NOT NULL,
    `name`     VARCHAR(45)  NOT NULL,
    `created`  DATETIME     NULL,
    `updated`  DATETIME     NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role`
(
    `id`      INT         NOT NULL,
    `name`    VARCHAR(45) NULL,
    `desc`    VARCHAR(45) NULL,
    `created` DATETIME    NULL,
    PRIMARY KEY (`id`)
);


-- -----------------------------------------------------
-- Table `user_authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_authority`
(
    `user_id` BIGINT NOT NULL,
    `role_id` INT    NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`)
);

-- -----------------------------------------------------
-- Table `users`.`outbox_message`
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

INSERT INTO `role` SELECT * FROM (SELECT 1, 'ROLE_ADMIN', 'normal admin', now()) AS tmp
WHERE NOT EXISTS (
        SELECT id FROM role WHERE id = 1
    ) LIMIT 1;

INSERT INTO `role` SELECT * FROM (SELECT 2, 'ROLE_USER', 'normal user', now()) AS tmp
WHERE NOT EXISTS (
        SELECT id FROM role WHERE id = 2
    ) LIMIT 2;
