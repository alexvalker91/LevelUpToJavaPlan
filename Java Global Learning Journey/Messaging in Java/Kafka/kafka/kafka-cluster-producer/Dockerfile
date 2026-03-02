FROM eclipse-temurin:21-jdk

WORKDIR /app

# Копирование JAR-файла в контейнер
COPY kafka-cluster-producer/target/kafka-cluster-producer-1.0-SNAPSHOT.jar producer.jar

# Команда для запуска JAR-файла
CMD ["java", "-jar", "producer.jar"]