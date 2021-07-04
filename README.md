# flat-organizer
Website used for coordination with roommates in larger flats.

Feel free to take a look at the following dummy project.
* Live Server (Dummy): http://195.201.129.117:8890/flat-organizer/
    * user: *max*
    * password: *mustermann0101*

In our flat every roommate has a specific set of chores he / she has to do. This webapp should help organize those chores. In the current version it's mainly used to implement a grocery list to which each roommate can add products. The roommate who does the grocery shopping for the flat can see to which storage room all the products should be delivered via the destination column in the tables shown below. This destination will be generated automatically by the system depending on the room location of the user putting the item in the list. Each item has an assigned category and storage place depending on the level of the bedroom of the flatmate that has put the item on the list. If a new input item is already present on the list, the system will update the item count of the already present item.

On the admin page it is possible to remove an user from the app and modify the item defaults (category, etc.).

## Screenshot
![screenshot](images/screenshot_03.png)
![screenshot](images/screenshot_02.png)

## Technologies
* Docker (docker-compose)
* Spring Boot
* Hibernate
* Bootstrap 4
* Thymeleaf
* git secret

## Database Schema
![schema](database_schema/db_schema.png)

### ⚠️ Incomplete configuration
* For privacy reasons the [data set](./secure-webapp/src/main/resources/data.sql) for the database may be incomplete in this public repository.
* The [credentials](./secure-webapp/src/main/resources/application.properties) for the database must also be filled out before the application can start. 

