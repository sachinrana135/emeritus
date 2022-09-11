# Emeritus

Simple Course Manangement Application using Java Microsevices with Spring projects. Service API endpoints are accessible only with valid token 

* Type of users
  * System Admin
  * Instructor
  * Student

* Role based access control
  * System Admin 
    * Create/Update/Delete/List Users (Instructor, Student)
    * List all courses
    * List all Students 
    * List students enrolled in each course

  * Instructor 
    * Create Courses
    * Create Assignment for each course
    * List all courses
    * Access/Modify courses created by themselves
    * List the students enrolled in each course
    * Must not be able to access courses created by other instuctors
  * Student
    * Can see all the courses 
    * Can enroll in more than one course 
    * Can access only enrolled courses and assignments
    * Can submit/update assignments 

* ##### Note - Project is built with H2 Memory database with empty database.


## Table of contents
* [Technologies](#technologies)
* [Setup](#setup)
* [APIs](#apis)


## Technologies

Project is created with:
* Java: 17
* Springboot: 2.7.3
* Hibernate 
* H2 Database
* JUnit5

## Setup
* Import all the services as gradle project
* Go to eurekaserver root folder and use **./gradlew bootRun** to run the eureka server .
* Go to gateway root folder and use **./gradlew bootRun** to run the gateway service. Gateway service will run on port 9000.
* Go to config root folder and use **./gradlew bootRun** to run the config service.
* Go to user and course root folder and run the **./gradlew bootRun** command.
* All the api can be accessed on gateway port 9000.
* H2 console user database link - http://localhost:9050/user/h2-console
* H2 console course database link - http://localhost:9051/course/h2-console

url: jdbc:h2:mem:emeritus
username: sa
password: password

## APIs

* [API Documentation](https://documenter.getpostman.com/view/2141799/2s7YSQYsRN)

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/d39c67e3b4bc2dfbb007?action=collection%2Fimport)
