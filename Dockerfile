FROM openjdk:21

WORKDIR /app

COPY . /app

EXPOSE 8085

CMD sh -c './gradlew build && for f in /app/build/libs/*.jar; do java -jar "$f"; done'
