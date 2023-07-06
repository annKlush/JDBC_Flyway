# JDBC_Flyway
Task #1 - Utilize Migrations:

Integrate the Flyway framework into the project.
Create two migrations: V1__init_db.sql and V2__populate_db.sql.
Remove the old code responsible for loading and executing SQL files.
Verify that Flyway is correctly configured and executes both migrations successfully.
Task #2 - Implement ClientService for CRUD Operations:

Create a class named ClientService.
Implement the following methods for CRUD operations on clients:
long create(String name) - Adds a new client with the given name and returns the ID of the newly created client.
String getById(long id) - Retrieves the name of the client with the specified ID.
void setName(long id, String name) - Updates the name of the client with the specified ID.
void deleteById(long id) - Deletes the client with the specified ID.
List<Client> listAll() - Retrieves all clients from the database and returns them as a collection of Client objects.
Implement input data validation in the methods of the ClientService class. If any input data is invalid
(e.g., attempting to create a client with a too short or too long name), the corresponding method should throw an exception.
