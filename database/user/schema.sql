-- -----------------------------------------------------
-- Schema users
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `users` DEFAULT CHARACTER SET utf8mb4;
USE `users`;

-- -----------------------------------------------------
-- Table `users`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users`.`user`
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
-- Table `users`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users`.`role`
(
    `id`      INT         NOT NULL,
    `name`    VARCHAR(45) NULL,
    `desc`    VARCHAR(45) NULL,
    `created` DATETIME    NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `users`.`user_authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users`.`user_authority`
(
    `user_id` BIGINT NOT NULL,
    `role_id` INT    NOT NULL,
    INDEX `fk_user_authority_user_idx` (`user_id` ASC),
    INDEX `fk_user_authority_role1_idx` (`role_id` ASC),
    PRIMARY KEY (`user_id`, `role_id`),
    CONSTRAINT `fk_user_authority_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `users`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_authority_role1`
        FOREIGN KEY (`role_id`)
            REFERENCES `users`.`role` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;
