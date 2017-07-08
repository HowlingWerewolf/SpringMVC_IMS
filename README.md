# SpringMVC_IMS

This application based on the Spring tutorial: https://docs.spring.io/docs/Spring-MVC-step-by-step/part3.html

Please note that I not tightly followed the instructions:
  - new Spring versions does not support SimpleFormController and SimpleJdbcDaoSupport
  - instead of Ant, I use Apache Maven 3.3.9
  - instead of Hsql, I use PostgreSQL
  
In addition to the project, other featured added:
  - add product
  - delete product

Prerequirements:
  This application collaborates with PostgreSQL database. 
    Please run the createPG_db.sql file to create to proper user, schema and table.

  Also make sure, that you installed Tomcat application server. I used version 8.5.9.
  
  Logging folder is under 'c:/temp', please create that folder. Logging provided by commons-logging, DEBUG, ERROR levels are separated to the debug.log and error.log.

  In order to use selenium, install it to Firefox, then create 'c:/selenium' folder.

 Start:
  http://localhost:8080/SpringMVC_IMS/