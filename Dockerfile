#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER ggarg.bhilai@gmail.com

# Add the application's jar to the container
COPY target/metro-rail-0.0.1-SNAPSHOT.jar metro-rail-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/metro-rail-0.0.1-SNAPSHOT.jar"]