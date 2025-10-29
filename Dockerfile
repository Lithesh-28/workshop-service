# Step 1: Use a lightweight JDK base image
FROM openjdk:21-jdk-slim

# Step 2: Set working directory inside the container
WORKDIR /app

# Step 3: Copy your jar file into the container
COPY target/workshop-service-0.0.1-SNAPSHOT.jar workshop-service.jar

# Step 4: Expose the port your service runs on
EXPOSE 8082

# Step 5: Command to run the application
ENTRYPOINT ["java", "-jar", "workshop-service.jar"]
