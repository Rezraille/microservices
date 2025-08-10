FROM eclipse-temurin:24-jre-ubi9-minimal
ARG JAR_FILE=target/gateway-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
EXPOSE 8383
HEALTHCHECK --interval=10s --timeout=3s \
  CMD curl -f http://localhost:8383/actuator/health || exit 1