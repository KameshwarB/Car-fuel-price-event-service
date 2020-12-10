## Technology used

``` r
JAVA
Spring boot
Spring Cloud
Maven
Redis cache implementation for 24 hours
RESTful WebServices
Rabbit mq
scheduled event
```

## Details of the modules

## netflix-eureka-naming-server	
    this will act as service  registry to the four service to identify instances running and configurations
    are mentioned in the applicaiton.properties file

## Event-driven-microservice-Producer 
     this will act as the producer for the service consumer which will take rabbitMQ help to publish messages to the queue,
     the method in the service producer is scheduled to run  every 2 mins with delay of 1 sec initially 

## Event-driven-microservice-Consumer
     this will act as consumer service which will call the conversion service for the calculation of price realted to the 
     number of units fuel consumed only if the fuellid is open, this will make a rest call to conversion service for the 
     price to the fuel consumed, this will logg the output to the console and will not produce an output to the consumer. 

## Price-conversion-service			
     this will act as a service to privide the total price to the consumed fuel and in excahnge the fuel it will return 
     an response to the event driven  microservice which will be logged as output this will make a rest call to the exchange
     service to find the changing price for the city, this will return response to event consumer service. 

## Price-exchange-service
     this will act as service to provide the actuall price per liter for fuel the values are cached for 24 hours by using the redis
     cache implmentation , once the cache expires the data is consumed from H2 in-memory database to provide and before that this
     will be cached for 24 hrs.it will return a response to the conversion service 
