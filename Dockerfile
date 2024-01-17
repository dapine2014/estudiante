FROM amazoncorretto:21.0.0


ENV TZ=America/Bogota
VOLUME /tmp
COPY /target/profession-0.0.1-SNAPSHOT.jar .
#EXPOSE 31001

#CMD ["java","-jar","locationsApi.jar"]
CMD ["java","-jar","profession-0.0.1-SNAPSHOT.jar","--spring.config.name=application-prod"]