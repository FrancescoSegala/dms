FROM adoptopenjdk:8u212-b04-jdk-openj9-0.14.2
COPY target/*.jar app.jar
EXPOSE 8090
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar","--spring.dbconsistenze.url=jdbc:sqlserver://${DATABASE_SERVER};database=summer_consistenze_imp","--spring.dbconsistenze.username=${DB_USER}","--spring.dbconsistenze.password=${DB_PASSWORD}","--spring.dbcataloghi.url=jdbc:sqlserver://${DATABASE_SERVER};database=summer_cataloghi","--spring.dbcataloghi.username=${DB_USER}","--spring.dbcataloghi.password=${DB_PASSWORD}","--spring.kafka.bootstrap-servers=${KAFKA_SERVERS}"]

