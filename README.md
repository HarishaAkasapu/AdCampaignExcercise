# AdCampaignExcercise
AdCampaignExcercise for Comcast

This exercise meets below mentioned functional requirements of Ad Server:
- REST Service to create Ad Campaign via POST using URI http://localhost:8080/ad
- Request and Response are in JSON data formats.
- Validation with an error response to have one active campaign for a Partner.
- Campaign data is non-persistent and for the application to be fault tolerant it should further rely on persistent storage.
- REST service to fetch Ad Campaign via GET using URI http://localhost:8080/ad/{partnerId}
- Validation with an error response to check active campaign using campign created timestamp and duration for a given Partner.
- REST service to fetch all Campaigns via GET using URI http://localhost:8080/ad/all

Steps to Run:
- This application can be run using Maven run goal through this command "mvn spring-boot:run"
