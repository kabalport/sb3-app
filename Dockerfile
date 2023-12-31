#FROM amazoncorretto:17
#RUN mkdir /work
#WORKDIR /work
##ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]
FROM amazoncorretto:17
RUN mkdir /work
WORKDIR /work

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/work/app.jar"]