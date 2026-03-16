
FROM eclipse-temurin:25-jdk-jammy AS builder
WORKDIR /build

# Copiar arquivos de configuração Maven
COPY pom.xml .

RUN mvn depency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk-jammy
WORKDIR /app

# Copiar o JAR compilado do stage anterior
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

# Iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
