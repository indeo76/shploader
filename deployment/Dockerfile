# Etap 1: budowanie aplikacji
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Skopiuj źródła i plik pom.xml
COPY . .

# Buduj aplikację (pomija testy, jeśli nie są potrzebne)
RUN mvn clean package -DskipTests

# Etap 2: uruchomienie
FROM eclipse-temurin:11-jdk-alpine

WORKDIR /app

COPY --from=builder /build/target/shploader.jar shploader.jar

# Parametry JVM
ENV JAVA_OPTS="-Xms3000m -Xmx3000m -XX:+UseParallelGC -Duser.timezone=Europe/Belgrade"

EXPOSE 8095

# Uruchomienie aplikacji z parametrami JVM
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar shploader.jar"]
