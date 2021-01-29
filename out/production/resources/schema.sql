-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema EMPDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `EMPDB` ;

-- -----------------------------------------------------
-- Schema EMPDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EMPDB` DEFAULT CHARACTER SET utf8 ;
USE `EMPDB` ;

-- -----------------------------------------------------
-- Table `EMPDB`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EMPDB`.`category` ;

CREATE TABLE IF NOT EXISTS `EMPDB`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `EMPDB`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EMPDB`.`user` ;

CREATE TABLE IF NOT EXISTS `EMPDB`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(12) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NULL,
  `phone_number` VARCHAR(13) NOT NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `EMPDB`.`partner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EMPDB`.`partner` ;

CREATE TABLE IF NOT EXISTS `EMPDB`.`partner` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `call_center` VARCHAR(13) NULL,
  `partner_number` VARCHAR(13) NULL,
  `business_number` VARCHAR(16) NULL,
  `ceo_name` VARCHAR(20) NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `EMPDB`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EMPDB`.`item` ;

CREATE TABLE IF NOT EXISTS `EMPDB`.`item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NULL,
  `price` DECIMAL(12,4) NOT NULL,
  `brand_name` VARCHAR(50) NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  `partner_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `EMPDB`.`order_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EMPDB`.`order_group` ;

CREATE TABLE IF NOT EXISTS `EMPDB`.`order_group` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(20) NOT NULL,
  `order_type` VARCHAR(50) NOT NULL,
  `rev_address` TEXT NOT NULL,
  `rev_name` VARCHAR(50) NULL,
  `payment_type` VARCHAR(50) NOT NULL,
  `total_price` DECIMAL(12,4) NOT NULL,
  `total_quantity` INT NOT NULL,
  `order_at` DATETIME NULL,
  `arrival_date` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `EMPDB`.`order_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EMPDB`.`order_detail` ;

CREATE TABLE IF NOT EXISTS `EMPDB`.`order_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `arrival_date` DATETIME NULL,
  `quantity` INT NOT NULL,
  `total_price` DECIMAL(12,4) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  `order_group_id` BIGINT NOT NULL,
  `item_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `EMPDB`.`admin_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EMPDB`.`admin_user` ;

CREATE TABLE IF NOT EXISTS `EMPDB`.`admin_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(12) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `last_login_at` DATETIME NULL,
  `password_updated_at` DATETIME NULL,
  `login_fail_count` INT NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
