FROM tomcat:9-jdk8-corretto
COPY core/target/core.war /usr/local/tomcat/webapps/

# for tryout reasons, you can log in from another container
# RUN apt update && apt -y install postgresql && apt clean