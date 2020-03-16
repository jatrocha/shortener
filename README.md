# URL Shortening project

## Prerequisites:
For test and run this project you just need to have `mvn` command in your path.

## Run all tests:
There are several integration and unit tests for project, you can run them by this command:

    mvn clean test
    
## Run project:
To run this project you need to run this command:

    mvn spring-boot:run    
    
## How to shorten a URL:
To create a shortened url you should use this endpoint:

url: `http://localhost:8080/api/v1/shorten`

http method: `POST`

request body:

    {
        "value": "https://cygnus-x1.visualstudio.com/Neueda/_sprints/taskboard/Neueda%20Team/Neueda/Iteration%201?workitem=76"
    }

success response body (http status `200`):  

    {
         "value": "http://localhost:8080/b9eNGS"
    }

## How to access the shortened URL:
To access the original url you should use this endpoint:

    url: `http://localhost:8080/b9eNGS`

    
## How to get telemetry information for API usage
To access usage statistic data you should you the following endpoints:

* list all shortened urls:

    url: `http://localhost:8080/api/v1/telemetry` 
 
* get data for a specific shortened url:

    url: `http://localhost:8080/api/v1/telemetry/b9eNGS`
    
## Docker

* to build a docker image

    `docker build -t cygnus/shortener .`

* to run the application

    `docker run -p 8080:8080 -t  cygnus/shortener`
        