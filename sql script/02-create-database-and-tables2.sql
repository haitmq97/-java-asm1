drop database if exists `asm1_donation_project`;
CREATE DATABASE  IF NOT EXISTS `asm1_donation_project`;
USE `asm1_donation_project`;

-- SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;


CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` int default 1,
  `created_date` varchar(100) DEFAULT null,
  `showing` boolean DEFAULT true,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `EMAIL_UNIQUE` (`email`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`user_name`),
  KEY `FK_ROLE_idx` (`role_id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;

CREATE TABLE `donation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) default NULL,
  `organization` varchar(255) DEFAULT NULL,
  `created_date` varchar(100) DEFAULT null,
  `money` int DEFAULT 0,
  `description` varchar(255) DEFAULT NULL,
  `status` int DEFAULT 0,
  `start_date` varchar(100) DEFAULT NULL,
  `end_date` varchar(100) DEFAULT NULL,
  `donation_number` int DEFAULT 0,
  `showing` boolean DEFAULT true,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;

CREATE TABLE `user_donation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` varchar(100) DEFAULT null,
  `money` int DEFAULT 0,
  `name` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `showing` boolean DEFAULT true,
  `user_id` int DEFAULT NULL,
  `donation_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_idx` (`user_id`),
  KEY `FK_DONATION_idx` (`donation_id`),
  CONSTRAINT `FK_USER` 
  FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`), 
  CONSTRAINT `FK_DONATION` 
  FOREIGN KEY (`donation_id`) 
  REFERENCES `donation` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 collate=utf8mb4_unicode_ci;



SET FOREIGN_KEY_CHECKS = 1;
