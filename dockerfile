# Use OpenJDK 21
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy gradle wrapper and build files
COPY gradle gradle
COPY gradlew .
COPY gradle.properties .
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src src

# Make gradlew executable
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew clean bootJar -x test

# Expose port that Render will use
EXPOSE 10000

# Set environment variable for port
ENV PORT=10000

# Run the application
CMD ["java", "-jar", "-Dserver.port=${PORT}", "build/libs/expenses.jar"]