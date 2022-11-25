FROM eclipse-temurin:17-jdk-jammy

WORKDIR /backend

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

COPY src ./nsightapi/src

CMD ["./mvnw", "spring-boot:run"]


