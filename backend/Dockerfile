# ---- Build Stage ----
FROM eclipse-temurin:17-jdk-alpine AS builder

# Install basic tools
RUN apk add --no-cache curl unzip

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Make mvnw executable if needed
RUN chmod +x mvnw

# Build the app (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# ---- Run Stage ----
FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

# Copy built JAR from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
