# city distance

This is a Spring Boot based service for storing and calculating routes between cities.

Neo4j was chosen as the database. It supports ACID transactions, has an integration with spring data, has an active community and covers all my needs in graphs processing.

However, there are no any obvious ways to guarantee uniqueness of relationships between nodes. So, I had to implement the queue to ensure consistency of the data. The queue is processed by separate SingleThread pool executor. This functionality can be moved to a dedicated server with message queue for scalability.

## how to run

Project requires Java 1.8.
To run the server with default settings, run following command under the project's folder:

`gradlew bootrun`

By default, the application works with an embedded neo4j server. You can specify connection settings in application-dev.yml and run it with:
`gradlew bootrun --args='--profile=dev'`

Also there is swagger API documentation available on:
http://localhost:8080/swagger-ui.html

To run tests use:

`gradlew test`
