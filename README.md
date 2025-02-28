# OrderMs
 OrderMs Async
 
# software versions

Java - 17

MySQL 8.0+
 
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

url : http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/

# Postman collection

Available in Repository as OrderMs.postman_collection.json

	{
		"info": {
			"_postman_id": "3bafa89d-0476-4253-ad50-b2468a5c96c8",
			"name": "OrderMs",
			"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
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
					"url": {
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/user/create",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"user",
							"create"
						]
					}
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
					"url": {
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/user/getAll",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"user",
							"getAll"
						]
					}
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
					"url": {
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/item/create",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"item",
							"create"
						]
					}
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
					"url": {
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/item/getAll",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"item",
							"getAll"
						]
					}
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
					"url": {
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/order",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"order"
						]
					}
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
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/order/status?orderId=791c85a3-7dfa-4524-ba25-1b101ca234a7",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
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
								"value": "791c85a3-7dfa-4524-ba25-1b101ca234a7"
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
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/order/getDetails?orderId=791c85a3-7dfa-4524-ba25-1b101ca234a7",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
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
								"value": "791c85a3-7dfa-4524-ba25-1b101ca234a7"
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
					"url": {
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/orderMetrics",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"orderMetrics"
						]
					}
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
					"url": {
						"raw": "http://orderms-env.eba-wxgwg2hv.ap-south-1.elasticbeanstalk.com/loadTest",
						"protocol": "http",
						"host": [
							"orderms-env",
							"eba-wxgwg2hv",
							"ap-south-1",
							"elasticbeanstalk",
							"com"
						],
						"path": [
							"loadTest"
						]
					}
				},
				"response": []
			}
		]
	}
