FROM eclipse-temurin:17-jdk-jammy as builder
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
RUN sh -c 'touch /pet-abuse-service.jar'


FROM eclipse-temurin:17-jdk-jammy
COPY --from=builder target/*.jar pet-abuse-service.jar
ENTRYPOINT ["java", "-jar", "pet-abuse-service.jar" ]