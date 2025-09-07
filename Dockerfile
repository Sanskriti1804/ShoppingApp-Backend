## Build stage
FROM gradle:8.7.0-jdk17 AS build
WORKDIR /app
COPY . /app
# Build fat jar (shadow or application). If shadow plugin isn't used, fall back to installDist
RUN gradle --no-daemon clean build -x test || true

## Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Create non-root user
RUN useradd -m -u 10001 appuser

# Utilities for healthcheck
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

# Copy built artifacts
COPY --from=build /app/build/libs /app/libs
COPY --from=build /app/build/resources/main /app/resources

ENV JAVA_OPTS=""
ENV TZ=Asia/Kolkata

# Expose Ktor default port
EXPOSE 8080

# Healthcheck: try to hit root
HEALTHCHECK --interval=30s --timeout=5s --retries=5 CMD curl -fsS http://localhost:8080/ || exit 1

USER appuser

# Try to run the fat jar if present, else classpath main
CMD bash -c 'FAT_JAR=$(ls /app/libs/*-all.jar 2>/dev/null | head -n 1) || true; \
if [ -n "$FAT_JAR" ]; then \
  echo "Running fat jar: $FAT_JAR"; \
  exec java $JAVA_OPTS -jar "$FAT_JAR"; \
else \
  JAR=$(ls /app/libs/*.jar | head -n 1); \
  echo "Running regular jar: $JAR"; \
  exec java $JAVA_OPTS -cp "$JAR:/app/resources" io.ktor.server.netty.EngineMain; \
fi'



