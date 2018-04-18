FROM java:8-jdk-alpine
MAINTAINER Dan <i@shanhh.com>

ENV SERVER_PORT 8080
ENV DAOCLOUD_TOKEN token
ENV CHERRYNIC_DATABASE_URL jdbc:mysql://localhost:3306/cherrynic?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&verifyServerCertificate=false
ENV CHERRYNIC_DATABASE_USERNAME username
ENV CHERRYNIC_DATABASE_PASSWORD password

ENV DIST_DIR /var/lib/app
RUN mkdir -p ${DIST_DIR}

ADD application.jar ${DIST_DIR}/application.jar

EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "java -Dserver.port=${SERVER_PORT} -Dcn.daocloud.token=${DAOCLOUD_TOKEN} -Dspring.datasource.url=${CHERRYNIC_DATABASE_URL} -Dspring.datasource.username=${CHERRYNIC_DATABASE_USERNAME} -Dspring.datasource.password=${CHERRYNIC_DATABASE_PASSWORD} -Djava.security.egd=file:/dev/./urandom -jar ${DIST_DIR}/application.jar"]
