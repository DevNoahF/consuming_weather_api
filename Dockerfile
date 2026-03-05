# Stage 1: Build
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /build

# Copiar arquivos de configuração Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
# Copiar código fonte
COPY src src

# Compilar a aplicação
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Copiar o JAR compilado do stage anterior
COPY --from=builder /build/target/*.jar app.jar

# Expor porta do Spring Boot
EXPOSE 8080

# Iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
