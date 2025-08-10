FROM eclipse-temurin:24-jre-ubi9-minimal
ARG JAR_FILE=target/eureka-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
EXPOSE 8761
HEALTHCHECK --interval=10s --timeout=3s \
  CMD curl -f http://localhost:8761/actuator/health || exit 1