# Use the official OpenJDK 8 image as the base image
FROM openjdk:8

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build context to the /app directory in the image
COPY target/*.jar app.jar

# Expose the port the application will run on
EXPOSE 9096

# Specify the command to run on container start
ENTRYPOINT ["java", "-jar", "/app.jar"]