# ---- Build Stage ----
FROM eclipse-temurin:17-jdk-alpine AS builder

# Install curl, unzip, and Maven
RUN apk add --no-cache curl tar

# Install Maven manually
ENV MAVEN_VERSION=3.9.6
RUN curl -fsSL https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xz -C /opt && \
    ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

# Set working directory
WORKDIR /app

# Copy your app's source code
COPY . .

# Build the project using Maven (skipping tests)
RUN mvn clean package -DskipTests

# ---- Run Stage ----
FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

# Copy JAR from builder image
COPY --from=builder /app/target/*.jar app.jar

# Start app
ENTRYPOINT ["java", "-jar", "/app.jar"]
