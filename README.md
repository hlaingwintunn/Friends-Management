# Friends-Management
API server that does simple "Friend Management"

#### Please see [User Stories](https://github.com/hlaingwintunn/Friends-Management/blob/master/User_Stories.md)


#### Prerequisite for this project:
1. Java JDK 8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Maven  (https://maven.apache.org/install.html)
3. Heroku Account for Deployment. (https://www.heroku.com/)


#### REST API Documentation, Please go to below link;
[demo](https://friend-management-restapi.herokuapp.com/swagger-ui.html#/)



#### How to run project locally
```sh
$ git clone https://github.com/hlaingwintunn/Friends-Management.git
$ cd Friends-Management/
$ mvn clean package
$ java -jar target/*.jar

- After app is started, you can access the swagger Rest Api documentation with 
   http://localhost:8080/swagger-ui.html
- *Application run on port 8080.Make sure its not in use!*
```


#### Technologies Used
```sh
Java 8
Springboot
Hibernate
H2 inmemory Database
Junit (Testcase)
Heroku (Deployment)
```


#### User EndPoints
```sh
http://localhost:8080/api/friends
http://localhost:8080/api/list
http://localhost:8080/api/commonfriend
http://localhost:8080/api/subscribe
http://localhost:8080/api/block
http://localhost:8080/api/receiveUpdates
```


#### Database Web Condole
The application's H2 database web console.
- `http://localhost:8080/console/`
```
 JDBC URL: `jdbc:h2:mem:fmdb`
 User Name: `sa`
 Password:
 ```
