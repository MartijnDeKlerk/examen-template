FROM tomcat:latest

MAINTAINER "Bram Ceulemans <bramceulemans@me.com>"

CMD ["rm", "-rf", "/usr/local/tomcat/webapps/ROOT*"]

COPY target/examen-template.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]