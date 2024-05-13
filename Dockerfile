FROM openjdk:17

WORKDIR /app

COPY . /app

COPY ./build/libs /app

EXPOSE 8081

ENTRYPOINT [ "java","-jar","/app/platform-0.0.1-SNAPSHOT-plain.jar" ]

RUN ls /app
