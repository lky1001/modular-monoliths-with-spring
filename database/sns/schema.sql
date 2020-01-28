-- -----------------------------------------------------
-- Schema sns
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sns` DEFAULT CHARACTER SET utf8mb4;
USE `sns`;

-- -----------------------------------------------------
-- Table `sns`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sns`.`user`
(
    `userId` BIGINT         NOT NULL,
    `name`   VARCHAR(45) NULL,
    PRIMARY KEY (`userId`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sns`.`outbox_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sns`.`outbox_message`
(
    `id`             CHAR(36)     NOT NULL,
    `occurred_on`    DATETIME     NULL,
    `type`           VARCHAR(200) NULL,
    `data`           TEXT         NULL,
    `processed_date` DATETIME     NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;
