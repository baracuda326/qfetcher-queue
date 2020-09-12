FROM openjdk:8-jdk-alpine
ADD target/qfetcher.jar qfetcher.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/qfetcher.jar"]