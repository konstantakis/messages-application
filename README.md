# Message API example project

## Instructions

To run the application execute the following steps:

1. Open a terminal and navigate the root folder 
2. Run `mvn clean install` to build the application
3. Run `java -jar ./target/messages-0.0.1-SNAPSHOT.jar` to run the application

OR import the project to your favorite IDE and run the deployment.

The application is reachable on the `8080` port, so the url is `http://localhost:8080/`

## Test

POST `http://localhost:8080/messages` to create a new message.<br> 
You can use the following request body: `{ "content": " Hello World" }`

GET `http://localhost:8080/messages` to retrieve all the existing messages.

GET `http://localhost:8080/messages/1` to retrieve an existing message with id 1.

PUT `http://localhost:8080/messages/1` to edit an existing message with id 1.<br>
You can use the following request body: `{ "content": " Hello World", "createdOn": "2022-06-20"}`

DELETE `http://localhost:8080/messages/1` to delete an existing message with id 1.
