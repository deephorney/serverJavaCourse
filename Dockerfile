FROM openjdk:21

WORKDIR /app

COPY . /app

COPY build /app/

EXPOSE 8085

CMD ["java", "-jar", "/app/build/libs/platform-0.0.1-SNAPSHOT-plain.jar"]