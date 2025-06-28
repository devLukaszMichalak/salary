FROM cypress/browsers:node-24.0.0-chrome-136.0.7103.92-1-ff-138.0.1-edge-136.0.3240.50-1 AS frontend-builder

WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm ci

COPY frontend/ ./
RUN npm run lint
RUN npm run test-nowatch
RUN npm run build

FROM maven:3.9.10-eclipse-temurin-24-alpine AS backend-builder

WORKDIR /app/backend

COPY backend/pom.xml ./
RUN mvn dependency:go-offline

COPY backend/ ./
RUN mvn spotless:check

COPY --from=frontend-builder /app/frontend/dist/frontend/browser/* /app/backend/src/main/resources/static
COPY data/salary-dev.db data/salary-dev.db

RUN mvn clean package

FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app
COPY data/salary.db /app/data/salary.db
COPY --from=backend-builder /app/backend/target/*.jar app.jar
VOLUME /app/data
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]