-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_escola
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_escola
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_escola` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_escola` ;

-- -----------------------------------------------------
-- Table `db_escola`.`tb_alunos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_escola`.`tb_alunos` (
  `id` INT NOT NULL,
  `nome` VARCHAR(250) NULL DEFAULT NULL,
  `idade` INT NULL DEFAULT NULL,
  `curso` VARCHAR(45) NULL DEFAULT NULL,
  `fase` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_escola`.`tb_professores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_escola`.`tb_professores` (
  `id` INT NOT NULL,
  `nome` VARCHAR(250) NULL DEFAULT NULL,
  `idade` INT NULL DEFAULT NULL,
  `campus` VARCHAR(45) NULL DEFAULT NULL,
  `cpf` VARCHAR(14) NULL DEFAULT NULL,
  `contato` VARCHAR(16) NULL DEFAULT NULL,
  `titulo` VARCHAR(45) NULL DEFAULT NULL,
  `salario` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
