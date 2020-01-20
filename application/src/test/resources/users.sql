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
);


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


INSERT INTO `role` VALUES (1, 'ROLE_ADMIN', 'normal admin', now());
INSERT INTO `role` VALUES (2, 'ROLE_USER', 'normal user', now());
