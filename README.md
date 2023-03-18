# blog-app-apis
### Live Demo : http://blogapp-env.eba-zrdbbwh6.eu-north-1.elasticbeanstalk.com/blog-apis/v1/swagger-ui/index.html
### Without Registering The User, You can Test API i.e All The Get Method except GetAllUsers, GetAllPosts, GetAllComments APIs. 
## Overview:

    1. Create a Blog Application using Spring Boot as the backend framework, MySQL as the database, Hibernate as the 
    ORM, AWS as the cloud platform, JWT for authentication, Swagger for API documentation, and Postman for testing the API endpoints.

    2. We will start by setting up the development environment and configuring the necessary dependencies. 
    Then, we will create a database schema for the blog application and define the entities using Hibernate annotations. 
    Next, we will implement the CRUD operations using Spring Data JPA and test them using Postman.

    3. We will then secure our application by implementing JWT-based authentication and authorization. We will also 
    use Swagger to document the REST APIs and test them using Postman.

    4. Finally, we will deploy our application to AWS Elastic Beanstalk and configure a MySQL database on RDS.

## Outline:

    1. Introduction to Spring Boot and setting up the development environment
    2. Creating a MySQL database schema and defining entities using Hibernate annotations
    3. Implementing CRUD operations using Spring Data JPA and testing with Postman
    4. Implementing JWT-based authentication and authorization
    5. Documenting REST APIs using Swagger
    6. Testing REST APIs using Postman
    7. Deploying the application to AWS Elastic Beanstalk
    8. Configuring a MySQL database on RDS

## Prerequisites:

    1. Knowledge of Java programming language
    2. Familiarity with RESTful web services and API development
    3. Basic understanding of SQL and databases
    4. Experience with Spring Framework is a plus, but not required

## Description:
    Building Real Time REST API`s for Blogging Application built with Core Java(OOp's, package, exception, Lambda, 
    stream api etc), Spring Framework(Spring Core, Spring Security, JWT, Spring Data JPA
    (Hibernate) and MySQL Database, IDE Intellij Idea.

    Backend of a blog application built with Core Java and the Spring Boot framework and deployed on AWS connected 
    to a MySQL database. This app has full CRUD capabilities, allowing users to create blog posts and comment on 
    blog posts created by other users. The app allows users to register with ADMIN/NORMAL role, which will give them 
    different capabilities within the application depending on their role.


## Blog Application Project Covers:
    1. Creating Rest Endpoints
    2. Complex DB Structure(JPA Entities)
    3. Role Based Authentication
    4. Handling Exceptions
    5. Creating DTO for Data Transfer
    6. Swagger UI
    7. How to add profiles for differnet Enviroments.
    8. How To Depoly Spring Boot Project in Productions i.e (AWS).

## Functionalities:
    Step 1: Register User:
    Step 2: Login User: After Login User We will get token.
    Step 3: Authorized Token:
    Step 4: If User is ADMIN: 
    Step 5: If User is Admin User, Then User has full CRUD capabilities Like.
        
        For User:
            1. GetAllUsers
            2. GetUser
            3. DeleteUser
            4. UpdateUser

        For Category:
            1. CreateCategory
            2. UpdateCategory
            3. GetCategory
            4. GetAllCategory
            5. DeleteCategory

        For Post:
            1. CreatePost
            2. UpdatePost
            3. GetPost
            4. GetPostByCategory
            5. GetPostByUser
            6. GetAllPost
            7. DeletePost
            8. SearchPost
            For PostImage:
                9. UploadPostImage
                10. DownloadPostImage
                11. UpdatePostImage
                12. DeletePostImage

        For Comment:
            1. CreateComment
            2. UpdateComment
            3. GetComment
            4. GetAllComment
            5. DeleteComment

    Step 6: If User is Normal User, Then User has CRUD capabilities Like.
        
        For User:
            1. GetAllUsers
            2. GetUser
            3. DeleteUser
            4. UpdateUser

        For Category:
            1. GetCategory
            2. GetAllCategory
            

        For Post:
            1. CreatePost
            2. UpdatePost
            3. GetPost
            4. GetPostByCategory
            5. GetPostByUser
            6. DeletePost
            7. SearchPost
            For PostImage:
                8. UploadPostImage
                9. DownloadPostImage
                10. UpdatePostImage
                11. DeletePostImage

        For Comment:
            1. CreateComment
            2. UpdateComment
            3. GetComment
            4. DeleteComment
            
# How To Run Application Local Machine:
    Step 1: First Clone Github Repository, On Local Machine.
    Step 2: Install Dependencies i.e mvn clean install
    Step 3: Configuration:
        Step 1. Main Configuration Files:
            Folder src/main/java/co/blog/BlogAppApisApplication.java contains config files for BlogAppApisApplication.
        
        Step 2. First Create an Database, put into application-dev.properties and configure.

        Step 3. Properties Configuration Files:
            src/main/resources/application.properties - main configuration file. 
        
        For Dev Environment:
            src/main/resources/application-dev.properties - Here it is possible to change admin username/password, as 
            well as change the port number.

        For Dev Environment:
            src/main/resources/application-prod.properties - Here it is possible to change admin username/password, as 
            well as change the port number.

    Step 4: Simply Run The Application.

#### Here is Collection Documentation of Postman: https://documenter.getpostman.com/view/19629540/2s93JzL13P

### How can I support the developer ?
    Star my Github repo ⭐
    Create pull requests, submit bugs, suggest new features or documentation updates 🛠

### Somethings wrong!!
    If you find that something's wrong with this package, you can let me know by raising an issue on the GitHub issue tracker