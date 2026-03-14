# ---------- STAGE 1: BUILD ----------
FROM eclipse-temurin:25-jdk-alpine AS builder

WORKDIR /app

# Copiar arquivo de configuração do Maven
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Dar permissão de execução e baixar dependências
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline

# Copiar código fonte
COPY src src

# Build da aplicação
RUN ./mvnw clean package -DskipTests



FROM eclipse-temurin:25-jre-alpine

# Adicionar usuário não-root por segurança
RUN addgroup -g 1000 appuser && \
    adduser -D -u 1000 -G appuser appuser

WORKDIR /app

# Copiar JAR do stage anterior
COPY --from=builder /app/target/*.jar app.jar

# Alterar proprietário do arquivo
RUN chown appuser:appuser app.jar

# Usar usuário não-root
USER appuser

# Porta padrão do Spring Boot
EXPOSE 8080

# Variáveis JVM otimizadas para container
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -XX:+ParallelRefProcEnabled"

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Iniciar aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
