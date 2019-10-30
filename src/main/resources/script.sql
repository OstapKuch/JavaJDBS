
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`sellers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`sellers` ;

CREATE TABLE IF NOT EXISTS `mydb`.`sellers` (
  `seller_id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `birthday` VARCHAR(45) NULL,
  PRIMARY KEY (`seller_id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `idSeller_UNIQUE` ON `mydb`.`sellers` (`seller_id` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `mydb`.`sellers` (`email` ASC);

CREATE UNIQUE INDEX `phone_number_UNIQUE` ON `mydb`.`sellers` (`phone_number` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`buyers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`buyers` ;

CREATE TABLE IF NOT EXISTS `mydb`.`buyers` (
  `buyer_id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NULL,
  `birthday` DATE NULL,
  PRIMARY KEY (`buyer_id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `mydb`.`buyers` (`buyer_id` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `mydb`.`buyers` (`email` ASC);

CREATE UNIQUE INDEX `phone_number_UNIQUE` ON `mydb`.`buyers` (`phone_number` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`appartments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`appartments` ;

CREATE TABLE IF NOT EXISTS `mydb`.`appartments` (
  `seller_id` INT NOT NULL,
  `rooms_number` INT NOT NULL,
  `beds_number` INT NOT NULL,
  `hour_price` INT NOT NULL,
  `adress` VARCHAR(45) NOT NULL,
  `appertment_id` INT NOT NULL,
  PRIMARY KEY (`appertment_id`),
  CONSTRAINT `seller_id`
    FOREIGN KEY (`seller_id`)
    REFERENCES `mydb`.`sellers` (`seller_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `appertment_id_UNIQUE` ON `mydb`.`appartments` (`appertment_id` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`appartments_reservations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`appartments_reservations` ;

CREATE TABLE IF NOT EXISTS `mydb`.`appartments_reservations` (
  `reservation_id` INT NOT NULL,
  `settlement_date` DATE NOT NULL,
  `leave_date` DATE NOT NULL,
  `paid` TINYINT(1) NOT NULL,
  PRIMARY KEY (`reservation_id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `reservation_id_UNIQUE` ON `mydb`.`appartments_reservations` (`reservation_id` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`billings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`billings` ;

CREATE TABLE IF NOT EXISTS `mydb`.`billings` (
  `billing_d` INT NOT NULL,
  `date_payed` DATE NOT NULL,
  `buyers_id` INT NOT NULL,
  `sellers_id` INT NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`billing_d`),
  CONSTRAINT `fk_billings_buyers1`
    FOREIGN KEY (`buyers_id`)
    REFERENCES `mydb`.`buyers` (`buyer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_billings_sellers1`
    FOREIGN KEY (`sellers_id`)
    REFERENCES `mydb`.`sellers` (`seller_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_billings_buyers1_idx` ON `mydb`.`billings` (`buyers_id` ASC);

CREATE INDEX `fk_billings_sellers1_idx` ON `mydb`.`billings` (`sellers_id` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`airbnb_wallet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`airbnb_wallet` ;

CREATE TABLE IF NOT EXISTS `mydb`.`airbnb_wallet` (
  `wallet_id` INT NOT NULL,
  `money` INT NULL,
  PRIMARY KEY (`wallet_id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `wallet_id_UNIQUE` ON `mydb`.`airbnb_wallet` (`wallet_id` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`appartments_has_appartments_reservations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`appartments_has_appartments_reservations` ;

CREATE TABLE IF NOT EXISTS `mydb`.`appartments_has_appartments_reservations` (
  `appartments_id` INT NOT NULL,
  `appartments_reservations_id` INT NOT NULL,
  `buyers_id` INT NOT NULL,
  `billings_id` INT NOT NULL,
  `airbnb_wallet_id` INT NOT NULL,
  PRIMARY KEY (`appartments_reservations_id`),
  CONSTRAINT `fk_appartments_has_appartments_reservations_appartments1`
    FOREIGN KEY (`appartments_id`)
    REFERENCES `mydb`.`appartments` (`appertment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appartments_has_appartments_reservations_appartments_reser1`
    FOREIGN KEY (`appartments_reservations_id`)
    REFERENCES `mydb`.`appartments_reservations` (`reservation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appartments_has_appartments_reservations_buyers1`
    FOREIGN KEY (`buyers_id`)
    REFERENCES `mydb`.`buyers` (`buyer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appartments_has_appartments_reservations_billings1`
    FOREIGN KEY (`billings_id`)
    REFERENCES `mydb`.`billings` (`billing_d`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appartments_has_appartments_reservations_airbnb_wallet1`
    FOREIGN KEY (`airbnb_wallet_id`)
    REFERENCES `mydb`.`airbnb_wallet` (`wallet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_appartments_has_appartments_reservations_appartments_res_idx` ON `mydb`.`appartments_has_appartments_reservations` (`appartments_reservations_id` ASC);

CREATE INDEX `fk_appartments_has_appartments_reservations_appartments1_idx` ON `mydb`.`appartments_has_appartments_reservations` (`appartments_id` ASC);

CREATE INDEX `fk_appartments_has_appartments_reservations_buyers1_idx` ON `mydb`.`appartments_has_appartments_reservations` (`buyers_id` ASC);

CREATE INDEX `fk_appartments_has_appartments_reservations_billings1_idx` ON `mydb`.`appartments_has_appartments_reservations` (`billings_id` ASC);

CREATE INDEX `fk_appartments_has_appartments_reservations_airbnb_wallet1_idx` ON `mydb`.`appartments_has_appartments_reservations` (`airbnb_wallet_id` ASC);


INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (1, 'name_surname_1@gmail.com', 'Vasyl', 'Petrov', '097000001', '');
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (2, 'name_surname_2@gmail.com', 'Nazar', 'Adolf ', '097010002', '20.08.1991');
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (3, 'name_surname_3@gmail.com', 'Liam', 'Brown', '097010003', '21.08.1991');
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (4, 'name_surname_4@gmail.com', 'William', 'Smith', '097010004', '20.08.1991');
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (5, 'name_surname_5@gmail.com', 'James', 'Jones', '097010005', '20.08.1991');
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (6, 'name_surname_6@gmail.com', 'Oliver', 'Jackson', '097010006', NULL);
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (7, 'name_surname_7@gmail.com', 'Jacob', 'Phillips', '097010007', NULL);
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (8, 'name_surname_8@gmail.com', 'Lucas', 'Davis', '097010008', '20.08.1978');
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (9, 'name_surname_9@gmail.com', 'Michael', 'Cox', '097010009', NULL);
INSERT INTO `mydb`.`sellers` (`seller_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (10, 'name_surname_10@gmail.com', 'Alexander', 'Quinn ', '097010010', '05.04.1956');


INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (1, 'name_surname_01@gmail.com', 'Muhammad', 'Smith', '097034001', '1956-11-11');
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (2, 'name_surname_02@gmail.com', 'George', 'Johnson', '097034002', NULL);
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (3, 'name_surname_03@gmail.com', 'Harry', 'Williams', '097034003', NULL);
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (4, 'name_surname_04@gmail.com', 'Logan', 'Jones', '097034004', NULL);
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (5, 'name_surname_05@gmail.com', 'Elijah', 'Brown', '097034005', NULL);
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (6, 'name_surname_06@gmail.com', 'Oliver', 'Davis', '097034006', NULL);
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (7, 'name_surname_07@gmail.com', 'Benjamin', 'Miller', '097034007', NULL);
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (8, 'name_surname_08@gmail.com', 'Noah', 'Wilson', '097034008', NULL);
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (9, 'name_surname_09@gmail.com', 'Jack', 'Jones', '097034009', NULL);
INSERT INTO `mydb`.`buyers` (`buyer_id`, `email`, `name`, `surname`, `phone_number`, `birthday`) VALUES (10, 'name_surname_010@gmail.com', 'Leo', 'Miller', '097034010', NULL);


INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (3, 3, 4, 100, 'Street _1', 1);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (1, 2, 2, 2500, 'Street_2', 2);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (1, 5, 8, 500, 'Street_3', 3);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (10, 1, 1, 1000, 'Street_34', 4);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (4, 2, 4, 5000, 'Street_39', 5);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (2, 1, 1, 500, 'Street_5', 6);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (1, 3, 6, 765, 'Street_007', 7);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (7, 2, 3, 987, 'Street_000', 8);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (10, 8, 12, 1999, 'Street_00', 9);
INSERT INTO `mydb`.`appartments` (`seller_id`, `rooms_number`, `beds_number`, `hour_price`, `adress`, `appertment_id`) VALUES (9, 2, 3, 400, 'Street', 10);


INSERT INTO `mydb`.`appartments_reservations` (`reservation_id`, `settlement_date`, `leave_date`, `paid`) VALUES (1, '20.09-2019', '30.09.2019', True);
INSERT INTO `mydb`.`appartments_reservations` (`reservation_id`, `settlement_date`, `leave_date`, `paid`) VALUES (2, '20.09.2019', '30.09.2019', False);
INSERT INTO `mydb`.`appartments_reservations` (`reservation_id`, `settlement_date`, `leave_date`, `paid`) VALUES (3, '20.09.2019', '30.09.2019', False);
INSERT INTO `mydb`.`appartments_reservations` (`reservation_id`, `settlement_date`, `leave_date`, `paid`) VALUES (4, '20.09.2019', '30.09.2019', True);
INSERT INTO `mydb`.`appartments_reservations` (`reservation_id`, `settlement_date`, `leave_date`, `paid`) VALUES (5, '20.09.2019', '30.09.2019', True);


INSERT INTO `mydb`.`billings` (`billing_d`, `date_payed`, `buyers_id`, `sellers_id`, `price`) VALUES (1, '2019-09-20', 1, 4, 1000);
INSERT INTO `mydb`.`billings` (`billing_d`, `date_payed`, `buyers_id`, `sellers_id`, `price`) VALUES (2, '2019-09-20', 2, 6, 4558);
INSERT INTO `mydb`.`billings` (`billing_d`, `date_payed`, `buyers_id`, `sellers_id`, `price`) VALUES (3, '2019-09-20', 3, 1, 456);
INSERT INTO `mydb`.`billings` (`billing_d`, `date_payed`, `buyers_id`, `sellers_id`, `price`) VALUES (4, '2019-09-20', 4, 3, 200);
INSERT INTO `mydb`.`billings` (`billing_d`, `date_payed`, `buyers_id`, `sellers_id`, `price`) VALUES (5, '2019-09-20', 5, 2, 8743);


INSERT INTO `mydb`.`airbnb_wallet` (`wallet_id`, `money`) VALUES (1, 123);
INSERT INTO `mydb`.`airbnb_wallet` (`wallet_id`, `money`) VALUES (2, 23);
INSERT INTO `mydb`.`airbnb_wallet` (`wallet_id`, `money`) VALUES (3, 456);


INSERT INTO appartments_has_appartments_reservations (appartments_id, appartments_reservations_id, buyers_id, billings_id, airbnb_wallet_id) VALUES (1, 1, 1, 1, 1);