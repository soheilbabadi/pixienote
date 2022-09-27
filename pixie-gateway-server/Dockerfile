FROM eclipse-temurin:17-jdk-jammy as builder
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
RUN sh -c 'touch /pixienote-gateway-service.jar'


FROM eclipse-temurin:17-jdk-jammy
COPY --from=builder target/*.jar pixienote-gateway-service.jar
ENTRYPOINT ["java", "-jar", "pixienote-gateway-service.jar" ]