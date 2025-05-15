This program, developed within the back end, fosters and designs the logic and components necessary for a mobile application responsible for creating agendas for a cooperative assembly, where they can, in addition to creating agendas, vote with "YES" or "NO" for their decisions and have the response of the total count for each agenda.

To run and compile this program, you will need the following items: 
1-Java SpringBoot IDE - LTS version
2-MongoDB - MongoCompass for local repository and MongoAtlas for remote repository.
3- Copy this repository to your computer and connect to your IDE.
4- Add to the pom.xml file in the <dependencies> reference <dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
</dependencies
5 - in the application.properties file in src/main/sources and add the path to your database.
6 - in the application src/main/java file - right click - Run Ans - Spring boot App.
7-Download Postman if you want to test REST requests/controllers via http.

