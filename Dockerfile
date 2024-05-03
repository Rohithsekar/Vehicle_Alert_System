# Use the official Maven image as a parent image
FROM maven:3.8.4-openjdk-11-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project descriptor
COPY pom.xml .

# Fetch all dependencies
RUN mvn dependency:go-offline

# Copy the project source code
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Use the official OpenJDK image as a parent image
FROM openjdk:11-jdk-slim AS final

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the build stage to the new image
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port the app runs on
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]