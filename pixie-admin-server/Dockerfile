FROM eclipse-temurin:17-jdk-jammy as builder
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
RUN sh -c 'touch /pixie-admin-server.jar'


FROM amd64/eclipse-temurin:17-jre-jammy
COPY --from=builder target/*.jar pixie-admin-server.jar
ENTRYPOINT ["java", "-jar", "pixie-admin-server.jar" ]