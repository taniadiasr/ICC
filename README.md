#ICC STOCK CRUD 

Postman collection for testing:

https://www.getpostman.com/collections/ab92c4f397f9465e325d

 # CRUD FEATURE STATUS
 
 Add: Working (POST)
 
 GetAll: Working (GET) 
  - http://localhost:8080/demo/stock
  - http://localhost:8080/demo/stock/4

 Update: 
    /* PATCH : using CrudRepository , from Spring Data, found a drawback, 
    as it performs full update - aka all columns. */
  )
 
 Delete: 


# HOW TO 

1. Git clone this repo;
  
2. Import the project using the build.gradle as config file;

3. Open Postman > import > Link > https://www.postman.com/collections/ab92c4f397f9465e325d

4. Trigger the requests that might interest you. Remember: you need to create the DB and populate! 

* For that, I made available a DB dump for you with data. Its under Dump folder. 

* There is also docker deploy option on stack.yml.


# WALK THROUGH (Theory and project decision-making) 

====> WAS ?! * What is SpringBoot? !

Spring Boot is a project that is built on the top of the Spring Framework. It provides an easier and faster way to set up, configure, and run both simple and web-based applications.

It is a Spring module that provides the RAD (Rapid Application Development) feature to the Spring Framework. It is used to create a stand-alone Spring-based application that you can just run because it needs minimal Spring configuration.

Spring Boot is the combination of Spring Framework and Embedded Servers. <! 

We can use Spring STS IDE or Spring Initializr to develop Spring Boot Java applications. <!

====> WIE ?! * Setting Up GENERAL WEB APP STUFF WITH SPRING INITIALZR

1. Go to https://start.spring.io/,
2. choose language and spring version
3. generate file
4. extract the zip and open build.gradle with intelliJ 

5. go to github and create a new repo
6. add the repo as remote using cli in intelliJ
7. git add . git commit -m git push --set-upstream master master
8. Build project to test. If successful, is ready to code.
project to test. If successful, is ready to code.

====> SPRING SISTERS I might need

- Spring WEB is enough but not for connection to  MySQL!
- Spring Data: It simplifies data access from the relational and NoSQL databases.
- Lombok: boilerplate friend
- https://www.baeldung.com/spring-data-partial-update << ! 

====> Coding steps to getUp And Running! (No DB Attached)

- Create a Resource Representation Class

- Create a Resource Controller for the previous

- Build an executable JAR 

	> gradlew bootRun

- Run DemoApplication.java (for now, makes sense to use a Service here !)

====> Coding steps to getUp And Running! (DB Attached)

- Entity for Stocks 

- Repository

-> Controller

-> Don't forget to set the Mapping accordingly.

    > gradlew bootRun
    > mvnw spring-boot:run 
   
====> SPICING UP: Adding MySQL // With Docker Server
	/* https://spring.io/guides/gs/accessing-data-mysql/ */
	/* https://hub.docker.com/_/mysql/ */


    > docker pull mysql
    > docker run --name mysql_springboot1 -e MYSQL_ROOT_PASSWORD=muitoseguro -d mysql:5.6
	> container: mysql_springboot1
    > docker ps 
	the container must be listed

	
-> edit the build.gradle now to add mysql in the dependencies:
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	runtimeOnly 'mysql:mysql-connector-java'
-> rebuild the project

-> run a mysql client in another container or within the previous
-> create a DB instance
-> create a user associated to a pass;
-> grant privileges to this user for the DB you created;

> docker exec -it mysql_springboot1 mysql -uroot -p

	mysql> create database db_example;
	mysql> create user 'springuser'@'%' identified by 'ThePassword';
	mysql> grant all on db_example.* to 'springuser'@'%';
	
-> Create the application.properties File to specify db is not H2 (default)

	might already exists under: src/main/resources/application.properties

-> paste the following content
	
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example?useTimezone=true&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=muitoseguro
	

====> SPECS DA DB NO CODIGO

-> Create the @Entity Model under com.stockrestservice.demo

-> Create the Repository

-> Create a Controller or use the other one you set up before. 

-> Update GRADLE THROUGH gradle tool;

-> Build must be successful :D!


After all that, you might have issues to recognize the new javax dependencies.

I updated gradle through the Gradle tool that is shown on the IDE and the errors of module recon 

disappeared. perhaps i could have run gradlew bootSpring again and would be fixed.

> Shift Alt F10, to run
> then , as expected, I got a visibility issue:

Caused by: com.mysql.cj.exceptions.CJCommunicationsException: Communications link failure

-> This is likely due to my containers not being visible to the external network. They are not on localhost as previous specified in the applications.properties!

/* DEPRECATED -> DOCKER db server & client deploy (stack.yml)

	# Use root/example as user/password credentials
	version: '3.1'

	services:

	  db:
		image: mysql
		command: --default-authentication-plugin=mysql_native_password
		restart: always
		environment:
		  MYSQL_ROOT_PASSWORD: example

	  adminer:
		image: adminer
		restart: always
		ports:
		  - 8080:8080
		  
    > docker-compose -f stack.yml up

OUTPUT

 | 2020-11-07T03:12:00.829852Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.22'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MyS
QL Community Server - GPL.

-> Open docker panel and you will see now another stuff running :D 

-> Browser + http://localhost:8080 -> You shall see the mysql db set there !

-> drop reason: inner docker network configuration was too time expensive for this precise moment,
however, the deploy using the stack.yml file configured above works great;

*/
