# VanHack Challenge

Hello Guys this api have the following features:
 - Allow Authentication;
 - Query Products;
 - Receive Orders;
 - Cancel an Order;
 - Get Order Status;
 - Store data in a database of his/her choice;
 
All this features were achieved.

As a plus I used Swagger-UI, streams from Java 8, Lombok to wipe out getters, setters, equalshashcodes and builders.
As you can see I've built it on Spring boot, using Spring Data and Spring security w/ JWT authentication.

Sadly I didn't have time code authentication through swagger, so you'll need to use the good ol' postman to test my api.

login endpoint : http://localhost:8081/vh-api/login
json example: {
    "userName": "test",
    "passWord": "1234"
}

Others endpoints are listed here : http://localhost:8081/swagger-ui.html


