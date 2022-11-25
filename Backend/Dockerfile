# This base image is required to install Maven and OpenJDK
FROM maven:3.8.6-openjdk-11

# This exposes port 8080 for Tomcat
EXPOSE 8080

# Adding folder into container
ADD nsightapi /nsightapi

# Set working directory inside container
WORKDIR /nsightapi

# Running maven inside container
RUN mvn package

# Switching to the created target folder
WORKDIR ./target

# Run the entrypoint inside Java
ENTRYPOINT ["java", "-jar", "./nsightapi-0.0.1-SNAPSHOT.jar"]