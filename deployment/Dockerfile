FROM eclipse-temurin:11-jdk-alpine

WORKDIR /app

COPY shploader.jar shploader.jar

# Zmienne środowiskowe aplikacji
ENV db.driverclassname=org.postgresql.Driver
ENV db.password=Wrzesnia2014
ENV db.url=jdbc:postgresql://172.20.200.40:5433/wrzesnia_shploader?currentSchema=shp,public
ENV	db.username=postgres
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
