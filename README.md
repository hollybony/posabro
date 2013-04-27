## Posabro Control Operation System
It is the application prototype for the company Posabro
The application is built with:
- Spring Framework 3.1.0
- Service layer with implementation with JPA (Hibernate with Spring Data JPA)
- Spring AOP
- Spring MVC, JSP, jQuery, jQueryUI
## In order to run it on tomcat you must add spring-instrument-3.1.0.RELEASE.jar file to Catalina lib folder

<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-instrument-tomcat</artifactId>
	<version>3.1.0.RELEASE</version>
</dependency>


Security module

