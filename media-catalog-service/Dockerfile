FROM amazoncorretto:17-alpine3.18-jdk

VOLUME /tmp
COPY build/libs/*.war app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.war"]
