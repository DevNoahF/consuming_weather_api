# ---------- STAGE 1: BUILD ----------
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copiar wrapper do Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Baixar dependências primeiro (melhora cache)
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copiar código fonte
COPY src src

# Gerar o JAR
RUN ./mvnw clean package -DskipTests


# ---------- STAGE 2: RUNTIME ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar JAR gerado
COPY --from=builder /app/target/*.jar app.jar

# Porta usada pelo Spring
EXPOSE 8080

# Variáveis recomendadas para container
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# Iniciar aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
