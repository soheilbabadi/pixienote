FROM eclipse-temurin:17-jdk-jammy as builder
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
RUN sh -c 'touch /pixie-document-service.jar'


FROM amd64/eclipse-temurin:17-jre-jammy
COPY --from=builder target/*.jar pixie-document-service.jar
ENTRYPOINT ["java", "-jar", "pixie-document-service.jar" ]