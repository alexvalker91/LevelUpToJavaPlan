FROM eclipse-temurin:21-jdk

WORKDIR /app

# Копирование JAR-файла в контейнер
COPY kafka-cluster-consumer/target/kafka-cluster-consumer-1.0-SNAPSHOT.jar consumer.jar

# Команда для запуска JAR-файла
CMD ["java", "-jar", "consumer.jar"]