FROM maven:3.3.9-jdk-8
MAINTAINER Dan <i@shanhh.com>

ENV PROJ_DIR /tmp/project
ENV JAR_PATH ${PROJ_DIR}/cherrynic-web/target/application.jar
ENV DIST_DIR /var/lib/app
RUN mkdir -p ${DIST_DIR}

ADD . ${PROJ_DIR}
WORKDIR ${PROJ_DIR}
RUN mvn package
RUN cp ${JAR_PATH} ${DIST_DIR}/application.jar

EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "java -jar ${DIST_DIR}/application.jar"]