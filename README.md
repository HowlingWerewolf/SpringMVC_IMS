# SpringMVC_IMS

This application based on the Spring tutorial: https://docs.spring.io/docs/Spring-MVC-step-by-step/part3.html

Please note that I not tightly followed the instructions:
  - new Spring versions does not support SimpleFormController and SimpleJdbcDaoSupport
  - instead of Ant, I use Apache Maven 3.3.9
  - instead of Hsql, I use PostgreSQL
  
In addition to the project, other features added:
  - add product
  - delete product

(TODO: add flyway so we don't have to run sql files manually)
Run the createpg_db.sql
  - create the springims user
  - creates the db and the necessary tables
  - fills up the tables with data

In order to use selenium, install it to Firefox, then create 'c:/selenium' folder.

 Start:
  http://localhost:8080/SpringMVC_IMS/