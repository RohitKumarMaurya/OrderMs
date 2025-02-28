CREATE DATABASE IF NOT EXISTS `ecomdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE DATABASE `ecomdb`;
CREATE TABLE IF NOT EXISTS`item_mstr` (
  `itemId` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `item_rate` double unsigned zerofill NOT NULL,
  `item_qty` int NOT NULL,
  `insertTmstmp` datetime NOT NULL,
  `insertDt` int GENERATED ALWAYS AS (date_format(`insertTmstmp`,_utf8mb4'%y%m%d')) VIRTUAL,
  `insertDtHH` int GENERATED ALWAYS AS (date_format(`insertTmstmp`,_utf8mb4'%y%m%d%H')) VIRTUAL,
  `updateTmstmp` datetime NOT NULL,
  `updateDt` int GENERATED ALWAYS AS (date_format(`updateTmstmp`,_utf8mb4'%y%m%d')) VIRTUAL,
  `updateDtHH` int GENERATED ALWAYS AS (date_format(`updateTmstmp`,_utf8mb4'%y%m%d%H')) VIRTUAL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `order_tracker` (
  `orderId` varchar(36) NOT NULL,
  `userId` varchar(45) NOT NULL,
  `orderStatus` varchar(3) NOT NULL,
  `errCd` varchar(10) DEFAULT NULL,
  `insertTmstmp` datetime NOT NULL,
  `insertDt` int GENERATED ALWAYS AS (date_format(`insertTmstmp`,_utf8mb4'%y%m%d')) VIRTUAL,
  `insertDtHH` int GENERATED ALWAYS AS (date_format(`insertTmstmp`,_utf8mb4'%y%m%d%H')) VIRTUAL,
  `updateTmstmp` datetime NOT NULL,
  `updateDt` int GENERATED ALWAYS AS (date_format(`updateTmstmp`,_utf8mb4'%y%m%d')) VIRTUAL,
  `updateDtHH` int GENERATED ALWAYS AS (date_format(`updateTmstmp`,_utf8mb4'%y%m%d%H')) VIRTUAL,
  `ptime` int GENERATED ALWAYS AS (floor(time_to_sec(timediff(`updateTmstmp`,`insertTmstmp`)))) VIRTUAL,
  PRIMARY KEY (`orderId`),
  KEY `idx_order_tracker_orderStatus` (`orderStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `orders` (
  `orderId` varchar(36) NOT NULL,
  `items` json NOT NULL,
  `userId` varchar(45) NOT NULL,
  `totalAmt` double unsigned zerofill NOT NULL,
  `insertTmstmp` datetime NOT NULL,
  `insertDt` int GENERATED ALWAYS AS (date_format(`insertTmstmp`,_utf8mb4'%y%m%d')) VIRTUAL,
  `insertDtHH` int GENERATED ALWAYS AS (date_format(`insertTmstmp`,_utf8mb4'%y%m%d%H')) VIRTUAL,
  `updateTmstmp` datetime NOT NULL,
  `updateDt` int GENERATED ALWAYS AS (date_format(`updateTmstmp`,_utf8mb4'%y%m%d')) VIRTUAL,
  `updateDtHH` int GENERATED ALWAYS AS (date_format(`updateTmstmp`,_utf8mb4'%y%m%d%H')) VIRTUAL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `user_mstr` (
  `userId` varchar(45) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `mobile` varchar(50) NOT NULL,
  `address` varchar(1000) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
