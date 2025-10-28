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

# Zmienne środowiskowe aplikacji
ENV db.driverclassname=org.postgresql.Driver
ENV db.password=equinoxe
ENV db.url=jdbc:postgresql://192.168.1.32:5432/shploader_struktura?currentSchema=shp,public
ENV	db.username=tomek
ENV hibernate.dialect=org.hibernate.spatial.dialect.postgis.PostgisDialect
ENV hibernate.format_sql=true
ENV hibernate.generate_statistics=false
ENV hibernate.hbm2ddl.auto=update
ENV hibernate.show_sql=false
ENV hibernate.use-new-id-generator-mappings=true
ENV SHP_GESUT_DIRECTORY=/volume/gesut
ENV SHP_SWDE_DIRECTORY=/volume/swde
ENV SHP_SYTUACJA_DIRECTORY=/volume/sytuacja

# Parametry JVM
ENV JAVA_OPTS="-Xms3000m -Xmx3000m -XX:+UseParallelGC -Duser.timezone=Europe/Belgrade"

EXPOSE 8095

# Uruchomienie aplikacji z parametrami JVM
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar shploader.jar"]
