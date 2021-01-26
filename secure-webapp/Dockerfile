FROM tomcat:8.0.51-jre8-alpine
ADD target/workshop-organizer.war /usr/local/tomcat/webapps/
EXPOSE 8888
CMD ["catalina.sh", "run"]
