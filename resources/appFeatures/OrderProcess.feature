@orderProcess
Feature: Tests to validate Tokopedia order process


@validatePayload
Scenario Outline: User is able to validate the response payload
Given User sets the API request "<URI>"
And User authenticates the API request with OAuth "<token>"
When User sends the valid API request with "<order_id>", "<order_desc>", "<order_status>", "<timestamp>", "<special_order>"
Then User gets the response with correct "<contentType>", "<serverType>" and status "<code>"

Examples: 
|URI|token|code|order_id|order_desc|order_status|timestamp|special_order|contentType|serverType|
|https://tokopedia.com|test123|200|ord_123|sample description_1|New|1642321210439|false|application/json; charset=utf-8|nginx/1.17.10 (Ubuntu)|
|https://tokopedia.com|test123|200|ord_456|sample description_2|New|1642321210450|false|application/json; charset=utf-8|nginx/1.17.10 (Ubuntu)|



@validateOrderData
Scenario Outline: User is able to validate the order status and timestamp in the processOrder API response

Given User sets the API request "<URI>"
And User authenticates the API request with OAuth "<token>"
When User sends the valid API request with "<order_id>", "<order_desc>", "<order_status>", "<timestamp>", "<special_order>"
Then User gets the response with updated "<order_status>" and "<timestamp>" with success "<code>"

Examples: 
|URI|token|code|order_id|order_desc|order_status|timestamp|special_order|
|https://tokopedia.com|test123|200|ord_123|sample description_1|New|1642321210439|false|
|https://tokopedia.com|test123|200|ord_456|sample description_2|New|1642321210450|true|




@validateDataValidation
Scenario Outline: User is able to validate the error code for invalid order status
Given User sets the API request "<URI>"
And User authenticates the API request with OAuth "<token>"
When User sends the API request with invalid "<order_status>" and valid "<order_id>", "<order_desc>", "<timestamp>", "<special_order>"
Then User gets the response with proper error "<code>"

Examples: 
|URI|token|code|order_id|order_desc|order_status|timestamp|special_order|
|https://tokopedia.com|test123|400|ord_123|sample description_1|Test|1642321210439|false|
|https://tokopedia.com|test123|400|ord_456|sample description_2|Wrong|1642321210450|false|




@validateInvalidResource
Scenario Outline: User is able to validate the error code for invalid order ID
Given User sets the API request "<URI>"
And User authenticates the API request with OAuth "<token>"
When User sends the API request with non-existing "<order_id>" and valid "<order_status>", "<order_desc>", "<timestamp>", "<special_order>"
Then User gets the response with proper error "<code>"

Examples: 
|URI|token|code|order_id|order_desc|order_status|timestamp|special_order|
|https://tokopedia.com|test123|404|123|sample description_1|New|1642321210439|false|
|https://tokopedia.com|test123|404|456|sample description_2|New|1642321210450|false|
