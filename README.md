# OrderMs
 OrderMs Async
 
# software versions

Java - 17

MySQL 8.0+

# SQL DDL Queries

	CREATE DATABASE `ecomdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
	USE DATABASE `ecomdb`;
	CREATE TABLE `item_mstr` (
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
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
	
	CREATE TABLE `order_tracker` (
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
	
	CREATE TABLE `orders` (
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
	
	CREATE TABLE `user_mstr` (
	  `userId` varchar(45) NOT NULL,
	  `first_name` varchar(100) NOT NULL,
	  `last_name` varchar(100) DEFAULT NULL,
	  `mobile` varchar(50) NOT NULL,
	  `address` varchar(1000) NOT NULL,
	  PRIMARY KEY (`userId`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


# Assumptions

a. for load testing i.e. "/loadTest" data is hard-coded

	[
	    {
	        "itemId": "1",
	        "itemName": "Pen",
	        "itemRate": 10.0,
	        "itemQty": 10
	    },
	    {
	        "itemId": "2",
	        "itemName": "Book",
	        "itemRate": 34.0,
	        "itemQty": 5
	    },
	    {
	        "itemId": "3",
	        "itemName": "Box",
	        "itemRate": 75.0,
	        "itemQty": 1
	    }
	]

b. You can create user and item data using the create end-points for each of these

# Design

a. ACtiveMQ in memory queue 

b. Asynchronous multi-threaded queue consumer : to pick multiple requests based on the thread parallelism

c. indexing on status of order in OrderTracker table : for efficient reads

d. DTO : Data Transfer Object to separate entities and response objects.

e. All responses are of status 200 , success with status 1 and failure with status 0.

f. maintained ER status for orders , for any business validation error.

# Hosted on AWS Elastic Beanstalk

url : http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/

Public url is subject to change is restart of env is done

# Postman collection

Available in Repository as OrderMs.postman_collection.json

	{
		"info": {
			"_postman_id": "3bafa89d-0476-4253-ad50-b2468a5c96c8",
			"name": "OrderMs",
			"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
			"_exporter_id": "42720500"
		},
		"item": [
			{
				"name": "CreateUser",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "POST",
					"header": [],
					"body": {
						"mode": "raw",
						"raw": "{\r\n    \"userId\": \"rohit\",\r\n    \"firstName\": \"Rohit\",\r\n    \"lastName\": \"Kumar\",\r\n    \"mobile\": \"1234567890\",\r\n    \"address\": \"Pune, India\"\r\n}",
						"options": {
							"raw": {
								"language": "json"
							}
						}
					},
					"url": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/user/create"
				},
				"response": []
			},
			{
				"name": "get All users",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "GET",
					"header": [],
					"url": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/user/getAll"
				},
				"response": []
			},
			{
				"name": "Create item",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "POST",
					"header": [],
					"body": {
						"mode": "raw",
						"raw": "{\r\n    \"itemName\": \"Box\",\r\n    \"itemRate\": 75.0,\r\n    \"itemQty\": 10000000\r\n}",
						"options": {
							"raw": {
								"language": "json"
							}
						}
					},
					"url": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/item/create"
				},
				"response": []
			},
			{
				"name": "get All items",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "GET",
					"header": [],
					"url": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/item/getAll"
				},
				"response": []
			},
			{
				"name": "create order",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "POST",
					"header": [],
					"body": {
						"mode": "raw",
						"raw": "{\r\n    \"userId\":\"rohit\",\r\n    \"items\":\r\n[\r\n    {\r\n        \"itemId\": \"1\",\r\n        \"itemName\": \"Pen\",\r\n        \"itemRate\": 10.0,\r\n        \"itemQty\": 10\r\n    },\r\n    {\r\n        \"itemId\": \"2\",\r\n        \"itemName\": \"Book\",\r\n        \"itemRate\": 34.0,\r\n        \"itemQty\": 5\r\n    },\r\n    {\r\n        \"itemId\": \"3\",\r\n        \"itemName\": \"Box\",\r\n        \"itemRate\": 75.0,\r\n        \"itemQty\": 1\r\n    }\r\n],\r\n\"totalAmount\":345\r\n}",
						"options": {
							"raw": {
								"language": "json"
							}
						}
					},
					"url": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/order"
				},
				"response": []
			},
			{
				"name": "get order status",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "GET",
					"header": [],
					"url": {
						"raw": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/order/status?orderId=5a71dbe6-badc-4f6a-93ee-1c01c9031fde",
						"protocol": "http",
						"host": [
							"orderms-env-new",
							"eba-s26jfzsn",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"order",
							"status"
						],
						"query": [
							{
								"key": "orderId",
								"value": "5a71dbe6-badc-4f6a-93ee-1c01c9031fde"
							}
						]
					}
				},
				"response": []
			},
			{
				"name": "get order Details",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "GET",
					"header": [],
					"url": {
						"raw": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/order/getDetails?orderId=5a71dbe6-badc-4f6a-93ee-1c01c9031fde",
						"protocol": "http",
						"host": [
							"orderms-env-new",
							"eba-s26jfzsn",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"order",
							"getDetails"
						],
						"query": [
							{
								"key": "orderId",
								"value": "5a71dbe6-badc-4f6a-93ee-1c01c9031fde"
							}
						]
					}
				},
				"response": []
			},
			{
				"name": "get order Metrics",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "GET",
					"header": [],
					"url": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/orderMetrics"
				},
				"response": []
			},
			{
				"name": "Load Test",
				"request": {
					"auth": {
						"type": "noauth"
					},
					"method": "POST",
					"header": [],
					"url": "http://orderms-env-new.eba-s26jfzsn.ap-south-1.elasticbeanstalk.com/loadTest"
				},
				"response": []
			}
		]
	}
