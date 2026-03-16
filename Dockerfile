
FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /build

# Copiar arquivos de configuração Maven
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline

# Copiar código fonte e compilar
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk-jammy
WORKDIR /app

# Copiar o JAR compilado do stage anterior
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
