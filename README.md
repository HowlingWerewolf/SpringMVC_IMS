# SpringMVC_IMS

This application based on the Spring tutorial: https://docs.spring.io/docs/Spring-MVC-step-by-step/part3.html

Please note that I not tightly followed the instructions:
  - new Spring versions does not support SimpleFormController and SimpleJdbcDaoSupport
  - instead of Ant, I use Apache Maven 3.3.9
  - instead of Hsql, I use PostgreSQL
  
In addition to the project, other features added:
  - add product
  - delete product

Thanks to flyway, the basic tables are set up by default.
  - product table, filled up with data
  - user springims that can access this table

2026 March:
  - migrated jsp to angular 21

Start:
    - frontend by
        cd web
        npm ci
        npm b
        ng serve
    - backend by
        start.bat
  http://localhost:4200